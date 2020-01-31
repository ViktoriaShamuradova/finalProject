package by.javatr.service.impl;

import by.javatr.bean.Book;
import by.javatr.dao.BookDao;
import by.javatr.dao.exception.DaoException;
import by.javatr.dao.factory.DaoFactory;
import by.javatr.service.BookService;
import by.javatr.service.exception.ServiceException;
import by.javatr.service.valid.ValidBook;

import java.util.*;

public class BookServiceImpl implements BookService {
    private static final BookServiceImpl instance = new BookServiceImpl();

    private ValidBook validBook;
    private BookDao bookDao;

    private BookServiceImpl() {
        DaoFactory daoFactory = DaoFactory.getInstance();
        bookDao = daoFactory.getBookDao();
        validBook = ValidBook.getInstance();
    }

    public static BookServiceImpl getInstance() {
        return instance;
    }

    @Override
    public boolean addNewBookInLibrary(Book book) throws ServiceException {
        try {
            return bookDao.saveBookInLibrary(book);
        } catch (DaoException d) {
            throw new ServiceException(d);
        }
    }

    @Override
    public List<Book> allBooksInLibrary() throws ServiceException {
        List<Book> allBooks;
        try {
            allBooks = bookDao.returnAllBookFromLibrary();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Collections.sort(allBooks);
        return allBooks;
    }

    @Override
    public List<Book> allBooksInIssuedList() throws ServiceException {
        List<Book> allBooks;
        try {
            allBooks = bookDao.returnAllBookFromIssuedList();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        Collections.sort(allBooks);
        return allBooks;
    }

    @Override
    public boolean deleteBookFromLibraryById(int id) throws ServiceException {
        try {
            List<Book> books = bookDao.returnAllBookFromLibrary();
            Collections.sort(books);
            if (id < 0 || id > books.size()) return false;
            Iterator<Book> bookIterator = books.iterator();
            while (bookIterator.hasNext()) {
                Book book = bookIterator.next();
                if (books.indexOf(book) == id) {
                    bookIterator.remove();
                    bookDao.saveArrayLibrary(books);
                    return true;
                }
            }
        } catch (DaoException d) {
            throw new ServiceException(d);
        }
        return false;
    }

    @Override
    public Book createNewBook(String[] dataBook) {
        float rating = Float.parseFloat(dataBook[1]);
        boolean isBestSeller = Boolean.parseBoolean(dataBook[2]);
        Book book = new Book(dataBook[0], rating, isBestSeller);
        if (validBook.isValidBook(book)) {
            return book;
        } else return null;
    }

    @Override
    public boolean replaceBookInLibrary(int idBook, Book newBook) throws ServiceException {
        try {
            List<Book> books = bookDao.returnAllBookFromLibrary();
            books.remove(idBook);
            books.add(newBook);
            bookDao.saveArrayLibrary(books);
            return true;
        } catch (IndexOutOfBoundsException d) {
            return false;
        } catch (DaoException d) {
            throw new ServiceException(d);
        }
    }

    @Override
    public boolean takeBookForReading(int idBook) throws ServiceException {
        Book book = searchBookInLibraryById(idBook);
        if (book == null) return false;
        try {
            bookDao.deleteBookFromLibrary(book);
            bookDao.saveBookInIssued(book);
            boolean haveBook = false;
            List<Book> books1 = bookDao.returnAllBookFromLibrary();
            Iterator<Book> iterator = books1.iterator();
            while (iterator.hasNext()) {
                Book next = iterator.next();
                if (next.equals(book)) {
                    haveBook = true;
                    break;
                }
            }
            if (!haveBook) {
                Set<Book> books = bookDao.returnUniqueBooks();
                for (Book book1 : books) {
                    if (book1.getName().equals(book.getName())
                            && book1.getRating() == book.getRating()
                            && book1.isBestSeller() == book.isBestSeller()) {
                        books.remove(book1);
                        break;
                    }
                }
                bookDao.saveListOfLiterature(books);
            }
            return true;
        } catch (DaoException d) {
            throw new ServiceException(d);
        }
    }

    @Override
    public boolean returnBookInLibrary(String nameBook) throws ServiceException {
        Book returnBook = null;
        boolean bookHave;
        try {
            List<Book> books = bookDao.returnAllBookFromIssuedList();
            bookHave = false;
            for (Book book : books) {
                if (book.getName().equalsIgnoreCase(nameBook)) {
                    returnBook = new Book(book.getName(), book.getRating(), book.isBestSeller());
                    bookHave = true;
                    break;
                }
            }
            //удаляем книгу из списка выданных книг
            if (bookHave) {
                Iterator<Book> iterator = books.iterator();
                while (iterator.hasNext()) {
                    Book nextBook = iterator.next();
                    if (nextBook.getName().equalsIgnoreCase(nameBook)) {
                        iterator.remove();
                        break;
                    }
                }
                bookDao.saveArrayInIssued(books);
                bookDao.saveBookInLibrary(returnBook);
            }
        } catch (DaoException d) {
            throw new ServiceException(d);
        }
        //значит по названию книгу не нашли в списке выданных книг
        return bookHave;
    }

    @Override
    public Map<Integer, Book> searchBookInLibraryByName(String name) throws ServiceException {
        Map<Integer, Book> books = new HashMap<>();
        List<Book> allBooks;
        try {
            allBooks = bookDao.returnAllBookFromLibrary();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        for (Book book : allBooks) {
            if (book.getName().equals(name)) {
                books.put(allBooks.indexOf(book), book);
            }
        }
        return books;
    }

    @Override
    public Book searchBookInLibraryById(int id) throws ServiceException {
        try {
            List<Book> books = bookDao.returnAllBookFromLibrary();
            //Collections.sort(books);
            Book book = books.get(id);
            book.setIdBook(id);
            return book;
        } catch (IndexOutOfBoundsException e) {
            return null;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Set<Book> returnUniqueBooks() throws ServiceException {
        try {
            return bookDao.returnUniqueBooks();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
