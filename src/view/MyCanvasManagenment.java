package view;

import javax.swing.*;
import java.awt.*;

public class MyCanvasManagenment extends JPanel {
    public MyCanvasManagenment(PanelTopManagenment panelTop, PanelMidManagenment panelMid, PanelBottomManagenment panelBottom) {
        setLayout(new BorderLayout());
        add(panelTop, BorderLayout.NORTH);
        add(panelMid,BorderLayout.CENTER);
        add(panelBottom,BorderLayout.SOUTH);
    }
}
