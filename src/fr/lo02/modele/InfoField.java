package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * classe abstraite renseignant sur les infos d'un aspect d'une couleur ou d'une shape ou d'un remplissage sur le {@link Terrain}
 * permettant par la suite à parti des classes héritante de calculer le score des Joueurs
 * 
 * @author Aubin Patrick TAKAM TAYO- Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02
 *
 *@see InfoFieldColor
 *@see InfoFieldFilled
 *@see InfoFieldShape
 *@see Color
 *@see Filled
 *@see Shape
 *@see CalculScore
 *@see Player
 *@see Partie
 */
abstract public class InfoField implements FieldVisitor{
	/**
	 * ensemble des positionnements obtenus sur d'uns {@link Color} : {@link InfoFieldColor}
	 * ou d'une {@link Shape} : {@link InfoFieldShape}
	 * ou une {@link Filled} : {@link InfoFieldFilled}
	 */
	protected HashMap<Integer, ArrayList<Integer>> result = new HashMap<Integer, ArrayList<Integer>>();
    /**
     * renvoie les informations sur {@link InfoField#result}
     * @return une HashMAp
     */
    public 	HashMap<Integer, ArrayList<Integer>> getResult()
	{
		return this.result;
	}
    /**
     * renvoie les informations sur l'instance de l'Objet
     * qui représente les infos des possibles
     */
    public String toString()
	{
		StringBuffer sd = new StringBuffer();
		if(this.result.isEmpty())
		{
			sd.append("Sorry i'm empty");
		}
		else
		{
			sd.append(this.result.toString());
		}
		return sd.toString();
	}
}
