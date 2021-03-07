package fr.lo02.controler;

import java.util.ArrayList;

import fr.lo02.InterfaceGraphique.BoutonHandCard;
import fr.lo02.InterfaceGraphique.GamePanel;
import fr.lo02.InterfaceGraphique.Vue;
import fr.lo02.InterfaceGraphique.VueGraphique;
import fr.lo02.modele.AdvancedGameRule;
import fr.lo02.modele.Card;
import fr.lo02.modele.CircularField;
import fr.lo02.modele.Color;
import fr.lo02.modele.Deck;
import fr.lo02.modele.EasyVirtualPlayer;
import fr.lo02.modele.Filled;
import fr.lo02.modele.GameRule;
import fr.lo02.modele.IntermediateVirtualPlayer;
import fr.lo02.modele.NormalGameRule;
import fr.lo02.modele.Partie;
import fr.lo02.modele.Player;
import fr.lo02.modele.RealPlayer;
import fr.lo02.modele.RectangularField;
import fr.lo02.modele.Shape;
import fr.lo02.modele.Strategy;
import fr.lo02.modele.Terrain;
import fr.lo02.modele.Update;
import fr.lo02.modele.VirtualPlayer;
/**
 * Cette classe est le {@link Controller} pour le mode Graphique du Jeu.<br>
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseaux et Télécommunication Semestre 01 - Automne 2020 - LO02
 * 
 * @see Controller
 * @see PartieConsoleEngine
 * @see Partie
 * @see VueGraphique
 * @see Vue
 *
 */
public class GraphicController implements Controller, Runnable{

	/**
	 * Une référence sur le {@link Controller} en mode Console de la {@link Partie}
	 */
	public PartieConsoleEngine myConsoleController;
	/**
	 * La {@link Partie} gérée sue lr {@link Controller}
	 */
	private Partie myPartie = null;
	/**
	 * La fenêtre du jeu : elle correspond à la Vue Graphique du jeu
	 */
	private VueGraphique myView = null;
	/**
	 * Nombre de {@link RealPlayer}
	 */
	private static int numberOfRealPlayer;
	/**
	 * Nombre de {@link Player} au total
	 */
	private static int numberOfPlayer;
	/**
	 * La liste des Noms utilisés pour les {@link RealPlayer}
	 */
	private ArrayList<String> NameList = new ArrayList<>(3);
	/**
	 * la {@link Strategy} des {@link VirtualPlayer}
	 */
	private Strategy botStrategy;
	/**
	 * le {@link GameRule} de la {@link Partie}
	 */
	private GameRule gameRule;

	private static int formeField = -1;
	
	private static int gamerule = -1;
	
	private static int strategyV = -1;
	/**
	 * Le {@link Terrain} de la {@link Partie}
	 */
	private Terrain field;
	
	private int playAlreadyDone = 0;
	/**
	 * Le numéro de la dernière Sequence de jeu : Non implémenté
	 */
	private int LAST_SEQUENCE = 4;
	/**
	 * le numéro de la séquence actuelle : non implémenté
	 */
	private int sequenceNumber = 1;
	/**
	 * NON IMPLEMENTE
	 */
	private Runnable v_makeOffer;
	/**
	 * NON IMPLEMENTE
	 */
	Runnable v_playerTakeCardHandler;
	/**
	 * NON IMPLEMENTE
	 */
	private Runnable setUpNextSequence;
	/**
	 * NON IMPLEMENTE
	 */
	private Runnable publishResults;
	/**
	 * Ce {@link Boolean} permet de savoir si la séquence s'est terminé pendant le tour d'un {@link Player}
	 */
	private boolean endOfSequenceInV_PlayerThread = false;
	
	private int takeCardTargetIndexVar;
	/**
	 * Ce {@link Boolean} permet de savoir si un {@link RealPlayer} est en cours de déplacement d'une {@link Card}
	 */
	private boolean setting = false;
	/**
	 * ce {@link Boolean} permet de savoir si un {@link RealPlayer} a poser une {@link Card} sur le {@link Terrain}
	 */
	private boolean hasplayed = false;
	/**
	 * ce {@link Boolean} permet de savoir si un {@link RealPlayer} a déplacer une {@link Card}
	 */
	private boolean hasSet = false;
	
	private boolean canPlayed = false;
	
	private boolean canSet = false;
	
	private boolean wantToPlaySet = false;
	
