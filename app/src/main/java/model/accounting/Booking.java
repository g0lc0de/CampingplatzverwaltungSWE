package model.accounting;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.equipment.ChipCard;
import model.equipment.RentableEquipment;
import model.hr.Person;
import model.properties.CampingSpace;
import model.properties.SpaceSuitability;
import util.AttributeUtilities;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Booking implements IDepictable, IPersistable {
    public final static int
            ID = 0,
            ARRIVAL_DATE = 1,
            DEPARTURE_DATE = 2,
            PERSON_RESPONSIBLE = 3,
            NUMBER_ADULTS = 4,
            NUMBER_CHILDREN = 5,
            LICENSE_PLATE = 6,
            IS_RESERVATION = 7,
            PRICE = 8,
            BOOKING_INVOICE = 9,
            BOOKING_CONFIRMATION = 10,
            GUEST = 11,
            CHIP_CARD = 12,
            BROUGHT_GEAR = 13,
            ASSIGNED_CAMPING_SPACE = 14,
            RENTED_EQUIPMENT = 15;

    private String id;
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

    public Booking(String id, Date arrivalDate, Date departureDate, Person personResponsible, int numberAdult, int numberChildren, String licensePlate, boolean isReservation, double price, Invoice bookingInvoice, Document bookingConfirmation, Person guest, ChipCard chipCard, List<SpaceSuitability> broughtGear, CampingSpace assignedCampingSpace, List<RentableEquipment> rentedEquipment) {
        this.id = id;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.personResponsible = personResponsible;
        this.numberAdult = numberAdult;
        this.numberChildren = numberChildren;
        this.licensePlate = licensePlate;
        this.isReservation = isReservation;
        this.price = price;
        this.bookingInvoice = bookingInvoice;
        this.bookingConfirmation = bookingConfirmation;
        this.guest = guest;
        this.chipCard = chipCard;
        this.broughtGear = broughtGear;
        this.assignedCampingSpace = assignedCampingSpace;
        this.rentedEquipment = rentedEquipment;
    }

    public Booking() {
        arrivalDate = Date.from(between(Instant.now().minusSeconds(60*60*24*365*10),Instant.now()));
        departureDate = Date.from(between(Instant.now().minusSeconds(60*60*24*365*10),Instant.now()));
    }

    public static Instant between(Instant startInclusive, Instant endExclusive) {
        long startSeconds = startInclusive.getEpochSecond();
        long endSeconds = endExclusive.getEpochSecond();
        long random = ThreadLocalRandom
                .current()
                .nextLong(startSeconds, endSeconds);

        return Instant.ofEpochSecond(random);
    }

    @Override
    public String getElementID() {
        return id;
    }

    public Person getPersonResponsible() {
        return personResponsible;
    }

    public void setPersonResponsible(Person personResponsible) {
        this.personResponsible = personResponsible;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[] {
                new Attribute("ID", this, String.class, id, "", true),
                new Attribute("Arrival Date", this, Date.class, arrivalDate, "unknown", true),
                new Attribute("Departure Date", this, Date.class, departureDate, "unknown", true),
                new Attribute("Person Responsible", this, Person.class, personResponsible, "unknown", true),
                new Attribute("Number Adults", this, Integer.class, numberAdult, 0, true),
                new Attribute("Number Children", this, Integer.class, numberChildren, 0, true),
                new Attribute("License Plate", this, String.class, licensePlate, "unknown", true),
                new Attribute("Is Reservation", this, Boolean.class, isReservation, true, true),
                new Attribute("Price", this, Double.class, price, 0, true),
                new Attribute("Booking Invoice", this, Invoice.class, bookingInvoice, "unknown", true),
                new Attribute("Booking Confirmation", this, Document.class, bookingConfirmation, "unknown", true),
                new Attribute("Guest", this, Person.class, guest, "unknown", true),
                new Attribute("Chip Card", this, ChipCard.class, chipCard, "unknown", true),
                new Attribute("Brought Gear", this, List.class, broughtGear, "unknown", true),
                new Attribute("Assigned Camping Space", this, CampingSpace.class, personResponsible, "unknown", true),
                new Attribute("Rented Equipment", this, List.class, rentedEquipment, "unknown", true),
        };
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Anlage: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }
}
