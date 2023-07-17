package model.bestand;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.personen.Mitarbeiter;
import model.wartungen.Wartungsauftrag;
import utils.AttributeUtilities;

import java.util.ArrayList;
import java.util.List;

public class Anlage implements IDepictable, IPersistable {

    public final static int ID = 0;
    public final static int NAME = 1;
    public final static int GEBAEUDEFLAECHE = 2;
    public final static int WASSERZAEHLSTAND = 3;
    public final static int STROMZAEHLSTAND = 4;
    public final static int BARRIEREFREI = 5;
    public final static int OEFFNUNGSZEITEN = 6;
    public final static int STATUS = 7;
    public final static int BESCHREIBUNG = 8;
    public final static int WARTUNGSAUFTRAEGE = 9;
    public final static int ZUGEWIESENE_MITARBEITER = 10;

//TODO    private List<TechnischesGeraet> technischeGeraete = new ArrayList<>()

    private Attribute[] attributes = new Attribute[] {
            new Attribute("ID", this, String.class, "", "unknown", true),
            new Attribute("Name", this, String.class, "", "unknown", true),
            new Attribute("Gebaeudeflaeche", this, Double.class, "", "unknown", true),
            new Attribute("Wasserzaehlstand", this, Double.class, 0, 0, true),
            new Attribute("Stromzaehlstand", this, Double.class, 0, 0, true),
            new Attribute("Barrierefrei", this, Boolean.class, false, false, true),
            new Attribute("Oeffnungszeiten", this, String.class, "", "unknown", true),
            new Attribute("Status", this, Status.class, Status.GESCHLOSSEN, Status.GESCHLOSSEN, true),
            new Attribute("Beschreibung", this, String.class, "", "unknown", true),

            new Attribute("Wartungsauftraege", this, List.class, new ArrayList<Wartungsauftrag>(), new ArrayList<Wartungsauftrag>(), true),
            new Attribute("zugewieseneMitarbeiter", this, List.class, new ArrayList<Mitarbeiter>(), new ArrayList<Mitarbeiter>(), true)
    };

    public Anlage(String id, String Name, double Gebaedeflaeche, boolean barrierefrei, Status status) throws Exception {
        attributes[ID].setValue(id);
        attributes[NAME].setValue(Name);
        attributes[GEBAEUDEFLAECHE].setValue(Gebaedeflaeche);

        attributes[BARRIEREFREI].setValue(barrierefrei);
        attributes[STATUS].setValue(status);
    }

    public void addWartungsauftrag(Wartungsauftrag wartungsauftrag) {
        List<Wartungsauftrag> wartungsauftraege = (List<Wartungsauftrag>) this.attributes[WARTUNGSAUFTRAEGE].getValue();
        wartungsauftraege.add(wartungsauftrag);
    }

    public void setWartungsauftraege(List<Wartungsauftrag> neueWartungsauftraege) throws Exception {
        attributes[WARTUNGSAUFTRAEGE].setValue(neueWartungsauftraege);
    }

    public void addMitarbeiter(Mitarbeiter mitarbeiter) {
        List<Mitarbeiter> zugewieseneMitarbeiter = (List<Mitarbeiter>) this.attributes[ZUGEWIESENE_MITARBEITER].getValue();
        zugewieseneMitarbeiter.add(mitarbeiter);
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Anlage: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(this.attributes));
        objectStringBuilder.append(String.format("\tWartungsaufträge: %s\n", this.attributes[WARTUNGSAUFTRAEGE].getValue()));
        objectStringBuilder.append(String.format("\tZugewiesene Mitarbeiter: %s\n", this.attributes[ZUGEWIESENE_MITARBEITER].getValue()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }

    @Override
    public Attribute[] getAttributeArray() {
        return attributes;
    }

    @Override
    public String getElementID() {
        return (String) attributes[ID].getValue();
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }
}
