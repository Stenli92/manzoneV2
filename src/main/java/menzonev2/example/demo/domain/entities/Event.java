package menzonev2.example.demo.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Event extends BaseEntity{

    private String name;
    private String type;
    private String location;
    private String date;
    private String time;
    private BigDecimal price;
    private User user;

    public Event() {
    }

    @Column(name = "event_name" , nullable = false , unique = true)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "event_type" , nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @Column(name = "location" , nullable = false)
    public String getLocation() {
        return location;
    }

    @Column(nullable = false)
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    @Column(name = "starts_at" , nullable = false )
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Column(name = "price" , nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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
