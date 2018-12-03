package pl.umcs;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface AbsenceRepository extends CrudRepository<Absence, Long> {
    List<Absence> getAbsencesByBeginningLessThanEqualAndEndGreaterThanEqualAndEmployeeIdEquals(LocalDate date, LocalDate theSameDate, long id);
}
