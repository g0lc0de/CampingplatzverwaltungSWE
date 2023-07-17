package model.bestand;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import utils.AttributeUtilities;

import java.util.ArrayList;
import java.util.List;

public class Stellplatz implements IDepictable {
	
	public final static int ID = 0;
	public final static int GROESSE = 1;
	public final static int RESERVIERT = 2; // Just for testing purposes
	public final static int PLATZEIGNUNGEN = 3;
	public final static int VERFUEGBARE_RESSOURCEN = 4;
	private Attribute[] attributes = new Attribute[] {
			new Attribute("ID", this, String.class, "", "unknown", true),
			new Attribute("groesse", this, Double.class, "", "unknown", true),
			new Attribute("reserviert", this, Boolean.class, false	, false, true),

			new Attribute("Platzeignungen", this,  List.class, new ArrayList<Platzeignung>(), new ArrayList<Platzeignung>(),true),
			new Attribute("VerfuegbareRessourcen", this,  List.class, new ArrayList<VerfuegbareRessource>(), new ArrayList<VerfuegbareRessource>(),true),
	};

	public Stellplatz(String IDParam, double groesse, List<Platzeignung> platzeignungen, List<VerfuegbareRessource> verfuegbareRessourcen) throws Exception {
		this(IDParam, groesse, platzeignungen, verfuegbareRessourcen, false);
	}

	public Stellplatz(String IDParam, double groesse, List<Platzeignung> platzeignungen, List<VerfuegbareRessource> verfuegbareRessourcen, boolean reserviert) throws Exception {
		super();
		this.attributes[Stellplatz.ID].setValue(IDParam);
		this.attributes[Stellplatz.GROESSE].setValue(groesse);
		this.attributes[Stellplatz.RESERVIERT].setValue(reserviert);

		this.attributes[Stellplatz.PLATZEIGNUNGEN].setValue(platzeignungen);
		this.attributes[Stellplatz.VERFUEGBARE_RESSOURCEN].setValue(verfuegbareRessourcen);
	}

	public void setReserviert(Boolean reserviert) throws Exception {
		this.attributes[Stellplatz.RESERVIERT].setValue(reserviert);
	}

	public Boolean isReserviert() {
		return (Boolean) this.attributes[RESERVIERT].getValue();
	}

	public void addPlatzeignung(Platzeignung neuePlatzeignung) {
		System.out.printf("Add platzeignung: %s\n", neuePlatzeignung);

		List<Platzeignung> platzeignungen = (List<Platzeignung>) this.attributes[PLATZEIGNUNGEN].getValue();
		platzeignungen.add(neuePlatzeignung);
	}

	public void removePlatzeignung(Platzeignung entfernendePlatzeignung) {
		List<Platzeignung> platzeignungen = (List<Platzeignung>) this.attributes[PLATZEIGNUNGEN].getValue();
		platzeignungen.remove(entfernendePlatzeignung);
	}

	public List<Platzeignung> getPlatzeignungen() {
		return (List<Platzeignung>) this.attributes[PLATZEIGNUNGEN];
	}

	public void addVerfuegbareRessourcen(VerfuegbareRessource neuePlatzeignung) {
		List<VerfuegbareRessource> verfuegbareRessourcen = (List<VerfuegbareRessource>) this.attributes[VERFUEGBARE_RESSOURCEN].getValue();
		verfuegbareRessourcen.add(neuePlatzeignung);
	}

	public void removeVerfuegbareRessourcen(VerfuegbareRessource entfernendePlatzeignung) {
		List<VerfuegbareRessource> verfuegbareRessourcen = (List<VerfuegbareRessource>) this.attributes[VERFUEGBARE_RESSOURCEN].getValue();
		verfuegbareRessourcen.remove(entfernendePlatzeignung);
	}

	public List<VerfuegbareRessource> getVerfuegbareRessourcen() {
		return (List<VerfuegbareRessource>) this.attributes[VERFUEGBARE_RESSOURCEN].getValue();
	}

	public Attribute[] getAttributeArray() {
		return this.attributes;
	}

	public String getElementID() {
		return (String) this.attributes[ID].getValue();
	}

	@Override
	public String toString() {
		StringBuilder objectStringBuilder = new StringBuilder();
		objectStringBuilder.append("Stellplatz: {\n");
		objectStringBuilder.append(AttributeUtilities.convertAttributeArrayToSmallString(this.attributes));
		objectStringBuilder.append(String.format("\tPlatzeignung: %s\n", this.attributes[PLATZEIGNUNGEN].getValue()));
		objectStringBuilder.append(String.format("\tVerfuegbareRessourcen: %s\n", this.attributes[VERFUEGBARE_RESSOURCEN].getValue()));
		objectStringBuilder.append("}");

		return objectStringBuilder.toString();
	}
}
