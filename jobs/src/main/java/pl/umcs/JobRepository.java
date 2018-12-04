package pl.umcs;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface JobRepository extends CrudRepository<Job, Long> {
    List<Job> getAllByEmployeeIdEquals(long employeeId);
}
