package pl.umcs.dto;

public class Job {
    private long id;

    Job(){}

    public Job(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
