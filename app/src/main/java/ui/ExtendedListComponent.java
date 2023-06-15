package ui;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IUpdateEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.Stellplatz;
import util.UserInterfaceUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.util.List;

public class ExtendedListComponent extends ObservableComponent implements IUpdateEventListener {
    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {
        System.out.println("received updates");
    }

    public enum Commands implements EventCommand {

        ITEM_SELECTED("ExtendedListComponent.itemSelected", String.class);

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

    public String getSourceName() {
        return sourceName;
    }

    public ExtendedListComponent(String sourceName) {
        this.sourceName = sourceName;
    }

    public JScrollPane createListComponent(List<JPanel> panels) {
        JPanel mainList = new JPanel(new GridBagLayout());

        mainList.setBorder(new LineBorder(Color.black, 2));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.weightx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        for (int i = 0; i < panels.size(); i++) {
            JPanel panel = panels.get(i);
            panel.setBorder(new MatteBorder(0, 0, 1, 0, Color.GRAY));
            int finalI = i;
            panel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("clicked" + finalI);
                    fireGUIEvent(new GUIEvent(sourceName, ExtendedListComponent.Commands.ITEM_SELECTED, finalI));
                }
            });
            mainList.add(panel, gbc);
        }

        JPanel wrapperPanel = new JPanel();
        wrapperPanel.setLayout(new BoxLayout(wrapperPanel, BoxLayout.Y_AXIS));
        wrapperPanel.add(mainList);

        return new JScrollPane(wrapperPanel);
    }


}
