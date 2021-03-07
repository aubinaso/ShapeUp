package fr.lo02.InterfaceGraphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Load.Launcher;
import fr.lo02.controler.ControllerBoutonPlayer;
import fr.lo02.controler.GraphicController;
import fr.lo02.modele.AdvancedGameRule;
import fr.lo02.modele.Card;
import fr.lo02.modele.Configuration;
import fr.lo02.modele.NormalGameRule;
import fr.lo02.modele.Partie;
import fr.lo02.modele.Player;
import fr.lo02.modele.RealPlayer;
import fr.lo02.modele.Strategy;
import fr.lo02.modele.Terrain;
import fr.lo02.modele.Update;
import fr.lo02.modele.VirtualPlayer;
/**
 * Ecran de l'évolution de la {@link Partie}
 * c'est dans cet écran également que les {@link Player} joue
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see GamePanel
 * @see Configuration
 * @see Partie
 * @see VueGraphique
 * @see Vue
 * @see GraphicController
 */
public class GamePanel extends JPanel implements Observer{
	/**
	 * La Vue du terrain
	 * @see ConfigTerrain
	 */
	ConfigTerrain panelTerrain;
	/**
	 * L'emplacement de la {@link Card} caché aucours de la {@link Partie}
	 */
	Bouton hiddencard;
	/**
	 * les Boutons des différents {@link RealPlayer}<br>
	 * Quand c'est leur tour, il clique dessus pour voir leurs mains, leur carte Victoire<br>
	 * au départ elles sont cachées et pour chaque {@link RealPlayer} elles deviennent visibles
	 * de même quand c'est le tour d'un Joueur, son Bouton devient cliquable et les autres ne sont plus cliquables
	 */
	BoutonHandCard BoutonPlayer1;
	/**
	 * les Boutons des différents {@link RealPlayer}<br>
	 * Quand c'est leur tour, il clique dessus pour voir leurs mains, leur carte Victoire<br>
	 * au départ elles sont cachées et pour chaque {@link RealPlayer} elles deviennent visibles
	 * de même quand c'est le tour d'un Joueur, son Bouton devient cliquable et les autres ne sont plus cliquables
	 */
	BoutonHandCard BoutonPlayer2;
	/**
	 * les Boutons des différents {@link RealPlayer}<br>
	 * Quand c'est leur tour, il clique dessus pour voir leurs mains, leur carte Victoire<br>
	 * au départ elles sont cachées et pour chaque {@link RealPlayer} elles deviennent visibles
	 * de même quand c'est le tour d'un Joueur, son Bouton devient cliquable et les autres ne sont plus cliquables
	 */
	BoutonHandCard BoutonPlayer3;
	/**
	 * Bouton pour configurer la Partie
	 * on ne peut l'utiliser en cours de {@link Partie} pour changer la {@link Strategy} des 
	 * {@link VirtualPlayer}
	 */
	JButton ConfigInPartie;
	/**
	 * Bouton pour reprendre un nouveau tour de {@link Partie}
	 */
	JButton restart;
	/**
	 * Bouton pour quitter la {@link Partie} en cours, avec ce Bouton, tout s'arrête, le joueur a abandonné la {@link Partie}
	 */
	JButton quit;
	/**
	 * la liste des {@link BoutonHandCard} des {@link RealPlayer}
	 */
	ArrayList<BoutonHandCard> players;
	/**
	 * la liste des {@link ControllerBoutonPlayer} des emplacements {@link Bouton} sur le {@link Terrain}
	 */
	ArrayList<ControllerBoutonPlayer> controllerBoutonPlayer;
	/**
	 * Bouton Pour voir un message donnée
	 * @see FrameMessage
	 * NON implémenté car pas de message
	 */
	JButton seeLabel;
	/**
	 * le Label d'affichage des messages en vue graphique venant de la partie
	 */
	JLabel label = new JLabel();
	/**
	 * {@link Boolean} pour savoir si le panel d'affichage de message est ouvert
	 */
	private boolean see = false;
	/**
	 * Message d'information des {@link Player} en version Graphic
	 */
	FrameMessage frame = new FrameMessage();
	
