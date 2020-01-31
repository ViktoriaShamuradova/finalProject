package by.javatr.bean;

import java.io.Serializable;

public class Password implements Serializable {

    private String password;

    public Password(String password) {
        this.password = password;
    }

    public Password(){};

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return " password= " + password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Password password1 = (Password) o;
        if (null == password) {
            return (password.equals(password1.getPassword()));
        } else {
            return password.equals(password1.getPassword());
        }
    }

    @Override
    public int hashCode() {
        return 31*password.hashCode();
    }
}
