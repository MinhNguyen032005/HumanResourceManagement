package view;

import controller.ActionManagenment;
import controller.IControllerManagenment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class PanelTraining extends JPanel {
    private JTable trainingTable;
    private DefaultTableModel tableModel;

    public PanelTraining(IControllerManagenment controller) {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông Tin Đào Tạo", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Tạo bảng đào tạo
        String[] columns = {"Khóa đào tạo", "Thời gian", "Nội dung", "Trạng thái"};
        tableModel = new DefaultTableModel(columns, 0);
        trainingTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(trainingTable);
        add(scrollPane, BorderLayout.CENTER);

        // Load dữ liệu mẫu
        loadTrainingData();
    }

    private void loadTrainingData() {
        // Thêm dữ liệu mẫu
        tableModel.addRow(new Object[]{"Kỹ năng mềm", "15/01/2024 - 20/01/2024", "Giao tiếp hiệu quả", "Đã hoàn thành"});
        tableModel.addRow(new Object[]{"Chuyên môn", "01/02/2024 - 10/02/2024", "Công nghệ mới", "Đang diễn ra"});
    }
}

