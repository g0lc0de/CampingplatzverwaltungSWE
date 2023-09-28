package model.properties;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.hr.Employee;
import model.maintenance.ServiceContract;
import utils.AttributeUtilities;

import java.util.ArrayList;
import java.util.List;

public class Facility implements IDepictable, IPersistable {

    public final static int ID = 0;
    public final static int NAME = 1;
    public final static int BUILDING_AREA = 2;
    public final static int WATER_USAGE = 3;
    public final static int POWER_USAGE = 4;
    public final static int ACCESSIBLE = 5;
    public final static int OPENING_HOURS = 6;
    public final static int STATUS = 7;
    public final static int DESCRIPTION = 8;
    public final static int SERVICE_CONTRACTS = 9;
    public final static int ASSIGNED_EMPLOYEES = 10;

//TODO    private List<TechnischesGeraet> technischeGeraete = new ArrayList<>()

    private Attribute[] attributes = new Attribute[] {
            new Attribute("ID", this, String.class, "", "unknown", true),
            new Attribute("Name", this, String.class, "", "unknown", true),
            new Attribute("Building Area", this, Double.class, "", "unknown", true),
            new Attribute("Water Usage", this, Double.class, 0, 0, true),
            new Attribute("Power Usage", this, Double.class, 0, 0, true),
            new Attribute("Accessible", this, Boolean.class, false, false, true),
            new Attribute("Opening Hours", this, String.class, "", "unknown", true),
            new Attribute("Status", this, Status.class, Status.CLOSED, Status.CLOSED, true),
            new Attribute("Description", this, String.class, "", "unknown", true),

            new Attribute("Service Contracts", this, List.class, new ArrayList<ServiceContract>(), new ArrayList<ServiceContract>(), true),
            new Attribute("Assigned Employee", this, List.class, new ArrayList<Employee>(), new ArrayList<Employee>(), true)
    };

    public Facility(String id, String Name, double building_area, boolean accessible, Status status) throws Exception {
        attributes[ID].setValue(id);
        attributes[NAME].setValue(Name);
        attributes[BUILDING_AREA].setValue(building_area);

        attributes[ACCESSIBLE].setValue(accessible);
        attributes[STATUS].setValue(status);
    }

    public void addServiceContract(ServiceContract serviceContract) {
        List<ServiceContract> serviceContractList = (List<ServiceContract>) this.attributes[SERVICE_CONTRACTS].getValue();
        serviceContractList.add(serviceContract);
    }

    public void setServiceContractList(List<ServiceContract> neueWartungsauftraege) throws Exception {
        attributes[SERVICE_CONTRACTS].setValue(neueWartungsauftraege);
    }

    public void addEmployee(Employee employee) {
        List<Employee> assignedEmployee = (List<Employee>) this.attributes[ASSIGNED_EMPLOYEES].getValue();
        assignedEmployee.add(employee);
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Anlage: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(this.attributes));
        objectStringBuilder.append(String.format("\tWartungsaufträge: %s\n", this.attributes[SERVICE_CONTRACTS].getValue()));
        objectStringBuilder.append(String.format("\tZugewiesene Mitarbeiter: %s\n", this.attributes[ASSIGNED_EMPLOYEES].getValue()));
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
