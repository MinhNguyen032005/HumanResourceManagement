package view;

import controller.IController;

import javax.swing.*;
import java.awt.*;

public class PanelTopManagenment extends JPanel {
    JButton btns;
    JButton trangChu, danhSachNhanVien, hoSoNhanVien, nghiPhep, chamCongThag, thongTinLuongNV, bangLuongChiTiet;
    JButton[] jButtons = {trangChu, danhSachNhanVien, hoSoNhanVien, nghiPhep, chamCongThag, thongTinLuongNV, bangLuongChiTiet};

    public PanelTopManagenment(IController iController) {
        String[] nameButton = {"Trang chủ", "Danh sách nhân viên", "Hồ sơ nhân viên", "Nghỉ phép", "Chấm Công", "Thông tin lương NV", "Bảng lương chi tiết"};
        setLayout(new FlowLayout());
        for (int i = 0; i < nameButton.length; i++) {
            jButtons[i] = new JButton(nameButton[i]);
            btns = jButtons[i];
            this.add(btns);
        }
    }
}
