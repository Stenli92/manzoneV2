package menzonev2.example.demo.domain.entities;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table
public class User extends BaseEntity  {

    private static final String EMAIL_PATTERN =
            "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private final static Integer MIN_AMOUNT = 100;
    private final static Integer MAX_AMOUNT = 1000;

    private String username;
    private String password;
    private String email;
    private String secretQuestion;
    private String secretAnswer;
    private Integer balance = (int)(Math.random() * ((MAX_AMOUNT - MIN_AMOUNT) + 1)) + MIN_AMOUNT;
    private List<Video> videos ;
    private List<Offer> offers ;
    private List<Event> events ;


    public User() {
    }

    @Column(nullable = false , unique = true)
    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(nullable = false , unique = true)
    @Pattern(regexp= EMAIL_PATTERN)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(nullable = false)
    public String getSecretQuestion() {
        return secretQuestion;
    }

    public void setSecretQuestion(String secretQuestion) {
        this.secretQuestion = secretQuestion;
    }
    @Column(nullable = false)
    public String getSecretAnswer() {
        return secretAnswer;
    }

    public void setSecretAnswer(String secretAnswer) {
        this.secretAnswer = secretAnswer;
    }

    @Column(nullable = false)
    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    @OneToMany(mappedBy = "user")
    public List<Video> getVideos() {
        return videos;
    }

    public void setVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Column(nullable = false)
    @OneToMany(mappedBy = "user")
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Column(nullable = false)
    @OneToMany(mappedBy = "user")
    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }
}
