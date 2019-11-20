package menzonev2.example.demo.domain.entities;

import javax.persistence.*;

@Entity
@Table
public class News extends BaseEntity {


    private String title;
    private String topic;
    private String text;
    private User user;

    public News() {
    }

    @Column(nullable = false)
    public String getTitle() {
        return title;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    @Column(nullable = false)
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    @Column(nullable = false)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
