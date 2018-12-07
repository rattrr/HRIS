package pl.umcs;

import javax.persistence.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long employeeId;
    @OneToMany(mappedBy = "job")
    List<Task> tasks = new ArrayList<>();
    private Month month;
    private boolean isSigned = false;

    public Job(){}

    public Job(long employeeId, Month month) {
        this.employeeId = employeeId;
        this.month = month;
    }

    public Job(long id, long employeeId, Month month) {
        this.id = id;
        this.employeeId = employeeId;
        this.month = month;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(long employeeId) {
        this.employeeId = employeeId;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTask(Task task){
        this.tasks.add(task);
    }

    public boolean isSigned() {
        return isSigned;
    }

    public void setSigned(boolean signed) {
        isSigned = signed;
    }
}
