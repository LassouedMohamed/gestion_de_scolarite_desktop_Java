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

public class Groupe {

	private JFrame frmGestionDeScolarit;
	private JTextField txt_field_num;
	private JTable table_groupe;
	private JComboBox comboBox_diplome;
	private JComboBox comboBox_specialite;
	private JTextField textField_nom;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Groupe window = new Groupe();
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
	public Groupe() {
		initialize();
		table_update();
		update_comboBox_diplome();
		update_comboBox_specialte();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void update_comboBox_diplome() {
		PreparedStatement select;
		int col;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
			System.out.println("DataBase Connected");
			select = con.prepareStatement("select * from niveau ");
			
			ResultSet rt = select.executeQuery();
			
			ResultSetMetaData rst = rt.getMetaData();
			col=rst.getColumnCount();
			while(rt.next()) {
				Vector v2 = new Vector();
				comboBox_diplome.addItem(rt.getString("nom"));
			}
			

		}catch(ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	private void update_comboBox_specialte() {
		PreparedStatement select;
		int col;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con;
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
			System.out.println("DataBase Connected");
			select = con.prepareStatement("select * from specialite");
			
			ResultSet rt = select.executeQuery();
			
			ResultSetMetaData rst = rt.getMetaData();
			col=rst.getColumnCount();
			while(rt.next()) {
				Vector v2 = new Vector();
				comboBox_specialite.addItem(rt.getString("nom"));
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
			select = con.prepareStatement("select * from groupe");
			
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
					v2.add(rt.getString("numero"));
					v2.add(rt.getString("diplome"));
					v2.add(rt.getString("specialite"));
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
		btn_ajouter_ens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement insert;
				String num = txt_field_num.getText();
				String dip = comboBox_diplome.getSelectedItem().toString();
				String spec = comboBox_specialite.getSelectedItem().toString();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con;
					con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
					System.out.println("DataBase Connected");
					insert = con.prepareStatement("insert into groupe (nom,numero,diplome,specialite) values (?,?,?,?)");
					String nom =dip+"_"+spec+"_"+num;
					insert.setString(2, nom);
					insert.setString(2, num);
					insert.setString(3, dip);
					insert.setString(4, spec);
					insert.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Ajouter avec succes");
					txt_field_num.setText("");
					update_comboBox_diplome();
					update_comboBox_specialte();
					table_update();
					txt_field_num.requestFocus();
				}catch(ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				}catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		
		btn_ajouter_ens.setBounds(88, 363, 89, 23);
		panel.add(btn_ajouter_ens);
		
		JButton btn_modifier_ens = new JButton("Modifier");
		btn_modifier_ens.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement update;
				DefaultTableModel df = (DefaultTableModel) table_groupe.getModel();
				int selectedIndex = table_groupe.getSelectedRow();
				try {
					int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					String num = txt_field_num.getText();
					String dip = comboBox_diplome.getSelectedItem().toString();
					String spec = comboBox_specialite.getSelectedItem().toString();
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con;
					con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
					System.out.println("DataBase Connected");
					update = con.prepareStatement("update groupe set numero = ?, diplome = ?, specialite = ?, nom = ? where id = ?");
					String nom =dip+"_"+spec+"_"+num;
					update.setString(1, num);
					update.setString(2, dip);
					update.setString(3, spec);
					update.setString(4, nom);
					update.setInt(5, id);
					update.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Groupe Modifié");
					txt_field_num.setText("");
					update_comboBox_diplome();
					update_comboBox_specialte();
					table_update();
					txt_field_num.requestFocus();
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
						delete = con.prepareStatement("delete from groupe where id = ?");
						
						delete.setInt(1, id);
						delete.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"Groupe supprimé");
						txt_field_num.setText("");
						update_comboBox_specialte();
						update_comboBox_diplome();
						table_update();
						txt_field_num.requestFocus();
						
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
		
		JLabel Num_groupe = new JLabel("Numero");
		Num_groupe.setFont(new Font("Tahoma", Font.ITALIC, 20));
		Num_groupe.setBounds(22, 164, 79, 23);
		panel.add(Num_groupe);
		
		JLabel lbl_dip_groupe = new JLabel("Diplome");
		lbl_dip_groupe.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lbl_dip_groupe.setBounds(22, 56, 79, 23);
		panel.add(lbl_dip_groupe);
		
		JLabel lbl_spec_groupe = new JLabel("Specialite");
		lbl_spec_groupe.setFont(new Font("Tahoma", Font.ITALIC, 20));
		lbl_spec_groupe.setBounds(22, 103, 104, 23);
		panel.add(lbl_spec_groupe);
		
		txt_field_num = new JTextField();
		txt_field_num.setBounds(151, 167, 222, 25);
		panel.add(txt_field_num);
		txt_field_num.setColumns(10);
		
		comboBox_diplome = new JComboBox();
		comboBox_diplome.setBounds(151, 60, 222, 22);
		panel.add(comboBox_diplome);
		
		comboBox_specialite = new JComboBox();
		comboBox_specialite.setBounds(151, 107, 222, 22);
		panel.add(comboBox_specialite);
		
		textField_nom = new JTextField();
		textField_nom.setEditable(false);
		textField_nom.setEnabled(false);
		textField_nom.setColumns(10);
		textField_nom.setBounds(151, 231, 222, 25);
		panel.add(textField_nom);
		
		JLabel Nom_groupe = new JLabel("Nom");
		Nom_groupe.setFont(new Font("Tahoma", Font.ITALIC, 20));
		Nom_groupe.setBounds(22, 228, 79, 23);
		panel.add(Nom_groupe);
		
		
		
		
		JLabel lblNewLabel = new JLabel("Gérer Groupe");
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
					textField_nom.setText(df.getValueAt(selectedIndex, 1).toString());
					txt_field_num.setText(df.getValueAt(selectedIndex, 2).toString());
					comboBox_diplome.setSelectedItem(df.getValueAt(selectedIndex, 3).toString());
					comboBox_specialite.setSelectedItem(df.getValueAt(selectedIndex, 4).toString());
				}
		});
		table_groupe.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
			},
			new String[] {
				"id", "Nom", "Numero", "Diplome", "Specialite"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_groupe.getColumnModel().getColumn(4).setMinWidth(30);
		scrollPane.setViewportView(table_groupe);
		
		JButton btnNewButton = new JButton("Retrouner");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Home h = new Home();
				h.main(null);
			}
		});
		btnNewButton.setBounds(10, 11, 96, 23);
		frmGestionDeScolarit.getContentPane().add(btnNewButton);
	}
}
