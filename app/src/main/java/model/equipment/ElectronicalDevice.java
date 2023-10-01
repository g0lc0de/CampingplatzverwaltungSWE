package model.equipment;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;

public class ElectronicalDevice implements IDepictable, IPersistable {

    public static int
            ID = 0;

    private String id;

    public ElectronicalDevice(String id) {
        this.id = id;
    }

    @Override
    public String getElementID() {
        return id;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[]{
                new Attribute("ID", this, String.class, id, "", true)
        };
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }
}
