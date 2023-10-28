package com.huaweicloud.samples;

import java.lang.management.ManagementFactory;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;

import com.alibaba.cloud.nacos.registry.NacosAutoServiceRegistration;
import com.alibaba.cloud.nacos.registry.NacosRegistration;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.ConfigurableWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NacosListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private NacosRegistration registration;

    @Autowired
    private NacosAutoServiceRegistration nacosAutoServiceRegistration;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ApplicationContext context = event.getApplicationContext();
        System.out.println("-----------------------------------------------registry start");
        if (context instanceof ConfigurableWebServerApplicationContext) {
            if ("management".equals(((ConfigurableWebServerApplicationContext) context).getServerNamespace())) {
                return;
            }
        }
        //如果使用tomcat端口，使用getTomcatPort()方法获取；如果用配置文件设置端口，不执行getTomcatPort()方法
        String property = ""; //getTomcatPort();
        if (StringUtils.isEmpty(property)) {
            property = event.getApplicationContext().getEnvironment().getProperty("server.port");
        }
        registration.setPort(Integer.parseInt(property));
        nacosAutoServiceRegistration.start();
        System.out.println("-----------------------------------------------registry end");
    }

    /**
     *   获取外部tomcat端口
     */
    public String getTomcatPort() {
        try {
            MBeanServer beanServer = ManagementFactory.getPlatformMBeanServer();
            Set<ObjectName> objectNames = beanServer.queryNames(new ObjectName("*:type=Connector,*"),
                Query.match(Query.attr("protocol"), Query.value("HTTP/1.1")));
            return objectNames.iterator().next().getKeyProperty("port");
        } catch (Exception e) {
            System.out.println("get port exception-------------------");
            return "";
        }
    }
}
