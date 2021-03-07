package fr.lo02.modele;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Observable;
import java.util.Scanner;

import Load.Launcher;
import fr.lo02.InterfaceGraphique.VueGraphique;


/**
 * Cette classe est le Coeur du jeu.<br>
 * elle d�crit la partie dans sa globalit� <br>
 * une partie est caract�ris�e par : <br>
 * <ul>
 * <li>D'une {@link Configuration}<br></li>
 * <li>Une carte face cach�e ( {@link Card} )<br></li>
 * <li>Une liste de {@link Player} participant � la {@link Partie}<br></li>
 * <li>Une pioche ( {@link Deck} )<br></li>
 * <li>D'un terrain ( {@link Terrain} )<br></li>
 * <li>La {@link Strategy} des {@link VirtualPlayer}<br></li>
 * <li>Les r�gles de la {@link Partie} ( {@link GameRule} )<br></li>
 * </ul>
 * 
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01 - Automne 2019 - LO02
 * @see Configuration
 * @see Card
 * @see Player
 * @see RealPlayer
 * @see VirtualPlayer
 * @see Deck
 * @see Strategy
 * @see GameRule
 * @see Terrain
 * @see RectangularField
 * @see CircularField
 * @see TriangularField
 * 
 */
 
public class Partie extends Observable {
	/**
	 * L'instance de la {@link Partie} qui est en train de se d�rouler
	 * <br> Ce programme n'admet qu'une seule partie puisse �tre jou�e � la fois.
	 * <br> la classe {@link Partie} impl�mente le pattern singleton
	 */
	private static Partie partie = null;
	/**
	 * La pioche de la {@link Partie}
	 */
	private Deck deck;
	/**
	 * Le terrain de la {@link Partie}
	 */
    protected Terrain field;
    /**
     * La configuration de la {@link Partie}
     */
	private Configuration configuration;
	/**
	 * La carte cach�e de la {@link Partie}
	 */
	private Card hiddenCard;
	/**
	 * Le {@link CalculScore} de la {@link Partie}
	 */
	private CalculScore calculScore = new CalculScore();
	/**
	 * Le message � afficher : Non impl�ment�
	 */
	private String broadCast = "";
	/**
	 * La liste des {@link Player} de la {@link Partie}
	 */
	private ArrayList<Player> players;
	/**
	 * Un iterateur permettant de parcourir la collection des {@link Player}}
	 */
	public Iterator<Player> itPlayer;
	/**
	 * L'�l�ment d�signant le {@link Player} en train de jouer 
	 */
	public Player play;
	/**
	 * 
	 */
	private   HashMap<ArrayList<Integer>, Player> scoreBoard = new HashMap<ArrayList<Integer>, Player>(4);
	/**
	 * Le nombre de Jeu pour cette {@link Partie} : 3 par d�faut
	 */
	private static int totalPartie = 0;
	/**
	 * drapeau permettant de savoir si la partie a d�j� �t� configur�
	 */
	private boolean PartieConfigured = false;
	/**
	 * drapeau permettant de savoir si la partie est termin�e : cet �l�ment est important pour 
	 * la GamePlay graphique puisqu'il permet de savoir s'il doit encore attendre des actions
	 */
	private boolean partyEnded = false;
	/**
	 * drapeau permettant de savoir si la partie a d�but�
	 */
	private boolean partyStarted = false;
	/**
	 * drapeau permettant de savoir si la derni�re sequence de la {@link Partie} est termin�e
	 */
	private boolean lastSequence = false;
	/**
	 * drapeau permettant de savoir si la sequence de la {@link Partie} est en train de se jouer
	 */	
	private volatile Boolean sequenceStarted = new Boolean(false);
	/**
	 * drapeau permettant de savoir depuis quelle vue la Partie a commenc�e
	 * false == Console :: true == GUI
	 */
	private static boolean viewWherePartyStarted = false;
	/**
	 * drapeau permettant de savoir depuis quelle vue la Partie s'est termin�e
	 * false == Console :: true == GUI
	 */
	private static boolean viewWherePartyEnded = false;
	/**
	 * drapeau permettant de savoir depuis quelle vue la Partie a �t� configur�e
	 * false == Console :: true == GUI
	 */
	private static boolean viewWherePartyConfigured = false;
	/**
	 * drapeau permettant de savoir si le {@link Partie#play} peut encore d�placer
	 */
	private boolean canSet = false;
	/**
	 * drapeau permettant de savoir si le {@link Partie#play} peut encore ajouter une carte
	 */
	private boolean canAdd = false;
	
	
	/**
	 * Constructeur : il initialise la { @link Partie} avec les param�tres par d�faut
	 */
	private Partie()
	{
		this.configuration = Configuration.getConfiguration();
		this.configuration.putFalse();
		this.scoreBoard.putAll(Launcher.scoreBoard);
		putFalse();
		this.setChanged();
		this.notifyObservers(Update.No);
	}
	/**
	 * cette m�thode permet d'initialiser le {@link Terrain}
	 */
	public void iniTerrain() {
		if(this.configuration.getShapeField() == 1)
			this.field = RectangularField.getInstanceint();
		else if(this.configuration.getShapeField() == 2)
			this.field = CircularField.getInstanceint();
		else
			this.field = TriangularField.getInstanceint();
	}
	/**
	 * cette m�thode permet de recr�er le {@link Deck} de la partie
	 */
	public void iniDeck(){
		deck = Deck.getInstance();
		deck.deckDestroy();
		deck = Deck.getInstance();
	}
	/**
	 * cette m�thode permet d'initialiser la liste des jpueurs {@link Partie#players}
	 */
	public void startPlayers() {
		this.players = new ArrayList<Player>();
	}
	/**
	 * cette m�thode permet de cr�er les {@link Player} de la {@link Partie}
	 * @see RealPlayer
	 * @see VirtualPlayer
	 */
	public void iniPlayer() {
		this.players = new ArrayList<Player>();
		RealPlayer rt;
		for(int i = 1; i<=configuration.getRealPlayer(); i++)
		{
			rt = new RealPlayer(this.configuration.getListeNom().get(i-1));
			rt.setGameRule(this.configuration.getGamerule());
			players.add(rt);
		}
	
		VirtualPlayer vt;
		VirtualPlayer.setIdBot(1);
		for(int i = this.configuration.getRealPlayer()+1; i<=this.configuration.getPlayerNumber(); i++)
		{
			vt = new VirtualPlayer("");
			vt.setStrategy(this.configuration.getStrategy());
			vt.setGameRule(this.configuration.getGamerule());
			players.add(vt);
		}
		itPlayer = this.players.iterator();
		this.setChanged();
		this.notifyObservers(Update.PLAYER);
	}
	/**
	 * cette m�thode permet de passer au {@link Player} suivant
	 * parmi ceux qui jouent
	 * @return un {@link Player}
	 */
	public Player NextPlayer() {
		if(itPlayer == null)
			itPlayer = this.players.iterator();
		if(this.itPlayer.hasNext()) {
			play = itPlayer.next();
			if(play instanceof RealPlayer)
				VueGraphique.getIT().getGame().arrange((RealPlayer)play);
			return play;
		}
		else {
			itPlayer = this.players.iterator();
			play = itPlayer.next();
			if(play instanceof RealPlayer)
				VueGraphique.getIT().getGame().arrange((RealPlayer)play);
			//play = itPlayer.next();
			return play;
		}
	}
	/**
	 * cette m�thode permet de passer au {@link Player} suivant
	 * parmi ceux qui jouent 	
	 */
	public void setNextPlayer() {
		if(itPlayer == null)
			itPlayer = this.players.iterator();
		if(!this.itPlayer.hasNext())
			itPlayer = this.players.iterator();
		play =  itPlayer.next();
		play.setHasPlayed(false);
		play.setHasTerminated(false);
		if(play instanceof RealPlayer) {
			VueGraphique.getIT().getGame().arrange((RealPlayer)play);
		}
	}

