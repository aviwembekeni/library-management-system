package library.management.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.sql.*;

public class ReturnBook extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField textField, textField_1, textField_2, textField_3, textField_4, textField_5, textField_6;
    private JButton b1, b2, b3;
    private JDateChooser dateChooser;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ReturnBook().setVisible(true));
    }

    public void delete() {
        try {
            Connection conn = DatabaseConnection.getConnection();
            String sql = "delete from Transactions where book_id=?";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, textField.getText());
            int i = st.executeUpdate();
            if (i > 0)
                JOptionPane.showMessageDialog(null, "Book Returned");
            else
                JOptionPane.showMessageDialog(null, "Error in Deleting");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            e.printStackTrace();
        }
    }

    public ReturnBook() {
        setTitle("Return Book");
        setBounds(450, 300, 617, 363);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Return Book");
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        title.setForeground(new Color(25, 25, 112));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(title, gbc);

        // Book ID
        JLabel lblNewLabel = new JLabel("Book ID:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(lblNewLabel, gbc);

        textField = new JTextField(10);
        gbc.gridx = 1;
        contentPane.add(textField, gbc);

        // Student ID
        JLabel lblStudentid = new JLabel("Student ID:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(lblStudentid, gbc);

        textField_1 = new JTextField(10);
        gbc.gridx = 1;
        contentPane.add(textField_1, gbc);

        // Search Button
        b1 = new JButton("Search");
        b1.addActionListener(this);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        contentPane.add(b1, gbc);

        // Book Details
        JLabel lblBook = new JLabel("Book:");
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(lblBook, gbc);

        textField_2 = new JTextField(20);
        textField_2.setEditable(false);
        gbc.gridx = 1;
        contentPane.add(textField_2, gbc);

        JLabel lblName = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPane.add(lblName, gbc);

        textField_3 = new JTextField(20);
        textField_3.setEditable(false);
        gbc.gridx = 1;
        contentPane.add(textField_3, gbc);

        JLabel lblCourse = new JLabel("Course:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPane.add(lblCourse, gbc);

        textField_4 = new JTextField(20);
        textField_4.setEditable(false);
        gbc.gridx = 1;
        contentPane.add(textField_4, gbc);

        JLabel lblBranch = new JLabel("Date of Issue:");
        gbc.gridx = 0;
        gbc.gridy = 6;
        contentPane.add(lblBranch, gbc);

        textField_5 = new JTextField(20);
        textField_5.setEditable(false);
        gbc.gridx = 1;
        contentPane.add(textField_5, gbc);

//        JLabel lblDateOfIssue = new JLabel("Date of Issue:");
//        gbc.gridx = 0;
//        gbc.gridy = 7;
//        contentPane.add(lblDateOfIssue, gbc);

        textField_6 = new JTextField(20);
        textField_6.setEditable(false);
        gbc.gridx = 1;
        contentPane.add(textField_6, gbc);


        // Return Button
        b2 = new JButton("Return");
        b2.addActionListener(this);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 9;
        contentPane.add(b2, gbc);

        // Back Button
        b3 = new JButton("Back");
        b3.addActionListener(this);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        gbc.gridx = 1;
        contentPane.add(b3, gbc);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 69, 0), 2, true), "Return-Panel",
                TitledBorder.LEADING, TitledBorder.TOP, null, new Color(220, 20, 60)));
        panel.setBackground(Color.WHITE);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 10;
        contentPane.add(panel, gbc);
    }

    public void actionPerformed(ActionEvent ae) {
        try {
            Connection conn = DatabaseConnection.getConnection();

            String bookId = textField.getText();
            String studentId = textField_1.getText();

            System.out.println("book" + bookId);
            System.out.println("Student" + studentId);

            if (ae.getSource() == b1) {
                String sql = """
                        SELECT\s
                            Books.title,
                            Students.name,
                            Students.course,
                            Transactions.borrow_date
                        FROM\s
                            Transactions
                        JOIN\s
                            Books ON Transactions.book_id = Books.id
                        JOIN\s
                            Students ON Transactions.student_id = Students.id
                        WHERE\s
                            Transactions.book_id = ? AND
                            Transactions.student_id = ? AND
                            status = "borrowed";
                        """;
                PreparedStatement st = conn.prepareStatement(sql);
                st.setString(1, bookId);
                st.setString(2, studentId);
                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    textField_2.setText(rs.getString("title"));
                    textField_3.setText(rs.getString("name"));
                    textField_4.setText(rs.getString("course"));
                    textField_5.setText(rs.getString("borrow_date"));
//                    textField_6.setText(rs.getString("dateOfIssue"));
                } else {
                    JOptionPane.showMessageDialog(null, "No Record Found");
                }
                st.close();
                rs.close();

            }
            if (ae.getSource() == b2) {



//                String returnDate = returnDateField.getText();
                java.util.Date utilDate = new java.util.Date();
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());


                String query = "SELECT * FROM Transactions WHERE book_id = ? AND student_id = ? AND status = 'borrowed'";
                PreparedStatement st = conn.prepareStatement(query);
                st.setString(1, bookId);
                st.setString(2, studentId);

                ResultSet rs = st.executeQuery();

                if (rs.next()) {
                    String updateTransaction = "UPDATE Transactions SET return_date = ?, status = 'returned' WHERE id = ?";
                    PreparedStatement st1 = conn.prepareStatement(updateTransaction);
                    st1.setDate(1, sqlDate);
                    st1.setInt(2, rs.getInt("id"));
                    st1.executeUpdate();

                    String updateBook = "UPDATE Books SET available = available + 1 WHERE id = ?";
                    PreparedStatement st2 = conn.prepareStatement(updateBook);
                    st2.setString(1, bookId);
                    st2.executeUpdate();

                    textField_2.setText("");
                    textField_3.setText("");
                    textField_4.setText("");
                    textField_5.setText("");
//

                    JOptionPane.showMessageDialog(null, "Book Returned Successfully");
                } else {
                    JOptionPane.showMessageDialog(null, "No record found for this Book ID and Student ID");
                }

                rs.close();
                st.close();
                conn.close();
            }
            if (ae.getSource() == b3) {
                this.setVisible(false);
                new Home().setVisible(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
