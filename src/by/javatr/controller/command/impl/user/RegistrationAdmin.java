package by.javatr.controller.command.impl.user;

import by.javatr.bean.Login;
import by.javatr.bean.Password;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.UserService;
import by.javatr.service.factory.ServiceFactory;
import by.javatr.service.valid.ValidLogin;
import by.javatr.service.valid.ValidPassword;
import by.javatr.service.exception.ServiceException;
import by.javatr.view.View;

public class RegistrationAdmin implements CommandExecute {

    @Override
    public String execute() throws ControllerException {
        View view = View.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        ValidLogin validLogin=ValidLogin.getInstance();
        ValidPassword validPassword=ValidPassword.getInstance();

        String newLogin = view.enterLogin();
        Login login = new Login(newLogin);
        try {
            if (validLogin.isValidLogin(login)) {
                if (userService.isThereSuchLogin(login)) return "Such login is already in use";
                String[] passwords = view.enterNewPassword();
                if (passwords[0].equals(passwords[1])) {
                    Password password = new Password(passwords[0]);
                    if (validPassword.isValidPassword(password)) {
                        try {
                            userService.createAccountAdmin(login, password);
                        } catch (ServiceException s) {
                            throw new ControllerException(s);
                        }
                        return ("Account created successfully");
                    }
                    return "Password are not valid. Please, try to register again";
                }
                return "Passwords are not equals. Try again";
            }
            return "Please, enter valid login";
        }catch (ServiceException e){
            throw new ControllerException(e);
        }
    }
}