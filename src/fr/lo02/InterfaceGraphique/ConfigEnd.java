package fr.lo02.InterfaceGraphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Load.Launcher;
import fr.lo02.controler.GraphicController;
import fr.lo02.modele.Partie;
import fr.lo02.modele.Terrain;
import fr.lo02.modele.Update;
/**
 * Ecran du des vainqueurs de la Partie
 * @see Launcher#scoreBoard
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see GamePanel
 * @see PanelScore
 * @see VueGraphique
 * @see Vue
 * @see GraphicController
 * @see Launcher
 * @see Partie
 */
public class ConfigEnd extends JPanel implements Observer{
	/**
	 * Panel présentant le résultats des tours de Partie<br>
	 * <ul>
	 * 	<li>N°de Partie</li>
	 * 	<li>Le Nom du Vainqueurs</li>
	 * 	<li>Le Score du Vainqueurs</li>
	 * </ul>
	 */
	JPanel tableVictory;
	/**
	 * bouton permettant de reprendre un nouveau tour de {@link Partie}
	 */
	JButton ReStartPartie;
	/**
	 * Bouton pour retourner à la Partie en Cours
	 */
	JButton EndPartie;
	private static int ajout = 317 - 213;
	/**
	 * pour connaître la hauteur de positionnement des éléments sur le panel
	 */
	private int debut = 5;
	/**
	 * Instance de ConfigEnd encours : elle implémente le patron Singleton
	 */
	private static ConfigEnd endPartie = null;
	
	/**
     * Constructeur : il initialise la {@link ConfigEnd} avec les paramètres par défaut
     */
	private ConfigEnd() {
		this.init();
	}
	/**
     * renvoi une instance de {@link ConfigEnd} si il y en a une ou crée et renvoie une nouvelle instance de {@link ConfigEnd} si aucune party n'est référencée
     * @return une référence sur un Objet de type {@link ConfigEnd}
     */
	public static ConfigEnd getInstance() {
		if(endPartie == null)
			endPartie = new ConfigEnd();
		return endPartie;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void init()
	{
		tableVictory= new JPanel();
		//this = new JPanel();
		this.setBackground(Color.ORANGE);
		this.setLayout(null);
		
		tableVictory= new JPanel();
		tableVictory.setBackground(Color.WHITE);
		tableVictory.setBounds(10, 111, 921, 425);
		tableVictory.setLayout(new GridLayout(4, 2, 2, 2));
		
		JLabel lblNewLabel_4 = new JLabel("Vainqueurs");
		lblNewLabel_4.setBackground(Color.WHITE);
		lblNewLabel_4.setForeground(Color.BLUE);
		lblNewLabel_4.setFont(new Font("Open Sans Semibold", Font.BOLD, 16));
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(419, 63, 102, 52);
		this.add(lblNewLabel_4);
		this.add(tableVictory);
		
		ReStartPartie = new JButton("New Partie");
		ReStartPartie.setForeground(Color.BLUE);
		ReStartPartie.setFont(new Font("Open Sans Semibold", Font.BOLD, 16));
		ReStartPartie.setBounds(809, 547, 122, 38);
		ReStartPartie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					Launcher.restart();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println("Impossible to restart");
				}
			}
		});
		this.add(ReStartPartie);
		
		EndPartie = new JButton("Quit");
		EndPartie.setForeground(Color.BLUE);
		EndPartie.setFont(new Font("Open Sans Semibold", Font.BOLD, 16));
		EndPartie.setBounds(631, 547, 122, 38);
		EndPartie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VueGraphique.getIT().getgame().doClick();
			}
		});
		this.add(EndPartie);
	}
	/**
	 * Mettre à jour le tableau des Vainqueurs
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof Partie) {
			if((Update)arg == Update.No) {
				PanelScore pan;
				Partie p = (Partie)o;
				tableVictory.removeAll();
				for(ArrayList<Integer> key : (p.getScoreBoard().keySet())){
					pan = new PanelScore(this.debut);
					this.debut += ajout;
					pan.setLabelPartie(key.get(0));
					pan.setLabelName(p.getScoreBoard().get(key).getPseudo());
					pan.setLabelScore("" + key.get(1) + "");
					tableVictory.add(pan);
				}
			}
			this.debut = 109;
		}
	}
}
