package view;

import controller.IController;

import javax.swing.*;

public class PanelBottomManagenment extends JPanel {
    JButton them, luu, xoa, sua, export, dong;
    JButton[] jButtons = {them, luu, xoa, sua, export, dong};
    public PanelBottomManagenment(IController iController) {
        String[] nameIcon = {"plus-sign.png,save-data.png,delete-friend.png,edit-tool.png,export-file.png,close.png"};
        String[] nameButton = {"Thêm,Lưu ,Xóa, Sửa,Export,Đóng"};
        for (int i = 0; i < nameIcon.length; i++) {
            jButtons[i] = new JButton(nameButton[i]);
            JButton btns=jButtons[i];
            this.add(btns);
        }
    }
}
