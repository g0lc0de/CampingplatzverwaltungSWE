package controller;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.util.BaseController;
import model.accounting.Booking;
import model.properties.CampingArea;
import model.properties.CampingSpace;
import model.properties.Region;
import ui.*;
import util.EntityManagerHolder;
import util.StaticSourceNames;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OverviewTabController extends BaseController implements IGUIEventListener {

    private OverviewTabComponent component;
    private TabController tabController;

    private List<IDepictable> regions;
    private List<IDepictable> campingAreas;
    private ExtendedListComponent oberbereichListComponent;
    private ExtendedListComponent stellbereicheListComponent;
    private CampingAreaDetailComponent campingAreaDetailComponent;

    public OverviewTabComponent getComponent() {
        return component;
    }

    public OverviewTabController(TabController tabController) throws Exception {
        this.tabController = tabController;

        this.regions = getRegions();
        this.campingAreas = getCampingAreas();

        component = new OverviewTabComponent(this.regions);
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

    private List<IDepictable> getRegions() {
        return EntityManagerHolder.getInstance().getEntityManager().findAll(Region.class)
                .stream()
                .map(b -> (IDepictable) b)
                .collect(Collectors.toList());
    }

    private List<IDepictable> getCampingAreas() {
        return EntityManagerHolder.getInstance().getEntityManager().findAll(CampingArea.class)
                .stream()
                .map(b -> (IDepictable) b)
                .collect(Collectors.toList());
    }

    @Override
    public void processGUIEvent(GUIEvent guiEvent) {
        System.out.println("Received from child:" + guiEvent.getCmd() + " " + guiEvent.getSource());
        System.err.println("GUIEventData:" + guiEvent.getData());

        if (guiEvent.getCmd() == ExtendedListComponent.Commands.ITEM_SELECTED){
            System.out.println( oberbereichListComponent);
            if(guiEvent.getSource() == StaticSourceNames.UEBERSICHT_TAB_OBERBEREICHE_LIST){
                Region selected = (Region) this.regions.get((int)guiEvent.getData());
                fireUpdateEvent(new UpdateEvent(this, CampingAreaDetailComponent.Commands.OBERBEREICH_SELECTED, selected));
            }
        } else if (guiEvent.getCmd() == OverviewTabComponent.Commands.BUCHUNG_ANLEGEN) {
            CreateBookingComponent createBookingForm = new CreateBookingComponent();
            createBookingForm.addObserver(tabController);
        }
    }
}
