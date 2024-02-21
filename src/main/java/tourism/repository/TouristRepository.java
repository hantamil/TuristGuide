package tourism.repository;

import org.springframework.stereotype.Repository;
import tourism.model.TouristAttraction;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TouristRepository {
    private final ArrayList<TouristAttraction> attractionsList = new ArrayList<>();

    public TouristRepository() {
        attractionsList.add(new TouristAttraction("Tivoli", "Stor forlystelsespark i midten af København.","København", List.of("Forslystelsespark", "Park", "Spisesteder", "Udendørs")));
        attractionsList.add(new TouristAttraction("Den Lille Havfrue", "En havfrue på en sten, fra H. C. Andersens kendte eventyr 'Den lille Havfrue'.", "København", List.of("Havfrue", "H.C. Andersen", "Eventyr", "Udendørs")));
        attractionsList.add(new TouristAttraction("Djurs Sommerland", "Forlystelsespark for børn.", "Nimtofte", List.of("Forlystelsespark", "Børn", "Park", "Udendørs")));
        attractionsList.add(new TouristAttraction("Glyptoteket", "Kunstmuseum i København.","København", List.of("Kunst", "Museum", "Indendørs")));
        attractionsList.add(new TouristAttraction("Bakken", "Danmarks ældste forlystelsespark.","Klampenborg", List.of("Forlystelsespark", "Park", "Udendørs")));
    }

    public TouristAttraction findName(String name){
        for (TouristAttraction touristAttraction : attractionsList){
            if (touristAttraction.getName().contains(name)){
                return touristAttraction;
            }
        }
        return null;
    }

    public TouristAttraction findUrlName(String urlName){
        for (TouristAttraction touristAttraction : attractionsList){
            if (touristAttraction.getUrl().equalsIgnoreCase(urlName)){
                return touristAttraction;
            }
        }
        return null;
    }

    public TouristAttraction getAttractionFromTag(String tag) {
        for (TouristAttraction attractionTag : attractionsList){
            if (attractionTag.getTags().contains(tag)){
                return attractionTag;
            }
        }
        return null;
    }

    public TouristAttraction addAttraction(TouristAttraction attraction){
        attractionsList.add(attraction);
        return attraction;
    }

    public TouristAttraction updateAttraction(TouristAttraction attraction){
        for (int i = 0; i < attractionsList.size(); i++) {
            if (attractionsList.get(i).getName().equalsIgnoreCase(attraction.getName())){
                attractionsList.set(i,attraction);
                return attraction;
            }
        }
        return null;
    }

    public TouristAttraction deleteAttraction(String name){
        TouristAttraction attraction = null;
        for (TouristAttraction touristAttraction : attractionsList){
            if (touristAttraction.getName().equalsIgnoreCase(name)){
                attraction = touristAttraction;
            }
        }
        if (attraction!=null){
            attractionsList.remove(attraction);
        }
        return attraction;
    }





    public ArrayList<TouristAttraction> getAttractionsList() {
        return attractionsList;
    }
}
