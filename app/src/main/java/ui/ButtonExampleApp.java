package ui;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.util.BaseController;
import de.dhbwka.swe.utils.util.IOUtilities;

import javax.swing.*;
import java.awt.*;

public class ButtonExampleApp extends BaseController implements IGUIEventListener {
    public static void main(String[] args) {
        new ButtonExampleApp();

    }
    private ButtonExampleApp(){
        ButtonExample_Sender beo = new ButtonExample_Sender();
        IOUtilities.openInJFrame(beo, 400, 400, 0,0,
                "TestFrame", Color.black, true
        );
        beo.addObserver(this);
        this.addObserver(beo);
    }

    @Override
    public void processGUIEvent(GUIEvent ge) {
        if (ge.getCmd()==ButtonExample_Sender.Commands.BUTTON_PRESSED){
          /*  System.out.println("Button 1 gedrückt");
            fireUpdateEvent(new UpdateEvent(this, ButtonExample_Sender.Commands.COLLORISE, ge.getData()));
        */

            JOptionPane.showConfirmDialog(null, new JButton("new Test"));
        }
        else if (ge.getCmd()==ButtonExample_Sender.Commands.BUTTON2_PRESSED){
            System.out.println("Button 2 gedrückt");
            fireUpdateEvent(new UpdateEvent(this, ButtonExample_Sender.Commands.COLOURISE, ge.getData()));
        }
    }
}
