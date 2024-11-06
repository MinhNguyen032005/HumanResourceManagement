package view;

import controller.IControllerManagenment;
import data.congViec.CongViec;
import data.nhanVien.NhanVien;

import javax.swing.*;
import java.awt.*;
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
    JButton button;
    static PriorityQueue<CongViec> list = new PriorityQueue<>(new Comparator<CongViec>() {
        @Override
        public int compare(CongViec o1, CongViec o2) {
            return Integer.compare(o1.getNum(), o2.getNum());
        }
    });

    public PanelWorkManagenment(PanelWork panelWork, IControllerManagenment iControllerManagenment) {
        JPanel jPanel=new JPanel();
        jScrollPane = new JScrollPane(panelWork, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jScrollPane.setPreferredSize(new Dimension(750, 300));
        setLayout(new BorderLayout());
        jPanel.add(jScrollPane, BorderLayout.CENTER);
        add(jPanel);


    }

    public static class PanelWork extends JPanel {
        JCheckBox jCheckBox;
        JLabel tieuDe, mota, ngayThangNam, chucVu;

        public PanelWork() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            addPanel();
            setPreferredSize(new Dimension(200, 1000));
        }

        public void addPanel() {
            loadDataWork();
            int n = list.size();
            for (int i = 0; i < n; i++) {
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BorderLayout());
                jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                jCheckBox = new JCheckBox();
                JPanel jPanel1 = new JPanel();
                jPanel1.setLayout(new BorderLayout());
                CongViec congViec = list.poll();
                tieuDe = new JLabel(congViec.getTitle());
                mota = new JLabel(congViec.getDeception());
                ngayThangNam = new JLabel(congViec.getDate());
                chucVu = new JLabel(congViec.getPosition());
                tieuDe.setFont(new Font("a", Font.BOLD, 15));
                mota.setFont(new Font("a", Font.HANGING_BASELINE, 13));
                jPanel1.add(tieuDe, BorderLayout.NORTH);
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


