package tourism.controller;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;
import tourism.model.Tags;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("attractions") //localhost:8080/attractions
public class TouristController {
    private final TouristService service;

    public TouristController(TouristService service){
        this.service = service;
    }

   /* @GetMapping("")
    public String welcomePage(){
        return "index.html";
    }*/
    
    /**SHOW ALL ATTRACTIONS**/
    @GetMapping("")
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

    @GetMapping("/{name}/tags")
    public String getTags(Model model, @PathVariable String name){
        TouristAttraction attraction = service.findUrlName(name);
        List<Tags> attractionTags = attraction.getTags();
        model.addAttribute("tags", attractionTags);
        return "tags.html";
    }

    @GetMapping(path = "/create")
    public String createAttractionPage(Model model) {
        TouristAttraction touristAttraction = new TouristAttraction("","", "", new ArrayList<>());
        model.addAttribute("touristAttraction", touristAttraction);
        return "create.html";
    }
    @PostMapping(path = "/create")
    public String postAttraction(@ModelAttribute("touristAttraction") TouristAttraction touristAttraction){
        service.addAttraction(new TouristAttraction(touristAttraction.getName(), touristAttraction.getDescription(), touristAttraction.getCity(), touristAttraction.getTags()));

        return "redirect:/attractions";
    }

    @PutMapping(path = "/{name}/update")
    public String putAttraction(@RequestBody TouristAttraction attraction) {
        TouristAttraction updateAttraction = service.putAttractions(attraction);
        if (updateAttraction!=null)
            return "update.html";
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND); // virker ikke :c -mici
        }
            //return "HVAD SKAL DER STÅ HERINDE?!??!?!??!?!?!?!?!?!??!?!?!?!?!"; //hJÆLP !
        //den skal ikke returne en html fil tror jeg(lassse)
    }

    @DeleteMapping("/{name}/delete")
    public String deleteAttraction(@RequestBody String name) {
        TouristAttraction deleteAttraction = service.deleteAttraction(name);
        return "delete.html";
    }
}
