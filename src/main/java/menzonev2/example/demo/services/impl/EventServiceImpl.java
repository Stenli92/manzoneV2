package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.Event;
import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.repositories.EventRepository;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.EventService;
import menzonev2.example.demo.web.models.CreateEventModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final ModelMapper mapper;
    private final EventRepository eventRepository;
    private final HttpServletRequest request;
    private final UserRepository userRepository;


    @Autowired
    public EventServiceImpl(ModelMapper mapper, EventRepository eventRepository, HttpServletRequest request, UserRepository userRepository) {
        this.mapper = mapper;
        this.eventRepository = eventRepository;
        this.request = request;
        this.userRepository = userRepository;
    }


    @Override
    public void createEvent(CreateEventModel model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = this.userRepository.findByUsername(auth.getName()).orElse(null);

        Event event = this.mapper.map(model , Event.class);

        event.setUser(user);

        this.eventRepository.save(event);

    }



    @Override
    public List<CreateEventModel> getAllUserEvents(User user) {

        HttpSession session = request.getSession(true);

        List<Event> models = this.eventRepository.findAllByUser(user);

        return models.stream().map(m -> this.mapper.map(m , CreateEventModel.class)).collect(Collectors.toList());
    }

    @Override
    public CreateEventModel getByID(Long id) {
        return this.mapper.map(this.eventRepository.getById(id) , CreateEventModel.class);
    }
}
