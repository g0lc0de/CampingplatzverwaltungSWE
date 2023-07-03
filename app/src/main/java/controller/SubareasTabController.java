package controller;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.util.BaseController;
import model.Stellplatz;
import ui.CampingSpaceDetailComponent;
import ui.ExtendedListComponent;
import ui.StellplatzSelector;
import ui.SubareasTabComponent;

import java.util.Arrays;
import java.util.List;

public class SubareasTabController  extends BaseController implements IGUIEventListener {
    @Override
    public void processGUIEvent(GUIEvent guiEvent) {
        if(guiEvent.getCmd() == StellplatzSelector.Commands.PLACE_SELECTED){
            fireUpdateEvent(new UpdateEvent(this, ExtendedListComponent.Commands.ITEM_SELECTED_BY_CONTROLLER, guiEvent.getData()));
        }
    }

    private List<IDepictable> stellplatzList = Arrays.asList(
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

    ExtendedListComponent campingSpacesList;
    StellplatzSelector campingSpacesSelector;
    CampingSpaceDetailComponent detailComponent;

    public SubareasTabController() throws Exception {
        SubareasTabComponent tabComponent = new SubareasTabComponent(stellplatzList);
        tabComponent.buildComponent();

        campingSpacesList = tabComponent.campingSpacesListComponent;
        campingSpacesList.addObserver(this);
        this.addObserver(campingSpacesList);

        detailComponent = tabComponent.campingSpaceDetailComponent;

        campingSpacesSelector = tabComponent.campingSpaceSelector;
        campingSpacesSelector.addObserver(this);
        this.addObserver(campingSpacesSelector);

    }
}
