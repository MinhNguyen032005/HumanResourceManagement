package view;

import controller.IControllerManagenment;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

// class để chứa các button bên dưới trong panel chính
public class PanelBottomManagenment extends JPanel {
    JButton btn;

    public PanelBottomManagenment(IControllerManagenment iController) {
        Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
        ArrayList<String> stringButton = new ArrayList<>();
        stringButton.add("Đăng xuất");
        setBorder(new MatteBorder(1,0,0,0,Color.BLACK));
        setLayout(new FlowLayout(FlowLayout.LEFT));
        ImageIcon icon=new ImageIcon("/home/wanmin/ForderOfMy/human resource management/src/storage/img/4831049_door_enter_entrance_exit_leave_icon.png");
        for (int i = 0; i < stringButton.size(); i++) {
            btn = new JButton(stringButton.get(i));
            btn.setIcon(icon);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setFont(FontLoader.loadCustomizeFont(robotoMedium, 20f));
            btn.setPreferredSize(new Dimension(190, 30));
            btn.setBackground(new Color(0, 0, 0, 0));
            btn.setFocusable(false);
            btn.setOpaque(false);
            btn.setBorder(null);
            btn.setSize(50, 50);
            btn.addActionListener(iController.logout());
            this.add(btn);
        }
    }
}
