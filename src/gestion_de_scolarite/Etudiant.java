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
import javax.swing.JComboBox;

public class Etudiant {

	private JFrame frmGestionDeScolarit;
	private JTextField txt_field_code;
	private JTable table_groupe;
	private JComboBox comboBox_groupe;
	private JTextField txt_field_cin;
	private JTextField txt_field_nom;
	private JTextField txt_field_prenom;
	private JTextField txt_field_email;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Etudiant window = new Etudiant();
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
	public Etudiant() {
		initialize();
		table_update();
		update_comboBox_groupe();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void update_comboBox_groupe() {
		PreparedStatement select;
		int col;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
			System.out.println("DataBase Connected");
			select = con.prepareStatement("select * from groupe ");
			
			ResultSet rt = select.executeQuery();
			
			ResultSetMetaData rst = rt.getMetaData();
			col=rst.getColumnCount();
			while(rt.next()) {
				Vector v2 = new Vector();
				comboBox_groupe.addItem(rt.getString("nom"));
			}
			

		}catch(ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}

	
	private void table_update() {
		PreparedStatement select;
		int col;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
			System.out.println("DataBase Connected");
			select = con.prepareStatement("select * from etudiant");
			
			ResultSet rt = select.executeQuery();
			
			ResultSetMetaData rst = rt.getMetaData();
			col=rst.getColumnCount();
			
			DefaultTableModel df = (DefaultTableModel) table_groupe.getModel();
			df.setRowCount(0);
			while(rt.next()) {
				Vector v2 = new Vector();
				
				for(int i=1;i<=col;i++) {
					v2.add(rt.getString("id"));
					v2.add(rt.getString("code"));
					v2.add(rt.getString("cin"));
					v2.add(rt.getString("nom"));
					v2.add(rt.getString("prenom"));
					v2.add(rt.getString("email"));
					v2.add(rt.getString("groupe"));
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
		
		JButton btn_ajouter_etudiant = new JButton("Ajouter");
		btn_ajouter_etudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement insert;
				int code = Integer.parseInt(txt_field_code.getText().toString());
				int cin = Integer.parseInt(txt_field_cin.getText().toString());
				String nom = txt_field_nom.getText().toString();
				String prenom = txt_field_prenom.getText().toString();
				String email = txt_field_email.getText().toString();
				String grp = comboBox_groupe.getSelectedItem().toString();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con;
					con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
					System.out.println("DataBase Connected");
					insert = con.prepareStatement("insert into etudiant(code, cin, nom, prenom, email, groupe) values (?,?,?,?,?,?)");
					insert.setInt(1, code);
					insert.setInt(2, cin);
					insert.setString(3, nom);
					insert.setString(4, prenom);
					insert.setString(5, email);
					insert.setString(6, grp);
					insert.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Ajouter avec succes");
					txt_field_code.setText("");
					update_comboBox_groupe();
					table_update();
					txt_field_code.requestFocus();
				}catch(ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				}catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
		btn_ajouter_etudiant.setBounds(88, 363, 89, 23);
		panel.add(btn_ajouter_etudiant);
		
		JButton btn_modifier_etudiant = new JButton("Modifier");
		btn_modifier_etudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement update;
				DefaultTableModel df = (DefaultTableModel) table_groupe.getModel();
				int selectedIndex = table_groupe.getSelectedRow();
				try {
					int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					String email = txt_field_email.getText();
					String grp = comboBox_groupe.getSelectedItem().toString();
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con;
					con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
					System.out.println("DataBase Connected");
					update = con.prepareStatement("update etudiant set email=?, groupe where id = ?");
					update.setString(1, email);
					update.setString(2, grp);
					update.setInt(3, id);
					update.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Etudiant Modifié");
					txt_field_code.setText("");
					txt_field_cin.setText("");
					txt_field_nom.setText("");
					txt_field_prenom.setText("");
					txt_field_email.setText("");
					update_comboBox_groupe();
					table_update();
					txt_field_code.requestFocus();
				}catch(ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				}catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
		btn_modifier_etudiant.setBounds(187, 363, 89, 23);
		panel.add(btn_modifier_etudiant);
		
		JButton btn_supprimer_etudiant = new JButton("Supprimer");
		btn_supprimer_etudiant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement delete;
				DefaultTableModel df = (DefaultTableModel) table_groupe.getModel();
				int selectedIndex = table_groupe.getSelectedRow();
				
				
				try {
					int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					int dialogResultat = JOptionPane.showConfirmDialog(null, "vous voulez supprimer cet enseignant","Alert",JOptionPane.YES_NO_OPTION);
					if(dialogResultat == JOptionPane.YES_OPTION) {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con;
						con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
						System.out.println("DataBase Connected");
						delete = con.prepareStatement("delete from etudiant where id = ?");
						
						delete.setInt(1, id);
						delete.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"Etudiant supprimé");
						txt_field_code.setText("");
						txt_field_cin.setText("");
						txt_field_nom.setText("");
						txt_field_prenom.setText("");
						txt_field_email.setText("");
						update_comboBox_groupe();
						table_update();
						txt_field_code.requestFocus();
						
					}
				}catch(ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				}catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
		btn_supprimer_etudiant.setBounds(286, 363, 111, 23);
		panel.add(btn_supprimer_etudiant);
		
		JLabel Nom_Ens = new JLabel("Code");
		Nom_Ens.setFont(new Font("Tahoma", Font.ITALIC, 20));
		Nom_Ens.setBounds(20, 43, 79, 23);
		panel.add(Nom_Ens);
		
		JLabel lbl_groupe_etudiant = new JLabel("Groupe");
		lbl_groupe_etudiant.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lbl_groupe_etudiant.setBounds(20, 301, 79, 23);
		panel.add(lbl_groupe_etudiant);
		
		txt_field_code = new JTextField();
		txt_field_code.setBounds(149, 46, 222, 25);
		panel.add(txt_field_code);
		txt_field_code.setColumns(10);
		
		comboBox_groupe = new JComboBox();
		comboBox_groupe.setBounds(149, 301, 222, 22);
		panel.add(comboBox_groupe);
		
		txt_field_cin = new JTextField();
		txt_field_cin.setColumns(10);
		txt_field_cin.setBounds(149, 92, 222, 25);
		panel.add(txt_field_cin);
		
		JLabel Nom_Ens_1 = new JLabel("CIN");
		Nom_Ens_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		Nom_Ens_1.setBounds(20, 89, 79, 23);
		panel.add(Nom_Ens_1);
		
		txt_field_nom = new JTextField();
		txt_field_nom.setColumns(10);
		txt_field_nom.setBounds(149, 146, 222, 25);
		panel.add(txt_field_nom);
		
		JLabel Nom_Ens_2 = new JLabel("Nom");
		Nom_Ens_2.setFont(new Font("Tahoma", Font.ITALIC, 20));
		Nom_Ens_2.setBounds(20, 143, 79, 23);
		panel.add(Nom_Ens_2);
		
		txt_field_prenom = new JTextField();
		txt_field_prenom.setColumns(10);
		txt_field_prenom.setBounds(149, 197, 222, 25);
		panel.add(txt_field_prenom);
		
		JLabel Nom_Ens_3 = new JLabel("Prenom");
		Nom_Ens_3.setFont(new Font("Tahoma", Font.ITALIC, 20));
		Nom_Ens_3.setBounds(20, 194, 79, 23);
		panel.add(Nom_Ens_3);
		
		JLabel Nom_Ens_3_1 = new JLabel("E-mail");
		Nom_Ens_3_1.setFont(new Font("Tahoma", Font.ITALIC, 20));
		Nom_Ens_3_1.setBounds(20, 246, 79, 23);
		panel.add(Nom_Ens_3_1);
		
		txt_field_email = new JTextField();
		txt_field_email.setColumns(10);
		txt_field_email.setBounds(149, 249, 222, 25);
		panel.add(txt_field_email);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Gérer Etudiant");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(116, 11, 594, 50);
		frmGestionDeScolarit.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setBounds(445, 94, 422, 404);
		frmGestionDeScolarit.getContentPane().add(scrollPane);
		
		table_groupe = new JTable();
		table_groupe.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_groupe.setForeground(new Color(0, 0, 0));
		table_groupe.setBorder(null);
		table_groupe.setBackground(new Color(255, 255, 255));
		table_groupe.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel df = (DefaultTableModel) table_groupe.getModel();
					int selectedIndex = table_groupe.getSelectedRow();
					txt_field_code.setText(df.getValueAt(selectedIndex, 1).toString());
					txt_field_cin.setText(df.getValueAt(selectedIndex, 2).toString());
					txt_field_nom.setText(df.getValueAt(selectedIndex, 3).toString());
					txt_field_prenom.setText(df.getValueAt(selectedIndex, 4).toString());
					txt_field_email.setText(df.getValueAt(selectedIndex, 5).toString());
					comboBox_groupe.setSelectedItem(df.getValueAt(selectedIndex, 6).toString());
				}
		});
		table_groupe.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"id", "code", "cin", "nom", "prenom", "E-mail", "Groupe"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, Object.class, Object.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPane.setViewportView(table_groupe);
		
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
