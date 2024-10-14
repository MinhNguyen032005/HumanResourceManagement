package view;

import controller.IControllerManagenment;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelLeftManagenment extends JPanel {
    JButton btn;
    ArrayList<JButton> jButtons;

    public PanelLeftManagenment(IControllerManagenment iController) {
        ArrayList<String> stringButton = new ArrayList<>();
        stringButton.add("Báo cáo");
        stringButton.add("Lịch làm việc");
        stringButton.add("Công việc");
        stringButton.add("Danh sách NV");
        stringButton.add("Thông tin lương NV");
        Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
        setBackground(Color.WHITE);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (int i = 0; i < stringButton.size(); i++) {
            this.add(Box.createVerticalStrut(30));
            btn = new JButton(stringButton.get(i));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setFont(FontLoader.loadCustomizeFont(robotoMedium, 17f));
            btn.setPreferredSize(new Dimension(190, 30));
            btn.setBackground(new Color(0, 0, 0, 0));
            btn.setFocusable(false);
            btn.setOpaque(false);
            btn.setBorder(null);
            btn.setSize(50, 50);
            btn.addActionListener(iController.controlButtonLeft());
            this.add(btn);
        }
    }
}
