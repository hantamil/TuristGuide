package tourism.model;

public enum Tags {
    FORLYSTELSESPARK("Forlystelsespark"),
    PARK("Park"),
    SPISESTEDER("Spisesteder"),
    UDENDØRS("Udendørs"),
    HC_ANDERSEN("H.C Andersen"),
    EVENTYR("Eventyr"),
    BØRN("Børn"),
    KUNST("Kunst"),
    MUSEUM("Museum"),
    INDENDØRS("Indendørs");

    private final String displayValue;

    private Tags(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

}
