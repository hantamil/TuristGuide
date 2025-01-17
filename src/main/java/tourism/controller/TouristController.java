package tourism.controller;

import org.springframework.ui.Model;
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

        // service.getAttractionFromTag(tag);
        return "tags.html";
    }

    @GetMapping(path = "/create")
    public String createAttractionPage(Model model) {
        List<Tags> tagsList = new ArrayList<>(Arrays.asList(Tags.values())); //alle tags

        model.addAttribute("cityList", service.getCitylist());
        model.addAttribute("tags", tagsList);
        model.addAttribute("touristAttraction", new TouristAttraction()); //det her skal være der
        return "create";
    }
    @PostMapping(path = "/create")
    public String postAttraction(@ModelAttribute TouristAttraction touristAttraction){
        service.addAttraction(touristAttraction);
        return "redirect:/attractions";
    }

    @GetMapping("/{name}/edit")
    public String editAttraction(Model model, @PathVariable String name) { //Skal fikses ligesom create
        List<String> cityList = service.getCitylist();
        List<Tags> tagsList = new ArrayList<>(Arrays.asList(Tags.values())); //alle tags

        model.addAttribute("touristAttraction" ,service.findUrlName(name));
        model.addAttribute("cityList",cityList);
        model.addAttribute("tags", tagsList);
        return "update";
    }

    @PostMapping(path = "/update")
    public String updateAttraction(TouristAttraction attraction) {
        service.updateAttraction(attraction);
        return "redirect:/attractions";
    }

    /*@PostMapping(path = "/update")
    public String updateAttraction(TouristAttraction attraction) {
        TouristAttraction updateAttraction = service.updateAttraction(attraction);
        if (updateAttraction!=null)
            return "redirect:/attractions";
        else {
            return null;
        }
    }*/

    @DeleteMapping("/{name}/delete")
    public String deleteAttraction(@RequestBody String name) {
        TouristAttraction deleteAttraction = service.deleteAttraction(name);
        return "delete.html";
    }
}