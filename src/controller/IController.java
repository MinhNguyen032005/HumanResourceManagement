package controller;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface IController {
    ActionListener login(JTextField text, JPasswordField password,IController iController);

}
