package data.congViec;

import java.time.LocalDate;

public class Job {
    private String position;   // Chức vụ
    private String jobTitle;   // Công việc
    private String duty;       // Nhiệm vụ
    private LocalDate date;    // Ngày

    public Job(String position, String jobTitle, String duty, LocalDate date) {
        this.position = position;
        this.jobTitle = jobTitle;
        this.duty = duty;
        this.date = date;
    }

    public String getPosition() { return position; }
    public String getJobTitle() { return jobTitle; }
    public String getDuty() { return duty; }
    public LocalDate getDate() { return date; }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

}
