package view;

import controller.IControllerManagenment;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;

public class PanelMidManagenment extends JPanel {
    public PanelMidManagenment(IControllerManagenment iController) {
        setLayout(new GridBagLayout());
        Font font= FontLoader.loadFont("/home/wanmin/ForderOfMy/human resource management/src/storage/font/Roboto-Light.ttf");
        JLabel jLabel = new JLabel("Hãy chọn chức năng bạn muốn thực hiện");
        jLabel.setFont(new Font("a", Font.PLAIN, 25));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;

        add(jLabel, gbc);
    }
}
