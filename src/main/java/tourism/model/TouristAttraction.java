package tourism.model;

import java.util.List;

public class TouristAttraction {
    private final String name;
    private final String description;
    private final List<Tags> tags;
    private String by;
    private final String urlName;

    public TouristAttraction(String name, String description,String by, List<Tags> tags) {
        this.name = name;
        this.description = description;
        this.urlName = this.name.replace(" ", "-");
        this.by = by;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }
    public String getUrlName(){
        return urlName;
    }
    public String getDescription(){ //den bliver brugt
        return description;
    }

    public List<Tags> getTags() {
        return tags;
    }
}
