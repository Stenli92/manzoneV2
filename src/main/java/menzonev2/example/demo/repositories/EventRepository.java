package menzonev2.example.demo.repositories;

import menzonev2.example.demo.domain.entities.Event;
import menzonev2.example.demo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event , Integer> {

    List<Event> findAllByUser(User user);
    Event getById(Long id);
}
