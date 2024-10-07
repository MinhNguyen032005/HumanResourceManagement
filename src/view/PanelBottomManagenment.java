package view;

import controller.IController;

import javax.swing.*;
import java.awt.*;

public class PanelBottomManagenment extends JPanel {
    JButton btns;
    JButton them, luu, xoa, sua, export, dong;
    JButton[] jButtons = {them, luu, xoa, sua, export, dong};
    public PanelBottomManagenment(IController iController) {
        String[] nameButton = {"Thêm","Lưu ","Xóa", "Sửa","Export","Đóng"};
        setLayout(new FlowLayout());
        for (int i = 0; i < nameButton.length; i++) {
            jButtons[i] = new JButton(nameButton[i]);
            btns=jButtons[i];
            this.add(btns);
        }
    }
}
