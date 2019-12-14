package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.repositories.NewsRepository;
import menzonev2.example.demo.services.NewsService;
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
public class NewsServiceImplTest {
    @Autowired
    NewsService newsService;

    @Mock
    NewsRepository mockedNewsRepository;



    @Test(expected = Exception.class)
    public void getNewsByInvalidTopic_shouldThrowIllegalException() {

        newsService.getNewsByTopic(null);
        verify(mockedNewsRepository.save(any()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void creatingNewsWithInvalidValue_shouldThrowIllegalArgEx() {

        newsService.createNews(null);
        verify(mockedNewsRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void findAllNewsWithInvalidValue_shouldThrowAnException() {

        newsService.getAll();
        verify(mockedNewsRepository.save(any()));

    }

    @Test(expected = IllegalArgumentException.class)
    public void getNewsByInvalidId_shouldThrowException() {
        newsService.getNewsById(null);
        verify(mockedNewsRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void deleteNewsByInvalidIdValue_shouldThrowError() {
        newsService.deleteNews(null);
        verify(mockedNewsRepository.save(any()));
    }
}