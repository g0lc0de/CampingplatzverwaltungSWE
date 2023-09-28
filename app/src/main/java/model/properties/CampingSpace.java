package model.properties;

import de.dhbwka.swe.utils.model.Attribute;
import de.dhbwka.swe.utils.model.IDepictable;
import utils.AttributeUtilities;

import java.util.ArrayList;
import java.util.List;

public class CampingSpace implements IDepictable {
	
	public final static int ID = 0;
	public final static int AREA = 1;
	public final static int RESERVED = 2; // Just for testing purposes
	public final static int SPACESUITABILITY_LIST = 3;
	public final static int AVAILABLE_RESSOURCES_LIST = 4;
	private Attribute[] attributes = new Attribute[] {
			new Attribute("ID", this, String.class, "", "unknown", true),
			new Attribute("Area", this, Double.class, "", "unknown", true),
			new Attribute("Reserved", this, Boolean.class, false	, false, true),

			new Attribute("Space Suitability List", this,  List.class, new ArrayList<SpaceSuitability>(), new ArrayList<SpaceSuitability>(),true),
			new Attribute("Available Ressource List", this,  List.class, new ArrayList<availableRessource>(), new ArrayList<availableRessource>(),true),
	};

	public CampingSpace(String IDParam, boolean reserved) throws Exception {
		this(IDParam, 100, new ArrayList<SpaceSuitability>(), new ArrayList<availableRessource>(), reserved);
	}
	public CampingSpace(String IDParam, double groesse, List<SpaceSuitability> spaceSuitabilities, List<availableRessource> availableRessourcen) throws Exception {
		this(IDParam, groesse, spaceSuitabilities, availableRessourcen, false);
	}

	public CampingSpace(String IDParam, double groesse, List<SpaceSuitability> spaceSuitabilities, List<availableRessource> availableRessourcen, boolean reserviert) throws Exception {
		super();
		this.attributes[CampingSpace.ID].setValue(IDParam);
		this.attributes[CampingSpace.AREA].setValue(groesse);
		this.attributes[CampingSpace.RESERVED].setValue(reserviert);

		this.attributes[CampingSpace.SPACESUITABILITY_LIST].setValue(spaceSuitabilities);
		this.attributes[CampingSpace.AVAILABLE_RESSOURCES_LIST].setValue(availableRessourcen);
	}

	public void setReserviert(Boolean reserviert) throws Exception {
		this.attributes[CampingSpace.RESERVED].setValue(reserviert);
	}

	public Boolean isReserviert() {
		return (Boolean) this.attributes[RESERVED].getValue();
	}

	public void addSpaceSuitability(SpaceSuitability newSpaceSuitability) {
		System.out.printf("Add Space Suitability: %s\n", newSpaceSuitability);

		List<SpaceSuitability> spaceSuitabilityList = (List<SpaceSuitability>) this.attributes[SPACESUITABILITY_LIST].getValue();
		spaceSuitabilityList.add(newSpaceSuitability);
	}

	public void removeSpaceSuitability(SpaceSuitability SpaceSuitabilityToRemove) {
		List<SpaceSuitability> spaceSuitabilityList = (List<SpaceSuitability>) this.attributes[SPACESUITABILITY_LIST].getValue();
		spaceSuitabilityList.remove(SpaceSuitabilityToRemove);
	}

	public List<SpaceSuitability> getSpaceSuitabilityList() {
		return (List<SpaceSuitability>) this.attributes[SPACESUITABILITY_LIST];
	}

	public void addVerfuegbareRessourcen(availableRessource neueRessource) {
		List<availableRessource> availableRessourcen = (List<availableRessource>) this.attributes[AVAILABLE_RESSOURCES_LIST].getValue();
		availableRessourcen.add(neueRessource);
	}

	public void removeVerfuegbareRessourcen(availableRessource entfernendeRessource) {
		List<availableRessource> availableRessourcen = (List<availableRessource>) this.attributes[AVAILABLE_RESSOURCES_LIST].getValue();
		availableRessourcen.remove(entfernendeRessource);
	}

	public List<availableRessource> getVerfuegbareRessourcen() {
		return (List<availableRessource>) this.attributes[AVAILABLE_RESSOURCES_LIST].getValue();
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
		objectStringBuilder.append(String.format("\tSpace Suitabilities: %s\n", this.attributes[SPACESUITABILITY_LIST].getValue()));
		objectStringBuilder.append(String.format("\tVerfuegbareRessourcen: %s\n", this.attributes[AVAILABLE_RESSOURCES_LIST].getValue()));
		objectStringBuilder.append("}");

		return objectStringBuilder.toString();
	}
}
