package library.management.system;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JFrame implements ActionListener {

    private JPanel contentPane;
    private JButton b1, b2, b3, b4, b5, b6;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Home().setVisible(true));
    }

    public Home() {
        setTitle("Library Management System");
        setBounds(400, 150, 1000, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel(new GridBagLayout());
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Menu Bar
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(0, 128, 0), new Color(128, 128, 128)));
        menuBar.setBackground(Color.CYAN);

        JMenu mnExit = new JMenu("Exit");
        mnExit.setFont(new Font("Trebuchet MS", Font.BOLD, 17));

        JMenuItem mntmLogout = new JMenuItem("Logout");
        mntmLogout.setBackground(new Color(211, 211, 211));
        mntmLogout.setForeground(new Color(105, 105, 105));
        mntmLogout.addActionListener(this);
        mnExit.add(mntmLogout);

        JMenuItem mntmExit = new JMenuItem("Exit");
        mntmExit.setForeground(new Color(105, 105, 105));
        mntmExit.setBackground(new Color(211, 211, 211));
        mntmExit.addActionListener(this);
        mnExit.add(mntmExit);

        JMenu mnHelp = new JMenu("Help");
        mnHelp.setFont(new Font("Trebuchet MS", Font.BOLD, 17));

        JMenuItem mntmReadme = new JMenuItem("Read Me");
        mntmReadme.setBackground(new Color(211, 211, 211));
        mntmReadme.setForeground(new Color(105, 105, 105));
        mnHelp.add(mntmReadme);

        JMenuItem mntmAboutUs = new JMenuItem("About Us");
        mntmAboutUs.setForeground(new Color(105, 105, 105));
        mntmAboutUs.setBackground(new Color(211, 211, 211));
        mntmAboutUs.addActionListener(this);
        mnHelp.add(mntmAboutUs);

        JMenu mnRecord = new JMenu("Record");
        mnRecord.setFont(new Font("Trebuchet MS", Font.BOLD, 17));

        JMenuItem bookdetails = new JMenuItem("Book Details");
        bookdetails.addActionListener(this);
        bookdetails.setBackground(new Color(211, 211, 211));
        bookdetails.setForeground(Color.DARK_GRAY);
        mnRecord.add(bookdetails);

        JMenuItem studentdetails = new JMenuItem("Student Details");
        studentdetails.setBackground(new Color(211, 211, 211));
        studentdetails.setForeground(Color.DARK_GRAY);
        studentdetails.addActionListener(this);
        mnRecord.add(studentdetails);

        menuBar.add(mnRecord);
        menuBar.add(mnHelp);
        menuBar.add(mnExit);

        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.gridx = 0;
        gbc.gridy = 0;
        contentPane.add(menuBar, gbc);

        // Title
        JLabel l1 = new JLabel("Library Management System");
        l1.setForeground(new Color(204, 51, 102));
        l1.setFont(new Font("Segoe UI Semilight", Font.BOLD, 30));
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPane.add(l1, gbc);

        // Icons and Buttons
        JPanel operationPanel = new JPanel();
        operationPanel.setLayout(new GridBagLayout());
        GridBagConstraints opGbc = new GridBagConstraints();
        opGbc.insets = new Insets(10, 10, 10, 10);
        opGbc.anchor = GridBagConstraints.CENTER;

        // Add Books
        JLabel l2 = new JLabel(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/second.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        opGbc.gridx = 0;
        opGbc.gridy = 0;
        operationPanel.add(l2, opGbc);

        b1 = new JButton("Add Books");
        b1.addActionListener(this);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        opGbc.gridy = 1;
        operationPanel.add(b1, opGbc);

        // Statistics
        JLabel l3 = new JLabel(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/third.png")).getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
        opGbc.gridx = 1;
        opGbc.gridy = 0;
        operationPanel.add(l3, opGbc);

        b2 = new JButton("Statistics");
        b2.addActionListener(this);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        opGbc.gridy = 1;
        operationPanel.add(b2, opGbc);

        // Add Student
        JLabel l4 = new JLabel(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/fourth.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        opGbc.gridx = 2;
        opGbc.gridy = 0;
        operationPanel.add(l4, opGbc);

        b3 = new JButton("Add Student");
        b3.addActionListener(this);
        b3.setBackground(Color.BLACK);
        b3.setForeground(Color.WHITE);
        opGbc.gridy = 1;
        operationPanel.add(b3, opGbc);

        // Issue Book
        JLabel l5 = new JLabel(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/fifth.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        opGbc.gridx = 0;
        opGbc.gridy = 2;
        operationPanel.add(l5, opGbc);

        b4 = new JButton("Issue Book");
        b4.addActionListener(this);
        b4.setBackground(Color.BLACK);
        b4.setForeground(Color.WHITE);
        opGbc.gridy = 3;
        operationPanel.add(b4, opGbc);

        // Return Book
        JLabel l6 = new JLabel(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/sixth.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        opGbc.gridx = 1;
        opGbc.gridy = 2;
        operationPanel.add(l6, opGbc);

        b5 = new JButton("Return Book");
        b5.addActionListener(this);
        b5.setBackground(Color.BLACK);
        b5.setForeground(Color.WHITE);
        opGbc.gridy = 3;
        operationPanel.add(b5, opGbc);

        // About Us
        JLabel l7 = new JLabel(new ImageIcon(new ImageIcon(ClassLoader.getSystemResource("library/management/system/icons/seventh.png")).getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT)));
        opGbc.gridx = 2;
        opGbc.gridy = 2;
        operationPanel.add(l7, opGbc);

        b6 = new JButton("About Us");
        b6.addActionListener(this);
        b6.setBackground(Color.BLACK);
        b6.setForeground(Color.WHITE);
        opGbc.gridy = 3;
        operationPanel.add(b6, opGbc);

        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(operationPanel, gbc);

        getContentPane().setBackground(Color.WHITE);
        contentPane.setBackground(Color.WHITE);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        String msg = ae.getActionCommand();
        switch (msg) {
            case "Logout" -> {
                setVisible(false);
                new Login_user().setVisible(true);
            }
//            case "Exit" -> System.exit(ABORT);
//            case "Read Me" -> {
//                // Implement Read Me action
//            }
            case "About Us" -> {
                setVisible(false);
                new aboutUs().setVisible(true);
            }
            case "Book Details" -> {
                setVisible(false);
                new BookDetails().setVisible(true);
            }
            case "Student Details" -> {
                setVisible(false);
                new StudentDetails().setVisible(true);
            }
        }

        if (ae.getSource() == b1) {
            this.setVisible(false);
            new AddBook().setVisible(true);
        }
//        if (ae.getSource() == b2) {
//            this.setVisible(false);
//            new Statistics().setVisible(true);
//        }
        if (ae.getSource() == b3) {
            this.setVisible(false);
            new AddStudent().setVisible(true);
        }
        if (ae.getSource() == b4) {
            this.setVisible(false);
            new IssueBook().setVisible(true);
        }
        if (ae.getSource() == b5) {
            this.setVisible(false);
            new ReturnBook().setVisible(true);
        }
        if (ae.getSource() == b6) {
            this.setVisible(false);
            new aboutUs().setVisible(true);
        }
    }
}
