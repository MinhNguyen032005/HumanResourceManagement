package data.congViec;

import java.time.LocalDate;
import java.util.Objects;

public class Job {

    private String position;   // Chức vụ
    private String jobTitle;   // Công việc
    private String duty;       // Nhiệm vụ
    private String date;    // Ngày

    public Job(String position, String jobTitle, String duty, String date) {
        this.position = position;
        this.jobTitle = jobTitle;
        this.duty = duty;
        this.date = date;
    }

    public String getPosition() { return position; }
    public String getJobTitle() { return jobTitle; }
    public String getDuty() { return duty; }
    public String getDate() { return date; }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return Objects.equals(getPosition(), job.getPosition()) && Objects.equals(getJobTitle(), job.getJobTitle()) && Objects.equals(getDuty(), job.getDuty()) && Objects.equals(getDate(), job.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPosition(), getJobTitle(), getDuty(), getDate());
    }

    @Override
    public String toString() {
        return "Job{" +
                "position='" + position + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", duty='" + duty + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
