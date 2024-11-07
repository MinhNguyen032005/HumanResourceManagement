package view;

import controller.IControllerManagenment;
import data.congViec.CongViec;
import data.nhanVien.NhanVien;
import utilities.FontLoader;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//
public class PanelWorkManagenment extends JPanel {
    static JScrollPane jScrollPane;
    static PriorityQueue<CongViec> list = new PriorityQueue<>(new Comparator<CongViec>() {
        @Override
        public int compare(CongViec o1, CongViec o2) {
            return Integer.compare(o1.getNum(), o2.getNum());
        }
    });

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

        public void addPanel(PanelWork panelWork) {
            Font robotoMedium = FontLoader.loadFont("/home/wanmin/ForderOfMy/human resource management/src/storage/font/Roboto-Medium.ttf");
            loadDataWork();
            int n = list.size();
            for (int i = 0; i < n; i++) {
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BorderLayout());
                jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jCheckBox = new JCheckBox();
                jCheckBox.setCursor(new Cursor(Cursor.HAND_CURSOR));
                jCheckBox.setHorizontalAlignment(SwingConstants.CENTER);
                jCheckBox.setFont(FontLoader.loadCustomizeFont(robotoMedium, 50f));
                jCheckBox.setPreferredSize(new Dimension(100, 30));
                jCheckBox.setBackground(new Color(0, 0, 0, 0));
                jCheckBox.setFocusable(false);
                jCheckBox.setOpaque(true);
                jCheckBox.setBorder(null);
                JPanel jPanel1 = new JPanel();
                jPanel1.setLayout(new BorderLayout());
                CongViec congViec = list.poll();
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
                buttonTieuDe.addActionListener(e -> {
                    jCheckBox.setSelected(true);
                    jPanel.setBackground(Color.GRAY);
                    jPanel2.setBackground(Color.GRAY);
                    jPanel1.setBackground(Color.GRAY);
                    panel.setBackground(Color.GRAY);
//                    jCheckBox.setSelected(true);
                    jCheckBox.setEnabled(true);
                    JOptionPane jOptionPane = new JOptionPane();

                });
                jCheckBox.addItemListener(e -> {
                    if (e.getStateChange() == 1) {
                        jPanel.setBackground(Color.GRAY);
                        jPanel2.setBackground(Color.GRAY);
                        jPanel1.setBackground(Color.GRAY);
                        panel.setBackground(Color.GRAY);
                    } else {
                        jPanel.setBackground(null);
                        jPanel1.setBackground(null);
                        jPanel2.setBackground(null);
                        panel.setBackground(null);
                    }
                });

                this.add(jPanel);
            }
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

}


