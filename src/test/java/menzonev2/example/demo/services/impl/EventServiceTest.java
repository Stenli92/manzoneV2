package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.repositories.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class EventServiceTest {

    EventRepository eventRepository;

    @BeforeEach
    void setUp() {

        eventRepository = Mockito.mock(EventRepository.class);


    }

    @Test
    void createEvent() {
    }

    @Test
    void getAllUserEvents() {
    }

    @Test
    void getByID() {
    }
}