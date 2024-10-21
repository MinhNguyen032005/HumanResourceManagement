package view;

import controller.IControllerManagenment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelTimeKeepingMid extends JPanel {
    static JTable table;
    static DefaultTableModel tableModel;
    JButton button,back;
    JTextField inputSeach;

    public PanelTimeKeepingMid(IControllerManagenment iController) {
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
        nameButton.add("<- Back");
        nameButton.add("Thống kê");
        nameButton.add("Tìm kiếm");
        inputSeach = new JTextField(20);
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
        back=new JButton(nameButton.get(0));
        back.addActionListener(iController.backHome());
        jPanel1.add(back);
        for (int i = 1; i < nameButton.size(); i++) {
            button = new JButton(nameButton.get(i));
            button.addActionListener(iController.controlButtonTimeKeeping(inputSeach, tableModel,table));
            jPanel1.add(button);
        }

        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(inputSeach);
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jPanel1, BorderLayout.CENTER);
        add(jPanel, BorderLayout.SOUTH);
    }
}
