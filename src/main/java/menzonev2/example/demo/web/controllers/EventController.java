package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.Event;
import menzonev2.example.demo.repositories.EventRepository;
import menzonev2.example.demo.services.EventService;
import menzonev2.example.demo.web.models.CreateEventModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;


    public EventController(EventService eventService, ModelMapper modelMapper, EventRepository eventRepository) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
    }

    @GetMapping("/create-event")
    public String createEvent(){

        return "/event/create-event.html";
    }

    @GetMapping("/events")
    public String events(Model model){


        List<Event> events = this.eventRepository.findAll();

        model.addAttribute("events" , events);

        return "/event/events.html";
    }

    @PostMapping("/create-event")
    public String createEventCommit(@ModelAttribute CreateEventModel model){



        this.eventService.createEvent(model);

        return "redirect:/events/events";
    }
}