	/**
     * Constructeur de la {@link GamePanel}    
     * @param panelTerrain le panel d'affichage de {@link Terrain} 
     */
	public GamePanel(ConfigTerrain panelTerrain) {
		this.panelTerrain = panelTerrain;
		this.players = new ArrayList<BoutonHandCard>();
		this.init();
	}
	/**
	 * Initialize the contents of the frame.
	 */
	public void init()
	{
		this.setLayout(null);
		this.add(panelTerrain);
		setHiddenCard();
		setButtonPLayer();
	}
	/**
	 * Initialiser {@link GamePanel#BoutonPlayer1}, {@link GamePanel#BoutonPlayer1}, {@link GamePanel#BoutonPlayer1}
	 */
	public void setButtonPLayer()
	{
		BoutonPlayer1 = new BoutonHandCard("Player1", 1, 1);
		BoutonPlayer1.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		BoutonPlayer1.setForeground(Color.BLUE);
		BoutonPlayer1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BoutonPlayer1.setBounds(440, 44, 132, 29);
		this.add(BoutonPlayer1);
		this.players.add(BoutonPlayer1);
		
		BoutonPlayer2 = new BoutonHandCard("Player2", 2, 2);
		BoutonPlayer2.setForeground(Color.BLUE);
		BoutonPlayer2.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		BoutonPlayer2.setBounds(247, 548, 132, 29);
		this.add(BoutonPlayer2);
		this.players.add(BoutonPlayer2);
		
		BoutonPlayer3 = new BoutonHandCard("Player3", 3, 3);
		BoutonPlayer3.setForeground(Color.BLUE);
		BoutonPlayer3.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		BoutonPlayer3.setBounds(613, 548, 132, 29);
		this.add(BoutonPlayer3);
		this.players.add(BoutonPlayer3);
		
		ConfigInPartie = new JButton("Config");
		ConfigInPartie.setForeground(Color.BLUE);
		ConfigInPartie.setFont(new Font("Open Sans Semibold", Font.BOLD, 16));
		ConfigInPartie.setBounds(761, 32, 122, 38);
		ConfigInPartie.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				VueGraphique.getIT().getConfiguration().doClick();
			}
		});
		this.add(ConfigInPartie);
		
		restart = new JButton("Restart");
		restart.setForeground(Color.BLUE);
		restart.setFont(new Font("Open Sans Semibold", Font.BOLD, 16));
		restart.setBounds(613, 32, 122, 38);
		this.add(restart);
		restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//GraphicController.getInstance(Partie.startPartie()).setRestart(true);
				try {
					Launcher.restart();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					//e1.printStackTrace();
					System.out.println("Impossible to restart");
				}
			}
		});
		
		quit = new JButton("Quit");
		quit.setForeground(Color.BLUE);
		quit.setFont(new Font("Open Sans Semibold", Font.BOLD, 16));
		quit.setBounds(774, 554, 122, 38);
		this.add(quit);
		quit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
		
		for(JButton b : this.players) {
			b.setEnabled(false);
			b.setVisible(false);
		}
		
		seeLabel = new JButton("seeMessage");
		seeLabel.setForeground(Color.BLUE);
		seeLabel.setFont(new Font("Open Sans Semibold", Font.BOLD, 14));
		seeLabel.setBounds(239, 44, 132, 29);
		this.add(seeLabel);
		seeLabel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frame.getLabel().setText(label.getText());
				frame .setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				if(see == false)
					see = true;
				else
					see = false;
				frame.setVisible(see);
			}
		});
	}
	/**
	 * Non implémenté
	 * 
	 * @param g le COntroller Graphic
	 */
	public void addHandler(GraphicController g) {
		restart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//g.setRestart(true);
			}
		});
	}
	/**
	 * Ajouter la {@link Card} caché au {@link Terrain}
	 */
	public void setHiddenCard() {
		hiddencard = new Bouton("Hidden Card", 0, 0);
		ImageIcon img = new ImageIcon("aaa.jpg");
		hiddencard.setIcon(img);
		hiddencard.setBounds(68, 11, 161, 88);
		this.add(hiddencard);
	}
	/**
	 * mettre à Jour le panel
	 * dans le cas où on doit rendre certains {@link BoutonHandCard} de certains joueur non cliquable
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof Partie)
		{
			this.controllerBoutonPlayer = new ArrayList<ControllerBoutonPlayer>();
			if(((Update)arg) == Update.No) {
				int i = 0;
				//for(JTextArea j : )
				for(Player p : ((Partie)o).getPlayers()) {
					//System.out.println("I am there");
					//this.players.get(i).setPseudo(p.getPseudo());
					this.players.get(i).setText(p.getPseudo());
					//this.players.get(i).repaint();
					if(p instanceof RealPlayer) {
						this.players.get(i).setEnabled(true);
						this.players.get(i).setVisible(true);
						this.controllerBoutonPlayer.add(new ControllerBoutonPlayer((Partie)o, this.players.get(i)));
						this.players.get(i).getHandCard().setPlayerName(p.getPseudo());
						((Partie) o).getPlayers().get(i).addObserver(this.players.get(i));
						i++;
					}
				}
			}
			if(((Update)arg) == Update.INT) {
				this.see = true;
				this.label.setText(((Partie)o).getBroadCast());
				this.frame.setVisible(see);
			}
		}
		if(o instanceof RealPlayer) {
			if(((Update)arg) == Update.No) {
				int i = 0;
				for(BoutonHandCard b : this.players)
					b.setEnabled(false);
				for(Player p : Partie.startPartie().getPlayers()) {
					if(p instanceof RealPlayer) {
						if(p == (RealPlayer)o) {
							if(Partie.startPartie().getConfiguration().getGamerule() instanceof NormalGameRule)
								this.players.get(i).setEnabled(true);
						}
						i++;
					}
				}
			}
			else {
				this.see = true;
				this.frame.getLabel().setText(label.getText());
				this.frame.setVisible(see);
			}
		}
	}
	/**
	 * retourne la liste des {@link BoutonHandCard} des {@link RealPlayer}
	 * @return le {@link GamePanel#players}
	 */
	public ArrayList<BoutonHandCard> getPlayers() {
		return players;
	}
	/**
	 * rend certains {@link BoutonHandCard} de certains joueur non cliquable
	 * et rend pour celui en cours cliquable
	 * @param play {@link RealPlayer} dont on veut rendre son Bouton cliquable
	 */
	public void arrange(RealPlayer play) {
		int i = 0;
		for(BoutonHandCard b : this.players)
			b.setEnabled(false);
		for(Player p : Partie.startPartie().getPlayers()) {
			if(p instanceof RealPlayer) {
				if(p == play) {
					if(Partie.startPartie().getConfiguration().getGamerule() instanceof NormalGameRule)
						this.players.get(i).setEnabled(true);
				}
				i++;
			}
		}
	}
	/**
	 * retourne le bouton du {@link RealPlayer} 1
	 * @return {@link GamePanel#BoutonPlayer1}
	 */
	public BoutonHandCard getBoutonPlayer1() {
		return BoutonPlayer1;
	}
	/**
	 * retourne le bouton du {@link RealPlayer} 2
	 * @return {@link GamePanel#BoutonPlayer2}
	 */
	public BoutonHandCard getBoutonPlayer2() {
		return BoutonPlayer2;
	}
	/**
	 * retourne le bouton du {@link RealPlayer} 3
	 * @return {@link GamePanel#BoutonPlayer3}
	 */
	public BoutonHandCard getBoutonPlayer3() {
		return BoutonPlayer3;
	}
	/**
	 * retourne le bouton pour recommencer
	 * @return {@link GamePanel#restart}
	 */
	public JButton getRestart() {
		return restart;
	}
}
