package fr.lo02.controler;

import fr.lo02.modele.Deck;
import fr.lo02.modele.Partie;
import fr.lo02.modele.Player;
import fr.lo02.modele.RealPlayer;
import fr.lo02.modele.Terrain;
/**
 * Cette interface regroupe toutes les m�thodes qu'un Controleur 
 * doit obligatoirement impl�menter
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seaux et T�l�communication Semestre 01 - Automne 2020 - LO02
 *
 * @see GraphicController
 * @see PartieConsoleEngine
 */
public interface Controller {
	/**
	 * Cette Methode permet de Lancer une {@link Partie}<br>
	 * si La {@link Partie} n'est pas encore configur�e, elle etablie la configuration par d�faut avant  d�marrer la {@link Partie}
	 */
	public void beginParty();
	/**
	 * Cette Methode Permet de Configurer une {@link Partie}
	 */
	public void configParty();
	/**
	 * elle permet de recommencer les s�quences jusqu'� ce que la {@link Partie} se termine
	 */
	public void sequenceLoop();
	/**
	 * Cette methode permtet de cr�er les {@link Player} de la {@link Partie} , elle est toujour appel�e apr�s la configuration de la {@link Partie}
	 */
	public void createPlayers();
	/**
	 * cette m�thodes permet d'instancier les {@link RealPlayer#getPseudo()} des joueurs re�ls<br>
	 * et par la suite, ces Pseudos leurs Seront attribu�s
	 */
	public void setRealPlayerName();
	/**
	 * Cette methode permet d'instancier certains membres de la {@link Partie} apr�s que cette derni�re ait �t� configur�e <br>
	 * par exemple : le {@link Deck} et le {@link Terrain}
	 */
	public void setParty();
	/**
	 * renvoie une ref�rence sur la {@link Partie} que le {@link Controller} g�re actuellement
	 * @return une ref�rence sur la {@link Partie} que le {@link Controller} g�re actuellement
	 */
	public Partie getMyParty();
}
