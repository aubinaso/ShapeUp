package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;
/**
 * Cette classe decrit un {@link Terrain} d'ajout de Carte
 * de forme Triangulaire et de nombre d'emplacement de 15
 * 
 * cette classe implemente le Pattern Singleton , un processus ne peut donc instancier qu'un seul objet de ce type à la fois 
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see Terrain
 * @see RectangularField
 * @see CircularField
 * @see Partie
 */
public class TriangularField extends Observable implements Terrain, Visitable{
	/**
	 * L'instance du {@link TriangularField} qui est entrain de se dérouler
	 * <b>Ce programme n'admet qu'un seul {@link Terrain} à la fois par {@link Partie}
	 * La classe {@link TriangularField} implémente le pattern Singleton</>
	 */
	private static TriangularField field = null;
	/**
     * liste de toutes les valeurs possible d'ajour de {@link Card} en cours de {@link Partie}
     * qui est une position vide et lié ({@link Terrain#AmILink(int, int, int, int)})
     */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> valuepossible;
	/**
     * ensemble de toutes les valeurs occupées par une {@link Card} non nulles sur le {@link TriangularField}
     */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> valueused;
	/**
     * liste de Coordonnées des emplacements possible en Mode {@link CircularField}
     * donc s'il faut jouer une {@link Card} à une position donnée, on vérifie déjà
     * que cet emplacement est contenu dans cette variable
     * en mode {@link CircularField} ces positions sont :
     * (1,3)
     * (2,2) (2,3) (1,4)
     * (3,1) (3,2) (3,3) (3,4) (3,5)
     * (4,2) (4,3) (4,4)
     * (5,1)
     */
    protected HashMap<ArrayList<Integer>, ArrayList<Integer>> fieldposition;
    
    
	/**
	    * renvoie une instance de {@link TriangularField} si il y en a une ou crèe et renvoie une nouvelle
	    * instance de {@link TriangularField} si aucun {@link Terrain} n'est référencé
	    * @return une référence sur un Objet de type {@link TriangularField}
	    */
	public static TriangularField getInstanceint()
	{
		if(TriangularField.field   == null)
			field = new TriangularField();
		return TriangularField.field;
    }
	/**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visité
     */
	@Override
	public void acceptT(FieldVisitor visitor) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * cette methode dont le paramètre est toujours false permet de dire qu'on a
	 * déjà 2 carte sur le {@link Terrain} et donc qu'on ne peut plus accepter
	 * toutes formes de déplacement
	 * @param directlyFirstCard vaut toujours false
	 */
	@Override
	public void setDirectlyFirstCard(boolean directlyFirstCard) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * Methode d'ajout de la carte sur le {@link TriangularField}
	 * @param card : {@link Card} à ajouter
	 * @param posi : indice de la ligne d'ajout
	 * @param posj : indice de la colonne d'ajout
	 * @param typeSet : type d'ajout : elle vaut 1 => dans le cas d'un déplacement
	 *  et 1 => dans le cas d'un ajout simple {@link RealPlayer#jouerCard(int, int, int, Terrain)}
	 * @return un {@link Boolean} qui dit si l'ajout est réussi
	 * elle n'est pas réussi si la position d'ajout est occupée ou n'est pas liée à une autre
	 * position déjà occupée dans le cas où il y'a déjà au moins une Card sur le terrain 
	 * d'ou l'utilisation de typeSet si on déplace pas besoin de chercher s'il y'a une card présente au
	 * premier tour
	 */
	@Override
	public boolean addCard(Card card, int posi, int posj, int typeSet) {
		// TODO Auto-generated method stub
		return false;
	}
	 /**
     * retourne la {@link Card} à une position donnée sur la vue du {@link Terrain}
     * @param i ligne d'ajout
     * @param j colonne d'ajout
     * @return retourne une {@link Card} si il y'a belle et bien une Card et Null si
     * la position est vide
     */
	@Override
	public Card getFieldposition(int i, int j) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
     * setters des éléments du {@link Terrain}
     * elle modifie l'élément à une position donnée de la vue du {@link Terrain}
     * elle place une {@link Card}
     * @param card card à positionner à l'emplacement du {@link Terrain}
     * @param i indice-ligne de la Position
     * @param j indice-colonne de la Position
     * @param type permet de savoir si on a besoin de notifier au vue
     * de ce changement :: elle peut prendre 2 valeur : 1 pour l'approbation de la
     * notification et 0 pour l'opposition à la notification
     */
	@Override
	public void setFieldposition(Card card, int i, int j, int type) {
		// TODO Auto-generated method stub
		
	}
	/**
     * permet de changer la position d'une {@link Card} sur le {@link Terrain}
     * @param card est la {@link Card} à changer de position
     * @param posi est la position-ligne final d'ajout
     * @param posj est la position-colonne finale d'ajout
     * @return {@link Boolean#TRUE} si l'ajout est réussi
     * elle ne l'est pas dans le cas où la Card à changer de place n'est pas sur le {@link Terrain} de {@link Card}
     * également lorsque la position finale est déjà occupée où si la position finale est n'est pas lier
     * {@link Terrain#AmILink(int, int)}
     */
	@Override
	public boolean setPositionCard(Card card, int posi, int posj) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
     * mettre à jour les positions libres de {@link Terrain}
     * c'est-à-dire les position où l'on peut ajouter une {@link Card} et qui est liée
     * @see Terrain#AmILink(int, int)
     */
	@Override
	public void updateValuePossible() {
		// TODO Auto-generated method stub
		
	}
	/**
     * mettre à jour les positions libres de {@link Terrain}
     * c'est-à-dire les position où une {@link Card} est présente
     * @see Terrain#AmILink(int, int)
     */
	@Override
	public void updateValueUsed() {
		// TODO Auto-generated method stub
		
	}
	/**
     * vérifie si une {@link Card} peut être déplacé d'une position
     * c'est-à-dire qu'elle ne va pas causé la création de deux Blocs de {@link Card} sur le tableau de {@link Card}
     * donné en paramètre
     * @param posi la position ligne finale d'ajout
     * @param posj la position finale d'ajout
     * @param typeSet le type de déplacement résultant {@link Terrain#addCard(Card, int, int, int)}
     * @param vali la position ligne initiale de la {@link Card}
     * @param valj la position colonne initiale de la {@link Card}
     * @param card le tableau de {@link Card} sur lequel 
     * @return {@link Boolean#TRUE} si c'est bien possible de déplacé la {@link Card}
     */
	@Override
	public boolean canIBeSet(int posi, int posj, int typeSet, int vali, int valj, Card[][] card) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
     * vérifie si une {@link Card} peut être déplacé d'une position
     * c'est-à-dire qu'elle ne va pas causé la création de deux Blocs de {@link Card} sur le {@link Terrain}
     * donné en paramètre
     * @param posi la position ligne finale d'ajout
     * @param posj la position finale d'ajout
     * @param typeSet le type de déplacement résultant {@link Terrain#addCard(Card, int, int, int)}
     * @param vali la position ligne initiale de la {@link Card}
     * @param valj la position colonne initiale de la {@link Card}
     * @return {@link Boolean#TRUE} si c'est bien possible de déplacé la {@link Card}
     */
	@Override
	public boolean canIBeSet(int posi, int posj, int typeSet, int vali, int valj) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
     * permet de savoir si le {@link Terrain} est totalement occupé
     * c'est à dire les positions dont la définition de la forme admet 
     * ont toutes été occupées
     * @return {@link Boolean#TRUE} si le {@link Terrain} est bien occupée
     */
	@Override
	public boolean isFull() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * méthode permettant donner false à la valeur de FirstCard si le {@link Terrain} contient déjà au moins
	 * une {@link Card} et typeSet vaut 1
	 * @param typeSet vaut toujours false
	 */
	@Override
	public void setdirectlyFirstCard(int typeSet) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * méthode permettant renvoyant false si le {@link Terrain} contient déjà au moins
	 * une {@link Card} et typeSet vaut 1
	 * @param typeSet vaut toujours false
	 */
	@Override
	public boolean getdirectlyFirstCard(int typeSet) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
     * méthode permettant de savoir si l'emplacement sur le {@link Terrain} dont
     * les positions sont passées en paramètres sont liées
     * @param posi indice ligne de l'emplacement sur le {@link Terrain}
     * @param posj indice colonne de l'emplacement sur le {@link Terrain}
     * @param vali valeur permettant d'éliminer un emplacement en ligne parmi ceux en paramètres
     * @param valj valeur permettant d'éliminer un emplacement en colonne parmi ceux en paramètres
     * @return {@link Boolean#TRUE} si l'emplacement passé en paramètre est liée à un emplacement occupé par une {@link Card}
     * hormis la {@link Card} à déplacer
     */
	@Override
	public boolean AmILink(int posi, int posj, int vali, int valj) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
     * méthode permettant de savoir si l'emplacement sur le card dont
     * les positions sont passées en paramètres sont liées
     * @param posi indice ligne de l'emplacement sur le card
     * @param posj indice colonne de l'emplacement sur le card
     * @param vali valeur permettant d'éliminer un emplacement en ligne parmi ceux en paramètres
     * @param valj valeur permettant d'éliminer un emplacement en colonne parmi ceux en paramètres
     * @param card vue {@link Terrain} sur lequel on étudie la liaison
     * @return {@link Boolean#TRUE} si l'emplacement passé en paramètre est liée à un emplacement occupé par une {@link Card}
     * hormis la {@link Card} à déplacer
     */
	@Override
	public boolean AmILink(int posi, int posj, int vali, int valj, Card[][] card) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
     * réinitialise la {@link TriangularField} avec ses paramètre par défaut
     * elle détruit l'instance présent et recrèe un nouveau
     */
	@Override
	public void fieldInitialise() {
		// TODO Auto-generated method stub
		
	}
	/**
     * déréférence l'instance de {@link TriangularField} présent
     * elle fait pointer
     */
	@Override
	public void fieldDestroy() {
		// TODO Auto-generated method stub
		
	}
	/**
     * méthode permettant de savoir si l'emplacement sur le card dont
     * les positions sont passées en paramètres sont liées
     * @param posi indice ligne de l'emplacement sur le card
     * @param posj indice colonne de l'emplacement sur le card
     * @return {@link Boolean#TRUE} si l'emplacement passé en paramètre est liée à un emplacement occupé par une {@link Card}
     */
	@Override
	public boolean AmILink(int posi, int posj) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
     * permet de savoir si une {@link Card} peut être ajouter à l'emplacement
     * donc les coordonnées sont passées en paramètres
     * @param posi indice ligne de l'emplacement
     * @param posj indice colonne de l'emplacement
     * @param typeSet permet de savoir la raison de la vérification d'ajout
     * 1 => déplacement
     * 0 => ajout
     * ceci car la possibilité d'ajout et de déplacement lorsqu'il y'a juste une
     * seule carte sur le {@link Terrain} doit être possible dans le cas du déplacement
     * à n'importe qu'elle position
     * @return {@link Boolean#TRUE} si il est bel et bien possible d'ajouter une {@link Card}
     * à l'emplacement donné
     */
	@Override
	public boolean CanIAdd(int posi, int posj, int typeSet) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
     * getter informant du nombre de colonne du {@link Terrain} à un moment donné
     * @return le nombre de colonne
     */
	@Override
	public int getLarge() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
     * getter informant du nombre de ligne du {@link Terrain} à un moment donné
     * @return le nombre de ligne
     */
	@Override
	public int getLenght() {
		// TODO Auto-generated method stub
		return 0;
	}
	/**
     * permet de savoir si le {@link Terrain} est totalement vide
     * c'est à dire les positions dont la définition de la forme admet 
     * ne sont occupées par aucune {@link Card}
     * @return {@link Boolean#TRUE} si le {@link Terrain} ne contient aucune emplacement occupée
     */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * getters de l'aspect du terrain à un instant donné :
	 * la disposition du Terrain
	 * @return le fieldView
	 */
	@Override
	public ArrayList<ArrayList<Card>> getFieldView() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * transforme la vue du {@link Terrain} en une Matrice de {@link Card} 5*5
	 * fait correspondre chaque position occupé par une {@link Card} par 
	 * le même emplacement dans la matrice de card
	 * @return la matrice de {@link Card}
	 */
	@Override
	public Card[][] toArray() {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * prend une chaine de trois caractère et le premier est soit 'f' ou 'h'
	 * le deuxième est 'r' 'g' ou 'b' et le dernier 't' 'c' ou 's'
	 * et transforme cela en une {@link Card}
	 * @see Card#assignID(Filled, Color, Shape)
	 * @param str chaine de trois caractères
	 * @return {@link Card} qui a le même {@link Card#getIDCARD()} que le paramètre
	 */
	public Card defineCard(String str)
	{
		Filled filled;
		Color color;
		Shape shape;
		String st = new String();
		st = "" + str.charAt(0);
		if(st.equals("f"))
			filled = Filled.FILLED;
		else if(st.equals("h"))
			filled = Filled.HOLLOW;
		else
			return null;//s'il la carte n'existe pas on ne fait rien
		st = "" + str.charAt(1);
		if(st.equals("b"))
			color = Color.BLUE;
		else if(st.equals("g"))
			color = Color.GREEN;
		else if(st.equals("r"))
			color = Color.RED;
		else
			return null;
		st = "" + str.charAt(2);
		if(st.equals("c"))
			shape = Shape.CIRCLE;
		else if(st.equals("s"))
			shape = Shape.SQUARE;
		else if(st.equals("t"))
			shape = Shape.TRIANGLE;
		else
			return null;
		return new Card(filled, color, shape);
	}
	/**
	 * vérifie si la {@link Card} donné en paramètre est présente sur
	 * le {@link Terrain}
	 * @param c une {@link Card} donc on veut savoir si il existe une {@link Card} sur le terrain semblable
	 * @return {@link Boolean#TRUE} s'il existe sur le terrain une Card au même {@link Card#getIDCARD()}
	 */
	public boolean hasCard(Card c) {
		return false;
		
	}
	/**
	 * vérifie si l'emplacement passé en paramètre est vide
	 * elle est vide si {@link Card} vaut null à l'emplacement du {@link Terrain}
	 * @param i indice colonne de l'emplacement
	 * @param j indice ligne de l'emplacement
	 * @return {@link Boolean#TRUE} si l'emplacement est bel et bien vide
	 */
	@Override
	public boolean hasNothing(int i, int j) {
		return false;
		
	}
	/**
	 * renvoie un {@link ArrayList} des emplacements (i,j) occupés par une {@link Card} sur le {@link Terrain}
	 * @return un {@link HashMap} de {@link ArrayList} de {@link Integer}
	 */
	@Override
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> getValueUsed() {
		// TODO Auto-generated method stub
		return this.valueused;
	}
	/**
	 * renvoie un {@link ArrayList} des emplacements (i,j) libre une {@link Card} sur le {@link Terrain}
	 * @return un {@link HashMap} de {@link ArrayList} de {@link Integer}
	 */
	@Override
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> getValuePossible() {
		// TODO Auto-generated method stub
		return this.valuepossible;
	}
	/**
	 * Ajoute un Observeur dans la liste d'{@link Observer} de {@link Terrain} qui n'appartient pas déjà au {@link Terrain}
	 * @param o un {@link Observer} à ajouter
	 */
	@Override
	public void addObservers(Observer o) {
		// TODO Auto-generated method stub
		this.addObserver(o);
	}
	/**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visité
     * @return les positions d'ajout possible
     */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> accept(FieldVisitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visité
     * @return les positions occupé par une {@link Card} non null du Terrain
     */
	@Override
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> acceptU(FieldVisitor visitor) {
		// TODO Auto-generated method stub
		return null;
	}
}
