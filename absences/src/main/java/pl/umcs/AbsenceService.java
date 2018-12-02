package pl.umcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
