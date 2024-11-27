package controller;

import data.chamCong.ChamCong;
import data.nghiPhep.NghiPhep;
import data.nhanVien.NhanVien;
import utilities.PositionComparator;
import utilities.WageComparator;
import view.*;
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

// lớp này dùng để làm những hành động của các panel có trong view
public class ActionManagenment implements IControllerManagenment {
    MyCanvasManagenment myCanvas;
    MyCanvasManagenment myCanvasAccounttant;
    PanelMidManagenment panelMid;
    PanelBottomManagenment panelBottom;
    PanelLeftManagenment panelLeftManagenment;
    PanelLeftAccountant panelLeftAccountant;
    PanelService panelService;
    PanelServiceMid panelServiceMid;
    SiginPanel siginPanel;
    SignInFrame signInFrame;
    SignInForm signInForm;
    CardLayout cardLayout;
    JPanel cardPanel;
    PanelReport panelReport;
    PanelWorkManagenment panelWorkManagenment;
    PanelWorkManagenment.PanelWork panelWork;
    PanelLeave panelLeave;
    LeaveListPanel leaveListPanel;
    PanelTimeKeepingMid panelTimeKeepingMid;
    PanelTimeKeeping panelTimeKeeping;
    Map<String, NhanVien> nhanVienListMap;
    ArrayList<NhanVien> nhanVienArrayList;
    Map<String, String> accounts;
    ArrayList<ChamCong> chamCongs;
    Set<NghiPhep> nghiPheps;
    boolean click = false;
    public  static  String role;

