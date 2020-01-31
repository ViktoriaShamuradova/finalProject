package by.javatr.dao.impl;

import by.javatr.bean.Book;
import by.javatr.dao.BookDao;
import by.javatr.dao.exception.DaoException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BookDaoImpl implements BookDao {

    private static final String libraryFile= "library.bin";
    private static final String fileOfIssuedBooks = "Issued books.bin";
    private static final BookDaoImpl instance = new BookDaoImpl();

    private Writer writer;
    private List<Book> library;
    private List<Book> issuedBooks;
    private Set<Book> listOfLiterature;

    private BookDaoImpl() {
        writer = Writer.getInstance();
        Reader reader = Reader.getInstance();
        listOfLiterature = new TreeSet<>();
        try {
            library = reader.readBooksFromFile(libraryFile);
            listOfLiterature.addAll(library);
            issuedBooks = reader.readBooksFromFile(fileOfIssuedBooks);
        } catch (DaoException e) {
            library=null;
            issuedBooks=null;
            listOfLiterature=null;
        }
    }

    public static BookDaoImpl getInstance() {
        return instance;
    }

    @Override
    public boolean saveBookInLibrary(Book book) throws DaoException {
        if (book == null) return false;
        library.add(book);
        listOfLiterature.add(book);
        saveListOfBooksToFile(library, libraryFile);
        return true;
    }

    @Override
    public void saveArrayLibrary(List<Book> books) throws DaoException {
        library = books;
        saveListOfBooksToFile(library, libraryFile);
    }

    @Override
    public void saveBookInIssued(Book book) throws DaoException {
        issuedBooks.add(book);
        saveListOfBooksToFile(issuedBooks, fileOfIssuedBooks);
    }

    @Override
    public void saveArrayInIssued(List<Book> books) throws DaoException {
        issuedBooks = books;
        saveListOfBooksToFile(issuedBooks, fileOfIssuedBooks);
    }

    @Override
    public void saveListOfLiterature(Set<Book> books) {
        listOfLiterature = books;
    }

    @Override
    public List<Book> returnAllBookFromLibrary() {
        return new ArrayList<>(library);
    }

    @Override
    public List<Book> returnAllBookFromIssuedList() {
        return new ArrayList<>(issuedBooks);
    }

    @Override
    public void deleteBookFromLibrary(Book book) throws DaoException {
        library.remove(book);
        saveListOfBooksToFile(library, libraryFile);
    }

    @Override
    public Set<Book> returnUniqueBooks() {
        return new TreeSet<>(listOfLiterature);
    }

    private void saveListOfBooksToFile(List<Book> books, String fileName) throws DaoException {
            writer.writeBooksToFile(books, fileName);
    }
}
