package menzonev2.example.demo.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table
public class Offer extends BaseEntity {


    private String name;
    private String type;
    private BigDecimal price;
    private String size;
    private String color;
    private Integer quantity = 1;
    private User user;

    public Offer() {
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

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Column(nullable = false)
    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Column(nullable = false)
    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id" , referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
