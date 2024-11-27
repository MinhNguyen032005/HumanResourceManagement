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
    public MyCanvasManagenment(PanelReport panelReport, PanelBottomManagenment panelBottom, PanelLeftManagenment panelLeftManagenment) {
        setLayout(new BorderLayout());
        add(panelReport, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelLeftManagenment, BorderLayout.WEST);
    }
    public MyCanvasManagenment(PanelLeave panelLeave, PanelBottomManagenment panelBottom, PanelLeftManagenment panelLeftManagenment) {
        setLayout(new BorderLayout());
        add(panelLeave, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelLeftManagenment, BorderLayout.WEST);
    }
    public MyCanvasManagenment(PanelService panelService, PanelBottomManagenment panelBottom, PanelLeftManagenment panelLeftManagenment) {
        setLayout(new BorderLayout());
        add(panelService, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelLeftManagenment, BorderLayout.WEST);
    }
    public MyCanvasManagenment(PanelTimeKeeping panelTimeKeeping, PanelBottomManagenment panelBottom, PanelLeftManagenment panelLeftManagenment) {
        setLayout(new BorderLayout());
        add(panelTimeKeeping, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelLeftManagenment, BorderLayout.WEST);
    }

    public MyCanvasManagenment(PanelWorkManagenment panelWorkManagenment, PanelBottomManagenment panelBottom, PanelLeftManagenment panelLeftManagenment) {
        setLayout(new BorderLayout());
        add(panelWorkManagenment, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelLeftManagenment, BorderLayout.WEST);
    }

    public MyCanvasManagenment(PanelMidManagenment panelMid, PanelBottomManagenment panelBottom, PanelLeftAccountant panelLeftAccountant) {
        setLayout(new BorderLayout());
        add(panelMid, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelLeftAccountant, BorderLayout.WEST);}

    public MyCanvasManagenment(PanelReport panelReport, PanelBottomManagenment panelBottom, PanelLeftAccountant panelLeftAccountant) {
        setLayout(new BorderLayout());
        add(panelReport, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelLeftAccountant, BorderLayout.WEST); }

    public MyCanvasManagenment(PanelTimeKeeping panelTimeKeeping, PanelBottomManagenment panelBottom, PanelLeftAccountant panelLeftAccountant) {
        setLayout(new BorderLayout());
        add(panelTimeKeeping, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelLeftAccountant, BorderLayout.WEST);
    }

    public MyCanvasManagenment(PanelLeave panelLeave, PanelBottomManagenment panelBottom, PanelLeftAccountant panelLeftAccountant) {
        setLayout(new BorderLayout());
        add(panelLeave, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelLeftAccountant, BorderLayout.WEST);
    }

    public MyCanvasManagenment(JPanel panelTop, JPanel panelBottom, JPanel panelLeft) {
        setLayout(new BorderLayout());
        add(panelTop, BorderLayout.CENTER);
        add(panelBottom, BorderLayout.SOUTH);
        add(panelLeft, BorderLayout.WEST);
    }
}
