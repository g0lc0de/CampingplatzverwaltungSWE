package model.hr;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import util.AttributeUtilities;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class Person implements IDepictable, IPersistable {

    public static int
        ID = 0,
        LAST_NAME = 1,
        FIRST_NAME = 2,
        DATE_OF_BIRTH = 3,
        ID_NUMBER = 4,
        EMAIL = 5,
        SEX = 6,
        ADDRESS = 7;

    private String id;
    private String lastName;
    private String firstName;
    private Date dateOfBirth;
    private String idNumber;
    private String email;
    private char sex;

    private Address address;

    public Person(String LastName, String FirstName) {
        firstName = FirstName;
        lastName = LastName;
        id = firstName+lastName+ ThreadLocalRandom.current().nextInt(0, 101);;
    }

    @Override
    public String getElementID() {
        return id;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[]{
                new Attribute("ID", this, String.class, id, "unknown", true),
                new Attribute("Last Name", this, String.class, lastName, "unknown", true),
                new Attribute("First Name", this, String.class, firstName, "unknown", true),
                new Attribute("Date of Birth", this, Date.class, dateOfBirth, "unknown", true),
                new Attribute("ID Number", this, String.class, idNumber, 0, true),
                new Attribute("Email", this, String.class, email, "unknown", true),
                new Attribute("Sex", this, Character.class, sex, "unknown", true),
                new Attribute("Address", this, Address.class, address, "unknown", true)
        };
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Person: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }
}
