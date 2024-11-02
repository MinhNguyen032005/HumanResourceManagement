package data.nghiPhep;

import data.nhanVien.NhanVien;

import java.util.Comparator;
import java.util.Objects;
//đây là lớp dùng để định nghĩa chức năng nghỉ phép
public class NghiPhep  {
    String id;
    String name;
    String title;
    String reason;
    String date;

    public NghiPhep(String id, String name, String title, String reason, String date) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.reason = reason;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getReason() {
        return reason;
    }

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NghiPhep nghiPhep = (NghiPhep) o;
        return Objects.equals(id, nghiPhep.id) && Objects.equals(name, nghiPhep.name) && Objects.equals(title, nghiPhep.title) && Objects.equals(reason, nghiPhep.reason) && Objects.equals(date, nghiPhep.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, title, reason, date);
    }
}
