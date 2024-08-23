package library.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.border.*;

public class Signup extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField name;
	private JTextField email;
	private JPasswordField password;
	private JPasswordField confirmPassword; // Added confirm password field
	private JButton b1, b2;

	public static void main(String[] args) {
		new Signup().setVisible(true);
	}

	public Signup() {
		setBounds(600, 250, 606, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name :");
		lblName.setForeground(Color.DARK_GRAY);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblName.setBounds(99, 86, 92, 26);
		contentPane.add(lblName);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setForeground(Color.DARK_GRAY);
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblEmail.setBounds(99, 123, 92, 26);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(Color.DARK_GRAY);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(99, 160, 92, 26);
		contentPane.add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password :"); // Label for confirm password
		lblConfirmPassword.setForeground(Color.DARK_GRAY);
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblConfirmPassword.setBounds(99, 197, 140, 26);
		contentPane.add(lblConfirmPassword);

		name = new JTextField();
		name.setBounds(265, 91, 148, 20);
		contentPane.add(name);
		name.setColumns(10);

		email = new JTextField();
		email.setColumns(10);
		email.setBounds(265, 128, 148, 20);
		contentPane.add(email);

		password = new JPasswordField();
		password.setColumns(10);
		password.setBounds(265, 165, 148, 20);
		contentPane.add(password);

		confirmPassword = new JPasswordField(); // Confirm password field
		confirmPassword.setColumns(10);
		confirmPassword.setBounds(265, 202, 148, 20);
		contentPane.add(confirmPassword);

		b1 = new JButton("Create");
		b1.addActionListener(this);
		b1.setFont(new Font("Tahoma", Font.BOLD, 13));
		b1.setBounds(140, 289, 100, 30);
		b1.setBackground(Color.BLACK);
		b1.setForeground(Color.WHITE);
		contentPane.add(b1);

		b2 = new JButton("Back");
		b2.addActionListener(this);
		b2.setFont(new Font("Tahoma", Font.BOLD, 13));
		b2.setBounds(300, 289, 100, 30);
		b2.setBackground(Color.BLACK);
		b2.setForeground(Color.WHITE);
		contentPane.add(b2);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(34, 139, 34));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(128, 128, 0), 2), "Create-Account",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(34, 139, 34)));
		panel.setBounds(31, 46, 476, 296);
		panel.setBackground(Color.WHITE);
		contentPane.add(panel);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == b1) {
			if (validateInput()) {
				try {
					Connection conn = DatabaseConnection.getConnection();
					String sql = "INSERT INTO Admins(name, email, password) VALUES(?, ?, ?)";
					PreparedStatement st = conn.prepareStatement(sql);

					st.setString(1, name.getText());
					st.setString(2, email.getText());
					st.setString(3, new String(password.getPassword()));

					int i = st.executeUpdate();
					if (i > 0) {
						JOptionPane.showMessageDialog(null, "Account successfully created");
						this.setVisible(false);
						new Login_user().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "Error creating account");
					}
					st.close();
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else if (ae.getSource() == b2) {
			this.setVisible(false);
			new Login_user().setVisible(true);
		}
	}

	private boolean validateInput() {
		String userName = name.getText().trim();
		String userEmail = email.getText().trim();
		String userPassword = new String(password.getPassword()).trim();
		String userConfirmPassword = new String(confirmPassword.getPassword()).trim(); // Get confirm password

		if (userName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Name cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (userEmail.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Email cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!isValidEmail(userEmail)) {
			JOptionPane.showMessageDialog(this, "Invalid email format", "Input Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (userPassword.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Password cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (userPassword.length() < 8) {
			JOptionPane.showMessageDialog(this, "Password must be at least 8 characters long", "Input Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!hasRequiredPasswordComplexity(userPassword)) {
			JOptionPane.showMessageDialog(this, "Password must contain at least one uppercase letter, one lowercase letter, and one digit", "Input Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (!userPassword.equals(userConfirmPassword)) { // Check if passwords match
			JOptionPane.showMessageDialog(this, "Passwords do not match", "Input Error", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		Pattern pat = Pattern.compile(emailRegex);
		return pat.matcher(email).matches();
	}

	private boolean hasRequiredPasswordComplexity(String password) {
		String upperCaseChars = "(.*[A-Z].*)";
		String lowerCaseChars = "(.*[a-z].*)";
		String digits = "(.*\\d.*)";
		return password.matches(upperCaseChars) && password.matches(lowerCaseChars) && password.matches(digits);
	}
}
