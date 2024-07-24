package com.pramu.fasteats.service;

import com.pramu.fasteats.model.Event;
import com.pramu.fasteats.model.Restaurant;
import com.pramu.fasteats.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventsService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private RestaurantService restaurantService;

    @Override
    public Event createEvent(Event event, Long restaurantId) throws Exception {
        Restaurant restaurant=restaurantService.findRestaurantById(restaurantId);

        Event createdEvent=new Event();
        createdEvent.setRestaurant(restaurant);
        createdEvent.setImage(event.getImage());
        createdEvent.setStartedAt(event.getStartedAt());
        createdEvent.setEndsAt(event.getEndsAt());
        createdEvent.setLocation(event.getLocation());
        createdEvent.setName(event.getName());

        return eventRepository.save(createdEvent);
    }

    @Override
    public List<Event> findAllEvent() {
        return eventRepository.findAll();
    }

    @Override
    public List<Event> findRestaurantsEvent(Long id) {
        return eventRepository.findEventsByRestaurantId(id);
    }

    @Override
    public void deleteEvent(Long id) throws Exception {
        Event event=findById(id);
        eventRepository.delete(event);
    }

    @Override
    public Event findById(Long id) throws Exception {
        Optional<Event> opt=eventRepository.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        }
        throw new Exception(id + "event not found");
    }
}
