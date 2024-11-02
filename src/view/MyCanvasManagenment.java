package view;

import javax.swing.*;
import java.awt.*;

// class chứa panel chính để thêm vào JFrame
public class MyCanvasManagenment extends JPanel {
    public MyCanvasManagenment(PanelMidManagenment panelMid, PanelBottomManagenment panelBottom, PanelLeftManagenment panelLeftManagenment) {
        setLayout(new BorderLayout());
        add(panelMid, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelLeftManagenment, BorderLayout.WEST);
    }
}
