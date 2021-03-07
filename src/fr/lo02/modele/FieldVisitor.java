package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * cette interface impl�ment�e par la {@link DifficultVirtualPlayer}, {@link IntermediateVirtualPlayer}, {@link EasyVirtualPlayer}, {@link InfoField} fournie trois methodes qui permettent de visiter le {@link Terrain} qui sont
 * {@link RectangularField}, {@link CircularField}, {@link TriangularField} et de renvoyer la disposition du {@link Terrain#getFieldView()} du {@link Terrain} visit�
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01- Automne 2020 - LO02 
 * @see Terrain
 * @see RectangularField
 * @see CircularField
 * @see TriangularField
 * @see InfoField
 * @see IntermediateVirtualPlayer
 * @see EasyVirtualPlayer
 * @see DifficultVirtualPlayer
 * @see InfoFieldColor
 * @see InfoFieldFilled
 * @see InfoFieldShape
 */
public interface FieldVisitor {
	/**
	 * cette m�thode renvoie les positions disponibles du {@link Terrain}
	 * @param field le {@link Terrain} de la {@link Partie}
	 * @param i un {@link Integer}
	 * @return une liste de position disponible {@link Terrain#getValuePossible()}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> visit(Terrain field, int i);
	/**
	 * cette m�thode renvoie les positions indisponibles du {@link Terrain}
	 * @param field le {@link Terrain} de la {@link Partie}
	 * @return une liste de position indisponible {@link Terrain#getValueUsed()}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> visitU(Terrain field);
	/**
	 * cette m�thode renvoie une certaines dispositions du terrain
	 * @param field {@link Terrain} de la {@link Partie}
	 */
	public void visit(Terrain field);	
}
