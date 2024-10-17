package data.congViec;

import java.util.Comparator;

public class CongViec{
    private String title;
    private String deception;
    private String date;
    private String position;
    private int num;

    public CongViec(String title, String deception, String date, String position,int num) {
        this.title = title;
        this.deception = deception;
        this.date = date;
        this.position = position;
        this.num=num;
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
}
