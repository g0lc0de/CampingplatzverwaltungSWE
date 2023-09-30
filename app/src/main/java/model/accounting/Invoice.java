package model.accounting;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import de.dhbwka.swe.utils.model.IPersistable;
import model.hr.Address;
import util.ArrayUtils;
import util.AttributeUtilities;

import java.util.Date;
import java.util.List;

public class Invoice extends Document implements IDepictable, IPersistable {
    private static int
        ID = 4,
        DATE = 5,
        PAID = 6,
        PRICE = 7,
        ADDRESS = 8,
        PAYMENT_SENDER_ACC = 9,
        PAYMENT_RECEIVER_ACC = 10,
        INVOICE_POSITIONS;

    private Date date;
    private boolean paid = false;
    private double price;

    private Address invoiceAddress;

    private Account paymentSender;
    private Account paymentReceiver;

    private List<InvoicePosition> invoicePositions;

    public Invoice() {
        super();
    }

    public Attribute[] getAttributeArray() {
        Attribute[] personAttributes = super.getAttributeArray();
        Attribute[] newAttributes = new Attribute[]{
                new Attribute("Pay Date", this, Date.class, date, "unknown", true),
                new Attribute("Paid", this, Boolean.class, paid, false, true),
                new Attribute("Price", this, Double.class, price, 0, true),
                new Attribute("Invoice Address", this, Address.class, invoiceAddress, "unknown", true),
                new Attribute("Payment Sender Acc.", this, Account.class, paymentSender, "unknown", true),
                new Attribute("Payment Receiver Acc.", this, Account.class, paymentReceiver, "unknown", true),
                new Attribute("Invoice Positions", this, InvoicePosition.class, invoicePositions, "unknown", true)
        };
        return ArrayUtils.concatWithStream(personAttributes, newAttributes);
    }

    @Override
    public String toString() {
        StringBuilder objectStringBuilder = new StringBuilder();
        objectStringBuilder.append("Invoice: {\n");
        objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(getAttributeArray()));
        objectStringBuilder.append("}");

        return objectStringBuilder.toString();
    }

}
