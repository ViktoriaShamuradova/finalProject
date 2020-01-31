package by.javatr.service;

import by.javatr.bean.Book;
import by.javatr.service.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface BookService {

    boolean addNewBookInLibrary(Book book) throws ServiceException;

    List<Book> allBooksInLibrary() throws ServiceException;

    List<Book> allBooksInIssuedList()throws ServiceException;

    boolean deleteBookFromLibraryById(int id) throws ServiceException;

    Book createNewBook(String[] dataBook) throws ServiceException; //может void, boolean????

    boolean replaceBookInLibrary(int idBook, Book book) throws ServiceException;

    boolean takeBookForReading(int idBook) throws ServiceException;

    boolean returnBookInLibrary(String nameBook) throws ServiceException;

    Map<Integer, Book> searchBookInLibraryByName(String name) throws ServiceException;

    Book searchBookInLibraryById(int id) throws ServiceException;

    Set<Book> returnUniqueBooks() throws ServiceException;
}
