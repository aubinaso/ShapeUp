package fr.lo02.modele;
/**
 * <p>Enumeration des differents remplissage de carte
 * Le ShapeUp utilise un Jeu de 18 Cartes avec 2 remplissage FILLED, HOLLOW
 * Ce qui fait 9 Cartes par couleurs
 * </p>
 * 
 *<p>
 *FILLED<BR>
 *HOLLOW<BR>
 *</p>
 * 
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01- Automne 2020 - LO02 
 * @see Card
 * @see Color
 * @see Shape
 * @version 1.0
 */
public enum Filled {
	/**
	 * carte pleine
	 */
	FILLED,
	/**
	 * carte vide
	 */
	HOLLOW;
}
