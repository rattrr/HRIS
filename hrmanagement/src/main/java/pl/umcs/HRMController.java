package pl.umcs;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
public class HRMController
{
    @Qualifier("eurekaClient")
    @Autowired
    private EurekaClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getEmployees")
    public String getEmployees(){
        String appUrl = findUrlOfApplication("employees");
        System.out.println(appUrl);
        return restTemplate.getForObject(appUrl, String.class);
    }

    private String findUrlOfApplication(String applicationName){
        Application application = eurekaClient.getApplication(applicationName);
        List<InstanceInfo> instances = application.getInstances();
        InstanceInfo instanceInfo = instances.iterator().next();
        String hostname = instanceInfo.getHostName();
        int port = instanceInfo.getPort();
        return "http://"+hostname+":"+port+"/"+applicationName;
    }
}
