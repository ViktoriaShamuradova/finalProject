package by.javatr.dao.factory;

import by.javatr.dao.BookDao;
import by.javatr.dao.UserDao;
import by.javatr.dao.impl.BookDaoImpl;
import by.javatr.dao.impl.UserDaoImpl;

public class DaoFactory {

    private static final DaoFactory INSTANCE = new DaoFactory();

    private static final UserDao USER_DAO_INSTANCE = UserDaoImpl.getInstance();
    private static final BookDao BOOK_DAO_INSTANCE = BookDaoImpl.getInstance();

    private DaoFactory() {
    }

    public static DaoFactory getInstance() {
        return INSTANCE;
    }

    public UserDao getUserDAO() {
        return USER_DAO_INSTANCE;
    }

    public BookDao getBookDao() {
        return BOOK_DAO_INSTANCE;
    }
}
