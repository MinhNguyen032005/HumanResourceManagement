package utilities;

import java.awt.*;
import java.io.File;

//class này dùng để chứa font chữ của những chữ trong dự án
public interface FontLoader {
    static Font loadFont(String filePath) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(filePath));
        } catch (Exception e) {
            return null;
        }
    }

    static Font loadCustomizeFont(Font font, float size) {
        return font.deriveFont(size);
    }
}
