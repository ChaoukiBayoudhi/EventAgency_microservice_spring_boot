package tn.esb.siad.eventAgency.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esb.siad.eventAgency.Domains.Event;
import tn.esb.siad.eventAgency.Repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    //the service layer is used to implement the business logic
    //the service need to access the repository layer to access the data
    EventRepository eventRepository;
    //we need to inject the repository layer in the service layer
    //we can use constructor injection or setter injection
    @Autowired
    //This annotation is used to tell the spring framework to inject
    // the repository layer in the service layer
    //the eventRepository object will be created by the spring framework
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }
    //List all events method
    //this method will return a list of events
    public List<Event> getAllEvents(){
        return eventRepository.findAll();
    }
    //Add an event by id
    //this method will return an event by id
    //Optional<Event> is used to avoid null pointer exception
    //Optional contains one or no object
    //Optional<Event> result=eventRepository.findById(id);
    //if(result.isPresent()) return result.get();



    public Event getEventById(Long id){
        return eventRepository.findById(id).orElseThrow();
    }
    //or
    //public Event getEventById(Long id){
    //Optional<Event> result=eventRepository.findById(id);
    //if(result.isPresent()) return result.get();
    //else throw new Exception("Event not found");
    //}
    //Add an event
    //this method will add an event
    //we use save method to add an event
    //save method will return the added event
    //Event e=new Event(1,"event1","event1 description");
    //eventRepository.save(e);=>this will add the event e
    //in SQL : insert into event values(1,"event1","event1 description");
    //save(e) is used to insert or update an entity
    //Event e=eventRepository.getEventById(1);
    //e.setName("event1 updated");
    ///...
    //eventRepository.save(e);=>this will update the event e
    //in SQL update event set name="event1 updated" where id=1;
    //add a new event
    //Example of use of Stream and Lambda expressions
    //print the square of the even integers
    //Steam.of(1,11,3,4,5,6,7,8,9,10)
    // .filter(x->x%2==0)
    // .map(x->x*x)
    // .forEach(System.out::println);
    //if we to return the values in a List
    //List<Integer> lst1=Steam.of(1,11,3,4,5,6,7,8,9,10)
    // .filter(x->x%2==0)
    // .map(x->x*x)
    // .collect(Collectors.toList());
    public Event addEvent(Event e1) throws IllegalStateException{
        //verify if the event is already in the database
        /*eventRepository.findAll().stream() //convert from List to Stream
                .filter(e->e.equals(e1)).findFirst().ifPresent(e->{
            throw new IllegalStateException("Event already exists");
        });*/
        //or
        List<Event> events = eventRepository.findAll();
        int i=0;
        while(i<events.size()){
            if(events.get(i).equals(e1)){
                throw new IllegalStateException("Event already exists");
            }
            i++;
        }
        return eventRepository.save(e1);
    }
//Update an event
    public Event updateEvent(Event event,Long id) throws IllegalStateException{
        Event e=eventRepository.findById(id).orElseThrow();
        e.setName(event.getName());
        e.setDescription(event.getDescription());
        e.setTheme(event.getTheme());
        e.setDate(event.getDate());
        //....
        return eventRepository.save(e);
    }
    //Delete an event
    public void deleteEvent(Long id){
        eventRepository.deleteById(id);
    }
}
