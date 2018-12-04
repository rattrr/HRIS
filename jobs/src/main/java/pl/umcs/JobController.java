package pl.umcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/jobs")
public class JobController {
    private final JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping
    public List<Job> getAll(){
        return jobService.getAll();
    }

    @PostMapping(path = "/ofEmployee", consumes = APPLICATION_JSON_VALUE)
    public List<Job> getAllJobsOfEmployee(@RequestBody Job job){
        return jobService.getAllJobsOfEmployee(job.getEmployeeId());
    }

    @PostMapping(path = "/sign", consumes = APPLICATION_JSON_VALUE)
    public Job test(@RequestBody Job job){
        jobService.signJob(job);
        return job;
    }
}
