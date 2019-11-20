package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.Offer;
import menzonev2.example.demo.repositories.OfferRepository;
import menzonev2.example.demo.services.OfferService;
import menzonev2.example.demo.web.models.CreateOfferModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelMapper mapper;

    public OfferServiceImpl(OfferRepository offerRepository, ModelMapper mapper) {
        this.offerRepository = offerRepository;
        this.mapper = mapper;
    }

    @Override
    public void createOffer(CreateOfferModel model) {

        this.offerRepository.save(this.mapper.map(model, Offer.class));

    }
}
