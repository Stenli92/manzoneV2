package menzonev2.example.demo.domain.entities;

import javax.persistence.*;

@Entity
@Table
public class Video extends BaseEntity {


    private String title;
    private String url;
    private String type;
    private User user;


    public Video() {
    }

    @Column(nullable = false )
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(nullable = false , unique = true)
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Column(nullable = false )
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
