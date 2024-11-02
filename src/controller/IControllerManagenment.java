package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public interface IControllerManagenment {
    // chức năng đăng nhập
    ActionListener login(JTextField text, JPasswordField password, IControllerManagenment iController);

    // chức năng đăng xuất
    ActionListener logout();

    // chức năng điều kiển các button trong giao diện chính bên trái
    ActionListener controlButtonLeft();

    // chức năng trở lại trang chính
    ActionListener backHome();

    // chức năng cập nhật bảng vào trong giao diện
    void updateTable(DefaultTableModel tableModel);

    // chức năng tìm kiêm nhân viên trong danh sách nhân viên
    ActionListener findEmPloyee(JTextField jTextField, DefaultTableModel tableModel);

    // chức năng cập nhật bản khi thanh tìm kiếm không có dữ liệu nhập vào
    KeyListener newTable(DefaultTableModel tableModel, JTextField inputSeach);

    // chức năng sửa thông tin của nhân viên trong danh sách nhân viên
    ActionListener fixInformationEmployee(JTextField inputSeach, JTextField inputName, JTextField inputGender, JTextField inputDate, JTextField inputPosition, DefaultTableModel tableModel);

    // chức năng điều khiển các button trong chức năng danh sách nhân viên
    ActionListener controlButtonEmployee(JTextField inputSeach, DefaultTableModel tableModel);

    // chức năng thêm nhân viên vào trong danh sách nhân viên
    ActionListener addEmployee(JTextField inputName, JTextField inputGender, JTextField inputDate, JTextField inputPosition, DefaultTableModel tableModel);

    // chức năng cập nhật lại bảng thông tin
    void updateTable3(DefaultTableModel tableModel);

    // chức năng thêm dữ liệu từ file ở data
    void loadDataTableReport();

    //  chức năng của cách nút  trong phần báo cáo
    ActionListener controlReport(DefaultTableModel tableModel);

    // chức năng của các nút  trong phần chấm công
    ActionListener controlButtonTimeKeeping(JTextField inputSeach, DefaultTableModel tableModel, JTable table);

    // chức năng cập nhật vào bảng trong bảng chấm công
    void updateTableTimeKeeping(DefaultTableModel tableModel);
}
