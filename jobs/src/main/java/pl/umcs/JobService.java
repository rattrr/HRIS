package pl.umcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JobService {
    private final JobRepository jobRepository;

    @Autowired
    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
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

    private boolean isNotNull(Job job){
        return job != null;
    }

    public boolean tasksNotEmpty(Job job){
        return !job.getTasks().isEmpty();
    }

    public Job getRestOfData(Job job){
        return jobRepository.findById(job.getId()).orElse(null);
    }
}
