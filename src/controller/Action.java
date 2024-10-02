package controller;

import view.*;

public class Action implements IController {
    MyCanvas myCanvas;
    MyFame myFame;
    MyMenuBar menuBar;
    PanelTop panelTop;
    PanelMid panelMid;
    PanelBottom panelBottom;

    public Action() {
        panelTop =new PanelTop(this);
        panelMid =new PanelMid(this);
        panelBottom=new PanelBottom(this);
        menuBar =new MyMenuBar(this);
        myCanvas =new MyCanvas(panelTop,panelMid,panelBottom);
        new MyFame(myCanvas,this);
    }


    public static void main(String[] args) {
        new Action();
    }
}
