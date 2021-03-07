package fr.lo02.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import fr.lo02.InterfaceGraphique.Bouton;
import fr.lo02.InterfaceGraphique.BoutonHandCard;
import fr.lo02.InterfaceGraphique.GamePanel;
import fr.lo02.InterfaceGraphique.HandCard;
import fr.lo02.modele.Card;
import fr.lo02.modele.Partie;
import fr.lo02.modele.Player;
import fr.lo02.modele.RealPlayer;
/**
 * cette classe est le Controller des {@link BoutonHandCard} des {@link RealPlayer}
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseaux et Télécommunication Semestre 01 - Automne 2020 - LO02
 * 
 * @see GamePanel
 * @see BoutonHandCard
 * @see HandCard
 * @see RealPlayer
 * @see GamePanel#getBoutonPlayer1()
 * @see GamePanel#getBoutonPlayer2()
 * @see GamePanel#getBoutonPlayer3()
 *
 */
public class ControllerBoutonPlayer {
	/**
	 * La {@link Partie} en cours
	 */
	Partie partie;
	/**
	 * le {@link BoutonHandCard} qu'elle gère
	 */
	BoutonHandCard bouton;
	/**
	 * {@link Boolean} permettant de savoir si la main du {@link RealPlayer} qu'elle gère est ouverte ou pas
	 */
	private boolean open = false;
	/**
	 * Constructeur de {@link ControllerBoutonPlayer}
	 * @param p la {@link Partie}
	 * @param b le {@link BoutonHandCard} à associer
	 */
	public ControllerBoutonPlayer(Partie p, BoutonHandCard b) {
		this.partie = p;
		this.bouton = b;
		this.bouton.addActionListener(new ActionListener() {
			
			/**
			 * elle Ouvre la main des joueurs réels si elle n'était pas ouverte et la referme au cas où elle
			 * était ouverte
			 */
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(open == false)
					open = true;
				else
					open = false;
				((BoutonHandCard)e.getSource()).getHandCard().getFrame().setVisible(open);
			}
		});
	}
}
