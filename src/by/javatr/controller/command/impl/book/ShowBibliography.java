package by.javatr.controller.command.impl.book;

import by.javatr.bean.Book;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;

import java.util.Set;

public class ShowBibliography implements CommandExecute {

    @Override
    public String execute() throws ControllerException {
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService=serviceFactory.getBookService();

        StringBuilder sb = new StringBuilder();
        Set<Book> bookSet;
        try {
            bookSet = bookService.returnUniqueBooks();
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        for (Book book : bookSet) {
            sb.append(book.getName()).append(" ")
                    .append(book.getRating()).append(" ")
                    .append(book.isBestSeller()).append("\n");
        }
        return new String(sb);
    }
}

