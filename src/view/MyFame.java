package view;

import controller.IController;

import javax.swing.*;
import java.awt.*;

public class MyFame  extends JFrame {


    public MyFame()  {
        setTitle("Quản Lý Nhân Sự ");
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        MenuBar menuBar=new MyMenuBar();
        setMenuBar(menuBar);



        setResizable(false);
        setVisible(true);




    }

    public static void main(String[] args) {
        new MyFame();
    }
}
