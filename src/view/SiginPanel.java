package view;

import controller.IControllerManagenment;

import javax.swing.*;
import java.awt.*;

// class chứa panel đăng nhập
public class SiginPanel extends JPanel {
    public SiginPanel(IControllerManagenment iController, JPanel jPanel) {
        setLayout(null);
        setBackground(new Color(197, 197, 197));
        add(new SignInForm(iController, jPanel));
    }
}
