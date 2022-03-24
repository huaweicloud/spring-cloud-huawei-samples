package org.apache.servicecomb.samples.porter.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringCloudGatewayMain {
    public static void main(String[] args) throws Exception {
        try {
            SpringApplication.run(SpringCloudGatewayMain.class, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
