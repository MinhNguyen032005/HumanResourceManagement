package view;

import java.awt.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends MenuBar {
    Menu nhanSu, chamCong, tienLuong, daoTao, tuyenDung, danhGia;

    public MyMenuBar() {
        nhanSu = new Menu("1.Nhân " + "sự");
        chamCong = new Menu("2.chấm" + "công");
        tienLuong = new Menu("3.Tiền lương");
        daoTao = new Menu("4.Đào tạo");
        tuyenDung = new Menu("5.Tuyển dụng");
        danhGia = new Menu("6.Đánh " + "giá");
        add(nhanSu);
        add(chamCong);
        add(tienLuong);
        add(daoTao);
        add(tuyenDung);
        add(danhGia);

    }
}
