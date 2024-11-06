package view;

import controller.IControllerManagenment;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.ArrayList;

// class hiện thị panel bên trái chủa panel chính
public class PanelLeftManagenment extends JPanel {
    JButton btn;
    ArrayList<JButton> jButtons;

    public PanelLeftManagenment(IControllerManagenment iController) {
        ArrayList<String> stringButton = new ArrayList<>();
        stringButton.add("Báo cáo");
        stringButton.add("Danh sách NV");
        stringButton.add("Công việc");
        stringButton.add("Chấm công");
        stringButton.add("Nghỉ phép");
        ArrayList<String> strings=new ArrayList<>();
        strings.add("/home/wanmin/ForderOfMy/human resource management/src/storage/img/2124299_app_document_report_essential_icon.png");
        strings.add("/home/wanmin/ForderOfMy/human resource management/src/storage/img/1471104_align_employee_general_human_human list_icon.png");
        strings.add("/home/wanmin/ForderOfMy/human resource management/src/storage/img/1988568_checklist_office_pen_signature_note_icon.png");
        strings.add("/home/wanmin/ForderOfMy/human resource management/src/storage/img/2672709_essential_app_clipboard_object_time_icon.png");
        strings.add("/home/wanmin/ForderOfMy/human resource management/src/storage/img/6138479_circle_out_person_profile_user_icon.png");
        setBorder(new MatteBorder(0, 0, 0, 1, Color.BLACK));
        Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (int i = 0; i < stringButton.size(); i++) {
            ImageIcon originalIcon = new ImageIcon(strings.get(i));
            Image scaledImage = originalIcon.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
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
