package tourism.controller;

import org.springframework.ui.Model;
import tourism.model.TouristAttraction;
import tourism.service.TouristService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("attractions") //localhost:8080/attractions
public class TouristController {
    private TouristService service;

    public TouristController(TouristService service){
        this.service = service;
    }

    @GetMapping("")
    public String getWelcomeSite(){
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
    public ResponseEntity<String> getSpecificAttraction(@PathVariable String name, String description) {

        ArrayList attraction = service.getAllAttractions(); //Viser alle i listen - vi skal have kun name + beskrivelse for hvert enkelte i listen.
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("Content-Type","text/html");

        return new ResponseEntity<String>(
                "<html><body><h1>" +
                        attraction +
                        "</h1></body></html>"
                ,responseHeaders, HttpStatus.OK);
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
