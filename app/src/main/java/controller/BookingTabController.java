package controller;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import de.dhbwka.swe.utils.util.BaseController;
import model.accounting.Booking;
import ui.BookingDetailComponent;
import ui.BookingTabComponent;
import ui.ExtendedListComponent;
import util.EntityManagerHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class BookingTabController extends BaseController {
    @Override
    public void processGUIEvent(GUIEvent ge) {
        if(ge.getCmd() == ExtendedListComponent.Commands.ITEM_SELECTED){
            fireUpdateEvent(new UpdateEvent(this, BookingDetailComponent.Commands.BOOKING_SELECTED, bookings.get((Integer) ge.getData())));
        }
    }

    private final BookingTabComponent component;

    public BookingTabComponent getComponent() {
        return component;
    }
    public main.java.ui.CreateBookingComponent createBookingComponent;

    private List<IDepictable> bookings = new ArrayList<>();

    public BookingTabController() throws Exception {
        Booking booking = new Booking();
        booking.setArrivalDate(new Date());
        booking.setDepartureDate(new Date());
        EntityManagerHolder.getInstance().getEntityManager().persist(booking);

        component = new BookingTabComponent();
        addObserver(component);
        component.addObserver(this);

        component.bookingDetailComponent.addObserver(this);
        addObserver(component.bookingDetailComponent);

        component.extendedBookingListComponent.addObserver(this);
        addObserver(component.extendedBookingListComponent);

    }
}
