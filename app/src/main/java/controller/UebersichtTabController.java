package controller;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.util.BaseController;
import model.Stellplatz;
import ui.StellplatzSelector;
import ui.UebersichtTabComponent;

import java.util.Arrays;
import java.util.List;

public class UebersichtTabController extends BaseController  implements IGUIEventListener {

    private UebersichtTabComponent component;
    private List<IDepictable> stellplatzList = Arrays.asList(
            new Stellplatz("1", false),
            new Stellplatz("2", true),
            new Stellplatz("3", false),
            new Stellplatz("4", false),
            new Stellplatz("5", true),
            new Stellplatz("6", false),
            new Stellplatz("7", false),
            new Stellplatz("8", true),
            new Stellplatz("9", false)
    );

    public UebersichtTabController() throws Exception {

        component = new UebersichtTabComponent(stellplatzList);
        component.addObserver(this);
        addObserver(component);

        component.getStellplatzSelector().addObserver(this);
        addObserver(component.getStellplatzSelector());
    }



    @Override
    public void processGUIEvent(GUIEvent guiEvent) {
        System.out.println("Received from child:" + guiEvent.getCmd() + " " + guiEvent.getSource());
        System.err.println("GUIEventData:" + guiEvent.getData());

        if(guiEvent.getCmd() == StellplatzSelector.Commands.PLACE_SELECTED){
            Stellplatz stellplatz = (Stellplatz) stellplatzList.get(Integer.parseInt(guiEvent.getSource().toString())-1);

            try {
                stellplatz.setReserviert(!stellplatz.isReserviert());
                fireUpdateEvent(new UpdateEvent(this, StellplatzSelector.Commands.UPDATE_PLACES));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        fireUpdateEvent(new UpdateEvent(this,UebersichtTabComponent.Commands.TEXT_CHANGED, guiEvent.getData()));
    }
}
