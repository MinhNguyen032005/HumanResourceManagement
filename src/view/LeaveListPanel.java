package view;

import controller.IControllerManagenment;
import data.nghiPhep.NghiPhep;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;

public class LeaveListPanel extends JPanel {
    private PriorityQueue<NghiPhep> leaveList;
    private static Set<NghiPhep> allLeaves;

    public LeaveListPanel(Set<NghiPhep> allLeaves, IControllerManagenment iControllerManagenment) {

        this.allLeaves = allLeaves;
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        refreshList(iControllerManagenment,allLeaves);
    }

    // chức năng dùng để load file vào và sắp xếp theo ngày tháng tư bé đến lơn
    public void refreshList(IControllerManagenment iControllerManagenment,Set<NghiPhep> allLeaves ) {
        removeAll();
        iControllerManagenment.loadDataWork(allLeaves);
        leaveList = new PriorityQueue<>(Comparator.comparing(NghiPhep::getDate).thenComparing(NghiPhep::getId).reversed());
        leaveList.addAll(allLeaves);

        for (NghiPhep leave : leaveList) {
            addLeaveToPanel(leave);
        }

        revalidate();
        repaint();
    }

    // chức năng dùng để tìm kiếm thông tin nhân viên
    public void filterLeaves(String keyword) {
        removeAll();
        PriorityQueue<NghiPhep> filteredLeaves = new PriorityQueue<>(Comparator.comparing(NghiPhep::getDate).reversed());

        for (NghiPhep leave : allLeaves) {
            if (leave.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                    leave.getId().toLowerCase().contains(keyword.toLowerCase())) {
                filteredLeaves.add(leave);
            }
        }

        for (NghiPhep leave : filteredLeaves) {
            addLeaveToPanel(leave);
        }

        revalidate();
        repaint();
    }

    // chức năng dùng để thêm các lịch nghỉ phép vào trong panel
    private void addLeaveToPanel(NghiPhep leave) {
        JPanel leavePanel = new JPanel(new BorderLayout());
        leavePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        JPanel infoPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel(leave.getTitle());
        JLabel reasonLabel = new JLabel(leave.getReason());
        JLabel dateLabel = new JLabel(leave.getDate());
        JLabel nameLabel = new JLabel(leave.getName());
        JLabel idLabel = new JLabel(leave.getId());

        titleLabel.setFont(new Font("Arial", Font.BOLD, 15));
        reasonLabel.setFont(new Font("Arial", Font.PLAIN, 13));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        idLabel.setFont(new Font("Arial", Font.PLAIN, 10));

        JPanel nameIdPanel = new JPanel(new FlowLayout());
        nameIdPanel.add(idLabel);
        nameIdPanel.add(nameLabel);

        infoPanel.add(titleLabel, BorderLayout.NORTH);
        infoPanel.add(reasonLabel, BorderLayout.SOUTH);

        JPanel contentPanel = new JPanel(new FlowLayout());
        contentPanel.add(nameIdPanel);
        contentPanel.add(infoPanel);

        leavePanel.add(contentPanel, BorderLayout.WEST);
        leavePanel.add(dateLabel, BorderLayout.EAST);

        this.add(leavePanel);
    }


}
