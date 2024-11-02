package controller;

import data.chamCong.ChamCong;
import data.nghiPhep.NghiPhep;
import data.nhanVien.NhanVien;
import utilities.PositionComparator;
import utilities.WageComparator;
import view.*;
import controller.IControllerManagenment;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

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
    PanelWorkManagenment panelWorkManagenment;
    PanelWorkManagenment.PanelWork panelWork;
    PanelLeave panelLeave;
    LeaveListPanel leaveListPanel;
    PanelTimeKeepingMid panelTimeKeepingMid;
    PanelTimeKeeping panelTimeKeeping;
    Stack<JPanel> jPanelStack;
    Map<String, NhanVien> nhanVienListMap;
    ArrayList<NhanVien> nhanVienArrayList;
    Map<String, String> accounts;
    ArrayList<ChamCong> chamCongs;
    Set<NghiPhep> nghiPheps;

    public ActionManagenment() throws Exception {
        nghiPheps = new HashSet<>();
        chamCongs = new ArrayList<>();
        nhanVienListMap = new LinkedHashMap<>();
        nhanVienArrayList = new ArrayList<>();
        jPanelStack = new Stack<>();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        accounts = new HashMap<>();
        panelTimeKeepingMid = new PanelTimeKeepingMid(this);
        panelTimeKeeping = new PanelTimeKeeping(this, panelTimeKeepingMid);
        leaveListPanel = new LeaveListPanel(nghiPheps);
        panelLeave = new PanelLeave(this, nghiPheps);
        panelWork = new PanelWorkManagenment.PanelWork();
        panelWorkManagenment = new PanelWorkManagenment(panelWork, this);
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
        cardPanel.add(panelWorkManagenment, "CV");
        cardPanel.add(panelLeave, "LV");
        cardPanel.add(panelTimeKeeping, "TK");
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
                    int result = JOptionPane.showConfirmDialog(myCanvas, "Bạn có chắc chắn muốn thoát", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        goBack();
                    } else {
                        cardLayout.show(cardPanel, "truongphong");
                    }
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
                        jPanelStack.push(myCanvas);
                        break;
                    }
                    case "Nghỉ phép": {
                        cardLayout.show(cardPanel, "LV");
                        jPanelStack.push(myCanvas);
                        break;
                    }
                    case "Công việc": {
                        cardLayout.show(cardPanel, "CV");
                        jPanelStack.push(myCanvas);
                        break;
                    }
                    case "Danh sách NV": {
                        cardLayout.show(cardPanel, "DV");
                        jPanelStack.push(myCanvas);
                        break;
                    }
                    case "Chấm công": {
                        cardLayout.show(cardPanel, "TK");
                        jPanelStack.push(myCanvas);
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

    //hàm để add danh sách nhân viên vào bảng
    @Override
    public void updateTable(DefaultTableModel tableModel) {
        loadListEmployee();
        tableModel.setRowCount(0);
        for (NhanVien nhanVien1 : nhanVienListMap.values()) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition()});
        }

    }

    //hàm dùng để tìm kiếm nhân viên bằng cách nhập ID vào để tìm
    @Override
    public ActionListener findEmPloyee(JTextField jTextField, DefaultTableModel tableModel) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchEmployeeById(jTextField, tableModel);
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
                    updateTableListEmployeee(tableModel);
                }
            }
        };
    }

    //chức năng của button tìm kiếm
    private void searchEmployeeById(JTextField jTextField, DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        ArrayList<NhanVien> list = new ArrayList<>();
        for (NhanVien nhanVien1 : nhanVienListMap.values()) {
            if (nhanVien1.getName().toLowerCase().contains(jTextField.getText().toLowerCase()) ||
                    nhanVien1.getId().toLowerCase().contains(jTextField.getText().toLowerCase())) {
                list.add(nhanVien1);
            }
        }
        for (NhanVien nhanVien : list) {
            tableModel.addRow(new Object[]{nhanVien.getId(), nhanVien.getName(), nhanVien.getGender(), nhanVien.getDate(), nhanVien.getPosition()});
        }
    }

    //hàm dùng để put những nhân viên vào trong map có key bằng chính id của nó
    public void loadListEmployee() {
        for (NhanVien nhanVien : nhanVienArrayList) {
            nhanVienListMap.put(nhanVien.getId(), nhanVien);
        }
    }

    //chức năng sửa thông tin nhân viên
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

    //phương thức dùng để cập nhật thông tin của nhân viên lên bảng hiện thị từ lúc đầu
    private void updateEmployee(String id, String newName, String newGender, String newDate, String newPosition, DefaultTableModel tableModel) {
        NhanVien nhanVien = nhanVienListMap.get(id);
        if (nhanVien != null) {
            nhanVien.setName(newName);
            nhanVien.setGender(newGender);
            nhanVien.setDate(newDate);
            nhanVien.setPosition(newPosition);
            nhanVienListMap.put(id, nhanVien);
            updateTableListEmployeee(tableModel);
            JOptionPane.showMessageDialog(panelServiceMid, "Sửa thông tin thành công!");
            cardLayout.show(cardPanel, "DV");
        } else {
            JOptionPane.showMessageDialog(panelServiceMid, "Không tìm thấy nhân viên với ID này!");
            cardLayout.show(cardPanel, "DV");
        }
    }

    // phương thức dùng để cập nhật thông tin của nhân viên lên bảng từ khi lúc thêm sửa xóa là 1 kiểu này
    private void updateTableListEmployeee(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        for (NhanVien nhanVien1 : nhanVienListMap.values()) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition()});
        }
    }

    // chức năng dùng để điều khiển các nút thêm sửa xóa trong chức năng danh sách nhân viên
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

    //  chức năng thêm nhân viên vào bảng
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

    //chức năng cập nhật danh sách khi thêm nhân viên vào bảng
    private void updateAddEmployee(String name, String gender, String date, String position, DefaultTableModel tableModel) {
        String idEmployee = idEmployee();
        NhanVien nhanVien = new NhanVien(idEmployee, name, gender, date, position);
        nhanVienListMap.put(idEmployee, nhanVien);
        updateTableListEmployeee(tableModel);
        JOptionPane.showMessageDialog(panelServiceMid, "Thêm thành công!");
        cardLayout.show(cardPanel, "DV");
    }

    // chức năng lấy id tự động tăng dần
    private String idEmployee() {
        int idMax = 0;
        idMax = nhanVienListMap.size();
        return Integer.toString(++idMax);
    }

    // chức năng xóa nhân viên trong bảng
    private void deleteEmployee(JTextField inputSeach, DefaultTableModel tableModel) {
        String id = inputSeach.getText();
        NhanVien nhanVien = nhanVienListMap.get(id);
        if (nhanVien != null) {
            nhanVienListMap.remove(id);
            updateTableListEmployeee(tableModel);
            JOptionPane.showMessageDialog(panelServiceMid, "Xóa nhân viên thành công!");
        } else {
            JOptionPane.showMessageDialog(panelServiceMid, "Không tìm thấy nhân viên với ID này!");
        }
    }

    // chức năng xuất file trong báo cáo
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

    // chức năng cập nhật thông tin vào bảng
    public void updateTable3(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        nhanVienArrayList.clear();
        loadDataTableReport();
        for (NhanVien nhanVien1 : nhanVienArrayList) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition(), nhanVien1.getWage()});
        }
    }

    // chức năng cập nhật dữ liệu từ file dataReport.txt vào bảng
    public void loadDataTableReport() {
        try (BufferedReader br = new BufferedReader(new FileReader("/home/wanmin/ForderOfMy/human resource management/src/data/dataReport.txt "))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    NhanVien nhanVien = new NhanVien(data[0], data[1], data[2], data[3], data[4], 0);
                    nhanVien.setWage(Math.round(calculateIndividualSalary(nhanVien)));
                    nhanVienArrayList.add(nhanVien);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // chức năng tính lương của nhân viên
    private double wageEmployee(NhanVien nhanVien) {
        return nhanVien.wage();  // Tính lương cơ bản cho từng nhân viên
    }

    // chức năng tính lương dựa trên nghỉ phép và chấm công
    private double calculateIndividualSalary(NhanVien nhanVien) {
        double salary = wageEmployee(nhanVien);  // Lương cơ bản của nhân viên
        boolean hasLeave = false;
        boolean hasAttendance = false;

        for (NghiPhep nghiPhep : nghiPheps) {
            if (nhanVien.getId().equals(nghiPhep.getId())) {
                hasLeave = true;  // Nhân viên có nghỉ phép
                break;
            }
        }

        for (ChamCong chamCong : chamCongs) {
            if (nhanVien.getId().equals(chamCong.getId())) {
                hasAttendance = true;  // Nhân viên có chấm công
                break;
            }
        }
        if (hasLeave && hasAttendance) {
            return salary * (1 - 0.2);  // Giảm 20% lương khi có nghỉ phép và chấm công
        } else if (!hasLeave && hasAttendance) {
            return salary * (1 - 0.5);  // Giảm 50% lương khi không nghỉ phép nhưng có chấm công
        } else {
            return salary * (1 - 0.8);  // Giảm 80% lương khi không nghỉ phép và không chấm công
        }
    }

    // chức năng các nút của report
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

    // chức năng sắp xếp vị trí của nhân viên
    private void sortPosition() {
        nhanVienArrayList.clear();
        loadDataTableReport();
        Collections.sort(nhanVienArrayList, new PositionComparator());
    }

    // chức năng cập nhật vị trí khi đã sắp xếp theo chức vụ
    private void updateTablePosition(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        sortPosition();
        for (NhanVien nhanVien1 : nhanVienArrayList) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition(), nhanVien1.getWage()});
        }
    }

    // chức năng sắp xếp theo lương tính được
    private void sortWage() {
        nhanVienArrayList.clear();
        loadDataTableReport();
        Collections.sort(nhanVienArrayList, new WageComparator());
    }

    // chức năng cập nhật vị trí khi đã sắp xếp theo số lương
    public void updateTableWage(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        sortWage();
        for (NhanVien nhanVien1 : nhanVienArrayList) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition(), nhanVien1.getWage()});
        }
    }

    // chức năng điều khiển các button ở chức năng chấm công
    @Override
    public ActionListener controlButtonTimeKeeping(JTextField inputSeach, DefaultTableModel tableModel, JTable table) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = e.getActionCommand();
                switch (string) {
                    case "Thống kê": {
                        createAndDisplayChart(tableModel);
                        break;
                    }
                    case "Tìm kiếm": {
                        findTimeKeeping(inputSeach, tableModel, table);
                        break;
                    }
                }
            }
        };
    }

    // chức năng tạo biểu đồ thống kê
    private void createAndDisplayChart(DefaultTableModel tableModel) {
        chamCongs.clear();
        JPanel chartPanelContainer = new JPanel();
        chartPanelContainer.setLayout(new BorderLayout());
        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 400));
        chartPanelContainer.add(chartPanel, BorderLayout.CENTER);
        JOptionPane.showMessageDialog(panelTimeKeeping, chartPanelContainer, "Biểu đồ chấm công", JOptionPane.PLAIN_MESSAGE);
    }

    // hàm phụ cho phương thức tạo bảng thống kê
    private CategoryDataset createDataset() {
        loadDataTimeKeeping();
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (ChamCong chamCong : chamCongs) {
            dataset.addValue(chamCong.getTimeWork(), "Giờ làm", chamCong.getName());
        }
        return dataset;
    }

    // tạo biểu đồ
    private JFreeChart createChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Biểu đồ chấm công nhân viên",
                "Nhân viên",
                "Giờ làm",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
    }

    // chức năng tìm kiếm nhân viên xem chấm công như thế nào
    public void findTimeKeeping(JTextField inputSeach, DefaultTableModel tableModel, JTable table) {
        ArrayList<ChamCong> list = new ArrayList<>();
        list.clear();
        for (ChamCong chamCong : chamCongs) {
            if (chamCong.getName().toLowerCase().contains(inputSeach.getText().toLowerCase()) ||
                    chamCong.getId().toLowerCase().contains(inputSeach.getText().toLowerCase())) {
                list.add(chamCong);
            }
        }
        updateTableTimeKeeping1(tableModel, list);
        table.revalidate();
        table.repaint();
    }

    // hàm phụ cập nhật lại khi tìm kiếm nhân viên
    public void updateTableTimeKeeping1(DefaultTableModel tableModel, ArrayList<ChamCong> list) {
        tableModel.setRowCount(0);
        for (ChamCong chamCong : list) {
            tableModel.addRow(new Object[]{chamCong.getId(), chamCong.getName(), chamCong.getDate(), chamCong.getRank(), chamCong.getInTime(), chamCong.getOutTime(), chamCong.getTimeWork(), chamCong.getLate()});
        }
        list.clear();

    }

    // chức năng tìm kiếm trên jTextfiel
    @Override
    public void updateTableTimeKeeping(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        loadDataTimeKeeping();
        for (ChamCong chamCong : chamCongs) {
            tableModel.addRow(new Object[]{chamCong.getId(), chamCong.getName(), chamCong.getDate(), chamCong.getRank(), chamCong.getInTime(), chamCong.getOutTime(), chamCong.getTimeWork(), chamCong.getLate()});
        }
        chamCongs.clear();

    }

    // chức năng thêm data vào danh sách chấm công từ file TimeKeeping.txt
    private void loadDataTimeKeeping() {
        try (BufferedReader br = new BufferedReader(new FileReader("/home/wanmin/ForderOfMy/human resource management/src/data/TimeKeeping.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8) {
                    ChamCong chamCong = new ChamCong(data[0], data[1], data[2], data[3], data[4], data[5], Integer.parseInt(data[6]), Integer.parseInt(data[7]));
                    chamCongs.add(chamCong);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // test ở đây
    public static void main(String[] args) throws Exception {
        new ActionManagenment();
    }
}


