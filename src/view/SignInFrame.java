package view;



import javax.swing.*;

public class SignInFrame extends JFrame {
    public SignInFrame() throws Exception {
        setTitle("Human Resource managenment");
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        add(new SiginPanel());

        setVisible(true);
    }

    public static void main(String[] args) throws Exception {
        new SignInFrame();
    }
}