package tn.esb.siad.eventAgency.Services;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import tn.esb.siad.eventAgency.Domains.Event;
import tn.esb.siad.eventAgency.Repositories.EventRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
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
    //scheduling : execute a method at a specific time or periodically
    //we can use @Scheduled annotation to schedule a method
    //we can use @Scheduled with fixedDelay, fixedRate, cron
    //fixedDelay is used to execute a method periodically with a fixed delay
    //fixedRate is used to execute a method periodically with a fixed rate
    //cron is used to execute a method at a specific time
    //cron is a string that contains 6 or 7 fields separated by space
    //the first 5 fields are used to specify the time
    //the 6th field is used to specify the day of the week
    //the 7th field is optional and is used to specify the year
    //the fields are:
    //1- seconds (0-59)
    //2- minutes (0-59)
    //3- hours (0-23)
    //4- day of month (1-31)
    //5- month (1-12 or JAN-DEC)
    //6- day of week (1-7 or SUN-SAT)
    //7- year (optional)
    //examples:
    //1- @Scheduled(fixedDelay = 1000) => execute the method every 1000 milliseconds
    //2- @Scheduled(fixedRate = 1000) => execute the method every 1000 milliseconds
    //3- @Scheduled(cron = "0 0 12 * * ?") => execute the method every day at 12:00
    //4- @Scheduled(cron = "30 0 12 * * ") => execute the method every 30 seconds at 12:00
    //5- @Scheduled(cron = "15/60 * * ") => execute the method every 15 seconds (15,30,45,00)

    //6- @Scheduled(cron = "0 0 12 * * MON-FRI") => execute the method every day at 12:00 from Monday to Friday
    //7- @Scheduled(cron = "30 0 12 * * MON-FRI") => execute the method every   30 seconds at 12:00 from Monday to Friday
    //8- @Scheduled(cron = "0 0 12 * * 2021") => execute the method every day at 12:00 in 2021
    //9- @Schedule(cron = "15 30 11 7 3 2024) => execute the method every 15 seconds at 11:30 on 7 March 2024 (Thursday)
    //run the method at the 3 week of each month of 2024 between 12:20 and 15:50
    //@Scheduled(cron = "0 20-50/30 12 ? 3 2024 3W")
    //10- @Scheduled(cron = "0 0 12 ? * 3L") => execute the method every day at 12:00 on the last Thursday of each month
    //11- @Scheduled(cron = "0/30 * * * * ?")=> execute the method every 30 seconds (0,30), the ? is used to specify that the day of the month is not important

    //we can use @Scheduled with void methods that have no parameters
    //log events that occur after the system date each 30 seconds use log.info, use @slf4j annotation of lombok
    @Scheduled(fixedDelay = 30 * 1000)
    public void logEvents(){
            List<Event> lstRes=eventRepository.findAll().stream()
            .filter(e->e.getDate().isAfter(LocalDate.now()))
                    .collect(Collectors.toList());
        log.info("Events after the system date : {}");
        for(Event e : lstRes) {
            Hibernate.initialize(e.getOrganizers());
            log.info(e.toString());
        }
    }

    //


}
