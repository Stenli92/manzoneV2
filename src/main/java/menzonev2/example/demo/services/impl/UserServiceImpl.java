package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.Event;
import menzonev2.example.demo.domain.entities.Offer;
import menzonev2.example.demo.domain.entities.Role;
import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.UserServiceModel;
import menzonev2.example.demo.errors.CheckForUsernameNameException;
import menzonev2.example.demo.repositories.EventRepository;
import menzonev2.example.demo.repositories.OfferRepository;
import menzonev2.example.demo.repositories.RoleRepository;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.RoleService;
import menzonev2.example.demo.services.UserService;
import menzonev2.example.demo.services.ValidationService;
import menzonev2.example.demo.web.models.CartModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ModelMapper mapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ValidationService authValidationService;
    private final OfferRepository offerRepository;
    private final EventRepository eventRepository;
    private final RoleService roleService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository,
                           ModelMapper mapper, BCryptPasswordEncoder passwordEncoder,
                           ValidationService authValidationService, OfferRepository offerRepository, EventRepository eventRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.authValidationService = authValidationService;
        this.offerRepository = offerRepository;
        this.eventRepository = eventRepository;
        this.roleService = roleService;
    }


    @Override
    public String register(UserServiceModel userServiceModel) {



        User user = this.mapper.map(userServiceModel, User.class);
        if (this.userRepository.count() == 0) {

            this.roleService.seedRolesInDB();

            user.setAuthorities(new LinkedHashSet<>(this.roleRepository.findAll()));
        } else {
            user.setAuthorities(new HashSet<>(Set.of(this.roleRepository.findByAuthority("USER"))));
        }

        user.setPassword(this.passwordEncoder.encode(userServiceModel.getPassword()));
        this.userRepository.saveAndFlush(user);
        return "redirect: /login";
    }

//    @Override
//    public String login(LoginUserServiceModel user) {
//        String passwordHash = passwordEncoder.encode(user.getPassword());
//
//        User userToLog = this.userRepository.findByUsernameAndPassword(user.getUsername() , passwordHash).orElse(null);
//
//        if (userToLog == null){
//
//            return "/auth/login";
//        }
//
//        String insertedPass = DigestUtils.sha256Hex(user.getUsername());
//
//        if(!insertedPass.equals(userToLog.getPassword())){
//            return "/auth/login";
//
//
//        }
//        return "/home/home";
//    }

    public UserServiceModel getUser(String username) {

         User user = this.userRepository.findByUsername(username).orElse(null);

        return this.mapper.map(user , UserServiceModel.class);
    }


    public boolean checkIfUserExists(String username) {

        return this.userRepository.existsByUsername(username);

    }

    public void updateUserBalance(String username, Integer balance) {
        User user = this.userRepository.findByUsername(username).orElse(null);

        user.setBalance(balance);

        this.userRepository.save(user);
    }

    public void removeUser(UserServiceModel user) {

        User userToRemove = this.userRepository.findByUsername(user.getUsername()).orElse(null);

        this.userRepository.delete(userToRemove);


    }

    public void updateSellerBalance(List<CartModel> cart) {
        List<User> userList = this.userRepository.findAll();

        UserServiceModel seller = new UserServiceModel();


        for (CartModel cartModel : cart) {

            if (cartModel.getType().equals("Football")
                    ||cartModel.getType().equals("Concert")  ){

                for (User user : userList) {


                    List<Event> events = user.getEvents();

                    for (Event event : events) {

                        if (event.getName().equals(cartModel.getName())){

                            user.setBalance((int) (user.getBalance() + cartModel.getPrice()));

                            this.userRepository.save(user);
                        }

                    }


                }

            }else {

                for (User user : userList) {


                    List<Offer> offers = user.getOffers();

                    for (Offer offer : offers) {

                        if (offer.getName().equals(cartModel.getName())){

                            user.setBalance((int) (user.getBalance() + cartModel.getPrice()));

                            this.userRepository.save(user);
                        }

                    }


                }
            }


        }

    }

    public int cartAmount(List<CartModel> cart) {

        int amount = 0;
        for (CartModel cartModel : cart) {

            amount+= cartModel.getPrice();

        }

        return amount;
    }

    @Override
    public User getUserFromContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        User user = this.userRepository.findByUsername(auth.getName()).orElse(null);

        return user;

    }

    public void updateBuyerBalance(int amount , User user) {

        int balance = user.getBalance() - amount;

        user.setBalance(balance);

        this.userRepository.save(user);

    }

        @Override
    public void updateToAdmin(UserServiceModel user) {

        User userToUpdate = this.userRepository.findByUsername(user.getUsername())
                .orElseThrow(()->new CheckForUsernameNameException("No such user found"));

        UserServiceModel userServiceModel = this.mapper.map(userToUpdate , UserServiceModel.class);

            userServiceModel.getAuthorities().clear();
            userServiceModel.getAuthorities().add(this.roleService.finByAuthority("USER"));
            userServiceModel.getAuthorities().add(this.roleService.finByAuthority("ADMIN"));
            System.out.println();

            this.userRepository.saveAndFlush(this.mapper.map(userServiceModel , User.class));


    }


    public List<UserServiceModel> getAllUsers() {

        List<User> allUsers = this.userRepository.findAll();

        return allUsers.stream().map(e-> this.mapper.map(e , UserServiceModel.class)).collect(Collectors.toList());

    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return this.userRepository.findByUsername(username).orElse(null);

    }
}
