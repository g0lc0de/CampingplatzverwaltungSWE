package model;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;

import java.util.ArrayList;
import java.util.List;

public class Oberbereich implements IDepictable {
    private String ID;
    private String lagebeschreibung, name;
    public static final int LAGEBESCHREIBUNG = 0;
    public static final int NAME = 1;
    public static final int STELLPLATZLIST = 2;
    private List<Stellplatz> stellplatzList;


    public Oberbereich(String ID, String lagebeschreibung, String name, List<Stellplatz> stellplatzList) {
        this.ID = ID;
        this.lagebeschreibung = lagebeschreibung;
        this.name = name;
        this.stellplatzList = stellplatzList;
    }

    public Oberbereich(String lagebeschreibung, String name) {
        this.ID = "0";
        this.lagebeschreibung = lagebeschreibung;
        this.name = name;
        stellplatzList = new ArrayList<>();
    }

    @Override
    public String getElementID() {
        return ID;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[] {
                new Attribute("lagebeschreibung", this, String.class, lagebeschreibung, "", true),
                new Attribute("name", this, String.class, name, "", true),
                new Attribute("stellplatzList", this, List.class, stellplatzList, null, true)
        };
    }
}
