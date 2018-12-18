import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Home {
    private JPanel main;
    private JTable tableBooks;
    private JComboBox comboBoxType;
    private JTextField textNameBook;
    private JButton addBookButton;
    private JButton cartBotton;
    static JFrame frame = new JFrame("Bookshop");
    BookService bookService = new BookService();
    CartService cartService = new CartService();

    public Home() {
        main.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                showHome();
                setNumberCartBotton();
            }
        });
        comboBoxType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type = comboBoxType.getSelectedItem().toString();
                setTableBooks(bookService.laodBooksByType(type));
            }
        });

        addBookButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addBook();
            }
        });
        cartBotton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToCartForm();
            }
        });

        main.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                setNumberCartBotton();
            }
        });
    }

    public void showHome() {
        createTabel();
        setTableBooks(bookService.loadAllBooks());

    }

    public static void main(String[] args) {
        Home form = new Home();
        frame.setContentPane(form.main);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 250));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

    public void createTabel() {
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Name");
        tableModel.addColumn("Author");
        tableModel.addColumn("Type");
        tableModel.addColumn("Price");
        tableBooks.setModel(tableModel);
        tableBooks.setDefaultEditor(Object.class, null);
        setComboBox();

    }

    public void setComboBox() {
        DefaultComboBoxModel comboBoxTypeModel = new DefaultComboBoxModel();
        comboBoxTypeModel.addElement("All");
        comboBoxTypeModel.addElement("นิยาย");
        comboBoxTypeModel.addElement("วรรณคดี");
        comboBoxTypeModel.addElement("ปรัชญา");
        comboBoxTypeModel.addElement("ภูมิศาสตร์");
        comboBoxTypeModel.addElement("ศาสนา");
        comboBoxTypeModel.addElement("กฎหมาย");
        comboBoxTypeModel.addElement("คอมพิวเตอร์");
        comboBoxType.setModel(comboBoxTypeModel);
    }

    public void setTableBooks(ArrayList<Book> temp) {
        DefaultTableModel modelCartTabelRow = (DefaultTableModel) tableBooks.getModel();
        modelCartTabelRow.setRowCount(0);
        Object rowCartData[] = new Object[4];
        for (int i = 0; i < temp.size(); i++) {
            rowCartData[0] = temp.get(i).name;
            rowCartData[1] = temp.get(i).author;
            rowCartData[2] = temp.get(i).type;
            rowCartData[3] = temp.get(i).price;
            modelCartTabelRow.addRow(rowCartData);

        }
    }

    public void addBook() {
        try {
            DefaultTableModel model = (DefaultTableModel) tableBooks.getModel();
            int selectedRowIndex = tableBooks.getSelectedRow();
            String nameBook = model.getValueAt(selectedRowIndex, 0).toString();
            cartService.selectBook(nameBook);
            setNumberCartBotton();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "กรุณาเลือกรายการที่ต้องการเพิ่ม");
        }

    }

    public void setNumberCartBotton() {
        cartBotton.setText(String.valueOf(cartService.getCartItem().amount));
    }

    public void switchToCartForm() {
        CartForm cartForm = new CartForm();
        cartForm.showCrat();
        frame.setVisible(false);
    }
}
