package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;

public class DeleteCustomer extends JFrame implements ActionListener {

    JTextField tfMeter;
    JButton delete, cancel;

    DeleteCustomer() {
        setSize(400, 200);
        setLocation(500, 300);

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new Color(173, 216, 230));
        add(p);

        JLabel lblMeter = new JLabel("Enter Meter Number");
        lblMeter.setBounds(40, 30, 150, 20);
        p.add(lblMeter);

        tfMeter = new JTextField();
        tfMeter.setBounds(200, 30, 150, 20);
        p.add(tfMeter);

        delete = new JButton("Delete");
        delete.setBounds(70, 80, 100, 25);
        delete.setBackground(Color.BLACK);
        delete.setForeground(Color.WHITE);
        delete.addActionListener(this);
        p.add(delete);

        cancel = new JButton("Cancel");
        cancel.setBounds(200, 80, 100, 25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        p.add(cancel);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            String meter = tfMeter.getText();

            try {
                Conn con = new Conn();

                // Delete customer record
                String query1 = "DELETE FROM customer WHERE meter_no = ?";
                PreparedStatement pstmt1 = con.c.prepareStatement(query1);
                pstmt1.setString(1, meter);
                pstmt1.executeUpdate();

                // Delete login record
                String query2 = "DELETE FROM login WHERE meter_no = ?";
                PreparedStatement pstmt2 = con.c.prepareStatement(query2);
                pstmt2.setString(1, meter);
                pstmt2.executeUpdate();

                JOptionPane.showMessageDialog(null, "Customer Deleted Successfully");
                setVisible(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new DeleteCustomer();
    }
}
