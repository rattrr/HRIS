package pl.umcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/absences")
public class AbsenceController {
    private final AbsenceService absenceService;

    @Autowired
    public AbsenceController(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public List<Absence> getEmployees(){
        return absenceService.getAll();
    }

    @GetMapping("/av")
    public String getAvailability(){
        return String.valueOf(absenceService.isEmployeeAvailableNow(1));
    }

    @PostMapping(path = "/add", consumes = APPLICATION_JSON_VALUE)
    public Absence addAbsence(@RequestBody Absence absence){
        absenceService.save(absence);
        return absence;
    }
}
