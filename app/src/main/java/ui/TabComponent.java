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
