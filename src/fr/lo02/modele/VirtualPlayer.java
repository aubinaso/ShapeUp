package fr.lo02.modele;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * Cette classe décrit un joueur Virtuel.<br>

 * <br> <ul>
 * <li>Une Identité  :  au moins un nom,</li>
 * <li>Une HandCard : dans lequel il conserve les cartes qu'il pioche</li>
 * <li>Une NexCard :  une carte dont il veut jouer sur le {@link Terrain}</li>
 * <li>Une VictoryCard : une Card utilisée pour le calcul de son score</li>
 * <li>une Strategy de jeu</li>
 * </ul>
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see Player
 * @see RealPlayer
 * @see Strategy
 */
public class VirtualPlayer extends Player{
	/**
	 * indice comptant le nombre d'instance de {@link VirtualPlayer}
	 * permet d'attribuer des noms au Joueur
	 */
	protected static int IdBot = 1;
	/**
	 * {@link Strategy} des joueurs {@link VirtualPlayer}
	 */
	protected Strategy strategy;
	/**
	 * liste des positions d'ajout de {@link Card} du joueurs {@link VirtualPlayer}
	 */
	HashMap<ArrayList<Integer>, ArrayList<Integer>> value = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
	/**
	 * Constructeur de {@link VirtualPlayer}	
	 * @param name nom du {@link VirtualPlayer}
	 */
	public VirtualPlayer(String name)
	{
		super("Bot"+IdBot);
		//this.setPseudo("Bot"+this.IdBot);
		IdBot++;
	}
	/**
	 * méthode pour ajouter une {@link Card} sur le {@link Terrain}
	 * @param field {@link Terrain}
	 */
	@SuppressWarnings("resource")
	public void jouerCard(Terrain field)
	{
		this.gameRule.jouerCard(field, this);
	}
	
	/**
	 * récupérer la {@link Strategy} des joueur virtuelle
	 * @return {@link VirtualPlayer#strategy}
	 */
	public Strategy getStrategy() {
		return strategy;
	}
	/**
	 * mettre à jour le {@link Strategy} des joueurs virtuels
	 * @param strateg nouvelle {@link Strategy}
	 */
	public void setStrategy(Strategy strateg) {
		this.strategy = strateg;
	}
	/**
	 * déplacer une {@link Card} sur le {@link Terrain}
	 * @param field une instance de {@link Terrain} sur lequel on va déplacer
	 */
	@SuppressWarnings("resource")
	public void deplacerCard(Terrain field)
	{
		field.updateValuePossible();
		field.updateValueUsed();
		strategy.deplacerCard(field);
	}
	/**
	 * savoir si le joueur décide de Jouer en premier ou de déplacer en premier
	 * @param field un entier entre 0 et 1
	 * 0 => Joue en premier
	 * 1 => Déplace en premier
	 * @return {@link Boolean}
	 */
	public boolean playFirst(Terrain field)
	{
		return strategy.playFirst(field);
	}
	/**
	 * setter de mise à jour de la {@link Strategy}
	 * @param i un entier représentant le numéro de {@link Strategy} de 1 à 3
	 */
	public void setStateg(int i) {
		if(i == 1) {
			this.strategy = new EasyVirtualPlayer();
		}else if(i == 2){
			this.strategy = new IntermediateVirtualPlayer();
		}
		else {
			this.strategy = new DifficultVirtualPlayer();
		}
	}
	/**
	 * récupere le nombre d'instance {@link VirtualPlayer}
	 * @return {@link VirtualPlayer#IdBot}
	 */
	public static int getIdBot() {
		return IdBot;
	}
	/**
	 * mettre à jour le {@link VirtualPlayer#IdBot}
	 * @param idBot un entier pour le numéro de Bot
	 */
	public static void setIdBot(int idBot) {
		IdBot++;
	}
	
}
