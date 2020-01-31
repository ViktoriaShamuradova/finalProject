package by.javatr.controller.command.impl.user;

import by.javatr.bean.User;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.UserService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;

import java.util.List;

public class AllAdmins implements CommandExecute {

    @Override
    public String execute() throws ControllerException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        UserService userService = serviceFactory.getUserService();

        StringBuilder sb = new StringBuilder();
        List<User> admins = null;
        try {
            admins = userService.allAdmins();
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        for (User user : admins) {
            sb.append(user.getLogin()).append(" ").append("\n");
        }
        return new String(sb);
    }
}
