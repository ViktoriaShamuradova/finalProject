package by.javatr.controller.command.impl.book;

import by.javatr.bean.Book;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;
import by.javatr.view.View;

public class ReplaceTheBook implements CommandExecute {

    @Override
    public String execute() throws ControllerException {
        View view = View.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService=serviceFactory.getBookService();

        int idBook = view.enterIdBook();
        String[] booksCharacteristics = view.enterDataBook();
        Book newBookToSet;
        try {
            newBookToSet = bookService.createNewBook(booksCharacteristics);
            if(bookService.replaceBookInLibrary(idBook, newBookToSet)) return "Book is replaced";
            else return "Book is not replaced. Please enter correct id";
        } catch (ServiceException s) {
            throw new ControllerException(s);
        }
    }
}
