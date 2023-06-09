package ui;
import de.dhbwka.swe.utils.event.*;
import de.dhbwka.swe.utils.gui.ObservableComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonExample_Sender extends ObservableComponent implements IUpdateEventListener {

    public enum Commands implements EventCommand {
        BUTTON_PRESSED( "AttributeComponent.button1Pressed", String.class ),
        COLOURISE( "ButtonExample:Colourise", String.class ),
        BUTTON2_PRESSED( "AttributeComponent.button2Pressed", String.class );
        public final Class<?> payloadType;
        public final String cmdText;

        private Commands( String cmdText, Class<?> payloadType ) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public String getCmdText() {
            return this.cmdText;
        }

        /**
         * {@inheritDoc}
         */
        @Override
        public Class<?> getPayloadType() {
            return this.payloadType;
        }
    }
    private JButton button1;
    private JButton button2;
    private JTextField textField;

    public ButtonExample_Sender() {
        this.setLayout(new BorderLayout());
        //JPanel PnlButtons = new JPanel(new FlowLayout());
        JPanel PnlButtons = new JPanel(new GridLayout());
        this.add(PnlButtons, BorderLayout.CENTER);

        // Buttons erstellen
        button1 = new JButton("Button 1");
        button2 = new JButton("Button 2");
        PnlButtons.add(button1);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireGUIEvent(new GUIEvent(ButtonExample_Sender.this, Commands.BUTTON_PRESSED, "button1"));
            }
        });
        PnlButtons.add(button2);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    fireGUIEvent(new GUIEvent(ButtonExample_Sender.this, Commands.BUTTON2_PRESSED, "button2"));

            }
        });

        // Textfeld erstellen
        textField = new JTextField(15);
        textField.setEditable(false);
        textField.setVisible(false);
    }

    @Override
    public void processUpdateEvent(UpdateEvent ue) {
        if (((String)ue.getData()).equals("button1")){
            button1.setBackground(Color.green);
        }
        else if (((String)ue.getData()).equals("button2")){
            if (button2.getBackground() == Color.red){
                button2.setBackground(Color.green);
            }
            else {
                button2.setBackground(Color.red);
            }
        }
    }
}
