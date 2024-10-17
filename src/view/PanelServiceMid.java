package view;

import controller.IControllerManagenment;
import data.nhanVien.NhanVien;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PanelServiceMid extends JPanel {
    static DefaultTableModel tableModel;
    JTable table;
    JButton button;
    JButton back;
    static JTextField inputSeach;

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
        JButton jButtonSeach = new JButton("Tìm kiếm");
        inputSeach = new JTextField(20);
        setBackground(new Color(197, 197, 197));
        tableModel = new DefaultTableModel(list.toArray(), 0);
        table = new JTable(tableModel);
        iController.updateTable(tableModel);
        setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        for (int i = 1; i < nameButton.size(); i++) {
            button = new JButton(nameButton.get(i));
            button.addActionListener(iController.controlButtonEmployee(inputSeach, tableModel));
            jPanel1.add(button);
        }
        inputSeach.addKeyListener(iController.newTable(tableModel, inputSeach));
        jButtonSeach.addActionListener(iController.findEmPloyee(inputSeach, tableModel));
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

    public static class PanelFixEmployee extends JPanel {
        JTextField inputName;
        JTextField inputGender;
        JTextField inputDate;
        JTextField inputPosition;
        JButton btnUpdate;

        public PanelFixEmployee(IControllerManagenment iControllerManagenment) {
            Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
            setSize(100, 100);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            inputName = new JTextField();
            inputName.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Họ và tên", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12)));
            inputGender = new JTextField();
            inputGender.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Giới tính ", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12)));
            inputDate = new JTextField();
            inputDate.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Năm Tháng Ngày", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12)));
            inputPosition = new JTextField();
            inputPosition.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Chức vụ", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12)));
            btnUpdate = new JButton("Sửa thông tin");
            btnUpdate.addActionListener(iControllerManagenment.fixInformationEmployee(inputSeach, inputName, inputGender, inputDate, inputPosition, tableModel));
            JPanel jPanel = new JPanel();
            jPanel.add(btnUpdate);
            add(inputName);
            add(inputGender);
            add(inputDate);
            add(inputPosition);
            add(jPanel);

        }
    }

    public static class PanelAddEmployee extends JPanel {
        JTextField inputName;
        JTextField inputGender;
        JTextField inputDate;
        JTextField inputPosition;
        JButton btnUpdate;

        public PanelAddEmployee(IControllerManagenment iControllerManagenment) {
            Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
            setSize(100, 100);
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            inputName = new JTextField();
            inputName.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Họ và tên", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12)));
            inputGender = new JTextField();
            inputGender.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Giới tính ", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12)));
            inputDate = new JTextField();
            inputDate.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Năm Tháng Ngày", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12)));
            inputPosition = new JTextField();
            inputPosition.setBorder(BorderFactory.createTitledBorder(new LineBorder(Color.BLACK), "Chức vụ", 0, 0, FontLoader.loadCustomizeFont(robotoMedium, 12)));
            btnUpdate = new JButton("Thêm nhân viên");
            btnUpdate.addActionListener(iControllerManagenment.addEmployee(inputName, inputGender, inputDate, inputPosition, tableModel));
            JPanel jPanel = new JPanel();
            jPanel.add(btnUpdate);
            add(inputName);
            add(inputGender);
            add(inputDate);
            add(inputPosition);
            add(jPanel);

        }
    }

}
