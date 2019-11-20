package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.Event;
import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.repositories.EventRepository;
import menzonev2.example.demo.services.EventService;
import menzonev2.example.demo.web.models.CreateEventModel;
import org.modelmapper.ModelMapper;
import org.springframework.security.Authentication;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.security.Principal;

@Service
public class EventServiceImpl implements EventService {

    private final ModelMapper mapper;
    private final EventRepository eventRepository;

    public EventServiceImpl(ModelMapper mapper, EventRepository eventRepository) {
        this.mapper = mapper;
        this.eventRepository = eventRepository;
    }


    @Override
    public void createEvent(CreateEventModel model) {

        this.eventRepository.save(this.mapper.map(model , Event.class));

    }
}
