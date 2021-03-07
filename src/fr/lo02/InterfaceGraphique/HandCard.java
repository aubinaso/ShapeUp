package fr.lo02.InterfaceGraphique;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.SwingConstants;

import Load.Launcher;
import fr.lo02.controler.GraphicController;
import fr.lo02.controler.PlayAdd;
import fr.lo02.modele.Card;
import fr.lo02.modele.Partie;
import fr.lo02.modele.Player;
import fr.lo02.modele.RealPlayer;

import java.awt.Color;
import javax.swing.JButton;
/**
 * Ecran du vue de la main des {@link RealPlayer} de la {@link Partie}
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see GamePanel
 * @see BoutonHandCard
 * @see VueGraphique
 * @see Vue
 * @see GraphicController
 * @see Launcher
 * @see Partie
 */
public class HandCard {
	/**
	 * {@link JFrame}
	 */
	private JFrame frame;
	/**
	 * pseudo du {@link RealPlayer}
	 */
	private JTextField PlayerName;
	/**
	 * Bouton qui est l'emplacement de la Victory {@link Card} de ce joueur
	 */
	Bouton VictoryCard;
	/**
	 * {@link Boolean} permettant de savoir si la victory {@link Card} du {@link RealPlayer} player est ouverte ou pas
	 */
	private boolean seeCard = false;
	/**
	 * Bouton qui ouvre ou cache la Victory-{@link Card} du joueur
	 */
	JButton seeVictoryCard;
	/**
	 * Bouton qui permet au {@link RealPlayer} de dire qu'il a finir
	 * et de vérifier effectivement qu'il a fini
	 */
	private JButton finished;
	/**
	 * Bouton qui est l'emplacement de la Next-{@link Card} de ce joueur
	 */
	Bouton NextCard;
	/**
	 * Bouton qui est l'emplacement d'une {@link Card} dans le {@link Player#getHandCard()}
	 */
	Bouton Card1;
	/**
	 * Bouton qui est l'emplacement d'une {@link Card} dans le {@link Player#getHandCard()}
	 */
	Bouton Card2;
	/**
	 * Bouton qui est l'emplacement d'une {@link Card} dans le {@link Player#getHandCard()}
	 */
	Bouton Card3;
	ArrayList<Bouton> card = new ArrayList<Bouton>();
	
	/**
	 * Constructeur de la {@link HandCard}
	 */
	public HandCard() {
		initialize();
	}
	/**
	 * initialise le contenu de {@link HandCard#Card1}, {@link HandCard#Card1}, {@link HandCard#Card1}
	 */
	public void initHandCard() {
		this.getCard().get(0).setImage("xxx.jpg");
		this.getCard().get(1).setImage("xxx.jpg");
		this.getCard().get(2).setImage("xxx.jpg");
	}
	/**
	 * ajoute les différent Handler aux différents Bouton
	 * @param g le {@link GraphicController}
	 */
	public void addHandler(GraphicController g) {
		NextCard.addActionListener(new PlayAdd(NextCard, g));
		Card1.addActionListener(new PlayAdd(Card1, g));
		Card2.addActionListener(new PlayAdd(Card2, g));
		Card3.addActionListener(new PlayAdd(Card3, g));
		this.finished.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(g.isHasplayed())
				{
					g.setHasFinished(true);
					frame.setVisible(false);
				}
			}
		});
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 484, 411);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		PlayerName = new JTextField();
		PlayerName.setForeground(Color.BLUE);
		PlayerName.setHorizontalAlignment(SwingConstants.CENTER);
		PlayerName.setText("My Name");
		PlayerName.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		PlayerName.setBounds(10, 11, 131, 34);
		panel.add(PlayerName);
		PlayerName.setColumns(10);
		
		Card1 = new Bouton("Ma carte", 0, 0);
		Card1.setForeground(Color.BLUE);
		Card1.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		Card1.setBounds(10, 218, 131, 182);
		panel.add(Card1);
		this.card.add(Card1);
		
		Card2 = new Bouton("Ma carte", 1, 1);
		Card2.setForeground(Color.BLUE);
		Card2.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		Card2.setBounds(177, 218, 131, 182);
		panel.add(Card2);
		this.card.add(Card2);
		
		Card3 = new Bouton("Ma carte", 2, 2);
		Card3.setForeground(Color.BLUE);
		Card3.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		Card3.setBounds(343, 218, 131, 182);
		panel.add(Card3);
		this.card.add(Card3);
		
		NextCard = new Bouton("next carte", 0, 0);
		NextCard.setBounds(177, 11, 131, 182);
		panel.add(NextCard);
		
		VictoryCard = new Bouton("carte victoire", 0, 0);
		VictoryCard.setBounds(343, 11, 131, 182);
		panel.add(VictoryCard);
		this.VictoryCard.setVisible(this.seeCard);
		
		seeVictoryCard = new JButton("Victory Card");
		seeVictoryCard.setForeground(Color.BLUE);
		seeVictoryCard.setHorizontalAlignment(SwingConstants.CENTER);
		seeVictoryCard.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		seeVictoryCard.setBounds(10, 84, 131, 34);
		panel.add(seeVictoryCard);

		finished = new JButton("Finished");
		finished.setForeground(Color.BLUE);
		finished.setHorizontalAlignment(SwingConstants.CENTER);
		finished.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		finished.setBounds(10, 153, 131, 34);

		panel.add(finished);
		this.seeVictoryCard.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(seeCard == false)
					seeCard = true;
				else
					seeCard = false;
				VictoryCard.setVisible(seeCard);
				frame.repaint();
			}
		});
	}
	/**
	 * retourne le Bouton de la {@link HandCard#VictoryCard} du {@link Player} donc la main est associée à cette {@link HandCard}
	 * @return une liste de {@link JButton}
	 */
	public Bouton getVictoryCard() {
		return VictoryCard;
	}
	/**
	 * retourne le Bouton de {@link HandCard#finished} du {@link Player} donc la main est associée à cette {@link HandCard}
	 * @return une liste de {@link JButton}
	 */
	public JButton getFinished() {
		return finished;
	}
	/**
	 * retourne le Bouton de {@link HandCard#NextCard} du {@link Player} donc la main est associée à cette {@link HandCard}
	 * @return une liste de {@link JButton}
	 */
	public Bouton getNextCard() {
		return NextCard;
	}
	/**
	 * retourne le Bouton de {@link HandCard#PlayerName} du {@link Player} donc la main est associée à cette {@link HandCard}
	 * @return une liste de {@link JTextField}
	 */
	public JTextField getPlayerName() {
		return PlayerName;
	}
	/**
	 * retourne le Bouton de {@link HandCard#frame} du {@link Player} donc la main est associée à cette {@link HandCard}
	 * @return une liste de {@link JRadioButton}
	 */
	public JFrame getFrame() {
		return frame;
	}
	/**
	 * met à jour le pseudo du {@link RealPlayer} associé à ce HandCard
	 * @param name pseudo du {@link RealPlayer}
	 */
	public void setPlayerName(String name) {
		PlayerName.setText(name);
	}
	/**
	 * retourne le Bouton de {@link HandCard#card} du {@link Player} donc la main est associée à cette {@link HandCard}
	 * @return une liste de {@link Bouton}
	 */
	public ArrayList<Bouton> getCard() {
		return card;
	}
}
