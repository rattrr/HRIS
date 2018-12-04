package pl.umcs;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface DeskRepository extends CrudRepository<Desk, Long> {
    Optional<Desk> getDeskByEmployeeIdEquals(Long employeeId);
    List<Desk> getAllByEmployeeIdIsNull();
}
