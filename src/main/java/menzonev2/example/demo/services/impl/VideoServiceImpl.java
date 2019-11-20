package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.domain.entities.Video;
import menzonev2.example.demo.repositories.VideoRepository;
import menzonev2.example.demo.services.URLTrimService;
import menzonev2.example.demo.services.VideoService;
import menzonev2.example.demo.web.models.CreateVideoModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class VideoServiceImpl implements VideoService {

    private final VideoRepository videoRepository;
    private final ModelMapper mapper;
    private final URLTrimService urlTrim;


    public VideoServiceImpl(VideoRepository videoRepository, ModelMapper mapper, URLTrimService urlTrim) {
        this.videoRepository = videoRepository;
        this.mapper = mapper;
        this.urlTrim = urlTrim;
    }

    @Override
    public void upload(CreateVideoModel model) {

        String link = this.urlTrim.trimURL(model.getUrl());

        model.setUrl(link);

        this.videoRepository.save(this.mapper.map(model , Video.class));
    }
}
