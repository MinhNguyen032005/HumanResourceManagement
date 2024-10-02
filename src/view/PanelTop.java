package view;

import controller.IController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanelTop extends JPanel {
    JButton trangChu,danhSachNhanVien,hoSoNhanVien,nghiPhep,chamCongThag,thongTinLuongNV,bangLuongChiTiet;
    JButton[] jButtons={trangChu,danhSachNhanVien,hoSoNhanVien,nghiPhep,chamCongThag,thongTinLuongNV,bangLuongChiTiet};

    public PanelTop(IController iController) {
        String[] nameIcon = {"house.png,man.png,man.png,man.png,clipboard.png,notes.png,clipboard.png"};
        String[] nameButton = {"Trang chủ, Danh sách nhân viên, Hồ sơ nhân viên, Nghỉ phép, Chấm Công, Thông tin lương NV, Bảng lương chi tiết "};
        for (int i = 0; i < nameIcon.length; i++) {
            URL url = PanelTop.class.getResource("/img/" + nameIcon[i]);
            Image img = Toolkit.getDefaultToolkit().createImage(url);
            ImageIcon icon = new ImageIcon(img);
            jButtons[i] = new JButton(nameButton[i]);
            JButton btns=jButtons[i];
            btns.setIcon(icon);
            this.add(btns);
        }
    }
}
