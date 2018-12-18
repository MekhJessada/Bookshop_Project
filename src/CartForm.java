import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class CartForm {
    private JPanel main;
    private JButton backButton;
    private JButton orderButton;
    private JTable tableCart;
    private JButton delectButton;
    private JLabel sumPrice;
    private JLabel sumAmount;
    static JFrame frame = new JFrame("Cart");
    CartService cartService = new CartService();

    public CartForm() {

        main.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                createTabel();
                setTableBooks();
                setSum();
            }
        });
        delectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteBook();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home.frame.setVisible(true);
                frame.setVisible(false);
            }
        });
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showCartDetail();
            }
        });
    }

    public static void main(String[] args) {

    }

    public void showCrat() {
        CartForm form = new CartForm();
        frame.setContentPane(form.main);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 250));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public void showCartDetail() {
        if (cartService.checkCartIsEmtry()) {
            JOptionPane.showMessageDialog(null, "กรุณาเพิ่มหนังสือลงตะกร้าก่อน");
        } else {
            CartDetail bookShopFrom = new CartDetail();
            bookShopFrom.showForm();
            frame.setVisible(false);
        }
    }

    public void createTabel() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Author");
        tableModel.addColumn("Type");
        tableModel.addColumn("Price");
        tableModel.addColumn("Amount");
        tableCart.setModel(tableModel);
        tableCart.setDefaultEditor(Object.class, null);


    }

    public void setTableBooks() {
        ArrayList<Book> temp = CartService.getCartItem().booksInCart;
        DefaultTableModel modelCartTabelRow = (DefaultTableModel) tableCart.getModel();
        modelCartTabelRow.setRowCount(0);
        Object rowCartData[] = new Object[5];
        for (int i = 0; i < temp.size(); i++) {
            rowCartData[0] = temp.get(i).name;
            rowCartData[1] = temp.get(i).author;
            rowCartData[2] = temp.get(i).type;
            rowCartData[3] = temp.get(i).price;
            rowCartData[4] = temp.get(i).amount;
            modelCartTabelRow.addRow(rowCartData);

        }
    }

    public void setSum() {
        sumAmount.setText("Amount : " + CartService.getCartItem().amount);
        sumPrice.setText("Price : " + CartService.getCartItem().pice);
    }

    public void deleteBook() {
        try {
            DefaultTableModel model = (DefaultTableModel) tableCart.getModel();
            int selectedRowIndex = tableCart.getSelectedRow();
            String nameBook = model.getValueAt(selectedRowIndex, 0).toString();
            CartService cartService = new CartService();
            cartService.deleteBookInCartItem(nameBook);
            setTableBooks();
            setSum();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "กรุณาเลือกรายการที่ต้องการลบ");
        }

    }
}
