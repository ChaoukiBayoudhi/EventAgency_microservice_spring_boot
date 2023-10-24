package tn.esb.siad.eventAgency.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esb.siad.eventAgency.Domains.Event;
import tn.esb.siad.eventAgency.Repositories.EventRepository;

import java.util.List;

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


}
