package ui;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import org.w3c.dom.events.UIEvent;
import util.StaticSourceNames;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class OverviewTabComponent extends ObservableComponent implements IUpdateEventListener, IGUIEventListener {

    public enum Commands implements EventCommand {

        TEXT_CHANGED("UebersichtTabComponent.textChanged", String.class),
        BUCHUNG_ANLEGEN("UebersichtTabComponent.buchungAnlegen", String.class);
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
    private CampingAreaDetailComponent campingAreaDetailComponent;
    private ExtendedListComponent oberbereichListComponent = new ExtendedListComponent();

    public ExtendedListComponent getOberbereichListComponent() {
        return oberbereichListComponent;
    }

    public CampingAreaDetailComponent getOberbereichDetailsComponent() {
        return campingAreaDetailComponent;
    }

    private List<IDepictable> campingAreas;
    private List<IDepictable> regions;
    public OverviewTabComponent(List<IDepictable> regions) throws Exception {

        this.regions = regions;
        this.campingAreaDetailComponent = new CampingAreaDetailComponent(regions.get(0));

        oberbereichListComponent.addSourceName(StaticSourceNames.UEBERSICHT_TAB_OBERBEREICHE_LIST);
        oberbereichListComponent.addIDepictables(regions);

        GridLayout gridLayout = new GridLayout(1, 3);
        this.setLayout(gridLayout);

        JPanel mapPanel = new JPanel(new BorderLayout());
        JButton oberbereichNordPanel = new JButton("Region Lakeside");
        mapPanel.add(oberbereichNordPanel, BorderLayout.NORTH);
        JButton oberbereichSuedPanel = new JButton("Region Magic Forest");
        mapPanel.add(oberbereichSuedPanel, BorderLayout.CENTER);

        this.add(mapPanel);

        JPanel middlePanelHolder = new JPanel(new GridLayout(2,1));
        JPanel listHolder = new JPanel(new BorderLayout());
        listHolder.add(oberbereichListComponent.build(), BorderLayout.CENTER);
        listHolder.setBorder(new LineBorder(Color.pink));
        middlePanelHolder.add(HeaderComponent.createHeaderComponent(listHolder, "Regions"));
        JPanel buttonPanel = new JPanel(new GridLayout(4,1));
        JButton createBookingButton = new JButton("Buchung anlegen");
        createBookingButton.setFont(new Font("Sans Serif", Font.BOLD,24));
        createBookingButton.setBackground(Color.WHITE);
        buttonPanel.add(createBookingButton);
        createBookingButton.addActionListener(e -> {
            fireGUIEvent(new GUIEvent(this, Commands.BUCHUNG_ANLEGEN));
//            new CreateBookingComponent();
        });
        JButton buchungEinsehenButton = new JButton("Buchung einseihen");
        buchungEinsehenButton.setFont(new Font("Sans Serif", Font.BOLD,24));
        buchungEinsehenButton.setBackground(Color.WHITE);
        buttonPanel.add(buchungEinsehenButton);

        JButton mitarbeiterAnsehen = new JButton("Mitarbeiter ansehen");
        buttonPanel.add(mitarbeiterAnsehen);
        mitarbeiterAnsehen.setFont(new Font("Sans Serif", Font.BOLD,24));
        mitarbeiterAnsehen.setBackground(Color.WHITE);

        JButton datenVerwalten = new JButton("Daten verwalten");
        datenVerwalten.setFont(new Font("Sans Serif", Font.BOLD,24));
        datenVerwalten.setBackground(Color.WHITE);
        buttonPanel.add(datenVerwalten);
        middlePanelHolder.add(buttonPanel);
        this.add(middlePanelHolder);

        this.add(campingAreaDetailComponent.createDetailComponent(), BorderLayout.NORTH);
    }

    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {
        System.out.println("UpdateEventReceived TabComponent:" + updateEvent.getCmdText());
//        fireUpdateEvent(updateEvent);
    }

    @Override
    public void processGUIEvent(GUIEvent guiEvent) {
//        fireGUIEvent(guiEvent);
    }
}
