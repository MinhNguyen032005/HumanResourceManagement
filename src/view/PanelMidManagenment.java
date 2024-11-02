package view;

import controller.IControllerManagenment;

import javax.swing.*;
import java.awt.*;

public class PanelMidManagenment extends JPanel {
    public PanelMidManagenment(IControllerManagenment iController) {
        setLayout(new GridBagLayout());
        JLabel jLabel = new JLabel("XIN CHÀO TRƯỞNG PHÒNG");
        jLabel.setFont(new Font("a", Font.BOLD, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(jLabel, gbc);
    }
}
