package ui;

import de.dhbwka.swe.utils.gui.ObservableComponent;
import util.UserInterfaceUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HeaderComponent extends ObservableComponent {
    public static JPanel createHeaderComponent(JComponent customContent, String title){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.setBorder(new LineBorder(Color.green, 2));

        mainPanel.add(UserInterfaceUtils.getHeaderLabel(title), BorderLayout.NORTH);
        mainPanel.add(customContent, BorderLayout.CENTER);

        return mainPanel;
    }
}
