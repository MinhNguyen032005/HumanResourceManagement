package controller;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;

public interface IControllerManagenment {
    ActionListener login(JTextField text, JPasswordField password, IControllerManagenment iController);

    ActionListener logout();

    ActionListener controlButtonLeft();

    ActionListener backHome();

    void loadDataFromFile();

    void updateTable(DefaultTableModel tableModel);

    ActionListener findEmPloyee(JTextField jTextField, DefaultTableModel tableModel);

    KeyListener newTable(DefaultTableModel tableModel, JTextField inputSeach);

    ActionListener fixInformationEmployee(JTextField inputSeach, JTextField inputName, JTextField inputGender, JTextField inputDate, JTextField inputPosition, DefaultTableModel tableModel);


    ActionListener controlButtonEmployee(JTextField inputSeach, DefaultTableModel tableModel);

    ActionListener addEmployee(JTextField inputName, JTextField inputGender, JTextField inputDate, JTextField inputPosition, DefaultTableModel tableModel);


    void updateTable3(DefaultTableModel tableModel);

    void loadDataTableReport();

    ActionListener controlReport(DefaultTableModel tableModel);

    ActionListener controlButtonTimeKeeping(JTextField inputSeach, DefaultTableModel tableModel,JTable table);


    void updateTableTimeKeeping(DefaultTableModel tableModel);
}
