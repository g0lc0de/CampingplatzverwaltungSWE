package ui;

import de.dhbwka.swe.utils.event.GUIEvent;
import de.dhbwka.swe.utils.event.UpdateEvent;
import de.dhbwka.swe.utils.gui.ObservableComponent;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.accounting.Booking;
import model.accounting.Document;
import model.accounting.Invoice;
import model.equipment.ChipCard;
import model.equipment.RentableEquipment;
import model.hr.Address;
import model.hr.Country;
import model.hr.Person;
import model.properties.CampingSpace;
import model.properties.SpaceSuitability;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import ui.BookingTabComponent;
import ui.SubareasTabComponent;
import util.EntityManagerHolder;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class CreateBookingComponent extends ObservableComponent {

    List<Person> personList = new ArrayList<>();
    List<ChipCard> chipCardList = new ArrayList<>();
    List<Invoice> invoiceList = new ArrayList<>();
    List<Document> documentList = new ArrayList<>();

    private void createFakeData(){

        personList.add(new Person("Mustermann", "Max"));
        personList.add(new Person("Schneider", "Romy"));
        personList.add(new Person("Schneider", "Magdalena"));

        chipCardList.add(new ChipCard("Peter"));
        chipCardList.add(new ChipCard("Hans"));

        invoiceList.add(new Invoice("I1", "Invoice1", new Address("1", Country.DE, "Test Street", "1", "", "12345", "Teststadt")));
        invoiceList.add(new Invoice("I1", "Invoice1", new Address("1", Country.EN, "Street Test", "1", "", "54321", "Hausenhausen")));

        documentList.add(new Document("Document_1", "doc1"));
        documentList.add(new Document("Document_2", "doc2"));
    }

    private Date arrivalDate, departureDate;
    private Person personResponsible;

    private int numberAdult;
    private int numberChildren;
    private String licensePlate;
    private boolean isReservation;
    private double price;

    private Invoice bookingInvoice;
    private Document bookingConfirmation;
    private Person guest;

    private ChipCard chipCard;
    private List<SpaceSuitability> broughtGear;

    private CampingSpace assignedCampingSpace;
    private List<RentableEquipment> rentedEquipment;

    public CreateBookingComponent(){
        createFakeData();

        JFrame mainFrame = new JFrame();
        //add(mainFrame);
        mainFrame.setLayout(new GridLayout(15,2));

        UtilDateModel modelFrom = new UtilDateModel();
        UtilDateModel modelTo = new UtilDateModel();

        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl datePanelFrom = new JDatePanelImpl(modelFrom, p);
        JDatePickerImpl datePickerFrom = new JDatePickerImpl(datePanelFrom, new DateComponentFormatter());
        datePickerFrom.addActionListener(e -> {

        });
        mainFrame.add(new JLabel("Date From"));
        mainFrame.add(datePickerFrom);

        JDatePanelImpl datePanelTo = new JDatePanelImpl(modelTo, p);
        JDatePickerImpl datePickerTo = new JDatePickerImpl(datePanelTo, new DateComponentFormatter());
        datePickerTo.addActionListener(e -> {

        });
        mainFrame.add(new JLabel("Date To"));
        mainFrame.add(datePickerTo);

        JLabel numberOfAdultsLabel = new JLabel("Number of adults");
        JTextField numberOfAdultsField = new JTextField();
        numberOfAdultsField.addActionListener(e -> {

        });
        mainFrame.add(numberOfAdultsLabel);
        mainFrame.add(numberOfAdultsField);

        JLabel numberOfChildrenLabel = new JLabel("Number of children");
        JTextField numberOfChildrenField = new JTextField();
        mainFrame.add(numberOfChildrenLabel);
        mainFrame.add(numberOfChildrenField);
        numberOfChildrenField.addActionListener(e -> {

        });

//        private Person personResponsible;
        JLabel personResponsibleLabel = new JLabel("Person responsible");
        JComboBox<String> personComboBox = new JComboBox<>();
        for (Person person :
                personList) {
            personComboBox.addItem(
                    String.format("%s %s", person.getAttributeArray()[Person.FIRST_NAME].getValue(), person.getAttributeArray()[Person.LAST_NAME].getValue()));
        }
        mainFrame.add(personResponsibleLabel);
        mainFrame.add(personComboBox);

        JLabel licencePlateLabel = new JLabel("Licence Plate");
        JTextField licencePlateField = new JTextField();
        mainFrame.add(licencePlateLabel);
        mainFrame.add(licencePlateField);

        JLabel isReservationLabel = new JLabel("Is Reservation?");
        JCheckBox isReservationCheck = new JCheckBox();
        mainFrame.add(isReservationLabel);
        mainFrame.add(isReservationCheck);

        JLabel priceLabel = new JLabel("Price");
        JTextField priceField = new JTextField();
        mainFrame.add(priceLabel);
        mainFrame.add(priceField);

        JLabel chipCardLabel = new JLabel("Chip card");
        JComboBox<String> chipCardComboBox = new JComboBox<>();
        for (int i = 0; i < chipCardList.size(); i++) {
            chipCardComboBox.addItem(String.format("%s",chipCardList.get(i).getAttributeArray()[ChipCard.NAME].getValue()));
        }
        mainFrame.add(chipCardLabel);
        mainFrame.add(chipCardComboBox);

        JLabel spaceLabel = new JLabel("Required space");
        JComboBox<String> spaceComboBox = new JComboBox<>();
        for (SpaceSuitability value :
                SpaceSuitability.values()) {
            spaceComboBox.addItem(value.getText());
        }

        mainFrame.add(spaceLabel);
        mainFrame.add(spaceComboBox);

        List<IPersistable> campingSpacesList = EntityManagerHolder.getInstance().getEntityManager().findAll(CampingSpace.class);

        JComboBox<String> campingSpaceComboBox = new JComboBox<>();
        for (IPersistable campingSpacePersistable: campingSpacesList) {
            IDepictable campingSpaceDepictable = (IDepictable) campingSpacePersistable;
            campingSpaceComboBox.addItem(campingSpaceDepictable.getVisibleText());
        }

        mainFrame.add(new JLabel("Camping Space"));
        mainFrame.add(campingSpaceComboBox);

        JButton createButton = new JButton("Create booking");
        mainFrame.add(createButton);
        createButton.addActionListener(e -> {
            arrivalDate = modelFrom.getValue();
            departureDate = modelTo.getValue();
            numberAdult = Integer.parseInt(numberOfAdultsField.getText());
            numberChildren = Integer.parseInt(numberOfChildrenField.getText());
            personResponsible = personList.get(personComboBox.getSelectedIndex());
            licensePlate = licencePlateField.getText();
            isReservation = isReservationCheck.isSelected();
            price = Double.parseDouble(priceField.getText());
            chipCard = chipCardList.get(chipCardComboBox.getSelectedIndex());
            broughtGear = List.of(SpaceSuitability.values()[spaceComboBox.getSelectedIndex()]);
            assignedCampingSpace = (CampingSpace) campingSpacesList.get(campingSpaceComboBox.getSelectedIndex());

            Booking booking = new Booking("id", arrivalDate, departureDate, personResponsible, numberAdult, numberChildren, licensePlate, isReservation, price, bookingInvoice, bookingConfirmation, personResponsible, chipCard, broughtGear, assignedCampingSpace, null);
            System.out.printf("Booking created!!!\n");
            try {
                fireGUIEvent(new GUIEvent(this, BookingTabComponent.Commands.BOOKING_ADDED, booking));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            mainFrame.dispose();
        });

        mainFrame.setSize(1080, 720);
        mainFrame.setVisible(true);
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
//        add(mainFrame);

        /*
        * private String id;
    private Date arrivalDate, departureDate;


    private int numberAdult;
    private int numberChildren;
    private String licensePlate;
    private boolean isReservation;
    private double price;

    private Invoice bookingInvoice;
    private Document bookingConfirmation;
    private Person guest;

    private ChipCard chipCard;
    private List<SpaceSuitability> broughtGear;

    private CampingSpace assignedCampingSpace;
    private List<RentableEquipment> rentedEquipment;*/
    }
}
