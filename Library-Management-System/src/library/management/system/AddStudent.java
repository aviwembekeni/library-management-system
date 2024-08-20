package library.management.system;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;
import java.util.regex.*;

public class AddStudent extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JTextField nameText, emailText, rollNumberText;
    private JComboBox<String> yearText, courseText;
    private JButton addButton, backButton;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddStudent().setVisible(true));
    }

    public AddStudent() {
        super("Add Student");
        setBounds(700, 200, 550, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title
        JLabel title = new JLabel("Add Student");
        title.setFont(new Font("Tahoma", Font.BOLD, 18));
        title.setForeground(new Color(25, 25, 112));
        gbc.gridwidth = 2; // Span across two columns
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(title, gbc);

        // Reset gridwidth for next components
        gbc.gridwidth = 1;

        // Roll Number
        JLabel rollNumberLabel = new JLabel("Roll Number:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        contentPane.add(rollNumberLabel, gbc);

        rollNumberText = new JTextField(20);
        gbc.gridx = 1;
        contentPane.add(rollNumberText, gbc);

        // Name
        JLabel nameLabel = new JLabel("Name:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPane.add(nameLabel, gbc);

        nameText = new JTextField(20);
        gbc.gridx = 1;
        contentPane.add(nameText, gbc);

        // Email
        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        contentPane.add(emailLabel, gbc);

        emailText = new JTextField(20);
        gbc.gridx = 1;
        contentPane.add(emailText, gbc);

        // Course
        JLabel courseLabel = new JLabel("Course:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        contentPane.add(courseLabel, gbc);

        courseText = new JComboBox<>(new String[]{"Mechanical", "CSE", "IT", "Civil", "Automobile", "EEE", "Other"});
        gbc.gridx = 1;
        contentPane.add(courseText, gbc);

        // Year
        JLabel yearLabel = new JLabel("Year:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        contentPane.add(yearLabel, gbc);

        yearText = new JComboBox<>(new String[]{"First", "Second", "Third", "Fourth"});
        gbc.gridx = 1;
        contentPane.add(yearText, gbc);

        // Buttons
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("ADD");
        addButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        addButton.setBackground(Color.BLACK);
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(this);
        buttonPanel.add(addButton);

        backButton = new JButton("Back");
        backButton.setFont(new Font("Trebuchet MS", Font.BOLD, 14));
        backButton.setBackground(Color.BLACK);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(this);
        buttonPanel.add(backButton);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2; // Span across two columns
        contentPane.add(buttonPanel, gbc);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addButton) {
            if (validateInput()) {
                try {
                    Connection conn = DatabaseConnection.getConnection();
                    String sql = "INSERT INTO Students (name, email, roll_number, course, year) VALUES (?, ?, ?, ?, ?)";
                    PreparedStatement st = conn.prepareStatement(sql);
                    st.setString(1, nameText.getText());
                    st.setString(2, emailText.getText());
                    st.setString(3, rollNumberText.getText());
                    st.setString(4, (String) courseText.getSelectedItem());
                    st.setString(5, (String) yearText.getSelectedItem());

                    int i = st.executeUpdate();
                    if (i > 0) {
                        JOptionPane.showMessageDialog(null, "Successfully Added");
                        this.setVisible(false);
                        new Home().setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Error");
                    }
                    st.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (ae.getSource() == backButton) {
            this.setVisible(false);
            new Home().setVisible(true);
        }
    }

    private boolean validateInput() {
        if (nameText.getText().trim().isEmpty() ||
                emailText.getText().trim().isEmpty() ||
                rollNumberText.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields must be filled out", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isValidEmail(emailText.getText())) {
            JOptionPane.showMessageDialog(null, "Invalid email format", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if (!isNumeric(rollNumberText.getText())) {
            JOptionPane.showMessageDialog(null, "Roll Number must be numeric", "Input Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
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
