package ui;

import controller.TabController;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.properties.CampingArea;
import model.properties.Region;
import util.StaticSourceNames;
import util.UserInterfaceUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class CampingAreaDetailComponent extends ObservableComponent implements IUpdateEventListener {

    IDepictable oberbereich;
    JLabel oberbereichNameLabel;
    JLabel oberbereichBeschreibungLabel;
    List<IDepictable> campingAreasList;

    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {
        oberbereich = (Region) updateEvent.getData();
        System.out.println(oberbereich);
        this.campingAreasList = (List<IDepictable>) oberbereich.getAttributeArray()[Region.CAMPING_AREAS].getValue();

        try {
            stellbereicheListComponent.removePanels();
            stellbereicheListComponent.rerenderIDepictables(this.campingAreasList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        oberbereichNameLabel.setText((String) oberbereich.getAttributeArray()[Region.NAME].getValue());
        oberbereichBeschreibungLabel.setText((String) oberbereich.getAttributeArray()[Region.DESCRIPTION].getValue());
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

    public CampingAreaDetailComponent(IDepictable attributes) {
        this.oberbereich = attributes;
        this.campingAreasList = (List<IDepictable>) oberbereich.getAttributeArray()[Region.CAMPING_AREAS].getValue();
        this.oberbereichNameLabel = new JLabel((String) oberbereich.getAttributeArray()[Region.NAME].getValue());
        this.oberbereichBeschreibungLabel = new JLabel((String) oberbereich.getAttributeArray()[Region.DESCRIPTION].getValue());
//        this.oberbereichBeschreibungLabel = new JLabel((String) oberbereich.getAttributeArray()[CampingArea.LAGEBESCHREIBUNG].getValue());
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

        oberbereichNameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        gridBagConstraints.gridy = 1;
        topPanel.add(oberbereichNameLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
//        topPanel.add(oberbereichBeschreibungLabel, gridBagConstraints);
//        gridBagConstraints.gridy += 1;
        oberbereichBeschreibungLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        topPanel.add(oberbereichBeschreibungLabel, gridBagConstraints);

        stellbereicheListComponent.addSourceName(StaticSourceNames.OBERBEREICH_DETAILS_COMPONENT);
        stellbereicheListComponent.removePanels();
        stellbereicheListComponent.addIDepictables(campingAreasList);

        stellbereicheListComponent.addMoveEventFunction(() -> {
            TabController.getInstance().switchPage(1);
        });

        mainPanel.add(stellbereicheListComponent.build(), BorderLayout.CENTER);

        return mainPanel;
    }


}
