package fr.lo02.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.lo02.InterfaceGraphique.Bouton;
import fr.lo02.InterfaceGraphique.BoutonHandCard;
import fr.lo02.InterfaceGraphique.GamePanel;
import fr.lo02.InterfaceGraphique.HandCard;
import fr.lo02.modele.Card;
import fr.lo02.modele.NormalGameRule;
import fr.lo02.modele.Partie;
import fr.lo02.modele.RealPlayer;
import fr.lo02.modele.Terrain;
/**
 * cette classe est le Controller des {@link Bouton} de la {@link HandCard} des {@link RealPlayer}
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseaux et Télécommunication Semestre 01 - Automne 2020 - LO02
 * 
 * @see GamePanel
 * @see BoutonHandCard
 * @see HandCard
 * @see RealPlayer
 *
 */
public class PlayAdd implements ActionListener{
	/**
	 * le {@link Bouton} qu'elle gère
	 */
	private Bouton b;
	/**
	 * Le {@link GraphicController}
	 */
	private GraphicController g;
	
	/**
	 * Constructeur de {@link PlayAdd}
	 * @param b le {@link Bouton} à associé
	 * @param g le {@link GraphicController}
	 */
	public PlayAdd(Bouton b, GraphicController g) {
		// TODO Auto-generated constructor stub
		this.b = b;
		this.g = g;
	}
	/**
	 * permet d'ajouter une {@link Card} sur le {@link Terrain},
	 * défini les cas d'ajout et les possibilités
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(b.getCard().equals("xxx")) {
			return;
		}
		if(g.getPlay() == null) {
			return;
		}
		if(!g.isSetting()) {
			g.setCard(b.getCard());
			if(g.getMyParty().getConfiguration().getGamerule() instanceof NormalGameRule)
			{
				if(PartieConsoleEngine.getInstanceOfEngine(Partie.startPartie()).isMyTurn() == false) {
					g.setCard(g.getPlay().getNextCard().getIDCARD());
					g.getPlay().setNextCard(null);
				}
			}
			else
			{
				if(PartieConsoleEngine.getInstanceOfEngine(Partie.startPartie()).isMyTurn() == false) {
					g.getPlay().setNextCard(g.defineCard(b.getCard()));
					g.getPlay().getHandCard().remove(g.defineCard(b.getCard()));
				}
			}
			g.setPlaying(true);
			g.setWantToAdd(true);
		}
	}

}
