package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.services.models.SessionUserModel;
import menzonev2.example.demo.repositories.EventRepository;
import menzonev2.example.demo.services.EventService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class EventServiceTest {

    @Autowired
    private EventService eventService;

    @Mock
    EventRepository mockEventRepository;


    @Test(expected = NullPointerException.class)
    public void createEventWithInvalidValue_shouldThrowException() {

        eventService.createEvent(null);
        verify(mockEventRepository.save(any()));


    }

    @Test(expected = Exception.class)
    public void getAllEventsByUserWithInvalidValue_ThrowException() {
        SessionUserModel userModel = new SessionUserModel();

        eventService.getAllUserEvents(userModel);
        verify(mockEventRepository.save(any()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void getUserWithInvalidId_ShouldThrowNullpointer() {

        eventService.getByID(null);
        mockEventRepository.save(any());
    }
}