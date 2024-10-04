package utilities;

import java.awt.*;
import java.io.File;

public interface FontLoader {
    static Font loadFont(String filePath) {
        try {
            return Font.createFont(Font.TRUETYPE_FONT,  new File(filePath));
        }
        catch (Exception e) {
            return null;
        }
    }
    static Font loadCustomizeFont(String filePath, float size) throws Exception {
        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT,  new File(filePath));
            return font.deriveFont(size);
        }
        catch (Exception e) {
            return null;
        }
    }
    static Font loadCustomizeFont(Font font, float size) {
        return font.deriveFont(size);
    }
}
