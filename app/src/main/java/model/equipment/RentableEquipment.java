package model.equipment;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;

import java.util.Date;

public class RentableEquipment implements IDepictable, IPersistable {

    private String id;
    private String name;
    private int costPerDay;
    private int maxRentDurationInDays;
    private int minRentDurationInDays;

    public RentableEquipment(String id, String name,int costPerDay, int maxRentDurationInDays, int minRentDurationInDays) {
        this.id = id;
        this.name = name;
        this.costPerDay = costPerDay;
        this.maxRentDurationInDays = maxRentDurationInDays;
        this.minRentDurationInDays = minRentDurationInDays;
    }

    @Override
    public String getElementID() {
        return id;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[]{
                new Attribute("ID", this, String.class, id, "", true),
                new Attribute("Name", this, String.class, name, "unknown", true),
                new Attribute("Cost Per Day", this, Integer.class, costPerDay, 0, true),
                new Attribute("Max Rent Duration", this, Integer.class, maxRentDurationInDays, 0, true),
                new Attribute("Min Rent Duration", this, Integer.class, minRentDurationInDays, 0, true)
        };
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }
}
