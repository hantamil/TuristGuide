package tourism.model;

public class TouristAttraction {
    String name;
    String description;

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
