package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;

/**
 * Cette interface définie les formes de Terrain
 * elle est dans la <b>version 1.0</b> la forme {@link RectangularField}, {@link CircularField}, {@link TriangularField}
 * @author Aubin Patrick TAKAM TAYO - Université de technologie de Troyes - Réseau et Télécommunication Semestre 01 - Automne 2020
 *@see RectangularField
 *@see CircularField
 *@see TriangularField
 */
public interface Terrain {
	/**
	 * cette methode dont le paramètre est toujours false permet de dire qu'on a
	 * déjà 2 carte sur le {@link Terrain} et donc qu'on ne peut plus accepter
	 * toutes formes de déplacement
	 * @param directlyFirstCard vaut toujours false
	 */
	public void setDirectlyFirstCard(boolean directlyFirstCard);
	/**
	 * getters de l'aspect du terrain à un instant donné :
	 * la disposition du Terrain
	 * @see RectangularField#fieldView
	 * @see CircularField#fieldView
	 * @return le fieldView du {@link Terrain}
	 */
	public ArrayList<ArrayList<Card>> getFieldView();
	/**
	 * Methode d'ajout de la carte sur le {@link Terrain}
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
    public boolean addCard(Card card, int posi, int posj, int typeSet);
    /**
     * retourne la {@link Card} à une position donnée sur la vue du {@link Terrain}
     * @param i ligne d'ajout
     * @param j colonne d'ajout
     * @return retourne une {@link Card} si il y'a belle et bien une Card et Null si
     * la position est vide
     */
    public Card getFieldposition(int i, int j);
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
    public void setFieldposition(Card card, int i, int j, int type);
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
    public boolean setPositionCard(Card card, int posi, int posj);
    /**
     * mettre à jour les positions libres de {@link Terrain}
     * c'est-à-dire les position où une {@link Card} est présente
     * @see Terrain#AmILink(int, int)
     */
    public void updateValuePossible();
    /**
     * mettre à jour les positions occupées de {@link Terrain}
     * c'est-à-dire les position où l'on peut ajouter une {@link Card} et qui est liée
     * @see Terrain#AmILink(int, int)
     */
    public void updateValueUsed();
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
     * cette classe a été spécialement conçu pour la {@link Strategy} {@link DifficultVirtualPlayer}
     * mais elle n'a pas encore été implémenté
     */
    public boolean canIBeSet(int posi, int posj, int typeSet, int vali, int valj, Card[][] card);
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
    public boolean canIBeSet(int posi, int posj, int typeSet, int vali, int valj);
    /**
     * permet de savoir si le {@link Terrain} est totalement occupé
     * c'est à dire les positions dont la définition de la forme admet 
     * ont toutes été occupées
     * @return {@link Boolean#TRUE} si le {@link Terrain} est bien occupée
     */
    public boolean isFull();
    /**
	 * méthode permettant donner false à la valeur de FirstCard si le {@link Terrain} contient déjà au moins
	 * une {@link Card} et typeSet vaut 1
	 * @param typeSet vaut toujours false
	 */
    public void setdirectlyFirstCard(int typeSet);
    /**
	 * méthode permettant renvoyant false si le {@link Terrain} contient déjà au moins
	 * une {@link Card} et typeSet vaut 1
	 * @param typeSet vaut toujours false
	 * @return un {@link Boolean}
	 */
    public boolean getdirectlyFirstCard(int typeSet);
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
    public boolean AmILink(int posi, int posj, int vali, int valj);
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
    public boolean AmILink(int posi, int posj, int vali, int valj, Card card[][]);
    /**
     * réinitialise la {@link Terrain} avec ses paramètre par défaut
     * elle détruit l'instance présent et recrèe un nouveau
     */
    public void  fieldInitialise();
    /**
     * déréférence l'instance de {@link Terrain} présent
     * elle fait pointer
     */
    public void fieldDestroy();
    /**
     * méthode permettant de savoir si l'emplacement sur le card dont
     * les positions sont passées en paramètres sont liées
     * @param posi indice ligne de l'emplacement sur le card
     * @param posj indice colonne de l'emplacement sur le card
     * @return {@link Boolean#TRUE} si l'emplacement passé en paramètre est liée à un emplacement occupé par une {@link Card}
     */
    public boolean AmILink(int posi, int posj);
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
    public boolean CanIAdd(int posi, int posj, int typeSet);
    /**
     * une description de l'état du {@link Terrain} en renvoyant
     * l'{@link Card#getIDCARD()} de la {@link Card} occupant de la position
     * @return les infos sur le {@link Terrain}
     */
    public String toString();
    /**
     * getter informant du nombre de colonne du {@link Terrain} à un moment donné
     * @return le nombre de colonne
     */
    public int getLarge();
    /**
     * getter informant du nombre de ligne du {@link Terrain} à un moment donné
     * @return le nombre de ligne
     */
    public int getLenght();
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
    /**
     * permet de savoir si le {@link Terrain} est totalement vide
     * c'est à dire les positions dont la définition de la forme admet 
     * ne sont occupées par aucune {@link Card}
     * @return {@link Boolean#TRUE} si le {@link Terrain} ne contient aucune emplacement occupée
     */
	public boolean isEmpty();
	/**
	 * transforme la vue du {@link Terrain} en une Matrice de {@link Card} 5*5
	 * fait correspondre chaque position occupé par une {@link Card} par 
	 * le même emplacement dans la matrice de card
	 * @return la matrice de {@link Card}
	 */
	public Card[][] toArray();
	/**
	 * renvoie un {@link ArrayList} des emplacements (i,j) occupés par une {@link Card} sur le {@link Terrain}
	 * @return un {@link HashMap} de {@link ArrayList} de {@link Integer}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> getValueUsed();
	/**
	 * renvoie un {@link ArrayList} des emplacements (i,j) libre une {@link Card} sur le {@link Terrain}
	 * @return un {@link HashMap} de {@link ArrayList} de {@link Integer}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> getValuePossible();
	/**
	 * prend une chaine de trois caractère et le premier est soit 'f' ou 'h'
	 * le deuxième est 'r' 'g' ou 'b' et le dernier 't' 'c' ou 's'
	 * et transforme cela en une {@link Card}
	 * @see Card#assignID(Filled, Color, Shape)
	 * @param str chaine de trois caractères
	 * @return {@link Card} qui a le même {@link Card#getIDCARD()} que le paramètre
	 */
	public Card defineCard(String str);
	/**
	 * vérifie si la {@link Card} donné en paramètre est présente sur
	 * le {@link Terrain}
	 * @param c une {@link Card} donc on veut savoir si il existe une {@link Card} sur le terrain semblable
	 * @return {@link Boolean#TRUE} s'il existe sur le terrain une Card au même {@link Card#getIDCARD()}
	 */
	public boolean hasCard(Card c);
	/**
	 * vérifie si l'emplacement passé en paramètre est vide
	 * elle est vide si {@link Card} vaut null à l'emplacement du {@link Terrain}
	 * @param i indice colonne de l'emplacement
	 * @param j indice ligne de l'emplacement
	 * @return {@link Boolean#TRUE} si l'emplacement est bel et bien vide
	 */
	public boolean hasNothing(int i, int j);
	/**
	 * Ajoute un Observeur dans la liste d'{@link Observer} de {@link Terrain} qui n'appartient pas déjà au {@link Terrain}
	 * @param o un {@link Observer} à ajouter
	 */
	public void addObservers(Observer o);
}
