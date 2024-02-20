package tourism.controller;

import org.springframework.ui.Model;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(path = "/opret")
    public ResponseEntity<TouristAttraction> postAttraction(@RequestBody TouristAttraction attraction) {
        TouristAttraction addAttraction = service.postAttraction(attraction);
        return new ResponseEntity<TouristAttraction>(addAttraction, HttpStatus.OK);
    }

    @PutMapping(path = "/{name}/ret")
    public ResponseEntity<TouristAttraction> putAttraction(@RequestBody TouristAttraction attraction) {
        TouristAttraction updateAttraction = service.putAttractions(attraction);
        if (updateAttraction!=null)
            return new ResponseEntity<TouristAttraction>(updateAttraction, HttpStatus.OK);
        else
            return new ResponseEntity<TouristAttraction>(new TouristAttraction(null, "Ikke fundet"), HttpStatus.NOT_FOUND);
    }


    @DeleteMapping("/{name}/slet")
    public ResponseEntity<TouristAttraction> deleteAttraction(@RequestBody String name) {
        TouristAttraction deleteAttraction = service.deleteAttraction(name);
        return new ResponseEntity<TouristAttraction>(deleteAttraction, HttpStatus.OK);
    }
}
