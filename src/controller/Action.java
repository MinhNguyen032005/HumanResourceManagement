package controller;

import data.nhanVien.NhanVien;
import view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Action implements IController {
    MyCanvasManagenment myCanvas;
    PanelMidManagenment panelMid;
    PanelBottomManagenment panelBottom;
    PanelLeftManagenment panelLeftManagenment;
    PanelService panelService;
    PanelServiceMid panelServiceMid;
    SiginPanel siginPanel;
    SignInFrame signInFrame;
    SignInForm signInForm;
    Map<String, String> accounts;
    CardLayout cardLayout;
    JPanel cardPanel;
    Stack<JPanel> jPanelStack;
    Map<String, NhanVien> nhanVienListMap;
    ArrayList<NhanVien> nhanVienArrayList;


    public Action() throws Exception {
        nhanVienListMap = new HashMap<>();
        nhanVienArrayList = new ArrayList<>();
        jPanelStack = new Stack<>();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        accounts = new HashMap<>();
        panelMid = new PanelMidManagenment(this);
        panelServiceMid = new PanelServiceMid(this);
        panelService = new PanelService(panelServiceMid);
        panelBottom = new PanelBottomManagenment(this);
        panelLeftManagenment = new PanelLeftManagenment(this);
        myCanvas = new MyCanvasManagenment(panelMid, panelBottom, panelLeftManagenment);
        siginPanel = new SiginPanel(this, cardPanel);
        signInForm = new SignInForm(this, cardPanel);
        cardPanel.add(siginPanel, "login");
        cardPanel.add(myCanvas, "truongphong");
        cardPanel.add(panelService, "DV");
        jPanelStack.push(siginPanel);
        signInFrame = new SignInFrame(cardPanel, this);
    }

    //chức năng kiểm tra từ file account.txt
    private void loadAccountsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/account.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    accounts.put(parts[0], parts[1]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //chức năng chuyển đổi jpanel khi đăng nhập mỗi tài khoản khác nhau
    @Override
    public ActionListener login(JTextField text, JPasswordField password1, IController iController) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAccountsFromFile();
                if (accounts.containsKey(text.getText()) && accounts.get(text.getText()).equals(new String(password1.getPassword()))) {
                    JOptionPane.showMessageDialog(signInFrame, "Đăng nhập thành công!", "Thông Báo", JOptionPane.DEFAULT_OPTION);
                    switch (text.getText()) {
                        case "truongphong": {
                            cardLayout.show(cardPanel, "truongphong");
                            jPanelStack.push(siginPanel);
                            break;
                        }
                        case "ketoan": {

                            break;
                        }
                        case "nhanvien": {

                            break;
                        }
                        case "nhansu": {
                            break;
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(signInFrame, "Tên đăng nhập hoặc mật khẩu sai!", "Thông Báo", JOptionPane.WARNING_MESSAGE);
                }
            }
        };
    }

    // chức năng đăng xuất
    @Override
    public ActionListener logout() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("<- Đăng xuất")) {
                    JOptionPane.showMessageDialog(myCanvas, "Bạn có chắc chắn muốn thoát", "Thông Báo", JOptionPane.DEFAULT_OPTION);
                    goBack();
                }
            }
        };
    }

    //hàm phụ của nút đăng xuất và nút back
    public void goBack() {
        if (jPanelStack.size() > 1) {
            JPanel previousPanel = jPanelStack.pop();
            ((CardLayout) cardPanel.getLayout()).show(cardPanel, getPanelName(previousPanel));
        }
    }

    // hàm phụ của goBack()
    private String getPanelName(JPanel panel) {
        if (panel == cardPanel.getComponent(0)) {
            return "login";
        } else if (panel == cardPanel.getComponent(1)) {
            return "truongphong";
        } else {
            return "DV";
        }
    }

    //chức năng nút bên trái của Managenment
    @Override
    public ActionListener controlButtonLeft() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = e.getActionCommand();
                switch (string) {
                    case "Trang chủ": {

                        break;
                    }
                    case "Báo cáo": {

                        break;
                    }
                    case "Thông báo": {

                        break;
                    }
                    case "Lịch làm việc": {

                        break;
                    }
                    case "Công việc": {

                        break;
                    }
                    case "Danh sách NV": {
                        cardLayout.show(cardPanel, "DV");
                        jPanelStack.push(myCanvas);
                        break;
                    }
                    case "Thông tin lương NV": {

                        break;
                    }
                }
            }
        };
    }

    //chức năng nút back
    @Override
    public ActionListener backHome() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getActionCommand().equals("<- Back")) {
                    goBack();
                }
            }
        };
    }

    //hàm để cập nhật các file trong data.txt để add vào danh sách nhân viên
    @Override
    public void loadDataFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/data.txt"))) {
            String line;
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

    //hàm để add danh sách nhân viên vào bảng
    @Override
    public void updateTable(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        for (NhanVien nhanVien1 : nhanVienArrayList) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition()});
        }
    }
//hàm dùng để tìm kiếm nhân viên bằng cách nhập ID vào để tìm
    @Override
    public ActionListener findEmPloyee(JTextField jTextField, DefaultTableModel tableModel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = jTextField.getText();
                searchEmployeeById(id, tableModel);
            }
        };
    }
//chức năng của jtextfield để hiện lại bẳng khi không có nhập dữ liệu vào
    @Override
    public KeyListener newTable(DefaultTableModel tableModel, JTextField inputSeach) {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String id=inputSeach.getText();
                if (id.equals("")){
                    updateTable(tableModel);
                }
            }
        };
    }
//chức năng của button tìm kiếm
    private void searchEmployeeById(String id, DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        loadListEmployee();
        NhanVien nhanVien = nhanVienListMap.get(id);
        if (nhanVien != null) {
            tableModel.addRow(new Object[]{nhanVien.getId(), nhanVien.getName(), nhanVien.getGender(), nhanVien.getDate(), nhanVien.getPosition()});
        } else {
            JOptionPane.showMessageDialog(panelServiceMid, "Không tìm thấy nhân viên!");
        }
    }
//hàm dùng để put những nhân viên vào trong map có key bằng chính id của nó
    public void loadListEmployee() {
        for (NhanVien nhanVien : nhanVienArrayList) {
            nhanVienListMap.put(nhanVien.getId(), nhanVien);
        }
    }


    // test ở đây
    public static void main(String[] args) throws Exception {
        new Action();
    }
}


