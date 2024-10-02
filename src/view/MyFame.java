package view;

import controller.IController;

import javax.swing.*;
import java.awt.*;

public class MyFame  extends JFrame {
    public MyFame(MyCanvas myCanvas,IController iController)  {
        setTitle("Quản Lý Nhân Sự ");
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        MenuBar menuBar=new MyMenuBar(iController);
        setMenuBar(menuBar);
        getContentPane().add(myCanvas);


        setResizable(false);
        setVisible(true);




    }
}
