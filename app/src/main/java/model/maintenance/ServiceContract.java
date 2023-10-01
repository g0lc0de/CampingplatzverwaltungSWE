package model.maintenance;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.accounting.Account;
import model.accounting.Document;
import model.accounting.InvoicePosition;
import model.hr.Address;
import util.ArrayUtils;
import util.AttributeUtilities;

import java.time.Duration;
import java.util.Date;
import java.util.List;

public class ServiceContract extends Document {

    private static int
        COST_ESTIMATE = 4,
        COST = 5,
        REPEATING = 6,
        REPETITION_INTERVAL = 7,
        SERVICE_DATE = 8,
        SERVICES = 9,
        CONTRACTOR = 10,
        STATUS = 11;

    private double costEstimate;
    private double cost;
    private boolean repeating;
    private Duration repetitionInterval;
    private Date serviceDate;
    private List<MaintenanceService> services;
    private Contractor contractor;
    private ServiceContractStatus status;

    public ServiceContract(String id, String name) {
        super(id, name);
    }

    public Attribute[] getAttributeArray() {
        Attribute[] documentAttributes = super.getAttributeArray();
        Attribute[] newAttributes = new Attribute[]{
                new Attribute("Cost estimate", this, Double.class, costEstimate, "unknown", true),
                new Attribute("Cost", this, Double.class, cost, false, true),
                new Attribute("Repeating", this, Boolean.class, repeating, 0, true),
                new Attribute("Repetition Interval", this, Duration.class, repetitionInterval, "unknown", true),
                new Attribute("Service Date", this, Date.class, serviceDate, "unknown", true),
                new Attribute("Services", this, MaintenanceService.class, services, "unknown", true),
                new Attribute("Contractor", this, Contractor.class, contractor, "unknown", true),
                new Attribute("Service Status", this, ServiceContractStatus.class, status, "unknown", true)
        };
        return ArrayUtils.concatWithStream(documentAttributes, newAttributes);
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
