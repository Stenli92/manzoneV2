package menzonev2.example.demo.web.controllers;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.entities.Video;
import menzonev2.example.demo.domain.services.models.SessionUserModel;
import menzonev2.example.demo.domain.services.models.VideoServiceModel;
import menzonev2.example.demo.repositories.VideoRepository;
import menzonev2.example.demo.services.VideoService;
import menzonev2.example.demo.web.models.CreateVideoModel;
import menzonev2.example.demo.web.models.RegisterUserServiceModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/videos")
public class VideoController {

    private final ModelMapper mapper;
    private final VideoService videoService;
    private final VideoRepository videoRepository;
    private final HttpServletRequest request;

    @Autowired
    public VideoController(ModelMapper mapper, VideoService videoService, VideoRepository videoRepository, HttpServletRequest request) {
        this.mapper = mapper;
        this.videoService = videoService;
        this.videoRepository = videoRepository;
        this.request = request;
    }

    @ModelAttribute("videoModel")
    public CreateVideoModel registerModel(){

        return new CreateVideoModel();
    }

    @GetMapping("/videos")
    public String videos(){


        return "/videos/videos.html";
    }

    @GetMapping("/music-videos")
    public String musicVideos(Model model){

        List<VideoServiceModel> videos = this.videoService.getAllVideosForMusic();
        model.addAttribute("videos" , videos);

        return "/videos/music-videos.html";
    }

    @GetMapping("/sport-videos")
    public String sportVideos(Model model){

        List<VideoServiceModel> videos = this.videoService.getAllVideosForSport();

        model.addAttribute("videos" , videos);

        return "/videos/sport-videos.html";
    }


    @GetMapping("/uploadvideo")
    public String uploadVideo(){

        return "/videos/upload-video.html";
    }

    @PostMapping("/uploadvideo")
    public String uploadVideoConfirm(@Valid @ModelAttribute("videoModel") CreateVideoModel model , BindingResult result,
    Model errorModel){

        if(result.hasErrors()){

            return "/videos/upload-video.html";
        }

        if (!this.videoService.videoIsPresentInDB(model)){

            errorModel.addAttribute("error" , "This video is already uploaded");

            return "/videos/upload-video.html";

        }

        this.videoService.upload(model);

        return "redirect:/videos/videos";
    }


    @GetMapping("/myvideos")
    public String myVideos(Model model){

        HttpSession session = request.getSession(true);

        SessionUserModel user = (SessionUserModel) session.getAttribute("user");

        List<VideoServiceModel> videos = this.videoService.getAllVideosByUser(user);

        model.addAttribute("videos" , videos);


        return "/videos/myvideos.html";
    }

//    @PostMapping("/delete-video/{id}")
//    public String deleteVideo(@PathVariable("id") Long id){
//
//        System.out.println();
//
//        this.videoService.deleteVideo(id);
//
//        return "redirect:/videos/videos";
//    }

}
