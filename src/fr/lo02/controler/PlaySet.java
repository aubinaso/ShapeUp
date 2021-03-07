package fr.lo02.controler;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import fr.lo02.InterfaceGraphique.Bouton;
import fr.lo02.InterfaceGraphique.BoutonHandCard;
import fr.lo02.InterfaceGraphique.GamePanel;
import fr.lo02.InterfaceGraphique.HandCard;
import fr.lo02.modele.AdvancedGameRule;
import fr.lo02.modele.Card;
import fr.lo02.modele.Partie;
import fr.lo02.modele.RealPlayer;
import fr.lo02.modele.Terrain;

/**
 * cette classe est le Controller des {@link Bouton} du {@link Terrain} de la {@link Partie}
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseaux et Télécommunication Semestre 01 - Automne 2020 - LO02
 * 
 * @see GamePanel
 * @see BoutonHandCard
 * @see HandCard
 * @see RealPlayer
 *
 */
public class PlaySet implements ActionListener{
	/**
	 * le {@link Bouton} qu'elle gère
	 */
	Bouton b;
	/**
	 * Le {@link GraphicController}
	 */
	GraphicController g;
	
	/**
	 * Constructeur de {@link PlaySet}
	 * @param b le {@link Bouton} à associé
	 * @param g le {@link GraphicController}
	 */
	public PlaySet(Bouton b, GraphicController g) {
		this.b = b;
		this.g = g;
	}

	/**
	 * permet de déplacer une {@link Card} sur le {@link Terrain},
	 * défini les cas de déplacement et les possibilités
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(g.getPlay() == null)
			return;
		if(!g.isPlaying()) {
			if(b.getCard().equals("aaa")) {
				if(g.isWantToAdd()) {
					g.setPosi(b.getLigne());
					g.setPosj(b.getColonne());
					if(g.isMyTurn() == true || ((RealPlayer)g.getPlay()).isHasPlayed() == false) {
						if(g.getMyParty().getField().setPositionCard(g.defineCard(g.getCard()), b.getLigne(), b.getColonne())) {
							g.setMyTurn(true);
							g.getPlay().setHasPlayed(true);
							g.getPlay().setWhoPlayed(1);
							g.setWantToAdd(false);
							g.setHasSet(true);
							Partie.startPartie().setSequenceStarted(true);
							g.setSetting(false);
						}
					}
				}
			}
			else {
				if(!g.isHasSet()) {
					g.setSetting(true);
					g.setCard(b.getCard());
					g.setWantToAdd(true);
				}
			}
		}
		else {
			if(b.getCard().equals("aaa")) {
				g.setPosi(b.getLigne());
				g.setPosj(b.getColonne());
				if(g.isMyTurn() == true || ((RealPlayer)g.getPlay()).isHasPlayed() == false) {
					if(g.getMyParty().getField().addCard(g.defineCard(g.getCard()), b.getLigne(), b.getColonne(), 1)) {
						if(Partie.startPartie().getConfiguration().getGamerule() instanceof AdvancedGameRule)
							g.Update();
						g.setMyTurn(true);
						g.getPlay().setHasPlayed(true);
						g.getPlay().setWhoPlayed(1);
						g.setPlaying(false);
						g.setHasplayed(true);
						Partie.startPartie().setSequenceStarted(true);
						g.setWantToAdd(false);
					}
				}
			}
		}
	}

}
