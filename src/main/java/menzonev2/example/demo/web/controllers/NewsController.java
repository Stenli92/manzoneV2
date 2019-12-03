package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.services.models.NewsServiceModel;
import menzonev2.example.demo.services.NewsService;
import menzonev2.example.demo.web.models.CreateNewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

        List <NewsServiceModel> sorted = allNews.stream().sorted((a , b) ->{

            LocalDateTime first = a.getDate();
            LocalDateTime second= b.getDate();

           return second.compareTo(first);

        }).collect(Collectors.toList());

        model.addAttribute("allNews" , sorted);

        return "/news/news.html";
    }

    @GetMapping("/sport-news")
    public String sportNews(Model model){

        List<NewsServiceModel> sportNews = this.newsService.getNewsByTopic("Sport");

        List <NewsServiceModel> sorted = sportNews.stream().sorted((a , b) ->{

            LocalDateTime first = a.getDate();
            LocalDateTime second= b.getDate();

            return second.compareTo(first);

        }).collect(Collectors.toList());

        model.addAttribute("sportNews" , sorted);

        return "/news/sport-news.html";
    }

    @GetMapping("/music-news")
    public String musicNews(Model model){

        List<NewsServiceModel> musicNews = this.newsService.getNewsByTopic("Music");

        List <NewsServiceModel> sorted = musicNews.stream().sorted((a , b) ->{

            LocalDateTime first = a.getDate();
            LocalDateTime second= b.getDate();

            return second.compareTo(first);

        }).collect(Collectors.toList());
        model.addAttribute("musicNews" , sorted);

        return "/news/music-news.html";
    }


    @GetMapping("/news/{id}")
    public String newsTopic(@PathVariable("id") Long id , Model model){

        NewsServiceModel news = this.newsService.getNewsById(id);




        model.addAttribute("news" , news.getText());
        model.addAttribute("date" , news.getDate());
        model.addAttribute("newsTitle" , news.getTitle());



        return "/news/current-news.html";
    }

}
