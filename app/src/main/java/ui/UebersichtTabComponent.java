package ui;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.Oberbereich;
import util.StaticSourceNames;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UebersichtTabComponent extends ObservableComponent implements IUpdateEventListener, IGUIEventListener {

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
    private OberbereichDetailsComponent oberbereichDetailsComponent;
    private ExtendedListComponent oberbereichListComponent = new ExtendedListComponent();

    public ExtendedListComponent getOberbereichListComponent() {
        return oberbereichListComponent;
    }

    public OberbereichDetailsComponent getOberbereichDetailsComponent() {
        return oberbereichDetailsComponent;
    }

    private List<IDepictable> oberbereiche;

    public UebersichtTabComponent(List<IDepictable> oberbereiche) throws Exception {

        this.oberbereiche = oberbereiche;
        this.oberbereichDetailsComponent = new OberbereichDetailsComponent(oberbereiche.get(0));

        oberbereichListComponent.addSourceName(StaticSourceNames.UEBERSICHT_TAB_OBERBEREICHE_LIST);
        oberbereichListComponent.addIDepictables(oberbereiche);

        JFrame frame = new JFrame();
        GridLayout gridLayout = new GridLayout(1, 3);
        frame.setLayout(gridLayout);

        JPanel mapPanel = new JPanel(new BorderLayout());
        JButton oberbereichNordPanel = new JButton("Oberbereich Nord");
        mapPanel.add(oberbereichNordPanel, BorderLayout.NORTH);
        JButton oberbereichMittePanel = new JButton("Oberbereich Mitte");
        mapPanel.add(oberbereichMittePanel, BorderLayout.CENTER);
        JButton oberbereichSuedPanel = new JButton("Oberbereich Süd");
        mapPanel.add(oberbereichSuedPanel, BorderLayout.SOUTH);

        frame.add(mapPanel);

        JPanel middlePanelHolder = new JPanel(new GridLayout(2,1));
        JPanel listHolder = new JPanel(new BorderLayout());
        listHolder.add(oberbereichListComponent.build(), BorderLayout.CENTER);
        listHolder.setBorder(new LineBorder(Color.pink));
        middlePanelHolder.add(HeaderComponent.createHeaderComponent(listHolder, "Oberbereiche"));
        JPanel buttonPanel = new JPanel(new GridLayout(4,1));
        buttonPanel.add(new JButton("Buchung anlegen"));
        buttonPanel.add(new JButton("Buchung einseihen"));
        buttonPanel.add(new JButton("Mitarbeiter ansehen"));
        buttonPanel.add(new JButton("Daten verwalten"));
        middlePanelHolder.add(buttonPanel);
        frame.add(middlePanelHolder);

        frame.add(oberbereichDetailsComponent.createDetailComponent(), BorderLayout.NORTH);


        frame.setSize(1080, 720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
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
