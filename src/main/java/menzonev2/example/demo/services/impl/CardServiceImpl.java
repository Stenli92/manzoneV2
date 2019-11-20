package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.CreditCard;
import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.repositories.CCardRepository;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.services.CardService;
import menzonev2.example.demo.web.models.CreateCreditCardServiceModel;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.modelmapper.ModelMapper;
import org.springframework.security.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.jsf.FacesContextUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class CardServiceImpl implements CardService {

    private final CCardRepository cardRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    public CardServiceImpl(CCardRepository cardRepository, UserRepository userRepository, ModelMapper mapper) {
        this.cardRepository = cardRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }


    @Override
    public void createCard(CreateCreditCardServiceModel cardModel) {

        this.cardRepository.save(this.mapper.map(cardModel , CreditCard.class));
    }
}
