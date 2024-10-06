package view;



import controller.IController;

import javax.swing.*;

public class SignInFrame extends JFrame {
    public SignInFrame(SiginPanel siginPanel, IController iController) throws Exception {
        setTitle("Human Resource managenment");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(siginPanel);

        setVisible(true);
    }
}