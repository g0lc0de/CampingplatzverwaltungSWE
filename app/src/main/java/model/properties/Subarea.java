package model.properties;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;

import java.util.List;
import java.util.Vector;

public class Subarea implements IDepictable {

    private String ID;
    private String lagebeschreibung, name;
    public static final int LAGEBESCHREIBUNG = 0;
    public static final int NAME = 1;

    public Subarea(String ID, String lagebeschreibung, String name) {
        this.ID = ID;
        this.lagebeschreibung = lagebeschreibung;
        this.name = name;
    }

    public Subarea(String lagebeschreibung, String name) {
        this.ID = "0";
        this.lagebeschreibung = lagebeschreibung;
        this.name = name;
    }

    @Override
    public String getElementID() {
        return null;
    }

    @Override
    public String getVisibleText() {
        return IDepictable.super.getVisibleText();
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[]{
                new Attribute("lagebeschreibung", this, String.class, lagebeschreibung, "", true),
                new Attribute("name", this, String.class, name, "", true),

        };
    }

    @Override
    public Attribute[] setAttributeValues(Attribute[] attributeArray) {
        return IDepictable.super.setAttributeValues(attributeArray);
    }

    @Override
    public List<Attribute> getAttributes() {
        return IDepictable.super.getAttributes();
    }
}
