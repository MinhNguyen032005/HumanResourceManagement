package view;

import javax.swing.*;
import java.awt.*;

public class MyCanvasManagenment extends JPanel {
    PanelTopManagenment panelTop;
    PanelMidManagenment panelMid;
    PanelBottomManagenment panelBottom;

    public MyCanvasManagenment(PanelTopManagenment panelTop, PanelMidManagenment panelMid, PanelBottomManagenment panelBottom) {
        setLayout(new BorderLayout());
        this.panelTop = panelTop;
        this.panelMid = panelMid;
        this.panelBottom = panelBottom;
        add(panelTop, BorderLayout.NORTH);
        add(panelMid,BorderLayout.CENTER);
        add(panelBottom,BorderLayout.SOUTH);
    }
}
