package ui;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.IUpdateEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.Stellplatz;
import util.UserInterfaceUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class CampingSpaceDetailComponent extends ObservableComponent implements IUpdateEventListener {

    IDepictable subarea;
    JLabel subareaNameLabel;
    JLabel subareaDescLabel;

    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {

    }

    public enum Commands implements EventCommand {

        CAMPING_SPACE_SELECTED("CampingSpacesDetailComponent.campingSpaceSelected", String.class);

        final String cmdText;
        final Class<?> payloadType;

        Commands(String cmdText, Class<?> payloadType) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }

        @Override
        public String getCmdText() {
            return cmdText;
        }

        @Override
        public Class<?> getPayloadType() {
            return payloadType;
        }
    }

    public CampingSpaceDetailComponent(IDepictable iDepictable) {
        this.subarea = iDepictable;
        subareaNameLabel = new JLabel((String) subarea.getAttributeArray()[Stellplatz.ID].getValue());
        subareaDescLabel = new JLabel("");
    }

    public JPanel createDetailComponent() throws Exception {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        mainPanel.setBorder(new LineBorder(Color.green, 2));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel topPanel = new JPanel(new GridBagLayout());

        mainPanel.add(topPanel, BorderLayout.NORTH);

        topPanel.add(UserInterfaceUtils.getHeaderLabel("Details"), gridBagConstraints);

        subareaNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gridBagConstraints.gridy = 1;
        topPanel.add(subareaNameLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(subareaDescLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(new JLabel("40% Auslastung"), gridBagConstraints);

        return mainPanel;
    }


}
