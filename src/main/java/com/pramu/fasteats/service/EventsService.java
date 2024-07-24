package com.pramu.fasteats.service;

import com.pramu.fasteats.model.Event;

import java.util.List;

public interface EventsService {

    public Event createEvent(Event event, Long restaurantId) throws Exception;

    public List<Event> findAllEvent();

    public List<Event> findRestaurantsEvent(Long id);

    public void deleteEvent(Long id) throws Exception;

    public Event findById(Long id) throws Exception;

}
