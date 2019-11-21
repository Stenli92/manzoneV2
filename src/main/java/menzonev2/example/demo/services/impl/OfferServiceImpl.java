package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.Offer;
import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.repositories.OfferRepository;
import menzonev2.example.demo.services.OfferService;
import menzonev2.example.demo.web.models.CreateOfferModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper mapper;
    private final HttpServletRequest request;

    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper mapper, HttpServletRequest request) {
        this.offerRepository = offerRepository;
        this.mapper = mapper;
        this.request = request;
    }

    @Override
    public void createOffer(CreateOfferModel model) {

        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute("user");

        Offer offer = this.mapper.map(model, Offer.class);

        offer.setUser(user);

        this.offerRepository.save(offer);

    }

    @Override
    public List<CreateOfferModel> getAllOffersByUser(User user) {

        List<Offer> offers = this.offerRepository.findAllByUser(user);

        return offers.stream().map(o -> this.mapper.map( o , CreateOfferModel.class)).collect(Collectors.toList());
    }
}
