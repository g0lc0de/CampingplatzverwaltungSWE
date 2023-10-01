package model.maintenance;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import util.AttributeUtilities;

import java.time.Duration;

public class MaintenanceService implements IDepictable, IPersistable {

    public static int
        ID = 0,
        DESCRIPTION = 1,
        SERVICE_DURATION = 2,
        COST = 3;

    private String id;
    private String description;
    private Duration serviceDuration;
    private double cost;

    public MaintenanceService(String id, String description, Duration serviceDuration, double cost) {
        this.id = id;
        this.description = description;
        this.serviceDuration = serviceDuration;
        this.cost = cost;
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
                new Attribute("Service Time", this, Duration.class, serviceDuration, "unknown", true),
                new Attribute("Cost", this, Double.class, cost, "unknown", true),
        };
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Service Contract: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }
}
