package ui;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class HeaderComponent {
    public static JPanel createHeaderComponent(JComponent customContent, String title){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        mainPanel.setBorder(new LineBorder(Color.green, 2));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = GridBagConstraints.NORTH;

        JLabel header = new JLabel(title);
        header.setBackground(Color.red);
        header.setOpaque(true);
        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 0;

        mainPanel.add(header, gridBagConstraints);
        gridBagConstraints.gridy = 2;
        gridBagConstraints.weighty = 24;
        mainPanel.add(customContent, gridBagConstraints);

        return mainPanel;
    }
}
