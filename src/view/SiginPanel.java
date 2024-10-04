package view;

import utilities.CustomLabel;
import utilities.FontLoader;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SiginPanel extends JPanel {
    public SiginPanel() {
        setLayout(null);
        setBackground(new Color(197, 197, 197));
        add(new SignInForm());
    }

    class SignInForm extends JPanel {
        JPanel infoPanel;   //Trick: Tạo 1 JPanel lồng các JTextField và JLabel lại
        JTextField account;
        JPasswordField passwd;
        JButton button;
        JLabel messageLabel;
        Map<String,String> accounts=new HashMap<>();


        public SignInForm() {
            loadAccountsFromFile();
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
            this.button.addActionListener(e -> {
                String username = account.getText();
                String password = new String(passwd.getPassword());

                if (accounts.containsKey(username) && accounts.get(username).equals(password)) {
                   switch (username){
                       case "truongphong":{

                           break;
                       }
                       case  "ketoan":{

                           break;
                       }
                       case "nhanvien":{

                           break;
                       }
                       case "nhansu":{
                           break;
                       }
                   }
                    JOptionPane.showMessageDialog(this,"Đăng nhập thành công!","Thông Báo",JOptionPane.DEFAULT_OPTION);
                } else {
                    JOptionPane.showMessageDialog(this,"Tên đăng nhập hoặc mật khẩu sai!","Thông Báo",JOptionPane.WARNING_MESSAGE);
                }
            });

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
            add(button);
        }
        private void loadAccountsFromFile() {
            try (BufferedReader br = new BufferedReader(new FileReader("/home/wanmin/ForderOfMy/human resource management/src/data/account.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length == 2) {
                        accounts.put(parts[0], parts[1]);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
