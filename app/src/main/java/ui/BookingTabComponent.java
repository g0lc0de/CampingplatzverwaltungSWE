package ui;

import de.dhbwka.swe.utils.event.EventCommand;
import de.dhbwka.swe.utils.event.IUpdateEventListener;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.accounting.Booking;
import model.properties.SpaceSuitability;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import util.EntityManagerHolder;
import util.StaticSourceNames;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BookingTabComponent extends ObservableComponent implements IUpdateEventListener {

    private UtilDateModel modelTo, modelFrom;
    private JDatePickerImpl datePickerFrom, datePickerTo;
    private List<IDepictable> bookings = new ArrayList<>();
    public ExtendedListComponent extendedBookingListComponent;
    public BookingDetailComponent bookingDetailComponent;


    public enum Commands implements EventCommand {

        BOOKING_ADDED("BookingTabComponent.bookingAdded", Booking.class);

        String cmdText;
        Class<?> payloadType;

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
    public BookingTabComponent() throws Exception {

        List<IPersistable> persistables = EntityManagerHolder.getInstance().getEntityManager().findAll(Booking.class);
        for (IPersistable persitable :
                persistables) {
            bookings.add((IDepictable) persitable);
        }

        setLayout(new GridBagLayout());
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.weighty = 1;
        gridBagConstraints.anchor = GridBagConstraints.NORTHWEST;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;

        JPanel filterPanel = createFilterPanel();
        this.add(filterPanel, gridBagConstraints);

        JPanel contentPanel = new JPanel(new GridLayout(1,2));
        extendedBookingListComponent = new ExtendedListComponent();
        extendedBookingListComponent.addSourceName(StaticSourceNames.BOOKINGS_TAB_EXTENDED_LIST);
        extendedBookingListComponent.addIDepictables(bookings);

        contentPanel.add(extendedBookingListComponent.build());
        bookingDetailComponent = new BookingDetailComponent(bookings.get(0));
        contentPanel.add(bookingDetailComponent);

        gridBagConstraints.gridy = 1;
        gridBagConstraints.weighty = 2;
        this.add(contentPanel, gridBagConstraints);
    }

    private JPanel createFilterPanel() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JPanel filterPanel = new JPanel(new GridBagLayout());

        JPanel datePickerFromLabelPanel = createDatePanel(modelFrom, p, datePickerFrom, "From");
        JPanel datePickerToLabelPanel = createDatePanel(modelTo, p, datePickerTo, "To");

        filterPanel.add(datePickerFromLabelPanel, gridBagConstraints);
        gridBagConstraints.gridx++;
        filterPanel.add(datePickerToLabelPanel, gridBagConstraints);

        JPanel lastnamePanel = new JPanel(new GridLayout(2,1));
        lastnamePanel.add(new JLabel("Last Name"));
        lastnamePanel.add(new JTextField(15));

        gridBagConstraints.gridx++;
        filterPanel.add(lastnamePanel, gridBagConstraints);

        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy++;

        JPanel campingAreaPanel = new JPanel(new GridLayout(2,1));
        JComboBox<String> campingAreaComboBox = new JComboBox<>();
        campingAreaComboBox.addItem("Nord");
        campingAreaComboBox.addItem("Süd");
        campingAreaComboBox.addItem("Ost");
        campingAreaPanel.add(new JLabel("Camping Area"));
        campingAreaPanel.add(campingAreaComboBox);
        filterPanel.add(campingAreaPanel, gridBagConstraints);

        gridBagConstraints.gridx++;

        JPanel subareaPanel = new JPanel(new GridLayout(2,1));
        JComboBox<String> subareaComboBox = new JComboBox<>();
        subareaComboBox.addItem("A");
        subareaComboBox.addItem("B");
        subareaComboBox.addItem("C");
        subareaComboBox.addItem("D");
        subareaPanel.add(new JLabel("Sub Area"));
        subareaPanel.add(subareaComboBox);
        filterPanel.add(subareaPanel, gridBagConstraints);

        gridBagConstraints.gridx++;

        JPanel spaceSuitabilityPanel = new JPanel(new GridLayout(2,1));
        JComboBox<String> spaceSuitabilityComboBox = new JComboBox<>();
        for (SpaceSuitability value : SpaceSuitability.values()) {
            spaceSuitabilityComboBox.addItem(value.getText());
        }
        spaceSuitabilityPanel.add(new JLabel("Space Suitability"));
        spaceSuitabilityPanel.add(spaceSuitabilityComboBox);
        filterPanel.add(spaceSuitabilityPanel, gridBagConstraints);
        return filterPanel;
    }

    private JPanel createDatePanel(UtilDateModel modelFrom, Properties p, JDatePickerImpl datePickerFrom, String From) {
        JPanel datePickerFromLabelPanel = new JPanel(new GridLayout(2, 1));

        modelFrom = new UtilDateModel();
        JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, p);
        datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateComponentFormatter());
        datePickerFromLabelPanel.add(new JLabel(From));
        datePickerFromLabelPanel.add(datePickerFrom);
        return datePickerFromLabelPanel;
    }

    @Override
    public void processUpdateEvent(UpdateEvent ue) {

    }
}
