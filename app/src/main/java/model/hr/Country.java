package model.hr;

public enum Country {

    DE ("GERMANY", "DE"),
    EN ("ENGLAND", "EN");

    String countryName;
    String countryCode;

    Country(String countryName, String countryCode) {
        this.countryName = countryName;
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return String.format("%s,%s",countryCode, countryName);
    }
}
