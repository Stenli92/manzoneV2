package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.web.models.CreateOfferModel;

import java.util.List;

public interface OfferService {

   void createOffer(CreateOfferModel model);

   List<CreateOfferModel> getAllOffersByUser(User user);
}
