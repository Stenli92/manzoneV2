package menzonev2.example.demo.services;

import menzonev2.example.demo.domain.entities.User;
import menzonev2.example.demo.web.models.CreateVideoModel;

import java.util.List;

public interface VideoService {

    void upload(CreateVideoModel model);

    List<CreateVideoModel> getAllVideosByUser(User user);
}
