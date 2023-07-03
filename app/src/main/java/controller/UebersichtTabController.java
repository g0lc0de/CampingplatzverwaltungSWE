package controller;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.util.BaseController;
import model.Oberbereich;
import model.Stellplatz;
import ui.*;
import util.StaticSourceNames;

import java.util.Arrays;
import java.util.List;

public class UebersichtTabController extends BaseController implements IGUIEventListener {

    private UebersichtTabComponent component;
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

    private ExtendedListComponent oberbereichListComponent;
    private ExtendedListComponent stellbereicheListComponent;
    private OberbereichDetailsComponent oberbereichDetailsComponent;
    public UebersichtTabController() throws Exception {

        component = new UebersichtTabComponent(oberbereichList);
        component.addObserver(this);
        addObserver(component);

        oberbereichListComponent = component.getOberbereichListComponent();
        oberbereichListComponent.addObserver(this);

        oberbereichDetailsComponent = component.getOberbereichDetailsComponent();
        oberbereichDetailsComponent.addObserver(this);
        addObserver(oberbereichDetailsComponent);

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
                Oberbereich selected = (Oberbereich) oberbereichList.get((int)guiEvent.getData());
                System.out.println("Test");
                fireUpdateEvent(new UpdateEvent(this, OberbereichDetailsComponent.Commands.OBERBEREICH_SELECTED, selected));
            }
        }
    }
}
