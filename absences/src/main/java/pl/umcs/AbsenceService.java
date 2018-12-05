package pl.umcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AbsenceService {
    private final AbsenceRepository absenceRepository;

    @Autowired
    public AbsenceService(AbsenceRepository absenceRepository){
        this.absenceRepository = absenceRepository;
    }

    public List<Absence> getAll(){
        Iterable<Absence> absencesIterable = absenceRepository.findAll();
        return StreamSupport.stream(absencesIterable.spliterator(), false).collect(Collectors.toList());
    }

    public boolean isEmployeeAvailableNow(long employeeId){
        return isEmployeePresentBetween(LocalDate.now(), LocalDate.now(), employeeId);
    }

    public Absence save(Absence absence){
        if(isEmployeePresentBetween(absence.getBeginning(), absence.getEnd(), absence.getEmployeeId())) {
            Absence savedAbsence = absenceRepository.save(absence);
            return savedAbsence;
        }
        return null;
    }

    private boolean isEmployeePresentBetween(LocalDate beginning, LocalDate end, long employeeId){
        List<Absence> absences = absenceRepository.getAbsencesByBeginningLessThanEqualAndEndGreaterThanEqualAndEmployeeIdEquals(beginning, end, employeeId);
        return absences.isEmpty();
    }

}
