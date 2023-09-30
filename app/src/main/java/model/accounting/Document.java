package model.accounting;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;

import java.util.Date;

public class Document implements IDepictable, IPersistable {

    public static int
        ID = 0,
        NAME = 1,
        CREATION_DATE = 2,
        LAST_MODIFIED_DATE = 3;

    private String id;
    private String name;
    private Date creationDate;
    private Date lastModified;
    @Override
    public String getElementID() {
        return id;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[]{
                new Attribute("ID", this, String.class, id, "", true),
                new Attribute("Name", this, String.class, name, "unknown", true),
                new Attribute("Creation Date", this, Date.class, creationDate, "unknown", true),
                new Attribute("Last Modified Date", this, Date.class, lastModified, "unknown", true)
        };
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }
}
