package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.services.CardService;
import menzonev2.example.demo.web.models.CreateCreditCardServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/cards")
public class CardController {

    private final CardService cardService;
    private final ModelMapper mapper;

    public CardController(CardService cardService, ModelMapper mapper) {
        this.cardService = cardService;
        this.mapper = mapper;
    }


    @GetMapping("/add-card")
    public String addCard(){

        return "card/add-card.html";
    }

    @PostMapping("/add-card")
    public String AddCard(@ModelAttribute CreateCreditCardServiceModel model) {



            this.cardService.createCard(model);

            return "user/myprofile.html";


    }
}
