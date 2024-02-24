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
import java.util.Arrays;
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
        TouristAttraction touristAttraction = new TouristAttraction();
        List<Tags> tagsList = new ArrayList<>(Arrays.asList(Tags.values())); //alle tags
        model.addAttribute("cityList", service.getCitylist());
        model.addAttribute("tags", tagsList);
        model.addAttribute("touristAttraction", touristAttraction);
        return "create";
    }
    @PostMapping(path = "/create")
    public String postAttraction(@ModelAttribute("touristAttraction") TouristAttraction touristAttraction){
        service.addAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String editAttraction() {
    return "update.html";
    }

    @PostMapping(path = "/update")
    public String updateAttraction(TouristAttraction attraction) {
        TouristAttraction updateAttraction = service.updateAttraction(attraction);
        if (updateAttraction!=null)
            return "update.html";
        else {
            return null; // virker ikke :c -mici
        }
        //"HVAD SKAL DER STÅ HERINDE?!??!?!??!?!?!?!?!?!??!?!?!?!?!"; //hJÆLP !
        //den skal ikke returne en html fil tror jeg(lassse)
    }

    @DeleteMapping("/{name}/delete")
    public String deleteAttraction(@RequestBody String name) {
        TouristAttraction deleteAttraction = service.deleteAttraction(name);
        return "delete.html";
    }
}
