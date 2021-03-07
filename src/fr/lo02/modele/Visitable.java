package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Cette interface est implémntée par tous les classes implémentant {@link Terrain} de la {@link Partie}<br>
 * Elle declare trois methodes {@link Visitable#accept(FieldVisitor)}, {@link Visitable#acceptT(FieldVisitor)}, {@link Visitable#acceptU(FieldVisitor)}
 * qui permettent à un {@link FieldVisitor} (la {@link Partie}) d'avoir un aspect et d'effectuer des calcul de score
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 *@see Terrain
 *@see RectangularField
 *@see CircularField
 *@see TriangularField
 *@see FieldVisitor
 */
public interface Visitable {
	/**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visité
     * @return les positions d'ajout possible
     */
	HashMap<ArrayList<Integer>, ArrayList<Integer>> accept(FieldVisitor visitor);
	/**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visité
     * @return les positions occupé par une {@link Card} non null du Terrain
     */
	HashMap<ArrayList<Integer>, ArrayList<Integer>> acceptU(FieldVisitor visitor);
	/**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visité
     */
	void acceptT(FieldVisitor visitor);
}
