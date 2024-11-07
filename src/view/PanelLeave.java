package view;

import controller.IControllerManagenment;
import data.nghiPhep.NghiPhep;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.PriorityQueue;
import java.util.Set;

// class hiện thị giao diện nghỉ phép
public class PanelLeave extends JPanel {
    private JScrollPane jScrollPane;
    private JButton buttonFind;
    private JTextField jTextField;
    private LeaveListPanel leaveListPanel;
    private IControllerManagenment controller;

    public PanelLeave(IControllerManagenment controller, Set<NghiPhep> nghiPheps) {
        this.controller = controller;
        this.leaveListPanel = new LeaveListPanel(nghiPheps,controller);

        initUI(nghiPheps,controller);
        setupListeners();
    }

    private void initUI(Set<NghiPhep> nghiPheps,IControllerManagenment controller) {
        Font robotoMedium = FontLoader.loadFont("/home/wanmin/ForderOfMy/human resource management/src/storage/font/Roboto-Medium.ttf");
        JPanel panel = new JPanel();
        JPanel jPanel = new JPanel(new FlowLayout());
        jTextField = new JTextField(10);
        buttonFind = new JButton("Tìm kiếm");
        jPanel.add(jTextField);
        jPanel.add(buttonFind);
        buttonFind.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonFind.setFont(FontLoader.loadCustomizeFont(robotoMedium, 15f));
        buttonFind.setPreferredSize(new Dimension(100, 30));
        buttonFind.setFocusable(false);
        buttonFind.setBackground(new Color(0, 227, 114));
        jTextField.setFont(new Font("a",Font.BOLD,15));

        jTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (jTextField.getText().equals("")) {
                    leaveListPanel.refreshList(controller,nghiPheps);
                }
            }
        });

        jScrollPane = new JScrollPane(leaveListPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setPreferredSize(new Dimension(700, 400));
        setLayout(new BorderLayout());
        panel.add(jScrollPane, BorderLayout.CENTER);
        panel.add(jPanel, BorderLayout.SOUTH);
        add(panel);
    }

    private void setupListeners() {
        buttonFind.addActionListener(e -> {
            leaveListPanel.filterLeaves(jTextField.getText());

        });
    }
}
