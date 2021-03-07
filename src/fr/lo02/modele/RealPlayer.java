package fr.lo02.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
//import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Cette classe décrit un joueur Réel.<br>

 * <br> <ul>
 * <li>Une Identité  :  au moins un nom,</li>
 * <li>Une HandCard : dans lequel il conserve les cartes qu'il pioche</li>
 * <li>Une NexCard :  une carte dont il veut jouer sur le {@link Terrain}</li>
 * <li>Une VictoryCard : une Card utilisée pour le calcul de son score</li>
 * 
 * </ul>
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see VirtualPlayer
 * @see Player
 */
public class RealPlayer extends Player{
	/**
	 * indice permettant servant les méthodes de {@link RealPlayer}
	 * principalement les lignes d'ajout de {@link Card} sur le {@link Terrain}
	 */
	private int posi;
	/**
	 * indice permettant servant les méthodes de {@link RealPlayer}
	 * principalement les colonnes d'ajout de {@link Card} sur le {@link Terrain}
	 */
	private int posj;
	/**
	 * Constructeur du {@link RealPlayer}
	 * @param name {@link Player#pseudo} du jouer
	 */
	public RealPlayer( String name)
	{
		super(name);
	}
		
	/**
	 * savoir si le joueur décide de Jouer en premier ou de déplacer en premier
	 * @param pvalue un entier entre 0 et 1
	 * 0 => Joue en premier
	 * 1 => Déplace en premier
	 * @return {@link Boolean}
	 */
	public boolean playFirst(int pvalue)
	{
		boolean playFirst = false;
		if(pvalue == 1)
			playFirst = true;
		return playFirst;
	}
	/**
	 * méthode pour ajouter une {@link Card} sur le {@link Terrain}
	 * @param cardN {@link Card} à ajouter
	 * @param ligne position ligne sur le {@link Terrain}
	 * @param colonne position colonne sur le {@link Terrain}
	 * @param field {@link Terrain}
	 * @return {@link Boolean#TRUE} si l'ajout a bien été fait
	 */
	public boolean jouerCard(int cardN, int ligne, int colonne, Terrain field) {
		Card c = this.handCard.get(cardN-1);
		this.setChanged();
		this.notifyObservers(Update.No);
		return field.addCard(c, ligne, colonne, 0);
	}
	/**
	 * méthode pour déplacer une {@link Card} sur le {@link Terrain}
	 * @param c {@link Card} à déplacer
	 * @param ligne position ligne sur le {@link Terrain} de l'emplacement de positionnement final
	 * @param colonne position colonne sur le {@link Terrain} de l'emplacement de positionnement final
	 * @param field {@link Terrain} de déplacement
	 * @return {@link Boolean#TRUE} si le déplacement à bel est bien réussi
	 */
	public boolean deplacerCard(Card c, int ligne, int colonne, Terrain field) {
		return field.setPositionCard(c, posi, posj);
	}
	/**
	 * Non implémenté
	 */
	public void notif() {
		this.setChanged();
		this.notifyObservers(Update.ITISME);
	}
	/**
	 * Non implémenté
	 */
	public void noti() {
		this.setChanged();
		this.notifyObservers(Update.No);
	}
}
