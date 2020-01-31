package by.javatr.controller.command.impl.book;

import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;
import by.javatr.view.View;

public class DeleteBookById implements CommandExecute {

    @Override
    public String execute() throws ControllerException {
        View view = View.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService = serviceFactory.getBookService();
        int idBook = view.enterIdBook();
        try {
            if (bookService.deleteBookFromLibraryById(idBook)) return "Book deleted by id";
            return "Book id not delete. Id does not match";
        } catch (ServiceException s) {
            throw new ControllerException();
        }
    }
}
