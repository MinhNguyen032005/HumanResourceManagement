package utilities;

import data.nhanVien.NhanVien;

import java.util.Comparator;
//Class này dùng để hiện thực comparator nhân viên để sắp xếp nhân viên

public class PositionComparator implements Comparator<NhanVien> {
    @Override
    public int compare(NhanVien o1, NhanVien o2) {
        return o1.getPosition().compareTo(o2.getPosition());
    }
}
