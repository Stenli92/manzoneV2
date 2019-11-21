package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.web.models.CreateEventModel;

import java.util.List;

public interface EventService {

    void createEvent(CreateEventModel model);

    List<CreateEventModel> getAllUserEvents(User user);
}
