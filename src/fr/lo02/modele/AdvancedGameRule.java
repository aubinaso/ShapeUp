package fr.lo02.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Scanner;
/**
 * <p>
 * Cette Classe permet de definir les règles de la partie c'est à dire comment la {@link Partie} se déroule
 * avec cette {@link GameRule}, les {@link Player} recoivent 3 {@link Card} aucours de la Distribution
 * et leur {@link Player#victoryCard} est leur dernière {@link Card} en main en fin de {@link Partie}
 *</p>
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * 
 * @see GameRule
 * @see NormalGameRule
 * @see Partie
 *
 */
public class AdvancedGameRule implements GameRule {
	/**
	 * Constructeur de la classe {@link AdvancedGameRule}
	 */
	public AdvancedGameRule() {
		// TODO Auto-generated constructor stub

	}
	/**
	 * méthode de distribution des {@link Card} aux {@link Player} de la {@link Partie}
	 * @param players liste des {@link Player} de la {@link Partie}
	 * @param deck instance de {@link Deck} de la {@link Partie}
	 */
	public void distributeCard(ArrayList<Player> players, Deck deck) {
		// TODO Auto-generated method stub
		for(int i=0; i<=2; i++)                                                          
		{                                                                                
			for(Player p : players)                                                 
			{                                                                            
				p.addHandCard(deck.getNextCard());
				if(p instanceof RealPlayer)
					((RealPlayer) p).noti();
			}  
		}
	}
	/**
	 * méthode permettant à un joueur Virtuel d'effectuer un tour de {@link Partie}
	 * @param player {@link VirtualPlayer} qui joue
	 * @param deck {@link Deck} de la {@link Partie}
	 * @param field {@link Terrain} de la {@link Partie}
	 */
	public void jouerTour(Player player, Deck deck, Terrain field) {
		// TODO Auto-generated method stub
		if(player.getHandCard().size()>=2) {
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
				if(!deck.deckIsEmpty())
					((VirtualPlayer)player).piocherCard(deck.getNextCard());
			}
		}
	}
	/**
	 * prendre une {@link Card} dans le {@link Deck} de la {@link Partie}
	 * et la rajouter dans la {@link Player#handCard}
	 * @param card {@link Card} renvoyé par le {@link Deck}
	 * @param p le {@link VirtualPlayer} en train de piocher
	 */
	public void piocherCard(Card card, Player p) {
		// TODO Auto-generated method stub
		p.addHandCard(card);
	}
	/**
	 * méthode permettant à un joueur Virtuel d'ajouter une {@link Card} sur le {@link Terrain}
	 * @param field {@link Terrain} sur lequel le {@link VirtualPlayer} joue
	 * @param p {@link VirtualPlayer} qui veut ajouter la {@link Card}
	 */
	public void jouerCard(Terrain field, Player p) {
		// TODO Auto-generated method stub
		if(p instanceof VirtualPlayer)
		{
			field.updateValuePossible();
			field.updateValueUsed();
			p.handCardShuffle();
			p.setNextCard(p.getHandCard().get(0));
			p.removeHandCard(0);
			((VirtualPlayer) p).getStrategy().jouerCard(field, p.getNextCard());
			p.setNextCard(null);
		}
	}
	/**
	 * méthode permettant de savoir si la {@link Partie} est terminé et qu'aucun joueur ne peut plus
	 * jouer concernant cette {@link GameRule} et donc les scores doivent être affichée
	 * @param myPartie {@link Partie} sur lequel on évalue
	 * @return {@link Boolean#TRUE} si le {@link Partie} est bien finie
	 */
	public boolean isEnded(Partie myPartie) {
		// TODO Auto-generated method stub
		if(myPartie.getDeck().deckIsEmpty() || myPartie.getField().isFull()) {
			for(Player p : myPartie.getPlayers()){
				if(p.getHandCard().size() >= 2)
					return false;
			}
			return true;
		}
		return false;
	}

}
