package fr.lo02.modele;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Scanner;

import fr.lo02.InterfaceGraphique.ConfigPanel;
import fr.lo02.InterfaceGraphique.VueGraphique;
import fr.lo02.controler.GraphicController;
/**
 * cette classe d�crit la configuration de la {@link Partie} courante
 * le ShapeUp est un jeu d�finissant dans sa <b>version 1.0</b> les �l�ments suivants :
 * <ul>
 * <li>le nombre de jouers total entre 2 et 3</li>
 * <li>le nombre de joueurs r�els</li>
 * <li>la strategy des joueurs virtuels
 * 		<ul>
 * 			<li>{@link Strategy}</li>
 * 			<li>{@link EasyVirtualPlayer}</li>
 * 			<li>{@link IntermediateVirtualPlayer}</li>
 * 			<li>{@link DifficultVirtualPlayer}</li>
 * 		</ul>
 * </li>
 * <li>le mode de jeu de la {@link Partie}
 * 		<ul>
 * 			<li>{@link GameRule}</li>
 * 			<li>{@link NormalGameRule}</li>
 * 			<li>{@link AdvancedGameRule}</li>
 * 		</ul>
 * </li>
 * <li>La forme de Tapis de la {@link Partie}
 * 		<ul>
 * 			<li>{@link Terrain}</li>
 * 			<li>{@link RectangularField}</li>
 * 			<li>{@link CircularField}</li>
 * 			<li>{@link TriangularField}</li>
 * 		</ul>
 * </li>
 * </ul>
 * <br>Cette classe impl�mente le Pattern Singleton, un processus ne peut donc instancier qu'un seul objet de ce type � la fois
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01 - Automne 2020 - LO02
 * @see Partie
 *
 */
public class Configuration extends Observable{
	/**
	 * nombre de joueur de la {@link Partie}
	 * @see Player
	 * @see RealPlayer
	 * @see VirtualPlayer
	 */
	private int playernumber;
	/**
	 * nombre de joueurs r�els de la {@link Partie}
	 * @see RealPlayer
	 */
	private int realPlayer;
	/**
	 * Strategy des joueurs Virtuels 1 => Facile, 2 => Interm�diaire, 3 => Difficile
	 */
	private int virtualPlayerDifficulty;
	/**
	 * mode de jeu de la {@link Partie} courante
	 * 1 => mode Normal, 2 => mode Avanc�
	 */
	private int gameRule;
	/**
	 * forme du tapie de la {@link Partie} courante
	 * 1 => Rectangulaire, 2 => Circulaire, 3 => Triangulaire
	 */
	private int shapeField;
	/**
	 * le mode de jeu de la {@link Partie} 
	 * @see GameRule
	 */
	private GameRule gamerule;
	/**
	 * la strategie des joueurs virtuels de la {@link Partie}
	 * @see Strategy
	 */
	private Strategy strategy;
	/**
	 * L'instance de la {@link Configuration} de la {@link Partie}
	 * <br> Ce programme n'admet qu'une seule {@link Configuration} de la {@link Partie}
	 * <br> La classe {@link Configuration} impl�mente le pattern Singleton
	 */
	private static Configuration configuration = null;
	/**
	 * L'instance de la {@link Partie} courante
	 */
	private Partie partie;
	/**
	 * La liste des noms des {@link RealPlayer} de la {@link Partie}
	 */
	ArrayList<String> listeNom;
	/**
	 * bool�en permettant si on peut modifier la strategy des joueurs virtuels en cours de Partie
	 * @see ConfigPanel
	 */
	private boolean configInPartie = false;
	/**
	 * bool�en permettant de savoir si la partie a bien �t� configur�
	 */
	private static boolean Configured = false;


	/**
	 * Constructeur : il initialise le {@link Configuration} avec les param�tres par d�faut
	 */
	private Configuration()
	{
		this.listeNom = new ArrayList<String>();
		Configured = false;
	}
	/**
	 * d�r�f�rence l'instance de {@link Configuration}
	 * d�truit en quelque sorte la {@link Configuration}
	 */
	public void destroyConfiguration()
	{
		Configuration.configuration = null;
	}
	/**
	 * Recr�e une nouvelle {@link Configuration} diff�rente de l'ancienne
	 * en la d�truisant et la cr�ant de nouveau
	 */
	public void initialiseConfiguration()
	{
		this.destroyConfiguration();
		Configuration.configuration = new Configuration();
	}
	/**
	 * renvoie une instance de {@link Configuration} si il y'en a ou cr�e et renvoie une nouvelle instance de {@link Configuration} si aucune {@link Configuration} n'est r�f�renc�
	 * @return une r�f�rence sur un Objet de type {@link Configuration}
	 */
	public static Configuration getConfiguration()
	{
		if(Configuration.configuration == null)
			Configuration.configuration = new Configuration();
		return Configuration.configuration;
	}
	
