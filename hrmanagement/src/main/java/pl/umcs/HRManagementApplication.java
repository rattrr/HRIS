package pl.umcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class HRManagementApplication {


    public static void main(String[] args) {
        SpringApplication.run(HRManagementApplication.class, args);
    }

}
