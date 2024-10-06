package view;

import controller.IController;

import javax.swing.*;
import java.awt.*;

public class MyFameManagenment extends JFrame {
    public MyFameManagenment(MyCanvasManagenment myCanvas, IController iController)  {
        setTitle("Quản Lý Nhân Sự ");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuBar menuBar=new MyMenuBarManagenment(iController);
        setMenuBar(menuBar);
        getContentPane().add(myCanvas);
        setResizable(false);

    }
}
