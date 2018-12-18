import java.util.ArrayList;

public class Cart {
    static ArrayList<Book> booksInCart = new ArrayList<>();
    int amount = 0;
    Double pice = 0.0;

    public void setBooksInCart(ArrayList<Book> booksInCart) {
        this.booksInCart = booksInCart;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setPice(Double pice) {
        this.pice = pice;
    }

    public ArrayList<Book> getBooksInCart() {
        return booksInCart;
    }

    public int getAmount() {
        return amount;
    }

    public Double getPice() {
        return pice;
    }

}
