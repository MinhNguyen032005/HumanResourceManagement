package view;

import javax.swing.*;
import java.awt.*;

public class PanelService extends JPanel {
    public PanelService(PanelServiceMid panelServiceMid) {
        setLayout(new BorderLayout());
        add(panelServiceMid, BorderLayout.CENTER);
    }
}
