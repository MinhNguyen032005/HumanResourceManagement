package controller;

import data.nhanVien.NhanVien;
import utilities.PositionComparator;
import utilities.WageComparator;
import view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;

public class ActionManagenment implements IControllerManagenment {
    MyCanvasManagenment myCanvas;
    PanelMidManagenment panelMid;
    PanelBottomManagenment panelBottom;
    PanelLeftManagenment panelLeftManagenment;
    PanelService panelService;
    PanelServiceMid panelServiceMid;
    SiginPanel siginPanel;
    SignInFrame signInFrame;
    SignInForm signInForm;
    CardLayout cardLayout;
    JPanel cardPanel;
    PanelServiceMid.PanelFixEmployee panelFixEmployee;
    PanelServiceMid.PanelAddEmployee panelAddEmployee;
    PanelReport panelReport;
    Stack<JPanel> jPanelStack;
    Map<String, NhanVien> nhanVienListMap;
    ArrayList<NhanVien> nhanVienArrayList;
    Map<String, String> accounts;

    public ActionManagenment() throws Exception {
        nhanVienListMap = new LinkedHashMap<>();
        nhanVienArrayList = new ArrayList<>();
        jPanelStack = new Stack<>();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        accounts = new HashMap<>();
        panelReport = new PanelReport(this);
        panelMid = new PanelMidManagenment(this);
        panelServiceMid = new PanelServiceMid(this);
        panelService = new PanelService(panelServiceMid);
        panelBottom = new PanelBottomManagenment(this);
        panelLeftManagenment = new PanelLeftManagenment(this);
        panelFixEmployee = new PanelServiceMid.PanelFixEmployee(this);
        panelAddEmployee = new PanelServiceMid.PanelAddEmployee(this);
        myCanvas = new MyCanvasManagenment(panelMid, panelBottom, panelLeftManagenment);
        siginPanel = new SiginPanel(this, cardPanel);
        signInForm = new SignInForm(this, cardPanel);
        cardPanel.add(siginPanel, "login");
        cardPanel.add(myCanvas, "truongphong");
        cardPanel.add(panelService, "DV");
        cardPanel.add(panelFixEmployee, "FIX");
        cardPanel.add(panelAddEmployee, "ADD");
        cardPanel.add(panelReport, "RP");
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
    public ActionListener login(JTextField text, JPasswordField password1, IControllerManagenment iController) {
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
                    JOptionPane.showMessageDialog(myCanvas, "Bạn có chắc chắn muốn thoát", "Thông Báo", JOptionPane.OK_CANCEL_OPTION);
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
                    case "Báo cáo": {
                        cardLayout.show(cardPanel, "RP");
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

    //hàm để cập nhật các file trong dataEmployee.txt để add vào danh sách nhân viên
    @Override
    public void loadDataFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/dataEmployee.txt"))) {
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
                String id = inputSeach.getText();
                if (id.equals("")) {
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

    @Override
    public ActionListener fixInformationEmployee(JTextField inputSeach, JTextField inputName, JTextField inputGender, JTextField inputDate, JTextField inputPosition, DefaultTableModel tableModel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = inputSeach.getText();
                String newName = inputName.getText();
                String newGender = inputGender.getText();
                String newDate = inputDate.getText();
                String newPosition = inputPosition.getText();

                updateEmployee(id, newName, newGender, newDate, newPosition, tableModel);
            }
        };
    }

    private void updateEmployee(String id, String newName, String newGender, String newDate, String newPosition, DefaultTableModel tableModel) {
        NhanVien nhanVien = nhanVienListMap.get(id);
        if (nhanVien != null) {
            nhanVien.setName(newName);
            nhanVien.setGender(newGender);
            nhanVien.setDate(newDate);
            nhanVien.setPosition(newPosition);
            nhanVienListMap.put(id, nhanVien);
            updateTable(tableModel);
            JOptionPane.showMessageDialog(panelServiceMid, "Sửa thông tin thành công!");
            cardLayout.show(cardPanel, "DV");
        } else {
            JOptionPane.showMessageDialog(panelServiceMid, "Không tìm thấy nhân viên với ID này!");
            cardLayout.show(cardPanel, "DV");
        }
    }

    @Override
    public ActionListener controlButtonEmployee(JTextField inputSeach, DefaultTableModel tableModel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = e.getActionCommand();
                switch (string) {
                    case "Thêm": {
                        cardLayout.show(cardPanel, "ADD");
                        break;
                    }
                    case "Sửa": {
                        cardLayout.show(cardPanel, "FIX");
                        break;
                    }
                    case "Xóa": {
                        deleteEmployee(inputSeach, tableModel);
                        break;
                    }
                }
            }
        };
    }

