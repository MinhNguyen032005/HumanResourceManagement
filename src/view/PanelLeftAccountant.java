package view;

import controller.IControllerManagenment;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

// class hiện thị panel bên trái chủa panel chính
public class PanelLeftAccountant extends JPanel {
    JButton btn;
    ArrayList<JButton> jButtons;

    public PanelLeftAccountant(IControllerManagenment iController) {
        ArrayList<String> stringButton = new ArrayList<>();
        stringButton.add("Tổng hợp lương");
        stringButton.add("Chấm công");
        stringButton.add("Nghỉ phép");

        ArrayList<String> strings=new ArrayList<>();
        strings.add("src/storage/img/Tonghopluong.png");
        strings.add("src/storage/img/ChamCong.png");
        strings.add("src/storage/img/NghiPhep.png");

        setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));
        Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (int i = 0; i < stringButton.size(); i++) {
            ImageIcon originalIcon = new ImageIcon(strings.get(i));
            Image scaledImage = originalIcon.getImage().getScaledInstance(30, 30 , Image.SCALE_SMOOTH);
            ImageIcon scaledIcon = new ImageIcon(scaledImage);
            this.add(Box.createVerticalStrut(40));
            btn = new JButton(stringButton.get(i));
            btn.setIcon(scaledIcon);
            btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btn.setHorizontalAlignment(SwingConstants.LEFT);
            btn.setFont(FontLoader.loadCustomizeFont(robotoMedium, 20f));
            btn.setPreferredSize(new Dimension(190, 30));
            btn.setBackground(new Color(0, 0, 0, 0));
            btn.setFocusable(false);
            btn.setOpaque(false);
            btn.setBorder(null);
            btn.setSize(50, 50);
            btn.addActionListener(iController.controlButtonLeft());
            this.add(btn);
        }
        this.add(Box.createRigidArea(new Dimension(50 ,500)));

    }
}
