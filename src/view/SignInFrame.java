package view;


import controller.IControllerManagenment;

import javax.swing.*;

// Frame chính
public class SignInFrame extends JFrame {
    public SignInFrame(JPanel siginPanel, IControllerManagenment iController) throws Exception {
        setTitle("Human Resource managenment");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(siginPanel);

        setResizable(false);
        setVisible(true);
    }
}