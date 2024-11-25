package view;

import controller.IControllerManagenment;
import data.congViec.CongViec;
import data.congViec.Job;
import data.nhanVien.NhanVien;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

//
public class PanelWorkManagenment extends JPanel {
    static JScrollPane jScrollPane;
    static ArrayList<Job> arrayList = new ArrayList();
    static PriorityQueue<CongViec> list = new PriorityQueue<>(Comparator.comparing(CongViec::getDate).reversed());

    public PanelWorkManagenment(PanelWork panelWork, IControllerManagenment iControllerManagenment) {
        JPanel jPanel = new JPanel();
        jScrollPane = new JScrollPane(panelWork, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setPreferredSize(new Dimension(750, 400));
        setLayout(new BorderLayout());
        jPanel.add(jScrollPane, BorderLayout.CENTER);
        add(jPanel);


    }

    public static class PanelWork extends JPanel {
        JCheckBox jCheckBox;
        JLabel mota, ngayThangNam, chucVu;
        JButton buttonTieuDe;

        public PanelWork() {

            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            addPanel(this);
            setPreferredSize(new Dimension(200, 1500));


        }
        // chức năng này dùng để load dữ liệu từ hàm loadDataWork và loadDataInfomation từ file src/data/workSchedule.txt và src/data/DataInformationWorking.txt
        // có tác dụng là hiện hộp thoại khi bấm vào chủ đề muốn đọc được xem như mail báo công việc
        public void addPanel(PanelWork panelWork) {
            Font robotoMedium = FontLoader.loadFont("src/storage/font/Roboto-Medium.ttf");
            loadDataInfomation();
            loadDataWork();
            int n = list.size();
            for (int i = 0; i < n; i++) {
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BorderLayout());
                jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jCheckBox = new JCheckBox("Chưa đọc");
                jCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
                jCheckBox.setFont(FontLoader.loadCustomizeFont(robotoMedium, 15));
                jCheckBox.setPreferredSize(new Dimension(100, 30));
                jCheckBox.setBackground(new Color(0, 0, 0, 0));
                jCheckBox.setFocusable(false);
                jCheckBox.setOpaque(true);
                jCheckBox.setBorder(null);
                JPanel jPanel1 = new JPanel();
                jPanel1.setLayout(new BorderLayout());
                CongViec congViec = list.poll();
                jCheckBox.setSelected(congViec.isRead());
                jCheckBox.setText(congViec.isRead() ? "Đã đọc" : "Chưa đọc");
                buttonTieuDe = new JButton(congViec.getTitle());
                buttonTieuDe.setCursor(new Cursor(Cursor.HAND_CURSOR));
                buttonTieuDe.setHorizontalAlignment(SwingConstants.LEFT);
                buttonTieuDe.setFont(FontLoader.loadCustomizeFont(robotoMedium, 20f));
                buttonTieuDe.setPreferredSize(new Dimension(190, 30));
                buttonTieuDe.setBackground(new Color(0, 0, 0, 0));
                buttonTieuDe.setFocusable(false);
                buttonTieuDe.setOpaque(false);
                buttonTieuDe.setBorder(null);
                buttonTieuDe.setSize(50, 50);
                mota = new JLabel(congViec.getDeception());
                ngayThangNam = new JLabel(congViec.getDate());
                chucVu = new JLabel(congViec.getPosition());
                mota.setFont(new Font("a", Font.HANGING_BASELINE, 13));
                jPanel1.add(buttonTieuDe, BorderLayout.NORTH);
                jPanel1.add(mota, BorderLayout.SOUTH);
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(jCheckBox);
                panel.add(jPanel1);
                jPanel.add(panel, BorderLayout.WEST);
                JPanel jPanel2 = new JPanel();
                jPanel2.setLayout(new BorderLayout());
                jPanel2.add(chucVu, BorderLayout.NORTH);
                jPanel2.add(ngayThangNam, BorderLayout.SOUTH);
                jPanel.add(jPanel2, BorderLayout.EAST);
                List<JCheckBox> checkBoxList = new ArrayList<>();
                checkBoxList.add(jCheckBox);
                buttonTieuDe.addActionListener(e -> {
                    for (JCheckBox checkBox : checkBoxList) {
                        checkBox.setSelected(true);
                        jPanel.setBackground(Color.GRAY);
                        jPanel2.setBackground(Color.GRAY);
                        jPanel1.setBackground(Color.GRAY);
                        panel.setBackground(Color.GRAY);
                        congViec.setRead(true); // Cập nhật trạng thái trong đối tượng
                        checkBox.setText("Đã đọc"); // Cập nhật nhãn
                        this.revalidate();
                        this.repaint();
                    }
                    int i1 = findInformation(arrayList,congViec);
                    JPanel panelOptionpane = new JPanel();
                    panelOptionpane.setLayout(new BoxLayout(panelOptionpane, BoxLayout.Y_AXIS));
                    JLabel position = new JLabel(arrayList.get(i1).getPosition());
                    JLabel jobTitle = new JLabel(arrayList.get(i1).getJobTitle());
                    JLabel duty = new JLabel(arrayList.get(i1).getDuty());
                    JLabel date = new JLabel(String.valueOf(arrayList.get(i1).getDate()));
                    position.setFont(new Font("a", Font.BOLD, 20));
                    panelOptionpane.add(position);
                    panelOptionpane.add(Box.createVerticalStrut(10));
                    panelOptionpane.add(jobTitle);
                    panelOptionpane.add(Box.createVerticalStrut(10));
                    panelOptionpane.add(duty);
                    panelOptionpane.add(Box.createVerticalStrut(10));
                    panelOptionpane.add(date);

                    // Hiển thị hộp thoại với panel tùy chỉnh
                    JOptionPane.showMessageDialog(null, panelOptionpane, "Thông tin công việc", JOptionPane.INFORMATION_MESSAGE);
                });
                for (JCheckBox checkBox : checkBoxList) {
                    checkBox.addItemListener(e -> {
                        boolean isSelected = e.getStateChange() == 2;
                        jPanel.setBackground(null);
                        jPanel1.setBackground(null);
                        jPanel2.setBackground(null);
                        panel.setBackground(null);
                        congViec.setRead(isSelected);
                        checkBox.setText("Chưa đọc");
                    });

                }
                this.add(jPanel);
            }
        }
        // hàm dùng để tìm kiếm dựa trên thông tin nhập vào
        public int findInformation(ArrayList<Job> arrayList, CongViec congViec) {
            for (int j = 0; j < arrayList.size(); j++) {
                Job job = arrayList.get(j);
                if (job.getJobTitle().equals(congViec.getTitle())) {
                    return j;
                }
            }
            return -1;
        }
    }

    public static void loadDataWork() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/workSchedule.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) {
                    CongViec nhanVien = new CongViec(data[0], data[1], data[2], data[3], Integer.parseInt(data[4]));
                    list.add(nhanVien);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadDataInfomation() {
        try (BufferedReader br = new BufferedReader(new FileReader("src/data/DataInformationWorking.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split("\t");
                if (data.length == 4) {
                    Job job = new Job(data[0], data[1], data[2], data[3]);
                    arrayList.add(job);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}