	/**
	 * non impl�ment�
	 */
	public void initializePartie()
	{
		this.players = new ArrayList<Player>();
		this.iniDeck();
		
		this.iniTerrain();
		
		this.iniPlayer();
		
	}
	/**
	 * renvoie une instance de {@link Partie} s'il y'en a une ou cr�e une nouvelle instance
	 * de {@link Partie} si aucune partie n'est r�f�renc�
	 * @return une r�f�rence sur un objet de type {@link Partie}
	 */
	public static Partie startPartie()
	{
		if(Partie.partie == null) {
			partie = new Partie();
		}
		return Partie.partie;
	}
	/**
	 * d�truire la {@link Partie} :
	 * {@link Deck#deckDestroy()}
	 * {@link Terrain#fieldDestroy()}
	 * {@link Configuration#destroyConfiguration()}
	 */
	public void endPartie()
	{
		Partie.partie = null;
		hiddenCard = null;
		this.deck.deckDestroy();
		this.field.fieldDestroy();
		this.configuration.destroyConfiguration();
	}
	/**
	 * initialise le {@link Deck}, la {@link Configuration} et le {@link Terrain} de la partie
	 * {@link Deck#deckInitialize()}
	 * {@link Configuration#initialiseConfiguration()}
	 * {@link Terrain#fieldInitialise()}
	 * {@link Partie#initializePartie()}
	 */
	public void Initialise()
	{
		this.hiddenCard = null;
		this.deck.deckInitialize();
		this.broadCast = "Le deck est initialis�";
		this.setChanged();
		this.notifyObservers(Update.No);
		this.configuration.initialiseConfiguration();
		this.field.fieldInitialise();
		this.initializePartie();
	}
	/**
	 * m�thode permettant de distribuer les cartes
	 * {@link GameRule#distributeCard(ArrayList, Deck)}
	 */
	public void distributeCard()
	{
		this.broadCast = "Prenez vos cartes";
		this.setChanged();
		this.notifyObservers(Update.No);
		this.setHiddenCard(this.deck.getNextCard());
		this.configuration.getGamerule().distributeCard(this.players, this.deck);
	}
	/**
	 * D�termine le {@link Player#score} de chaque {@link Player}
	 * de la {@link Partie#players}
	 */
	public void SetScorePlayer()
	{
		for(Player p : this.players)
		{
			if(this.configuration.getGameRule() == 2)
				p.getVictoryCardAdvanced();
			p.setScore(0);
			//Calcul des score des joueurs virtuels
			p.setScore(this.calculScore.calculScorePlayer(field, p.getVictoryCard()));
		}
	}
	
