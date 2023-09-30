package model.accounting;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.hr.Address;
import util.AttributeUtilities;

import java.util.Date;

public class Account implements IDepictable, IPersistable {

    public static int
        ID = 0,
        ACCOUNT_HOLDER = 1,
        IBAN = 2;

    private String id;
    private String accountHolder;
    private String iban;

    @Override
    public String getElementID() {
        return id;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[]{
                new Attribute("Id", this, String.class, id, "unknown", true),
                new Attribute("Account Holder", this, String.class, accountHolder, false, true),
                new Attribute("IBAN", this, String.class, iban, 0, true)
        };
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Account: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }
}
