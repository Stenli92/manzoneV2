package menzonev2.example.demo.services;

import menzonev2.example.demo.web.models.CreateCreditCardServiceModel;

import javax.servlet.http.HttpSession;

public interface CardService {

    void createCard(CreateCreditCardServiceModel cardModel);
}
