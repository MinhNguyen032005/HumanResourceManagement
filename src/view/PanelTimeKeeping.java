package view;

import controller.IControllerManagenment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// class dùng để hiện thị giao diện chấm công
public class PanelTimeKeeping extends JPanel {
    public PanelTimeKeeping(IControllerManagenment iControllerManagenment,PanelTimeKeepingMid panelTimeKeepingMid) {
        add(panelTimeKeepingMid);
    }
}
