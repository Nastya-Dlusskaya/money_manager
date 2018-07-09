package by.epam.manager.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User {

    @Id
    private int id;

    @NotNull(message = "Surname is empty")
    @Size(min=6, max=15, message = "Length of surname between 6 and 15")
    private String surname;

    @NotNull(message = "Name is empty")
    @Size(min=3, max=18, message = "Length of name between 3 and 18")
    private String name;

    @NotNull(message = "Login is empty")
    @Size(min=6, max=8, message = "Length of login between 6 and 8")
    private String login;

    @NotNull(message = "Password is empty")
    @Size(min=6, max=8, message = "Length of password between 6 and 8")
    private String password;

    public User() {
    }

    public User(String surname, String name, String login, String password) {
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(int id, String surname, String name, String login, String password) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }

        if (object == null || getClass() != object.getClass()){
            return false;
        }

        User user = (User) object;
        return id == user.id &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(name, user.name) &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, surname, name, login, password);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
