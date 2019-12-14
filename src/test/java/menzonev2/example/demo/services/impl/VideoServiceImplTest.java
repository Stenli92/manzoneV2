package menzonev2.example.demo.services.impl;

import menzonev2.example.demo.repositories.VideoRepository;
import menzonev2.example.demo.services.VideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
@RunWith(SpringRunner.class)
public class VideoServiceImplTest {
    @Autowired
    VideoService videoService;

    @Mock
    VideoRepository mockedVideoRepository;

    @Test(expected = Exception.class)
    public void uploadVideoWithInvalidValue_shouldThrowException() {

        videoService.upload(null);
        verify(mockedVideoRepository.save(any()));
    }

    @Test
    public void videoIsPresentInDB() {

    }

    @Test(expected = Exception.class)
    public void getVideosByInvalidUser_shouldThrowException() {

        videoService.getAllVideosByUser(null);
        verify(mockedVideoRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void deletingVideoWithInvalidID_shouldThrowException() {

        videoService.deleteVideo(null);
        verify(mockedVideoRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void getAllVideosForSportWithInvalidValue_shouldThrowException() {

        videoService.getAllVideosForSport();
        verify(mockedVideoRepository.save(any()));
    }

    @Test(expected = Exception.class)
    public void getAllVideosForMusicWithInvalidValue_shouldThrowException() {
        videoService.getAllVideosForMusic();
        verify(mockedVideoRepository.save(any()));
    }
}