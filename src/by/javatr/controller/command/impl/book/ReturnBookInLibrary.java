package by.javatr.controller.command.impl.book;

import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;
import by.javatr.view.View;

public class ReturnBookInLibrary implements CommandExecute {

    @Override
    public String execute() throws ControllerException {
        View view = View.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService=serviceFactory.getBookService();
        String nameBook = view.enterNameBook();
        try {
            if(bookService.returnBookInLibrary(nameBook)) return "The book was successfully submitted to the library";
            else  return "The book not submit in library";
        } catch (ServiceException s) {
            throw new ControllerException(s);
        }
    }
}