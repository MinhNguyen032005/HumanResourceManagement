package utilities;

import javax.swing.*;
import java.awt.*;

public class CustomLabel extends JLabel {
    public CustomLabel(String text) {
        super(text);
    }

    public void setCustomFont(Font font , float size) {
        super.setFont(FontLoader.loadCustomizeFont(font, size));
    }

}
