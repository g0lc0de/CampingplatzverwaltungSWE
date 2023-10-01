package model.accounting;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.hr.Address;

import java.util.Date;

public class InvoicePosition implements IDepictable, IPersistable {

    private static int
        ID = 0,
        NAME = 1,
        PRICE = 2;
    private String id;
    private String name;

    private double price;

    public InvoicePosition(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String getElementID() {
        return id;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[]{
            new Attribute("ID", this, String.class, id, "unknown", true),
            new Attribute("Name", this, String.class, name, "unknown", true),
            new Attribute("Price", this, Double.class, price, 0, true),
        };
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s", id, name, price);
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }
}
