package by.javatr.controller.command.impl.user;

import by.javatr.bean.Login;
import by.javatr.bean.Password;
import by.javatr.bean.User;
import by.javatr.controller.command.CommandSignInRegistration;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.UserService;
import by.javatr.service.factory.ServiceFactory;
import by.javatr.service.valid.ValidLogin;
import by.javatr.service.valid.ValidPassword;
import by.javatr.service.exception.ServiceException;
import by.javatr.view.View;

public class RegistrationClient implements CommandSignInRegistration {

    @Override
    public User execute() throws ControllerException {
        View view = View.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        ValidPassword validPassword=ValidPassword.getInstance();
        ValidLogin validLogin = ValidLogin.getInstance();

        User newAccount;
        String loginStr = view.enterLogin();
        Login newLogin = new Login(loginStr);
        try {
            if (!userService.isThereSuchLogin(newLogin)) {
                if (validLogin.isValidLogin(newLogin)) {
                    String[] passwords = view.enterNewPassword();
                    if (passwords[0].equals(passwords[1])) {
                        Password password = new Password(passwords[0]);
                        if (validPassword.isValidPassword(password)) {
                            try {
                                newAccount = userService.createAccountClient(newLogin, password);
                                view.printMessage("Account created successfully");
                                return newAccount;
                            } catch (ServiceException s) {
                                throw new ControllerException(s);
                            }
                        }
                        view.printMessage("Password is not valid. Please, try again");
                        return null;
                    }
                    view.printMessage("Passwords are not equals, please, try again");
                    return null;
                }
                view.printMessage("Login is not valid. Please, try again");
                return null;

            } else {
                view.printMessage("This login is already in use");
                return null;
            }
        }catch (ServiceException e){
            throw new ControllerException(e);
        }
    }
}