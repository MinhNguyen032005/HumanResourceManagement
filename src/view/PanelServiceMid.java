package view;

import controller.IControllerManagenment;
import data.nhanVien.NhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelServiceMid extends JPanel {
    DefaultTableModel tableModel;
    JTable table;
    ArrayList<NhanVien> nhanVienArrayList;
    JButton button;
    JButton back;
    JTextField inputSeach;

    public PanelServiceMid(IControllerManagenment iController) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Mã nhân viên ");
        list.add("Tên nhân viên");
        list.add("Giới tính");
        list.add("Ngày sinh");
        list.add("Chức vụ");
        ArrayList<String> nameButton = new ArrayList<>();
        nameButton.add("<- Back");
        nameButton.add("Thêm");
        nameButton.add("Sửa");
        nameButton.add("Xóa");
        nameButton.add("Export");
        JButton jButtonSeach=new JButton("Tìm kiếm");
        inputSeach = new JTextField( 20);
        setBackground(new Color(197, 197, 197));
        tableModel = new DefaultTableModel(list.toArray(), 0);
        table = new JTable(tableModel);
        nhanVienArrayList = new ArrayList<>();
        iController.loadDataFromFile();
        iController.updateTable(tableModel);
        setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        for (int i = 1; i < nameButton.size(); i++) {
            button = new JButton(nameButton.get(i));
            jPanel1.add(button);
        }
        inputSeach.addKeyListener(iController.newTable(tableModel,inputSeach));
        jButtonSeach.addActionListener(iController.findEmPloyee(inputSeach,tableModel));
        back = new JButton(nameButton.get(0));
        back.addActionListener(iController.backHome());
        jPanel1.setLayout(new FlowLayout());
        jPanel1.add(inputSeach);
        jPanel1.add(jButtonSeach);
        jPanel.setLayout(new BorderLayout());
        jPanel.add(back, BorderLayout.WEST);
        jPanel.add(jPanel1, BorderLayout.CENTER);
        add(jPanel, BorderLayout.SOUTH);
    }
}
