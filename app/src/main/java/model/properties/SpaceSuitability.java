package model.properties;

public enum SpaceSuitability {
    CAR("CAR"),
    RV("RV"),
    BIG_RV("Big RV"),
    TENT("Tent");

    private final String text;

    SpaceSuitability(String suitabilityName) {
        this.text = suitabilityName;
    }

    public String getText() {
        return text;
    }
}
