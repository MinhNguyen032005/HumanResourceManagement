package utilities;

import data.nhanVien.NhanVien;

import java.util.Comparator;

public class WageComparator implements Comparator<NhanVien> {
    @Override
    public int compare(NhanVien o1, NhanVien o2) {
        return Integer.compare((int) o2.getWage(),(int) o1.getWage());
    }
}
