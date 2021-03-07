package fr.lo02.modele;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import fr.lo02.InterfaceGraphique.VueGraphique;
/**
 * <p>
 * Cette Classe permet de definir les r�gles de la partie c'est � dire comment la {@link Partie} se d�roule
 * avec cette {@link GameRule}, les {@link Player} recoivent 1 {@link Card} aucours de la Distribution
 * et leur {@link Player#victoryCard} est attribu�e �galement pendant la distribution donc ils ont connaissance
 * de leur victory Card en d�but de {@link Partie}
 *</p>
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01- Automne 2020 - LO02 
 * 
 * @see GameRule
 * @see AdvancedGameRule
 * @see Partie
 *
 */
public class NormalGameRule implements GameRule {
	/**
	 * constructeur de la classe {@link NormalGameRule}
	 */
	public NormalGameRule() {
		// TODO Auto-generated constructor stub
		
	}
	/**
	 * m�thode de distribution des {@link Card} aux {@link Player} de la {@link Partie}
	 * @param players liste des {@link Player} de la {@link Partie}
	 * @param deck instance de {@link Deck} de la {@link Partie}
	 */
	public void distributeCard(ArrayList<Player> players, Deck deck) {
		// TODO Auto-generated method stub
		for(Player p : players)                                                                                     
		{                                                                                                                
			p.setVictoryCard(deck.getNextCard());
		}                                                                                                                
	}
	/**
	 * m�thode permettant � un joueur Virtuel d'effectuer un tour de {@link Partie}
	 * @param player {@link VirtualPlayer} qui joue
	 * @param deck {@link Deck} de la {@link Partie}
	 * @param field {@link Terrain} de la {@link Partie}
	 */
	public void jouerTour(Player player, Deck deck, Terrain field) {
		// TODO Auto-generated method stub
		if(!deck.deckIsEmpty())
			player.piocherCard(deck.getNextCard());
		if(player instanceof VirtualPlayer)
		{
			if(((VirtualPlayer)player).playFirst(field))
			{
				((VirtualPlayer)player).jouerCard(field);
				((VirtualPlayer)player).deplacerCard(field);
			}
			else
			{
				((VirtualPlayer)player).deplacerCard(field);
				((VirtualPlayer)player).jouerCard(field);
			}
		}
	}
	/**
	 * prendre une {@link Card} dans le {@link Deck} de la {@link Partie}
	 * et la rajouter dans la {@link Player#handCard}
	 * @param card {@link Card} renvoy� par le {@link Deck}
	 * @param p le {@link VirtualPlayer} en train de piocher
	 */
	public void piocherCard(Card card, Player p) {
		// TODO Auto-generated method stub
		p.setNextCard(card);
	}
	/**
	 * m�thode permettant � un joueur Virtuel d'ajouter une {@link Card} sur le {@link Terrain}
	 * @param field {@link Terrain} sur lequel le {@link VirtualPlayer} joue
	 * @param p {@link VirtualPlayer} qui veut ajouter la {@link Card}
	 */
	public void jouerCard(Terrain field, Player p) {
		// TODO Auto-generated method stub
		if(p instanceof VirtualPlayer)
		{
			field.updateValuePossible();
			field.updateValueUsed();
			((VirtualPlayer) p).getStrategy().jouerCard(field, p.getNextCard());
			p.setNextCard(null);
		}
	}
	/**
	 * m�thode permettant de savoir si la {@link Partie} est termin� et qu'aucun joueur ne peut plus
	 * jouer concernant cette {@link GameRule} et donc les scores doivent �tre affich�e
	 * @param myPartie {@link Partie} sur lequel on �value
	 * @return {@link Boolean#TRUE} si le {@link Partie} est bien finie
	 */
	public boolean isEnded(Partie myPartie) {
		// TODO Auto-generated method stub
		if(myPartie.getDeck().deckIsEmpty() || myPartie.getField().isFull()) {
			return true;
		}
		return false;
	}	

}
