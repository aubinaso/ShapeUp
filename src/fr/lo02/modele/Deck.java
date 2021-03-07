package fr.lo02.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Cette classe decrit La Pioche/Banque � carte 
 * Le ShapeUp est un jeu de 18 Cartes Jouables 
 * 
 * cette classe implemente le Pattern Singleton , un processus ne peut donc instancier qu'un seul objet de ce type � la fois 
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01- Automne 2020 - LO02 
 * @see Card
 * @see ArrayList
 *
 */
public class Deck {
	/**
	 * L'instance du {@link Deck}
	 * <br> Ce programme n'admet qu'une seule pioche qui puisse �tre utilis�e en cours de jeu
	 * <br> la classe {@link Deck} impl�ment� le pattern singleton
	 */
	private static Deck deck = null;
	/**
	 * le nombre de carte max de du jeu
	 */
	private static int TOTALCARTE = 18;
	/**
	 * le nombre de carte encore pr�sent dans le {@link Deck}
	 */
	private int carteRestant = 18;
	/**
	 * La liste des {@link Card} de ce {@link Deck}
	 */
	private List<Card> collectcard = new ArrayList<Card>();
	
	/**
	 * Constructeur du {@link Deck} de la {@link Partie}
	 */
	private Deck()
	{
		this.initializeCollectCard();
	}
	
	/**
	 * Cr�ation des 18 {@link Card}
	 */
	public void initializeCollectCard()
	{
		for(Color color : Color.values())
			this.collectcard.add(new Card(Filled.HOLLOW, color, Shape.CIRCLE));
		for(Color color : Color.values())
			this.collectcard.add(new Card(Filled.HOLLOW, color, Shape.SQUARE));
		for(Color color : Color.values())
			this.collectcard.add(new Card(Filled.HOLLOW, color, Shape.TRIANGLE));
		for(Color color : Color.values())
			this.collectcard.add(new Card(Filled.FILLED, color, Shape.CIRCLE));
		for(Color color : Color.values())
			this.collectcard.add(new Card(Filled.FILLED, color, Shape.SQUARE));
		for(Color color : Color.values())
			this.collectcard.add(new Card(Filled.FILLED, color, Shape.TRIANGLE));
		this.ShuffleCard();
	}
	
	/**
	 * @return renvoie la r�f�rence sur un object de type {@link Deck} si il y'a un<br>
	 * Sinon cr�e une r�f�rence sur un objet de type {@link Deck} avant de la renvoyer<br>
	 */
	public static Deck getInstance()
	{
		if(deck == null) {
			deck = new Deck();
		}
		return Deck.deck;
	}
	/**
	 * @return getters de {@link Deck#carteRestant}
	 */
	public int getCarteRestant()
	{
		return this.carteRestant;
	}
	/**
	 * setters de {@link Deck#carteRestant}
	 * @param carterestant {@link Deck#carteRestant}
	 */
	public void setCarteRestant(int carterestant)
	{
		this.carteRestant = carterestant;
	}
	/**
	 * permet de recr�er un nouveau {@link Deck}
	 */
	public void deckInitialize()
	{
		this.deckDestroy();
		Deck.deck = this.getInstance();
		this.initializeCollectCard();
	}
	
	/**
	 * d�r�f�rence le deck courant
	 */
	public void deckDestroy()
	{
		Deck.deck = null;
		this.setTotalCarte(18);
		this.carteRestant = 18;
		this.collectcard.clear();
	}
	/**
	 * enl�ve la premi�re {@link Card} de la Pioche
	 */
	public void removeCard()
	{
		this.collectcard.remove(0);
	}
	/**
	 * renvoie une carte aucours des pioches 
	 * {@link Player#piocherCard(Card)}
	 * @return {@link Card}
	 */
	public Card getNextCard()
	{
		this.ShuffleCard();
		Card card = this.collectcard.get(0);
		this.setCarteRestant(this.carteRestant-1);
		this.removeCard();
		return card;
	}
	/**
	 * elle renvoie les infos de ce {@link Deck}
	 */
	public String toString()
	{
		StringBuffer sd = new StringBuffer();
		sd.append("Je suis la banque et j'ai encore comme carte restante " + this.getCarteRestant());
		sd.append("\nLes cartes pr�sentes sont : ");
		for(Card c : this.collectcard)
			sd.append("\n" + c);
		return sd.toString();
	}
	/**
	 * permet de m�langer les {@link Card} du {@link Deck#collectcard}
	 * @see Collections#shuffle(List)
	 */
	public void ShuffleCard()
	{
		Collections.shuffle(this.collectcard);
	}
	/**
	 * permet de savoir si les deck est vide ou pas
	 * @return {@link Boolean} qui est {@link Boolean#TRUE} si le {@link Deck#collectcard} est vide et
	 * {@link Boolean#FALSE} sinon
	 */
	public boolean deckIsEmpty()
	{
		if(this.carteRestant > 0 && Deck.deck != null)
			return false;
		else
			return true;
	}
	/**
	 * getters de {@link Deck#TOTALCARTE}
	 * @return {@link Deck#TOTALCARTE} qui est le nombre max de carte : 18
	 */
	public int getTotalCarte() {
		return TOTALCARTE;
	}
	/**
	 * setters permettant de modifier la veleur du nombre total de carte
	 * non utilis�; dans le cas d'une �volution future
	 * @param totalCarte : valeur attribu�e � {@link Deck#TOTALCARTE}
	 */
	public void setTotalCarte(int totalCarte) {
		TOTALCARTE = totalCarte;
	}
}
