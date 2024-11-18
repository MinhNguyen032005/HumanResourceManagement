package controller;

import data.nghiPhep.NghiPhep;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener;
import java.util.Set;

public interface IControllerManagenment {
    // chức năng đăng nhập
    ActionListener login(JTextField text, JPasswordField password, IControllerManagenment iController);

    // chức năng đăng xuất
    ActionListener logout();

    // chức năng điều kiển các button trong giao diện chính bên trái
    ActionListener controlButtonLeft();

    // chức năng cập nhật bảng vào trong giao diện
    void updateTable(DefaultTableModel tableModel);

    // chức năng tìm kiêm nhân viên trong danh sách nhân viên
    ActionListener findEmPloyee(JTextField jTextField, DefaultTableModel tableModel);

    // chức năng cập nhật bản khi thanh tìm kiếm không có dữ liệu nhập vào
    KeyListener newTable(DefaultTableModel tableModel, JTextField inputSeach);

    // chức năng cập nhật lại bảng thông tin
    void updateTable3(DefaultTableModel tableModel);

    // chức năng thêm dữ liệu từ file ở data
    void loadDataTableReport();

    //  chức năng của cách nút  trong phần báo cáo
    ActionListener controlReport(DefaultTableModel tableModel);

    // chức năng cập nhật vào bảng trong bảng chấm công
    void updateTableTimeKeeping(DefaultTableModel tableModel);

    // chức năng thêm nhân viên vào bảng
    void addEmployee(DefaultTableModel tableModel);

    // chức năng sửa thông tin nhân viên
    void fixEmployee(DefaultTableModel tableModel, JTextField input,JTable table);

    // chức năng xóa nhân viên
    void deleteEmployee(DefaultTableModel tableModel, JTextField input,JTable table);

    // chức năng tạo bảng thống kê
    void createAndDisplayChart(DefaultTableModel tableModel);

    // chức năng tìm kiếm nhân viên
    void findTimeKeeping(JTextField inputSeach, DefaultTableModel tableModel, JTable table);

    // chức năng khi jtextfiel không có gì thì sẽ cập nhật bảng mới
    KeyListener newTable1(DefaultTableModel tableModel, JTextField inputSeach, JButton button);

    void loadDataWork(Set<NghiPhep> allLeaves);

}
