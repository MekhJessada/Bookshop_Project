import com.mongodb.*;

import java.util.ArrayList;

public class BookDAO {
    static MongoClientURI uri;
    static MongoClient mongo;
    static DB db;
    static DBCollection product;

    public void connect() {
        try {
            uri = new MongoClientURI("mongodb://admin:admin123456@ds249503.mlab.com:49503/bookshop");
            mongo = new MongoClient(uri);
            db = mongo.getDB(uri.getDatabase());
            product = db.getCollection("Product");
        } catch (Exception e) {

        }
    }

    public ArrayList<Book> getBooks() {
        ArrayList<Book> books = new ArrayList<>();
        DBCursor value = product.find();
        while (value.hasNext()) {
            BasicDBObject obj = (BasicDBObject) value.next();
            String ID = (String) obj.get("_id");
            String bookName = (String) obj.get("bookname");
            String t = (String) obj.get("type");
            String author = (String) obj.get("author");
            int price = (int) obj.get("price");
            books.add(new Book(ID, bookName, t, author, price));

        }
        return books;

    }
}
