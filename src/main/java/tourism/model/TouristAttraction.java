package tourism.model;

public class TouristAttraction {
    private final String name;
    private final String description;

    public TouristAttraction(String name, String description) {
        this.name = name;
        this.description = description;
        this.urlName = this.name.replace(" ", "-");
        this.tags = tags;
    }

    public String getName() {
        return name;
    }
    public String getUrl(){
        return urlName;
    }
    public String getDescription(){ //den bliver brugt
        return description;
    }
}
