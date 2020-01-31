package by.javatr.controller.command.impl.book;

import by.javatr.bean.Book;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;

import java.util.List;

public class ShowAllIssuedBooks implements CommandExecute {

    @Override
    public String execute() throws ControllerException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService=serviceFactory.getBookService();

        StringBuilder sb = new StringBuilder();
        List<Book> books = null;
        try {
            books = bookService.allBooksInIssuedList();
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        for (Book book : books) {
            sb.append(books.indexOf(book)).append(" ")
                    .append(book.getName()).append(" ")
                    .append(book.getRating()).append(" ")
                    .append(book.isBestSeller()).append(" ").append("\n");
        }
        return new String(sb);
    }
}
