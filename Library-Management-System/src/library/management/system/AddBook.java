package library.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;

public class AddBook extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField bookTitle, author, isbn, quantity, available;
    private JButton b1, b2;

    public static void main(String[] args) {
        new AddBook().setVisible(true);
    }

    public AddBook() {
        setBounds(600, 200, 518, 442);
        contentPane = new JPanel(new GridBagLayout()); // Using GridBagLayout
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel l6 = new JLabel("Title");
        l6.setForeground(new Color(47, 79, 79));
        l6.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(l6, gbc);

        bookTitle = new JTextField();
        bookTitle.setForeground(new Color(47, 79, 79));
        bookTitle.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPane.add(bookTitle, gbc);

        // Author
        JLabel l1 = new JLabel("Author");
        l1.setForeground(new Color(47, 79, 79));
        l1.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPane.add(l1, gbc);

        author = new JTextField();
        author.setForeground(new Color(47, 79, 79));
        author.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        contentPane.add(author, gbc);

        // ISBN
        JLabel l2 = new JLabel("ISBN");
        l2.setForeground(new Color(47, 79, 79));
        l2.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(l2, gbc);

        isbn = new JTextField();
        isbn.setForeground(new Color(47, 79, 79));
        isbn.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        contentPane.add(isbn, gbc);

        // Quantity
        JLabel l3 = new JLabel("Quantity");
        l3.setForeground(new Color(47, 79, 79));
        l3.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(l3, gbc);

        quantity = new JTextField();
        quantity.setForeground(new Color(47, 79, 79));
        quantity.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        contentPane.add(quantity, gbc);

        // Available
        JLabel l7 = new JLabel("Available");
        l7.setForeground(new Color(47, 79, 79));
        l7.setFont(new Font("Tahoma", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPane.add(l7, gbc);

        available = new JTextField();
        available.setForeground(new Color(47, 79, 79));
        available.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        contentPane.add(available, gbc);

        // Buttons
        b1 = new JButton("Add");
        b1.addActionListener(this);
        b1.setBorder(new CompoundBorder(new LineBorder(new Color(128, 128, 128)), null));
        b1.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        contentPane.add(b1, gbc);

        b2 = new JButton("Back");
        b2.addActionListener(this);
        b2.setBorder(new CompoundBorder(new LineBorder(new Color(105, 105, 105)), null));
        b2.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 5;
        contentPane.add(b2, gbc);

        // Panel Border
        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(138, 43, 226), 2), "Add Books", TitledBorder.LEADING,
                TitledBorder.TOP, null, new Color(0, 0, 255)));
        panel.setBounds(10, 29, 398, 344);
        contentPane.add(panel, gbc);

        panel.setBackground(Color.WHITE);
        contentPane.setBackground(Color.WHITE);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            if (validateInput()) {
                try (Connection conn = DatabaseConnection.getConnection()) {
                    String checkSql = "SELECT COUNT(*) FROM Books WHERE isbn = ?";
                    PreparedStatement checkStmt = conn.prepareStatement(checkSql);
                    checkStmt.setString(1, isbn.getText());
                    ResultSet rsCheck = checkStmt.executeQuery();
                    rsCheck.next();
                    int count = rsCheck.getInt(1);

                    if (count > 0) {
                        JOptionPane.showMessageDialog(null, "This ISBN already exists in the database.", "Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String sql = "INSERT INTO Books(title, author, isbn, quantity, available) VALUES(?, ?, ?, ?, ?)";
                        PreparedStatement st = conn.prepareStatement(sql);
                        st.setString(1, bookTitle.getText());
                        st.setString(2, author.getText());
                        st.setString(3, isbn.getText());
                        st.setInt(4, Integer.parseInt(quantity.getText()));
                        st.setInt(5, Integer.parseInt(available.getText()));

                        int rs = st.executeUpdate();
                        if (rs > 0) {
                            JOptionPane.showMessageDialog(null, "Successfully Added");
                            new Home().setVisible(true);
                            this.setVisible(false);
                        } else {
                            JOptionPane.showMessageDialog(null, "Error");
                        }
                        st.close();
                    }
                    rsCheck.close();
                    checkStmt.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (ae.getSource() == b2) {
            this.setVisible(false);
            new Home().setVisible(true);
        }
    }

    private boolean validateInput() {
        if (bookTitle.getText().trim().isEmpty() || author.getText().trim().isEmpty() ||
                isbn.getText().trim().isEmpty() || quantity.getText().trim().isEmpty() || available.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled out", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isNumeric(quantity.getText())) {
            JOptionPane.showMessageDialog(null, "Quantity must be a valid integer", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isNumeric(available.getText())) {
            JOptionPane.showMessageDialog(null, "Available must be a valid integer", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
