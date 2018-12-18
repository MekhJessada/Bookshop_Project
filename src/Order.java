import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;

public class Order {
    private JTextArea textAreaAdress;
    private JTable tableCart;
    private JTable tableTotal;
    private JButton confirmButton;
    private JPanel main;
    private JLabel typeOfDelivery;
    private JLabel typeOfPay;
    private JButton backButton;
    static JFrame frame = new JFrame("Order");
    CartService cartService = new CartService();


    public Order() {
        main.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                showInfo();

            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirm();
                returnToHome();
            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                CartDetail.frame.setVisible(true);
            }
        });
    }

    public void showOder() {
        Order form = new Order();
        frame.setContentPane(form.main);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 250));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void showInfo() {
        getAddress();
        createTabelBooks();
        createTableTotal();
        getDelivery();
        getPay();

    }

    public void createTabelBooks() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Author");
        tableModel.addColumn("Type");
        tableModel.addColumn("Price");
        tableModel.addColumn("Amount");
        tableCart.setModel(tableModel);

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

    public void getDelivery() {
        typeOfDelivery.setText("        Type of  Delivery : " + CartDetail.delivery);
    }

    public void getPay() {
        typeOfPay.setText("         Type of Pay : " + CartDetail.payment);
    }

    public void getAddress() {
        textAreaAdress.setText(CartDetail.address);
    }

    public void createTableTotal() {
        DefaultTableModel modelTotalTabel = new DefaultTableModel();
        modelTotalTabel.addColumn("Total");
        modelTotalTabel.addColumn("Price");
        modelTotalTabel.addColumn("Amount");
        tableTotal.setModel(modelTotalTabel);
        DefaultTableModel modelTotalTabelRow = (DefaultTableModel) tableTotal.getModel();
        Object rowTotalData[] = new Object[3];
        rowTotalData[0] = "Total";
        rowTotalData[1] = CartService.cart.pice;
        rowTotalData[2] = CartService.cart.amount;
        modelTotalTabelRow.addRow(rowTotalData);
    }

    public void returnToHome() {
        CartService.resetData();
        CartDetail.resetData();
        JOptionPane.showMessageDialog(null, "สั่งซื้อสำเร็จแล้ว");
        Home.frame.setVisible(true);
        frame.setVisible(false);
    }

    public void confirm() {
        cartService.saveOrder(CartDetail.address, CartDetail.payment, CartDetail.delivery);
    }
}
