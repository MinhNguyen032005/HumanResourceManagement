package view;

import javax.swing.*;
import java.awt.*;

public class MyCanvas extends JPanel {
    PanelTop panelTop;
    PanelMid panelMid;
    PanelBottom panelBottom;

    public MyCanvas(PanelTop panelTop, PanelMid panelMid, PanelBottom panelBottom) {
        setLayout(new BorderLayout());
        this.panelTop = panelTop;
        this.panelMid = panelMid;
        this.panelBottom = panelBottom;
        add(panelTop, BorderLayout.NORTH);
        add(panelMid,BorderLayout.CENTER);
        add(panelBottom,BorderLayout.SOUTH);
    }
}
