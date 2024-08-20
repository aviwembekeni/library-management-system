package library.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import com.toedter.calendar.JDateChooser;
import java.awt.event.*;
import java.sql.*;

public class IssueBook extends JFrame implements ActionListener{

    private JPanel contentPane;
    JDateChooser dateChooser;
    private JTextField t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,t14, t15;
    private JButton b1,b2,b3,b4;

    public static void main(String[] args) {
	new IssueBook().setVisible(true);

    }

    public IssueBook() {
        setBounds(300, 200, 900, 500);
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
        contentPane.setBackground(Color.WHITE);
	contentPane.setLayout(null);

	JLabel l1 = new JLabel("Book ID");
	l1.setFont(new Font("Tahoma", Font.BOLD, 14));
	l1.setForeground(new Color(47, 79, 79));
	l1.setBounds(47, 63, 100, 23);
	contentPane.add(l1);

	JLabel l2 = new JLabel("Title");
	l2.setForeground(new Color(47, 79, 79));
	l2.setFont(new Font("Tahoma", Font.BOLD, 14));
	l2.setBounds(47, 97, 100, 23);
	contentPane.add(l2);

	JLabel l3 = new JLabel("ISBN");
	l3.setForeground(new Color(47, 79, 79));
	l3.setFont(new Font("Tahoma", Font.BOLD, 14));
	l3.setBounds(47, 131, 100, 23);
	contentPane.add(l3);

	JLabel l4 = new JLabel("Author");
	l4.setForeground(new Color(47, 79, 79));
	l4.setFont(new Font("Tahoma", Font.BOLD, 14));
	l4.setBounds(47, 165, 100, 23);
	contentPane.add(l4);

	JLabel l5 = new JLabel("Available");
	l5.setForeground(new Color(47, 79, 79));
	l5.setFont(new Font("Tahoma", Font.BOLD, 14));
	l5.setBounds(47, 199, 100, 23);
	contentPane.add(l5);

//	JLabel l6 = new JLabel("Price");
//	l6.setForeground(new Color(47, 79, 79));
//	l6.setFont(new Font("Tahoma", Font.BOLD, 14));
//	l6.setBounds(47, 233, 100, 23);
//	contentPane.add(l6);
//
//	JLabel l7 = new JLabel("Pages");
//	l7.setForeground(new Color(47, 79, 79));
//	l7.setFont(new Font("Tahoma", Font.BOLD, 14));
//	l7.setBounds(47, 267, 100, 23);
//	contentPane.add(l7);

	t1 = new JTextField();
	t1.setForeground(new Color(47, 79, 79));
	t1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t1.setBounds(126, 66, 86, 20);
	contentPane.add(t1);

	b1 = new JButton("Search");
	b1.addActionListener(this);
	b1.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
	b1.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	b1.setBounds(234, 59, 100, 30);

	contentPane.add(b1);

	t2 = new JTextField();
	t2.setEditable(false);
	t2.setForeground(new Color(47, 79, 79));
	t2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t2.setBounds(126, 100, 208, 20);
	contentPane.add(t2);
	t2.setColumns(10);

	t3 = new JTextField();
	t3.setEditable(false);
	t3.setForeground(new Color(47, 79, 79));
	t3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t3.setColumns(10);
	t3.setBounds(126, 131, 208, 20);
	contentPane.add(t3);

	t4 = new JTextField();
	t4.setEditable(false);
	t4.setForeground(new Color(47, 79, 79));
	t4.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t4.setColumns(10);
	t4.setBounds(126, 168, 208, 20);
	contentPane.add(t4);

	t5 = new JTextField();
	t5.setEditable(false);
	t5.setForeground(new Color(47, 79, 79));
	t5.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t5.setColumns(10);
	t5.setBounds(126, 202, 208, 20);
	contentPane.add(t5);

//	t6 = new JTextField();
//	t6.setEditable(false);
//	t6.setForeground(new Color(47, 79, 79));
//	t6.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
//	t6.setColumns(10);
//	t6.setBounds(126, 236, 208, 20);
//	contentPane.add(t6);
//
//	t7 = new JTextField();
//	t7.setEditable(false);
//	t7.setForeground(new Color(47, 79, 79));
//	t7.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
//	t7.setColumns(10);
//	t7.setBounds(126, 270, 208, 20);
//	contentPane.add(t7);

	JPanel panel = new JPanel();
	panel.setBorder(new TitledBorder(new LineBorder(new Color(47, 79, 79), 2, true), "Issue-Book",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(34, 139, 34)));
	panel.setFont(new Font("Tahoma", Font.BOLD, 14));
	panel.setBounds(10, 38, 345, 288);
        panel.setBackground(Color.WHITE);
	contentPane.add(panel);

	JLabel l8 = new JLabel("Student ID");
	l8.setForeground(new Color(47, 79, 79));
	l8.setFont(new Font("Tahoma", Font.BOLD, 14));
	l8.setBounds(384, 63, 100, 23);
	contentPane.add(l8);

	JLabel l9 = new JLabel("Name");
	l9.setForeground(new Color(47, 79, 79));
	l9.setFont(new Font("Tahoma", Font.BOLD, 14));
	l9.setBounds(384, 103, 100, 23);
	contentPane.add(l9);

	JLabel l10 = new JLabel("Email");
	l10.setForeground(new Color(47, 79, 79));
	l10.setFont(new Font("Tahoma", Font.BOLD, 14));
	l10.setBounds(384, 147, 100, 23);
	contentPane.add(l10);

	JLabel l11 = new JLabel("Roll Number");
	l11.setForeground(new Color(47, 79, 79));
	l11.setFont(new Font("Tahoma", Font.BOLD, 14));
	l11.setBounds(384, 187, 100, 23);
	contentPane.add(l11);

	JLabel l12 = new JLabel("Course");
	l12.setForeground(new Color(47, 79, 79));
	l12.setFont(new Font("Tahoma", Font.BOLD, 14));
	l12.setBounds(384, 233, 100, 23);
	contentPane.add(l12);

	JLabel l13 = new JLabel("Year");
	l13.setForeground(new Color(47, 79, 79));
	l13.setFont(new Font("Tahoma", Font.BOLD, 14));
	l13.setBounds(384, 233, 100, 23);
	contentPane.add(l13);




	t8 = new JTextField();
	t8.setForeground(new Color(47, 79, 79));
	t8.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t8.setColumns(10);
	t8.setBounds(508, 66, 86, 20);
	contentPane.add(t8);

	b2 = new JButton("Search");
	b2.addActionListener(this);
	b2.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	b2.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
	b2.setBounds(604, 59, 100, 30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
	contentPane.add(b2);

	t9 = new JTextField();
	t9.setForeground(new Color(47, 79, 79));
	t9.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t9.setEditable(false);
	t9.setColumns(10);
	t9.setBounds(508, 106, 208, 20);
	contentPane.add(t9);

	t10 = new JTextField();
	t10.setForeground(new Color(47, 79, 79));
	t10.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t10.setEditable(false);
	t10.setColumns(10);
	t10.setBounds(508, 150, 208, 20);
	contentPane.add(t10);

	t11 = new JTextField();
	t11.setForeground(new Color(47, 79, 79));
	t11.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t11.setEditable(false);
	t11.setColumns(10);
	t11.setBounds(508, 190, 208, 20);
	contentPane.add(t11);

	t12 = new JTextField();
	t12.setForeground(new Color(47, 79, 79));
	t12.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t12.setEditable(false);
	t12.setColumns(10);
	t12.setBounds(508, 236, 208, 20);
	contentPane.add(t12);


	JPanel panel_1 = new JPanel();
	panel_1.setBorder(new TitledBorder(new LineBorder(new Color(70, 130, 180), 2, true), "Student Details",
			TitledBorder.LEADING, TitledBorder.TOP, null, new Color(100, 149, 237)));
	panel_1.setForeground(new Color(0, 100, 0));
	panel_1.setBounds(360, 38, 378, 372);
	panel_1.setBackground(Color.WHITE);
	panel_1.setLayout(null); // Set layout to null for absolute positioning
	contentPane.add(panel_1);

// Labels and Text Fields for panel_1
	JLabel l16 = new JLabel("Admin ID :");
	l16.setForeground(new Color(105, 105, 105));
	l16.setFont(new Font("Trebuchet MS", Font.BOLD, 15));
	l16.setBounds(20, 300, 118, 26); // Adjusted y-coordinate
	panel_1.add(l16);

	t15 = new JTextField();
	t15.setForeground(new Color(47, 79, 79));
	t15.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	t15.setColumns(10);
	t15.setBounds(137, 300, 200, 20); // Adjusted y-coordinate
	panel_1.add(t15);


	b3 = new JButton("Issue");
	b3.addActionListener(this);
	b3.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	b3.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
	b3.setBounds(47, 377, 118, 33);
	b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        contentPane.add(b3);

	b4 = new JButton("Back");
	b4.addActionListener(this);
	b4.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
	b4.setBorder(new LineBorder(new Color(192, 192, 192), 1, true));
	b4.setBounds(199, 377, 100, 33);
	b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        contentPane.add(b4);
    }

    public void actionPerformed(ActionEvent ae){
        try{
            Connection  conn = DatabaseConnection.getConnection();
            if(ae.getSource() == b1){
                String sql = "select * from Books where id = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, t1.getText());
		ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    t2.setText(rs.getString("title"));
                    t3.setText(rs.getString("isbn"));
                    t4.setText(rs.getString("author"));
                    t5.setText(rs.getString("available"));
//                    t6.setText(rs.getString("price"));
//                    t7.setText(rs.getString("pages"));
		}
//				if(rs.next()) {
//					JOptionPane.showMessageDialog(null, "0 records found");
//				}
		st.close();
		rs.close();

            }
            if(ae.getSource() == b2){
                String sql = "select * from Students where id = ?";
		PreparedStatement st = conn.prepareStatement(sql);
		st.setString(1, t8.getText());
		ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    t9.setText(rs.getString("name"));
                    t10.setText(rs.getString("email"));
                    t11.setText(rs.getString("roll_number"));
                    t12.setText(rs.getString("course"));
                    t13.setText(rs.getString("year"));
		}
		st.close();
		rs.close();

            }
            if(ae.getSource() == b3){
                    try{
						int bookId = Integer.parseInt(t1.getText());
						int studentId = Integer.parseInt(t8.getText());
						int adminId = Integer.parseInt(t15.getText());
//						java.util.Date utilDate = new java.util.Date();
//						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

//						java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

						// Check if the book is available
						String checkBookQuery = "SELECT available FROM Books WHERE id = ?";
						PreparedStatement	pstmt = conn.prepareStatement(checkBookQuery);
						pstmt.setInt(1, bookId);
						ResultSet rs = pstmt.executeQuery();

						if (rs.next()) {
							int available = rs.getInt("available");

							if (available > 0) {
								// Update book availability
								String updateBookQuery = "UPDATE Books SET available = available - 1 WHERE id = ?";
								pstmt = conn.prepareStatement(updateBookQuery);
								pstmt.setInt(1, bookId);
								pstmt.executeUpdate();

								// Record the transaction
								String insertTransactionQuery = "INSERT INTO Transactions (book_id, student_id, admin_id) VALUES (?, ?, ?)";
								pstmt = conn.prepareStatement(insertTransactionQuery);
								pstmt.setInt(1, bookId);
								pstmt.setInt(2, studentId);
								pstmt.setInt(3, adminId);
//								pstmt.setDate(4, sqlDate);
								pstmt.executeUpdate();

								JOptionPane.showMessageDialog(null, "Book issued successfully!");
							} else {
								JOptionPane.showMessageDialog(null, "This book is currently out of stock.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "Book not found in the database.");
						}
                    }catch(Exception e){
                        e.printStackTrace();
                                }
            }
            if(ae.getSource() == b4){
                this.setVisible(false);
		new Home().setVisible(true);

            }

            conn.close();
        }catch(Exception e){

        }
    }
}
