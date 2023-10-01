package model.equipment;

import de.dhbwka.swe.utils.model.Attribute;

public class ChipCard extends ElectronicalDevice{

    public static int
        NAME = 0;

    public ChipCard(String name) {
        super(name);
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[]{
                new Attribute("Name", this, String.class, getElementID(), "", true)
        };
    }
}
