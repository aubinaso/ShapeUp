package Load;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JDialog;

import fr.lo02.InterfaceGraphique.VueGraphique;
import fr.lo02.controler.GraphicController;
import fr.lo02.controler.PartieConsoleEngine;
import fr.lo02.modele.Partie;
import fr.lo02.modele.Player;
/**
 * C'est la Classe principale : c'est depuis cette classe que toutes les {@link Partie} sont lancées
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 *
 */
public class Launcher {
	/**
	 * Le modèle du Jeu
	 */
	public static Partie gameModel;
	/**
	 * le nombre de {@link Partie} jouée
	 */
	public static int numberPartie = 1;
	/**
     * Le moteur du jeu en Mode console , qui correspond également au Controleur en mode console
     */
	public static PartieConsoleEngine consoleEngine;
	/**
     * Le controleur graphique de la {@link Partie}
     */
	public static GraphicController GuiControler;
	/**
     * La fenêtre de lancement de la partie en mode Graphique
     */
	public static VueGraphique gameView;
	/**
	 * le {@link Thread} console
	 */
	public static Thread consoleThread;
	/**
	 * le {@link Thread} graphic
	 */
	public static Thread GraphicThread;
	/**
	 * le {@link Thread} du controller Graphique
	 */
	public static Thread ControlGraphic;
	/**
	 * l'historique des Vainqueurs des tours Jouers et leur Score
	 */
	public static HashMap<ArrayList<Integer>, Player> scoreBoard = new HashMap<ArrayList<Integer>, Player>(4);
	/**
	 * {@link Boolean} pour savoir si on recommence la {@link Partie}
	 */
	public static boolean restart = false;
	
	/**
     * Porte d'entrée du programme
     * @param args les arguments fournies depuis l'invite de commande : normalement aucun argument n'est attendu de l'invite de commande
     */
	public static void main(String[] args) {
		JDialog.setDefaultLookAndFeelDecorated(true);
		gameModel = Partie.startPartie();
		consoleEngine = PartieConsoleEngine.getInstanceOfEngine(gameModel);

		GuiControler = new GraphicController(gameModel);

		gameView = VueGraphique.getInstance(gameModel, GuiControler);

		consoleThread = new Thread(consoleEngine, "Console_Thread#0"+PartieConsoleEngine.counter);
		GraphicThread = new Thread(gameView, "GUI_Thread");

		ControlGraphic = new Thread(GuiControler, "GraphicController#");
	
		Runnable notif_runnable = new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				/*try {
					gameModel.notifyAll();
				}catch (NullPointerException e) {
					e.printStackTrace();
				}*/
			}
		};
		
		GuiControler.setMyView(gameView);
		//GuiControler.init();
		gameModel.addObserver(consoleEngine);
		gameModel.addObserver(gameView.getGame());
		gameModel.addObserver(gameView.getPanelVictory());
		gameModel.getConfiguration().addObserver(gameView.getPanelConfiguration());
		
		consoleEngine.setMyGraphicController(GuiControler);
		GuiControler.setMyConsoleCOntroller(consoleEngine);
		GraphicThread.start();
		consoleThread.start();
		ControlGraphic.start();
		
		try {
			while(gameModel.isPartyEnded() == false ) {
				Thread notificator = new Thread(notif_runnable, "NOTIFICATION THREAD");
				notificator.start();
				
				try {
					notificator.join();
					Thread.sleep(10);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}catch(NullPointerException e) {
			return;
		}
	}
	/**
     * Cette methode permet de relancer une nouvelle Party
     */
	public static void restart() {
		if(gameModel.getConfiguration().isConfigure()) {
			if(numberPartie < 4) {
				numberPartie++;
				Thread restartParty = new Thread("restart Party") {
					public void run() {
						gameModel.printScore();
						Launcher.scoreBoard.putAll(gameModel.getScoreBoard());
						Launcher.gameModel.endPartie();
						Launcher.gameModel.setPartyEnded(true);
						//Launcher.gameModel.startPartie();
						Launcher.gameView.reset();
						Launcher.consoleEngine.reset();
						Launcher.consoleEngine = null;
						Launcher.restart = true;
						//Launcher.gameModel = null;
						//Launcher.gameView = null;
						Partie.setViewWherePartyConfigured(false);
						Partie.setViewWherePartyStarted(false);
						Partie.setViewWherePartyEnded(false);
						System.out.println("Partie destroyed");
						System.out.println("Running after dispose");
						GraphicThread.stop();
						consoleThread.stop();
						ControlGraphic.stop();
						Launcher.main(null);
					}
				};
				System.out.println("Exiting Previous Console Thread");
				restartParty.start();
			}
		}
	}
}
