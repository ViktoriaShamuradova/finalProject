package by.javatr.controller.command.impl.user;

import by.javatr.bean.User;
import by.javatr.controller.command.CommandSignInRegistration;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.UserService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;
import by.javatr.view.View;

public class SignIn implements CommandSignInRegistration {

    @Override
    public User execute() throws ControllerException{
        View view = View.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        String[] loginAndPassword = view.enterSignIn();
        User user = null;
        try {
            user = userService.signIn(loginAndPassword[0], loginAndPassword[1]);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        if (user == null) {
            view.printMessage("Login or password is wrong");
            return null;
        } else return user;
    }
}
