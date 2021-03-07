package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Cette interface est impl�mnt�e par tous les classes impl�mentant {@link Terrain} de la {@link Partie}<br>
 * Elle declare trois methodes {@link Visitable#accept(FieldVisitor)}, {@link Visitable#acceptT(FieldVisitor)}, {@link Visitable#acceptU(FieldVisitor)}
 * qui permettent � un {@link FieldVisitor} (la {@link Partie}) d'avoir un aspect et d'effectuer des calcul de score
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01- Automne 2020 - LO02 
 *@see Terrain
 *@see RectangularField
 *@see CircularField
 *@see TriangularField
 *@see FieldVisitor
 */
public interface Visitable {
	/**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visit�
     * @return les positions d'ajout possible
     */
	HashMap<ArrayList<Integer>, ArrayList<Integer>> accept(FieldVisitor visitor);
	/**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visit�
     * @return les positions occup� par une {@link Card} non null du Terrain
     */
	HashMap<ArrayList<Integer>, ArrayList<Integer>> acceptU(FieldVisitor visitor);
	/**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visit�
     */
	void acceptT(FieldVisitor visitor);
}
