package data.nhanVien;

import java.util.Comparator;
//đây là lớp dùng để định nghĩa chức năng nhân viên
public class NhanVien {
    private static final long serialVersionUID = 1L;
    private String id;
    private String name;
    private String gender;
    private String date;
    private String  position;
    private double wage;

    public NhanVien(String id, String name, String gender, String date, String position) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.position = position;
    }

    public NhanVien(String id, String name, String gender, String date, String position, double wage) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.position = position;
        this.wage = wage;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getDate() {
        return date;
    }

    public String getPosition() {
        return position;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getWage() {
        return wage;
    }

    public void setWage(double wage) {
        this.wage = wage;
    }

    public double wage() {
        if (position.equals("Ky su")){
            return 15000;
        } else if (position.equals("Ke toan")) {
            return 10000;
        }else {
            return 30000;
        }
    }
}
