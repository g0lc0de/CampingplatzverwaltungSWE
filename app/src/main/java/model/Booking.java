package model;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import model.personen.Person;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Booking implements IDepictable {
    private Person personResponsible;
    private Date arrivalDate, departureDate;
    public final static int ARRIVAL_DATE = 0, DEPARTURE_DATE = 1, PERSON_RESPONSIBLE = 2;


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
        return null;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[] {
                new Attribute("Arrival Date", this, Date.class, arrivalDate, null, true),
                new Attribute("Departure Date", this, Date.class, departureDate, null, true),
                new Attribute("Person Responsible", this, Person.class, personResponsible, null, true),
        };
    }
}
