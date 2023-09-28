package ui;

import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.properties.SpaceSuitability;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import util.StaticSourceNames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Properties;

public class SubareasTabComponent extends ObservableComponent {
    public SubareasTabComponent(List<IDepictable> campingSpaces) throws Exception {
        this.subAreas = campingSpaces;
        this.setLayout(new GridLayout(1,3));
        JPanel leftSidePanel = new JPanel();
        JPanel rightSidePanel = new JPanel();
        leftSidePanel.setLayout(new BorderLayout());

        fillLeftPanel(leftSidePanel);
        fillRightPanel(rightSidePanel);

        this.add(leftSidePanel);
        this.add(rightSidePanel);
    }

    private List<IDepictable> subAreas;
    public ExtendedListComponent campingSpacesListComponent;
    public CampingSpaceDetailComponent campingSpaceDetailComponent;
    public CampingSpaceSelector campingSpaceSelector;

    private void fillRightPanel(JPanel rightSidePanel) throws Exception {
        JPanel topRightPanel = new JPanel();
        JPanel bottomRightPanel = new JPanel();
        campingSpacesListComponent = new ExtendedListComponent();
        campingSpacesListComponent.addIDepictables(subAreas);
        campingSpacesListComponent.addSourceName(StaticSourceNames.SUBAREAS_TAB_EXTENDED_LIST);
        JPanel listHolder = HeaderComponent.createHeaderComponent(campingSpacesListComponent.build(),"Stellplätze");
        topRightPanel.add(listHolder);
        campingSpaceDetailComponent = new CampingSpaceDetailComponent(subAreas.get(0));
        topRightPanel.add(campingSpaceDetailComponent);
        rightSidePanel.add(topRightPanel);
    }

    UtilDateModel modelTo;
    JDatePickerImpl datePickerTo;
    UtilDateModel modelFrom;
    JDatePickerImpl datePickerFrom;

    private void fillLeftPanel(JPanel leftSidePanel) {
        JPanel topLeftPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        modelTo = new UtilDateModel();
        modelFrom = new UtilDateModel();

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, p);
        datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateComponentFormatter());
        datePickerFrom.addActionListener(new DateActionListener());
        topLeftPanel.add(datePickerFrom, gridBagConstraints);

        gridBagConstraints.gridx++;

        JDatePanelImpl datePanelTo = new JDatePanelImpl(modelTo, p);
        datePickerTo = new JDatePickerImpl(datePanelTo, new DateComponentFormatter());
        datePickerTo.addActionListener(new DateActionListener());
        topLeftPanel.add(datePickerTo, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;
        gridBagConstraints.weightx = 1;

        JComboBox<String> campingAreaComboBox = new JComboBox<>();
        campingAreaComboBox.addItem("Nord");
        campingAreaComboBox.addItem("Süd");
        campingAreaComboBox.addItem("Ost");
        topLeftPanel.add(campingAreaComboBox, gridBagConstraints);

        gridBagConstraints.gridx++;

        JComboBox<String> subareaComboBox = new JComboBox<>();
        subareaComboBox.addItem("A");
        subareaComboBox.addItem("B");
        subareaComboBox.addItem("C");
        subareaComboBox.addItem("D");
        topLeftPanel.add(subareaComboBox, gridBagConstraints);

        gridBagConstraints.gridx++;
        gridBagConstraints.weightx = 10;

        JComboBox<String> spaceSuitabilityComboBox = new JComboBox<>();
        for (SpaceSuitability value : SpaceSuitability.values()) {
            spaceSuitabilityComboBox.addItem(value.getText());
        }
        topLeftPanel.add(spaceSuitabilityComboBox, gridBagConstraints);

        leftSidePanel.add(topLeftPanel, BorderLayout.NORTH);
        campingSpaceSelector = new CampingSpaceSelector(subAreas);
        leftSidePanel.add(campingSpaceSelector, BorderLayout.CENTER);
    }

    private class DateActionListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            if(modelTo.getValue() != null && modelFrom.getValue() != null) {
                if (modelTo.getValue().before(modelFrom.getValue())) {
                    System.out.println("Bad date");
                    datePickerTo.getJFormattedTextField().setBackground(Color.RED);
                    datePickerFrom.getJFormattedTextField().setBackground(Color.RED);
                } else {
                    datePickerTo.getJFormattedTextField().setBackground(null);
                    datePickerFrom.getJFormattedTextField().setBackground(null);
                }
            }
        }
    }
}