	/**
	 * permet de savoir si la {@link Partie} est configur�
	 * elle est configur�e si {@link Configuration#Configured} vaut true
	 * @return {@link Configuration#Configured}
	 */
	public boolean isConfigure() {
		return Configured;
	}
	/**
	 * elle permet de savoir si la {@link Partie} peut �tre lanc�,
	 * elle peut �tre lanc� si les �l�ments de configuration sont valides selon
	 * cette m�thode a �t� t�l�port� dans le {@link GraphicController}
	 * elle retourne true si les valeurs sont ad�quates
	 * @see GraphicController
	 * @return {@link Boolean#TRUE}
	 */
	public boolean canStart() {
		if(playernumber >= 2 && playernumber <=3) {
			if(virtualPlayerDifficulty >= 1 && virtualPlayerDifficulty <=3)
			{
				if(gameRule >= 1 && gameRule <=2)
				{
					if(shapeField >= 1 && shapeField <= 2) {
						if(realPlayer<= playernumber && realPlayer >= 0) {
							return true;
						}
						return false;
					}
					return false;
				}
				return false;
			}
			return false;
		}
		return false;
	}
	/**
	 * elle permet de d�finir la {@link Configuration#strategy} des {@link VirtualPlayer}
	 * en fonction de la valeur de {@link Configuration#virtualPlayerDifficulty}
	 * 1 => {@link EasyVirtualPlayer}
	 * 2 => {@link IntermediateVirtualPlayer}
	 * 3 => {@link DifficultVirtualPlayer}
	 */
	public void whichVirtualStrategy()
	{
		if(this.getVirtualPlayerDifficulty() == 1)
			strategy = new EasyVirtualPlayer();
		else if (this.getVirtualPlayerDifficulty() == 3)
			strategy = new DifficultVirtualPlayer();
		else
			strategy = new IntermediateVirtualPlayer();
	}
	/**
	 * getters de {@link Configuration#gamerule}
	 * pour conna�tre la {@link GameRule} de la {@link Partie}
	 * @return {@link Configuration#gamerule}
	 */
	public GameRule getGamerule() {
		return gamerule;
	}
	/**
	 * setters permettant de changer la valeur de {@link Configuration#gamerule}
	 * @param gamerule le {@link GameRule} de la {@link Partie}
	 */
	public void setGamerule(GameRule gamerule) {
		this.gamerule = gamerule;
		whichGameRule();
	}
	/**
	 * getters de la {@link Configuration#strategy}
	 * pour conna�tre la {@link Strategy} des {@link VirtualPlayer}
	 * @return {@link Configuration#strategy}
	 */
	public Strategy getStrategy() {
		return strategy;
	}
	/**
	 * setters permettant de changer la valeur de {@link Configuration#strategy}
	 * @param strategy la {@link Strategy} des {@link VirtualPlayer}
	 */
	public void setStrategy(Strategy strategy) {
		this.strategy = strategy;
		whichVirtualStrategy();
	}
	/**
	 * elle permet de remplir la {@link Configuration#listeNom} des {@link RealPlayer} de la {@link Partie}
	 * @param listePlayer qui est une liste des noms des {@link RealPlayer} de la {@link Partie}
	 */
	public void DefinePlayerName(ArrayList<String> listePlayer) {
		this.listeNom = listePlayer;
		
	}
	/**
	 * getters de la {@link Configuration#listeNom}
	 * elle permet d'obtenir la {@link Configuration#listeNom} des {@link RealPlayer}
	 * @return la liste des noms des {@link RealPlayer}
	 */
	public ArrayList<String> getListeNom() {
		return listeNom;
	}
	/**
	 * elle permet de d�finir la {@link GameRule} de la {@link Partie}
	 * en fonction de la {@link Configuration#gameRule}
	 * 1 => {@link NormalGameRule}
	 * 2 => {@link AdvancedGameRule}
	 */
	public void whichGameRule()
	{
		if(this.getGameRule() == 1)
			this.gamerule = new NormalGameRule();
		else
			this.gamerule = new AdvancedGameRule();
	}
	/**
	 * getters de la {@link Configuration#playernumber}
	 * elle permet d'obtenir le nombre de {@link Player} de la {@link Partie}
	 * @return {@link Configuration#playernumber}
	 */
	public int getPlayerNumber()
	{
		return this.playernumber;
	}
	/**
	 * getters de la {@link Configuration#realPlayer}
	 * elle permet d'obtenir le nombre de {@link RealPlayer} de la {@link Partie}
	 * @return {@link Configuration#realPlayer}
	 */
	public int getRealPlayer()
	{
		return this.realPlayer;
	}
	/**
	 * un getter
	 * @return un entier
	 */
	public int getVirtualPlayerDifficulty()
	{
		return this.virtualPlayerDifficulty;
	}
	/**
	 * un getter
	 * @return un entier
	 */
	public int getGameRule()
	{
		return this.gameRule;
	}
	/**
	 * un getter
	 * @return un entier
	 */
	public int getShapeField()
	{
		return this.shapeField;
	}
	/**
	 * setters permettant de changer la valeur {@link Configuration#playernumber}
	 * @param nbPlayer : le nombre de {@link Player} de la partie
	 */
	public void setPlayerNumber(int nbPlayer)
	{
		this.playernumber = nbPlayer;
		this.setChanged();
		this.notifyObservers(Update.No);
	}
	/**
	 * setters permettant de changer la valeur {@link Configuration#realPlayer}
	 * @param rPlayer : le nombre de {@link RealPlayer} de la partie
	 */
	public void setRealPlayer(int rPlayer)
	{
		this.realPlayer = rPlayer;
		this.setChanged();
		this.notifyObservers(Update.No);
	}
	/**
	 * setters permettant de changer la valeur {@link Configuration#virtualPlayerDifficulty}
	 * @param difficulty : difficult� des {@link VirtualPlayer} : 1 ou 2 ou 3
	 */
	public void setVirtualPlayerDifficulty(int difficulty)
	{
		this.virtualPlayerDifficulty = difficulty;
		this.setChanged();
		this.notifyObservers(Update.No);
		whichVirtualStrategy();
	}
	/**
	 * setters permettant de changer la valeur {@link Configuration#gameRule}
	 * @param gRule : le numero de {@link GameRule} : 1 => {@link NormalGameRule} et 2 => {@link AdvancedGameRule}
	 */
	public void setGameRule(int gRule)
	{
		this.gameRule = gRule;
		this.setChanged();
		this.notifyObservers(Update.No);
		if(this.getGameRule() == 1)
			this.gamerule = new NormalGameRule();
		else
			this.gamerule = new AdvancedGameRule();
	}
	/**
	 * setters permettant de changer la valeur {@link Configuration#shapeField}
	 * @param shape : la forme de terrain : 
	 * 1 => {@link RectangularField}
	 * 2 => {@link CircularField}
	 * 3 => {@link TriangularField}
	 */
	public void setShapeField(int shape)
	{
		this.shapeField = shape;
		this.setChanged();
		this.notifyObservers(Update.No);
	}
	/**
	 * getter de la {@link Configuration#partie}
	 * elle renvoie la {@link Partie} en cours
	 * @return {@link Configuration#partie}
	 */
	public Partie getPartie() {
		return partie;
	}
	/**
	 * 
	 * @param partie la {@link Partie}
	 */
	public void setPartie(Partie partie) {
		this.partie = partie;
	}

