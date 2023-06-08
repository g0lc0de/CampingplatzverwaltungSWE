package ui;

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
    private List<IDepictable> stellplatzList = Arrays.asList(
            new Stellplatz("1", false),
            new Stellplatz("2", true),
            new Stellplatz("3", false),
            new Stellplatz("4", false),
            new Stellplatz("5", true),
            new Stellplatz("6", false),
            new Stellplatz("7", false),
            new Stellplatz("8", true),
            new Stellplatz("9", false)
    );

    public UebersichtTabComponent() throws Exception {
        JFrame frame = new JFrame();
        UebersichtTabController controller = new UebersichtTabController(this);

        frame.setLayout(new GridLayout(1, 3));

        StellplatzSelector stellplatzSelector = new StellplatzSelector(stellplatzList, this);

        stellplatzSelector.addObserver(this);
        frame.add(stellplatzSelector
        );

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
        addObserver(controller);

        frame.add(textField);

        frame.setSize(1080, 720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {
        System.out.println("Update:" + updateEvent.getData());
    }

    @Override
    public void processGUIEvent(GUIEvent guiEvent) {
        System.out.println("Received from child:" + guiEvent.getCmd() + " " + guiEvent.getSource());
        if(guiEvent.getCmd() == StellplatzSelector.Commands.PLACE_SELECTED){
            Stellplatz stellplatz = (Stellplatz) stellplatzList.get(Integer.parseInt(guiEvent.getSource().toString())-1);
            try {
                stellplatz.setReserviert(!stellplatz.isReserviert());
                fireUpdateEvent(new UpdateEvent(this, StellplatzSelector.Commands.UPDATE_PLACES));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
