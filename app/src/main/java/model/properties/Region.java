package model.properties;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.accounting.Account;
import model.accounting.InvoicePosition;
import model.hr.Address;
import util.AttributeUtilities;

import java.util.Date;
import java.util.List;

public class Region implements IDepictable, IPersistable {

    public static int
        ID = 0,
        DESCRIPTION = 1,
        NAME = 2,
        CAMPING_AREAS = 3;

    private String id;
    private String description;
    private String name;
    private List<CampingArea> campingAreas;

    public Region(String id, String description, String name, List<CampingArea> campingAreas) {
        this.id = id;
        this.description = description;
        this.name = name;
        this.campingAreas = campingAreas;
    }

    @Override
    public String getElementID() {
        return id;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[]{
                new Attribute("ID", this, String.class, id, "unknown", true),
                new Attribute("Description", this, String.class, description, "unknown", true),
                new Attribute("Name", this, String.class, name, "unknown", true),
                new Attribute("Camping Areas", this, List.class, campingAreas, "unknown", true),
        };
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Region: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }
}
