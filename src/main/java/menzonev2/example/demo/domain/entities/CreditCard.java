package menzonev2.example.demo.domain.entities;

import javax.persistence.*;
import java.math.BigDecimal;


@Entity
@Table(name = "credit_cards")
public class CreditCard extends BaseEntity {

    private final static Integer MIN_AMOUNT = 100;
    private final static Integer MAX_AMOUNT = 1000;

    private String cardHolder;
    private String cardNumber;
    private BigDecimal balance = BigDecimal.valueOf((Math.random() * ((MAX_AMOUNT - MIN_AMOUNT) + 1)) + MIN_AMOUNT);

    public CreditCard() {
    }

    @Column(name = "card_holder" , nullable = false)
    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }
    @Column(name = "card_number" , nullable = false)
    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    @Column(nullable = false)
    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


}
