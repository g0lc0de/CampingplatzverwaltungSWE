package utils;


import de.dhbwka.swe.utils.model.Attribute;

public class AttributeUtilities {

    /**
     *
     * Converts an Attribute Array into a String that can be displayed in the console.
     * The Standard toString() Method of an Attribute includes all properties, but apart
     * from the use in the View a Name - Value pair is easier to debug.
     * @param attributeArray Attribute array to print out
     * @return Multiline String where each Row has an Attribute Name and Value
     */
    public static String convertAttributeArrayToSmallString(Attribute[] attributeArray) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("\t|%-12s |%-10s:|\n","Name", "Wert"));

        for (Attribute attr : attributeArray) {
            stringBuilder.append(String.format("\t|%-12s |%-10s:|\n",attr.getName(), attr.getValue()));
        }

        return stringBuilder.toString();
    }

}
