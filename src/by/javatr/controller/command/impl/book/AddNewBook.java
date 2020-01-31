package by.javatr.controller.command.impl.book;

import by.javatr.bean.Book;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;
import by.javatr.view.View;

public class AddNewBook implements CommandExecute {

    @Override
    public String execute() throws ControllerException {
        View view = View.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService=serviceFactory.getBookService();

        String[] booksCharacteristics = view.enterDataBook();
        Book newBook;
        try {
        newBook = bookService.createNewBook(booksCharacteristics);
            if(bookService.addNewBookInLibrary(newBook)) return "Book is added";
            return "Book is not added in library. Please, enter valid date of book";
        }catch (ServiceException s){
            throw new ControllerException(s);
        }
    }
}
