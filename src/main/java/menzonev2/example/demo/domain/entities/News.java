package menzonev2.example.demo.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table
public class News extends BaseEntity {


    private LocalDateTime date = LocalDateTime.now();
    private String title;
    private String topic;
    private String text;
    private User user;

    public News() {
    }

    @Column
    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
    @Size(max = 10000)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

}
