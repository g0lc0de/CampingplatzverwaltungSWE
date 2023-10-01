package ui;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import main.java.ui.CreateBookingComponent;
import util.StaticSourceNames;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.List;

public class OverviewTabComponent extends ObservableComponent implements IUpdateEventListener, IGUIEventListener {

    public enum Commands implements EventCommand {

        TEXT_CHANGED("UebersichtTabComponent.textChanged", String.class);

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

    private List<IDepictable> oberbereiche;

    public OverviewTabComponent(List<IDepictable> oberbereiche) throws Exception {

        this.oberbereiche = oberbereiche;
        this.campingAreaDetailComponent = new CampingAreaDetailComponent(oberbereiche.get(0));

        oberbereichListComponent.addSourceName(StaticSourceNames.UEBERSICHT_TAB_OBERBEREICHE_LIST);
        oberbereichListComponent.addIDepictables(oberbereiche);

        GridLayout gridLayout = new GridLayout(1, 3);
        this.setLayout(gridLayout);

        JPanel mapPanel = new JPanel(new BorderLayout());
        JButton oberbereichNordPanel = new JButton("Oberbereich Nord");
        mapPanel.add(oberbereichNordPanel, BorderLayout.NORTH);
        JButton oberbereichMittePanel = new JButton("Oberbereich Mitte");
        mapPanel.add(oberbereichMittePanel, BorderLayout.CENTER);
        JButton oberbereichSuedPanel = new JButton("Oberbereich Süd");
        mapPanel.add(oberbereichSuedPanel, BorderLayout.SOUTH);

        this.add(mapPanel);

        JPanel middlePanelHolder = new JPanel(new GridLayout(2,1));
        JPanel listHolder = new JPanel(new BorderLayout());
        listHolder.add(oberbereichListComponent.build(), BorderLayout.CENTER);
        listHolder.setBorder(new LineBorder(Color.pink));
        middlePanelHolder.add(HeaderComponent.createHeaderComponent(listHolder, "Oberbereiche"));
        JPanel buttonPanel = new JPanel(new GridLayout(4,1));
        JButton createBookingButton = new JButton("Buchung anlegen");
        buttonPanel.add(createBookingButton);
        createBookingButton.addActionListener(e -> {
            new CreateBookingComponent();
        });
        buttonPanel.add(new JButton("Buchung einseihen"));
        buttonPanel.add(new JButton("Mitarbeiter ansehen"));
        buttonPanel.add(new JButton("Daten verwalten"));
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
