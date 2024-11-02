package data.chamCong;

/**
 * đây là lớp dùng để định nghĩa chức năng chấm công
 */
public class ChamCong {
    String id;
    String name;
    String date;
    String rank;
    String inTime;
    String outTime;
    int late;
    int timeWork;

    public ChamCong(String id, String name, String date, String rank, String inTime, String outTime, int timeWork, int late) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.rank = rank;
        this.inTime = inTime;
        this.outTime = outTime;
        this.late = late;
        this.timeWork = timeWork;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDate() {
        return date;
    }

    public String getRank() {
        return rank;
    }

    public String getInTime() {
        return inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public int getLate() {
        return late;
    }

    public int getTimeWork() {
        return timeWork;
    }

}
