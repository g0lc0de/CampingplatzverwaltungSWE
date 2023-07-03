package model;

public enum SpaceSuitability {
    RV("RV"),
    BIG_RV("Big RV"),

    TENT("Tent");

    private final String text;

    SpaceSuitability(String s) {
        text = s;
    }

    public String getText() {
        return text;
    }
}
