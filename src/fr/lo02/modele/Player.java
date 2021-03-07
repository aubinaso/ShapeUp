package fr.lo02.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Observable;
//import java.util.InputMismatchException;
import java.util.Scanner;

import fr.lo02.InterfaceGraphique.Vue;
import fr.lo02.controler.GraphicController;
import fr.lo02.controler.PartieConsoleEngine;
/**
 * Cette classe décrit un joueur de manière générale.<br>

 * Un Joueur peu importe sa  nature profonde (Reel ou Virtuel) 
 * possede :<br> <ul>
 * <li>Une Identité  :  au moins un nom,</li>
 * <li>Une HandCard : dans lequel il conserve les cartes qu'il pioche</li>
 * <li>Une NexCard :  une carte dont il veut jouer sur le {@link Terrain}</li>
 * <li>Une VictoryCard : une Card utilisée pour le calcul de son score</li>
 * 
 * </ul>
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see VirtualPlayer
 * @see RealPlayer
 */
public class Player extends Observable{
	/**
	 * L'identité du Joueur : il s'agit de son nom
	 */
	protected String pseudo = "Joueur";
	/**
	 * {@link Card} victoire du {@link Player} utilisé pour calculer son Score
	 */
	protected Card victoryCard = null;
	/**
	 * Score du Joueur
	 */
	protected int score = 0;
	/**
	 * {@link Card} que le Joeur envisage déposer sur le {@link Terrain}
	 */
	protected Card nextCard = null;
	/**
	 * Ensemble des {@link Card} en main du Joueur
	 */
	protected ArrayList<Card> handCard = new ArrayList<Card>();
	/**
	 * le {@link GameRule} de la {@link Partie}
	 */
	protected GameRule gameRule;
	/**
	 * permet de savoir si le {@link Player} a effectuer toutes les actions de son tour en cours
	 * important pour le {@link RealPlayer}
	 */
	boolean hasPlayed = false;
	/**
	 * permet de savoir si le {@link Player} a terminé son tour en cours
	 */
	boolean hasTerminated = false;
	/**
	 * permet de savoir sur quel {@link Vue} le {@link Player} est en train de jouer
	 * elle vaut 1 => la vue Grahic et 2 => la vue Console 0 => pour aucun des deux
	 * elle est importante pour connaître sur quelle vue les {@link RealPlayer} sont en train de Jouer
	 */
	private int whoPlayed = 0;//2 -> Console et 1-> interface
	/**
	 * renvoie la valeur de {@link Player#whoPlayed}
	 * @return {@link Player#whoPlayed}
	 */
	public int getWhoPlayed() {
		return whoPlayed;
	}
	/**
	 * attribut une valeur qui vaut 1 si c'est le {@link GraphicController} qui appelle la méthode
	 * et 2 si c'est le {@link PartieConsoleEngine} qui l'appelle
	 * elle dit sur quel vue le Joueur est en train de Jouer
	 * elle modifie {@link Player#whoPlayed}
	 * @param whoPlayed valeur entre 1 et 2
	 */
	public void setWhoPlayed(int whoPlayed) {
		this.whoPlayed = whoPlayed;
	}
	/**
	 * dit si un {@link Player} a terminé ou pas son tour de Jeu
	 * @return {@link Boolean#TRUE} s'il a terminé son tour
	 */
	public boolean isHasTerminated() {
		return hasTerminated;
	}
	/**
	 * mettre à jour le fait qu'un {@link Player} a terminé ou pas son tour
	 * @param hasTerminated est {@link Boolean#TRUE} si le {@link Player} a terminé son tour
	 */
	public void setHasTerminated(boolean hasTerminated) {
		this.hasTerminated = hasTerminated;
	}
	/**
	 * dit si un {@link Player} a effectuer toutes ses actions
	 * @return {@link Boolean#TRUE} s'il a effectuer toute ses actions : déplacement ou ajout
	 */
	public boolean isHasPlayed() {
		return hasPlayed;
	}
	/**
	 * mettre à jour le fait qu'un {@link Player} a effectuer toutes ses actions
	 * @param hasPlayed est {@link Boolean#TRUE} si le {@link Player} a effectuer toutes ses actions
	 */
	public void setHasPlayed(boolean hasPlayed) {
		this.hasPlayed = hasPlayed;
	}
	/**
	 * Constructeur de {@link Player}
	 * @param pseudoP le pseudo du joueur
	 */
	public Player (String pseudoP) {
		this.pseudo = pseudoP;
	}
	/**
	 * mettre à jour {@link GameRule} de la {@link Partie}
	 * @param gameRule le mode de jeu
	 */
	public void setGameRule(GameRule gameRule) {
		this.gameRule = gameRule;
	}
	/**
	 * mélanger la main des {@link Player}
	 */
	public void handCardShuffle()
	{
		Collections.shuffle(this.handCard);
	}
	/**
	 * ajouter une {@link Card} à la main du {@link Player}
	 * @param card card à ajouter dans la {@link Player#handCard}
	 */
	public void addHandCard(Card card) {
		this.handCard.add(card);
	}
	/**
	 * enlever une {@link Card} à la main d'un Joueur
	 */
	public void removeHandCard() {
		int i = 0;
		for(Card c : handCard) {
			if(nextCard != null) {
				if(c.getIDCARD().equals(nextCard.getIDCARD())) {
					handCard.remove(i);
					break;
				}
			}
			i++;
		}
		setNextCard(null);
	}
	/**
	 * enlever la {@link Card} à la position i de la {@link Player#handCard} du joueur
	 * @param i position de la {@link Card} à enlever
	 */
	public void removeHandCard(int i) {
		this.handCard.remove(i);
	}
	/**
	 * prendre une {@link Card} dans le {@link Deck} de la {@link Partie}
	 * et la rajouter dans la {@link Player#handCard}
	 * @param card {@link Card} renvoyé par le {@link Deck}
	 */
	public void piocherCard(Card card)
	{
		this.gameRule.piocherCard(card, this);
		this.setChanged();
		this.notifyObservers(Update.No);
	}
	/**
	 * getter du {@link GameRule}
	 * @return un {@link GameRule}
	 */
	public GameRule getGameRule() {
		return gameRule;
	}
	/**
	 * définir une {@link Card} à partir de son idCard
	 * @see Card#assignID(Filled, Color, Shape)
	 * @param str l'id de la {@link Card}
	 * @return la {@link Card} correspondant à l'id
	 */
	public Card defineCard(String str)
	{
		Filled filled;
		Color color;
		Shape shape;
		String st = new String();
		st = "" + str.charAt(0);
		if(st.equals("f"))
			filled = Filled.FILLED;
		else if(st.equals("h"))
			filled = Filled.HOLLOW;
		else
			return null;//s'il la carte n'existe pas on ne fait rien
		st = "" + str.charAt(1);
		if(st.equals("b"))
			color = Color.BLUE;
		else if(st.equals("g"))
			color = Color.GREEN;
		else if(st.equals("r"))
			color = Color.RED;
		else
			return null;
		st = "" + str.charAt(2);
		if(st.equals("c"))
			shape = Shape.CIRCLE;
		else if(st.equals("s"))
			shape = Shape.SQUARE;
		else if(st.equals("t"))
			shape = Shape.TRIANGLE;
		else
			return null;
		return new Card(filled, color, shape);
	}
	/**
	 * assigner une {@link Player#victoryCard} au Joueur qui est la dernière {@link Card}
	 * dans le mode de jeu {@link AdvancedGameRule}
	 * @see GameRule
	 * @see AdvancedGameRule
	 */
	public void getVictoryCardAdvanced()
	{
		this.victoryCard = this.handCard.get(0);
	}
	/**
	 * récuperer le {@link Player#pseudo} du player
	 * @return {@link Player#pseudo}
	 */
	public String getPseudo()
	{
		return this.pseudo;
	}
	/**
	 * résuperer la {@link Player#handCard} du Joueur
	 * @return la liste des {@link Card} en main du Joueur
	 */
	public ArrayList<Card> getHandCard()
	{
		return this.handCard;
	}
	/**
	 * Récuperer le score du {@link Player}
	 * @return {@link Player#score}
	 */
	public int getScore()
	{
		return this.score;
	}
	/**
	 * récuperer la {@link Card} que le {@link Player} s'apprête à Jouer
	 * @return {@link Player#nextCard}
	 */
	public Card getNextCard()
	{
		return this.nextCard;
	}
	/**
	 * retourne la {@link Player#victoryCard} du Joueur
	 * @return {@link Player#victoryCard}
	 */
	public Card getVictoryCard()
	{
		return this.victoryCard;
	}
	/**
	 * change le {@link Player#pseudo}
	 * @param pseudo nouveau pseudo du {@link Player}
	 */
	public void setPseudo(String pseudo)
	{
		this.pseudo = pseudo;
	}
	/**
	 * mettre à jour le {@link Player#score du Joueur}
	 * @param score le score du Joueur
	 */
	public void setScore(int score)
	{
		this.score = score;
	}
	/**
	 * mettre à jour la {@link Player#nextCard} du joueur
	 * @param card la nouvelle {@link Card}
	 */
	public void setNextCard(Card card)
	{
		this.nextCard = card;
		this.setChanged();
		this.notifyObservers(Update.No);
	}
	/**
	 * mettre à jour la {@link Player#victoryCard} du joueur
	 * @param card la {@link Card} qui sera la victory {@link Card} de ce {@link Player}
	 */
	public void setVictoryCard(Card card)
	{
		this.victoryCard = card;
		this.setChanged();
		this.notifyObservers(Update.No);
	}

}
