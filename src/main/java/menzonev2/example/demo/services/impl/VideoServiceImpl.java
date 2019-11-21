package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.domain.entities.Video;
import menzonev2.example.demo.repositories.VideoRepository;
import menzonev2.example.demo.services.URLTrimService;
import menzonev2.example.demo.services.VideoService;
import menzonev2.example.demo.web.models.CreateVideoModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final ModelMapper mapper;
    private final URLTrimService urlTrim;
    private final HttpServletRequest request;


    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository, ModelMapper mapper, URLTrimService urlTrim, HttpServletRequest request) {
        this.videoRepository = videoRepository;
        this.mapper = mapper;
        this.urlTrim = urlTrim;
        this.request = request;
    }

    @Override
    public void upload(CreateVideoModel model) {

        HttpSession session = request.getSession(true);

        User user = (User) session.getAttribute("user");

        String link = this.urlTrim.trimURL(model.getUrl());

        model.setUrl(link);

        Video video = this.mapper.map(model , Video.class);

        video.setUser(user);

        this.videoRepository.save(video);
    }

    @Override
    public List<CreateVideoModel> getAllVideosByUser(User user) {

        List<Video> videos = this.videoRepository.findAllByUser(user);

        return videos.stream().map(v -> this.mapper.map(v , CreateVideoModel.class)).collect(Collectors.toList());
    }
}
