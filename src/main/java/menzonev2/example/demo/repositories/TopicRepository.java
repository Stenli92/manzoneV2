package menzonev2.example.demo.repositories;

import menzonev2.example.demo.domain.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicRepository extends JpaRepository<News, Integer> {
}
