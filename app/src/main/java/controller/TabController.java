package controller;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.event.IUpdateEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.util.BaseController;
import ui.SubareasTabComponent;
import ui.TabComponent;
import ui.UebersichtTabComponent;

public class TabController extends BaseController implements IGUIEventListener {
    @Override
    public void processGUIEvent(GUIEvent guiEvent) {

    }

    public TabController() throws Exception {
        UebersichtTabController overviewTabController = new UebersichtTabController();
        SubareasTabController subareasTabController = new SubareasTabController();
        TabComponent tabComponent = new TabComponent();
        tabComponent.setOverviewTabComponent(overviewTabController.getComponent());
        tabComponent.setSubareasTabComponent(subareasTabController.getComponent());
        tabComponent.init();

    }
}
