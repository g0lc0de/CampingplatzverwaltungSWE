package ui;

import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import util.StaticSourceNames;

import javax.swing.*;
import java.awt.*;
import java.util.List;

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
        topRightPanel.add(campingSpaceDetailComponent.createDetailComponent());
        rightSidePanel.add(topRightPanel);
    }

    private void fillLeftPanel(JPanel leftSidePanel) {
        JPanel topLeftPanel = new JPanel();
        JComboBox<String> dateFromComboBox = new JComboBox<>();
        dateFromComboBox.addItem("Test1");
        dateFromComboBox.addItem("Test2");
        topLeftPanel.add(dateFromComboBox);
        leftSidePanel.add(topLeftPanel, BorderLayout.NORTH);
        campingSpaceSelector = new CampingSpaceSelector(subAreas);
        leftSidePanel.add(campingSpaceSelector, BorderLayout.CENTER);
    }
}
