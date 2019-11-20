package menzonev2.example.demo.domain.entities;

import javax.persistence.*;

@Entity
@Table
public class Event extends BaseEntity{

    private String name;
    private EventType type;
    private String location;
    private String time;
    private Integer price;
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

    @Enumerated(EnumType.STRING)
    @Column(name = "event_type" , nullable = false)
    public EventType getType() {
        return type;
    }

    public void setType(EventType type) {
        this.type = type;
    }
    @Column(name = "location" , nullable = false)
    public String getLocation() {
        return location;
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
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

}
