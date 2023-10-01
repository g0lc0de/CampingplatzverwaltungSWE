package ui;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.IUpdateEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.accounting.Booking;
import util.UserInterfaceUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class BookingDetailComponent extends ObservableComponent implements IUpdateEventListener {

    IDepictable booking;

    JLabel bookingHeaderLabel;
    JLabel bookingArrivalDateLabel;
    JLabel bookingDepartureDateLabel;
    JLabel adultsLabel;

    @Override
    public void processUpdateEvent(UpdateEvent updateEvent) {
        if(updateEvent.getCmd() == Commands.BOOKING_SELECTED){
            booking = (IDepictable) updateEvent.getData();
            fillLabels();
        }
    }

    public enum Commands implements EventCommand {

        BOOKING_SELECTED("BookingDetailComponent.bookingSelected", IDepictable.class);

        final String cmdText;
        final Class<?> payloadType;

        Commands(String cmdText, Class<?> payloadType) {
            this.cmdText = cmdText;
            this.payloadType = payloadType;
        }

        @Override
        public String getCmdText() {
            return cmdText;
        }

        @Override
        public Class<?> getPayloadType() {
            return payloadType;
        }
    }

    private void fillLabels(){
        bookingHeaderLabel.setText(String.format("Booking %s",booking.getAttributeArray()[Booking.ID].getValue()));
        bookingArrivalDateLabel.setText(String.format("Arrival: %s", booking.getAttributeArray()[Booking.ARRIVAL_DATE].getValue().toString()));
        bookingDepartureDateLabel.setText(String.format("Departure: %s", booking.getAttributeArray()[Booking.DEPARTURE_DATE].getValue().toString()));
        adultsLabel.setText(String.format("Adults: %s", booking.getAttributeArray()[Booking.NUMBER_ADULTS].getValue().toString()));
    }
    public BookingDetailComponent(IDepictable iDepictable) {
        this.booking = iDepictable;
        this.setLayout(new BorderLayout());
        bookingHeaderLabel = new JLabel();
        bookingArrivalDateLabel = new JLabel();
        bookingDepartureDateLabel = new JLabel();
        adultsLabel = new JLabel();
        fillLabels();
        this.setBorder(new LineBorder(Color.green, 2));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel topPanel = new JPanel(new GridBagLayout());

        this.add(topPanel, BorderLayout.NORTH);

        topPanel.add(UserInterfaceUtils.getHeaderLabel("Details"), gridBagConstraints);

        bookingHeaderLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gridBagConstraints.gridy = 1;
        topPanel.add(bookingHeaderLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(bookingArrivalDateLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(bookingDepartureDateLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(adultsLabel, gridBagConstraints);
    }


}
