package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.entities.Video;
import menzonev2.example.demo.domain.services.models.SessionUserModel;
import menzonev2.example.demo.domain.services.models.VideoServiceModel;
import menzonev2.example.demo.web.models.CreateVideoModel;

import java.util.List;

public interface VideoService {

    void upload(CreateVideoModel model);

    List<VideoServiceModel> getAllVideosByUser(SessionUserModel user);

    List<VideoServiceModel> getAllVideosForSport();

    List<VideoServiceModel> getAllVideosForMusic();

    boolean videoIsPresentInDB(CreateVideoModel model);

    void deleteVideo(Long id);
}
