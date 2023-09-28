package ui;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.IUpdateEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import model.Booking;
import util.UserInterfaceUtils;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.Date;

public class BookingDetailComponent extends ObservableComponent implements IUpdateEventListener {

    IDepictable booking;
    JLabel bookingArrivalDateLabel;
    JLabel bookingDepartureDateLabel;

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
        bookingArrivalDateLabel.setText(booking.getAttributeArray()[Booking.ARRIVAL_DATE].getValue().toString());
        bookingDepartureDateLabel.setText(booking.getAttributeArray()[Booking.DEPARTURE_DATE].getValue().toString());
    }
    public BookingDetailComponent(IDepictable iDepictable) {
        this.booking = iDepictable;
        this.setLayout(new BorderLayout());
        bookingArrivalDateLabel = new JLabel();
        bookingDepartureDateLabel = new JLabel();
        fillLabels();
        this.setBorder(new LineBorder(Color.green, 2));

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.weightx = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel topPanel = new JPanel(new GridBagLayout());

        this.add(topPanel, BorderLayout.NORTH);

        topPanel.add(UserInterfaceUtils.getHeaderLabel("Details"), gridBagConstraints);

        bookingArrivalDateLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        gridBagConstraints.gridy = 1;
        topPanel.add(bookingArrivalDateLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(bookingDepartureDateLabel, gridBagConstraints);
        gridBagConstraints.gridy += 1;
        topPanel.add(new JLabel("40% Auslastung"), gridBagConstraints);
    }


}
