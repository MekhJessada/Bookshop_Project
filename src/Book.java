public class Book {
    String id;
    String name;
    String type;
    String author;
    int price;
    int amount = 0;

    public Book(String id, String name, String type, String author, int price) {
        this.id = name;
        this.name = name;
        this.type = type;
        this.author = author;
        this.price = price;
        this.amount = 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
