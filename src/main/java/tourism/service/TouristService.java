package tourism.service;

import tourism.model.TouristAttraction;
import tourism.repository.TouristRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TouristService {

    private final TouristRepository repository;

    public TouristService() {
        repository = new TouristRepository();
    }

    public ArrayList<TouristAttraction> getAllAttractions()  {
        return repository.getAttractionsList();
    }

    public TouristAttraction getTags(String tags) {
        return repository.getTags(tags);
    }

    public TouristAttraction postAttraction(TouristAttraction attraction) {
        return  repository.addAttraction(attraction);
    }

    public TouristAttraction putAttractions(TouristAttraction attraction) {
        return repository.updateAttraction(attraction);
    }

    public TouristAttraction deleteAttraction(String name){
        return repository.deleteAttraction(name);

    }

    public TouristAttraction findName(String name){
        return repository.findName(name);
    }
    public TouristAttraction findUrlName(String urlName){
        return repository.findUrlName(urlName);
    }

}
