/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library.management.system;

import java.awt.*;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;
import java.util.Arrays;
import javax.swing.border.*;

public class Signup extends JFrame implements ActionListener{

    private JPanel contentPane;
    private JTextField name;
    private JTextField email;
    private JPasswordField password;
//    private JTextField textField_3;
    private JButton b1, b2;
//    private JComboBox comboBox;


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

//	JLabel lblAnswer = new JLabel("Answer :");
//	lblAnswer.setForeground(Color.DARK_GRAY);
//	lblAnswer.setFont(new Font("Tahoma", Font.BOLD, 14));
//	lblAnswer.setBounds(99, 234, 92, 26);
//	contentPane.add(lblAnswer);

//	comboBox = new JComboBox();
//	comboBox.setModel(new DefaultComboBoxModel(new String[] { "Your NickName?", "Your Lucky Number?",
//			"Your child SuperHero?", "Your childhood Name ?" }));
//	comboBox.setBounds(265, 202, 148, 20);
//	contentPane.add(comboBox);

//	JLabel lblSecurityQuestion = new JLabel("Security Question :");
//	lblSecurityQuestion.setForeground(Color.DARK_GRAY);
//	lblSecurityQuestion.setFont(new Font("Tahoma", Font.BOLD, 14));
//	lblSecurityQuestion.setBounds(99, 197, 140, 26);
//	contentPane.add(lblSecurityQuestion);

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

//	textField_3 = new JTextField();
//	textField_3.setColumns(10);
//	textField_3.setBounds(265, 239, 148, 20);
//	contentPane.add(textField_3);

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

    public void actionPerformed(ActionEvent ae){
        try{
            Connection conn = DatabaseConnection.getConnection();

            if(ae.getSource() == b1){
                String sql = "insert into Admins(name, email, password) values(?, ?, ?)";
		PreparedStatement st = conn.prepareStatement(sql);

		st.setString(1, name.getText());
                st.setString(2, email.getText());
		st.setString(3, new String(password.getPassword()));
//		st.setString(4, (String) comboBox.getSelectedItem());
//		st.setString(5, name_3.getText());

		int i = st.executeUpdate();
		if (i > 0){
                    JOptionPane.showMessageDialog(null, "successfully Created");
                }

                name.setText("");
                email.setText("");
				password.setText("");
				this.setVisible(false);
				new Login_user().setVisible(true);
//		textField_3.setText("");
            }
            if(ae.getSource() == b2){
                this.setVisible(false);
				new Login_user().setVisible(true);

            }
        }catch(Exception e){

        }
    }
}

