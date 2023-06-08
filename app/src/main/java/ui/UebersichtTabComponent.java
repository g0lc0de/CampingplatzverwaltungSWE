package ui;

import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ObservableComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UebersichtTabComponent extends ObservableComponent implements IUpdateEventListener {

    public enum Commands implements EventCommand{

        TEXT_CHANGED("UebersichtTabView.textChanged", String.class);

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

    public UebersichtTabComponent() throws Exception {
        JFrame frame = new JFrame();
        UebersichtTabController controller = new UebersichtTabController(this);

        frame.setLayout(new GridLayout(5, 5));

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
//        frame.add()

        frame.setSize(1080, 720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {
        System.out.println("Update:" + updateEvent.getData());
    }
}
