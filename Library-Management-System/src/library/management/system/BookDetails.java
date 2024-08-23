package library.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import net.proteanit.sql.DbUtils;
import java.sql.*;
import java.awt.event.*;

public class BookDetails extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTable table;
	private JTextField search;
	private JButton b1, b2;
	private JComboBox<String> genreComboBox; // ComboBox for genres

	public static void main(String[] args) {
		new BookDetails().setVisible(true);
	}

	public void Book() {
		try {
			Connection conn = DatabaseConnection.getConnection();
			String sql = "SELECT * FROM Books";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			st.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public BookDetails() {
		setBounds(350, 200, 890, 475);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(79, 133, 771, 282);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				search.setText(table.getModel().getValueAt(row, 1).toString());
			}
		});
		table.setBackground(new Color(240, 248, 255));
		table.setForeground(Color.DARK_GRAY);
		table.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		scrollPane.setViewportView(table);

		b1 = new JButton("Search");
		b1.addActionListener(this);
		b1.setBorder(new LineBorder(new Color(255, 20, 147), 2, true));
		ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/eight.png"));
		Image i2 = i1.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon i3 = new ImageIcon(i2);
		b1.setIcon(i3);
		b1.setForeground(new Color(199, 21, 133));
		b1.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		b1.setBounds(564, 89, 138, 33);
		contentPane.add(b1);

		b2 = new JButton("Delete");
		b2.addActionListener(this);
		ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/nineth.png"));
		Image i5 = i4.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);
		ImageIcon i6 = new ImageIcon(i5);
		b2.setIcon(i6);
		b2.setForeground(new Color(199, 21, 133));
		b2.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		b2.setBorder(new LineBorder(new Color(255, 20, 147), 2, true));
		b2.setBounds(712, 89, 138, 33);
		contentPane.add(b2);

		JLabel l1 = new JLabel("Book Details");
		l1.setForeground(new Color(107, 142, 35));
		l1.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 30));
		l1.setBounds(300, 15, 400, 47);
		contentPane.add(l1);

		search = new JTextField();
		search.setBackground(new Color(255, 240, 245));
		search.setBorder(new LineBorder(new Color(255, 105, 180), 2, true));
		search.setForeground(new Color(47, 79, 79));
		search.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 17));
		search.setBounds(189, 89, 357, 33);
		contentPane.add(search);
		search.setColumns(10);

		// Add a ComboBox for genre filtering
		genreComboBox = new JComboBox<>(new String[]{"All Genres", "Fiction", "Dystopian", "Classic", "Adventure", "Romance", "Literary Fiction", "Fantasy", "Science Fiction"});
		genreComboBox.setBounds(189, 55, 357, 33);
		genreComboBox.setFont(new Font("Trebuchet MS", Font.BOLD | Font.ITALIC, 17));
		contentPane.add(genreComboBox);

		JLabel l3 = new JLabel("Back");
		l3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setVisible(false);
				Home home = new Home();
				home.setVisible(true);
			}
		});
		l3.setForeground(Color.GRAY);
		l3.setFont(new Font("Trebuchet MS", Font.BOLD, 18));
		ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/tenth.png"));
		Image i8 = i7.getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT);
		ImageIcon i9 = new ImageIcon(i8);
		l3.setIcon(i9);
		l3.setBounds(97, 89, 72, 33);
		contentPane.add(l3);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 128, 128), 3, true), "Book-Details",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 128, 0)));
		panel.setBounds(67, 54, 793, 368);
		contentPane.add(panel);
		panel.setBackground(Color.WHITE);
		Book();
	}

	public void actionPerformed(ActionEvent ae) {
		try (Connection conn = DatabaseConnection.getConnection()) {
			if (ae.getSource() == b1) {
				String genre = genreComboBox.getSelectedItem().toString();
				String sql;

				if (genre.equals("All Genres")) {
					sql = "SELECT * FROM Books WHERE CONCAT(title, id) LIKE ?";
				} else {
					sql = "SELECT * FROM Books WHERE genre = ? AND CONCAT(title, id) LIKE ?";
				}

				PreparedStatement st = conn.prepareStatement(sql);

				if (genre.equals("All Genres")) {
					st.setString(1, "%" + search.getText() + "%");
				} else {
					st.setString(1, genre);
					st.setString(2, "%" + search.getText() + "%");
				}

				ResultSet rs = st.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(rs));
				rs.close();
				st.close();
			}

			if (ae.getSource() == b2) {
				String bookTitle = search.getText();
				String sqlCheck = "SELECT id, quantity, available FROM Books WHERE title = ?";
				PreparedStatement stCheck = conn.prepareStatement(sqlCheck);
				stCheck.setString(1, bookTitle);
				ResultSet rsCheck = stCheck.executeQuery();

				if (rsCheck.next()) {
					int bookId = rsCheck.getInt("id");
					int quantity = rsCheck.getInt("quantity");
					int available = rsCheck.getInt("available");

					// Check for related transactions
					String sqlTransCheck = "SELECT COUNT(*) FROM Transactions WHERE book_id = ?";
					PreparedStatement stTransCheck = conn.prepareStatement(sqlTransCheck);
					stTransCheck.setInt(1, bookId);
					ResultSet rsTransCheck = stTransCheck.executeQuery();

					rsTransCheck.next();
					int transCount = rsTransCheck.getInt(1);

					if (transCount > 0) {
						JOptionPane.showMessageDialog(null, "Cannot delete the book as it is referenced in Transactions.");
					} else {
						if (quantity > 0 && available > 0) {
							// Decrement quantity
							String sqlUpdate = "UPDATE Books SET quantity = ?, available = ? WHERE title = ?";
							PreparedStatement stUpdate = conn.prepareStatement(sqlUpdate);
							stUpdate.setInt(1, quantity - 1);
							stUpdate.setInt(2, available - 1);
							stUpdate.setString(3, bookTitle);
							stUpdate.executeUpdate();
							stUpdate.close();

							// Delete the book if quantity is zero
							if (quantity - 1 == 0) {
								String sqlDelete = "DELETE FROM Books WHERE title = ?";
								PreparedStatement stDelete = conn.prepareStatement(sqlDelete);
								stDelete.setString(1, bookTitle);
								stDelete.executeUpdate();
								stDelete.close();
								JOptionPane.showMessageDialog(null, "Book removed completely as quantity reached zero!");
							} else {
								JOptionPane.showMessageDialog(null, "Book quantity decremented by one.");
							}
						} else {
							JOptionPane.showMessageDialog(null, "No books available for deletion.");
						}
					}
					rsTransCheck.close();
					stTransCheck.close();
				} else {
					JOptionPane.showMessageDialog(null, "Book not found.");
				}
				rsCheck.close();
				stCheck.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
