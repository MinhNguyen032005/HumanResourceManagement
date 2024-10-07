package view;

import controller.IController;
import data.nhanVien.NhanVien;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PanelMidManagenment extends JPanel {
    DefaultTableModel tableModel;
    String[] str = {"Mã nhân viên ", "Tên nhân viên", "Giới tính", "Ngày sinh", "Chức vụ"};
    JTable table;
    ArrayList<NhanVien> nhanVienArrayList;

    public PanelMidManagenment(IController iController) {
        setBackground(new Color(197, 197, 197));
        tableModel = new DefaultTableModel(str, 0);
        table = new JTable(tableModel);
        nhanVienArrayList=new ArrayList<>();
        loadDataFromFile("src/data/data.txt");
        updateTable();
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane);
    }
    private void loadDataFromFile(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            if ((line = br.readLine()) != null) {
                String[] headers = line.split(",");
                tableModel.setColumnIdentifiers(headers);
            }
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    NhanVien nhanVien = new NhanVien(data[0], data[1], data[2], data[3], data[4]);
                    nhanVienArrayList.add(nhanVien);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void updateTable() {
        for (NhanVien nhanVien1 : nhanVienArrayList) {
            tableModel.addRow(new Object[]{nhanVien1.getId(),nhanVien1.getName(),nhanVien1.getGender(),nhanVien1.getDate(),nhanVien1.getPosition()});
        }
    }
}
