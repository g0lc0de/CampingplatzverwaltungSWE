package controller;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.util.BaseController;
import model.properties.CampingSpace;
import ui.CampingSpaceDetailComponent;
import ui.ExtendedListComponent;
import ui.CampingSpaceSelector;
import ui.SubareasTabComponent;

import java.util.Arrays;
import java.util.List;

public class SubareasTabController  extends BaseController implements IGUIEventListener {
    @Override
    public void processGUIEvent(GUIEvent guiEvent) {
        if(guiEvent.getCmd() == CampingSpaceSelector.Commands.PLACE_SELECTED){
            System.out.println("Received guiEvent: "+ guiEvent);
            fireUpdateEvent(new UpdateEvent(this, CampingSpaceDetailComponent.Commands.CAMPING_SPACE_SELECTED, stellplatzList.get((Integer) guiEvent.getData())));
            fireUpdateEvent(new UpdateEvent(this, ExtendedListComponent.Commands.ITEM_SELECTED_BY_CONTROLLER, guiEvent.getData()));
        } else if(guiEvent.getCmd() == ExtendedListComponent.Commands.ITEM_SELECTED){
            fireUpdateEvent(new UpdateEvent(this, CampingSpaceDetailComponent.Commands.CAMPING_SPACE_SELECTED, stellplatzList.get((Integer) guiEvent.getData())));
            fireUpdateEvent(new UpdateEvent(this, CampingSpaceSelector.Commands.PLACE_SELECTED_BY_CONTROLLER, guiEvent.getData()));
        }
    }

    private List<IDepictable> stellplatzList = Arrays.asList(
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

    private ExtendedListComponent campingSpacesList;
    private CampingSpaceSelector campingSpacesSelector;
    private CampingSpaceDetailComponent detailComponent;
    private SubareasTabComponent component;

    public SubareasTabComponent getComponent() {
        return component;
    }

    public SubareasTabController() throws Exception {

        component = new SubareasTabComponent(stellplatzList);

        campingSpacesList = component.campingSpacesListComponent;
        campingSpacesList.addObserver(this);
        this.addObserver(campingSpacesList);

        detailComponent = component.campingSpaceDetailComponent;
        this.addObserver(detailComponent);
        detailComponent.addObserver(this);

        campingSpacesSelector = component.campingSpaceSelector;
        campingSpacesSelector.addObserver(this);
        this.addObserver(campingSpacesSelector);

    }
}