	private int posi = -1;
	
	private int posj = -1;
	
	private String Card = "";
	/**
	 * Ce {@link Boolean} permet de savoir si un {@link RealPlayer} peut ajouter une {@link Card}
	 * il peut si il est en cours d'ajout ou de déplacement de {@link Card}
	 */
	private boolean wantToAdd = false;
	/**
	 * Ce {@link Boolean} permet de savoir si un {@link RealPlayer} a jouer une {@link Card}
	 */
	private boolean hasPlayed = false;
	/**
	 * Le {@link Player} en train de joueur son tour
	 */
	private Player play;
	/**
	 * {@link Boolean} permettant de savoir si un {@link RealPlayer} a terminé son tour et qu'on peut passer
	 * au {@link Player} suivant
	 */
	boolean hasFinished = false;
	
	boolean takeHand = false;
	/**
	 * Connaître si c'est c'est le {@link Thread} de ce {@link Controller} qui a la main
	 */
	boolean myTurn = false;
	
	boolean hasTerminated = false;
	/**
	 * Boolean permettant de savoir si la {@link Partie} est configurée
	 */
	private static boolean configure = false;
	/**
	 * Booelan permettant de savoir si un {@link Player} est en cours de jeu
	 */
	private boolean playing = false;
	/**
	 * Le Construteur du controleur graphique
	 * @param p la {@link Partie} qui sera gérée par le {@link Controller}
	 */
	public GraphicController(Partie p) {
		// TODO Auto-generated constructor stub
		this.myPartie = p;
		numberOfPlayer = -1;
		numberOfRealPlayer = -1;
		formeField = -1;
		gamerule = -1;
		strategyV = -1;
		putFalse();
		v_playerTakeCardHandler = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
			}
		};
		
	}
	/**
     * cette methode constitue le reel moteur du Jeu : elle 
     * s'occupe de :
     * <ul>
     * <li>Configuer la {@link Partie} si elle n'a pas encore été configurée</li>
     * <li>Instancier les {@link Player} et les attributs de la {@link Partie} </li>
     * <li>Lancer les séquences</li>
     * <li>publier les resultats</li>
     * <li>Recommencer la Party eventuellement à la fin</li>
     * </ul>
     */
	public void beginParty() {
		// TODO Auto-generated method stub
		if(myPartie.isPartyStarted() == false && myPartie.getConfiguration().isConfigure() == false) {
			init();
			do {
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}while(takeHand == false && myPartie.getConfiguration().isConfigure() == false);
			takeHand = true;
		}
		if(myPartie.isPartyStarted() == false && myPartie.isViewWherePartyConfigured() == true) {
			Partie.setViewWherePartyStarted(true);
			this.setParty();
			myPartie.distributeCard();
			myPartie.setPartyStarted(true);
		}
		
		do {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(myPartie.isPartyStarted() == false);
		
		while(!myPartie.getConfiguration().getGamerule().isEnded(myPartie)) {
			this.sequenceLoop();
		}
		myPartie.printScore();
		
		try {
			Thread.sleep(800);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		VueGraphique.getIT().getNext().doClick();
	}
	/**
	 * le tour de jeu d'un {@link RealPlayer}
	 * @param p un {@link RealPlayer} qui va jouer son tour
	 */
	public void sequencePlayer(Player p)
	{
		setHasplayed(false);
		setHasSet(false);
		int i = 0, posi, posj;
		boolean isOk = false;
		if(p instanceof VirtualPlayer) {
			if(p.isHasPlayed() == false) {
				p.setHasPlayed(false);
				myPartie.setSequenceStarted(true);
				this.myPartie.getConfiguration().getGamerule().jouerTour(p, this.myPartie.getDeck(), this.myPartie.getField());
				myPartie.setNextPlayer();
			}
		}
		else {
			if(this.myPartie.getConfiguration().getGamerule() instanceof NormalGameRule) {
				if(!this.myPartie.getDeck().deckIsEmpty()) {
					p.piocherCard(this.myPartie.getDeck().getNextCard());
					((RealPlayer)p).noti();
				}
			}
			hasPlayed = false;
			hasSet = false;
			playing = false;
			setting = false;
			play = p;
			hasFinished = false;
			myTurn = false;
			while(!hasFinished && (play.isHasPlayed()==false || myTurn == true)) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(play.isHasTerminated() == true)
					break;
			}
			if(this.myPartie.getConfiguration().getGamerule() instanceof AdvancedGameRule && myPartie.getPlay().getWhoPlayed() == 1) {
				
				if(!this.myPartie.getDeck().deckIsEmpty()) {
					p.piocherCard(this.myPartie.getDeck().getNextCard());
					((RealPlayer)p).noti();
				}
			}
			hasTerminated = false;
			if(myPartie.getPlay().getWhoPlayed() == 2) {
				do {
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}while(hasTerminated == false);
			}
			if(myPartie.getPlay().getWhoPlayed() == 1) {
				myPartie.setNextPlayer();
			}
		}
		myPartie.setSequenceStarted(false);
	}
	/**
	 * Cette Methode Permet de Configurer une {@link Partie}
	 */
	public void configParty() {
		// TODO Auto-generated method stub
		synchronized (myPartie) {
				myPartie.getConfiguration().setPlayerNumber(numberOfPlayer);
				myPartie.getConfiguration().setRealPlayer(numberOfRealPlayer);
				myPartie.getConfiguration().setShapeField(formeField);
				myPartie.getConfiguration().setGameRule(gamerule);
				myPartie.getConfiguration().setVirtualPlayerDifficulty(strategyV);
				setRealPlayerName();
				myPartie.getConfiguration().setConfigInPartie(true);
				myPartie.getConfiguration().setConfigure(true);
				Partie.setViewWherePartyConfigured(true);
		}
	}
	/**
	 * elle permet d'initialiser les handler des différents parties de la {@link VueGraphique}
	 */
	public void init() {
		this.myView.getPanelTerrain().addHandler(this);
		this.myView.getGame().addHandler(this);
		this.myView.getGame().getBoutonPlayer1().getHandCard().addHandler(this);
		this.myView.getGame().getBoutonPlayer2().getHandCard().addHandler(this);
		this.myView.getGame().getBoutonPlayer2().getHandCard().addHandler(this);
	}
	/**
	 * elle permet de recommencer les séquences jusqu'à ce que la {@link Partie} se termine
	 */
	public void sequenceLoop() {
		// TODO Auto-generated method stub
		this.play = myPartie.getPlay();
		if(this.play instanceof RealPlayer)
			this.notif((RealPlayer) play);
		this.sequencePlayer(play);
	}
	/**
	 * Cette methode permtet de créer les {@link Player} de la {@link Partie} , elle est toujour appelée après la configuration de la {@link Partie}
	 */
	public void createPlayers() {
		// TODO Auto-generated method stub		
		myPartie.startPlayers();
		RealPlayer rt;
		for(int i = 1; i<=myPartie.getConfiguration().getRealPlayer(); i++)
		{
			rt = new RealPlayer(myPartie.getConfiguration().getListeNom().get(i-1));
			rt.setGameRule(myPartie.getConfiguration().getGamerule());
			rt.addObserver(VueGraphique.getInstance(myPartie, this).getGame());
			myPartie.addPlayer(rt);
		}
	
		VirtualPlayer vt;
		VirtualPlayer.setIdBot(1);
		for(int i = myPartie.getConfiguration().getRealPlayer()+1; i<=myPartie.getConfiguration().getPlayerNumber(); i++)
		{
			vt = new VirtualPlayer("");
			vt.setStrategy(myPartie.getConfiguration().getStrategy());
			vt.setGameRule(myPartie.getConfiguration().getGamerule());
			myPartie.addPlayer(vt);
		}
		myPartie.iniIterator();
		myPartie.notif();
	}
	/**
	 * Cette methode permet d'instancier certains membres de la {@link Partie} après que cette dernière ait été configurée <br>
	 * par exemple : le {@link Deck} et le {@link Terrain}
	 */
	public void setParty() {
		// TODO Auto-generated method stub
		myPartie.iniDeck();
		myPartie.iniTerrain();
		this.myPartie.getField().addObservers(this.myView.getPanelTerrain());
		this.setRealPlayerName();
		createPlayers();
		myPartie.notifyObservers(Update.No);
		myPartie.setNextPlayer();
	}
	/**
	 * cette méthodes permet d'instancier les {@link RealPlayer#getPseudo()} des joueurs reéls<br>
	 * et par la suite, ces Pseudos leurs Seront attribués
	 */
	public void setRealPlayerName() {
		String str = new String();
		for(int i = 0; i < this.myPartie.getConfiguration().getRealPlayer(); i++)
		{
			str = myView.getPanelConfiguration().getPlayerName().get(i).getText();
			if(str.trim().isEmpty()) {
				str = "Player#"+i;
			}
			this.myPartie.getConfiguration().getListeNom().add(str);
		}
	}
	/**
	 * initialise les valeurs par défaut du COntroller graphique
	 */
	public void iniGraphicController() {
		numberOfPlayer = -1;
		numberOfRealPlayer = -1;
		gameRule = new NormalGameRule();
		NameList.add(0, "PLAYER#0");
		botStrategy = new IntermediateVirtualPlayer();
		field = RectangularField.getInstanceint();
	}
	/**
	 * définir une {@link Card} à partir de son idCard
	 * @see Card#assignID(Filled, Color, Shape)
	 * @param str l'id de la {@link Card}
	 * @return la {@link Card} correspondant à l'id donné en paramètre
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
			return null;
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
	 * Cette méthode permet de démarrer une {@link Partie} dans le {@link Thread} en appellant le méthode {@link Controller#beginParty()}
	 */
	public void run() {
		beginParty();
	}
	/**
	 * permet d'activer le Bouton du {@link RealPlayer} qui doit commencer son tour
	 * et désactive celle des autres pour ainsi éviter la triche
	 * @see GamePanel#getBoutonPlayer1()
	 * @see GamePanel#getBoutonPlayer2()
	 * @see GamePanel#getBoutonPlayer3()
	 * @param p le Player en cours pour connaître quel est son {@link BoutonHandCard}
	 */
	public void notif(RealPlayer p) {
		int i = 0;
		for(Player pl : myPartie.getPlayers()) {
			if(pl.equals(p)) {
				for(BoutonHandCard b : VueGraphique.getInstance(myPartie, this).getGame().getPlayers()) {
					b.setEnabled(false);
				}
				VueGraphique.getInstance(myPartie, this).getGame().getPlayers().get(i).setEnabled(true);
				break;
			}
			i++;
		}
	}
	/**
	 * Permet de savoir si la {@link Partie} a été bien Configuré en mode Graphique
	 * @return Un {@link Boolean}
	 */
	public boolean canStart() {
		if(numberOfPlayer >= 2 && numberOfPlayer <=3) {
			if(strategyV >= 1 && numberOfPlayer <=3)
			{
				if(gamerule >= 1 && gamerule <=2)
				{
					if(formeField >= 1 && formeField <= 2) {
						if(numberOfRealPlayer <= numberOfPlayer && numberOfRealPlayer >= 0) {
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
	 * initialise le {@link Boolean} de la classe
	 */
	public void putFalse() {
		endOfSequenceInV_PlayerThread = false;
		setting = false;
		hasplayed = false;
		hasSet = false;
		canPlayed = false;
		canSet = false;
		wantToPlaySet = false;
		wantToAdd = false;
		hasPlayed = false;
		hasFinished = false;
		takeHand = false;
		configure = false;
		myTurn = false;
		hasTerminated = false;
		playing = false;
	}
	/**
	 * getters de {@link GraphicController#takeHand}
	 * @return {@link Boolean}
	 */
	public boolean isTakeHand() {
		return takeHand;
	}
/**
 * met à jour la valeur de takeHand
 * @param takeHand un {@link Boolean}
 */
	public void setTakeHand(boolean takeHand) {
		this.takeHand = takeHand;
	}
	/**
	 * getters de {@link GraphicController#playing}
	 * @return {@link Boolean}
	 */
	public boolean isPlaying() {
		return playing;
	}
	/**
	 * met à jour playing
	 * @param playing un {@link Boolean}
	 */
	public void setPlaying(boolean playing) {
		this.playing = playing;
	}
	/**
	 * getters de {@link GraphicController#setting}
	 * @return {@link Boolean}
	 */
	public boolean isSetting() {
		return setting;
	}
	/**
	 * met a jour setting
	 * @param setting {@link Boolean}
	 */
	public void setSetting(boolean setting) {
		this.setting = setting;
	}
	/**
	 * le getter de hasPlayed
	 * @return un {@link Boolean}
	 */
	public boolean isHasplayed() {
		return hasplayed;
	}
	/**
	 * getters de {@link GraphicController#hasplayed}
	 * @param hasplayed {@link Boolean}
	 */
	public void setHasplayed(boolean hasplayed) {
		this.hasplayed = hasplayed;
	}
	/**
	 * getters de {@link GraphicController#hasSet}
	 * @return {@link Boolean}
	 */
	public boolean isHasSet() {
		return hasSet;
	}
	/**
	 * la nouvelle valeur de hasSet
	 * @param hasSet un {@link Boolean}
	 */
	public void setHasSet(boolean hasSet) {
		this.hasSet = hasSet;
	}
	/**
	 * getters de {@link GraphicController#canPlayed}
	 * @return {@link Boolean}
	 */
	public boolean isCanPlayed() {
		return canPlayed;
	}
	/**
	 * la nouvelle valeur de canPlayed
	 * @param canPlayed un {@link Boolean}
	 */
	public void setCanPlayed(boolean canPlayed) {
		this.canPlayed = canPlayed;
	}
	/**
	 * getters de {@link GraphicController#canSet}
	 * @return {@link Boolean}
	 */
	public boolean isCanSet() {
		return canSet;
	}
	/**
	 * setters de {@link GraphicController#canSet}
	 * @param canSet un {@link Boolean}
	 */
	public void setCanSet(boolean canSet) {
		this.canSet = canSet;
	}
	/**
	 * getters de {@link GraphicController#isWantToPlaySet()}
	 * @return {@link Boolean}
	 */
	public boolean isWantToPlaySet() {
		return wantToPlaySet;
	}
	/**
	 * setter de wantToPlaySet
	 * @param wantToPlaySet un {@link Boolean}
	 */
	public void setWantToPlaySet(boolean wantToPlaySet) {
		this.wantToPlaySet = wantToPlaySet;
	}
	/**
	 * getters de posi
	 * @return un entier
	 */
	public int getPosi() {
		return posi;
	}
	/**
	 * setter de posi
	 * @param posi un entier
	 */
	public void setPosi(int posi) {
		this.posi = posi;
	}
	/**
	 * getter de posi
	 * @return un entier
	 */
	public int getPosj() {
		return posj;
	}
	/**
	 * setter de posj
	 * @param posj un entier
	 */
	public void setPosj(int posj) {
		this.posj = posj;
	}
	/**
	 * getter de {@link GraphicController#Card}
	 * @return une {@link Card}
	 */
	public String getCard() {
		return Card;
	}
	/**
	 * setter de {@link GraphicController#Card}
	 * @param card une {@link Card}
	 */
	public void setCard(String card) {
		Card = card;
	}
	/**
	 * getters de {@link GraphicController#wantToAdd}
	 * @return {@link Boolean}
	 */
	public boolean isWantToAdd() {
		return wantToAdd;
	}
	/**
	 * setter de wantToAdd
	 * @param wantToAdd un {@link Boolean}
	 */
	public void setWantToAdd(boolean wantToAdd) {
		this.wantToAdd = wantToAdd;
	}
	/**
	 * getters de {@link GraphicController#hasplayed}
	 * @return {@link Boolean}
	 */
	public boolean isHasPlayed() {
		return hasPlayed;
	}
	/**
	 * setter de hasPlayed
	 * @param hasPlayed un {@link Boolean}
	 */
	public void setHasPlayed(boolean hasPlayed) {
		this.hasPlayed = hasPlayed;
	}
	/**
	 * getters de {@link GraphicController#hasFinished}
	 * @return {@link Boolean}
	 */
	public boolean isHasFinished() {
		return hasFinished;
	}
	/**
	 * setter de hasFinished
	 * @param hasFinished un {@link Boolean}
	 */
	public void setHasFinished(boolean hasFinished) {
		this.hasFinished = hasFinished;
	}
	/**
	 * getters de {@link GraphicController#hasTerminated}
	 * @return {@link Boolean}
	 */
	public boolean isHasTerminated() {
		return hasTerminated;
	}
	/**
	 * setter de hasTerminated
	 * @param hasTerminated un {@link Boolean}
	 */
	public void setHasTerminated(boolean hasTerminated) {
		this.hasTerminated = hasTerminated;
	}
	/**
	 * getters de {@link GraphicController#myTurn}
	 * @return {@link Boolean}
	 */
	public boolean isMyTurn() {
		return myTurn;
	}
	/**
	 * setter de myTurn
	 * @param myTurn un {@link Boolean}
	 */
	public void setMyTurn(boolean myTurn) {
		this.myTurn = myTurn;
	}
	/**
	 * getters de {@link GraphicController#configure}
	 * @return {@link Boolean}
	 */
	public static boolean isConfigure() {
		return configure;
	}
	/**
	 * setter de configure
	 * @param configure un {@link Boolean}
	 */
	public static void setConfigure(boolean configure) {
		GraphicController.configure = configure;
	}
	/**
	 * renvoie une reférence sur la {@link Partie} que le {@link Controller} gère actuellement
	 * @return une reférence sur la {@link Partie} que le {@link Controller} gère actuellement
	 */
	public Partie getMyParty() {
		// TODO Auto-generated method stub
		return myPartie;
	}
	/**
	 * setter de myPartie
	 */
	public void setMyPartie() {
		this.myPartie = Partie.startPartie();
	}
	/**
	 * initialiser la {@link Strategy}
	 * @param i un entier
	 */
	public void setStrategy(int i) {
		if(i == 1)
			this.botStrategy = new EasyVirtualPlayer();
		else
			this.botStrategy = new IntermediateVirtualPlayer();
	}
	/**
	 * initialiser le {@link Terrain}
	 * @param i un entier
	 */
	public void setField(int i) {
		if(i == 1)
			this.field = RectangularField.getInstanceint();
		else
			this.field = CircularField.getInstanceint();
	}
	/**
	 * getter de numberOfRealPlayer
	 * @return un entier
	 */
	public static int getNumberOfRealPlayer() {
		return numberOfRealPlayer;
	}
	/**
	 * setter de numberOfRealPlayer
	 * @param n un entier
	 */
	public static void setNumberOfRealPlayer(int n) {
		numberOfRealPlayer = n;
	}
	/**
	 * getter de numberOfplayer
	 * @return un entier
	 */
	public static int getNumberOfPlayer() {
		return numberOfPlayer;
	}
	/**
	 * setter de numberOfPlayer
	 * @param n un entier
	 */
	public static void setNumberOfPlayer(int n) {
		numberOfPlayer = n;
	}
	/**
	 * initialiser le {@link GameRule}
	 * @param i un entier
	 */
	public void setGameRule(int i) {
		if(i == 1)
			this.gameRule = new NormalGameRule();
		else
			this.gameRule = new AdvancedGameRule();
	}
	/**
	 * setter de ma vue
	 * @param g ma vue {@link VueGraphique}
	 */
	public void setMyView(VueGraphique g) {
		this.myView = g;
	}
	/**
	 * getter de mon controller console
	 * @return mon controller console
	 */
	public PartieConsoleEngine getPartieConsoleEngine() {
		return myConsoleController;
	}
	/**
	 * setter de mon constroller console
	 * @param p nouveau controller console
	 */
	public void setMyConsoleCOntroller(PartieConsoleEngine p) {
		this.myConsoleController = p;
	}
	/**
	 * getter de ma vue
	 * @return ma {@link VueGraphique}
	 */
	public VueGraphique getMyView() {
		return myView;
	}
	/**
	 * getter de formeField
	 * @return un entier
	 */
	public static int getFormeField() {
		return formeField;
	}
	/**
	 * setter de ma formefiled
	 * @param formeField un entier
	 */
	public static void setFormeField(int formeField) {
		GraphicController.formeField = formeField;
	}
	/**
	 * getter de gamerule
	 * @return un entier
	 */
	public static int getGamerule() {
		return gamerule;
	}
	/**
	 * setter de gamerule
	 * @param gamerule un entier
	 */
	public static void setGamerule(int gamerule) {
		GraphicController.gamerule = gamerule;
	}
	/**
	 * getter de strategyV
	 * @return un entier
	 */
	public static int getStrategyV() {
		return strategyV;
	}
	/**
	 * non implémenté
	 */
	public void Update() {
		play.removeHandCard();
	}
	/**
	 * setter de strategyV
	 * @param strategyV un entier
	 */
	public static void setStrategyV(int strategyV) {
		GraphicController.strategyV = strategyV;
	}
	/**
	 * retourne me {@link Player} en cours de jeu
	 * @return le {@link Player} en cour de jeu
	 */
	public Player getPlay() {
		return play;
	}
	/**
	 * setter du {@link Player} en cours de jeu
	 * @param play nouveau {@link Player} qui commence son tour
	 */
	public void setPlay(Player play) {
		this.play = play;
	}
}
