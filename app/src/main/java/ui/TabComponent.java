package ui;

import controller.TabController;
import controller.UebersichtTabController;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.Oberbereich;
import model.Stellplatz;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;

public class TabComponent extends ObservableComponent {
    private TabController controller;
    private List<Stellplatz> stellplatzList = Arrays.asList(
            new Stellplatz("1", false),
            new Stellplatz("2", true),
            new Stellplatz("3a", false),
            new Stellplatz("4", false),
            new Stellplatz("5", true),
            new Stellplatz("6a", false),
            new Stellplatz("7", false),
            new Stellplatz("8", true),
            new Stellplatz("9a", false)
    );

    private List<IDepictable> iDepictableList = Arrays.asList(
            new Stellplatz("1", false),
            new Stellplatz("2", true),
            new Stellplatz("3a", false),
            new Stellplatz("4", false),
            new Stellplatz("5", true),
            new Stellplatz("6a", false),
            new Stellplatz("7", false),
            new Stellplatz("8", true),
            new Stellplatz("9a", false)
    );

    private List<Stellplatz> stellplatzList2 = Arrays.asList(
            new Stellplatz("11", false),
            new Stellplatz("21", true),
            new Stellplatz("31", false),
            new Stellplatz("41", false),
            new Stellplatz("51", true)
    );

    private List<IDepictable> oberbereichList = Arrays.asList(
            new Oberbereich("1", "Lorem ipsum", "O1", stellplatzList),
            new Oberbereich("2", "Lorem ipsum a", "O2", stellplatzList2),
            new Oberbereich("3", "Lorem ipsum aa", "O3", stellplatzList),
            new Oberbereich("4", "Lorem ipsum aaa", "O4", stellplatzList2)
    );

    private UebersichtTabComponent overviewTabComponent;
    private SubareasTabComponent subareasTabComponent;

    public UebersichtTabComponent getOverviewTabComponent() {
        return overviewTabComponent;
    }

    public void setOverviewTabComponent(UebersichtTabComponent overviewTabComponent) {
        this.overviewTabComponent = overviewTabComponent;
    }

    public SubareasTabComponent getSubareasTabComponent() {
        return subareasTabComponent;
    }

    public void setSubareasTabComponent(SubareasTabComponent subareasTabComponent) {
        this.subareasTabComponent = subareasTabComponent;
    }

    public void init(){
        JFrame frame = new JFrame();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.add("Übersicht", overviewTabComponent);
        tabbedPane.add("Stellbereiche", subareasTabComponent);
        frame.add(tabbedPane);

        frame.setSize(1080, 720);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public TabComponent() throws Exception {

    }
}
