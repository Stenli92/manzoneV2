package menzonev2.example.demo.web.models;

import javax.validation.constraints.*;

public class CreateOfferModel {

    @NotEmpty(message = "Field cannot be empty")
    @Size(min = 3 , max = 15 , message = "Name should have between 3 and 15 digits")
    private String name;

    @NotEmpty(message = "Field cannot be empty")
    private String type;

    @NotEmpty(message = "Field cannot be empty")
    @Pattern(regexp = "^[a-zA-Z0-9]+$" , message = "Size field can contain only letters or numbers")
    private String size;

    @NotEmpty(message = "Field cannot be empty")
    @Size(min = 3 , message = "Color should have between 3 and 15 digits")
    @Pattern(regexp = "^[a-z][a-z\\s]*$" , message = "Field should contain only letters")
    private String color;

    @Min(value = 1 , message = "Price cannot be zero or a negative number")
    @NotNull(message = "Field cannot be empty")
    private Integer price;

    public CreateOfferModel() {
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
