package view;

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
    private JButton registerButton;
    private JLabel statusLabel;
    private Set<Integer> registeredCourses;
    private IControllerManagenment controller;

    public PanelTraining(IControllerManagenment controller) {
        this.controller = controller;
        this.registeredCourses = new HashSet<>();
        initializeUI();
        loadTrainingData();
    }

    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Tiêu đề
        JLabel titleLabel = new JLabel("Thông Tin Đào Tạo", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);

        // Khởi tạo model cho bảng
        String[] columnNames = {"STT", "Tên khóa học", "Mô tả", "Ngày bắt đầu", "Vị trí mục tiêu", "Thời lượng (Tháng)"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        trainingTable = new JTable(tableModel);
        trainingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Tùy chỉnh giao diện bảng
        trainingTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 12));
        trainingTable.setFont(new Font("Arial", Font.PLAIN, 12));
        trainingTable.setRowHeight(25);

        // Panel chứa bảng
        JScrollPane scrollPane = new JScrollPane(trainingTable);
        add(scrollPane, BorderLayout.CENTER);

        // Panel chứa button và label
        JPanel controlPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        registerButton = new JButton("Đăng ký khóa học");
        registerButton.setFont(new Font("Arial", Font.BOLD, 12));

        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));

        controlPanel.add(registerButton);
        controlPanel.add(statusLabel);

        add(controlPanel, BorderLayout.SOUTH);

        // Xử lý sự kiện đăng ký
        registerButton.addActionListener(e -> registerTraining());
    }

    private void loadTrainingData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("src/data/workSchedule.txt"))) {
            String line;
            int rowCount = 1;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    Object[] rowData = {
                            rowCount++,
                            parts[0].trim(),
                            parts[1].trim(),
                            parts[2].trim(),
                            parts[3].trim(),
                            parts[4].trim()
                    };
                    tableModel.addRow(rowData);
                }
            }
        } catch (IOException e) {
            // Nếu không đọc được file, thêm dữ liệu mẫu
            tableModel.addRow(new Object[]{1, "Kỹ năng mềm", "Giao tiếp hiệu quả", "15/01/2024", "Nhân viên", "1"});
            tableModel.addRow(new Object[]{2, "Chuyên môn", "Công nghệ mới", "01/02/2024", "Kỹ thuật", "2"});
        }
    }

    private void registerTraining() {
        int selectedRow = trainingTable.getSelectedRow();
        if (selectedRow == -1) {
            statusLabel.setText("Vui lòng chọn một khóa học để đăng ký!");
            statusLabel.setForeground(Color.RED);
            return;
        }

        if (registeredCourses.contains(selectedRow)) {
            statusLabel.setText("Bạn đã đăng ký khóa học này rồi!");
            statusLabel.setForeground(Color.RED);
            return;
        }

        String courseName = tableModel.getValueAt(selectedRow, 1).toString();
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn đăng ký khóa học: " + courseName + "?",
                "Xác nhận đăng ký",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            registeredCourses.add(selectedRow);
            statusLabel.setText("Đăng ký thành công khóa học: " + courseName);
            statusLabel.setForeground(new Color(0, 128, 0));
        }
    }
}