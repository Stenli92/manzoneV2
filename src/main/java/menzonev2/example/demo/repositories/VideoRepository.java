package menzonev2.example.demo.repositories;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends JpaRepository <Video , Integer> {

    List<Video> findAllByType(String type);

    List<Video> findAllByUser(User user);

    Video findById(Long id);
}
