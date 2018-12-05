package pl.umcs.api;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import pl.umcs.dto.Leave;

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
        return restTemplate.getForObject(appUrl, String.class);
    }

    @PostMapping("/takeLeave")
    public String takeLeave(@RequestBody  Leave leave){
        String appUrl = findUrlOfApplication("absences") + "/add";
        return restTemplate.postForObject(appUrl, leave, String.class);
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
