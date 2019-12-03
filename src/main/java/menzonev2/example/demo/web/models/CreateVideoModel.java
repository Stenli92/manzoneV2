package menzonev2.example.demo.web.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class CreateVideoModel {

    private static final String YOUTUBE_REGEX = "(?:https?:\\/\\/)?(?:www\\.)?youtu\\.?be(?:\\.com)?" +
            "\\/?.*(?:watch|embed)?(?:.*v=|v\\/|\\/)([\\w\\-_]+)\\&?";

    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 3 , max = 15 , message = "Title should contain between 3 and 15 digits")
    private String title;

    @NotEmpty(message = "Link cannot be empty")
    @Pattern(regexp = YOUTUBE_REGEX , message = "The platform supports only youtube videos")
    private String url;

    private String type;

    public CreateVideoModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
