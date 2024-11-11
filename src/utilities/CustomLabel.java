package utilities;

import javax.swing.*;
import java.awt.*;

// class này dùng để custom Label cho label chính của giao diện
public class CustomLabel extends JLabel {
    public CustomLabel(String text) {
        super(text);
    }

    public void setCustomFont(Font font, float size) {
        super.setFont(FontLoader.loadCustomizeFont(font, size));
    }

}
