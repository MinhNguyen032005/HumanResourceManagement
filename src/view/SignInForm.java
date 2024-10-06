package view;

import controller.IController;
import utilities.CustomLabel;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class SignInForm extends JPanel {
    JPanel infoPanel;   //Trick: Tạo 1 JPanel lồng các JTextField và JLabel lại
    JTextField account;
    JPasswordField passwd;
    JButton button;
    Map<String, String> accounts;


    public SignInForm(IController iController) {
        Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
        Font robotoLight = FontLoader.loadFont("src/storage/font/Roboto-Light.ttf");
        setLayout(new FlowLayout());
        setBounds(275, 150, 450, 300); //Trick: Dùng FlowLayout + setPreferredSize để đẩy JButton xuống chính giữa ở dưới infoPanel và đẩy title ở chính giữa bên trên infoPanel

        this.infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(this.infoPanel, BoxLayout.Y_AXIS));

        CustomLabel title = new CustomLabel("Đăng nhập");
        CustomLabel description = new CustomLabel("Hãy đăng nhập để có thể truy cập vào hệ thống");

        this.account = new JTextField(20);
        this.passwd = new JPasswordField(20);
        this.button = new JButton("Đăng nhập");
        this.accounts=new HashMap<>();

        this.account.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Tên đăng nhập", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12f)));
        this.passwd.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Mật khẩu", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12f)));
        this.account.setOpaque(false);
        this.passwd.setOpaque(false);

        title.setCustomFont(robotoMedium, 30f);
        description.setCustomFont(robotoLight, 12f);

        this.account.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
        this.passwd.setFont(FontLoader.loadCustomizeFont(robotoLight, 15f));
        this.button.setFont(FontLoader.loadCustomizeFont(robotoMedium, 18f));

        this.button.setFocusable(false);
        this.button.setBackground(new Color(0, 227, 114));
        this.button.addActionListener(iController.login(account,passwd,iController));

        add(Box.createRigidArea(new Dimension(450, 20)));
        add(title);

        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));   //Box.createRigidArea -> tạo 1 box vô hình (Trick: Dùng để spacing 2 component)
        infoPanel.add(description);

        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(this.account);

        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        infoPanel.add(this.passwd);
        infoPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        add(this.infoPanel);
        add(this.button);
    }

    public JTextField getAccount() {
        return account;
    }

    public JPasswordField getPasswd() {
        return passwd;
    }

    public Map<String, String> getAccounts() {
        return accounts;
    }


}
