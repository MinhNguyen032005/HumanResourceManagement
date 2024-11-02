package view;

import controller.IControllerManagenment;
import data.nghiPhep.NghiPhep;

import javax.swing.*;
import java.awt.*;
import java.util.PriorityQueue;
import java.util.Set;

// class hiện thị giao diện nghỉ phép
public class PanelLeave extends JPanel {
    private JScrollPane jScrollPane;
    private JButton buttonBack, buttonFind;
    private JTextField jTextField;
    private LeaveListPanel leaveListPanel;
    private IControllerManagenment controller;

    public PanelLeave(IControllerManagenment controller, Set<NghiPhep> nghiPheps) {
        this.controller = controller;
        this.leaveListPanel = new LeaveListPanel(nghiPheps);

        initUI();
        setupListeners();
    }

    private void initUI() {
        JPanel panel1 = new JPanel(new FlowLayout());
        jTextField = new JTextField(20);
        buttonBack = new JButton("<- Back");
        buttonFind = new JButton("Find");

        panel1.add(jTextField);
        panel1.add(buttonFind);

        jScrollPane = new JScrollPane(leaveListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        setLayout(new BorderLayout());
        add(jScrollPane, BorderLayout.CENTER);

        JPanel jPanel = new JPanel(new FlowLayout());
        jPanel.add(buttonBack);
        jPanel.add(panel1);

        add(jPanel, BorderLayout.SOUTH);
    }

    private void setupListeners() {
        buttonBack.addActionListener(controller.backHome());
        buttonFind.addActionListener(e -> {
            if (jTextField.getText().isEmpty()) {
                leaveListPanel.refreshList();
            } else {
                leaveListPanel.filterLeaves(jTextField.getText());
            }
        });
    }
}
