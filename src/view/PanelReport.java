package view;

import controller.IControllerManagenment;
import utilities.FontLoader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

//class dùng để hiện thị cho người xem thấy khi mình bấm vào báo cáo
public class PanelReport extends JPanel {
    JTable table;
    DefaultTableModel tableModel;
    JButton button;

    public PanelReport(IControllerManagenment iController) {
        Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
        ArrayList<String> list = new ArrayList<>();
        list.add("Mã nhân viên ");
        list.add("Tên nhân viên");
        list.add("Giới tính");
        list.add("Ngày sinh");
        list.add("Chức vụ");
        list.add("Lương");
        ArrayList<String> nameButton = new ArrayList<>();
        nameButton.add("Export");
        nameButton.add("Mức lương");
        nameButton.add("Chức vụ");
        nameButton.add("Cập nhật");
        setBackground(new Color(197, 197, 197));
        tableModel = new DefaultTableModel(list.toArray(), 0);
        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(700, 200)); // Đặt kích thước tổng thể của bảng
        iController.loadDataTableReport();
        iController.updateTable3(tableModel);
        setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        JPanel jPanel2 = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jPanel2.add(scrollPane, BorderLayout.CENTER);
        for (int i = 0; i < nameButton.size(); i++) {
            button = new JButton(nameButton.get(i));
            button.setCursor(new Cursor(Cursor.HAND_CURSOR));
            button.setFont(FontLoader.loadCustomizeFont(robotoMedium, 15f));
            button.setPreferredSize(new Dimension(150, 30));
            button.setFocusable(false);
            button.setBackground(new Color(0, 227, 114));
            button.addActionListener(iController.controlReport(tableModel));
            jPanel1.add(button);
        }
        jPanel1.setLayout(new FlowLayout());
        jPanel.setLayout(new FlowLayout());
        jPanel.add(jPanel1);
        jPanel2.add(jPanel, BorderLayout.SOUTH);
        add(jPanel2);
    }
}
