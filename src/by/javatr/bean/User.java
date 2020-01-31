package by.javatr.bean;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable { //каждое поле должно быть сериалезуемо
    private Login login;                           //transient означает, что поле должо быть проигнорировано при сериализации
    private Password password;
    private UserType type;

    public User() {
    }

    public User(Login login, Password password, UserType type) {
        this.login = login;
        this.password = password;
        this.type = type;
    }

    public void setLogin(Login login) {
        this.login = login;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public Login getLogin() {
        return login;
    }

    public Password getPassword() {
        return password;
    }

    public UserType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        if(!login.equals(user.getLogin()))return false;
        if(!password.equals(user.getPassword()))return false;
        if(!type.equals(user.getType()))return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, type);
    }

    @Override
    public String toString() {
        return "User{" +
                "login=" + login +
                ", password=" + password +
                ", type='" + type + '\'' +
                '}';
    }
}