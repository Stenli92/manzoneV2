package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.Video;
import menzonev2.example.demo.repositories.VideoRepository;
import menzonev2.example.demo.services.VideoService;
import menzonev2.example.demo.web.models.CreateVideoModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/videos")
public class VideoController {

    private final ModelMapper mapper;
    private final VideoService videoService;
    private final VideoRepository videoRepository;

    public VideoController(ModelMapper mapper, VideoService videoService, VideoRepository videoRepository) {
        this.mapper = mapper;
        this.videoService = videoService;
        this.videoRepository = videoRepository;
    }

    @GetMapping("/videos")
    public String videos(){


        return "/videos/videos.html";
    }

    @GetMapping("/music-videos")
    public String musicVideos(Model model){

        List<Video> videos = this.videoRepository.findAllByType("Music");

        model.addAttribute("videos" , videos);

        return "/videos/music-videos.html";
    }

    @GetMapping("/sport-videos")
    public String sportVideos(Model model){

        List<Video> videos = this.videoRepository.findAllByType("Sport");

        model.addAttribute("videos" , videos);

        return "/videos/sport-videos.html";
    }


    @GetMapping("/uploadvideo")
    public String uploadVideo(){

        return "/videos/upload-video.html";
    }

    @PostMapping("/uploadvideo")
    public String uploadVideoConfirm(@ModelAttribute CreateVideoModel model){


        this.videoService.upload(model);

        return "redirect:/videos/videos";
    }

}
