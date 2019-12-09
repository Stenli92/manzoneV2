package menzonev2.example.demo.repositories;

import menzonev2.example.demo.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsernameAndPassword(String username , String password);

    boolean existsByUsername(String name);

    Optional<User> findByUsername(String username);

    User findById(Long id);



//    List<User> findAllByRole(String role);

}
