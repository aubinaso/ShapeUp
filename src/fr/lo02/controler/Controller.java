package fr.lo02.controler;

import fr.lo02.modele.Deck;
import fr.lo02.modele.Partie;
import fr.lo02.modele.Player;
import fr.lo02.modele.RealPlayer;
import fr.lo02.modele.Terrain;
/**
 * Cette interface regroupe toutes les méthodes qu'un Controleur 
 * doit obligatoirement implémenter
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseaux et Télécommunication Semestre 01 - Automne 2020 - LO02
 *
 * @see GraphicController
 * @see PartieConsoleEngine
 */
public interface Controller {
	/**
	 * Cette Methode permet de Lancer une {@link Partie}<br>
	 * si La {@link Partie} n'est pas encore configurée, elle etablie la configuration par défaut avant  démarrer la {@link Partie}
	 */
	public void beginParty();
	/**
	 * Cette Methode Permet de Configurer une {@link Partie}
	 */
	public void configParty();
	/**
	 * elle permet de recommencer les séquences jusqu'à ce que la {@link Partie} se termine
	 */
	public void sequenceLoop();
	/**
	 * Cette methode permtet de créer les {@link Player} de la {@link Partie} , elle est toujour appelée après la configuration de la {@link Partie}
	 */
	public void createPlayers();
	/**
	 * cette méthodes permet d'instancier les {@link RealPlayer#getPseudo()} des joueurs reéls<br>
	 * et par la suite, ces Pseudos leurs Seront attribués
	 */
	public void setRealPlayerName();
	/**
	 * Cette methode permet d'instancier certains membres de la {@link Partie} après que cette dernière ait été configurée <br>
	 * par exemple : le {@link Deck} et le {@link Terrain}
	 */
	public void setParty();
	/**
	 * renvoie une reférence sur la {@link Partie} que le {@link Controller} gère actuellement
	 * @return une reférence sur la {@link Partie} que le {@link Controller} gère actuellement
	 */
	public Partie getMyParty();
}
