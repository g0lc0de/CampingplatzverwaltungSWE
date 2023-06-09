package ui;

import controller.UebersichtTabController;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.Stellplatz;

import javax.swing.*;
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
    public List<IDepictable> stellplaetze;
    private StellplatzSelector stellplatzSelector;

    public UebersichtTabComponent(List<IDepictable> stellplaetze) throws Exception {
        this.stellplaetze = stellplaetze;

        JFrame frame = new JFrame();
        frame.setLayout(new GridLayout(1, 3));

        stellplatzSelector = new StellplatzSelector(this.stellplaetze);
        frame.add(stellplatzSelector);

//        stellplatzSelector.addObserver(this);
//        this.addObserver(stellplatzSelector);


        List<JPanel> stellbereichePanels = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JPanel stellbereich = new JPanel(new BorderLayout());
            stellbereich.add(new JLabel("Stellbereich " + i), BorderLayout.NORTH);
            stellbereich.add(new JLabel("Auslastung " + Math.round(Math.random() * 100) + "%"), BorderLayout.SOUTH);
            stellbereichePanels.add(stellbereich);
        }

        JPanel extendedListHolder = new JPanel(new BorderLayout());
        extendedListHolder.add((new ExtendedListComponent()).createListComponent(stellbereichePanels), BorderLayout.NORTH);
        frame.add(extendedListHolder);

        JTextField textField = new JTextField(10);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text = textField.getText();
                fireGUIEvent(new GUIEvent(this, Commands.TEXT_CHANGED, text));
            }
        });

        frame.add(textField);

        frame.setSize(1080, 720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public StellplatzSelector getStellplatzSelector() {
        return stellplatzSelector;
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
