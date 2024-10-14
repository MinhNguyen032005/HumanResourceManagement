package utilities;

import data.nhanVien.NhanVien;

import java.util.Comparator;

public class PositionComparator implements Comparator<NhanVien> {
    @Override
    public int compare(NhanVien o1, NhanVien o2) {
        return o1.getPosition().compareTo(o2.getPosition());
    }
}
