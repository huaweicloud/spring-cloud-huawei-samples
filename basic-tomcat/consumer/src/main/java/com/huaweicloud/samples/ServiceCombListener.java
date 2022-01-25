package com.huaweicloud.samples;

import com.huaweicloud.servicecomb.discovery.registry.ServiceCombAutoServiceRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.web.context.ConfigurableWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;

public class ServiceCombListener implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    private ServiceCombAutoServiceRegistration registration;

    @Override
    @Deprecated
    public void onApplicationEvent(ApplicationReadyEvent event) {
        ApplicationContext context = event.getApplicationContext();
        if (context instanceof ConfigurableWebServerApplicationContext) {
            if ("management".equals(((ConfigurableWebServerApplicationContext) context).getServerNamespace())) {
                return;
            }
        }
        registration.start();
    }
}
