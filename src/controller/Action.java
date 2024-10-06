package controller;

import view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Action implements IController {
    MyCanvasManagenment myCanvas;
    MyFameManagenment myFame;
    MyMenuBarManagenment menuBar;
    PanelTopManagenment panelTop;
    PanelMidManagenment panelMid;
    PanelBottomManagenment panelBottom;
    SiginPanel siginPanel;
    SignInFrame signInFrame;
    SignInForm signInForm;
    Map<String, String> accounts;

    public Action() throws Exception {
        accounts = new HashMap<>();
        panelTop = new PanelTopManagenment(this);
        panelMid = new PanelMidManagenment(this);
        panelBottom = new PanelBottomManagenment(this);
        menuBar = new MyMenuBarManagenment(this);
        myCanvas = new MyCanvasManagenment(panelTop, panelMid, panelBottom);
        siginPanel = new SiginPanel(this);
        signInForm = new SignInForm(this);
        new SignInFrame(siginPanel, this);
    }

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

    public static void main(String[] args) throws Exception {
        new Action();
    }

    @Override
    public ActionListener login(JTextField text, JPasswordField password1, IController iController) {
        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAccountsFromFile();
                if (accounts.containsKey(text.getText()) && accounts.get(text.getText()).equals(new String(password1.getPassword()))) {
                    JOptionPane.showMessageDialog(signInFrame, "Đăng nhập thành công!", "Thông Báo", JOptionPane.DEFAULT_OPTION);
                    switch (text.getText()) {
                        case "truongphong": {
                            myFame = new MyFameManagenment(myCanvas, iController);
                            myFame.setVisible(true);
                            myFame.pack();
                            SwingUtilities.getWindowAncestor(signInFrame).dispose();
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
}
