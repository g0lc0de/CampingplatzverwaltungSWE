package controller;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IGUIEventListener;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import de.dhbwka.swe.utils.util.BaseController;
import ui.BookingTabComponent;
import ui.TabComponent;
import util.EntityManagerHolder;

public class TabController extends BaseController implements IGUIEventListener {

    private OverviewTabController overviewTabController;
    private SubareasTabController subareasTabController;
    private BookingTabController bookingTabController;


    @Override
    public void processGUIEvent(GUIEvent guiEvent) {

        if (guiEvent.getCmd() == BookingTabComponent.Commands.BOOKING_ADDED) {
            System.out.println("Tab Controller GUI Event");
            try {
                EntityManagerHolder.getInstance().getEntityManager().persist((IPersistable) guiEvent.getData());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            bookingTabController.refreshBooking();
        }

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
        MockDataCreator.createMockData();

        overviewTabController = new OverviewTabController(this);
        subareasTabController = new SubareasTabController();
        bookingTabController = new BookingTabController();

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
