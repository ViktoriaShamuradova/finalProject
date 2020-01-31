package by.javatr.controller.command.impl.book;

import by.javatr.bean.Book;
import by.javatr.controller.command.CommandExecute;
import by.javatr.controller.exception.ControllerException;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.factory.ServiceFactory;
import by.javatr.view.View;

import java.util.Map;

public class SearchBookByName implements CommandExecute {
    @Override
    public String execute() throws ControllerException {
        View view = View.getInstance();
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        BookService bookService=serviceFactory.getBookService();

        String nameBook = view.enterNameBook();
        StringBuilder sb = new StringBuilder();
        Map<Integer, Book> books;
        try {
            books = bookService.searchBookInLibraryByName(nameBook);
        } catch (ServiceException e) {
            throw new ControllerException(e);
        }
        for (Integer id : books.keySet()) {
            sb.append(books.get(id).getName()).append(" ")
                    .append(books.get(id).getRating()).append(" ")
                    .append(books.get(id).isBestSeller())
                    .append(" ").append(id).append(" ").append("\n");
        }
        return new String(sb);
    }
}
