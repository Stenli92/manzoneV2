package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.Offer;
import menzonev2.example.demo.repositories.OfferRepository;
import menzonev2.example.demo.services.OfferService;
import menzonev2.example.demo.web.models.CreateOfferModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.constraints.Size;
import java.util.List;

@Controller
@RequestMapping("/store")
public class StoreController {

    private final OfferService offerService;
    private final OfferRepository offerRepository;

    public StoreController(OfferService offerService, OfferRepository offerRepository) {
        this.offerService = offerService;
        this.offerRepository = offerRepository;
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
    public String createOfferConfirm(@ModelAttribute CreateOfferModel model){

        System.out.println();

        this.offerService.createOffer(model);

        return "redirect:/store/offer-index";
    }


    @GetMapping("/tshirts")
    public String tshirts(Model model){

        List<Offer> offers = this.offerRepository.findAllByType("T-Shirt");

        model.addAttribute("offers" , offers);

        return "/offer/tshirts.html";
    }

    @GetMapping("/shoes")
    public String shoes(Model model){

        List<Offer> offers = this.offerRepository.findAllByType("Shoe");

        model.addAttribute("offers" , offers);

        return "/offer/shoes.html";
    }

    @GetMapping("/accessories")
    public String accessories(Model model){

        List<Offer> offers = this.offerRepository.findAllByType("Accessory");

        model.addAttribute("offers" , offers);

        return "/offer/accessories.html";
    }
}
