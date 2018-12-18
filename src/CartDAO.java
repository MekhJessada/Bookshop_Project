import com.mongodb.*;

import java.util.List;

public class CartDAO {
    static MongoClientURI uri;
    static MongoClient mongo;
    static DB db;
    static DBCollection user;
    static DBCollection order;

    public void connect() {
        try {
            uri = new MongoClientURI("mongodb://admin:admin123456@ds249503.mlab.com:49503/bookshop");
            mongo = new MongoClient(uri);
            db = mongo.getDB(uri.getDatabase());
            user = db.getCollection("User");
            order = db.getCollection("Order");
        } catch (Exception e) {

        }
    }

    public void insert(String id, Cart cart, String address, String payment, String delivery) {
        BasicDBObject orderObj = new BasicDBObject();
        List<Object> productsDBList = new BasicDBList();
        for (Book product : Cart.booksInCart) {
            DBObject productDBObject = new BasicDBObject();
            productDBObject.put("IDProduct", product.id);
            productDBObject.put("Amount", product.amount);
            productsDBList.add(productDBObject);
        }
        orderObj.put("User", id);
        orderObj.put("Books", productsDBList);
        orderObj.put("TotalPrice", cart.pice);
        orderObj.put("TotalAmount", cart.amount);
        orderObj.put("TypeOfDelivery", delivery);
        orderObj.put("TypeOfPay", payment);
        orderObj.put("Address", address);
        order.insert(orderObj);
    }
}
