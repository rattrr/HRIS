package pl.umcs;

public class TaskToAdd {
    long jobId;
    String description;

    TaskToAdd(){}

    public TaskToAdd(long jobId, String description) {
        this.jobId = jobId;
        this.description = description;
    }

    public long getJobId() {
        return jobId;
    }

    public void setJobId(long jobId) {
        this.jobId = jobId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
