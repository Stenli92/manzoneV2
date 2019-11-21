package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.Event;
import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.repositories.EventRepository;
import menzonev2.example.demo.services.EventService;
import menzonev2.example.demo.web.models.CreateEventModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final HttpServletRequest request;




    @Autowired
    public EventController(EventService eventService, ModelMapper modelMapper,
                           EventRepository eventRepository, HttpServletRequest request) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
        this.request = request;
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

    @GetMapping("/myevents")
    public String myEvents(Model model){

        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute("user");


        List<CreateEventModel> events = this.eventService.getAllUserEvents(user);

        model.addAttribute("events" , events);

        return "/event/myevents.html";
    }
}
