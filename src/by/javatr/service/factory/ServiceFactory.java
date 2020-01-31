package by.javatr.service.factory;

import by.javatr.service.BookService;
import by.javatr.service.UserService;
import by.javatr.service.impl.BookServiceImpl;
import by.javatr.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private static final UserService userServiceInstance = UserServiceImpl.getInstance();
    private static final BookService bookServiceInstance = BookServiceImpl.getInstance();

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() {
        return userServiceInstance;
    }

    public BookService getBookService() {
        return bookServiceInstance;
    }
}
