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

public class Matiere {

	private JFrame frmGestionDeScolarit;
	private JTable table_groupe;
	private JComboBox comboBox_enseignant;
	private JTextField txt_field_nom;
	private JTextField txt_field_coefficient;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Matiere window = new Matiere();
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
	public Matiere() {
		initialize();
		table_update();
		update_comboBox_enseignant();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void update_comboBox_enseignant() {
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
			while(rt.next()) {
				Vector v2 = new Vector();
				comboBox_enseignant.addItem(rt.getString("nom")+"_"+rt.getString("prenom")+"_"+rt.getString("cnss"));
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
			select = con.prepareStatement("select * from matiere");
			
			ResultSet rt = select.executeQuery();
			
			ResultSetMetaData rst = rt.getMetaData();
			col=rst.getColumnCount();
			
			DefaultTableModel df = (DefaultTableModel) table_groupe.getModel();
			df.setRowCount(0);
			while(rt.next()) {
				Vector v2 = new Vector();
				for(int i=1;i<=col;i++) {
					v2.add(rt.getString("id"));
					v2.add(rt.getString("nom"));
					v2.add(rt.getString("coefficient"));
					v2.add(rt.getString("enseignant"));
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
		
		JButton btn_ajouter_matiere = new JButton("Ajouter");
		btn_ajouter_matiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement insert;
				String nom = txt_field_nom.getText().toString();
				String coef = txt_field_coefficient.getText().toString();
				String ens = comboBox_enseignant.getSelectedItem().toString();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con;
					con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
					System.out.println("DataBase Connected");
					insert = con.prepareStatement("insert into matiere(nom, coefficient, enseignant) values (?,?,?");
					insert.setString(1, nom);
					insert.setString(2, coef);
					insert.setString(3, ens);
					insert.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Ajouter avec succes");
					txt_field_nom.setText("");
					txt_field_coefficient.setText("");
					update_comboBox_enseignant();
					table_update();
					txt_field_nom.requestFocus();
				}catch(ClassNotFoundException ex) {
					JOptionPane.showMessageDialog(null,ex.getMessage().toString());
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null,ex.getMessage().toString());
				}
			}
		});
		
		btn_ajouter_matiere.setBounds(75, 226, 89, 23);
		panel.add(btn_ajouter_matiere);
		
		JButton btn_modifier_matiere = new JButton("Modifier");
		btn_modifier_matiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement update;
				String nom = txt_field_nom.getText().toString();
				String coef = txt_field_coefficient.getText().toString();
				String ens = comboBox_enseignant.getSelectedItem().toString();
				DefaultTableModel df = (DefaultTableModel) table_groupe.getModel();
				int selectedIndex = table_groupe.getSelectedRow();
				try {
					int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					String grp = comboBox_enseignant.getSelectedItem().toString();
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con;
					con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
					System.out.println("DataBase Connected");
					update = con.prepareStatement("update matiere set nom=?, coefficient = ?, enseignant = ? where id = ?");
					update.setString(1, nom);
					update.setString(2, coef);
					update.setString(3, ens);
					update.setInt(4, id);
					update.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Matière Modifié");
					txt_field_nom.setText("");
					txt_field_coefficient.setText("");
					update_comboBox_enseignant();
					table_update();
					txt_field_nom.requestFocus();
				}catch(ClassNotFoundException ex) {
					JOptionPane.showMessageDialog(null,ex.getMessage().toString());
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null,ex.getMessage().toString());
				}
			}
		});
		
		btn_modifier_matiere.setBounds(174, 226, 89, 23);
		panel.add(btn_modifier_matiere);
		
		JButton btn_supprimer_matiere = new JButton("Supprimer");
		btn_supprimer_matiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement delete;
				DefaultTableModel df = (DefaultTableModel) table_groupe.getModel();
				int selectedIndex = table_groupe.getSelectedRow();
				
				
				try {
					int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					int dialogResultat = JOptionPane.showConfirmDialog(null, "vous voulez supprimer cet matière","Alert",JOptionPane.YES_NO_OPTION);
					if(dialogResultat == JOptionPane.YES_OPTION) {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con;
						con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
						System.out.println("DataBase Connected");
						delete = con.prepareStatement("delete from matiere where id = ?");
						
						delete.setInt(1, id);
						delete.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"Matière supprimé");
						txt_field_nom.setText("");
						txt_field_coefficient.setText("");
						update_comboBox_enseignant();
						table_update();
						txt_field_nom.requestFocus();
						
					}
				}catch(ClassNotFoundException ex) {
					JOptionPane.showMessageDialog(null,ex.getMessage().toString());
				}catch(SQLException ex) {
					JOptionPane.showMessageDialog(null,ex.getMessage().toString());
				}
			}
		});
		
		btn_supprimer_matiere.setBounds(273, 226, 111, 23);
		panel.add(btn_supprimer_matiere);
		
		JLabel lbl_groupe_etudiant = new JLabel("Enseignant");
		lbl_groupe_etudiant.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lbl_groupe_etudiant.setBounds(20, 160, 119, 23);
		panel.add(lbl_groupe_etudiant);
		
		comboBox_enseignant = new JComboBox();
		comboBox_enseignant.setBounds(149, 160, 222, 22);
		panel.add(comboBox_enseignant);
		
		txt_field_nom = new JTextField();
		txt_field_nom.setColumns(10);
		txt_field_nom.setBounds(149, 44, 222, 25);
		panel.add(txt_field_nom);
		
		JLabel Nom_Ens_2 = new JLabel("Nom");
		Nom_Ens_2.setFont(new Font("Tahoma", Font.ITALIC, 20));
		Nom_Ens_2.setBounds(20, 41, 79, 23);
		panel.add(Nom_Ens_2);
		
		txt_field_coefficient = new JTextField();
		txt_field_coefficient.setColumns(10);
		txt_field_coefficient.setBounds(149, 95, 222, 25);
		panel.add(txt_field_coefficient);
		
		JLabel Nom_Ens_3 = new JLabel("coefficient");
		Nom_Ens_3.setFont(new Font("Tahoma", Font.ITALIC, 20));
		Nom_Ens_3.setBounds(20, 92, 119, 23);
		panel.add(Nom_Ens_3);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Gérer Matière");
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
					txt_field_nom.setText(df.getValueAt(selectedIndex, 1).toString());
					txt_field_coefficient.setText(df.getValueAt(selectedIndex, 2).toString());
					comboBox_enseignant.setSelectedItem(df.getValueAt(selectedIndex, 3).toString());
				}
		});
		table_groupe.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
			},
			new String[] {
				"id", "nom", "coefficient", "Enseignant"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, Object.class, Object.class
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
