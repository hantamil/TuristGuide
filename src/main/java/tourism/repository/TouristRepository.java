package tourism.repository;

import org.springframework.stereotype.Repository;
import tourism.model.TouristAttraction;

import java.util.ArrayList;

@Repository
public class TouristRepository {
    private ArrayList<TouristAttraction> attractionsList = new ArrayList<>();

    public TouristRepository() {
        attractionsList.add(new TouristAttraction("Tivoli", "Stor forlystelsespark i midten af København."));
        attractionsList.add( new TouristAttraction("Den Lille Havfrue", "En havfrue på en sten, fra H. C. Andersens kendte eventyr 'Den lille Havfrue'."));
        attractionsList.add(new TouristAttraction("Djurs Sommerland", "Forlystelsespark for små børn."));
        attractionsList.add(new TouristAttraction("Glyptoteket", "Kunstmuseum i København."));
        attractionsList.add(new TouristAttraction("Bakken", "Danmarks ældste forlystelsespark."));
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

    public ArrayList getAttractionsList() {
        return attractionsList;
    }
}
