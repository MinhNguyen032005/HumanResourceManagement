package view;

import controller.IControllerManagenment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelSalary extends JPanel {
    private JTable salaryTable;
    private DefaultTableModel tableModel;

    public PanelSalary(IControllerManagenment controller) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông Tin Lương", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Tạo bảng lương
        String[] columns = {"Tháng", "Lương cơ bản", "Phụ cấp", "Thưởng", "Tổng lương"};
        tableModel = new DefaultTableModel(columns, 0);
        salaryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(salaryTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu mẫu
        loadSalaryData();
    }

    private void loadSalaryData() {
        // Thêm dữ liệu mẫu
        tableModel.addRow(new Object[]{"1/2024", "8,000,000", "1,000,000", "500,000", "9,500,000"});
        tableModel.addRow(new Object[]{"2/2024", "8,000,000", "1,000,000", "700,000", "9,700,000"});
    }
}
