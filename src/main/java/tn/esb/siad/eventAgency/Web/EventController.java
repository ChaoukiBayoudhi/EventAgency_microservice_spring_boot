package tn.esb.siad.eventAgency.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import tn.esb.siad.eventAgency.Domains.Event;
import tn.esb.siad.eventAgency.Services.EventService;

import java.util.List;

@RestController
//@RestController is a convenience annotation that is itself annotated with @Controller and @ResponseBody
//we use @RestController instead of @Controller because we want to return a json response (REST API)
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }
    //The REST API is a web service that uses HTTP requests to GET, PUT, POST and DELETE data
    //spring-boot annotations:
    // @GetMapping is used to map HTTP GET requests onto specific handler methods (select)
    //we can use @GetMapping to send request parameters in the url
    // @PostMapping is used to map HTTP POST requests onto specific handler methods (insert, add resource)
    // @PutMapping is used to map HTTP PUT requests onto specific handler methods (complete update)
    // @PatchMapping is used to map HTTP PATCH requests onto specific handler methods (partial update)
    // @DeleteMapping is used to map HTTP DELETE requests onto specific handler methods (delete)

    //get all events method
    //to invoke this method we need to send a GET request to the url /events
    @GetMapping("/events")
    //to get all events we invoke the service using the url :
    //http://localhost:8080/events (by default the port is 8080)
    //in our case the url is http://localhost:9995/events
    public List<Event> getAllEvents(){
        return eventService.getAllEvents();
    }
    //get event by id method
    @GetMapping("/event/{id}")
    //to invoke this method we need to send a GET request to the url /event/{id}
    //the {id} is a path variable that can be replaced by an integer value like 1, 11, 5
    //@PathVariable is used to map the path variable to the method parameter
    //@PathVariable is used to tell the API that the value of the id is passed through the path
    public Event getEvent(@PathVariable Long id){
        return eventService.getEventById(id);
    }
    //...
}
