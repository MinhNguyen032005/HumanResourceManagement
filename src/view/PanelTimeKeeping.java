package view;

import controller.IControllerManagenment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelTimeKeeping extends JPanel {
    public PanelTimeKeeping(IControllerManagenment iControllerManagenment,PanelTimeKeepingMid panelTimeKeepingMid) {
        add(panelTimeKeepingMid);
    }
}
