package menzonev2.example.demo.repositories;

import menzonev2.example.demo.domain.entities.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CCardRepository extends JpaRepository<CreditCard , Integer> {
}
