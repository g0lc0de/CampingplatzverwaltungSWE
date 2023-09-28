package controller;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.util.BaseController;
import ui.TabComponent;

public class TabController extends BaseController implements IGUIEventListener {
    @Override
    public void processGUIEvent(GUIEvent guiEvent) {

    }

    private static TabController INSTANCE;

    public static TabController getInstance() {
        if(INSTANCE == null){
            INSTANCE = new TabController();
        }
        return INSTANCE;
    }

    private TabComponent tabComponent;

    public void init() throws Exception {
        OverviewTabController overviewTabController = new OverviewTabController();
        SubareasTabController subareasTabController = new SubareasTabController();
        BookingTabController bookingTabController = new BookingTabController();
        tabComponent = new TabComponent();
        tabComponent.setOverviewTabComponent(overviewTabController.getComponent());
        tabComponent.setSubareasTabComponent(subareasTabController.getComponent());
        tabComponent.setBookingTabComponent(bookingTabController.getComponent());
        tabComponent.init();
    }

    public void switchPage(int pageNumber){
        tabComponent.getTabbedPane().setSelectedIndex(pageNumber);
    }

    public void openSubareasPage(IDepictable campingArea, IDepictable subarea){
        //Use the subareas controller to regenerate the subareas tab based on the parameter camping area (Oberbereich)
        // and subarea (Stellbereich). These two should than be selected as default values on the subareas tab
        //This function is called by the subareas extended list from OberbereichDetailsComponent
    }

    private TabController() {

    }
}
