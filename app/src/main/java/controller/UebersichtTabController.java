package controller;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.util.BaseController;
import model.bestand.Platzeignung;
import model.bestand.Stellplatz;
import model.bestand.VerfuegbareRessource;
import ui.StellplatzSelector;
import ui.UebersichtTabComponent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UebersichtTabController extends BaseController  implements IGUIEventListener {

    private UebersichtTabComponent component;

    private List<IDepictable> stellplatzList;

    public UebersichtTabController() throws Exception {

        List<Platzeignung> platzeignungen = new ArrayList<>();
        platzeignungen.add(Platzeignung.AUTO);
        platzeignungen.add(Platzeignung.WOHNWAGEN);

        List<VerfuegbareRessource> verfuegbareRessources = new ArrayList<>();
        verfuegbareRessources.add(VerfuegbareRessource.WASSER);

        stellplatzList = Arrays.asList(
                new Stellplatz("A1", 10, platzeignungen, verfuegbareRessources, true),
                new Stellplatz("A2", 15, platzeignungen, verfuegbareRessources, false),
                new Stellplatz("A3", 10, platzeignungen, verfuegbareRessources, true),
                new Stellplatz("A4", 15, platzeignungen, verfuegbareRessources, false)
        );

        Stellplatz a = (Stellplatz) stellplatzList.get(0);
        System.out.println(a);

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
