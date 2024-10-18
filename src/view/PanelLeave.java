package view;

import controller.IControllerManagenment;
import data.nghiPhep.NghiPhep;
import data.nhanVien.NhanVien;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class PanelLeave extends JPanel {
    JScrollPane jScrollPane;
    JButton buttonBack, buttonFind;
    JTextField jTextField;
    ArrayList<String> nameButton;
    static PriorityQueue<NghiPhep> list = new PriorityQueue<NghiPhep>(new Comparator<NghiPhep>() {
        @Override
        public int compare(NghiPhep o1, NghiPhep o2) {
            return o2.getDate().compareTo(o1.getDate());
        }
    });
    static Set<NghiPhep> nghiPheps = new HashSet<>();
   static Panel panel;

    public PanelLeave(IControllerManagenment iControllerManagenment) {
        this.panel = new Panel();
        JPanel panel1 = new JPanel();
        jTextField = new JTextField(20);
        buttonBack = new JButton("<- Back");
        buttonBack.addActionListener(iControllerManagenment.backHome());
        buttonFind = new JButton("Find");
        buttonFind.addActionListener(e -> {
            if (jTextField.getText().isEmpty()) {
                panel.removeAll();
               panel.addPanel();
            } else {
                findLeave(jTextField.getText());
            }


        });
        jScrollPane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setLayout(new BorderLayout());
        add(jScrollPane, BorderLayout.CENTER);
        panel1.add(jTextField);
        panel1.add(buttonFind);
        panel1.setLayout(new FlowLayout());
        JPanel jPanel = new JPanel();
        jPanel.add(buttonBack);
        jPanel.add(panel1);
        jPanel.setLayout(new FlowLayout());
        add(jPanel, BorderLayout.SOUTH);
    }

    public void findLeave(String keyword) {
        panel.removeAll();
        PriorityQueue<NghiPhep> filteredList = new PriorityQueue<>(new Comparator<NghiPhep>() {
            @Override
            public int compare(NghiPhep o1, NghiPhep o2) {
                return o2.getDate().compareTo(o1.getDate());
            }
        });

        for (NghiPhep nghiPhep : nghiPheps) {
            if (nghiPhep.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    nghiPhep.getId().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(nghiPhep);
            }
        }

        panel.addPanel1(filteredList);
        panel.revalidate();
        panel.repaint();
    }

    public static class Panel extends JPanel {
        JLabel id, ten, tieuDe, mota, ngayThangNam;

        public Panel() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            addPanel();
            setPreferredSize(new Dimension(400, 1500));
        }

        public void addPanel() {
            loadDataWork();
            list.addAll(nghiPheps);
            int n = list.size();
            for (int i = 0; i < n; i++) {
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BorderLayout());
                jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JPanel jPanel1 = new JPanel();
                jPanel1.setLayout(new BorderLayout());
                NghiPhep nghiPhep = list.poll();
                tieuDe = new JLabel(nghiPhep.getTitle());
                mota = new JLabel(nghiPhep.getReason());
                ngayThangNam = new JLabel(nghiPhep.getDate());
                ten = new JLabel(nghiPhep.getName());
                id = new JLabel(nghiPhep.getId());
                ten.setFont(new Font("a", Font.BOLD, 15));
                id.setFont(new Font("a", Font.BOLD, 10));
                tieuDe.setFont(new Font("a", Font.BOLD, 15));
                mota.setFont(new Font("a", Font.HANGING_BASELINE, 13));
                JPanel jPanel3 = new JPanel();
                jPanel3.add(id);
                jPanel3.add(ten);
                jPanel3.setLayout(new FlowLayout());
                jPanel1.add(tieuDe, BorderLayout.NORTH);
                jPanel1.add(mota, BorderLayout.SOUTH);
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(jPanel3);
                panel.add(jPanel1);
                jPanel.add(panel, BorderLayout.WEST);
                JPanel jPanel2 = new JPanel();
                jPanel2.setLayout(new BorderLayout());
                jPanel2.add(ngayThangNam, BorderLayout.SOUTH);
                jPanel.add(jPanel2, BorderLayout.EAST);
                this.add(jPanel);
                this.revalidate();
                this.repaint();
            }
        }

        public void addPanel1(PriorityQueue<NghiPhep> filteredList) {
            int n = filteredList.size();
            for (int i = 0; i < n; i++) {
                JPanel jPanel = new JPanel();
                jPanel.setLayout(new BorderLayout());
                jPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                JPanel jPanel1 = new JPanel();
                jPanel1.setLayout(new BorderLayout());
                NghiPhep nghiPhep = filteredList.poll();
                tieuDe = new JLabel(nghiPhep.getTitle());
                mota = new JLabel(nghiPhep.getReason());
                ngayThangNam = new JLabel(nghiPhep.getDate());
                ten = new JLabel(nghiPhep.getName());
                id = new JLabel(nghiPhep.getId());
                ten.setFont(new Font("a", Font.BOLD, 15));
                id.setFont(new Font("a", Font.BOLD, 10));
                tieuDe.setFont(new Font("a", Font.BOLD, 15));
                mota.setFont(new Font("a", Font.HANGING_BASELINE, 13));
                JPanel jPanel3 = new JPanel();
                jPanel3.add(id);
                jPanel3.add(ten);
                jPanel3.setLayout(new FlowLayout());
                jPanel1.add(tieuDe, BorderLayout.NORTH);
                jPanel1.add(mota, BorderLayout.SOUTH);
                JPanel panel = new JPanel();
                panel.setLayout(new FlowLayout());
                panel.add(jPanel3);
                panel.add(jPanel1);
                jPanel.add(panel, BorderLayout.WEST);
                JPanel jPanel2 = new JPanel();
                jPanel2.setLayout(new BorderLayout());
                jPanel2.add(ngayThangNam, BorderLayout.SOUTH);
                jPanel.add(jPanel2, BorderLayout.EAST);
                this.add(jPanel);
            }
        }

        public static void loadDataWork() {
            try (BufferedReader br = new BufferedReader(new FileReader("src/data/leave.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    if (data.length == 5) {
                        NghiPhep nghiPhep = new NghiPhep(data[0], data[1], data[2], data[3], data[4]);
                        nghiPheps.add(nghiPhep);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