    @Override
    public ActionListener addEmployee(JTextField inputName, JTextField inputGender, JTextField inputDate, JTextField inputPosition, DefaultTableModel tableModel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = inputName.getText();
                String gender = inputGender.getText();
                String date = inputDate.getText();
                String position = inputPosition.getText();
                updateAddEmployee(name, gender, date, position, tableModel);
            }
        };
    }

    private void updateAddEmployee(String name, String gender, String date, String position, DefaultTableModel tableModel) {
        String idEmployee = idEmployee();
        NhanVien nhanVien = new NhanVien(null, null, null, null, null);
        if (nhanVien != null) {
            nhanVien.setName(name);
            nhanVien.setGender(gender);
            nhanVien.setDate(date);
            nhanVien.setPosition(position);
            nhanVienListMap.put(idEmployee, nhanVien);
            updateTable2(idEmployee, tableModel, name, gender, date, position);
            JOptionPane.showMessageDialog(panelServiceMid, "Thêm thành công!");
            cardLayout.show(cardPanel, "DV");
        } else {
            JOptionPane.showMessageDialog(panelServiceMid, "Không thêm Nhân viên được!");
            cardLayout.show(cardPanel, "DV");
        }
    }

    private void updateTable2(String id, DefaultTableModel tableModel, String name, String gender, String date, String position) {
        tableModel.addRow(new Object[]{id, name, gender, date, position});
    }

    private String idEmployee() {
        loadListEmployee();
        int idMax = 0;
        System.out.println(nhanVienListMap.size());
        idMax = nhanVienListMap.size();
        idMax++;
        return Integer.toString(idMax);
    }

    private void deleteEmployee(JTextField inputSeach, DefaultTableModel tableModel) {
        String id = inputSeach.getText();
        NhanVien nhanVien = nhanVienListMap.get(id);
        if (nhanVien != null) {
            nhanVienListMap.remove(id);
            updateTable1(tableModel);
            JOptionPane.showMessageDialog(panelServiceMid, "Xóa nhân viên thành công!");
        } else {
            JOptionPane.showMessageDialog(panelServiceMid, "Không tìm thấy nhân viên với ID này!");
        }
    }

    private void updateTable1(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        List<NhanVien> list = new ArrayList<>(nhanVienListMap.values());
        for (NhanVien nhanVien1 : list) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition()});
        }
        list.sort(Comparator.comparing(NhanVien::getId));
    }

    public void xuatBaoCaoTXT() {
        loadDataTableReport();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Chọn nơi lưu file báo cáo .txt");
        int userSelection = fileChooser.showSaveDialog(null);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getAbsolutePath().endsWith(".txt")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".txt");
            }
            try (FileWriter writer = new FileWriter(fileToSave)) {
                writer.write("Mã NV\tHọ và Tên\tChức Vụ\tLương\n"); // Header
                for (NhanVien nv : nhanVienArrayList) {
                    writer.write(nv.getId() + "\t" + nv.getName() + "\t" + nv.getPosition() + "\t\t" + nv.getWage() + "\n");
                }
                JOptionPane.showMessageDialog(null, "Xuất báo cáo ra file .txt thành công!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void updateTable3(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        nhanVienArrayList.clear();
        loadDataTableReport();
        for (NhanVien nhanVien1 : nhanVienArrayList) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition(), nhanVien1.getWage()});
        }
    }

    public void loadDataTableReport() {
        try (BufferedReader br = new BufferedReader(new FileReader("/home/wanmin/ForderOfMy/human resource management/src/data/dataReport.txt "))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 6) {
                    NhanVien nhanVien = new NhanVien(data[0], data[1], data[2], data[3], data[4], Double.parseDouble(data[5]));
                    nhanVienArrayList.add(nhanVien);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public ActionListener controlReport(DefaultTableModel tableModel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = e.getActionCommand();
                switch (string) {
                    case "Export": {
                        xuatBaoCaoTXT();
                        break;
                    }
                    case "Mức lương": {
                        updateTableWage(tableModel);
                        break;
                    }
                    case "Chức vụ": {
                        updateTablePosition(tableModel);
                        break;
                    }
                }
            }
        };
    }
    private void sortPosition(){
        nhanVienArrayList.clear();
        loadDataTableReport();
        Collections.sort(nhanVienArrayList,new PositionComparator());
    }

    private void updateTablePosition(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        sortPosition();
        for (NhanVien nhanVien1: nhanVienArrayList){
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition(), nhanVien1.getWage()});
        }
    }

    private void sortWage() {
        nhanVienArrayList.clear();
        loadDataTableReport();
        Collections.sort(nhanVienArrayList, new WageComparator());
    }

    public void updateTableWage(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        sortWage();
        for (NhanVien nhanVien1 : nhanVienArrayList) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition(), nhanVien1.getWage()});
        }
    }


    // test ở đây
    public static void main(String[] args) throws Exception {
        new ActionManagenment();
    }
}


