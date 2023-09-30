package model.hr;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import util.AttributeUtilities;

import java.util.Date;

public class Address implements IDepictable, IPersistable {

    public static int
        ID = 0,
        COUNTRY = 1,
        STREET = 2,
        HOUSING_NUMBER = 3,
        ADDITIONAL_INFORMATION = 4,
        ZIP_CODE = 5,
        CITY = 6;

    private String id;
    private Country country;
    private String street;
    private String housingNumber;
    private String additionalLine;
    private String zipCode;
    private String city;

    @Override
    public String getElementID() {
        return id;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[]{
                new Attribute("ID", this, String.class, id, "unknown", true),
                new Attribute("Country", this, Country.class, country, "unknown", true),
                new Attribute("Street", this, String.class, street, "unknown", true),
                new Attribute("Housing Number", this, String.class, housingNumber, "unknown", true),
                new Attribute("Additional Information", this, String.class, additionalLine, 0, true),
                new Attribute("ZIP Code", this, String.class, zipCode, "unknown", true),
                new Attribute("City", this, String.class, city, "unknown", true)
        };
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Address: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }
}
