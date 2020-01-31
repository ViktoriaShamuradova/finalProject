package by.javatr.controller.command.impl.book;

import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;
import by.javatr.view.View;

public class TakeBookForReading implements CommandExecute {

    @Override
    public String execute() throws ControllerException {
        View view = View.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService=serviceFactory.getBookService();
        ShowAllBooksInLibrary showBooks = new ShowAllBooksInLibrary();
        SearchBookByName searchBooks = new SearchBookByName();

        view.printMessage(showBooks.execute());
        view.printMessage(searchBooks.execute());
        int idBook = view.enterIdBookTakeForReading();
        try {
            if(bookService.takeBookForReading(idBook))return "Your book is ready. Enjoy reading!";
            return "Book is not issued";
        } catch (ServiceException s) {
            throw new ControllerException(s);
        }

    }
}