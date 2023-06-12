package ui;

import controller.UebersichtTabController;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.Stellplatz;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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

    public String text = "";

    public UebersichtTabComponent(List<IDepictable> stellplaetze) throws Exception {

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

        List<JPanel> oberbereichePanels = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            JPanel oberbereichPanel = new JPanel(new BorderLayout());
            oberbereichPanel.add(new JLabel("Oberbereich " + i), BorderLayout.NORTH);
            oberbereichPanel.add(new JLabel("Auslastung " + Math.round(Math.random() * 100) + "%"), BorderLayout.SOUTH);
            oberbereichePanels.add(oberbereichPanel);
        }

        JPanel middlePanelHolder = new JPanel(new BorderLayout());
        middlePanelHolder.add((new ExtendedListComponent()).createListComponent(oberbereichePanels), BorderLayout.NORTH);
        JPanel buttonPanel = new JPanel(new GridLayout(4,1));
        buttonPanel.add(new JButton("Buchung anlegen"));
        buttonPanel.add(new JButton("Buchung einseihen"));
        buttonPanel.add(new JButton("Mitarbeiter ansehen"));
        buttonPanel.add(new JButton("Daten verwalten"));
        middlePanelHolder.add(buttonPanel);
        frame.add(middlePanelHolder);


        OberbereichDetailsComponent detailsComponent = new OberbereichDetailsComponent();
        frame.add(detailsComponent.createDetailComponent(), BorderLayout.NORTH);


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
