package menzonev2.example.demo.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Event extends BaseEntity {

    private String name;
    private String type;
    private BigDecimal price;
    private String location;
    private String date;
    private Integer quantity = 1;
    private String time;
    private User user;

    public Event() {
    }


    @Column(nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Column(nullable = false)
    public BigDecimal getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false)
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


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
