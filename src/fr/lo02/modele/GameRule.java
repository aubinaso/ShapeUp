package fr.lo02.modele;

import java.util.ArrayList;
/**
 * <p>
 * Cette Interface permet de definir les r�gles de la partie c'est � dire comment une {@link Partie} normale se d�roule
 *</p>
 * @author Aubin Patrick TAKAM TAYO- Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01- Automne 2020 - LO02 
 * 
 * @see NormalGameRule
 * @see AdvancedGameRule
 * @see Partie
 *
 */
public interface GameRule {
	/**
	 * m�thode de distribution des {@link Card} aux {@link Player} de la {@link Partie}
	 * @param players liste des {@link Player} de la {@link Partie}
	 * @param deck instance de {@link Deck} de la {@link Partie}
	 */
	public void distributeCard(ArrayList<Player> players, Deck deck);
	/**
	 * m�thode permettant � un joueur Virtuel d'effectuer un tour de {@link Partie}
	 * @param player {@link VirtualPlayer} qui joue
	 * @param deck {@link Deck} de la {@link Partie}
	 * @param field {@link Terrain} de la {@link Partie}
	 */
	public void jouerTour(Player player, Deck deck, Terrain field);
	/**
	 * prendre une {@link Card} dans le {@link Deck} de la {@link Partie}
	 * et la rajouter dans la {@link Player#handCard}
	 * @param card {@link Card} renvoy� par le {@link Deck}
	 * @param p le {@link VirtualPlayer} en train de piocher
	 */
	public void piocherCard(Card card, Player p);
	/**
	 * m�thode permettant � un joueur Virtuel d'ajouter une {@link Card} sur le {@link Terrain}
	 * @param field {@link Terrain} sur lequel le {@link VirtualPlayer} joue
	 * @param p {@link VirtualPlayer} qui veut ajouter la {@link Card}
	 */
	public void jouerCard(Terrain field, Player p);
	/**
	 * m�thode permettant de savoir si la {@link Partie} est termin� et qu'aucun joueur ne peut plus
	 * jouer concernant cette {@link GameRule} et donc les scores doivent �tre affich�e
	 * @param myPartie {@link Partie} sur lequel on �value
	 * @return {@link Boolean#TRUE} si le {@link Partie} est bien finie
	 */
	public boolean isEnded(Partie myPartie);
}