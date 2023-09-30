package model.hr;

import de.dhbwka.swe.utils.model.Attribute;
import util.ArrayUtils;
import util.AttributeUtilities;

import java.util.Date;

public class Employee extends Person {

    public static int
        EMPLOYEE_SINCE = 8,
        SALARY = 9,
        REMAINING_VACATIONDAYS = 10,
        OVERTIME = 11,
        MANAGER = 12;

    private Date employeeSince;
    private double salary;
    private int remainingVacationDays;
    private double overtime = 0;

    private Employee manager;

    public Employee(String LastName, String FirstName) {
        super(LastName, FirstName);
    }

    public Attribute[] getAttributeArray() {
        Attribute[] personAttributes = super.getAttributeArray();
        Attribute[] newAttributes = new Attribute[] {
                new Attribute("Employee Since", this, Date.class, employeeSince, "unknown", true),
                new Attribute("Salary", this, Double.class, salary, "unknown", true),
                new Attribute("Remaining Vacation Days", this, Integer.class, remainingVacationDays, "unknown", true),
                new Attribute("Overtime", this, Double.class, overtime, "unknown", true),
                new Attribute("Manager", this, Employee.class, manager, "unknown", true)
        };
        return ArrayUtils.concatWithStream(personAttributes, newAttributes);
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Employee: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }

}
