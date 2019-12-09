package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.services.models.RoleServiceModel;

import java.util.Set;

public interface RoleService {

    void seedRolesInDB();

    RoleServiceModel finByAuthority(String role);

    Set<RoleServiceModel> findAllRoles();
}
