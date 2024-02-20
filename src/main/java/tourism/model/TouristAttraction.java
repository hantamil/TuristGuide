package tourism.model;

import java.util.List;

public class TouristAttraction {
    private final String name;
    private final String description;
    private final List<String> tags;
    private String by;
    private final String urlName;

    public TouristAttraction(String name, String description, List<String> tags) {
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
