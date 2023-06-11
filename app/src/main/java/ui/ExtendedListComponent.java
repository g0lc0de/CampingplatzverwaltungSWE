package ui;

import de.dhbwka.swe.utils.gui.ObservableComponent;

import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.List;

public class ExtendedListComponent extends ObservableComponent {

    public static final int ROWS = 5;

    public JScrollPane createListComponent(List<JPanel> panels) throws Exception {
        JPanel mainList = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        for (JPanel panel : panels) {
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));

            mainList.add(panel, gbc);
        }

        return new JScrollPane(mainList);
    }


}
