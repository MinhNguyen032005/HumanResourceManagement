package view;

import controller.IController;

import javax.swing.*;

public class PanelTopManagenment extends JPanel {
    JButton trangChu, danhSachNhanVien, hoSoNhanVien, nghiPhep, chamCongThag, thongTinLuongNV, bangLuongChiTiet;
    JButton[] jButtons = {trangChu, danhSachNhanVien, hoSoNhanVien, nghiPhep, chamCongThag, thongTinLuongNV, bangLuongChiTiet};

    public PanelTopManagenment(IController iController) {
        String[] nameIcon = {"house.png,man.png,man.png,man.png,clipboard.png,notes.png,clipboard.png"};
        String[] nameButton = {"Trang chủ, Danh sách nhân viên, Hồ sơ nhân viên, Nghỉ phép, Chấm Công, Thông tin lương NV, Bảng lương chi tiết "};
        for (int i = 0; i < nameIcon.length; i++) {
            jButtons[i] = new JButton(nameButton[i]);
            JButton btns = jButtons[i];
            this.add(btns);
        }
    }
}