    //constructor
    public ActionManagenment() throws Exception {
        nghiPheps = new HashSet<>();
        chamCongs = new ArrayList<>();
        nhanVienListMap = new LinkedHashMap<>();
        nhanVienArrayList = new ArrayList<>();
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        accounts = new HashMap<>();
        panelTimeKeepingMid = new PanelTimeKeepingMid(this);
        panelTimeKeeping = new PanelTimeKeeping(this, panelTimeKeepingMid);
        leaveListPanel = new LeaveListPanel(nghiPheps, this);
        panelLeave = new PanelLeave(this, nghiPheps);
        panelWork = new PanelWorkManagenment.PanelWork();
        panelWorkManagenment = new PanelWorkManagenment(panelWork, this);
        panelReport = new PanelReport(this);
        panelMid = new PanelMidManagenment(this);
        panelServiceMid = new PanelServiceMid(this);
        panelService = new PanelService(panelServiceMid);
        panelBottom = new PanelBottomManagenment(this);
        panelLeftManagenment = new PanelLeftManagenment(this);
        panelLeftAccountant = new PanelLeftAccountant(this);
        myCanvas = new MyCanvasManagenment(panelMid, panelBottom, panelLeftManagenment);
        myCanvasAccounttant = new MyCanvasManagenment(panelMid, panelBottom, panelLeftAccountant);
        siginPanel = new SiginPanel(this, cardPanel);
        signInForm = new SignInForm(this, cardPanel);
        cardPanel.add(siginPanel, "login");
        cardPanel.add(myCanvas, "truongphong");
        cardPanel.add(myCanvasAccounttant, "ketoan");
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
                role = text.getText();
                if (accounts.containsKey(text.getText()) && accounts.get(text.getText()).equals(new String(password1.getPassword()))) {
                    JOptionPane.showMessageDialog(signInFrame, "Đăng nhập thành công!", "Thông Báo", JOptionPane.DEFAULT_OPTION);
                    switch (role) {
                        case "truongphong": {
                            cardLayout.show(cardPanel, "truongphong");
                            break;
                        }
                        case "ketoan": {
                            cardLayout.show(cardPanel, "ketoan");
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
                if (e.getActionCommand().equals("Đăng xuất")) {
                    int result = JOptionPane.showConfirmDialog(myCanvas, "Bạn có chắc chắn muốn thoát", "Thông Báo", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (result == JOptionPane.YES_OPTION) {
                        cardLayout.show(cardPanel, "login");
                    }
                }
            }
        };
    }


    private MyCanvasManagenment createCanvas(JPanel panelTop, JPanel panelBottom, JPanel panelLeft) {
        return new MyCanvasManagenment(panelTop, panelBottom, panelLeft);
    }

    private MyCanvasManagenment getCanvasByRole(String panelType) {
        JPanel panelLeft;
        JPanel panelTop;

        switch (role) {
            case "truongphong":
                panelLeft = panelLeftManagenment;
                switch (panelType) {
                    case "Báo cáo": panelTop = panelReport; break;
                    case "Nghỉ phép": panelTop = panelLeave; break;
                    case "Công việc": panelTop = panelWorkManagenment; break;
                    case "Danh sách NV": panelTop = panelService; break;
                    case "Chấm công": panelTop = panelTimeKeeping; break;
                    case "Tổng hợp lương":
                        panelTop = panelReport;
                        panelLeft = panelLeftAccountant;
                        break;
                    default: return null;
                }
                break;

            case "ketoan":
                panelLeft = panelLeftAccountant;
                switch (panelType) {
                    case "Nghỉ phép": panelTop = panelLeave; break;
                    case "Chấm công": panelTop = panelTimeKeeping; break;
                    case "Tổng hợp lương": panelTop = panelReport; break;
                    default: return null;
                }
                break;

            default:
                return null;
        }

        return createCanvas(panelTop, panelBottom, panelLeft);
    }
    @Override
    public ActionListener controlButtonLeft() {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String string = e.getActionCommand();
                MyCanvasManagenment canvas = getCanvasByRole(string);
                    cardPanel.add(canvas, string);
                    cardLayout.show(cardPanel, string);

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
    public KeyListener newTable(DefaultTableModel tableModel, JTextField inputSeach,JTable table) {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                String id = inputSeach.getText();
                if (id.equals("")) {
                    updateTableListEmployeee(tableModel);
                }
                ArrayList<NhanVien> list = new ArrayList<>();
                list.clear();
                for (NhanVien nhanVien : nhanVienListMap.values()) {
                    if (nhanVien.getName().toLowerCase().contains(inputSeach.getText().toLowerCase()) ||
                            nhanVien.getId().toLowerCase().contains(inputSeach.getText().toLowerCase())) {
                        list.add(nhanVien);
                    }
                }
                updateTable(tableModel);
                table.revalidate();
                table.repaint();
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
        } else {
            JOptionPane.showMessageDialog(panelServiceMid, "Không tìm thấy nhân viên với ID này!");
        }
    }

    // phương thức dùng để cập nhật thông tin của nhân viên lên bảng từ khi lúc thêm sửa xóa là 1 kiểu này
    private void updateTableListEmployeee(DefaultTableModel tableModel) {
        tableModel = new DefaultTableModel();
        for (NhanVien nhanVien1 : nhanVienListMap.values()) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition()});
        }
    }

    //chức năng cập nhật danh sách khi thêm nhân viên vào bảng
    private void updateAddEmployee(String name, String gender, String date, String position, DefaultTableModel tableModel) {
        String idEmployee = idEmployee();
        NhanVien nhanVien = new NhanVien(idEmployee, name, gender, date, position);
        nhanVienListMap.put(idEmployee, nhanVien);
        updateTableListEmployeee(tableModel);
        updateTable3(tableModel);
        JOptionPane.showMessageDialog(panelServiceMid, "Thêm thành công!");
    }

    // chức năng lấy id tự động tăng dần
    private String idEmployee() {
        int idMax = 1;
        for (String id : nhanVienListMap.keySet()) {
            int numericId = Integer.parseInt(id);
            if (numericId >= idMax) {
                idMax = numericId + 1;
            }
        }

        return Integer.toString(idMax);
    }

    // chức năng xuất file trong báo cáo
    public void xuatBaoCaoTXT() {
        loadListEmployee();
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
                for (NhanVien nv : nhanVienListMap.values()) {
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
        for (NhanVien nhanVien1 : nhanVienListMap.values()) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition(), nhanVien1.getWage()});
        }

    }

    // chức năng cập nhật dữ liệu từ file dataReport.txt vào bảng
    public void loadDataTableReport() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/dataReport.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    NhanVien nhanVien = new NhanVien(data[0], data[1], data[2], data[3], data[4], 0);
                    nhanVien.setWage(Math.round(calculateIndividualSalary(nhanVien)));
                    nhanVienListMap.put(nhanVien.getId(), nhanVien);
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
        loadDataTimeKeeping();
        int countChamCong = 0;
        int countNghiPhep = 0;
        double salary = wageEmployee(nhanVien);  // Lương cơ bản của nhân viên
        boolean[] hasLeave = new boolean[3];
        boolean[] hasAttendance = new boolean[3];
        ArrayList arrayListNghiPhep = new ArrayList(nghiPheps);
        for (int i = 0; i < arrayListNghiPhep.size(); i++) {
            if (nhanVien.getId().equals(arrayListNghiPhep.get(i))) {
                if (countNghiPhep < 3) {
                    hasLeave[countNghiPhep++] = true;
                }
            }
        }
        for (int i = 0; i < chamCongs.size(); i++) {
            if (nhanVien.getId().equals(chamCongs.get(i).getId())) {
                if (countChamCong < 3) {
                    hasAttendance[countChamCong++] = true;
                }
            }
        }

        for (int i = 0; i < hasAttendance.length; i++) {
            if (hasAttendance[i] == false && hasLeave[i] == false) {
                salary -= salary * (1 - 0.8);
            } else if (hasAttendance[i] == false && hasLeave[i] == true) {
                salary -= salary * (1 - 0.3);
            }
        }
        return salary;

    }

    // chức năng cập nhật dữ liệu vào trong set
    @Override
    public void loadDataWork(Set<NghiPhep> allLeaves) {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/leave.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    NghiPhep nghiPhep = new NghiPhep(data[0], data[1], data[2], data[3], data[4]);
                    allLeaves.add(nghiPhep);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
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

                    case "Cập nhật": {
                        updateTable3(tableModel);
                    }
                }
            }
        };
    }

    // chức năng sắp xếp vị trí của nhân viên
    private ArrayList sortPosition() {
        ArrayList arrayList = new ArrayList(nhanVienListMap.values());
        Collections.sort(arrayList, new PositionComparator());
        return arrayList;
    }

    // chức năng cập nhật vị trí khi đã sắp xếp theo chức vụ
    private void updateTablePosition(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        nhanVienArrayList = sortPosition();
        for (NhanVien nhanVien1 : nhanVienArrayList) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition(), nhanVien1.getWage()});
        }
    }

    // chức năng sắp xếp theo lương tính được
    private ArrayList sortWage() {
        ArrayList arrayList = new ArrayList(nhanVienListMap.values());
        Collections.sort(arrayList, new WageComparator());
        return arrayList;
    }

    // chức năng cập nhật vị trí khi đã sắp xếp theo số lương
    public void updateTableWage(DefaultTableModel tableModel) {
        tableModel.setRowCount(0);
        nhanVienArrayList = sortWage();
        for (NhanVien nhanVien1 : nhanVienArrayList) {
            tableModel.addRow(new Object[]{nhanVien1.getId(), nhanVien1.getName(), nhanVien1.getGender(), nhanVien1.getDate(), nhanVien1.getPosition(), nhanVien1.getWage()});
        }
    }

    // chức năng tạo biểu đồ thống kê
    @Override
    public void createAndDisplayChart(DefaultTableModel tableModel) {
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

    // tạo biểu đồ chủa chức năng thống kê chấm công
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
    @Override
    public void findTimeKeeping(JTextField inputSeach, DefaultTableModel tableModel, JTable table) {
        loadDataTimeKeeping();
        if (inputSeach.getText().equals("")) {
            return;
        } else {
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
    }

    // chức năng cập nhật lại bảng khi jtextfiel không có kí tự nào
    @Override
    public KeyListener newTable1(DefaultTableModel tableModel, JTextField inputSeach, JButton button) {
        return new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if (inputSeach.getText().equals("")) {
                    updateTableTimeKeeping(tableModel);
                    button.setEnabled(true);
                }
            }
        };
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

    //chức năng dùng để add nhân viên vào trong danh sách
    @Override
    public void addEmployee(DefaultTableModel tableModel) {
        String[] chucVu = {"Quan ly", "Ke toan", "Ky su"};
        String[] gioiTinh = {"Nam", "Nu"};
        JTextField nameField = new JTextField("Nhập Họ và Tên", 15);
        JComboBox gender = new JComboBox(gioiTinh);
        JTextField dateField = new JTextField("yyyy-mm-dd", 15);
        JComboBox position = new JComboBox(chucVu);
        JPanel panel = new JPanel();

        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameField.getText().trim().equals("Nhập Họ và Tên")) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().trim().isEmpty()) {
                    nameField.setText("Nhập Họ và Tên");
                    nameField.setForeground(Color.GRAY);
                }
            }
        });

        // Xử lý placeholder và kiểm tra định dạng cho dateField
        dateField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dateField.getText().trim().equals("yyyy-mm-dd")) {
                    dateField.setText("");
                    dateField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = dateField.getText().trim();
                if (text.isEmpty()) {
                    dateField.setText("yyyy-mm-dd");
                    dateField.setForeground(Color.GRAY);
                } else if (!text.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    dateField.setText("yyyy-mm-dd");
                    dateField.setForeground(Color.GRAY);
                }
            }
        });

        // Chỉ cho phép chữ cái trong nameField
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                    e.consume(); // Hủy bỏ nếu nhập số
                }
            }
        });

        // Chỉ cho phép số và dấu '-' trong dateField
        dateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                // Kiểm tra nếu ký tự không phải số thì hủy bỏ
                if (!Character.isDigit(c)) {
                    e.consume();
                    return;
                }

                // Tự động thêm dấu "-" khi nhập xong phần 'yyyy' hoặc 'mm'
                String text = dateField.getText();
                int length = text.length();

                if (length == 4 || length == 7) {
                    dateField.setText(text + "-");
                }

                // Giới hạn độ dài không vượt quá 10 ký tự (định dạng yyyy-mm-dd)


                // Khi đã đủ độ dài 10 ký tự, thực hiện kiểm tra tháng, ngày và tuổi
                if (length == 9) {
                    // Kiểm tra định dạng yyyy-mm-dd
                    try {
                        String[] parts = (text + c).split("-"); // Lấy các phần năm, tháng, ngày
                        int year = Integer.parseInt(parts[0]);
                        int month = Integer.parseInt(parts[1]);
                        int day = Integer.parseInt(parts[2]);

                        // Kiểm tra tháng từ 1 đến 12
                        if (month < 1 || month > 12) {
                            showErrorDialog("Tháng không hợp lệ. Vui lòng nhập từ 01 đến 12.");
                            e.consume();
                            return;
                        }

                        // Kiểm tra ngày trong tháng hợp lệ
                        if (day < 1 || day > getDaysInMonth(year, month)) {
                            showErrorDialog("Ngày không hợp lệ trong tháng đã chọn.");
                            e.consume();
                            return;
                        }

                        // Kiểm tra tuổi (18 <= tuổi <= 60)
                        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                        int age = currentYear - year;
                        if (age < 18 || age > 60) {
                            showErrorDialog("Tuổi phải từ 18 đến 60. Vui lòng kiểm tra lại.");
                            e.consume();
                        }

                    } catch (Exception ex) {
                        e.consume();
                    }
                }
            }

            // Hàm hỗ trợ để lấy số ngày trong tháng
            private int getDaysInMonth(int year, int month) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.set(year, month - 1, 1); // Tháng tính từ 0-11
                return calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
            }

            // Hàm hỗ trợ để hiện hộp thoại khi nhập và ngày tháng năm không thực tế
            private void showErrorDialog(String message) {
                JOptionPane.showMessageDialog(null, message, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                dateField.setText(""); // Xóa nội dung không hợp lệ
            }

        });

        panel.add(new JLabel("Tên:"));
        panel.add(nameField);
        panel.add(Box.createHorizontalStrut(15)); // tạo khoảng cách giữa các ô nhập
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Giới Tính:"));
        panel.add(gender);
        panel.add(Box.createHorizontalStrut(15)); // tạo khoảng cách giữa các ô nhập
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Ngày sinh"));
        panel.add(dateField);
        panel.add(Box.createHorizontalStrut(15)); // tạo khoảng cách giữa các ô nhập
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Chức vụ"));
        panel.add(position);

        int result = JOptionPane.showConfirmDialog(null, panel,
                "Nhập thông tin", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            String name = nameField.getText();
            String gender1 = (String) gender.getSelectedItem();
            String date1 = dateField.getText();
            String position1 = (String) position.getSelectedItem();
            updateAddEmployee(name, gender1, date1, position1, tableModel);
            updateTable3(tableModel);
        }

    }

    // chức năng dùng dể sửa thông tin trong danh sách
    @Override
    public void fixEmployee(DefaultTableModel tableModel, JTextField input, JTable table) {
        String[] chucVu = {"Quan ly", "Ke toan", "Ky su"};
        String[] gioiTinh = {"Nam", "Nu"};
        JTextField nameField = new JTextField(10);
        JComboBox gender = new JComboBox(gioiTinh);
        JTextField dateField = new JTextField(15);
        JComboBox position = new JComboBox(chucVu);
        JPanel panel = new JPanel();
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (nameField.getText().trim().equals("Nhập Họ và Tên")) {
                    nameField.setText("");
                    nameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (nameField.getText().trim().isEmpty()) {
                    nameField.setText("Nhập Họ và Tên");
                    nameField.setForeground(Color.GRAY);
                }
            }
        });

        // Xử lý placeholder và kiểm tra định dạng cho dateField
        dateField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (dateField.getText().trim().equals("yyyy-mm-dd")) {
                    dateField.setText("");
                    dateField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                String text = dateField.getText().trim();
                if (text.isEmpty()) {
                    dateField.setText("yyyy-mm-dd");
                    dateField.setForeground(Color.GRAY);
                } else if (!text.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    dateField.setText("yyyy-mm-dd");
                    dateField.setForeground(Color.GRAY);
                }
            }
        });

        // Chỉ cho phép chữ cái trong nameField
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                    e.consume(); // Hủy bỏ nếu nhập số
                }
            }
        });

        // Chỉ cho phép số và dấu '-' trong dateField
        dateField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();

                // Kiểm tra nếu ký tự không phải số thì hủy bỏ
                if (!Character.isDigit(c)) {
                    e.consume();
                    return;
                }

                // Tự động thêm dấu "-" khi nhập xong phần 'yyyy' hoặc 'mm'
                String text = dateField.getText();
                int length = text.length();

                if (length == 4 || length == 7) {
                    dateField.setText(text + "-");
                }

                // Giới hạn độ dài không vượt quá 10 ký tự (định dạng yyyy-mm-dd)


                // Khi đã đủ độ dài 10 ký tự, thực hiện kiểm tra tháng, ngày và tuổi
                if (length == 9) {
                    // Kiểm tra định dạng yyyy-mm-dd
                    try {
                        String[] parts = (text + c).split("-"); // Lấy các phần năm, tháng, ngày
                        int year = Integer.parseInt(parts[0]);
                        int month = Integer.parseInt(parts[1]);
                        int day = Integer.parseInt(parts[2]);

                        // Kiểm tra tháng từ 1 đến 12
                        if (month < 1 || month > 12) {
                            showErrorDialog("Tháng không hợp lệ. Vui lòng nhập từ 01 đến 12.");
                            e.consume();
                            return;
                        }

                        // Kiểm tra ngày trong tháng hợp lệ
                        if (day < 1 || day > getDaysInMonth(year, month)) {
                            showErrorDialog("Ngày không hợp lệ trong tháng đã chọn.");
                            e.consume();
                            return;
                        }

                        // Kiểm tra tuổi (18 <= tuổi <= 60)
                        int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                        int age = currentYear - year;
                        if (age < 18 || age > 60) {
                            showErrorDialog("Tuổi phải từ 18 đến 60. Vui lòng kiểm tra lại.");
                            e.consume();
                        }

                    } catch (Exception ex) {
                        e.consume();
                    }
                }
            }

            // Hàm hỗ trợ để lấy số ngày trong tháng
            private int getDaysInMonth(int year, int month) {
                java.util.Calendar calendar = java.util.Calendar.getInstance();
                calendar.set(year, month - 1, 1); // Tháng tính từ 0-11
                return calendar.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
            }

            // Hàm hỗ trợ để hiện hộp thoại khi nhập và ngày tháng năm không thực tế
            private void showErrorDialog(String message) {
                JOptionPane.showMessageDialog(null, message, "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                dateField.setText(""); // Xóa nội dung không hợp lệ
            }

        });
        panel.add(new JLabel("Tên:"));
        panel.add(nameField);
        panel.add(Box.createHorizontalStrut(15)); // tạo khoảng cách giữa các ô nhập
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Giới Tính:"));
        panel.add(gender);
        panel.add(Box.createHorizontalStrut(15)); // tạo khoảng cách giữa các ô nhập
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Ngày sinh"));
        panel.add(dateField);
        panel.add(Box.createHorizontalStrut(15)); // tạo khoảng cách giữa các ô nhập
        panel.add(Box.createHorizontalStrut(15));
        panel.add(new JLabel("Chức vụ"));
        panel.add(position);
        String id = input.getText().trim();
        int selectedRow = table.getSelectedRow(); // Lấy dòng được chọn từ bảng

        if (selectedRow == -1 && id.isEmpty()) {
            JOptionPane.showMessageDialog(panelServiceMid, "Vui lòng chọn một dòng trên bảng hoặc nhập ID để xóa!");
            return;
        }

        // Nếu không có ID trong JTextField, lấy ID từ dòng được chọn
        if (id.isEmpty() && selectedRow != -1) {
            id = tableModel.getValueAt(selectedRow, 0).toString();
        }
        int result = JOptionPane.showConfirmDialog(null, panel,
                "Nhập thông tin", JOptionPane.OK_CANCEL_OPTION);
        if (result == 0) {
            String name = nameField.getText();
            String gender1 = (String) gender.getSelectedItem();
            String date1 = dateField.getText();
            String position1 = (String) position.getSelectedItem();
            updateEmployee(id, name, gender1, date1, position1, tableModel);
            updateTable3(tableModel);
            input.setText("");
        }


    }

    // chức năng dùng để xóa thông tin của nhân viên trong danh sách
    @Override
    public void deleteEmployee(DefaultTableModel tableModel, JTextField input, JTable table) {
        String id = input.getText().trim();
        int selectedRow = table.getSelectedRow(); // Lấy dòng được chọn từ bảng

        if (selectedRow == -1 && id.isEmpty()) {
            JOptionPane.showMessageDialog(panelServiceMid, "Vui lòng chọn một dòng trên bảng hoặc nhập ID để xóa!");
            return;
        }

        // Nếu không có ID trong JTextField, lấy ID từ dòng được chọn
        if (id.isEmpty() && selectedRow != -1) {
            id = tableModel.getValueAt(selectedRow, 0).toString();
        }
        NhanVien nhanVien = nhanVienListMap.get(id);
        if (nhanVien != null) {
            nhanVienListMap.remove(id);
            updateTableListEmployeee(tableModel);
            updateTable3(tableModel);
            JOptionPane.showMessageDialog(panelServiceMid, "Xóa nhân viên thành công!");
            input.setText("");
        } else {
            JOptionPane.showMessageDialog(panelServiceMid, "Không tìm thấy nhân viên với ID này!");
        }
    }

    // chức năng thêm data vào danh sách chấm công từ file TimeKeeping.txt
    private void loadDataTimeKeeping() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/TimeKeeping.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 8) {
                    ChamCong chamCong = new ChamCong(data[0], data[1], data[2], data[3], data[4], data[5], Integer.parseInt(data[6]), Integer.parseInt(data[7]));
                    chamCongs.add(chamCong);
                }
            }
            Collections.sort(chamCongs,Comparator.comparing(ChamCong::getDate).reversed());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // test ở đây
    public static void main(String[] args) throws Exception {
        new ActionManagenment();
    }
}


