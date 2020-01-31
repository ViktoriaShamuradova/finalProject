package by.javatr.dao.impl;

import by.javatr.bean.*;
import by.javatr.dao.exception.DaoException;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Writer {
    private static final Writer instance = new Writer();

    private Writer() {
    }

    public static Writer getInstance() {
        return instance;
    }

    public void writeUsersToFile(List<User> listOfUsers, String fileName) throws DaoException {
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) { //ObjectOutPutStream умеет сериализовать объекты, но ему неизвесто, куда мы собираемся отправлять эти объекты(по сети или сохранить в файл, поэтому должны указать внутренний стрим, который мы будем использовать для сохранения наших объетов, то есть ObjectOutPutStream обернет переданный стрим в конструктор и используя его выполнит сериализацию и отправку, также этот класс отвечает за проверку того, что наш объект сериализуемый
            for (User user : listOfUsers) {                                                                                  // то есть создаем стрим для сохранения объетокв, для этого понадобится класс Files.newOutputStream возвращает OutputStream, а ему в свою очередь понадобятся координаты нашего файла, как минимум нужно указать имя файла
                out.writeObject(user);
            }
            out.writeObject(new User(new Login("1111"), new Password("11111111"), UserType.ADMIN));
        } catch (IOException e) {
            throw new DaoException("File cannot be open.");
        }
    }

    public void writeBooksToFile(List<Book> listOfBooks, String fileName) throws DaoException {                                                      //записывем объекты в бинарные файлы, то есть они не текстовые и читать их с помощбю текстового редактора невозможно, будет аброкодабра, чтобы сохранить объект, он должен быть сериализиремый
        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileName)))) { //ObjectOutPutStream умеет сериализовать объекты, но ему неизвесто, куда мы собираемся отправлять эти объекты(по сети или сохранить в файл, поэтому должны указать внутренний стрим, который мы будем использовать для сохранения наших объетов, то есть ObjectOutPutStream обернет переданный стрим в конструктор и используя его выполнит сериализацию и отправку, также этот класс отвечает за проверку того, что наш объект сериализуемый
            for (Book book : listOfBooks) {                                                                                  // то есть создаем стрим для сохранения объетокв, для этого понадобится класс Files.newOutputStream возвращает OutputStream, а ему в свою очередь понадобятся координаты нашего файла, как минимум нужно указать имя файла
                out.writeObject(book);
            }
            out.writeObject(new Book("1111", 0, false));
        } catch (IOException e) {
            throw new DaoException("File cannot be open.");
        }
    }


    public static void main(String[] args) throws DaoException {
        List<Book> books = new ArrayList<>();
        books.add(new Book("Философия JAVA", 5.3f, false));
        books.add(new Book("Изучаем JAVA", 7.3f, true));
        books.add(new Book("Кулинария", 3.6f, false));
        books.add(new Book("Эволюция человечества", 9.3f, true));
        books.add(new Book("Познер", 7.5f, false));
        books.add(new Book("Поющие в терновнике", 6.6f, false));
        books.add(new Book("Приключения кротика", 7.1f, false));
        books.add(new Book("Я буду ждать тебя всегда", 8.5f, false));
        books.add(new Book("Тонкое искусство пофигизма", 9.4f, true));
        books.add(new Book("Война и мир", 7.3f, false));
        String file = "library.bin";
        String file2 = "Issued books.bin";
        List<Book> issued = new ArrayList();

        Book book1 = new Book("Грызун с большой дороги", 8.6f, false);
        Book book2 = new Book("Груффало", 9.6f, true);
        issued.add(book1);
        issued.add(book2);
        Writer writer = Writer.getInstance();
        writer.writeBooksToFile(books, file);
        writer.writeBooksToFile(issued, file2);
        Reader reader = Reader.getInstance();
        List<Book> books1 = reader.readBooksFromFile(file);
        System.out.println(books1);
        System.out.println(reader.readBooksFromFile(file2));
        writeUsersToFile1("User.bin");

    }

    public static void writeUsersToFile1(String fileOfUsers) {

        try (ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(Paths.get(fileOfUsers)))) { //ObjectOutPutStream умеет сериализовать объекты, но ему неизвесто, куда мы собираемся отправлять эти объекты(по сети или сохранить в файл, поэтому должны указать внутренний стрим, который мы будем использовать для сохранения наших объетов, то есть ObjectOutPutStream обернет переданный стрим в конструктор и используя его выполнит сериализацию и отправку, также этот класс отвечает за проверку того, что наш объект сериализуемый
            // то есть создаем стрим для сохранения объетокв, для этого понадобится класс Files.newOutputStream возвращает OutputStream, а ему в свою очередь понадобятся координаты нашего файла, как минимум нужно указать имя файла
            out.writeObject(new User(new Login("Vika"), new Password("1234567a"), UserType.ADMIN));

            out.writeObject(new User(new Login("1111"), new Password("11111111"), UserType.ADMIN));
        } catch (IOException e) {
            System.out.println("File cannot be open. Program terminates");
            e.printStackTrace();
        }
    }
}