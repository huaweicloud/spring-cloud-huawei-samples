package com.huaweicloud.samples;

import com.google.common.eventbus.Subscribe;
import com.huaweicloud.common.event.EventManager;
import org.apache.servicecomb.config.common.ConfigurationChangedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 监听配置变化
 */
@Component
public class ConfigListen {

    @Value("${name}")
    String name;

    public ConfigListen() {
        EventManager.register(this);
    }

    @Subscribe
    public void onConfigurationChangedEvent(ConfigurationChangedEvent event) {
        if(event.getUpdated().containsKey("name")){
            System.out.println("name updated");
        }
    }
}
