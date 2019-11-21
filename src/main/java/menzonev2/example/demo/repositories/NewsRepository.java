package menzonev2.example.demo.repositories;

import menzonev2.example.demo.domain.entities.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News, Integer> {

    List<News> findAllByTopic(String topic);
}
