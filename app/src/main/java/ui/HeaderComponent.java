package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HeaderComponent {
    public static JPanel createHeaderComponent(JComponent customContent, String title){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.setBorder(new LineBorder(Color.green, 2));

        JLabel header = new JLabel(title);
        header.setBackground(Color.red);
        header.setOpaque(true);

        mainPanel.add(header, BorderLayout.NORTH);
        mainPanel.add(customContent, BorderLayout.CENTER);

        return mainPanel;
    }
}
