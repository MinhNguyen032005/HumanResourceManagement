package view;

import javax.swing.*;
import java.awt.*;

// hiện thị trang khi nhấn vào danh sách nhân viên
public class PanelService extends JPanel {
    public PanelService(PanelServiceMid panelServiceMid) {
        setLayout(new BorderLayout());
        add(panelServiceMid, BorderLayout.CENTER);
    }
}
