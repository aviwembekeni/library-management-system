package library.management.system;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class LibraryManagementSystem extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1;

    public LibraryManagementSystem() {
        // Set size and layout
        setSize(800, 600); // Adjusted size for better vertical space
        setLayout(null);
        setLocation(300, 100); // Adjusted location to center the window vertically

        // Initialize components
        l1 = new JLabel("");
        b1 = new JButton("Next");

        b1.setBackground(Color.WHITE);
        b1.setForeground(Color.BLACK);

        // Load and scale the image
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/first.png"));
        Image i3 = i1.getImage().getScaledInstance(800, 600, Image.SCALE_DEFAULT);
        ImageIcon i2 = new ImageIcon(i3);
        l1 = new JLabel(i2);

        // Set bounds for the button to be more vertical
        b1.setBounds(50, 500, 200, 60); // Positioned lower and closer to the left

        // Set bounds for the background label
        l1.setBounds(0, 0, 800, 600);

        // Add components
        l1.add(b1);
        add(l1);

        // Add action listener
        b1.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
//        new Login_user().setVisible(true);
        this.setVisible(false);

          // Create and display the new window
        Login_user login_user = new Login_user();
        login_user.setVisible(true);
    }

    public static void main(String[] args) {
        LibraryManagementSystem window = new LibraryManagementSystem();
        window.setVisible(true);
    }
}
