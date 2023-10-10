package gestion_de_scolarite;

import java.awt.EventQueue;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import java.awt.ComponentOrientation;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;

public class Enseignant {

	private JFrame frmGestionDeScolarit;
	private JTextField txt_field_nom;
	private JTextField txt_field_prenom;
	private JTextField txt_field_email;
	private JTextField txt_field_cnss;
	private JTable table_ens;
	private JTextField txt_field_tel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Enseignant window = new Enseignant();
					window.frmGestionDeScolarit.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Enseignant() {
		initialize();
		table_update();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void table_update() {
		PreparedStatement select;
		int col;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
			System.out.println("DataBase Connected");
			select = con.prepareStatement("select * from enseignant ");
			
			ResultSet rt = select.executeQuery();
			
			ResultSetMetaData rst = rt.getMetaData();
			col=rst.getColumnCount();
			
			DefaultTableModel df = (DefaultTableModel) table_ens.getModel();
			df.setRowCount(0);
			while(rt.next()) {
				Vector v2 = new Vector();
				
				for(int i=1;i<=col;i++) {
					v2.add(rt.getString("id"));
					v2.add(rt.getString("nom"));
					v2.add(rt.getString("prenom"));
					v2.add(rt.getString("email"));
					v2.add(rt.getString("cnss"));
					v2.add(rt.getString("tel"));
				}
				df.addRow(v2);
			}
			

		}catch(ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void initialize() {
		frmGestionDeScolarit = new JFrame();
		frmGestionDeScolarit.setFont(new Font("Arabic Typesetting", Font.BOLD, 20));
		frmGestionDeScolarit.setTitle("Gestion de scolarité");
		frmGestionDeScolarit.setBounds(100, 100, 912, 548);
		frmGestionDeScolarit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionDeScolarit.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setToolTipText("");
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panel.setBounds(10, 101, 419, 397);
		frmGestionDeScolarit.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btn_ajouter_ens = new JButton("Ajouter");
		
		btn_ajouter_ens.setBounds(88, 363, 89, 23);
		panel.add(btn_ajouter_ens);
		
		JButton btn_modifier_ens = new JButton("Modifier");
		btn_modifier_ens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement update;
				DefaultTableModel df = (DefaultTableModel) table_ens.getModel();
				int selectedIndex = table_ens.getSelectedRow();
				try {
					int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					String nom = txt_field_nom.getText();
					String prenom = txt_field_prenom.getText();
					String email = txt_field_email.getText();
					int tel = Integer.parseInt(txt_field_tel.getText());
					int cnss = Integer.parseInt(txt_field_cnss.getText());
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con;
					con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
					System.out.println("DataBase Connected");
					update = con.prepareStatement("update enseignant set nom = ?, prenom = ?, email = ?, tel = ?, cnss = ? where id = ?");
					update.setString(1, nom);
					update.setString(2, prenom);
					update.setString(3, email);
					update.setInt(4, tel);
					update.setInt(5, cnss);
					update.setInt(6, id);
					update.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Enseignant Modifié");
					txt_field_nom.setText("");
					txt_field_prenom.setText("");
					txt_field_email.setText("");
					txt_field_tel.setText("");
					txt_field_cnss.setText("");
					table_update();
					txt_field_nom.requestFocus();
				}catch(ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				}catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btn_modifier_ens.setBounds(187, 363, 89, 23);
		panel.add(btn_modifier_ens);
		
		JButton btn_supprimer_ens = new JButton("Supprimer");
		btn_supprimer_ens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement delete;
				DefaultTableModel df = (DefaultTableModel) table_ens.getModel();
				int selectedIndex = table_ens.getSelectedRow();
				
				
				try {
					int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					int dialogResultat = JOptionPane.showConfirmDialog(null, "vous voulez supprimer cet enseignant","Alert",JOptionPane.YES_NO_OPTION);
					if(dialogResultat == JOptionPane.YES_OPTION) {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con;
						con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
						System.out.println("DataBase Connected");
						delete = con.prepareStatement("delete from enseignant where id = ?");
						
						delete.setInt(1, id);
						delete.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"Enseignant supprimé");
						txt_field_nom.setText("");
						txt_field_prenom.setText("");
						txt_field_email.setText("");
						txt_field_tel.setText("");
						txt_field_cnss.setText("");
						table_update();
						txt_field_nom.requestFocus();
						
					}
				}catch(ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				}catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btn_supprimer_ens.setBounds(286, 363, 111, 23);
		panel.add(btn_supprimer_ens);
		
		JLabel Nom_Ens = new JLabel("Nom");
		Nom_Ens.setFont(new Font("Tahoma", Font.ITALIC, 20));
		Nom_Ens.setBounds(24, 64, 79, 23);
		panel.add(Nom_Ens);
		
		JLabel lbl_prenom_ens = new JLabel("Prenom");
		lbl_prenom_ens.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lbl_prenom_ens.setBounds(24, 113, 79, 23);
		panel.add(lbl_prenom_ens);
		
		JLabel lbl_email_ens = new JLabel("email");
		lbl_email_ens.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lbl_email_ens.setBounds(24, 160, 79, 23);
		panel.add(lbl_email_ens);
		
		JLabel lbl_cnss_ens = new JLabel("CNSS");
		lbl_cnss_ens.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lbl_cnss_ens.setBounds(24, 220, 79, 23);
		panel.add(lbl_cnss_ens);
		
		txt_field_nom = new JTextField();
		txt_field_nom.setBounds(153, 64, 222, 25);
		panel.add(txt_field_nom);
		txt_field_nom.setColumns(10);
		
		txt_field_prenom = new JTextField();
		txt_field_prenom.setColumns(10);
		txt_field_prenom.setBounds(153, 118, 222, 25);
		panel.add(txt_field_prenom);
		
		txt_field_email = new JTextField();
		txt_field_email.setColumns(10);
		txt_field_email.setBounds(153, 165, 222, 25);
		panel.add(txt_field_email);
		
		txt_field_cnss = new JTextField();
		txt_field_cnss.setColumns(10);
		txt_field_cnss.setBounds(153, 218, 222, 25);
		panel.add(txt_field_cnss);
		
		JLabel lbl_tel_ens_1 = new JLabel("Tel");
		lbl_tel_ens_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lbl_tel_ens_1.setBounds(24, 281, 79, 23);
		panel.add(lbl_tel_ens_1);
		
		txt_field_tel = new JTextField();
		txt_field_tel.setColumns(10);
		txt_field_tel.setBounds(153, 279, 222, 25);
		panel.add(txt_field_tel);
		
		
		
	
		btn_ajouter_ens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement insert;
				String nom = txt_field_nom.getText();
				String prenom = txt_field_prenom.getText();
				String email = txt_field_email.getText();
				int tel = Integer.parseInt(txt_field_tel.getText().toString());
				int cnss = Integer.parseInt(txt_field_cnss.getText().toString());
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con;
					con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
					System.out.println("DataBase Connected");
					insert = con.prepareStatement("insert into enseignant (nom,prenom,email,tel,cnss) values (?,?,?,?,?)");
					insert.setString(1, nom);
					insert.setString(2, prenom);
					insert.setString(3, email);
					insert.setInt(4, tel);
					insert.setInt(5, cnss);
					insert.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Ajouter avec succes");
					txt_field_nom.setText("");
					txt_field_prenom.setText("");
					txt_field_email.setText("");
					txt_field_tel.setText("");
					txt_field_cnss.setText("");
					table_update();
					txt_field_nom.requestFocus();
				}catch(ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				}catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
		
		
		JLabel lblNewLabel = new JLabel("Gérer Enseignant");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(116, 11, 594, 50);
		frmGestionDeScolarit.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(445, 94, 422, 404);
		frmGestionDeScolarit.getContentPane().add(scrollPane);
		
		table_ens = new JTable();
		table_ens.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_ens.setForeground(new Color(0, 0, 0));
		table_ens.setBorder(null);
		table_ens.setBackground(new Color(255, 255, 255));
		table_ens.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel df = (DefaultTableModel) table_ens.getModel();
				int selectedIndex = table_ens.getSelectedRow();
				txt_field_nom.setText(df.getValueAt(selectedIndex, 1).toString());
				txt_field_prenom.setText(df.getValueAt(selectedIndex, 2).toString());
				txt_field_email.setText(df.getValueAt(selectedIndex, 3).toString());
				txt_field_cnss.setText(df.getValueAt(selectedIndex, 4).toString());
				txt_field_tel.setText(df.getValueAt(selectedIndex, 5).toString());
			}
		});
		table_ens.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null},
			},
			new String[] {
				"id", "Nom", "Prenom", "E-mail", "Cnss", "Tel"
			}
		));
		scrollPane.setViewportView(table_ens);
		
		JButton btnNewButton = new JButton("Retourner");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h = new Home();
				h.main(null);
			}
		});
		btnNewButton.setBounds(10, 11, 89, 23);
		frmGestionDeScolarit.getContentPane().add(btnNewButton);
	}
}
