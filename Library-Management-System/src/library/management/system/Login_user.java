package library.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.Pattern;

public class Login_user extends JFrame implements ActionListener {

    private JTextField textField;
    private JPasswordField passwordField;
    private JButton b1, b2, b3;

    public Login_user() {
        setTitle("Login");
        setBounds(600, 300, 600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 2)); // Split the frame into two columns

        // Create the left panel
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(245, 245, 220)); // Beige background
        leftPanel.setLayout(null);

        // Create the right panel
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(245, 245, 220)); // Beige background
        rightPanel.setLayout(null);

        // Add components to the left panel
        JLabel l1 = new JLabel("Username : ");
        l1.setBounds(50, 80, 95, 24);
        leftPanel.add(l1);

        JLabel l2 = new JLabel("Password : ");
        l2.setBounds(50, 115, 95, 24);
        leftPanel.add(l2);

        textField = new JTextField();
        textField.setBounds(150, 80, 200, 24);
        leftPanel.add(textField);

        passwordField = new JPasswordField();
        passwordField.setBounds(150, 115, 200, 24);
        leftPanel.add(passwordField);

        b1 = new JButton("Login");
        b1.addActionListener(this);
        b1.setForeground(Color.WHITE); // White text
        b1.setBackground(Color.BLACK); // Black background
        b1.setBounds(50, 170, 100, 30);
        leftPanel.add(b1);

        b2 = new JButton("SignUp");
        b2.addActionListener(this);
        b2.setForeground(Color.WHITE); // White text
        b2.setBackground(Color.BLACK); // Black background
        b2.setBounds(170, 170, 100, 30);
        leftPanel.add(b2);

        b3 = new JButton("Forgot Password");
        b3.addActionListener(this);
        b3.setForeground(Color.WHITE); // White text
        b3.setBackground(Color.BLACK); // Black background
        b3.setBounds(50, 220, 220, 30);
        leftPanel.add(b3);

        JLabel l5 = new JLabel("Trouble in Login?");
        l5.setFont(new Font("Tahoma", Font.PLAIN, 15));
        l5.setBounds(50, 260, 150, 20);
        leftPanel.add(l5);

        // Add components to the right panel
        JLabel l6 = new JLabel("Welcome to the Library Management System");
        l6.setBounds(20, 100, 300, 50);
        rightPanel.add(l6);

        // Add panels to the frame
        add(leftPanel);
        add(rightPanel);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            if (validateInput()) {  // Added validation check
                boolean status = false;
                Connection conn = null;
                PreparedStatement st = null;
                ResultSet rs = null;
                try {
                    conn = DatabaseConnection.getConnection();
                    String sql = "SELECT * FROM Admins WHERE email=? AND password=?";
                    st = conn.prepareStatement(sql);
                    st.setString(1, textField.getText());
                    st.setString(2, new String(passwordField.getPassword()));

                    rs = st.executeQuery();

                    if (rs.next()) {
                        this.setVisible(false);
                        new Loading().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid Login...!.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (rs != null) rs.close();
                        if (st != null) st.close();
                        if (conn != null) conn.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (ae.getSource() == b2) {
            setVisible(false);
            Signup su = new Signup();
            su.setVisible(true);
        }
        if (ae.getSource() == b3) {
            setVisible(false);
            // Uncomment and implement the Forgot password functionality if needed
            // Forgot forgot = new Forgot();
            // forgot.setVisible(true);
        }
    }

    private boolean validateInput() {  // Validation method
        String userEmail = textField.getText().trim();
        String userPassword = new String(passwordField.getPassword()).trim();

        if (userEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isValidEmail(userEmail)) {  // Email validation
            JOptionPane.showMessageDialog(this, "Invalid email format", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (userPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Password cannot be empty", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {  // Email validation method
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    public static void main(String[] args) {
        new Login_user().setVisible(true);
    }
}
