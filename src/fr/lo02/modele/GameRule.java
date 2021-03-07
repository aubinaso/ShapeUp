package fr.lo02.modele;

import java.util.ArrayList;
/**
 * <p>
 * Cette Interface permet de definir les règles de la partie c'est à dire comment une {@link Partie} normale se déroule
 *</p>
 * @author Aubin Patrick TAKAM TAYO- Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * 
 * @see NormalGameRule
 * @see AdvancedGameRule
 * @see Partie
 *
 */
public interface GameRule {
	/**
	 * méthode de distribution des {@link Card} aux {@link Player} de la {@link Partie}
	 * @param players liste des {@link Player} de la {@link Partie}
	 * @param deck instance de {@link Deck} de la {@link Partie}
	 */
	public void distributeCard(ArrayList<Player> players, Deck deck);
	/**
	 * méthode permettant à un joueur Virtuel d'effectuer un tour de {@link Partie}
	 * @param player {@link VirtualPlayer} qui joue
	 * @param deck {@link Deck} de la {@link Partie}
	 * @param field {@link Terrain} de la {@link Partie}
	 */
	public void jouerTour(Player player, Deck deck, Terrain field);
	/**
	 * prendre une {@link Card} dans le {@link Deck} de la {@link Partie}
	 * et la rajouter dans la {@link Player#handCard}
	 * @param card {@link Card} renvoyé par le {@link Deck}
	 * @param p le {@link VirtualPlayer} en train de piocher
	 */
	public void piocherCard(Card card, Player p);
	/**
	 * méthode permettant à un joueur Virtuel d'ajouter une {@link Card} sur le {@link Terrain}
	 * @param field {@link Terrain} sur lequel le {@link VirtualPlayer} joue
	 * @param p {@link VirtualPlayer} qui veut ajouter la {@link Card}
	 */
	public void jouerCard(Terrain field, Player p);
	/**
	 * méthode permettant de savoir si la {@link Partie} est terminé et qu'aucun joueur ne peut plus
	 * jouer concernant cette {@link GameRule} et donc les scores doivent être affichée
	 * @param myPartie {@link Partie} sur lequel on évalue
	 * @return {@link Boolean#TRUE} si le {@link Partie} est bien finie
	 */
	public boolean isEnded(Partie myPartie);
}