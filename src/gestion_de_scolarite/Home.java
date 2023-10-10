package gestion_de_scolarite;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Home {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Home window = new Home();
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
	public Home() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(255, 255, 255));
		frame.setTitle("Gestion de scolarité");
		frame.setBounds(100, 100, 912, 548);	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbl_consulter_pv = new JLabel("New label");
		lbl_consulter_pv.setBounds(49, 83, 113, 77);
		frame.getContentPane().add(lbl_consulter_pv);
		ImageIcon img_pv= new ImageIcon(getClass().getResource("pv.jpg"));
		Image newimg_pv= img_pv.getImage();
		Image pv = newimg_pv.getScaledInstance(lbl_consulter_pv.getWidth(), lbl_consulter_pv.getHeight(), Image.SCALE_SMOOTH);
		lbl_consulter_pv.setIcon(new ImageIcon(pv));
		
		JLabel lbl_gerer_notes = new JLabel("New label");
		lbl_gerer_notes.setBounds(267, 83, 113, 77);
		frame.getContentPane().add(lbl_gerer_notes);
		ImageIcon img_note= new ImageIcon(getClass().getResource("note.jpg"));
		Image newimg_note= img_note.getImage();
		Image note = newimg_note.getScaledInstance(lbl_gerer_notes.getWidth(), lbl_gerer_notes.getHeight(), Image.SCALE_SMOOTH);
		lbl_gerer_notes.setIcon(new ImageIcon(note));
		
		JLabel lbl_gerer_absences = new JLabel("New label");
		lbl_gerer_absences.setBounds(459, 83, 113, 77);
		frame.getContentPane().add(lbl_gerer_absences);
		ImageIcon img_absence= new ImageIcon(getClass().getResource("absence.jpg"));
		Image newimg_absence= img_absence.getImage();
		Image absence = newimg_absence.getScaledInstance(lbl_gerer_absences.getWidth(), lbl_gerer_absences.getHeight(), Image.SCALE_SMOOTH);
		lbl_gerer_absences.setIcon(new ImageIcon(absence));
		
		JLabel lbl_gerer_etudiants = new JLabel("New label");
		lbl_gerer_etudiants.setBounds(671, 83, 113, 77);
		frame.getContentPane().add(lbl_gerer_etudiants);
		ImageIcon img_etud= new ImageIcon(getClass().getResource("eud.jpg"));
		Image newimg_etud= img_etud.getImage();
		Image etud = newimg_etud.getScaledInstance(lbl_gerer_etudiants.getWidth(), lbl_gerer_etudiants.getHeight(), Image.SCALE_SMOOTH);
		lbl_gerer_etudiants.setIcon(new ImageIcon(etud));
		
		JLabel lbl_gerer_groupes = new JLabel("New label");
		lbl_gerer_groupes.setBounds(49, 245, 113, 77);
		frame.getContentPane().add(lbl_gerer_groupes);
		ImageIcon img_grp= new ImageIcon(getClass().getResource("grp.jpg"));
		Image newimg_grp= img_grp.getImage();
		Image grp = newimg_grp.getScaledInstance(lbl_gerer_groupes.getWidth(), lbl_gerer_groupes.getHeight(), Image.SCALE_SMOOTH);
		lbl_gerer_groupes.setIcon(new ImageIcon(grp));
		
		JLabel lbl_gerer_enseignant = new JLabel("New label");
		lbl_gerer_enseignant.setBounds(267, 245, 113, 77);
		frame.getContentPane().add(lbl_gerer_enseignant);
		ImageIcon img_ens= new ImageIcon(getClass().getResource("ens.jpg"));
		Image newimg_ens= img_ens.getImage();
		Image ens = newimg_ens.getScaledInstance(lbl_gerer_enseignant.getWidth(), lbl_gerer_enseignant.getHeight(), Image.SCALE_SMOOTH);
		lbl_gerer_enseignant.setIcon(new ImageIcon(ens));
		
		JLabel lbl_gerer_matiere = new JLabel("New label");
		lbl_gerer_matiere.setBounds(459, 245, 113, 77);
		frame.getContentPane().add(lbl_gerer_matiere);
		ImageIcon img_mat= new ImageIcon(getClass().getResource("matiere.jpg"));
		Image newimg_mat= img_mat.getImage();
		Image mat = newimg_mat.getScaledInstance(lbl_gerer_matiere.getWidth(), lbl_gerer_matiere.getHeight(), Image.SCALE_SMOOTH);
		lbl_gerer_matiere.setIcon(new ImageIcon(mat));
		
		JLabel lbl_gerer_cours = new JLabel("New label");
		lbl_gerer_cours.setBounds(671, 245, 113, 77);
		frame.getContentPane().add(lbl_gerer_cours);
		ImageIcon img_cours= new ImageIcon(getClass().getResource("cours.jpg"));
		Image newimg_cours = img_cours.getImage();
		Image cours = newimg_cours.getScaledInstance(lbl_gerer_cours.getWidth(), lbl_gerer_cours.getHeight(), Image.SCALE_SMOOTH);
		lbl_gerer_cours.setIcon(new ImageIcon(cours));
		
		JLabel lbl_gerer_compte = new JLabel("New label");
		lbl_gerer_compte.setBounds(49, 385, 113, 77);
		frame.getContentPane().add(lbl_gerer_compte);
		ImageIcon img_cpt= new ImageIcon(getClass().getResource("gerer_cpt.png"));
		Image newimg_cpt= img_cpt.getImage();
		Image cpt = newimg_cpt.getScaledInstance(lbl_gerer_compte.getWidth(), lbl_gerer_compte.getHeight(), Image.SCALE_SMOOTH);
		lbl_gerer_compte.setIcon(new ImageIcon(cpt));
		
		JLabel lbl_loug_out = new JLabel("New label");
		lbl_loug_out.setBounds(671, 399, 113, 77);
		frame.getContentPane().add(lbl_loug_out);
		ImageIcon img_log= new ImageIcon(getClass().getResource("lougout.jpg"));
		Image newimg_log = img_log.getImage();
		Image log = newimg_log.getScaledInstance(lbl_loug_out.getWidth(), lbl_loug_out.getHeight(), Image.SCALE_SMOOTH);
		lbl_loug_out.setIcon(new ImageIcon(log));
		
		JButton btnNewButton = new JButton("Consulter PV");
		btnNewButton.setBounds(59, 171, 113, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnGererLesNotes = new JButton("Gerer les notes");
		btnGererLesNotes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnGererLesNotes.setBounds(267, 172, 143, 23);
		frame.getContentPane().add(btnGererLesNotes);
		
		JButton btnGererLesAbsences = new JButton("Gerer les absences");
		btnGererLesAbsences.setBounds(459, 172, 164, 23);
		frame.getContentPane().add(btnGererLesAbsences);
		
		JButton btnGererLesEtudiants = new JButton("Gerer les etudiants");
		btnGererLesEtudiants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Etudiant et =new Etudiant();
				et.main(null);
			}
		});
		btnGererLesEtudiants.setBounds(671, 172, 174, 23);
		frame.getContentPane().add(btnGererLesEtudiants);
		
		JButton btnGererLesGroupes = new JButton("Gerer les groupes");
		btnGererLesGroupes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Groupe grp = new Groupe();
				grp.main(null);
			}
		});
		btnGererLesGroupes.setBounds(30, 350, 153, 23);
		frame.getContentPane().add(btnGererLesGroupes);
		
		JButton btnGererLesEnseignants = new JButton("Gerer les enseignants");
		btnGererLesEnseignants.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Enseignant ens = new Enseignant();
				ens.main(null);
			}
		});
		btnGererLesEnseignants.setBounds(246, 350, 174, 23);
		frame.getContentPane().add(btnGererLesEnseignants);
		
		JButton btnGererLesMatires = new JButton("Gerer les matières");
		btnGererLesMatires.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Matiere m = new Matiere();
				m.main(null);
			}
		});
		btnGererLesMatires.setBounds(459, 350, 143, 23);
		frame.getContentPane().add(btnGererLesMatires);
		
		JButton btnGererLesCours = new JButton("Gerer les cours");
		btnGererLesCours.setBounds(671, 350, 143, 23);
		frame.getContentPane().add(btnGererLesCours);
		
		JButton btnGererLesComptes = new JButton("Gerer les comptes");
		btnGererLesComptes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				User_account user = new User_account();
				user.main(null);
			}
		});
		btnGererLesComptes.setBounds(49, 473, 143, 23);
		frame.getContentPane().add(btnGererLesComptes);
		
		JButton btnDeconnecter = new JButton("deconnecter");
		btnDeconnecter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginFrame login = new LoginFrame();
				frame.setVisible(false);
                login.main(null);
			}
		});
		btnDeconnecter.setBounds(671, 473, 123, 23);
		frame.getContentPane().add(btnDeconnecter);
		
	}

}
