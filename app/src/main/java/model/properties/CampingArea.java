package model.properties;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import util.AttributeUtilities;

import java.util.ArrayList;
import java.util.List;

public class CampingArea implements IDepictable, IPersistable {
    private String ID;
    private String name;
    public static final int
            NAME = 0,
            STELLPLATZLIST = 1;
    private List<CampingSpace> campingSpaceList;


    public CampingArea(String name, List<CampingSpace> campingSpaceList) {
        this.ID = name;
        this.name = name;
        this.campingSpaceList = campingSpaceList;
    }

    public CampingArea(String name) {
        this.ID = name;
        this.name = name;
        campingSpaceList = new ArrayList<>();
    }

    @Override
    public String getElementID() {
        return ID;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[] {
                new Attribute("name", this, String.class, name, "", true),
                new Attribute("stellplatzList", this, List.class, campingSpaceList, null, true)
        };
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Camping Area: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }
}
