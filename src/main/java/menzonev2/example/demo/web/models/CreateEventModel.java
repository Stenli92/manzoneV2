package menzonev2.example.demo.web.models;


import javax.validation.constraints.*;

public class CreateEventModel {

    private Long id;

    @NotEmpty(message = "Field cannot be empty")
    @Size(min = 3 , max = 15 , message = "Name should have between 3 and 15 digits")
    private String name;

    @NotEmpty(message = "Field cannot be empty")
    private String type;

    @NotEmpty(message = "Field cannot be empty")
    @Size(min = 2 , max = 20 , message = "Location name should have between 2 and 20 symbols")
    @Pattern(regexp = "^[a-zA-Z\\s]*$" , message = "Field should contain only letters")
    private String location;

    @NotEmpty(message = "Please select event date")
    private String date;

    @NotEmpty(message = "Please select event time")
    private String time;


    @Min(value = 1 , message = "Price cannot be zero or a negative number")
    @NotNull(message = "Field cannot be empty")
    private Integer price;

    public CreateEventModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
