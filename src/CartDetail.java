import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class CartDetail {
    private JRadioButton kBANKRadioButton;
    private JRadioButton krungthaiRadioButton;
    private JRadioButton SCBRadioButton;
    private JRadioButton governmentSavingsBankRadioButton;
    private JRadioButton EMSRadioButton;
    private JRadioButton normalRadioButton;
    private JRadioButton KERRYRadioButton;
    private JButton confirmButton;
    private JTextArea textAreaAddress;
    private JPanel main;
    private JButton backButton;
    static String payment = "";
    static String delivery = "";
    static String address = "";
    static JFrame frame = new JFrame("Order detail");

    public static void main(String[] args) {
    }

    public CartDetail() {
        kBANKRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPayment();
                krungthaiRadioButton.setSelected(false);
                krungthaiRadioButton.updateUI();
                SCBRadioButton.setSelected(false);
                SCBRadioButton.updateUI();
                governmentSavingsBankRadioButton.setSelected(false);
                governmentSavingsBankRadioButton.updateUI();
            }
        });
        krungthaiRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPayment();
                SCBRadioButton.setSelected(false);
                SCBRadioButton.updateUI();

                governmentSavingsBankRadioButton.setSelected(false);
                governmentSavingsBankRadioButton.updateUI();

                kBANKRadioButton.setSelected(false);
                kBANKRadioButton.updateUI();
            }
        });
        SCBRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPayment();
                governmentSavingsBankRadioButton.setSelected(false);
                governmentSavingsBankRadioButton.updateUI();

                kBANKRadioButton.setSelected(false);
                kBANKRadioButton.updateUI();

                krungthaiRadioButton.setSelected(false);
                krungthaiRadioButton.updateUI();
            }
        });
        governmentSavingsBankRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setPayment();
                kBANKRadioButton.setSelected(false);
                kBANKRadioButton.updateUI();

                krungthaiRadioButton.setSelected(false);
                krungthaiRadioButton.updateUI();
                SCBRadioButton.setSelected(false);
                SCBRadioButton.updateUI();
            }
        });
        EMSRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDelivery();
                normalRadioButton.setSelected(false);
                normalRadioButton.updateUI();
                KERRYRadioButton.setSelected(false);
                KERRYRadioButton.updateUI();
            }
        });
        normalRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDelivery();
                KERRYRadioButton.setSelected(false);
                KERRYRadioButton.updateUI();
                EMSRadioButton.setSelected(false);
                EMSRadioButton.updateUI();
            }
        });
        KERRYRadioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDelivery();
                normalRadioButton.setSelected(false);
                normalRadioButton.updateUI();
                EMSRadioButton.setSelected(false);
                EMSRadioButton.updateUI();
            }
        });
        main.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

            }
        });
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirm();
            }
        });
        textAreaAddress.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                CartForm.frame.setVisible(true);
            }
        });
    }

    public void setPayment() {
        if (kBANKRadioButton.isSelected()) {
            payment = "kBANK";

        }
        if (krungthaiRadioButton.isSelected()) {
            payment = "Krungthai";

        }
        if (SCBRadioButton.isSelected()) {
            payment = "SCB";

        }
        if (governmentSavingsBankRadioButton.isSelected()) {
            payment = "GSB";


        }
    }

    void showForm() {
        CartDetail form = new CartDetail();
        frame.setContentPane(form.main);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(600, 250));
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

    public void setDelivery() {
        if (EMSRadioButton.isSelected())
            delivery = "EMS";
        if (normalRadioButton.isSelected())
            delivery = "normal";
        if (KERRYRadioButton.isSelected())
            delivery = "Kerry";
    }

    public void confirm() {
        if (checkData()) {
            Order order = new Order();
            order.showOder();
            frame.setVisible(false);
        }


    }

    public boolean checkData() {
        if (textAreaAddress.getText().isEmpty() || (!(kBANKRadioButton.isSelected() || governmentSavingsBankRadioButton.isSelected() || SCBRadioButton.isSelected() || krungthaiRadioButton.isSelected())) || (!(KERRYRadioButton.isSelected() || normalRadioButton.isSelected() || EMSRadioButton.isSelected()))) {
            if (textAreaAddress.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "กรุณากรอกที่อยู่จัดส่ง");
            }
            if (!(kBANKRadioButton.isSelected() || governmentSavingsBankRadioButton.isSelected() || SCBRadioButton.isSelected() || krungthaiRadioButton.isSelected())) {
                JOptionPane.showMessageDialog(null, "กรุณาเลือกช่องทางการชำระเงิน");
            }
            if (!(KERRYRadioButton.isSelected() || normalRadioButton.isSelected() || EMSRadioButton.isSelected())) {
                JOptionPane.showMessageDialog(null, "กรุณาเลือกช่องทางการจัดส่ง");
            }
            return false;
        } else {
            address = textAreaAddress.getText();
            return true;
        }
    }

    public static void resetData() {
        delivery = "";
        payment = "";
        address = "";
    }
}
