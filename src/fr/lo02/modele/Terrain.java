package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observer;

/**
 * Cette interface d�finie les formes de Terrain
 * elle est dans la <b>version 1.0</b> la forme {@link RectangularField}, {@link CircularField}, {@link TriangularField}
 * @author Aubin Patrick TAKAM TAYO - Universit� de technologie de Troyes - R�seau et T�l�communication Semestre 01 - Automne 2020
 *@see RectangularField
 *@see CircularField
 *@see TriangularField
 */
public interface Terrain {
	/**
	 * cette methode dont le param�tre est toujours false permet de dire qu'on a
	 * d�j� 2 carte sur le {@link Terrain} et donc qu'on ne peut plus accepter
	 * toutes formes de d�placement
	 * @param directlyFirstCard vaut toujours false
	 */
	public void setDirectlyFirstCard(boolean directlyFirstCard);
	/**
	 * getters de l'aspect du terrain � un instant donn� :
	 * la disposition du Terrain
	 * @see RectangularField#fieldView
	 * @see CircularField#fieldView
	 * @return le fieldView du {@link Terrain}
	 */
	public ArrayList<ArrayList<Card>> getFieldView();
	/**
	 * Methode d'ajout de la carte sur le {@link Terrain}
	 * @param card : {@link Card} � ajouter
	 * @param posi : indice de la ligne d'ajout
	 * @param posj : indice de la colonne d'ajout
	 * @param typeSet : type d'ajout : elle vaut 1 => dans le cas d'un d�placement
	 *  et 1 => dans le cas d'un ajout simple {@link RealPlayer#jouerCard(int, int, int, Terrain)}
	 * @return un {@link Boolean} qui dit si l'ajout est r�ussi
	 * elle n'est pas r�ussi si la position d'ajout est occup�e ou n'est pas li�e � une autre
	 * position d�j� occup�e dans le cas o� il y'a d�j� au moins une Card sur le terrain 
	 * d'ou l'utilisation de typeSet si on d�place pas besoin de chercher s'il y'a une card pr�sente au
	 * premier tour
	 */
    public boolean addCard(Card card, int posi, int posj, int typeSet);
    /**
     * retourne la {@link Card} � une position donn�e sur la vue du {@link Terrain}
     * @param i ligne d'ajout
     * @param j colonne d'ajout
     * @return retourne une {@link Card} si il y'a belle et bien une Card et Null si
     * la position est vide
     */
    public Card getFieldposition(int i, int j);
    /**
     * setters des �l�ments du {@link Terrain}
     * elle modifie l'�l�ment � une position donn�e de la vue du {@link Terrain}
     * elle place une {@link Card}
     * @param card card � positionner � l'emplacement du {@link Terrain}
     * @param i indice-ligne de la Position
     * @param j indice-colonne de la Position
     * @param type permet de savoir si on a besoin de notifier au vue
     * de ce changement :: elle peut prendre 2 valeur : 1 pour l'approbation de la
     * notification et 0 pour l'opposition � la notification
     */
    public void setFieldposition(Card card, int i, int j, int type);
    /**
     * permet de changer la position d'une {@link Card} sur le {@link Terrain}
     * @param card est la {@link Card} � changer de position
     * @param posi est la position-ligne final d'ajout
     * @param posj est la position-colonne finale d'ajout
     * @return {@link Boolean#TRUE} si l'ajout est r�ussi
     * elle ne l'est pas dans le cas o� la Card � changer de place n'est pas sur le {@link Terrain} de {@link Card}
     * �galement lorsque la position finale est d�j� occup�e o� si la position finale est n'est pas lier
     * {@link Terrain#AmILink(int, int)}
     */
    public boolean setPositionCard(Card card, int posi, int posj);
    /**
     * mettre � jour les positions libres de {@link Terrain}
     * c'est-�-dire les position o� une {@link Card} est pr�sente
     * @see Terrain#AmILink(int, int)
     */
    public void updateValuePossible();
    /**
     * mettre � jour les positions occup�es de {@link Terrain}
     * c'est-�-dire les position o� l'on peut ajouter une {@link Card} et qui est li�e
     * @see Terrain#AmILink(int, int)
     */
    public void updateValueUsed();
    /**
     * v�rifie si une {@link Card} peut �tre d�plac� d'une position
     * c'est-�-dire qu'elle ne va pas caus� la cr�ation de deux Blocs de {@link Card} sur le tableau de {@link Card}
     * donn� en param�tre
     * @param posi la position ligne finale d'ajout
     * @param posj la position finale d'ajout
     * @param typeSet le type de d�placement r�sultant {@link Terrain#addCard(Card, int, int, int)}
     * @param vali la position ligne initiale de la {@link Card}
     * @param valj la position colonne initiale de la {@link Card}
     * @param card le tableau de {@link Card} sur lequel 
     * @return {@link Boolean#TRUE} si c'est bien possible de d�plac� la {@link Card}
     * cette classe a �t� sp�cialement con�u pour la {@link Strategy} {@link DifficultVirtualPlayer}
     * mais elle n'a pas encore �t� impl�ment�
     */
    public boolean canIBeSet(int posi, int posj, int typeSet, int vali, int valj, Card[][] card);
    /**
     * v�rifie si une {@link Card} peut �tre d�plac� d'une position
     * c'est-�-dire qu'elle ne va pas caus� la cr�ation de deux Blocs de {@link Card} sur le {@link Terrain}
     * donn� en param�tre
     * @param posi la position ligne finale d'ajout
     * @param posj la position finale d'ajout
     * @param typeSet le type de d�placement r�sultant {@link Terrain#addCard(Card, int, int, int)}
     * @param vali la position ligne initiale de la {@link Card}
     * @param valj la position colonne initiale de la {@link Card}
     * @return {@link Boolean#TRUE} si c'est bien possible de d�plac� la {@link Card}
     */
    public boolean canIBeSet(int posi, int posj, int typeSet, int vali, int valj);
    /**
     * permet de savoir si le {@link Terrain} est totalement occup�
     * c'est � dire les positions dont la d�finition de la forme admet 
     * ont toutes �t� occup�es
     * @return {@link Boolean#TRUE} si le {@link Terrain} est bien occup�e
     */
    public boolean isFull();
    /**
	 * m�thode permettant donner false � la valeur de FirstCard si le {@link Terrain} contient d�j� au moins
	 * une {@link Card} et typeSet vaut 1
	 * @param typeSet vaut toujours false
	 */
    public void setdirectlyFirstCard(int typeSet);
    /**
	 * m�thode permettant renvoyant false si le {@link Terrain} contient d�j� au moins
	 * une {@link Card} et typeSet vaut 1
	 * @param typeSet vaut toujours false
	 * @return un {@link Boolean}
	 */
    public boolean getdirectlyFirstCard(int typeSet);
    /**
     * m�thode permettant de savoir si l'emplacement sur le {@link Terrain} dont
     * les positions sont pass�es en param�tres sont li�es
     * @param posi indice ligne de l'emplacement sur le {@link Terrain}
     * @param posj indice colonne de l'emplacement sur le {@link Terrain}
     * @param vali valeur permettant d'�liminer un emplacement en ligne parmi ceux en param�tres
     * @param valj valeur permettant d'�liminer un emplacement en colonne parmi ceux en param�tres
     * @return {@link Boolean#TRUE} si l'emplacement pass� en param�tre est li�e � un emplacement occup� par une {@link Card}
     * hormis la {@link Card} � d�placer
     */
    public boolean AmILink(int posi, int posj, int vali, int valj);
    /**
     * m�thode permettant de savoir si l'emplacement sur le card dont
     * les positions sont pass�es en param�tres sont li�es
     * @param posi indice ligne de l'emplacement sur le card
     * @param posj indice colonne de l'emplacement sur le card
     * @param vali valeur permettant d'�liminer un emplacement en ligne parmi ceux en param�tres
     * @param valj valeur permettant d'�liminer un emplacement en colonne parmi ceux en param�tres
     * @param card vue {@link Terrain} sur lequel on �tudie la liaison
     * @return {@link Boolean#TRUE} si l'emplacement pass� en param�tre est li�e � un emplacement occup� par une {@link Card}
     * hormis la {@link Card} � d�placer
     */
    public boolean AmILink(int posi, int posj, int vali, int valj, Card card[][]);
    /**
     * r�initialise la {@link Terrain} avec ses param�tre par d�faut
     * elle d�truit l'instance pr�sent et recr�e un nouveau
     */
    public void  fieldInitialise();
    /**
     * d�r�f�rence l'instance de {@link Terrain} pr�sent
     * elle fait pointer
     */
    public void fieldDestroy();
    /**
     * m�thode permettant de savoir si l'emplacement sur le card dont
     * les positions sont pass�es en param�tres sont li�es
     * @param posi indice ligne de l'emplacement sur le card
     * @param posj indice colonne de l'emplacement sur le card
     * @return {@link Boolean#TRUE} si l'emplacement pass� en param�tre est li�e � un emplacement occup� par une {@link Card}
     */
    public boolean AmILink(int posi, int posj);
    /**
     * permet de savoir si une {@link Card} peut �tre ajouter � l'emplacement
     * donc les coordonn�es sont pass�es en param�tres
     * @param posi indice ligne de l'emplacement
     * @param posj indice colonne de l'emplacement
     * @param typeSet permet de savoir la raison de la v�rification d'ajout
     * 1 => d�placement
     * 0 => ajout
     * ceci car la possibilit� d'ajout et de d�placement lorsqu'il y'a juste une
     * seule carte sur le {@link Terrain} doit �tre possible dans le cas du d�placement
     * � n'importe qu'elle position
     * @return {@link Boolean#TRUE} si il est bel et bien possible d'ajouter une {@link Card}
     * � l'emplacement donn�
     */
    public boolean CanIAdd(int posi, int posj, int typeSet);
    /**
     * une description de l'�tat du {@link Terrain} en renvoyant
     * l'{@link Card#getIDCARD()} de la {@link Card} occupant de la position
     * @return les infos sur le {@link Terrain}
     */
    public String toString();
    /**
     * getter informant du nombre de colonne du {@link Terrain} � un moment donn�
     * @return le nombre de colonne
     */
    public int getLarge();
    /**
     * getter informant du nombre de ligne du {@link Terrain} � un moment donn�
     * @return le nombre de ligne
     */
    public int getLenght();
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
    /**
     * permet de savoir si le {@link Terrain} est totalement vide
     * c'est � dire les positions dont la d�finition de la forme admet 
     * ne sont occup�es par aucune {@link Card}
     * @return {@link Boolean#TRUE} si le {@link Terrain} ne contient aucune emplacement occup�e
     */
	public boolean isEmpty();
	/**
	 * transforme la vue du {@link Terrain} en une Matrice de {@link Card} 5*5
	 * fait correspondre chaque position occup� par une {@link Card} par 
	 * le m�me emplacement dans la matrice de card
	 * @return la matrice de {@link Card}
	 */
	public Card[][] toArray();
	/**
	 * renvoie un {@link ArrayList} des emplacements (i,j) occup�s par une {@link Card} sur le {@link Terrain}
	 * @return un {@link HashMap} de {@link ArrayList} de {@link Integer}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> getValueUsed();
	/**
	 * renvoie un {@link ArrayList} des emplacements (i,j) libre une {@link Card} sur le {@link Terrain}
	 * @return un {@link HashMap} de {@link ArrayList} de {@link Integer}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> getValuePossible();
	/**
	 * prend une chaine de trois caract�re et le premier est soit 'f' ou 'h'
	 * le deuxi�me est 'r' 'g' ou 'b' et le dernier 't' 'c' ou 's'
	 * et transforme cela en une {@link Card}
	 * @see Card#assignID(Filled, Color, Shape)
	 * @param str chaine de trois caract�res
	 * @return {@link Card} qui a le m�me {@link Card#getIDCARD()} que le param�tre
	 */
	public Card defineCard(String str);
	/**
	 * v�rifie si la {@link Card} donn� en param�tre est pr�sente sur
	 * le {@link Terrain}
	 * @param c une {@link Card} donc on veut savoir si il existe une {@link Card} sur le terrain semblable
	 * @return {@link Boolean#TRUE} s'il existe sur le terrain une Card au m�me {@link Card#getIDCARD()}
	 */
	public boolean hasCard(Card c);
	/**
	 * v�rifie si l'emplacement pass� en param�tre est vide
	 * elle est vide si {@link Card} vaut null � l'emplacement du {@link Terrain}
	 * @param i indice colonne de l'emplacement
	 * @param j indice ligne de l'emplacement
	 * @return {@link Boolean#TRUE} si l'emplacement est bel et bien vide
	 */
	public boolean hasNothing(int i, int j);
	/**
	 * Ajoute un Observeur dans la liste d'{@link Observer} de {@link Terrain} qui n'appartient pas d�j� au {@link Terrain}
	 * @param o un {@link Observer} � ajouter
	 */
	public void addObservers(Observer o);
}
