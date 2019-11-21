package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.News;
import menzonev2.example.demo.domain.services.models.NewsServiceModel;
import menzonev2.example.demo.repositories.NewsRepository;
import menzonev2.example.demo.services.NewsService;
import menzonev2.example.demo.web.models.CreateNewsModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceModelImpl implements NewsService {

    private final NewsRepository newsRepository;
    private final ModelMapper maper;

    @Autowired
    public NewsServiceModelImpl(NewsRepository newsRepository, ModelMapper maper) {
        this.newsRepository = newsRepository;
        this.maper = maper;
    }


    @Override
    public List<NewsServiceModel> getNewsByTopic(String topic) {

        List<News> news = this.newsRepository.findAllByTopic(topic);
        return news.stream().map(n -> this.maper.map(n , NewsServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void createNews(CreateNewsModel model) {

        this.newsRepository.save(this.maper.map(model , News.class));
    }

    @Override
    public List<NewsServiceModel> getAll() {

        List<News> news = this.newsRepository.findAll();

        return news.stream().map(n -> this.maper.map(n , NewsServiceModel.class)).collect(Collectors.toList());

    }
}
