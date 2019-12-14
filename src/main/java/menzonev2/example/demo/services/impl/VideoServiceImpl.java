package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.entities.Video;
import menzonev2.example.demo.domain.services.models.SessionUserModel;
import menzonev2.example.demo.domain.services.models.VideoServiceModel;
import menzonev2.example.demo.repositories.UserRepository;
import menzonev2.example.demo.repositories.VideoRepository;
import menzonev2.example.demo.services.URLTrimService;
import menzonev2.example.demo.services.VideoService;
import menzonev2.example.demo.web.models.CreateVideoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;
    private final URLTrimService urlTrim;
    private final HttpServletRequest request;


    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository, ModelMapper mapper, UserRepository userRepository, URLTrimService urlTrim, HttpServletRequest request) {
        this.videoRepository = videoRepository;
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.urlTrim = urlTrim;
        this.request = request;
    }

    @Override
    public void upload(CreateVideoModel model) {



        HttpSession session = request.getSession(true);

        SessionUserModel userToFind = (SessionUserModel) session.getAttribute("user");


        model.setUrl(this.urlTrim.trimURL(model.getUrl()));

        Video video = this.mapper.map(model , Video.class);

        User user = this.userRepository.findById(userToFind.getId());

        video.setUser(user);

        this.videoRepository.save(video);
    }

    @Override
    public boolean videoIsPresentInDB(CreateVideoModel model) {

        String link = this.urlTrim.trimURL(model.getUrl());

        List<Video> videos = this.videoRepository.findAll();

        for (Video video : videos) {

            if(link.equals(video.getUrl())){

                return false;
            }


        }


        return true;
    }

    @Override
    public List<VideoServiceModel> getAllVideosByUser(SessionUserModel user) {

        List<Video> videos = this.videoRepository.findAllByUser(this.mapper.map(user , User.class));

        return videos.stream().map(v -> this.mapper.map(v , VideoServiceModel.class)).collect(Collectors.toList());
    }

    @Override
    public void deleteVideo(Long id) {

        HttpSession session = request.getSession(true);

        SessionUserModel userToFind = (SessionUserModel) session.getAttribute("user");

        Video video = this.videoRepository.findById(id);

//
        this.videoRepository.delete(video);
//        Video video = this.videoRepository.findByUrl(url);

//        List<Video> videos =
//
//        for (Video userVideo : videos) {
//
//
//            if (userVideo.getUrl().equals(url)){
//
//                user.getVideos().remove(userVideo);
//
//            }
//
//        }


    }

    @Override
    public List<VideoServiceModel> getAllVideosForSport() {

        List<Video> videos = this.videoRepository.findAllByType("Sport");
        return videos.stream().map(e -> this.mapper.map(e , VideoServiceModel.class)).collect(Collectors.toList());


    }

    @Override
    public List<VideoServiceModel> getAllVideosForMusic() {

        List<Video> videos = this.videoRepository.findAllByType("Music");
        return videos.stream().map(e -> this.mapper.map(e , VideoServiceModel.class)).collect(Collectors.toList());
    }


}
