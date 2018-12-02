package pl.umcs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAll(){
        Iterable<Employee> employeesIterable = employeeRepository.findAll();
        return StreamSupport.stream(employeesIterable.spliterator(), false).collect(Collectors.toList());
    }

    public Employee getById(long id){
        Optional<Employee> employee = employeeRepository.findById(id);
        return employee.orElse(null);
    }

}
