package ui;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.IUpdateEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.properties.CampingSpace;
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
        if(updateEvent.getCmd() == Commands.CAMPING_SPACE_SELECTED){
            subarea = (IDepictable) updateEvent.getData();
            fillLabels();
        }
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

    private void fillLabels(){
        subareaNameLabel.setText((String) subarea.getAttributeArray()[CampingSpace.ID].getValue());
        subareaDescLabel.setText((Boolean) subarea.getAttributeArray()[CampingSpace.RESERVED].getValue() ? "Reserviert" : "Nicht reserviert");
    }
    public CampingSpaceDetailComponent(IDepictable iDepictable) {
        this.subarea = iDepictable;
        this.setLayout(new BorderLayout());
        subareaNameLabel = new JLabel();
        subareaDescLabel = new JLabel();
        fillLabels();
        this.setBorder(new LineBorder(Color.green, 2));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel topPanel = new JPanel(new GridBagLayout());

        this.add(topPanel, BorderLayout.NORTH);

        topPanel.add(UserInterfaceUtils.getHeaderLabel("Details"), gridBagConstraints);

        subareaNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gridBagConstraints.gridy = 1;
        topPanel.add(subareaNameLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(subareaDescLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(new JLabel("40% Auslastung"), gridBagConstraints);
    }


}
