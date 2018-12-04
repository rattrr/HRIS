package pl.umcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/desks")
public class DeskController {
    private final DeskService deskService;

    @Autowired
    public DeskController(DeskService deskService) {
        this.deskService = deskService;
    }

    @GetMapping(path = "/all", produces = APPLICATION_JSON_VALUE)
    public List<Desk> getDesks(){
        return deskService.getAll();
    }

    @GetMapping(path = "/unassigned", produces = APPLICATION_JSON_VALUE)
    public List<Desk> getUnassignedDesks(){
        return deskService.getUnassignedDesks();
    }

    @PostMapping(path = "/employee", consumes = APPLICATION_JSON_VALUE)
    public Desk getDeskOfEmployee(@RequestBody Desk desk){
        return deskService.getDeskOfEmployee(desk.getEmployeeId());
    }

    @PostMapping(path = "/assign", consumes = APPLICATION_JSON_VALUE)
    public void assign(@RequestBody Desk desk){
        deskService.assignDesk(desk.getEmployeeId(), desk.getId());
    }

    @PostMapping(path = "/reassign", consumes = APPLICATION_JSON_VALUE)
    public void reassign(@RequestBody Desk desk){
        deskService.reassignDesk(desk.getEmployeeId(), desk.getId());
    }
}
