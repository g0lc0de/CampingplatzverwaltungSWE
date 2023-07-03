package ui;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.Oberbereich;
import util.StaticSourceNames;
import util.UserInterfaceUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class OberbereichDetailsComponent extends ObservableComponent implements IUpdateEventListener {

    IDepictable oberbereich;
    JLabel oberbereichNameLabel;
    JLabel oberbereichBeschreibungLabel;
    List<IDepictable> stellplatzList;

    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {
        oberbereich = (Oberbereich) updateEvent.getData();
        System.out.println(oberbereich);
        this.stellplatzList = (List<IDepictable>) oberbereich.getAttributeArray()[Oberbereich.STELLPLATZLIST].getValue();
        System.out.println(stellplatzList);
        oberbereichNameLabel.setText((String) oberbereich.getAttributeArray()[Oberbereich.NAME].getValue());
        oberbereichBeschreibungLabel.setText((String) oberbereich.getAttributeArray()[Oberbereich.LAGEBESCHREIBUNG].getValue());
    }

    public enum Commands implements EventCommand {

        OBERBEREICH_SELECTED("OberbereichDetailsComponent.oberbereichSelected", String.class);

        String cmdText;
        Class<?> payloadType;

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

    public OberbereichDetailsComponent(IDepictable attributes) {
        this.oberbereich = attributes;
        this.stellplatzList = (List<IDepictable>) oberbereich.getAttributeArray()[Oberbereich.STELLPLATZLIST].getValue();
        this.oberbereichNameLabel = new JLabel((String) oberbereich.getAttributeArray()[Oberbereich.NAME].getValue());
        this.oberbereichBeschreibungLabel = new JLabel((String) oberbereich.getAttributeArray()[Oberbereich.LAGEBESCHREIBUNG].getValue());
    }

    private ExtendedListComponent stellbereicheListComponent = new ExtendedListComponent();

    public ExtendedListComponent getStellbereicheListComponent() {
        return stellbereicheListComponent;
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

        oberbereichNameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gridBagConstraints.gridy = 1;
        topPanel.add(oberbereichNameLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(oberbereichBeschreibungLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(new JLabel("40% Auslastung"), gridBagConstraints);

        stellbereicheListComponent.addSourceName(StaticSourceNames.OBERBEREICH_DETAILS_COMPONENT);
        stellbereicheListComponent.addIDepictables(stellplatzList);

        mainPanel.add(stellbereicheListComponent.build(), BorderLayout.CENTER);

        return mainPanel;
    }


}
