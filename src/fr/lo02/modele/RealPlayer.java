package fr.lo02.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.InputMismatchException;
//import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Cette classe d�crit un joueur R�el.<br>

 * <br> <ul>
 * <li>Une Identit�  :  au moins un nom,</li>
 * <li>Une HandCard : dans lequel il conserve les cartes qu'il pioche</li>
 * <li>Une NexCard :  une carte dont il veut jouer sur le {@link Terrain}</li>
 * <li>Une VictoryCard : une Card utilis�e pour le calcul de son score</li>
 * 
 * </ul>
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01- Automne 2020 - LO02 
 * @see VirtualPlayer
 * @see Player
 */
public class RealPlayer extends Player{
	/**
	 * indice permettant servant les m�thodes de {@link RealPlayer}
	 * principalement les lignes d'ajout de {@link Card} sur le {@link Terrain}
	 */
	private int posi;
	/**
	 * indice permettant servant les m�thodes de {@link RealPlayer}
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
	 * savoir si le joueur d�cide de Jouer en premier ou de d�placer en premier
	 * @param pvalue un entier entre 0 et 1
	 * 0 => Joue en premier
	 * 1 => D�place en premier
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
	 * m�thode pour ajouter une {@link Card} sur le {@link Terrain}
	 * @param cardN {@link Card} � ajouter
	 * @param ligne position ligne sur le {@link Terrain}
	 * @param colonne position colonne sur le {@link Terrain}
	 * @param field {@link Terrain}
	 * @return {@link Boolean#TRUE} si l'ajout a bien �t� fait
	 */
	public boolean jouerCard(int cardN, int ligne, int colonne, Terrain field) {
		Card c = this.handCard.get(cardN-1);
		this.setChanged();
		this.notifyObservers(Update.No);
		return field.addCard(c, ligne, colonne, 0);
	}
	/**
	 * m�thode pour d�placer une {@link Card} sur le {@link Terrain}
	 * @param c {@link Card} � d�placer
	 * @param ligne position ligne sur le {@link Terrain} de l'emplacement de positionnement final
	 * @param colonne position colonne sur le {@link Terrain} de l'emplacement de positionnement final
	 * @param field {@link Terrain} de d�placement
	 * @return {@link Boolean#TRUE} si le d�placement � bel est bien r�ussi
	 */
	public boolean deplacerCard(Card c, int ligne, int colonne, Terrain field) {
		return field.setPositionCard(c, posi, posj);
	}
	/**
	 * Non impl�ment�
	 */
	public void notif() {
		this.setChanged();
		this.notifyObservers(Update.ITISME);
	}
	/**
	 * Non impl�ment�
	 */
	public void noti() {
		this.setChanged();
		this.notifyObservers(Update.No);
	}
}
