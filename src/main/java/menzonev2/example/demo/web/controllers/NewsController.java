package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.services.models.NewsServiceModel;
import menzonev2.example.demo.services.NewsService;
import menzonev2.example.demo.web.models.CreateNewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/news")
public class NewsController {

    private final NewsService newsService;

    @Autowired
    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/create-news")
    public String createEvent(){

        return "/news/create-news.html";
    }


    @PostMapping("/create-news")
    public String createEventConfirm(@ModelAttribute CreateNewsModel model){


        this.newsService.createNews(model);

        return "redirect:/news/news";
    }

    @GetMapping("/news")
    public String news(Model model){

        List<NewsServiceModel> allNews = this.newsService.getAll();

        model.addAttribute("allNews" , allNews);

        return "/news/news.html";
    }

    @GetMapping("/sport-news")
    public String sportNews(Model model){

        List<NewsServiceModel> sportNews = this.newsService.getNewsByTopic("Sport");

        model.addAttribute("sportNews" , sportNews);

        return "/news/sport-news.html";
    }

    @GetMapping("/music-news")
    public String musicNews(Model model){

        List<NewsServiceModel> musicNews = this.newsService.getNewsByTopic("Music");

        model.addAttribute("musicNews" , musicNews);

        return "/news/music-news.html";
    }
}
