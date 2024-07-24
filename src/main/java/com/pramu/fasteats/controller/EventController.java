package com.pramu.fasteats.controller;

import com.pramu.fasteats.model.Event;
import com.pramu.fasteats.response.MessageResponse;
import com.pramu.fasteats.service.EventsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EventController {

    @Autowired
    public EventsService eventService;

    @PostMapping("/admin/event/restaurant/{restaurantId}")
    public ResponseEntity<Event> createEvents(@RequestBody Event event,
                                              @PathVariable Long restaurantId) throws Exception{
        Event createdEvents=eventService.createEvent(event, restaurantId);
        return new ResponseEntity<>(createdEvents, HttpStatus.ACCEPTED);
    }

    @GetMapping("/events")
    public ResponseEntity<List<Event>> findAllEvents() throws Exception{
        List<Event> events=eventService.findAllEvent();
        return new ResponseEntity<>(events,HttpStatus.ACCEPTED);
    }

    @GetMapping("/admin/event/restaurant/{restaurantId}")
    public ResponseEntity<List<Event>> findRestaurantsEvents(
            @PathVariable Long restaurantId) throws Exception{
        List<Event> events=eventService.findRestaurantsEvent(restaurantId);
        return new ResponseEntity<>(events,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/admin/event/{id}")
    public ResponseEntity<MessageResponse> deleteEvents(
            @PathVariable Long id) throws Exception{
        eventService.deleteEvent(id);
        MessageResponse messageResponse = new MessageResponse();
        messageResponse.setMessage("id " + id + " Event deleted");
        return new ResponseEntity<>(messageResponse,HttpStatus.OK);
    }

}