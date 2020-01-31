package by.javatr.dao.impl;

import by.javatr.bean.Book;
import by.javatr.bean.Login;
import by.javatr.bean.User;
import by.javatr.dao.exception.DaoException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Reader {
    private static final Reader instance = new Reader();

    private Reader() {
    }

    public static Reader getInstance() {
        return instance;
    }

    public List<User> readUsersFromFile(String fileName) throws DaoException {
        List<User> listOfUsers = new ArrayList<>();
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {//указывем из какого стрима мы собираемся читать данные
            boolean keepReading = true;                      //ObjectInputStream не знает, какой тип нужно возвращать и он всегда использует обжек
            while (keepReading) {                               //возможно появится ClassNotFoundEx. ObjectInputStream не знает, когда заканчивается файл
                User user = (User) input.readObject();                                            //пока keepReading true, выполняем чтение из файла
                if (!user.getLogin().equals(new Login("1111"))) {
                    listOfUsers.add(user);
                } else {
                    keepReading = false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new DaoException("File with users does not exist", e);
        }
        return listOfUsers;
    }

    public List<Book> readBooksFromFile(String fileName) throws DaoException { ////////!!!!!!!!!
        List<Book> listOfBooks = new ArrayList<>();
        try (ObjectInputStream input = new ObjectInputStream(Files.newInputStream(Paths.get(fileName)))) {//указывем из какого стрима мы собираемся читать данные
            boolean keepReading = true;                      //ObjectInputStream не знает, какой тип нужно возвращать и он всегда использует обжек
            while (keepReading) {                               //возможно появится ClassNotFoundEx. ObjectInputStream не знает, когда заканчивается файл
                Book book = (Book) input.readObject();                                            //пока keepReading true, выполняем чтение из файла
                if (!book.getName().equals("1111")) {
                    listOfBooks.add(book);
                } else {
                    keepReading = false;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new DaoException("File with books does not exist", e);
        }
        return listOfBooks;
    }
}