package menzonev2.example.demo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/news")
public class NewsController {

    @GetMapping("/create-topic")
    public String createEvent(){

        return "/topic/create-topic.html";
    }

    @GetMapping("/news")
    public String news(){

        return "/news/news.html";
    }
}
