package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.Event;
import menzonev2.example.demo.domain.entities.Offer;
import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.OfferServiceModel;
import menzonev2.example.demo.domain.services.models.SessionUserModel;
import menzonev2.example.demo.repositories.OfferRepository;
import menzonev2.example.demo.services.OfferService;
import menzonev2.example.demo.services.UserService;
import menzonev2.example.demo.web.models.CreateOfferModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/store")
public class StoreController {

    private final OfferService offerService;
    private final OfferRepository offerRepository;
    private final ModelMapper mapper;
    private final HttpServletRequest request;
    private final UserService userService;

    @Autowired
    public StoreController(OfferService offerService, OfferRepository offerRepository, ModelMapper mapper, HttpServletRequest request, UserService userService) {
        this.offerService = offerService;
        this.offerRepository = offerRepository;
        this.mapper = mapper;
        this.request = request;
        this.userService = userService;
    }

    @ModelAttribute("offerModel")
    public CreateOfferModel registerModel(){

        return new CreateOfferModel();
    }

    @GetMapping("/offer-index")
    public String offerindex(){

        return "/offer/offer-index.html";
    }

    @GetMapping("/create-offer")
    public String createOffer(){

        return "/offer/submit-offer.html";
    }

    @PostMapping("/create-offer")
    public String createOfferConfirm(@Valid @ModelAttribute("offerModel") CreateOfferModel model , BindingResult result){

        if (result.hasErrors()){

            return "/offer/submit-offer.html";

        }

        this.offerService.createOffer(model);

       this.userService.getUserFromContext().getOffers().add(this.mapper.map(model , Offer.class));



        return "redirect:/store/offer-index";
    }


    @GetMapping("/tshirts")
    public String tshirts(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        SessionUserModel user = this.mapper.map(this.userService.getUser(auth.getName()) , SessionUserModel.class);

        List<OfferServiceModel> offers = this.offerService.getAllTShirtOffersExceptCXs(user);

        System.out.println();
        model.addAttribute("offers" , offers);

        return "/offer/tshirts.html";
    }

    @GetMapping("/shoes")
    public String shoes(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        SessionUserModel user = this.mapper.map(this.userService.getUser(auth.getName()) , SessionUserModel.class);

        List<OfferServiceModel> offers = this.offerService.getAllShoeOffersExceptCXs(user);

        System.out.println();
        model.addAttribute("offers" , offers);

        return "/offer/tshirts.html";
    }

    @GetMapping("/accessories")
    public String accessories(Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        SessionUserModel user = this.mapper.map(this.userService.getUser(auth.getName()) , SessionUserModel.class);

        List<OfferServiceModel> offers = this.offerService.getAllAccOffersExceptCXs(user);

        model.addAttribute("offers" , offers);

        return "/offer/tshirts.html";
    }

    @GetMapping("/my-offers")
    public String myOffers(Model model){

        HttpSession session = request.getSession(true);

        SessionUserModel user = (SessionUserModel) session.getAttribute("user");

        List<CreateOfferModel> offers = this.offerService.getAllOffersByUser(user);

        model.addAttribute("offers" , offers);

        return "/offer/my-offers.html";
    }

    @GetMapping("/my-orders")
    public String myOrders(){

        return "/order/my-orders.html";
    }
}
