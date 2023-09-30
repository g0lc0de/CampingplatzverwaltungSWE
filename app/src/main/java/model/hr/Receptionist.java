package model.hr;

import de.dhbwka.swe.utils.model.Attribute;
import util.ArrayUtils;
import util.AttributeUtilities;

import java.util.ArrayList;
import java.util.List;

public class Receptionist extends Employee{

    public static int LANGUAGES = 13;

    private List<String> languages = new ArrayList<>();

    public Receptionist(String LastName, String FirstName) {
        super(LastName, FirstName);
    }

    public Attribute[] getAttributeArray() {
        Attribute[] personAttributes = super.getAttributeArray();
        Attribute[] newAttributes = new Attribute[] {
                new Attribute("Languages", this, List.class, languages, "unknown", true),
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
