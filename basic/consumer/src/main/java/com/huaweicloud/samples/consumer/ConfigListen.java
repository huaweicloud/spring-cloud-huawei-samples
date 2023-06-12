package com.huaweicloud.samples.consumer;

import com.google.common.eventbus.Subscribe;
import org.apache.servicecomb.governance.event.GovernanceConfigurationChangedEvent;
import org.apache.servicecomb.governance.event.GovernanceEventManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Examples to show how to listen configuration change
 */
@Component
public class ConfigListen {

    @Value("${name:}")
    String name;

    public ConfigListen() {
        GovernanceEventManager.register(this);
    }

    @Subscribe
    public void onEnvironmentChangeEvent(GovernanceConfigurationChangedEvent event) {
        if(event.getChangedConfigurations().contains("name")){
            System.out.println("------------name updated----------------");
        }
    }
}
