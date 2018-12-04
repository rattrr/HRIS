package pl.umcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeskService {
    private final DeskRepository deskRepository;

    @Autowired
    public DeskService(DeskRepository deskRepository) {
        this.deskRepository = deskRepository;
    }

    public List<Desk> getAll(){
        Iterable<Desk> deskIterable = deskRepository.findAll();
        return StreamSupport.stream(deskIterable.spliterator(), false).collect(Collectors.toList());
    }

    public Desk getDeskOfEmployee(long employeeId){
        Optional<Desk> desk = deskRepository.getDeskByEmployeeIdEquals(employeeId);
        return desk.orElse(null);
    }

    public Desk addNewDesk(){
        return deskRepository.save(new Desk());
    }

    public List<Desk> getUnassignedDesks(){
        return deskRepository.getAllByEmployeeIdIsNull();
    }

    public void assignDesk(Long employeeId, long deskId){
        if(employeeHasNoOtherDesk(employeeId) && deskIsUnassigned(deskId)){
            Desk deskToAssign = getDeskById(deskId);
            deskToAssign.setEmployeeId(employeeId);
            deskRepository.save(deskToAssign);
        }
    }

    public void reassignDesk(Long employeeId, long deskId){
        if(employeeHasNoOtherDesk(employeeId) && deskExists(deskId)){
            Desk deskToAssign = getDeskById(deskId);
            deskToAssign.setEmployeeId(employeeId);
            deskRepository.save(deskToAssign);
        }
    }

    public Desk getDeskById(long deskId){
        Optional<Desk> desk = deskRepository.findById(deskId);
        return desk.orElse(null);
    }

    private boolean deskIsUnassigned(long deskId){
        if(!deskExists(deskId)){
            return false;
        }
        return getUnassignedDesks().contains(getDeskById(deskId));
    }

    private boolean deskExists(long deskId){
        return getDeskById(deskId) != null;
    }

    private boolean employeeHasNoOtherDesk(Long employeeId){
        return getDeskOfEmployee(employeeId) == null;
    }
}
