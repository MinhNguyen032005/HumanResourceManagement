package view;

import controller.IControllerManagenment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

//class dùng để hiện thị cho người xem thấy khi mình bấm vào báo cáo
public class PanelReport extends JPanel {
    JTable table;
    DefaultTableModel tableModel;
    JButton button;
    JButton back;

    public PanelReport(IControllerManagenment iController) {
        ArrayList<String> list = new ArrayList<>();
        list.add("Mã nhân viên ");
        list.add("Tên nhân viên");
        list.add("Giới tính");
        list.add("Ngày sinh");
        list.add("Chức vụ");
        list.add("Lương");
        ArrayList<String> nameButton = new ArrayList<>();
        nameButton.add("<- Back");
        nameButton.add("Export");
        nameButton.add("Mức lương");
        nameButton.add("Chức vụ");
        setBackground(new Color(197, 197, 197));
        tableModel = new DefaultTableModel(list.toArray(), 0);
        table = new JTable(tableModel);
        iController.loadDataTableReport();
        iController.updateTable3(tableModel);
        setLayout(new BorderLayout());
        JPanel jPanel = new JPanel();
        JPanel jPanel1 = new JPanel();
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);
        for (int i = 1; i < nameButton.size(); i++) {
            button = new JButton(nameButton.get(i));
            button.addActionListener(iController.controlReport(tableModel));
            jPanel1.add(button);
        }
        back = new JButton(nameButton.get(0));
        back.addActionListener(iController.backHome());
        jPanel1.setLayout(new FlowLayout());
        jPanel.setLayout(new BorderLayout());
        jPanel.add(back, BorderLayout.WEST);
        jPanel.add(jPanel1, BorderLayout.CENTER);
        add(jPanel, BorderLayout.SOUTH);
    }
}
