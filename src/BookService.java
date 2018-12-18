import java.util.ArrayList;

public class BookService {
    BookDAO bookDAO = new BookDAO();
    public static ArrayList<Book> books;
    private ArrayList<Book> temp;

    public ArrayList loadAllBooks() {
        bookDAO.connect();
        books = bookDAO.getBooks();
        return books;
    }

    public ArrayList laodBooksByType(String type) {
        temp = new ArrayList<>();
        temp.clear();
        if (type.equals("All")) {
            return books;
        } else {
            for (int i = 0; i < books.size(); i++) {
                if (books.get(i).type.equals(type)) {
                    temp.add(new Book(books.get(i).id, books.get(i).name, books.get(i).type, books.get(i).author, books.get(i).price));
                }
            }
            return temp;
        }

    }
}
