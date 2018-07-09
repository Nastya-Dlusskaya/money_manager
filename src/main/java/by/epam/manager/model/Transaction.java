package by.epam.manager.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="transaction")
public class Transaction {

    @Id
    private int id;

    @NotNull(message = "User is empty")
    private int user;

    private Date date;

    @NotNull
    private String type;

    @NotNull(message = "Capital is empty")
    @Digits(integer = 5, fraction = 3, message = "Wrong capital")
    private BigDecimal capital;

    @NotNull(message = "Sum is empty")
    @Digits(integer = 5, fraction = 3, message = "Wrong sum")
    private BigDecimal sum;

    @NotNull(message = "Description is empty")
    private String description;

    public Transaction() {
    }

    public Transaction(int user, String description, Date date, String typeOperation, BigDecimal capital, BigDecimal sum) {
        this.user = user;
        this.description = description;
        this.date = date;
        this.type = typeOperation;
        this.capital = capital;
        this.sum = sum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int idUser) {
        this.user = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String typeOperation) {
        this.type = typeOperation;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public BigDecimal getSum() {
        return sum;
    }

    public void setSum(BigDecimal sum) {
        this.sum = sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                user == that.user &&
                type == that.type &&
                Objects.equals(description, that.description) &&
                Objects.equals(date, that.date) &&
                Objects.equals(capital, that.capital) &&
                Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, user, description, date, type, capital, sum);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", idUser=" + user +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", typeOperation=" + type +
                ", capital=" + capital +
                ", sum=" + sum +
                '}';
    }
}
