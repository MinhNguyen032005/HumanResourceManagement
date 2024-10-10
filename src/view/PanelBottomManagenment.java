package view;

import controller.IControllerManagenment;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PanelBottomManagenment extends JPanel {
    JButton btn;
    public PanelBottomManagenment(IControllerManagenment iController) {
        Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
        ArrayList<String> stringButton=new ArrayList<>();
        stringButton.add("<- Đăng xuất");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        for (int i = 0; i < stringButton.size(); i++) {
            btn = new JButton(stringButton.get(i));
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setFont(FontLoader.loadCustomizeFont(robotoMedium, 17f));
            btn.setPreferredSize(new Dimension(190, 30));
            btn.setBackground(new Color(0, 0, 0, 0));
            btn.setFocusable(false);
            btn.setOpaque(false);
            btn.setBorder(null);
            btn.setSize(50,50);
            btn.addActionListener(iController.logout());
            this.add(btn);
        }
    }
}
