package by.javatr.controller.command.impl.book;

import by.javatr.bean.Book;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;
import by.javatr.view.View;

public class SearchBookById implements CommandExecute {

    @Override
    public String execute() throws ControllerException {
        View view = View.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService=serviceFactory.getBookService();

        int idBook = view.enterIdBook();
        Book book;
        StringBuilder sb = new StringBuilder();
        try {
            book = bookService.searchBookInLibraryById(idBook);
        } catch (ServiceException s) {
            throw new ControllerException(s);
        }
        sb.append(book.getName()).append(" ")
                .append(book.getRating()).append(" ")
                .append(book.isBestSeller()).append(" ")
                .append(book.getIdBook()).append(" ").append("\n");

        return new String(sb);
    }
}
