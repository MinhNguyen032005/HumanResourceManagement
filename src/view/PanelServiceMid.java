package view;

import controller.IControllerManagenment;
import data.nhanVien.NhanVien;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

//c class dùng để hiện thị các chức năng khi bấm vào danh sách nhân viên
public class PanelServiceMid extends JPanel {
    static DefaultTableModel tableModel;
    JTable table;
    JButton button;
    static JTextField inputSeach;

    public PanelServiceMid(IControllerManagenment iController) {
        Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
        ArrayList<String> list = new ArrayList<>();
        list.add("Mã nhân viên ");
        list.add("Tên nhân viên");
        list.add("Giới tính");
        list.add("Ngày sinh");
        list.add("Chức vụ");
        ArrayList<String> nameButton = new ArrayList<>();
        nameButton.add("Thêm");
        nameButton.add("Sửa");
        nameButton.add("Xóa");
        JButton jButtonSeach = new JButton("Tìm kiếm");
        inputSeach = new JTextField("ID hoặc tên",10);
        inputSeach.setFont(new Font("a",Font.BOLD,15));
        setBackground(new Color(197, 197, 197));
        tableModel = new DefaultTableModel(list.toArray(), 0);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(700, 200));
        iController.updateTable(tableModel);
        setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jPanel2.add(scrollPane, BorderLayout.CENTER);
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
                    case "Thêm": {
                        iController.addEmployee(tableModel);
                        break;
                    }
                    case "Sửa": {
                        iController.fixEmployee(tableModel, inputSeach,table);
                        break;
                    }
                    case "Xóa": {
                        iController.deleteEmployee(tableModel, inputSeach,table);
                        break;
                    }
                    case "Tìm kiếm": {
                        iController.findEmPloyee(inputSeach, tableModel);
                        break;
                    }
                }
            });
            jPanel1.add(button);
        }
        inputSeach.addKeyListener(iController.newTable(tableModel, inputSeach,table));
        jButtonSeach.addActionListener(iController.findEmPloyee(inputSeach, tableModel));
        jButtonSeach.setCursor(new Cursor(Cursor.HAND_CURSOR));
        jButtonSeach.setFont(FontLoader.loadCustomizeFont(robotoMedium, 15f));
        jButtonSeach.setPreferredSize(new Dimension(100, 30));
        jButtonSeach.setFocusable(false);
        jButtonSeach.setBackground(new Color(0, 227, 114));
        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(inputSeach);
        jPanel1.add(jButtonSeach);
        jPanel.setLayout(new BorderLayout());
        jPanel.add(jPanel1, BorderLayout.CENTER);
        jPanel2.add(jPanel, BorderLayout.SOUTH);
        add(jPanel2);
    }

}
