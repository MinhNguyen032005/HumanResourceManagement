package view;

import controller.IController;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PanelBottom extends JPanel {
    JButton them, luu, xoa, sua, export, dong;
    JButton[] jButtons = {them, luu, xoa, sua, export, dong};
    public PanelBottom(IController iController) {
        String[] nameIcon = {"plus-sign.png,save-data.png,delete-friend.png,edit-tool.png,export-file.png,close.png"};
        String[] nameButton = {"Thêm,Lưu ,Xóa, Sửa,Export,Đóng"};
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