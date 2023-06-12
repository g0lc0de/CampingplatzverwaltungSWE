package ui;

import de.dhbwka.swe.utils.gui.ObservableComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DetailsComponent extends ObservableComponent {
    public JPanel createDetailComponent(JPanel customDetails){
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());

        mainPanel.setBorder(new LineBorder(Color.green, 2));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JLabel header = new JLabel("Details");
        header.setBackground(Color.red);
        header.setOpaque(true);
        mainPanel.add(header, gridBagConstraints);

        gridBagConstraints.gridy = 1;
        mainPanel.add(customDetails, gridBagConstraints);

        return mainPanel;
    }
}
