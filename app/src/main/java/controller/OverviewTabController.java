package controller;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.util.BaseController;
import model.CampingArea;
import model.CampingSpace;
import ui.*;
import util.StaticSourceNames;

import java.util.Arrays;
import java.util.List;

public class OverviewTabController extends BaseController implements IGUIEventListener {

    private OverviewTabComponent component;
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

    private ExtendedListComponent oberbereichListComponent;
    private ExtendedListComponent stellbereicheListComponent;
    private CampingAreaDetailComponent campingAreaDetailComponent;

    public OverviewTabComponent getComponent() {
        return component;
    }

    public OverviewTabController() throws Exception {

        component = new OverviewTabComponent(oberbereichList);
        component.addObserver(this);
        addObserver(component);

        oberbereichListComponent = component.getOberbereichListComponent();
        oberbereichListComponent.addObserver(this);

        campingAreaDetailComponent = component.getOberbereichDetailsComponent();
        campingAreaDetailComponent.addObserver(this);
        addObserver(campingAreaDetailComponent);

        stellbereicheListComponent = component.getOberbereichDetailsComponent().getStellbereicheListComponent();
        stellbereicheListComponent.addObserver(this);
        addObserver(stellbereicheListComponent);
    }


    @Override
    public void processGUIEvent(GUIEvent guiEvent) {
        System.out.println("Received from child:" + guiEvent.getCmd() + " " + guiEvent.getSource());
        System.err.println("GUIEventData:" + guiEvent.getData());

        if (guiEvent.getCmd() == ExtendedListComponent.Commands.ITEM_SELECTED){
            System.out.println( oberbereichListComponent);
            if(guiEvent.getSource() == StaticSourceNames.UEBERSICHT_TAB_OBERBEREICHE_LIST){
                CampingArea selected = (CampingArea) oberbereichList.get((int)guiEvent.getData());
                System.out.println("Test");
                fireUpdateEvent(new UpdateEvent(this, CampingAreaDetailComponent.Commands.OBERBEREICH_SELECTED, selected));
            }
        }
    }
}
