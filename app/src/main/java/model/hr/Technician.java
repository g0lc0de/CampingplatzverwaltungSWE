package model.hr;

import de.dhbwka.swe.utils.model.Attribute;
import util.ArrayUtils;
import util.AttributeUtilities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Technician extends Employee {

    public static int SKILLS = 13;

    private List<TechnicalSkill> skills = new ArrayList<>();

    public Technician(String LastName, String FirstName) {
        super(LastName, FirstName);
    }

    public Attribute[] getAttributeArray() {
        Attribute[] personAttributes = super.getAttributeArray();
        Attribute[] newAttributes = new Attribute[] {
                new Attribute("Technical Skills", this, List.class, skills, "unknown", true),
        };
        return ArrayUtils.concatWithStream(personAttributes, newAttributes);
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Receptionist: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }
}
