package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.services.models.OfferServiceModel;
import menzonev2.example.demo.domain.services.models.SessionUserModel;
import menzonev2.example.demo.web.models.CreateOfferModel;

import java.util.List;

public interface OfferService {

   void createOffer(CreateOfferModel model);

   List<CreateOfferModel> getAllOffersByUser(SessionUserModel user);

   List<OfferServiceModel> getAllTShirtOffersExceptCXs(SessionUserModel user);

   List<OfferServiceModel> getAllShoeOffersExceptCXs(SessionUserModel user);

   List<OfferServiceModel> getAllAccOffersExceptCXs(SessionUserModel user);


   CreateOfferModel getById(Long id);
}
