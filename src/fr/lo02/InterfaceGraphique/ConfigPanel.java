package fr.lo02.InterfaceGraphique;

import java.awt.Choice;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import fr.lo02.controler.GraphicController;
import fr.lo02.controler.PartieConsoleEngine;
import fr.lo02.modele.Configuration;
import fr.lo02.modele.GameRule;
import fr.lo02.modele.Partie;
import fr.lo02.modele.Player;
import fr.lo02.modele.RealPlayer;
import fr.lo02.modele.Strategy;
import fr.lo02.modele.Terrain;
import fr.lo02.modele.Update;
import fr.lo02.modele.VirtualPlayer;
/**
 * Ecran de la {@link Configuration} de la {@link Partie}
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see GamePanel
 * @see Configuration
 * @see Partie
 * @see VueGraphique
 * @see Vue
 * @see GraphicController
 */
public class ConfigPanel extends JPanel implements Observer{
	/**
	 * la {@link Configuration} de la {@link Partie}
	 */
	Configuration config;
	/**
	 * Instance de ConfigPanel encours : elle implémente le patron Singleton
	 */
	private static ConfigPanel panel = null;
	/**
	 * le nombre de {@link Player} de la {@link Partie}
	 */
	private int playerreal = 0;
	/**
	 * vue de gestion du Panel du nombre de Joueur
	 * @see JPanel
	 */
	JPanel panel_Joeur = new JPanel();
	/**
	 * un bouton de {@link ConfigPanel#panel_Joeur}
	 * @see JRadioButton
	 */
	JRadioButton bouton2Joueur = new JRadioButton("2");
	/**
	 * un bouton de {@link ConfigPanel#panel_Joeur}
	 * @see JRadioButton
	 */
	JRadioButton bouton3Joueur = new JRadioButton("3");
	/**
	 * un groupement de Bouton de {@link ConfigPanel#panel_Joeur}
	 * @see ButtonGroup
	 * @see JRadioButton
	 */
	ButtonGroup groupNbJoueur = new ButtonGroup();
	/**
	 * liste des bouton de {@link ConfigPanel#panel_Joeur}
	 */
	ArrayList<JRadioButton> listJoueur = new ArrayList<JRadioButton>();
	/**
	 * vue de gestion du Panel du nombre de Joueur Reel
	 * @see JPanel
	 */
	JPanel panel_JoueurReel = new JPanel();
	/**
	 * un bouton de {@link ConfigPanel#panel_JoueurReel}
	 * @see JRadioButton
	 */
	JRadioButton bouton2JoueurR_2 = new JRadioButton("2");
	/**
	 * un bouton de {@link ConfigPanel#panel_JoueurReel}
	 * @see JRadioButton
	 */
	JRadioButton bouton2JoueurR_0 = new JRadioButton("0");
	/**
	 * un bouton de {@link ConfigPanel#panel_JoueurReel}
	 * @see JRadioButton
	 */
	JRadioButton bouton2JoueurR_1 = new JRadioButton("1");
	/**
	 * un bouton de {@link ConfigPanel#panel_JoueurReel}
	 * @see JRadioButton
	 */
	JRadioButton bouton2JoueurR_3 = new JRadioButton("3");
	/**
	 * un bouton de {@link ConfigPanel#panel_JoueurReel}
	 * @see JRadioButton
	 */
	JTextField player1;
	/**
	 * un bouton de {@link ConfigPanel#panel_JoueurReel}
	 * @see JRadioButton
	 */
	JTextField player2;
	/**
	 * un bouton de {@link ConfigPanel#panel_JoueurReel}
	 * @see JRadioButton
	 */
	JTextField player3;
	/**
	 * un {@link JTextField} de {@link ConfigPanel#panel_JoueurReel}
	 * @see JTextField
	 */
	JLabel label_name_1;
	/**
	 * un {@link JTextField} de {@link ConfigPanel#panel_JoueurReel}
	 * @see JTextField
	 */
	JLabel label_name_2;
	/**
	 * un {@link JTextField} de {@link ConfigPanel#panel_JoueurReel}
	 * @see JTextField
	 */
	JLabel label_name_3;
	/**
	 * le liste des labels de {@link Player}
	 */
	ArrayList<JLabel> labelPlayer = new ArrayList<JLabel>();
	/**
	 * la liste des {@link JTextField} des noms des {@link Player}
	 */
	ArrayList<JTextField> playerName = new ArrayList<JTextField>();
	/**
	 * la liste des {@link JRadioButton} des Players
	 */
	ArrayList<JRadioButton> listJoueurR = new ArrayList<JRadioButton>();
	/**
	 * vue de gestion du Panel de la forme du {@link Terrain}
	 * @see JPanel
	 */
	JPanel panel_Terrain = new JPanel();
	/**
	 * un bouton de {@link ConfigPanel#panel_Terrain}
	 * @see JRadioButton
	 */
	JRadioButton boutonRectangulaire = new JRadioButton("Rectangulaire");
	/**
	 * un bouton de {@link ConfigPanel#panel_Terrain}
	 * @see JRadioButton
	 */
	JRadioButton boutonCirculaire = new JRadioButton("Circulaire");
	/**
	 * un bouton de {@link ConfigPanel#panel_Terrain}
	 * @see JRadioButton
	 */
	JRadioButton boutonTriangulaire = new JRadioButton("Triangulaire");
	/**
	 * la list des {@link JRadioButton} des formes de {@link Terrain} du jeu
	 */
	ArrayList<JRadioButton> listForme = new ArrayList<JRadioButton>();
	/**
	 * vue de gestion du Panel du {@link GameRule} de la {@link Partie}
	 * @see JPanel
	 */
	JPanel panel_GameRule = new JPanel();
	/**
	 * un bouton de {@link ConfigPanel#panel_GameRule}
	 * @see JRadioButton
	 */
	JRadioButton boutonNormal = new JRadioButton("Mode Normal");
	/**
	 * un bouton de {@link ConfigPanel#panel_GameRule}
	 * @see JRadioButton
	 */
	JRadioButton boutonAvance = new JRadioButton("Mode Avanc\u00E9");
	/**
	 * la liste des {@link JRadioButton} des Gamerule du jeu
	 */
	ArrayList<JRadioButton> listGameRule = new ArrayList<JRadioButton>();
	/**
	 * vue de gestion du Panel de la {@link Strategy} des {@link VirtualPlayer} de la {@link Partie}
	 * @see JPanel
	 */
	JPanel panel_Strategy = new JPanel();
	/**
	 * un bouton de {@link ConfigPanel#panel_Strategy}
	 * @see JRadioButton
	 */
	JRadioButton boutonFacile = new JRadioButton("Facile");
	/**
	 * un bouton de {@link ConfigPanel#panel_Strategy}
	 * @see JRadioButton
	 */
	JRadioButton boutonIntermediaire = new JRadioButton("Intermediaire");
	/**
	 * un bouton de {@link ConfigPanel#panel_Strategy}
	 * @see JRadioButton
	 */
	JRadioButton boutonDifficile = new JRadioButton("Difficile");
	/**
	 * la liste des JradioButton des choix de Strategy de jeu
	 */
	ArrayList<JRadioButton> listJoueurStrategy = new ArrayList<JRadioButton>();
	/**
	 * Bouton pour Terminé la configuration et commencer la {@link Partie}
	 */
	JButton FinishConfig;
	/**
	 * Bouton pour aller continuer l'état de la {@link Partie}
	 */
	JButton QuitConfig;
/**
 * Réinitialiser l'élément panel
 */
	public static void setNull() {
		panel = null;
	}
	/**
     * Constructeur : il initialise la {@link ConfigPanel} avec les paramètres par défaut
     */
	private ConfigPanel(Configuration c)
	{
		this.init();
		config = c;
		c.addObserver(this);
	}
	/**
     * renvoi une instance de {@link ConfigPanel} si il y en a une ou crée et renvoie une nouvelle instance de {@link ConfigPanel} si aucune party n'est référencée
     * @param c la Configuration qui sera le modèle de cette vue
     * @return une référence sur un Objet de type {@link ConfigPanel}
     */
	public static ConfigPanel getInstance(Configuration c)
	{
		if(ConfigPanel.panel == null)
			panel = new ConfigPanel(c);
		return panel;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void init()
	{
		this.setBackground(Color.ORANGE);
		this.setLayout(null);

		setNumberPlayer();
		
		setReelPlayer();
		
		setTerrain();
		
		setGameRule();
		
		setStrategy();
		
		setWhatNext();
	}
	/**
	 * Initialize the contents of the {@link ConfigPanel#panel_Joeur}
	 */
	public void setNumberPlayer()
	{
		panel_Joeur.setBackground(Color.WHITE);
		panel_Joeur.setBounds(0, 10, 965, 68);
		this.add(panel_Joeur);
		panel_Joeur.setLayout(null);
		
		JLabel lblQuelEstLe = new JLabel("Quel est le nombre de joueurs de la partie :");
		lblQuelEstLe.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		lblQuelEstLe.setForeground(Color.BLUE);
		lblQuelEstLe.setBounds(10, 5, 544, 20);
		panel_Joeur.add(lblQuelEstLe);
		
		
		bouton2Joueur.setForeground(Color.BLUE);
		bouton2Joueur.setBackground(Color.WHITE);
		bouton2Joueur.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		bouton2Joueur.setBounds(82, 32, 109, 23);
		bouton2Joueur.setSelected(false);
		bouton2Joueur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				GraphicController.setNumberOfPlayer(2);
				//config.setPlayerNumber(2);
				panel_JoueurReel.setVisible(true);
				listJoueurR.get(0).setVisible(true);
				listJoueurR.get(1).setVisible(true);
				listJoueurR.get(2).setVisible(true);
				listJoueurR.get(3).setVisible(false);
				//TextController.getIt().setValue(2);
			}
		});
		panel_Joeur.add(bouton2Joueur);
		
		bouton3Joueur.setBackground(Color.WHITE);
		bouton3Joueur.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		bouton3Joueur.setForeground(Color.BLUE);
		bouton3Joueur.setBounds(356, 32, 109, 23);
		bouton3Joueur.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				panel_JoueurReel.setVisible(true);
				GraphicController.setNumberOfPlayer(3);
				listJoueurR.get(0).setVisible(true);
				listJoueurR.get(1).setVisible(true);
				listJoueurR.get(2).setVisible(true);
				listJoueurR.get(3).setVisible(true);
				//config.setPlayerNumber(3);
				//TextController.getIt().setValue(2);
			}
		});
		panel_Joeur.add(bouton3Joueur);
		
		groupNbJoueur.add(bouton2Joueur);
		groupNbJoueur.add(bouton3Joueur);
		listJoueur.add(bouton2Joueur);
		listJoueur.add(bouton3Joueur);
	}
	/**
	 * Initialize the contents of the {@link ConfigPanel#panel_JoueurReel}
	 */
	public void setReelPlayer()
	{
		panel_JoueurReel.setLayout(null);
		panel_JoueurReel.setBackground(Color.WHITE);
		panel_JoueurReel.setBounds(0, 85, 965, 157);
		this.add(panel_JoueurReel);
		
		JLabel lblQuelEstLe_2 = new JLabel("Quel est le nombre de joueurs Reels de la partie :");
		lblQuelEstLe_2.setForeground(Color.BLUE);
		lblQuelEstLe_2.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		lblQuelEstLe_2.setBounds(10, 5, 544, 20);
		panel_JoueurReel.add(lblQuelEstLe_2);
		
		
		bouton2JoueurR_0.setForeground(Color.BLUE);
		bouton2JoueurR_0.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		bouton2JoueurR_0.setBackground(Color.WHITE);
		bouton2JoueurR_0.setBounds(41, 32, 60, 23);
		bouton2JoueurR_0.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					//config.setRealPlayer(0);
					GraphicController.setNumberOfRealPlayer(0);
					playerName.get(0).setVisible(false);
					playerName.get(1).setVisible(false);
					playerName.get(2).setVisible(false);
					labelPlayer.get(0).setVisible(false);
					labelPlayer.get(1).setVisible(false);
					labelPlayer.get(2).setVisible(false);
			}
		});
		
		bouton2JoueurR_2.setForeground(Color.BLUE);
		bouton2JoueurR_2.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		bouton2JoueurR_2.setBackground(Color.WHITE);
		bouton2JoueurR_2.setBounds(310, 32, 60, 23);
		bouton2JoueurR_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					//config.setRealPlayer(0);
					GraphicController.setNumberOfRealPlayer(2);
					playerName.get(0).setVisible(true);
					playerName.get(1).setVisible(true);
					playerName.get(2).setVisible(false);
					labelPlayer.get(0).setVisible(true);
					labelPlayer.get(1).setVisible(true);
					labelPlayer.get(2).setVisible(false);
			}
		});
		
		
		bouton2JoueurR_1.setForeground(Color.BLUE);
		bouton2JoueurR_1.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		bouton2JoueurR_1.setBackground(Color.WHITE);
		bouton2JoueurR_1.setBounds(169, 32, 60, 23);
		bouton2JoueurR_1.setSelected(false);
		bouton2JoueurR_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					//config.setRealPlayer(1);
					GraphicController.setNumberOfRealPlayer(1);
					playerName.get(0).setVisible(true);
					playerName.get(1).setVisible(false);
					playerName.get(2).setVisible(false);
					labelPlayer.get(0).setVisible(true);
					labelPlayer.get(1).setVisible(false);
					labelPlayer.get(2).setVisible(false);
			}
		});
		
		
		bouton2JoueurR_3.setForeground(Color.BLUE);
		bouton2JoueurR_3.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		bouton2JoueurR_3.setBackground(Color.WHITE);
		bouton2JoueurR_3.setBounds(438, 32, 60, 23);
		bouton2JoueurR_3.setSelected(false);
		bouton2JoueurR_3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					//config.setRealPlayer(1);
					GraphicController.setNumberOfRealPlayer(3);
					playerName.get(0).setVisible(true);
					playerName.get(1).setVisible(true);
					playerName.get(2).setVisible(true);
					labelPlayer.get(0).setVisible(true);
					labelPlayer.get(1).setVisible(true);
					labelPlayer.get(2).setVisible(true);
			}
		});
		
		
		panel_JoueurReel.add(bouton2JoueurR_0);
		panel_JoueurReel.add(bouton2JoueurR_1);
		panel_JoueurReel.add(bouton2JoueurR_2);
		panel_JoueurReel.add(bouton2JoueurR_3);
		ButtonGroup groupNbJoueurReel = new ButtonGroup();
		groupNbJoueurReel.add(bouton2JoueurR_3);
		groupNbJoueurReel.add(bouton2JoueurR_0);
		groupNbJoueurReel.add(bouton2JoueurR_1);
		groupNbJoueurReel.add(bouton2JoueurR_2);
		
		listJoueurR.add(bouton2JoueurR_0);
		listJoueurR.add(bouton2JoueurR_1);
		listJoueurR.add(bouton2JoueurR_2);
		listJoueurR.add(bouton2JoueurR_3);
		for(JRadioButton b : this.listJoueurR) {
			b.setVisible(false);
		}
		
		
		
		player1 = new JTextField();
		player1.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		player1.setBackground(Color.ORANGE);
		player1.setForeground(Color.BLUE);
		player1.setBounds(129, 89, 146, 36);
		panel_JoueurReel.add(player1);
		player1.setColumns(10);
		player1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//config.getListeNom().add(player1.getText());
			}
		});
		this.playerName.add(player1);
		player1.setVisible(false);
		
		player2 = new JTextField();
		player2.setForeground(Color.BLUE);
		player2.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		player2.setColumns(10);
		player2.setBackground(Color.ORANGE);
		player2.setBounds(493, 89, 146, 36);
		player2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//config.getListeNom().add(player2.getText());
			}
		});
		panel_JoueurReel.add(player2);
		this.playerName.add(player2);
		player2.setVisible(false);
		
		player3 = new JTextField();
		player3.setForeground(Color.BLUE);
		player3.setFont(new Font("Microsoft YaHei", Font.BOLD, 14));
		player3.setColumns(10);
		player3.setBackground(Color.ORANGE);
		player3.setBounds(795, 89, 146, 36);
		player3.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//config.getListeNom().add(player3.getText());
			}
		});
		panel_JoueurReel.add(player3);
		this.playerName.add(player3);
		player3.setVisible(false);
		
		label_name_1 = new JLabel("Player 1 :");
		label_name_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_name_1.setForeground(Color.BLUE);
		label_name_1.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		label_name_1.setBounds(10, 89, 109, 36);
		panel_JoueurReel.add(label_name_1);
		
		label_name_2 = new JLabel("Player 2 :");
		label_name_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_name_2.setForeground(Color.BLUE);
		label_name_2.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		label_name_2.setBounds(355, 89, 109, 36);
		panel_JoueurReel.add(label_name_2);
		
		label_name_3 = new JLabel("Player 3 :");
		label_name_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_name_3.setForeground(Color.BLUE);
		label_name_3.setFont(new Font("MS Reference Sans Serif", Font.BOLD, 14));
		label_name_3.setBounds(676, 88, 109, 36);
		panel_JoueurReel.add(label_name_3);
		this.label_name_1.setVisible(false);
		this.label_name_2.setVisible(false);
		this.label_name_3.setVisible(false);
		this.labelPlayer.add(label_name_1);
		this.labelPlayer.add(label_name_2);
		this.labelPlayer.add(label_name_3);
		
		
		
		this.panel_JoueurReel.setVisible(false);
	}
	/**
	 * Initialize the contents of the {@link ConfigPanel#panel_Terrain}
	 */
	public void setTerrain()
	{
		panel_Terrain.setLayout(null);
		panel_Terrain.setBackground(Color.WHITE);
		panel_Terrain.setBounds(0, 252, 965, 88);
		this.add(panel_Terrain);
		
		JLabel lblQuelleEstLa = new JLabel("Quelle est la forme du terrain :");
		lblQuelleEstLa.setForeground(Color.BLUE);
		lblQuelleEstLa.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		lblQuelleEstLa.setBounds(10, 5, 544, 20);
		panel_Terrain.add(lblQuelleEstLa);
		
		boutonRectangulaire.setForeground(Color.BLUE);
		boutonRectangulaire.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		boutonRectangulaire.setBackground(Color.WHITE);
		boutonRectangulaire.setBounds(74, 32, 119, 23);
		boutonRectangulaire.setSelected(false);
		boutonRectangulaire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//config.setShapeField(1);
				GraphicController.setFormeField(1);
			}
		});
		panel_Terrain.add(boutonRectangulaire);
		
		boutonCirculaire.setForeground(Color.BLUE);
		boutonCirculaire.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		boutonCirculaire.setBackground(Color.WHITE);
		boutonCirculaire.setBounds(221, 32, 109, 23);
		boutonCirculaire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//config.setShapeField(2);
				GraphicController.setFormeField(2);
			}
		});
		panel_Terrain.add(boutonCirculaire);
		
		boutonTriangulaire.setForeground(Color.BLUE);
		boutonTriangulaire.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		boutonTriangulaire.setBackground(Color.WHITE);
		boutonTriangulaire.setBounds(384, 32, 109, 23);
		boutonTriangulaire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//config.setShapeField(3);
				GraphicController.setFormeField(3);
			}
		});
		panel_Terrain.add(boutonTriangulaire);
		ButtonGroup groupFormeTerrain = new ButtonGroup();
		groupFormeTerrain.add(boutonRectangulaire);
		groupFormeTerrain.add(boutonCirculaire);
		groupFormeTerrain.add(boutonTriangulaire);
		
		listForme.add(boutonRectangulaire);
		listForme.add(boutonCirculaire);
		listForme.add(boutonTriangulaire);
		
	}
	/**
	 * Initialize the contents of the {@link ConfigPanel#panel_GameRule}
	 */
	public void setGameRule()
	{
		panel_GameRule.setLayout(null);
		panel_GameRule.setBackground(Color.WHITE);
		panel_GameRule.setBounds(0, 350, 965, 88);
		this.add(panel_GameRule);
		
		JLabel lblQuelleEstLe = new JLabel("Quelle est le mode de Jeu :");
		lblQuelleEstLe.setForeground(Color.BLUE);
		lblQuelleEstLe.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		lblQuelleEstLe.setBounds(10, 5, 544, 20);
		panel_GameRule.add(lblQuelleEstLe);
		
		boutonNormal.setForeground(Color.BLUE);
		boutonNormal.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		boutonNormal.setBackground(Color.WHITE);
		boutonNormal.setBounds(104, 32, 119, 23);
		boutonNormal.setSelected(false);
		boutonNormal.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//config.setGameRule(1);
				GraphicController.setGamerule(1);
			}
		});
		panel_GameRule.add(boutonNormal);
		
		boutonAvance.setForeground(Color.BLUE);
		boutonAvance.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		boutonAvance.setBackground(Color.WHITE);
		boutonAvance.setBounds(356, 32, 128, 23);
		boutonAvance.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//config.setGameRule(2);
				GraphicController.setGamerule(2);
			}
		});
		panel_GameRule.add(boutonAvance);
		ButtonGroup groupGameRule = new ButtonGroup();
		groupGameRule.add(boutonNormal);
		groupGameRule.add(boutonAvance);
		
		listGameRule.add(boutonNormal);
		listGameRule.add(boutonAvance);
		
	}
	/**
	 * Initialize the contents of the {@link ConfigPanel#panel_Strategy}
	 */
	public void setStrategy()
	{
		panel_Strategy.setLayout(null);
		panel_Strategy.setBackground(Color.WHITE);
		panel_Strategy.setBounds(0, 448, 965, 82);
		this.add(panel_Strategy);
		
		JLabel lblQuelleEstLa_2 = new JLabel("Quelle est la strategy de jeu des joueurs virtuels");
		lblQuelleEstLa_2.setForeground(Color.BLUE);
		lblQuelleEstLa_2.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		lblQuelleEstLa_2.setBounds(10, 5, 544, 20);
		panel_Strategy.add(lblQuelleEstLa_2);
		
		boutonFacile.setForeground(Color.BLUE);
		boutonFacile.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		boutonFacile.setBackground(Color.WHITE);
		boutonFacile.setBounds(74, 32, 119, 23);
		boutonFacile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//config.setVirtualPlayerDifficulty(2);
				GraphicController.setStrategyV(1);
				
				if(Partie.startPartie().getConfiguration().isConfigInPartie()) {
					Partie.startPartie().getConfiguration().SetConfigInPartie(1);
				}
			}
		});
		boutonIntermediaire.setSelected(false);
		boutonIntermediaire.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//config.setVirtualPlayerDifficulty(2);
				GraphicController.setStrategyV(2);
				if(Partie.startPartie().getConfiguration().isConfigInPartie()) {
					Partie.startPartie().getConfiguration().SetConfigInPartie(2);
				}
			}
		});
		panel_Strategy.add(boutonFacile);
		
		boutonIntermediaire.setForeground(Color.BLUE);
		boutonIntermediaire.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		boutonIntermediaire.setBackground(Color.WHITE);
		boutonIntermediaire.setBounds(403, 32, 119, 23);
		panel_Strategy.add(boutonIntermediaire);
		
		boutonDifficile.setForeground(Color.BLUE);
		boutonDifficile.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		boutonDifficile.setBackground(Color.WHITE);
		boutonDifficile.setBounds(773, 32, 109, 23);
		panel_Strategy.add(boutonDifficile);
		boutonDifficile.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//config.setVirtualPlayerDifficulty(3);
				GraphicController.setStrategyV(3);
				if(Partie.startPartie().getConfiguration().isConfigInPartie()) {
					Partie.startPartie().getConfiguration().SetConfigInPartie(2);
				}
			}
		});
		ButtonGroup groupStrategy = new ButtonGroup();
		groupStrategy.add(boutonFacile);
		groupStrategy.add(boutonIntermediaire);
		groupStrategy.add(boutonDifficile);
		
		listJoueurStrategy.add(boutonFacile);
		listJoueurStrategy.add(boutonIntermediaire);
		listJoueurStrategy.add(boutonDifficile);
		
	}
	/**
	 * Initialize the contents of next Element after configuration
	 */
	public void setWhatNext() {
		this.FinishConfig = new JButton("Start");
		FinishConfig.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphicController g = PartieConsoleEngine.getInstanceOfEngine(Partie.startPartie()).getGraphicController();
				if(g.canStart()) {
					g.configParty();
				}
				if(config.canStart()) {
					//config.setConfigure(true);
					
					
					GraphicController.setConfigure(true);
					panel_Joeur.setEnabled(false);

					panel_Terrain.setEnabled(false);

					panel_GameRule.setEnabled(false);
					
					panel_JoueurReel.setEnabled(false);
					
					VueGraphique.getIT().getgame().doClick();
					
					//panel_GameRule.disable();
					for(JRadioButton c : listGameRule) {
						c.setEnabled(false);
					}
					for(JRadioButton c : listForme) {
						c.setEnabled(false);
					}
					for(JRadioButton c : listJoueurR) {
						c.setEnabled(false);
					}
					for(JRadioButton c : listJoueur) {
						c.setEnabled(false);
					}
					for(JTextField c : playerName) {
						c.setEnabled(false);
					}
					
				}
			}
		});
		FinishConfig.setFont(new Font("Open Sans Semibold", Font.BOLD, 16));
		FinishConfig.setForeground(Color.BLUE);
		FinishConfig.setBounds(833, 546, 122, 38);
		this.add(FinishConfig);
		
		QuitConfig = new JButton("Quit");
		QuitConfig.setForeground(Color.BLUE);
		QuitConfig.setFont(new Font("Open Sans Semibold", Font.BOLD, 16));
		QuitConfig.setBounds(672, 546, 122, 38);
		QuitConfig.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VueGraphique.getIT().getgame().doClick();
			}
		});
		this.add(QuitConfig);
	}
	/**
	 * rend les boutons de selestion incatif
	 * sauf les bouton de choix de la strategy
	 */
	public void enableThing() {
		for(JRadioButton c : listGameRule) {
			c.setEnabled(false);
		}
		for(JRadioButton c : listForme) {
			c.setEnabled(false);
		}
		for(JRadioButton c : listJoueurR) {
			c.setEnabled(false);
		}
		for(JRadioButton c : listJoueur) {
			c.setEnabled(false);
		}
		for(JTextField c : playerName) {
			c.setEnabled(false);
		}
	}
	/**
	 * Mettre à jour le choix des Joueurs en cas de configuration en mode Console
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		//System.out.println("Massa");
		if(o instanceof Configuration) {
			//System.out.println("Entrer");
			Configuration c = (Configuration) o;
			if(GraphicController.getNumberOfPlayer() == -1) {
				if(c.getPlayerNumber()-2 >= 0) {
					if(GraphicController.getNumberOfPlayer() == -1)
						listJoueur.get(c.getPlayerNumber()-2).setSelected(true);
					this.panel_JoueurReel.setVisible(true);
					for(JRadioButton j : this.listJoueurR) {
						j.setVisible(false);
					}
					for(int i = 0; i<= c.getPlayerNumber(); i++){
						this.listJoueurR.get(i).setVisible(true);
					}
				}
				
				for(JRadioButton obs : listJoueurR)
				{
					
					if(Integer.valueOf(obs.getText()).intValue() == c.getRealPlayer() && GraphicController.getNumberOfRealPlayer() == -1) {
						obs.setSelected(true);
					}
					for(int i = 0; i < c.getRealPlayer(); i++) {
						this.playerName.get(i).setVisible(true);
						this.labelPlayer.get(i).setVisible(true);
					}
					this.panel_Terrain.setVisible(true);
				}
				//listJoueurR.get(c.getRealPlayer()).setSelected(true);
				if(c.getVirtualPlayerDifficulty()-1 >= 0) {
					listJoueurStrategy.get(c.getVirtualPlayerDifficulty()-1).setSelected(true);
				}
				if(c.getGameRule()-1 >= 0) {
					listGameRule.get(c.getGameRule()-1).setSelected(true);
				}
				if(c.getShapeField()-1 > 0)
					listForme.get(c.getShapeField()-1).setSelected(true);
			}
			if(o instanceof RealPlayer) {
				if((Update)arg == Update.NAMEIG) {
					RealPlayer p = (RealPlayer)o;
					this.playerName.get(playerreal).setText(p.getPseudo());
					playerreal++;
				}
			}
		}
	}
	/**
	 * retourne la liste des choix du nombre de {@link Player}
	 * @return une liste de {@link JRadioButton}
	 */
	public ArrayList<JRadioButton> getListJoueur() {
		return listJoueur;
	}
	/**
	 * retourne la liste des choix du nombre de {@link RealPlayer}
	 * @return une liste de {@link JRadioButton}
	 */
	public ArrayList<JRadioButton> getListJoueurR() {
		return listJoueurR;
	}
	/**
	 * retourne la liste des choix de la forme de {@link Terrain}
	 * @return une liste de {@link JRadioButton}
	 */
	public ArrayList<JRadioButton> getListForme() {
		return listForme;
	}
	/**
	 * retourne la liste des choix du {@link GameRule}
	 * @return une liste de {@link JRadioButton}
	 */
	public ArrayList<JRadioButton> getListGameRule() {
		return listGameRule;
	}
	/**
	 * retourne la liste des choix de la {@link Strategy} des {@link VirtualPlayer}
	 * @return une liste de {@link JRadioButton}
	 */
	public ArrayList<JRadioButton> getListJoueurStrategy() {
		return listJoueurStrategy;
	}
	/**
	 * retourne la liste des nom des emplacement de saisi de nom des {@link RealPlayer}
	 * @return une liste de {@link JTextField}
	 */
	public ArrayList<JTextField> getPlayerName() {
		return playerName;
	}
}
