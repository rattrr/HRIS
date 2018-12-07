package pl.umcs;

public class NewTask {
    long jobId;
    String description;

    NewTask(){}

    public NewTask(long jobId, String description) {
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
