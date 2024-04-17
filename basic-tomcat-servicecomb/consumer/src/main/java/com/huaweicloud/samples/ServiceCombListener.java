package com.huaweicloud.samples;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.management.Query;

import com.huaweicloud.servicecomb.discovery.registry.ServiceCombAutoServiceRegistration;
import com.huaweicloud.servicecomb.discovery.registry.ServiceCombRegistration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.ConfigurableWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ServiceCombListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ServiceCombAutoServiceRegistration registration;

    @Autowired
    private ServiceCombRegistration serviceCombRegistration;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ApplicationContext context = event.getApplicationContext();
        if (context instanceof ConfigurableWebServerApplicationContext) {
            if ("management".equals(((ConfigurableWebServerApplicationContext) context).getServerNamespace())) {
                return;
            }
        }

        // 如何需要注册的实例端口为tomcat端口，将实例信息中的endpoint端口进行调整
//        buildInstanceWithTomcatPort();

        registration.start();
    }

    private void buildInstanceWithTomcatPort() {
        List<String> endPoints = new ArrayList<>();
        String endPoint = "";
        if (!serviceCombRegistration.getMicroserviceInstance().getEndpoints().isEmpty()) {
            endPoint = serviceCombRegistration.getMicroserviceInstance().getEndpoints().get(0);
        }
        if (StringUtils.isEmpty(endPoint)) {
            return;
        }
        if (endPoint.contains("sslEnabled")) {
            endPoints.add(endPoint.substring(0, endPoint.lastIndexOf(':') + 1) + getTomcatPort()
                + endPoint.substring(endPoint.indexOf("?sslEnabled=")));
        } else {
            endPoints.add(endPoint.substring(0, endPoint.lastIndexOf(':') + 1) + getTomcatPort());
        }
        serviceCombRegistration.getMicroserviceInstance().setEndpoints(endPoints);
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
