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

public class Niveau {

	private JFrame frmGestionDeScolarit;
	private JTable table_specialite;
	private JTextField textField_niveau;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Niveau window = new Niveau();
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
	public Niveau() {
		initialize();
		table_update();
	}
	
	
	private void table_update() {
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
			
			DefaultTableModel df = (DefaultTableModel) table_specialite.getModel();
			df.setRowCount(0);
			while(rt.next()) {
				Vector v2 = new Vector();
				
				for(int i=1;i<=col;i++) {
					v2.add(rt.getString("id"));
					v2.add(rt.getString("nom"));
				}
				df.addRow(v2);
			}
			

		}catch(ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmGestionDeScolarit = new JFrame();
		frmGestionDeScolarit.setTitle("Gestion de scolarité");
		frmGestionDeScolarit.setBounds(100, 100, 912, 548);
		frmGestionDeScolarit.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionDeScolarit.getContentPane().setLayout(null);
		JLabel lbl_diplome = new JLabel("Gérer Niveau");
		lbl_diplome.setFont(new Font("Tahoma", Font.BOLD, 30));
		lbl_diplome.setBounds(348, 11, 275, 37);
		frmGestionDeScolarit.getContentPane().add(lbl_diplome);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(192, 192, 192), 2, true));
		panel.setBounds(20, 101, 866, 397);
		frmGestionDeScolarit.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Niveau");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 20));
		lblNewLabel.setBounds(10, 3, 118, 23);
		panel.add(lblNewLabel);
		
		JButton btn_ajouter_niveau = new JButton("Ajouter");
		btn_ajouter_niveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement insert;
				String nom = textField_niveau.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con;
					con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
					System.out.println("DataBase Connected");
					insert = con.prepareStatement("insert into niveau (nom) values (?)");
					insert.setString(1, nom);
					insert.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"Ajouter avec succes");
					textField_niveau.setText("");
					table_update();
					textField_niveau.requestFocus();
				}catch(ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				}catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btn_ajouter_niveau.setBounds(316, 37, 118, 23);
		panel.add(btn_ajouter_niveau);
		
		JButton btn_modifier_niveau = new JButton("Modifier");
		btn_modifier_niveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement update;
				DefaultTableModel df = (DefaultTableModel) table_specialite.getModel();
				int selectedIndex = table_specialite.getSelectedRow();
				try {
					int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					String specialite = textField_niveau.getText();
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con;
					con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
					System.out.println("DataBase Connected");
					update = con.prepareStatement("update niveau set nom = ? where id = ?");
					update.setString(1, specialite);
					update.setInt(2, id);
					update.executeUpdate();
					
					JOptionPane.showMessageDialog(null,"niveau modifié");
					textField_niveau.setText("");
					table_update();
					textField_niveau.requestFocus();
				}catch(ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				}catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btn_modifier_niveau.setBounds(509, 37, 118, 23);
		panel.add(btn_modifier_niveau);
		
		JButton btn_supprimer_niveau = new JButton("Supprimer");
		btn_supprimer_niveau.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PreparedStatement delete;
				DefaultTableModel df = (DefaultTableModel) table_specialite.getModel();
				int selectedIndex = table_specialite.getSelectedRow();
				try {
					int id = Integer.parseInt(df.getValueAt(selectedIndex, 0).toString());
					int dialogResultat = JOptionPane.showConfirmDialog(null, "vous voulez supprimer cet niveau","Alert",JOptionPane.YES_NO_OPTION);
					if(dialogResultat == JOptionPane.YES_OPTION) {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con;
						con =DriverManager.getConnection("jdbc:mysql://localhost:3306/gestion_de_scolarite","root","");
						System.out.println("DataBase Connected");
						delete = con.prepareStatement("delete from niveau where id = ?");
						
						delete.setInt(1, id);
						delete.executeUpdate();
						
						JOptionPane.showMessageDialog(null,"Niveau supprimé");
						textField_niveau.setText("");
						table_update();
						textField_niveau.requestFocus();
						
					}
				}catch(ClassNotFoundException ex) {
					System.out.println(ex.getMessage());
				}catch(SQLException ex) {
					System.out.println(ex.getMessage());
				}
			}
		});
		btn_supprimer_niveau.setBounds(696, 37, 118, 23);
		panel.add(btn_supprimer_niveau);
		
		textField_niveau = new JTextField();
		textField_niveau.setBounds(146, 8, 695, 20);
		panel.add(textField_niveau);
		textField_niveau.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(146, 107, 695, 279);
		panel.add(scrollPane);
		scrollPane.setViewportBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		
		table_specialite = new JTable();
		table_specialite.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel df = (DefaultTableModel) table_specialite.getModel();
				int selectedIndex = table_specialite.getSelectedRow();
				textField_niveau.setText(df.getValueAt(selectedIndex, 1).toString());
			}
		});
		table_specialite.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"id", "nom"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table_specialite.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table_specialite.setBorder(new LineBorder(new Color(0, 0, 0)));
		scrollPane.setViewportView(table_specialite);
	}
}
