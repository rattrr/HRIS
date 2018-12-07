package pl.umcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JobService {
    private final JobRepository jobRepository;
    private final TaskRepository taskRepository;

    @Autowired
    public JobService(JobRepository jobRepository, TaskRepository taskRepository) {
        this.jobRepository = jobRepository;
        this.taskRepository = taskRepository;
    }

    public Job getJobById(long id){
        return jobRepository.findById(id).orElse(null);
    }

    public List<Job> getAll(){
        return StreamSupport.stream(jobRepository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    public List<Job> getAllJobsOfEmployee(long employeeId){
        return jobRepository.getAllByEmployeeIdEquals(employeeId);
    }

    public Job signJob(Job job){
        job.setSigned(true);
        jobRepository.save(job);
        return job;
    }

    public void addTask(Job job, String description){
        Task task = new Task(job, description);
        job.addTask(task);
        jobRepository.save(job);
        taskRepository.save(task);
    }

    public boolean tasksNotEmpty(Job job){
        return !job.getTasks().isEmpty();
    }

    public Job getRestOfData(Job job){
        return getJobById(job.getId());
    }
}
