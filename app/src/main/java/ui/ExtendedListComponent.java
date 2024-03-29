package ui;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IUpdateEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.properties.CampingSpace;

import javax.swing.*;
import javax.swing.border.LineBorder;
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
    private List<IDepictable> iDepictables = new ArrayList<>();
    private List<Runnable> mouseEventFunctions = new ArrayList<>();

    GridBagConstraints gbc = new GridBagConstraints();

    public String getSourceName() {
        return sourceName;
    }

    public ExtendedListComponent(String sourceName, List<IDepictable> iDepictables) {
        this.sourceName = sourceName;
        this.iDepictables = iDepictables;
    }

    public ExtendedListComponent(){
        gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
    }

    public void selectItem(int position) {
        selected = position;
        highlightPanel(position);
    }

    private void highlightPanel(int position){
        highlightPanel(subareaPanels.get(position));
    }

    private void highlightPanel(JPanel panel){
        subareaPanels.forEach(jPanel -> jPanel.setBackground(null));
        panel.setBackground(Color.red);
    }

    public ExtendedListComponent addSourceName(String sourceName){
        this.sourceName = sourceName;
        return this;
    }

    public ExtendedListComponent addIDepictables(List<IDepictable> iDepictables){
        this.iDepictables = iDepictables;
        return this;
    }

    private List<JPanel> subareaPanels = new ArrayList<>();

    public ExtendedListComponent removePanels(){
        subareaPanels = new ArrayList<>();
        removeAll();
        return this;
    }

    public void addMoveEventFunction(Runnable runnable){
        mouseEventFunctions.add(runnable);
    }

    public ExtendedListComponent build() throws Exception {
        this.setLayout(new GridBagLayout());
//        System.out.println("List received:" + iDepictables.toString());

        if(sourceName == null){
            throw new Exception();
        }

        this.setBorder(new LineBorder(Color.black, 2));
        rerenderIDepictables(this.iDepictables);

        return this;
    }

    public void rerenderIDepictables(List<IDepictable> iDepictableList) {
        int i = 0;

        for (IDepictable id : iDepictableList) {
            addNewIDepictable(id, i);
            i++;
        }
    }

    public void addNewIDepictable(IDepictable iDepictable, int index) {
        JPanel subareaPanel = new JPanel(new BorderLayout());
        JLabel nameLabel = new JLabel(String.format("%s", iDepictable.getAttributeArray()[CampingSpace.ID].getValue()));
        nameLabel.setMinimumSize(new Dimension(100,30));
        subareaPanel.add(nameLabel, BorderLayout.NORTH);
        subareaPanel.add(new JLabel("Auslastung " + Math.round(Math.random() * 100) + "%"), BorderLayout.SOUTH);
        subareaPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("clicked" + index);
                fireGUIEvent(new GUIEvent(sourceName, ExtendedListComponent.Commands.ITEM_SELECTED, index));
                highlightPanel(subareaPanel);
                mouseEventFunctions.forEach(Runnable::run);
            }
        });
        subareaPanels.add(subareaPanel);
        this.add(subareaPanel, gbc);
    }
}
