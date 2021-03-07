package fr.lo02.controler;

import fr.lo02.modele.Partie;

/**
 * Cette Classe est un Wrapper du type primitif Boolean
 * <br> Cette classe a ete cree pour resoudre certains problèmes de synchronisation({@link PartieConsoleEngine#beginParty()}) par le biais de Verrou ( lock) pour les blocs sunchronized car ces blocs ne prennent pas en parametres des types primitifs
 * <br>Cette classe est inspirée de la Classe {@link Boolean}
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see Partie
 * @see PartieConsoleEngine
 * @see Boolean
 *
 */
public class ShapeUpBoolean {
	/**
    * a {@link ShapeUpBoolean} is set to false by default
    */
	private boolean booleanValue = false;
	/**
	 * Constructeur
	 */
	public ShapeUpBoolean() {
		
	}
	/**
    * construct a new ShapeUpBoolean with the specified value
    * @param param the value of the boolean wrapped
    */
	public ShapeUpBoolean(boolean param) {
		booleanValue = param;
	}
	  /**
     * @return the value of the boolean wrapped
     */
	public boolean booleanValue() {
		return booleanValue;
	}
	/**
     * Sets the value of the boolean wrapped
     * @param myValue the value of the boolean wrapped
     */
	public void setBooleanValue(boolean myValue) {
		this.booleanValue = myValue;
	}
}
