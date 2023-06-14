package ui;

import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.Stellplatz;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class ExtendedListComponent extends ObservableComponent {

    public static final int ROWS = 5;

    public static JScrollPane createListComponent(List<JPanel> panels) {
        JPanel mainList = new JPanel(new GridBagLayout());

        //TODO: Use BoxLayout

        mainList.setBorder(new LineBorder(Color.black, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTH;

        for (JPanel panel : panels) {
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));

            mainList.add(panel, gbc);
        }

        return new JScrollPane(mainList);
    }


}
