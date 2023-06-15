package util;

import javax.swing.*;
import java.awt.*;

public class UserInterfaceUtils {
    public static JLabel getHeaderLabel(String text){
        JLabel header = new JLabel(text);
        header.setBackground(Color.red);
        header.setOpaque(true);
        return header;
    }
}
