package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.services.models.NewsServiceModel;
import menzonev2.example.demo.web.models.CreateNewsModel;

import java.util.List;

public interface NewsService {

    List<NewsServiceModel> getNewsByTopic(String topic);

    void createNews(CreateNewsModel model);

    List<NewsServiceModel> getAll();
}