	/**
	 * elle permet de signaler que la {@link Configuration} a �t� configur�
	 * elle modifie la variable {@link Configuration#Configured}
	 * @param isConfigure vaut toujours true puisqu'on ne peut juste dire que la partie vient d'�tre configur�e
	 * et qu'elle ne peut �tre d�configur�e
	 */
	public void setConfigure(boolean isConfigure) {
		Configured = isConfigure;
		
	}
	
	/**
	 * elle permet de mettre � jour la {@link Strategy} des {@link VirtualPlayer}
	 * qui peut �tre modifi� en cours de {@link Partie} en mode Graphique
	 * @param i donne la nouvelle valeur de la {@link Strategy}
	 * 1 => {@link EasyVirtualPlayer}
	 * 2 => {@link IntermediateVirtualPlayer}
	 * 3 => {@link AdvancedGameRule}
	 */
	public void SetConfigInPartie(int i) {
		if(configInPartie == true) {
			this.virtualPlayerDifficulty = i;
			this.whichVirtualStrategy();
			for(Player p : Partie.startPartie().getPlayers()) {
				if(p instanceof VirtualPlayer) {
					((VirtualPlayer)p).setStrategy(strategy);
				}
			}
		}		
	}
	/**
	 * renvoie {@link Boolean#TRUE} si la {@link Partie} avait d�j� �t� configur�
	 * car {@link Configuration#configInPartie} vaut {@link Boolean#TRUE} si la parti� a d�j� commenc�
	 * et {@link Boolean#FALSE} si la {@link Partie} n'a pas encore commenc�
	 * @return {@link Configuration#configInPartie}
	 */
	public boolean isConfigInPartie() {
		return configInPartie;
	}
	/**
	 * met {@link Configuration} � true car la {@link Partie} vient tout juste d'�tre configur�
	 * @param configInPartie a {@link Boolean}
	 */
	public void setConfigInPartie(boolean configInPartie) {
		this.configInPartie = configInPartie;
	}
	/**
	 * remet les {@link Boolean} de la {@link Configuration} � leur valeur initiale
	 */
	public void putFalse() {
		Configured = false;
		configInPartie = false;
	}
}
