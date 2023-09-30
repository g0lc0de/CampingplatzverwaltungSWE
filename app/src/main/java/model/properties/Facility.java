package model.properties;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.equipment.ElectronicalDevice;
import model.hr.Employee;
import model.maintenance.ServiceContract;
import util.AttributeUtilities;

import java.util.ArrayList;
import java.util.List;

public class Facility implements IDepictable, IPersistable {

    public final static int
            ID = 0,
            NAME = 1,
            BUILDING_AREA = 2,
            WATER_USAGE = 3,
            POWER_USAGE = 4,
            ACCESSIBLE = 5,
            OPENING_HOURS = 6,
            STATUS = 7,
            DESCRIPTION = 8,
            SERVICE_CONTRACTS = 9,
            ASSIGNED_EMPLOYEES = 10;

    private String id;
    private String name;
    private double building_size;
    private boolean accessible;
    private double water_usage = 0;
    private double power_usage = 0;
    private String opening_hours = "";
    private Status status = Status.CLOSED;
    private String description = "";

    private List<ServiceContract> serviceContracts = new ArrayList<>();
    private List<Employee> assignedEmployees = new ArrayList<>();

    private List<ElectronicalDevice> electronicalDevices = new ArrayList<>();

    public Facility(String id, String name, double building_size, boolean accessible, Status status) throws Exception {
        this.id = id;
        this.name = name;
        this.building_size = building_size;
        this.accessible = accessible;
        this.status = status;
    }


    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Anlage: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append(String.format("\tService Contracts: %s\n", serviceContracts));
        objectStringBuilder.append(String.format("\tAssigned Employees: %s\n", assignedEmployees));
        objectStringBuilder.append(String.format("\tElectronical Devices: %s\n", electronicalDevices));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[] {
                new Attribute("ID", this, String.class, id, "unknown", true),
                new Attribute("Name", this, String.class, name, "unknown", true),
                new Attribute("Building Size", this, Double.class, building_size, "unknown", true),
                new Attribute("Water Usage", this, Double.class, water_usage, 0, true),
                new Attribute("Power Usage", this, Double.class, power_usage, 0, true),
                new Attribute("Accessible", this, Boolean.class, accessible, false, true),
                new Attribute("Opening Hours", this, String.class, opening_hours, "unknown", true),
                new Attribute("Status", this, Status.class, status, Status.CLOSED, true),
                new Attribute("Description", this, String.class, description, "unknown", true),

                new Attribute("Service Contracts", this, List.class, serviceContracts, new ArrayList<ServiceContract>(), true),
                new Attribute("Assigned Employee", this, List.class, assignedEmployees, new ArrayList<Employee>(), true),
                new Attribute("Electronical Devices", this, List.class, electronicalDevices, new ArrayList<ElectronicalDevice>(), true)
        };
    }

    @Override
    public String getElementID() {
        return id;
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }
}
