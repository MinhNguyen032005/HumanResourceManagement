package view;

import controller.IController;
import javax.swing.*;
import java.awt.*;


public class SiginPanel extends JPanel {
    public SiginPanel(IController iController) {
        setLayout(null);
        setBackground(new Color(197, 197, 197));
        add(new SignInForm(iController));
    }
}
