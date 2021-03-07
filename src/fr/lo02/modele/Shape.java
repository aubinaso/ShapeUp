package fr.lo02.modele;
/**
 * <p>Enumeration des differents formes de carte
 * Le ShapeUp utilise un Jeu de 18 Cartes avec 3 formes TRIANGLE, SQUARE, CIRCLE
 * Ce qui fait 6 Cartes par formes
 * </p>
 * 
 *<p>
 *TRIANGLE<BR>
 *SQUARE<BR>
 *CIRCLE<BR>
 *</p>
 * 
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see Card
 * @see Filled
 * @see Color
 * @version 1.0
 */
public enum Shape {
	/**
	 * Terrain {@link TriangularField}
	 */
	TRIANGLE,
	/**
	 * {@link Terrain} {@link RectangularField}
	 */
	SQUARE,
	/**
	 * {@link Terrain} {@link CircularField}
	 */
	CIRCLE;
}
