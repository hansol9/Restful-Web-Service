package com.hansol.restfulwebservice.event;

import com.hansol.restfulwebservice.event.controller.EventController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class EventResourceBundle extends Resource<Event> {

    public EventResourceBundle(Event event, Link... links) {
        super(event, links);
        add(linkTo(EventController.class).slash(event.getId()).withSelfRel());
//        add(new Link("http://localhost:8080/api/events/" + event.getId()));

    }
}
