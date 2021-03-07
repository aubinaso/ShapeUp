package fr.lo02.controler;

import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;
import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import Load.Launcher;
import fr.lo02.InterfaceGraphique.BoutonHandCard;
import fr.lo02.InterfaceGraphique.VueGraphique;
import fr.lo02.modele.AdvancedGameRule;
import fr.lo02.modele.Card;
import fr.lo02.modele.Color;
import fr.lo02.modele.Configuration;
import fr.lo02.modele.Deck;
import fr.lo02.modele.Filled;
import fr.lo02.modele.NormalGameRule;
import fr.lo02.modele.Partie;
import fr.lo02.modele.Player;
import fr.lo02.modele.RealPlayer;
import fr.lo02.modele.RectangularField;
import fr.lo02.modele.Shape;
import fr.lo02.modele.Terrain;
import fr.lo02.modele.Update;
import fr.lo02.modele.VirtualPlayer;

/**
 * Cette classe est le {@link Controller} pour le mode Console du Jeu.<br>
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseaux et Télécommunication Semestre 01 - Automne 2020 - LO02
 * @see Runnable
 * @see Observer
 * @see Controller
 * @see Partie
 * @see GraphicController
 *
 */
public class PartieConsoleEngine implements Runnable, Observer, Controller{
	/**
	 * La {@link Partie} gérée sue lr {@link Controller}
	 */
	private Partie myPartie = null;
	/**
	 * Drapeau signalant que la methode {@link Controller#sequenceLoop()} est arrivée à son terme
	 */
	private boolean endSequenceLoop = false;
	/**
	 * Le flux d'entré des données depuis l'entrée standard
	 */
	public static BufferedReader input = new BufferedReader(
			new InputStreamReader(
					Channels.newInputStream(
							(new FileInputStream(FileDescriptor.in)).getChannel())));
	/**
	 * L'instance du controleur console : Une partie ne peut d'instancier qu'un seul controleur à la fois
	 */
	private static PartieConsoleEngine myInstance = null;
	/**
	 * non implémenté
	 */
	public static int counter = 0;
	/**
	 * Verrou permettant de gérer la lecture des entrées de l'utilisateur de manière asynchrone
	 * <br>Le {@link Thread} qui attend les entrées de l'utilisateur set ce flag ce qui permet de faire de sortir d'une boucle d'attente
	 */
	private static boolean IOLockFlag = false;
	/**
	 * Verrou permettant de gérer la lecture des entrées de l'utilisateur de manière asynchrone
	 * <br>Le {@link Thread} qui attend les entrées de l'utilisateur set ce flag ce qui permet de faire de sortir d'une boucle d'attente
	 */
	private ShapeUpBoolean LockObject = new ShapeUpBoolean(false);
	/**
	 * Non Implémenté
	 */
	private StringBuffer consoleView = new StringBuffer(500);
	/**
	 * Le controleur graphic de la {@link Partie} : en effet les deux doivent communiquer ensemble
	 */
	private Runnable AsyncIO;
	/**
	 * Ce Runnable s'occupe d'attendre les entrées de l'Utilisateur
	 */
	boolean myTurn = false;
	/**
	 * Le Controleur graphique de la {@link Partie} : en effet les deux doivent communiquer ensemble
	 */
	private GraphicController myGraphicController;
	/**
	 * Le Construteur du {@link Controller}
	 * @param p la {@link Partie} qui sera gérée par le {@link Controller}
	 */
	private PartieConsoleEngine(Partie p) {
		// TODO Auto-generated constructor stub
		AsyncIO = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("PRESS 'ENTER' TO CONTINUE");
				try {
					input.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				IOLockFlag = true;
				LockObject.setBooleanValue(true);
			}
		};
		this.myPartie = p;
	}
	/**
	 * retourne le référencement du controleur console, si elle ne référence rien elle crèe un nouveau et retourne le nouveau
	 * @param p la {@link Partie}
	 * @return un controleur de console
	 */
	public static PartieConsoleEngine getInstanceOfEngine(Partie p) {
		if(myInstance == null) {
			counter++;
			myInstance = new PartieConsoleEngine(p);
		}
		return myInstance;
	}
	/**
	 * Cette méthode permet de démarrer une {@link Partie} dans le {@link Thread} en appellant le méthode {@link Controller#beginParty()}
	 */
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			this.beginParty();
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.out.println("Exiting Thread " + Thread.currentThread().getName());
			return;
		}
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
		System.out.println("\tHELLO !!! WELCOME TO SHAPEUP GAME \n\t");
		Thread startConsole = new Thread(AsyncIO, "Console_Begin_Party_AsyncIO");
		startConsole.start();
		while(LockObject.booleanValue() == false && myPartie.isPartyEnded() == false) {
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
				
			}
		}
		LockObject.setBooleanValue(false);
		while(IOLockFlag==false && myPartie.isPartyEnded() == false) {
			System.out.println("#");
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		IOLockFlag=false;
		if(myPartie.isPartyEnded()) {
			System.out.println("PARTY ENDED IN ANOTHER VIEW");
			return;
		}
		synchronized (myPartie) {
			if(myPartie.getConfiguration().isConfigure() == false) {
				System.out.println("\tVoulez vous configurer la partie ('0' --> 'Non' || '1' --> 'Oui')");
				if(readChoice(0,1) == 1) {
					myPartie.getConfiguration().setConfigure(true);
					Partie.setViewWherePartyConfigured(false);
					this.configParty();
				}
			}
			if(Partie.isViewWherePartyConfigured() == false && myPartie.isPartyStarted() == false) {
				this.setParty();
				this.createPlayers();
				myPartie.setNextPlayer();
				myPartie.distributeCard();
				myPartie.setPartyStarted(true);
				myPartie.notifyAll();
			}
			if(Partie.isViewWherePartyStarted() == false) {
				myPartie.setPartyStarted(true);
				myPartie.notifyAll();
			}
		}
		this.sequenceLoop();
		if(Partie.isViewWherePartyEnded() == false) {
			this.publishResult();
			try {
				Thread.sleep(800);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			VueGraphique.getIT().getNext().doClick();
			System.out.println("Do you want to Restart ? \t '0' --> 'No' ; '1' --> 'Yes'");
			int choice = readChoice(0, 1);
			if(choice == 1) {
				Launcher.restart();
			}else {
				System.exit(0);
			}
		}
	}
	/**
	 * Cette Methode Permet de Configurer une {@link Partie}
	 */
	public void configParty() {
		// TODO Auto-generated method stub
		System.out.println("PARTY CONFIGURATION\n\tCombien y'a-t'il de joueur ?? \n \t N.B ce nombre est soit 2 soit 3");
		myPartie.getConfiguration().setPlayerNumber(readChoice(2, 3));
		System.out.println("Combien y'a-t'il de joueur réel ?? \n \t N.B ce nombre doit être inferieur à " + myPartie.getConfiguration().getPlayerNumber());
		myPartie.getConfiguration().setRealPlayer(readChoice(0, myPartie.getConfiguration().getPlayerNumber()));
		System.out.println("Quelle est la strategy des joueur virtuels ?? \n \t N.B ce nombre est soit 1 (Facile) soit 2 (Intermediaire)");
		myPartie.getConfiguration().setVirtualPlayerDifficulty(readChoice(1, 3));
		System.out.println("quelle règle de jeu appliquer vous ?? \n \t N.B 1 (normal) \t 2 (avancé)");
		myPartie.getConfiguration().setGameRule(readChoice(1, 2));
		System.out.println("choisissez votre terrain ?? \n \t N.B 1 (Rectangular), 2 (circular)");
		myPartie.getConfiguration().setShapeField(readChoice(1, 2));
		myPartie.getConfiguration().setConfigInPartie(true);
		this.myGraphicController.getMyView().getPanelConfiguration().enableThing();
	}
	/**
	 * elle permet de recommencer les séquences jusqu'à ce que la {@link Partie} se termine
	 */
	public void sequenceLoop() {
		// TODO Auto-generated method stub
		Thread sequenceStart;
		while(!myPartie.getConfiguration().getGamerule().isEnded(myPartie)) {
			sequenceStart = new Thread(AsyncIO, "Sequence_Begin_AsyncIO");
			sequenceStart.start();
			while(LockObject.booleanValue() == false && myPartie.isPartyEnded() == false) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			LockObject.setBooleanValue(false);
			while(IOLockFlag==false && myPartie.isPartyEnded()== false) {
				System.out.println("#");
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			IOLockFlag = false;
			if(myPartie.isPartyEnded())
				break;
			synchronized (myPartie) {
				if(myPartie.getSequenceStarted() == false) {
					myPartie.setSequenceStarted(true);
					myPartie.setBroadCast("");
					Player p = myPartie.getPlay();
					if(p instanceof RealPlayer)
						this.notif((RealPlayer) p);
					if(p instanceof VirtualPlayer) {
						if(p.isHasPlayed() == false) {
							p.setHasPlayed(true);
							myPartie.getConfiguration().getGamerule().jouerTour(p, myPartie.getDeck(), myPartie.getField());
							myPartie.setNextPlayer();
						}
					}
					else {
						myTurn = false;
						if(p.isHasPlayed() == false) {
							if(myPartie.getConfiguration().getGamerule() instanceof NormalGameRule) {
								myTurn = true;
								p.setWhoPlayed(2);
								p.setHasPlayed(true);
								if(!myPartie.getDeck().deckIsEmpty())
									p.piocherCard(myPartie.getDeck().getNextCard());
								System.out.println(p.getPseudo() + " voulez vous jouer avant de déplacer ??\n\t'0' -> 'NON'\n\t'1' -> 'OUI'");
								if(((RealPlayer)p).playFirst(readChoice(0, 1))) {
									JouerTourNormal((RealPlayer)p);
									p.setNextCard(null);
									setPosiCard(p);
								}else {
									setPosiCard(p);
									JouerTourNormal((RealPlayer)p);
									p.setNextCard(null);
								}
								p.setHasTerminated(true);
								this.myGraphicController.setHasTerminated(true);
								myPartie.setNextPlayer();
							}else {
								System.out.println(p.getPseudo() + " voulez vous jouer avant de déplacer ??\n\t'0' -> 'NON'\n\t'1' -> 'OUI'");
								myTurn = true;
								p.setWhoPlayed(2);
								p.setHasPlayed(true);
								if(((RealPlayer)p).playFirst(readChoice(0, 1))) {
									JoueurTourAdvanced((RealPlayer)p);
									setPosiCard(p);
								}else {
									setPosiCard(p);
									JoueurTourAdvanced((RealPlayer)p);
								}
								if(!myPartie.getDeck().deckIsEmpty())
									p.piocherCard(myPartie.getDeck().getNextCard());
								this.myGraphicController.setHasTerminated(true);
								p.setHasTerminated(true);
								myPartie.setNextPlayer();
							}
						}
					}
					myTurn = false;
					myPartie.setSequenceStarted(false);
					myPartie.notifyAll();
				}else {
					try {
						System.out.println("PLEASE WAIT A SEQUENCE IS RUNNING IN ANOTHER VIEW");
						myPartie.wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
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
			rt.addObserver(VueGraphique.getInstance(myPartie, myGraphicController).getGame());
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
		this.myPartie.getField().addObservers(this);
		this.myPartie.getField().addObservers(VueGraphique.getInstance(myPartie, myGraphicController).getPanelTerrain());
		System.out.println(myPartie.getField().toString());
		this.setRealPlayerName();
	}
	/**
	 * la méthode Update de l'Observer
	 * non implémenté
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		Update u = (Update)arg;
		if(o instanceof Partie) {
			Partie c = (Partie) o;
			switch (u) {
			case INT :
				System.out.println(c.getBroadCast());
				break;
			case NAME :
				System.out.println(c.getBroadCast());
				break;
			case No :
				System.out.println(c.getBroadCast());
				break;
			case CARD :
				System.out.println(c.getBroadCast());
				break;
			}
		}
		if(o instanceof Terrain) {
			System.out.println((Terrain)o);
		}
	}
	/**
	 * permet de lire l'ID d'une {@link Card}, donc elle vérifie le respect de la syntaxe
	 * @return un String de la forme de {@link Card#getIDCARD()}
	 */
	public String readChoice() {
		String str = "";
		Scanner sc = new Scanner(System.in);
		while(true) {
			try {
				str = PartieConsoleEngine.input.readLine();
				if(str.matches("..."));
					break;
			}catch(InputMismatchException | IOException e)
			{
				System.out.println("Entrez une carte svp ");
				sc.next();
			}catch(NullPointerException e)
			{
				System.out.println("Entrez une bonne valeur svp ");
				sc.next();
			}
		}
		return str;
	}
	/**
	 * permet de lire un entier entre deux valeurs données en paramètres
	 * @param includeMinValue la valeur min
	 * @param includeMaxValue la valeur max
	 * @return un entier
	 */
	public int readChoice(int includeMinValue, int includeMaxValue) {
		String asciiChoice = "";
		int intChoice = -1;
			while(intChoice<includeMinValue || intChoice>includeMaxValue)
			{
				try {
					asciiChoice = PartieConsoleEngine.input.readLine();
					intChoice = Integer.parseInt(asciiChoice);
					if(intChoice<includeMinValue || intChoice> includeMaxValue)
					{
						System.out.println("\tS'il vous plait entrez un entier entre [ "+includeMinValue+"; "+includeMaxValue+" ]");
						intChoice = -1;
					}
				}catch (NumberFormatException e) {
					System.out.println("\tEntrer un entier entre [ "+includeMinValue+"; "+includeMaxValue+" ]");
					intChoice = -1;
				}catch (IOException e) {
					e.printStackTrace();
				}
			}
		return intChoice;
	}
	/**
	 * permet de lire un String
	 * @param playerRank utilisé pour attribuéun  nom par défaut et donc permet dedifférencier les noms donné par défaut à l'aide d'un entier
	 * @return un {@link String}
	 */
	public String readName(int playerRank) {
		String name = "";
		try {
			name = PartieConsoleEngine.input.readLine();
		}catch(IOException e) {
			e.printStackTrace();
		}
		if(name.trim().isEmpty()) {
			name = "Player#"+playerRank;
		}
		return name.toUpperCase();
	}
	/**
	 * cette méthodes permet d'instancier les {@link RealPlayer#getPseudo()} des joueurs reéls<br>
	 * et par la suite, ces Pseudos leurs Seront attribués
	 */
	public void setRealPlayerName() {
		for(int i = 0; i < this.myPartie.getConfiguration().getRealPlayer(); i++)
		{
			System.out.println("Entrer le nom du " + i+1 + " joueur Réel");
			this.myPartie.getConfiguration().getListeNom().add(readName(i+1));
		}
	}
	/**
	 * affiche le résultat de la {@link Partie} qui vient juste d'être terminée
	 */
	public void publishResult() {
		myPartie.printScore();
		System.out.println(myPartie.getBroadCast());
	}
	/**
	 * Détruit le controleur de la Console
	 */
	public void reset() {
		myInstance = null;
		myPartie = null;
		putFalse();
	}
	/**
	 * méthode permettant à un joueur Virtuel d'effectuer un tour de {@link Partie} en mode {@link NormalGameRule}
	 * @param p {@link RealPlayer} qui joue
	 */
	public void JouerTourNormal(RealPlayer p) {
		int posi, posj;
		do {
			System.out.println("Entrer la ligne d'ajout de la carte");
			posi = readChoice(1, 5);
			System.out.println("Entrer la colonne d'ajout");
			posj = readChoice(1, 5);
		}while(!this.myPartie.getField().addCard(p.getNextCard(), posi, posj, 0));
	}
	/**
	 * méthode permettant à un joueur Virtuel d'effectuer un tour de {@link Partie} en mode {@link AdvancedGameRule}
	 * @param p {@link RealPlayer} qui joue
	 */
	public void JoueurTourAdvanced(RealPlayer p) {
		boolean getIt = false;
		Card card;
		int i = 0, posi, posj;
		p.handCardShuffle();
		System.out.println("Voici vos cartes quelles cartes voulez vous jouer??\n");
		for(Card card1 : p.getHandCard())
		{
			System.out.println("\t"+card1.getIDCARD());
		}
		String str = new String();
		
		while(true) {
			System.out.println("\n"+p.getPseudo() + " Entrer la carte à jouer; NB c'est sous la forme xxx");
			str = readChoice();
			card = p.defineCard(str);
			for(i = 0; i<p.getHandCard().size(); i++)
			{
				if(card != null) {
					if(p.getHandCard().get(i).equals(card))
					{
						getIt = true;
						break;
					}
				}
			}
			if(getIt)
			{
				p.setNextCard(card);
				p.removeHandCard(i);
				break;
			}
		}
		posi=0;
		posj=0;
		boolean isok = false;
		do
		{
			System.out.println(p.getPseudo() + " Entrer la ligne d'ajout de la carte");
			posi = readChoice(1, 5);
			System.out.println(p.getPseudo() + " Entrer la colonne d'ajout de la carte");
			posj = readChoice(1, 5);
		}while(!this.myPartie.getField().addCard(p.getNextCard(), posi, posj, 0));
		p.setNextCard(null);
	}
	/**
	 * méthode permettant à un {@link RealPlayer} de déplacer une {@link Card} sur le {@link Terrain}
	 * @param p le {@link Player} qui doit déplacer une {@link Card}
	 */
	public void setPosiCard(Player p) {
		String str = new String();
		int posi, posj;
		boolean wantToSetPosition = false;
		System.out.println(p.getPseudo() + " Voulez vous déplacer la carte?? '0' -> 'Non' || '1' -> 'Oui'");
		if(readChoice(0, 1) == 1)
			wantToSetPosition = true;
		Card card;
		boolean hasSetPosition = false;
		if(wantToSetPosition) {
			while (!hasSetPosition) {
				boolean ok = false;
				do {
					System.out.println("\n"+p.getPseudo() + " Entrer la carte à changer de position; NB c'est sous la forme xxx");
					str = readChoice();
					card = p.defineCard(str);
					ok = myPartie.getField().hasCard(card);
					if(card == null)
						ok = false;
				}while(!ok);
				do {
					System.out.println(p.getPseudo() + " Entrer la ligne finale de positionnement");
					posi = readChoice(1, 5);
					System.out.println(p.getPseudo() + " Entrer la colonne finale de positionnement");
					posj = readChoice(1, 5);
				}while(!myPartie.getField().hasNothing(posi, posj));
				hasSetPosition = myPartie.getField().setPositionCard(card, posi, posj);
				if(!hasSetPosition) {
					System.out.println(myPartie.getField() + "");
					System.out.println(p.getPseudo() + " Ne voulez vous pas continuer sans changer de position?? \n\t'0' -> 'Non' \n\t'1' -> 'Oui'");
					hasSetPosition = false;
					if(readChoice(0, 1) == 1)
						hasSetPosition = true;
				}
			}
		}
	}
	/**
	 * définir une {@link Card} à partir de son idCard
	 * @see Card#assignID(Filled, Color, Shape)
	 * @param str l'id de la {@link Card}
	 * @return la {@link Card} corpondant à l'id
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
	 * active le bouton du player donné en paramètre et désactive celle des autres
	 * @param p un {@link RealPlayer} qui commence son tour
	 */
	public void notif(RealPlayer p) {
		int i = 0;
		for(Player pl : myPartie.getPlayers()) {
			if(pl.equals(p)) {
				for(BoutonHandCard b : VueGraphique.getInstance(myPartie, myGraphicController).getGame().getPlayers()) {
					b.setEnabled(false);
				}
				VueGraphique.getInstance(myPartie, myGraphicController).getGame().getPlayers().get(i).setEnabled(true);
				break;
			}
			i++;
		}
	}
	/**
	 * initialise le {@link Boolean} de la classe
	 */
	public void putFalse() {
		endSequenceLoop = false;
		IOLockFlag = false;
		LockObject = new ShapeUpBoolean(false);
		myTurn = false;
	}
	/**
	 * getters de {@link PartieConsoleEngine#myTurn}
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
	 * renvoie une reférence sur la {@link Partie} que le {@link Controller} gère actuellement
	 * @return une reférence sur la {@link Partie} que le {@link Controller} gère actuellement
	 */
	public Partie getMyParty() {
		// TODO Auto-generated method stub
		return myPartie;
	}
	/**
	 * setter du controler graphique
	 * @param g nouveau controller graphique
	 */
	public void setMyGraphicController(GraphicController g) {
		this.myGraphicController = g;
	}
	/**
	 * getter du controleur graphique
	 * @return un {@link GraphicController}
	 */
	public GraphicController getGraphicController() {
		return myGraphicController;
	}
	/**
	 * getter de la partie
	 * @return une {@link Partie}
	 */
	public Partie getMyPartie() {
		return this.myPartie;
	}
	/**
	 * setter de ma {@link Partie}
	 * instancie une nouvelle
	 */
	public void setMyParty() {
		myPartie = Partie.startPartie();
	}
	/**
	 * retourne le gestionnaire des flux d'entré
	 * @return un {@link BufferedReader}
	 */
	public static BufferedReader getInput() {
		if(myInstance == null)
			return null;
		else
			return PartieConsoleEngine.input;
	}
}
