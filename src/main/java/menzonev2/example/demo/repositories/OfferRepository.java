package menzonev2.example.demo.repositories;

import menzonev2.example.demo.domain.entities.Offer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfferRepository extends JpaRepository<Offer , Integer> {

    Offer save(Offer offer);

    List<Offer> findAllByType(String type);
}
