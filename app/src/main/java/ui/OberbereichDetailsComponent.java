package ui;

import de.dhbwka.swe.utils.gui.ObservableComponent;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class OberbereichDetailsComponent extends ObservableComponent {
    public static JPanel createDetailComponent() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.setBorder(new LineBorder(Color.green, 2));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel topPanel = new JPanel(new GridBagLayout());

        mainPanel.add(topPanel, BorderLayout.NORTH);

        JLabel header = new JLabel("Details");
        header.setBackground(Color.red);
        header.setOpaque(true);
        topPanel.add(header, gridBagConstraints);

        JLabel oberbereichNameLabel = new JLabel("Oberbereich Nord");
        oberbereichNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gridBagConstraints.gridy = 1;
        topPanel.add(oberbereichNameLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(new JLabel("40% Auslastung"), gridBagConstraints);

        List<JPanel> stellbereichPanels = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            JPanel stellbereichPanel = new JPanel(new BorderLayout());
            stellbereichPanel.add(new JLabel("Stellbereich " + i), BorderLayout.NORTH);
            stellbereichPanel.add(new JLabel("Auslastung " + Math.round(Math.random() * 100) + "%"), BorderLayout.SOUTH);
            stellbereichPanels.add(stellbereichPanel);
        }
        mainPanel.add(ExtendedListComponent.createListComponent(stellbereichPanels), BorderLayout.CENTER);

        return mainPanel;
    }
}
