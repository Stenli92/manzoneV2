package menzonev2.example.demo.repositories;

import menzonev2.example.demo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameAndPassword(String username , String password);

    boolean existsByUsername(String name);

    User findByUsername(String username);

    User findById(String id);

}