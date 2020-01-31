package by.javatr.service.impl;

import by.javatr.bean.Login;
import by.javatr.bean.Password;
import by.javatr.bean.User;
import by.javatr.bean.UserType;
import by.javatr.dao.UserDao;
import by.javatr.dao.exception.DaoException;
import by.javatr.dao.factory.DaoFactory;
import by.javatr.service.UserService;
import by.javatr.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserServiceImpl implements UserService {

    private static final UserServiceImpl instance = new UserServiceImpl();
    private UserDao userDao;

    private UserServiceImpl() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        userDao = daoFactory.getUserDAO();
    }

    public static UserServiceImpl getInstance() {
        return instance;
    }


    @Override
    public User signIn(String loginString, String passwordString) throws ServiceException {
        Login login = new Login(loginString);
        User user;
        try {
            user = userDao.giveUser(login);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Password password = new Password(passwordString);
        if (user.getPassword().equals(password)) return user;
        else {
            return null;
        }
    }

    @Override
    public List<User> allAdmins() throws ServiceException {
        List<User> users;
        try {
            users = userDao.giveAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        List<User> admins = new ArrayList<>();
        for (User user : users) {
            if (user.getType() == UserType.ADMIN) {
                admins.add(user);
            }
        }
        return admins;
    }

    @Override
    public List<User> allClients() throws ServiceException {
        List<User> users;
        try {
            users = userDao.giveAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        List<User> clients = new ArrayList<>();
        for (User user : users) {
            if (user.getType() == UserType.USER) {
                clients.add(user);
            }
        }
        return clients;
    }

    @Override
    public User createAccountClient(Login login, Password password) throws ServiceException {
        User user = createUser(login, password, UserType.USER);
        saveUser(user);
        return user;
    }

    @Override
    public User createAccountAdmin(Login login, Password password) throws ServiceException {
        User user = createUser(login, password, UserType.ADMIN);
        saveUser(user);
        return user;
    }

    @Override
    public boolean deleteUser(Login login) throws ServiceException {
        List<User> users;
        try {
            users = userDao.giveAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            if (user.getLogin().equals(login)) {
                iterator.remove();
                try {
                    userDao.saveListUsers(users);
                } catch (DaoException e) {
                    throw new ServiceException(e);
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isThereSuchLogin(Login login) throws ServiceException {
        List<User> allUsers;
        try {
            allUsers = userDao.giveAllUsers();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        for (User user : allUsers) {
            if (user.getLogin().equals(login)) {
                return true;
            }
        }
        return false;
    }

    private User createUser(Login login, Password password, UserType type) {
        return new User(login, password, type);
    }

    private void saveUser(User user) throws ServiceException {
        try {
            userDao.saveUser(user);
        } catch (DaoException d) {
            throw new ServiceException(d);
        }
    }
}
