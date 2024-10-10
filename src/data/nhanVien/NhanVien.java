package data.nhanVien;

public class NhanVien {
    private String id;
    private String name;
    private String gender;
    private String date;
    private String  position;

    public NhanVien(String id, String name, String gender, String date, String position) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.date = date;
        this.position = position;
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

    @Override
    public String toString() {
        return "NhanVien{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", date='" + date + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
