package controller;

import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.util.CommonEntityManager;
import model.accounting.Booking;
import model.accounting.Document;
import model.accounting.Invoice;
import model.equipment.ChipCard;
import model.hr.Address;
import model.hr.Country;
import model.hr.Person;
import model.hr.Receptionist;
import model.properties.CampingArea;
import model.properties.CampingSpace;
import model.properties.Region;
import util.EntityManagerHolder;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MockDataCreator {

    public static void createMockData() throws Exception {

        CommonEntityManager entityManager = EntityManagerHolder.getInstance().getEntityManager();

//      Add Receptionists
        entityManager.persist(new Receptionist("Mustermann", "Max"));
        entityManager.persist(new Receptionist("Schneider", "Romy"));

//      Add Guests
        entityManager.persist(new Person("Schneider", "Magdalena"));
        entityManager.persist(new Person("Wussow", "Klausjürgen"));

//      Add Chip Cards
        entityManager.persist(new ChipCard("Peter"));
        entityManager.persist(new ChipCard("Hans"));

//      Add Booking
        Booking booking = new Booking();
        booking.setArrivalDate(new Date());
        booking.setDepartureDate(new Date());
        entityManager.persist(booking);

//
        List<CampingSpace> campingSpacesAreaA = Arrays.asList(
            new CampingSpace("1A", false),
            new CampingSpace("2A", true),
            new CampingSpace("3A", false),
            new CampingSpace("4A", false),
            new CampingSpace("5A", true),
            new CampingSpace("6A", false),
            new CampingSpace("7A", false),
            new CampingSpace("8A", true),
            new CampingSpace("9A", false)
        );

        List<CampingSpace> campingSpacesAreaB = Arrays.asList(
                new CampingSpace("1B", false),
                new CampingSpace("2B", true),
                new CampingSpace("3B", false),
                new CampingSpace("4B", false),
                new CampingSpace("5B", true)
        );

        List<CampingSpace> campingSpacesAreaC = Arrays.asList(
                new CampingSpace("1C", false),
                new CampingSpace("2C", true),
                new CampingSpace("3C", false),
                new CampingSpace("4C", false),
                new CampingSpace("5C", true),
                new CampingSpace("6C", false),
                new CampingSpace("7C", false),
                new CampingSpace("8C", true)
        );

        for (CampingSpace c : campingSpacesAreaA) {
            entityManager.persist(c);
        }

        for (CampingSpace c : campingSpacesAreaB) {
            entityManager.persist(c);
        }

        for (CampingSpace c : campingSpacesAreaC) {
            entityManager.persist(c);
        }

        List<CampingArea> campingAreasLakeside = Arrays.asList(
                new CampingArea("Area A", campingSpacesAreaA),
                new CampingArea("Area B", campingSpacesAreaB)
        );

        List<CampingArea> campingAreasForest = Arrays.asList(
                new CampingArea("Area C", campingSpacesAreaC)
        );

        for (CampingArea c : campingAreasLakeside) {
            entityManager.persist(c);
        }

        for (CampingArea c : campingAreasForest) {
            entityManager.persist(c);
        }


        entityManager.persist(new Region("Lakeside", "Camping Region next to a big lake", "Lakeside", campingAreasLakeside));
        entityManager.persist(new Region("Magic Forest", "Camping Region next to the forest", "Magic Forest", campingAreasForest));


//      invoiceList.add(new Invoice("I1", "Invoice1", new Address("1", Country.DE, "Test Street", "1", "", "12345", "Teststadt")));
//      invoiceList.add(new Invoice("I1", "Invoice1", new Address("1", Country.EN, "Street Test", "1", "", "54321", "Hausenhausen")));
//
//      documentList.add(new Document("Document_1", "doc1"));
//      documentList.add(new Document("Document_2", "doc2"));
    }
}
