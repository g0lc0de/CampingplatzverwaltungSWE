package ui;

import controller.TabController;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.properties.CampingArea;
import model.properties.CampingSpace;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class TabComponent extends ObservableComponent {
    private TabController controller;
    private List<CampingSpace> campingSpaceList = Arrays.asList(
            new CampingSpace("1", false),
            new CampingSpace("2", true),
            new CampingSpace("3a", false),
            new CampingSpace("4", false),
            new CampingSpace("5", true),
            new CampingSpace("6a", false),
            new CampingSpace("7", false),
            new CampingSpace("8", true),
            new CampingSpace("9a", false)
    );

    private List<IDepictable> iDepictableList = Arrays.asList(
            new CampingSpace("1", false),
            new CampingSpace("2", true),
            new CampingSpace("3a", false),
            new CampingSpace("4", false),
            new CampingSpace("5", true),
            new CampingSpace("6a", false),
            new CampingSpace("7", false),
            new CampingSpace("8", true),
            new CampingSpace("9a", false)
    );

    private List<CampingSpace> campingSpaceList2 = Arrays.asList(
            new CampingSpace("11", false),
            new CampingSpace("21", true),
            new CampingSpace("31", false),
            new CampingSpace("41", false),
            new CampingSpace("51", true)
    );

    private List<IDepictable> oberbereichList = Arrays.asList(
            new CampingArea("1", "Lorem ipsum", "O1", campingSpaceList),
            new CampingArea("2", "Lorem ipsum a", "O2", campingSpaceList2),
            new CampingArea("3", "Lorem ipsum aa", "O3", campingSpaceList),
            new CampingArea("4", "Lorem ipsum aaa", "O4", campingSpaceList2)
    );

    private OverviewTabComponent overviewTabComponent;
    private SubareasTabComponent subareasTabComponent;
    private BookingTabComponent bookingTabComponent;

    public BookingTabComponent getBookingTabComponent() {
        return bookingTabComponent;
    }

    public void setBookingTabComponent(BookingTabComponent bookingTabComponent) {
        this.bookingTabComponent = bookingTabComponent;
    }

    public OverviewTabComponent getOverviewTabComponent() {
        return overviewTabComponent;
    }

    public void setOverviewTabComponent(OverviewTabComponent overviewTabComponent) {
        this.overviewTabComponent = overviewTabComponent;
    }

    public SubareasTabComponent getSubareasTabComponent() {
        return subareasTabComponent;
    }

    public void setSubareasTabComponent(SubareasTabComponent subareasTabComponent) {
        this.subareasTabComponent = subareasTabComponent;
    }

    private JTabbedPane tabbedPane;

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public void init(){
        JFrame frame = new JFrame();

        tabbedPane = new JTabbedPane();
        tabbedPane.add("Übersicht", overviewTabComponent);
        tabbedPane.add("Stellbereiche", subareasTabComponent);
        tabbedPane.add("Buchungen", bookingTabComponent);
        frame.add(tabbedPane);

        frame.setSize(1080, 720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public TabComponent() throws Exception {

    }
}
