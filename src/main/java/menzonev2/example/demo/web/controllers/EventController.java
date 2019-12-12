package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.Event;
import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.repositories.EventRepository;
import menzonev2.example.demo.services.EventService;
import menzonev2.example.demo.services.UserService;
import menzonev2.example.demo.web.models.CreateEventModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/events")
public class EventController {

    private final EventService eventService;
    private final ModelMapper modelMapper;
    private final EventRepository eventRepository;
    private final UserService userService;
    private final HttpServletRequest request;




    @Autowired
    public EventController(EventService eventService, ModelMapper modelMapper,
                           EventRepository eventRepository, UserService userService, HttpServletRequest request) {
        this.eventService = eventService;
        this.modelMapper = modelMapper;
        this.eventRepository = eventRepository;
        this.userService = userService;
        this.request = request;
    }

    @ModelAttribute("eventModel")
    public CreateEventModel eventModel(){

        return new CreateEventModel();
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

    @GetMapping(value = "/ajax/events", produces = "application/json")
    @ResponseBody
    public List<CreateEventModel> AjaxEvents(){

//        List<Event> eventList = this.eventRepository.findAll();
//
//        List<Event> events = new ArrayList<>();
//
//        for (Event event : eventList) {
//            events.add(event);
//        }

        return this.eventRepository.findAll().stream()
                .map(e-> this.modelMapper.map(e , CreateEventModel.class)).collect(Collectors.toList());
    }



    @PostMapping("/create-event")
    public String createEventCommit(@Valid @ModelAttribute("eventModel") CreateEventModel model , BindingResult result){


        if (result.hasErrors()){

            return "/event/create-event.html";
        }

        this.eventService.createEvent(model);



        this.userService.getUserFromContext().getEvents().add(this.modelMapper.map(model , Event.class));
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
