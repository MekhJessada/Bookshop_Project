public class CartService {
    static Cart cart = new Cart();
    CartDAO cartDAO = new CartDAO();

    public void addToCart(Book book) {
        boolean flax = true;
        for (int i = 0; i < cart.booksInCart.size(); i++) {
            if (book.name.equals(cart.booksInCart.get(i).name)) {
                cart.booksInCart.get(i).amount++;
                flax = false;
                break;
            }
        }
        if (flax) {
            cart.booksInCart.add(book);
        }
        cart.pice += book.price;
        cart.amount++;

    }

    public void selectBook(String nameBook) {
        for (int i = 0; i < BookService.books.size(); i++) {
            if (BookService.books.get(i).name.equals(nameBook)) {
                addToCart(BookService.books.get(i));
                break;
            }
        }
    }

    public void deleteBookInCartItem(String nameBook) {
        for (int i = 0; i < cart.booksInCart.size(); i++) {
            if (cart.booksInCart.get(i).name.equals(nameBook)) {
                cart.pice -= cart.booksInCart.get(i).price * cart.booksInCart.get(i).amount;
                cart.amount -= cart.booksInCart.get(i).amount;
                cart.booksInCart.get(i).amount = 1;
                cart.booksInCart.remove(i);
                break;
            }
        }
    }

    public static Cart getCartItem() {
        return cart;
    }

    public void saveOrder(String address, String payment, String delivery) {
        cartDAO.connect();
        cartDAO.insert("1", cart, address, payment, delivery);
    }

    public static void resetData() {
        cart.getBooksInCart().clear();
        cart.amount = 0;
        cart.pice = 0.0;
    }

    public boolean checkCartIsEmtry() {
        if (cart.amount == 0) {
            return true;
        } else {
            return false;
        }
    }

}
