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
    public String sign(@RequestBody Job jobWithIdOnly){
        Job job = jobService.getRestOfData(jobWithIdOnly);
        if(job == null){
            return "Job hasn't been found";
        }
        if(jobService.tasksNotEmpty(job)) {
            jobService.signJob(job);
            return "Job has been signed";
        }
        return "Job hasn't been signed: tasks list empty";
    }

    @PostMapping(path = "/addTask", consumes = APPLICATION_JSON_VALUE)
    public String add(@RequestBody TaskToAdd taskToAdd){
        Job job = jobService.getJobById(taskToAdd.getJobId());
        if (job == null){
            return "Job hasn't been found";
        }
        jobService.addTask(job, taskToAdd.getDescription());
        return "Task has been added";
    }
}
