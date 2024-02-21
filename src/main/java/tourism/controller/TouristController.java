package tourism.controller;

import org.springframework.ui.Model;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("attractions") //localhost:8080/attractions
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service){
        this.service = service;
    }

    @GetMapping("")
    public String welcomePage(){
        return "index.html";
    }
    
    /**SHOW ALL ATTRACTIONS**/
    @GetMapping("/all")
    public String allAttractions(Model model){
        List<TouristAttraction> attractionList = service.getAllAttractions();
        model.addAttribute("attractions", attractionList);
        return "attractions.html";
    }

    /** SHOW ATTRACTION NAME AND DESCRIPTION**/
    @GetMapping(path = "/{name}")
    public String getSpecificAttraction(Model model, @PathVariable String name) {
        TouristAttraction attraction = service.findUrlName(name);

        model.addAttribute("attraction", attraction);
        return "attraction.html";
    }

    @GetMapping("/{name}/tags") //TODO - de forskellige kategorier
    public String getTags(Model model, @PathVariable String tags){
        TouristAttraction attractionTags = service.getAttractionFromTag(tags);
        model.addAttribute("tags", attractionTags);
        return "tags.html";
    }

    @PostMapping(path = "/create")
    public String postAttraction(TouristAttraction attraction) {
        TouristAttraction addAttraction = service.postAttraction(attraction);
        return "create.html";
    }

    @PutMapping(path = "/{name}/update")
    public String putAttraction(@RequestBody TouristAttraction attraction) {
        TouristAttraction updateAttraction = service.putAttractions(attraction);
        if (updateAttraction!=null)
            return "update.html";
        else
            return "HVAD SKAL DER STÅ HERINDE?!??!?!??!?!?!?!?!?!??!?!?!?!?!"; //hJÆLP !
    }


    @DeleteMapping("/{name}/delete")
    public String deleteAttraction(@RequestBody String name) {
        TouristAttraction deleteAttraction = service.deleteAttraction(name);
        return "delete.html";
    }
}
