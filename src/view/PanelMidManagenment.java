package view;

import controller.IControllerManagenment;

import javax.swing.*;
import java.awt.*;

public class PanelMidManagenment extends JPanel {
    public PanelMidManagenment(IControllerManagenment iController) {
        JLabel jLabel = new JLabel("XIN CHÀO TRƯỞNG PHÒNG");
        jLabel.setFont(new Font("a", Font.BOLD, 20));
        add(jLabel);
    }
}
