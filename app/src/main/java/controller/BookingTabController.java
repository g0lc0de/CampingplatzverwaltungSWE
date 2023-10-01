package controller;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.IUpdateEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import de.dhbwka.swe.utils.util.BaseController;
import model.accounting.Booking;
import ui.BookingDetailComponent;
import ui.BookingTabComponent;
import ui.CreateBookingComponent;
import ui.ExtendedListComponent;
import util.EntityManagerHolder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookingTabController extends BaseController implements IUpdateEventListener {
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
    public CreateBookingComponent createBookingComponent;

    private List<IDepictable> bookings = new ArrayList<>();

    public BookingTabController() throws Exception {
        this.bookings = getBookings();

        component = new BookingTabComponent(bookings);
        addObserver(component);
        component.addObserver(this);

        component.bookingDetailComponent.addObserver(this);
        addObserver(component.bookingDetailComponent);

        component.extendedBookingListComponent.addObserver(this);
        addObserver(component.extendedBookingListComponent);

    }

    public void refreshBooking() {
        this.bookings = getBookings();
        component.setBookings(this.bookings);
    }

    private List<IDepictable> getBookings() {
        return EntityManagerHolder.getInstance().getEntityManager().findAll(Booking.class)
                .stream()
                .map(b -> (IDepictable) b)
                .collect(Collectors.toList());

    }

    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {
        System.out.println("Update Event received BookingTabController");
        System.out.println(updateEvent);
    }
}
