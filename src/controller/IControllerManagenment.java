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
}
