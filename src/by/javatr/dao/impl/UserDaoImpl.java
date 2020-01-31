package by.javatr.dao.impl;

import by.javatr.bean.Login;
import by.javatr.bean.User;
import by.javatr.dao.UserDao;
import by.javatr.dao.exception.DaoException;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final UserDaoImpl instance = new UserDaoImpl();
    private static final String fileOfUsers = "User.bin";

    private List<User> listOfUsers;
    private Writer writer;
    private Reader reader;

    private UserDaoImpl() {
        writer = Writer.getInstance();
        reader = Reader.getInstance();
        try {
            listOfUsers = reader.readUsersFromFile(fileOfUsers);
        } catch (DaoException e) {
            listOfUsers = null;
        }
    }

    public static UserDaoImpl getInstance() {
        return instance;
    }

    @Override
    public void saveUser(User user) throws DaoException {
        listOfUsers.add(user);
        writeUsersToFile(listOfUsers, fileOfUsers);
    }

    @Override
    public void saveListUsers(List<User> users) throws DaoException {
        listOfUsers = users;
        writeUsersToFile(listOfUsers, fileOfUsers);
    }

    @Override
    public User giveUser(Login login) {
        for (User user : listOfUsers) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> giveAllUsers() {
        return new ArrayList<>(listOfUsers);
    }

    private void writeUsersToFile(List<User> listOfUsers, String fileName) throws DaoException {
            writer.writeUsersToFile(listOfUsers, fileName);
    }
}
