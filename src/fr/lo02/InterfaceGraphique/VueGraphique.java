package fr.lo02.InterfaceGraphique;

import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Load.Launcher;
import fr.lo02.controler.Controller;
import fr.lo02.controler.GraphicController;
import fr.lo02.modele.Configuration;
import fr.lo02.modele.Partie;

/**
 * La fenetre du Jeu
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see GamePanel
 * @see Vue
 * @see ConfigEnd
 * @see ConfigTerrain
 * @see GamePanel
 * @see ConfigPanel
 * @see GraphicController
 */
public class VueGraphique implements Runnable{
	/**
	 * {@link JFrame} de la vue
	 */
	private JFrame frame;
	/**
	 * {@link Controller} Graphic
	 */
	private GraphicController myController = null;
	/**
	 * Instance de {@link VueGraphique} encours : elle implémente le patron Singleton
	 */
	private static VueGraphique IGM = null;
	/**
	 * le {@link ConfigPanel} de la {@link Partie}
	 * où se passe les Configuration
	 */
	private ConfigPanel panelConfiguration;
	/**
	 * le {@link ConfigTerrain} de la {@link Partie}
	 * où se déroule le Jeu
	 */
	private ConfigTerrain panelTerrain;
	/**
	 * le {@link ConfigEnd} de la {@link Partie}
	 * où les scores sont afficher
	 */
	private ConfigEnd panelVictory;
	/**
	 * La {@link GamePanel} de la {@link Partie} où se déroule le Jeu
	 */
	private GamePanel Game;
	/**
	 * le Modèle du Jeu, La {@link Partie} centrale
	 */
	Partie partie;
	/**
	 * La {@link Configuration} du jeu
	 */
	JButton configuration;
	JButton Next;
	JButton game;

	/**
	 * Constructeur de la {@link VueGraphique}
	 * Create the application.
	 * @wbp.parser.entryPoint
	 * @param p la {@link Partie}
	 * @param g le {@link GraphicController}
	 */
	private VueGraphique(Partie p, GraphicController g) {
		this.myController = g;
		this.partie = p;
		ConfigPanel.setNull();
		panelConfiguration = ConfigPanel.getInstance(p.getConfiguration());
		panelTerrain = new ConfigTerrain(p.getField());
		panelVictory = ConfigEnd.getInstance();
		Game = new GamePanel(this.panelTerrain);
		initialize();
		this.configuration.setVisible(false);
		this.game.setVisible(false);
	}
	/**
     * renvoi une instance de {@link VueGraphique} si il y en a une ou crée et renvoie une nouvelle instance de {@link VueGraphique} si aucune party n'est référencée
     * @param p la {@link Partie} à observé
     * @param g le {@link GraphicController}
     * @return une référence sur un Objet de type {@link VueGraphique}
     */
	public static VueGraphique getInstance(Partie p, GraphicController g)
	{
		if(VueGraphique.IGM == null)
			VueGraphique.IGM = new VueGraphique(p, g);
		return VueGraphique.IGM;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("ShapeUp");
		frame.getContentPane().setBackground(Color.BLUE);
		frame.setBounds(100, 100, 1000, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBounds(10, 42, 965, 608);
		frame.getContentPane().add(layeredPane);
		layeredPane.setLayout(new java.awt.CardLayout(0, 0));
		
		layeredPane.add(panelConfiguration, "name_1548426765511400");
		
		layeredPane.add(Game, "name_1550034547491200");
		Game.setLayout(null);
		Game.add(panelTerrain);
		layeredPane.add(panelVictory, "name_1552812791188400");
		
		configuration = new JButton("Configuration");
		configuration.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panelConfiguration);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		configuration.setBackground(Color.ORANGE);
		configuration.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		configuration.setForeground(Color.BLUE);
		configuration.setBounds(10, 8, 139, 23);
		frame.getContentPane().add(configuration);
		
		Next = new JButton("Fin");
		Next.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(panelVictory);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		Next.setBackground(Color.ORANGE);
		Next.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		Next.setForeground(Color.BLUE);
		Next.setBounds(886, 8, 89, 23);
		frame.getContentPane().add(Next);
		
		game = new JButton("Game");
		game.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				layeredPane.removeAll();
				layeredPane.add(Game);
				layeredPane.repaint();
				layeredPane.revalidate();
			}
		});
		game.setBackground(Color.ORANGE);
		game.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		game.setForeground(Color.BLUE);
		game.setBounds(477, 9, 89, 23);
		frame.getContentPane().add(game);
	}
	/**
	 * Initialisation d'une nouvelle Vue
	 */
	public void reset() {
		this.panelConfiguration = null;
		this.panelTerrain = null;
		Launcher.gameModel.setPartyStarted(false);
		Launcher.gameModel.setPartieConfigured(false);
		this.frame.setVisible(false);
		IGM = null;
	}
	/**
	 * méthode run de la vue graphique, pour gérer son Thread à Part
	 */
	public void run() {
		// TODO Auto-generated method stub
		this.frame.setVisible(true);
	}
	/**
	 * le panel de {@link ConfigEnd} de la vue
	 * @return une instance de {@link ConfigEnd}
	 */
	public ConfigEnd getPanelVictory() {
		return panelVictory;
	}
	/**
	 * le {@link JButton} de la vue de configuration
	 * elle est cahée
	 * @return une instance de {@link JButton}
	 */
	public JButton getConfiguration() {
		return configuration;
	}
	/**
	 * le {@link JButton} de la vue de fin de Partie
	 * {@link VueGraphique#Next}
	 * @return une instance de {@link JButton}
	 */
	public JButton getNext() {
		return Next;
	}
	/**
	 * le {@link JButton} de la vue du jeu
	 * elle est cahée
	 * @return une instance de {@link JButton}
	 */
	public JButton getgame() {
		return game;
	}
	/**
	 * retourne l'instance de vue Graphique
	 * @return une {@link VueGraphique}
	 */
	public static VueGraphique getIT() {
		return IGM;
	}
	/**
	 * retourne le panel de Configuration
	 * @return la {@link VueGraphique#panelConfiguration}
	 */
	public ConfigPanel getPanelConfiguration() {
		return panelConfiguration;
	}
	/**
	 * retourne le panel de jeu
	 * @return la {@link VueGraphique#Game}
	 */
	public GamePanel getGame() {
		return Game;
	}
	/**
	 * retourne le panel de fin
	 * @return la {@link VueGraphique#panelTerrain}
	 */
	public ConfigTerrain getPanelTerrain() {
		return panelTerrain;
	}
}

