package com.hansol.restfulwebservice.event.controller;

import com.hansol.restfulwebservice.event.*;
import com.hansol.restfulwebservice.event.repository.EventRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
@RequestMapping(value = "/api/events", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class EventController {

    private final EventRepository eventRepository;

    private final ModelMapper modelMapper;

    private final EventValidator eventValidator;

    public EventController(EventRepository eventRepository, ModelMapper modelMapper, EventValidator eventValidator) {
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
        this.eventValidator = eventValidator;
    }

    @PostMapping
    public ResponseEntity createEvent(@RequestBody @Valid EventDto eventDto, Errors errors) {
        // check empty input
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        // check wrong input
        eventValidator.validate(eventDto, errors);
        if(errors.hasErrors()) {
            return ResponseEntity.badRequest().body(errors);
        }

        Event event = modelMapper.map(eventDto, Event.class);
        event.update();
        Event newEvent = this.eventRepository.save(event);
        ControllerLinkBuilder selfLinkBuilder = linkTo(EventController.class).slash("{id}");
        URI createUri = selfLinkBuilder.toUri();

        EventResourceBundle eventResource = new EventResourceBundle(event);
//        EventResource eventResource = new EventResource(event);

        eventResource.add(linkTo(EventController.class).withRel("query-events"));
//        eventResource.add(selfLinkBuilder.withSelfRel());
        eventResource.add(selfLinkBuilder.withRel("update-event"));
        return ResponseEntity.created(createUri).body(eventResource);

    }
}
