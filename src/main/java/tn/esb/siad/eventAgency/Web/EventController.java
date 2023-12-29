package tn.esb.siad.eventAgency.Web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esb.siad.eventAgency.Domains.Event;
import tn.esb.siad.eventAgency.Services.EventService;

import javax.validation.Valid;
import java.util.List;
@RequestMapping("/api/events")
@CrossOrigin("http://localhost:4200")
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
    //to invoke this method we need to send a GET request to the url /
    //url ="http://localhost:9995/api/events/
    @GetMapping("/")
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
    //to send values to the API we can use one of the following annotations:
    //@RequestParam is used to map the request parameters to the method parameters
    //example if we want to pass x=5 and y=7 in the url we can use the following url:
    //http://localhost:9995/sum?x=5&y=7
    //we can't use @RequestParam with sensitive data like passwords, ... and with large data
    //we can use @RequestParam with GET.
    //@RequestBody is used to map the request body to the method parameter
    //we can use @RequestBody with POST, PUT, PATCH
    //we can use @RequestBody to send sensitive data and large data like files, objects, images, ...

    @PostMapping("/event")
    //@Valid is used to tell the API to validate the request body
    //if the request body is not valid the API will return a 400 Bad Request error
    public Event addEvent(@RequestBody @Valid Event event){
        return eventService.addEvent(event);
    }
    //update an event method
    @PutMapping("/event/{id}")
    public Event updateEvent(@PathVariable Long id,@RequestBody @Valid Event event){
        return eventService.updateEvent(event,id);
    }
    //delete an event method
    @DeleteMapping("/event/{id}")
    public void deleteEvent(@PathVariable Long id){
        eventService.deleteEvent(id);
    }
//call the scheduled service

    //call the scheduled service
//    @GetMapping("/event/unfinished/{id}/{date}")
//    public List<Event> getUnfinishedEventsByOrganizerAndDate(@PathVariable Long id,@PathVariable String date){
//        return eventService.getUnfinishedEventsByOrganizerAndDate(id,date);
//    }
    //call the scheduled service logEvents() method
    @GetMapping("/event/scheduled")
    private void callScheduledServiceLogEvents()
    {
        eventService.logEvents();
    }
}
