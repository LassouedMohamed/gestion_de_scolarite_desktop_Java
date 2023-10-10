package gestion_de_scolarite;

import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;


import javax.swing.ImageIcon;

public class LoginFrame {
	
	private JFrame frame;
    private JPasswordField passwordField;
    private JTextField textField;
    private JLabel lblNewLabel_1;
    private JLabel lblNewLabel_2;
    private JLabel lbl_img;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setBackground(new Color(255, 255, 255));
		frame.setTitle("Gestion de scolarité");
		frame.setBounds(100, 100, 912, 548);		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btn_connecter = new JButton("Connecter");
		btn_connecter.setBackground(new Color(0, 128, 255));
		btn_connecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String userName = textField.getText();
                String password = passwordField.getText();
                PreparedStatement select;
                try {
                	Class.forName("com.mysql.cj.jdbc.Driver");
        			Connection con;
        			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
        			System.out.println("DataBase Connected");
        			select = con.prepareStatement("select * from user_account where mail=? and password=?");
        			select.setString(1, userName);
        			select.setString(2, password);
        			ResultSet rs = select.executeQuery();
        			if (rs.next()) {
        				Home h = new Home();
        				frame.setVisible(false);
                        h.main(null);
                    } else {
                        JOptionPane.showMessageDialog(null, "Verifier vos coordonnees");
                    }
                }catch(ClassNotFoundException ex) {
					JOptionPane.showMessageDialog(null,ex.getMessage().toString());
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null,ex.getMessage().toString());
				}
                
			}
		});
		btn_connecter.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btn_connecter.setBounds(411, 344, 183, 41);
		frame.getContentPane().add(btn_connecter);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(346, 267, 336, 48);
		frame.getContentPane().add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(346, 169, 336, 48);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		lblNewLabel_1 = new JLabel("E-mail");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_1.setBounds(128, 169, 134, 48);
		frame.getContentPane().add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("Mot de passe");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_2.setBounds(128, 270, 183, 31);
		frame.getContentPane().add(lblNewLabel_2);
		
		lbl_img = new JLabel("New label");
		lbl_img.setBounds(172, 40, 669, 87);
		frame.getContentPane().add(lbl_img);
		ImageIcon img= new ImageIcon(getClass().getResource("Logo-Pi-RVB.png"));
		Image newimg = img.getImage();
		Image poly = newimg.getScaledInstance(lbl_img.getWidth(), lbl_img.getHeight(), Image.SCALE_SMOOTH);
		lbl_img.setIcon(new ImageIcon(poly));
		
	}
}
