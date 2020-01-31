package by.javatr.bean;

import java.io.Serializable;

public class Login implements Serializable {

    private String login;

    public Login(String login){
        this.login=login;
    }

    public Login(){}

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return login;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Login login1 = (Login) o;
        return login.equals(login1.getLogin());
    }

    @Override
    public int hashCode() {
        return 31*login.hashCode();
    }
}
