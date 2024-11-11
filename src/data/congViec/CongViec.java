package data.congViec;

import java.util.Comparator;
import java.util.Objects;

//đây là lớp dùng để định nghĩa chức năng công việc
public class CongViec {
    private String title;
    private String deception;
    private String date;
    private String position;
    private int num;
    private boolean isRead;


    public CongViec(String title, String deception, String date, String position, int num) {
        this.title = title;
        this.deception = deception;
        this.date = date;
        this.position = position;
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public String getDeception() {
        return deception;
    }

    public String getDate() {
        return date;
    }

    public String getPosition() {
        return position;
    }

    public int getNum() {
        return num;
    }

    public boolean isRead() {
        return isRead;
    }

    public void setRead(boolean isRead) {
        this.isRead = isRead;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CongViec congViec = (CongViec) o;
        return getNum() == congViec.getNum() && isRead() == congViec.isRead() && Objects.equals(getTitle(), congViec.getTitle()) && Objects.equals(getDeception(), congViec.getDeception()) && Objects.equals(getDate(), congViec.getDate()) && Objects.equals(getPosition(), congViec.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getDeception(), getDate(), getPosition(), getNum(), isRead());
    }

    @Override
    public String toString() {
        return "CongViec{" +
                "title='" + title + '\'' +
                ", deception='" + deception + '\'' +
                ", date='" + date + '\'' +
                ", position='" + position + '\'' +
                ", num=" + num +
                ", isRead=" + isRead +
                '}';
    }
}
