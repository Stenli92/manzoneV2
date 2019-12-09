package menzonev2.example.demo.repositories;

import menzonev2.example.demo.domain.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role , Long> {

    Role findByAuthority(String authority);

}
