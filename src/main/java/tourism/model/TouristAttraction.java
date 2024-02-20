package tourism.model;

public class TouristAttraction {
    private final String name;
    private final String description;

    public TouristAttraction(String name, String description) {
        this.name = name;
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }
}
