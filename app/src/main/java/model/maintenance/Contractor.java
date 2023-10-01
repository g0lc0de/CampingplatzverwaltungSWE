package model.maintenance;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.accounting.Account;
import util.AttributeUtilities;

import java.time.Duration;
import java.util.Date;

public class Contractor implements IDepictable,IPersistable {

    public static int
        ID = 0,
        NAME = 1,
        TELEPHONE_NUMBER = 2,
        EMAIL = 3,
        COMPANY_ACCOUNT = 4;

    private String id;
    private String name;
    private String telephoneNumber;
    private String email;
    private Account companyAccount;

    @Override
    public String getElementID() {
        return id;
    }

    @Override
    public Attribute[] getAttributeArray() {
        return new Attribute[]{
                new Attribute("ID", this, String.class, id, "unknown", true),
                new Attribute("Name", this, String.class, name, false, true),
                new Attribute("Telephone", this, String.class, telephoneNumber, 0, true),
                new Attribute("Email", this, String.class, email, "unknown", true),
                new Attribute("Company Account", this, Account.class, companyAccount, "unknown", true),
        };
    }

    @Override
    public Object getPrimaryKey() {
        return getElementID();
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Contractor: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }
}
