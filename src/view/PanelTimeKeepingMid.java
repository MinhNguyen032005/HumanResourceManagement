package view;

import controller.IControllerManagenment;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

// class dùng để hiện thị giao diện chấm công
public class PanelTimeKeepingMid extends JPanel {
    static JTable table;
    static DefaultTableModel tableModel;
    JButton button, back;
    JTextField inputSeach;

    public PanelTimeKeepingMid(IControllerManagenment iController) {
        Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
        ArrayList<String> list = new ArrayList<>();
        list.add("Mã nhân viên ");
        list.add("Tên nhân viên");
        list.add("Ngày");
        list.add("Thứ");
        list.add("Giờ vào");
        list.add("Giờ ra");
        list.add("ĐI muộn");
        list.add("Thời gian làm");
        ArrayList<String> nameButton = new ArrayList<>();
        nameButton.add("Thống kê");
        nameButton.add("Tìm kiếm");
        inputSeach = new JTextField(10);
        inputSeach.setFont(new Font("a",Font.BOLD,15));
        setBackground(new Color(197, 197, 197));
        tableModel = new DefaultTableModel(list.toArray(), 0);
        table = new JTable(tableModel);
        iController.updateTableTimeKeeping(tableModel);
        setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        for (int i = 0; i < nameButton.size(); i++) {
            button = new JButton(nameButton.get(i));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setFont(FontLoader.loadCustomizeFont(robotoMedium, 15f));
            button.setPreferredSize(new Dimension(100, 30));
            button.setFocusable(false);
            button.setBackground(new Color(0, 227, 114));
            button.addActionListener(e -> {
                String string = e.getActionCommand();
                switch (string) {
                    case "Thống kê": {
                        iController.createAndDisplayChart(tableModel);
                        break;
                    }
                    case "Tìm kiếm": {
                        iController.findTimeKeeping(inputSeach, tableModel, table);
                        button.setEnabled(false);
                        break;
                    }
                }
            });
            jPanel1.add(button);
        }
        inputSeach.addKeyListener(iController.newTable1(tableModel,inputSeach,button));
        scrollPane.setPreferredSize(new Dimension(750, 300)); // Đặt kích thước cho JScrollPane
        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(inputSeach);
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jPanel1, BorderLayout.CENTER);
        add(jPanel, BorderLayout.SOUTH);
    }
}