	private boolean afficheScore = true;
	/**
	 * Affiche le {@link Player#score} de chaque {@link Player}
	 */
	public void printScore()
	{
		if(afficheScore) {
			this.SetScorePlayer();
			Player pVictory = null;
			int i = 0;
			for(Player p : this.players)
			{
				if(i == 0)
				{
					pVictory = p;
				}
				if(p.getScore() > pVictory.getScore())
					pVictory = p;
				else if(p.getScore() == pVictory.getScore() && p.getPseudo().compareToIgnoreCase(pVictory.getPseudo()) < 0)
					pVictory = p;
				i++;
			}
			this.broadCast = field.toString() + "\n";
			this.setChanged();
			this.notifyObservers(Update.No);
			ArrayList<Integer> colonne1 = new ArrayList<Integer>();
			colonne1.add(Launcher.numberPartie);
			colonne1.add(pVictory.getScore());
			this.scoreBoard.put(colonne1, pVictory);
			this.broadCast += "F�licitations " + pVictory.getPseudo() + " Vous avez gagner avec " + pVictory.getScore() + " points!!!\n";
			for(Player p : this.players)
			{
				this.broadCast += "\t" + p.getPseudo() + " votre score est de " + p.getScore();
			}
			this.setChanged();
			this.notifyObservers(Update.No);
			this.broadCast += "La listes des gagnants sont\n";
			this.setChanged();
			this.notifyObservers(Update.No);
			this.broadCast += "";
			for(ArrayList<Integer> key : this.scoreBoard.keySet())
			{
				this.broadCast += "A la partie "+ key.get(0)+" Le joueur " + this.scoreBoard.get(key).getPseudo() + " a eu comme score " + key.get(1) + "\n";
			}
			Launcher.scoreBoard.putAll(this.scoreBoard);
		}
		this.setChanged();
		this.notifyObservers(Update.No);
		afficheScore = false;
	}
	/**
	 * non impl�ment�
	 * @return non impk�ment�
	 */
	public boolean restart()
	{
		if(this.getTotalPartie() == 3)
			return false;
		boolean playAgain = false;
		this.setTotalPartie();
		this.broadCast = "Vous avez Jouer " + partie.getTotalPartie() +" partie(s) Voulez vous continuer?? Repondez par '0' -> 'Non' || '1' -> 'Oui'";
		this.setChanged();
		this.notifyObservers(Update.INT);
		return playAgain;
	}
	/**
	 * non impl�ment�
	 * permettait de n�ttoyer la console
	 */
	public static void clrscr()
	{
		try {
			if(System.getProperty("os.name").contains("Windows"))
				new ProcessBuilder("cmd", "/c", "ds").inheritIO().start().waitFor();
			else
				Runtime.getRuntime().exec("clear");
		}catch(IOException | InterruptedException ex) {}
	}
	/**
	 * getter du {@link CalculScore}
	 * @return un {@link CalculScore}
	 */
	public CalculScore getCalculScore() {
		return calculScore;
	}
	/**
	 * getter du {@link Deck}
	 * @return un {@link Deck}
	 */
	public Deck getDeck() {
		return deck;
	}
	/**
	 * getter du {@link Terrain}
	 * @return un {@link Terrain}
	 */
	public Terrain getField() {
		return field;
	}
	/**
	 * getter de la {@link Configuration}
	 * @return la {@link Configuration} de la {@link Partie}
	 */
	public Configuration getConfiguration() {
		return configuration;
	}
	/**
	 * retourne la liste des {@link Player} de la {@link Partie}
	 * @return une liste de {@link Player}
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	/**
	 * retourne l'historique des partie est de vainqueurs
	 * @return la liste des vainquers et leur scores
	 */
	public HashMap<ArrayList<Integer>, Player> getScoreBoard() {
		return scoreBoard;
	}
	/**
	 * le message � afficher
	 * non impl�ment�
	 * @return un message
	 */
	public String getBroadCast() {
		return broadCast;
	}
	/**
	 * dit si la partie est configur�
	 * @return un {@link Boolean}
	 */
	public boolean isPartieConfigured() {
		return PartieConfigured;
	}
	/**
	 * met � jour la configuration de la {@link Partie}
	 * @param partieConfigured un {@link Boolean}
	 */
	public void setPartieConfigured(boolean partieConfigured) {
		PartieConfigured = partieConfigured;
	}
	/**
	 * dit si la {@link Partie} est finie
	 * @return un {@link Boolean}
	 */
	public boolean isPartyEnded() {
		return partyEnded;
	}
	/**
	 * met � jour le fait que la {@link Partie} est finie
	 * @param partyEnded vaut toujours true
	 */
	public void setPartyEnded(boolean partyEnded) {
		this.partyEnded = partyEnded;
	}
	/**
	 * ou la partie a �t� configur�
	 * @return un {@link Boolean}
	 */
	public static boolean isViewWherePartyStarted() {
		return viewWherePartyStarted;
	}
	/**
	 * informe de quelle vue la {@link Partie} a �t� confugur�
	 * @param viewWherePartyStarted un {@link Boolean}
	 */
	public static void setViewWherePartyStarted(boolean viewWherePartyStarted) {
		Partie.viewWherePartyStarted = viewWherePartyStarted;
	}
	/**
	 * dit o� la {@link Partie} a �t� termin�
	 * @return un {@link Boolean}
	 */
	public static boolean isViewWherePartyEnded() {
		return viewWherePartyEnded;
	}
	/**
	 * informe de la vue o� la {@link Partie} a fini
	 * @param viewWherePartyEnded un {@link Boolean}
	 */
	public static void setViewWherePartyEnded(boolean viewWherePartyEnded) {
		Partie.viewWherePartyEnded = viewWherePartyEnded;
	}
	/**
	 * dit o� la {@link Partie} a �t� configur�e
	 * @return un {@link Boolean}
	 */
	public static boolean isViewWherePartyConfigured() {
		return viewWherePartyConfigured;
	}
	/**
	 * informe d'o� on a configur� la {@link Partie}
	 * @param viewWherePartyConfigured un {@link Boolean}
	 */
	public static void setViewWherePartyConfigured(boolean viewWherePartyConfigured) {
		Partie.viewWherePartyConfigured = viewWherePartyConfigured;
	}
	/**
	 * dit ou la {@link Partie} a commenc�
	 * @return un {@link Boolean}
	 */
	public boolean isPartyStarted() {
		return partyStarted;
	}
	/**
	 * retourne l'instance de {@link Partie}
	 * @return l'instace de {@link Partie}
	 */
	public static Partie getInstance() {
		return Partie.partie;
	}
	/**
	 * informe ou la {@link Partie} a commenc�
	 * @param partyStarted un {@link Boolean}
	 */
	public void setPartyStarted(boolean partyStarted) {
		this.partyStarted = partyStarted;
	}
	/**
	 * informe de la vue o� s'est pass� la derni�re s�quence
	 * @param lastSequence un {@link Boolean}
	 */
	public void setLastSequence(boolean lastSequence) {
		this.lastSequence = lastSequence;
	}
	/**
	 * dit sur quelle vue �tait la derni�re s�quence
	 * @return un {@link Boolean}
	 */
	public Boolean getSequenceStarted() {
		return sequenceStarted;
	}
	/**
	 * informe la vue o� la partie a commenc�
	 * @param sequenceStarted un {@link Boolean}
	 */
	public void setSequenceStarted(Boolean sequenceStarted) {
		this.sequenceStarted = sequenceStarted;
	}
	/**
	 * met � jour le message � inform�
	 * @param broadCast un message
	 */
	public void setBroadCast(String broadCast) {
		this.broadCast = broadCast;
	}
	/**
	 * ajoute un Joueur
	 * @param p un {@link Player}
	 */
	public void addPlayer(Player p) {
		this.players.add(p);
	}
	/**
	 * non impl�ment�
	 */
	public void setTotalPartie()
	{
		Partie.totalPartie++;
	}
	/**
	 * non impl�ment�
	 * @return un entier
	 */
	public int getTotalPartie()
	{
		return Partie.totalPartie;
	}
	/**
	 * retourne la carte cach�e
	 * @return une {@link Card}
	 */
	public Card getHiddenCard()
	{
		return this.hiddenCard;
	}
	/**
	 * cache un {@link Card} sur le {@link Terrain} jusqu'� la fin de la {@link Partie}
	 * @param card la {@link Card}
	 */
	public void setHiddenCard(Card card)
	{
		this.hiddenCard = card;
	}
	/**
	 * initialise l'it�rateur des {@link Player}
	 */
	public void iniIterator() {
		this.itPlayer = this.players.iterator();
	}
/**
 * retourne l'it�rateur de {@link Partie} des {@link Player}
 * @return un it�rateur
 */
	public Iterator<Player> getItPlayer() {
		return itPlayer;
	}
	/**
	 * le {@link Player} qui joue son tour
	 * @return un {@link Player}
	 */
	public Player getPlay() {
		return play;
	}
	/**
	 * non impl�ment�
	 */
	public void notif() {
		this.setChanged();
		this.notifyObservers(Update.No);
	}	
	/**
	 * Permet de r�initialise les valeurs par d�faut de la {@link Partie}
	 */
	public void putFalse() {
		canSet = false;
		canAdd = false;
		PartieConfigured = false;
		partyEnded = false;
		partyStarted = false;
		lastSequence = false;
		sequenceStarted = new Boolean(false);
		viewWherePartyStarted = false;
		viewWherePartyEnded = false;
		viewWherePartyConfigured = false;
		afficheScore = true;
	}
}
