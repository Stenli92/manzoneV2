package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.Role;
import menzonev2.example.demo.domain.services.models.RoleServiceModel;
import menzonev2.example.demo.repositories.RoleRepository;
import menzonev2.example.demo.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedRolesInDB() {

        if(this.roleRepository.count() == 0){

            this.roleRepository.saveAndFlush(new Role("USER"));
            this.roleRepository.saveAndFlush(new Role("ADMIN"));
            this.roleRepository.saveAndFlush(new Role("ROOT"));
        }

    }

    @Override
    public RoleServiceModel finByAuthority(String role) {
        return this.modelMapper.map(this.roleRepository.findByAuthority(role) , RoleServiceModel.class);
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        return this.roleRepository.findAll()
                .stream().map(e -> this.modelMapper.map(e , RoleServiceModel.class))
                .collect(Collectors.toSet());
    }
}
