package by.javatr.controller.command.impl.user;

import by.javatr.bean.Login;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.UserService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;
import by.javatr.view.View;

public class DeleteAdmin implements CommandExecute {

    @Override
    public String execute() throws ControllerException{
        View view = View.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();
        String loginStr = view.enterAdminLogin();
        Login login = new Login(loginStr);
        try {
            userService.deleteUser(login);
        }catch (ServiceException e){
            throw new ControllerException(e);
        }
        return "well removed";
    }
}

