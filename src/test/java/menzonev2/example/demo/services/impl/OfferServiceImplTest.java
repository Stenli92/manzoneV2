package menzonev2.example.demo.services.impl;
import menzonev2.example.demo.repositories.OfferRepository;
import menzonev2.example.demo.services.OfferService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class OfferServiceImplTest {

    @Autowired
    OfferService offerService;

    @Mock
    OfferRepository mockOfferRepository;



    @Test(expected = NullPointerException.class)
    public void craetingOfferWithInvalidValue_ShouldThrowException() {

        offerService.createOffer(null);
        verify(mockOfferRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void getOffersByInvalidUser_shouldThrowException() {

        offerService.createOffer(null);
        verify(mockOfferRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void getByInvalidId_ShouldThrowException() {

        offerService.getById(null);
        verify(mockOfferRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void getAllShirtsByCx_shouldThrowExcepion() {

        offerService.getAllTShirtOffersExceptCXs(null);
        verify(mockOfferRepository.save(any()));

    }

    @Test(expected = Exception.class)
    public void getAllShoeByCx_shouldThrowExcepion() {

        offerService.getAllShoeOffersExceptCXs(null);
        verify(mockOfferRepository.save(any()));

    }

    @Test(expected = Exception.class)
    public void getAllAccByCx_shouldThrowExcepion() {

        offerService.getAllAccOffersExceptCXs(null);
        verify(mockOfferRepository.save(any()));

    }
}