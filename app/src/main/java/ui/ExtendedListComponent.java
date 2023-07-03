package ui;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IUpdateEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.Stellplatz;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ExtendedListComponent extends ObservableComponent implements IUpdateEventListener {
    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {
        if(updateEvent.getCmd() == Commands.ITEM_SELECTED_BY_CONTROLLER){
            System.out.println("selected item by controller");
            try {
                selectItem((Integer) updateEvent.getData());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public enum Commands implements EventCommand {

        ITEM_SELECTED("ExtendedListComponent.itemSelected", String.class),
        ITEM_SELECTED_BY_CONTROLLER("ExtendedListComponent.itemSelectedByController", String.class);

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

    private String sourceName;
    private int selected = -1;
    private List<IDepictable> iDepictables;

    public String getSourceName() {
        return sourceName;
    }

    public ExtendedListComponent(String sourceName, List<IDepictable> iDepictables) {
        this.sourceName = sourceName;
        this.iDepictables = iDepictables;
    }

    public ExtendedListComponent(){

    }

    public void selectItem(int position) {
        selected = position;
    }

    public ExtendedListComponent addSourceName(String sourceName){
        this.sourceName = sourceName;
        return this;
    }

    public ExtendedListComponent addIDepictables(List<IDepictable> iDepictables){
        this.iDepictables = iDepictables;
        return this;
    }

    public ExtendedListComponent build() throws Exception {
        this.setLayout(new GridBagLayout());
        System.out.println("List received:" + iDepictables.toString());

        if(iDepictables == null || sourceName == null){
            throw new Exception();
        }

        this.setBorder(new LineBorder(Color.black, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int i = 0;
        for (IDepictable stellbereich : iDepictables) {
            JPanel stellbereichPanel = new JPanel(new BorderLayout());
            JLabel nameLabel = new JLabel("Stellbereich " + stellbereich.getAttributeArray()[Stellplatz.ID].getValue());
            stellbereichPanel.add(nameLabel, BorderLayout.NORTH);
            stellbereichPanel.add(new JLabel("Auslastung " + Math.round(Math.random() * 100) + "%"), BorderLayout.SOUTH);
            int finalI = i;
            stellbereichPanel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("clicked" + finalI);
                    fireGUIEvent(new GUIEvent(sourceName, ExtendedListComponent.Commands.ITEM_SELECTED, finalI));
                }
            });
            if(i == selected){
                System.out.println(i);
                stellbereichPanel.setBackground(Color.BLUE);
            }
            this.add(stellbereichPanel, gbc);
            i++;
        }
        return this;
    }
}
