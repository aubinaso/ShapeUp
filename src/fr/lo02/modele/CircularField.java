package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Observable;
import java.util.Observer;

/**
 * Cette classe decrit un {@link Terrain} d'ajout de Carte
 * de forme Circulaire et de nombre d'emplacement de 13
 * 
 * cette classe implemente le Pattern Singleton , un processus ne peut donc instancier qu'un seul objet de ce type � la fois 
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01- Automne 2020 - LO02 
 * @see Terrain
 * @see RectangularField
 * @see TriangularField
 * @see Partie
 */
public class CircularField extends Observable implements Terrain, Visitable{
	/**
	 * L'instance du {@link CircularField} qui est entrain de se d�rouler
	 * <b>Ce programme n'admet qu'un seul {@link Terrain} � la fois par {@link Partie}
	 * La classe {@link CircularField} impl�mente le pattern Singleton</>
	 */
	private static CircularField field = null;
	/**
     * liste de toutes les valeurs possible d'ajour de {@link Card} en cours de {@link Partie}
     * qui est une position vide et li� ({@link Terrain#AmILink(int, int, int, int)})
     */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> valuepossible = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
	/**
     * ensemble de toutes les valeurs occup�es par une {@link Card} non nulles sur le {@link CircularField#fieldView}
     */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> valueused = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
	/**
	 * matrice de {@link Card} en 5*5
	 * en mode {@link CircularField}, l'ensemble des valeurs possibles
	 * ont leurs coordonn�es dans {@link CircularField#fieldposition}
	 */
	protected ArrayList<ArrayList<Card>> fieldView = new ArrayList<ArrayList<Card>>();
	/**
     * le nombre de ligne du {@link CircularField#fieldView} sur lequel on peut jouer
     * fixe pour le {@link Terrain} {@link CircularField}
     */
	protected int Lenght = 4;
	/**
     * le nombre de colonne du {@link CircularField#fieldView} sur lequel on peut jouer
     * fixe pour le {@link Terrain} {@link CircularField}
     */
	protected int large = 4;
	/**
     * permet de Savoir s'il y'a d�j� une {@link Card} sur le {@link RectangularField#fieldView} alors elle vaut {@link Boolean#TRUE} s'il n'y a aucune {@link Card}
     * ou s'il y'a une {@link Card} et que typeSet vaut 1 => l'action est le d�placement
     */
    protected boolean firstAdd = true;
    /**
     * permet de savoir s'il y'a d�j� une {@link Card} sur le {@link Terrain} et que c'est le Second {@link Player} de la {@link Partie}
     * qui est en train de Jouer alors s'il d�place en premier, il vaut {@link Boolean#TRUE} dans le cas contraire c'est {@link Boolean#FALSE}
     */
    protected boolean directlyFirstCard = true;
    /**
     * liste de Coordonn�es des emplacements possible en Mode {@link CircularField}
     * donc s'il faut jouer une {@link Card} � une position donn�e, on v�rifie d�j�
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
     * Constructeur : il initialise le {@link CircularField} avec les param�tres par d�faut
     */
    protected CircularField()
    {
    	//this.addObserver(InterfaceGraphiqueMode.getInstance().getPanelTerrain());
    	this.initializeFieldPosition();
    	for (int i = 0; i<5; i++)
        {
		   this.fieldView.add(new ArrayList<Card>());
            for(int j = 0; j<5; j++)
            {
            	this.fieldView.get(i).add(null);
                ArrayList<Integer> colonne1 = new ArrayList<Integer>();
                colonne1.clear();
                colonne1.add(i);
                colonne1.add(j);
                valuepossible.put(colonne1, colonne1);
            }
        }
    	this.setChanged();
		this.notifyObservers(Update.No);
    }
    /**
     * initialise les position du {@link Terrain}
     */
    public void initializeFieldPosition()
    {
    	ArrayList<Integer> colonne1;
    	this.fieldposition = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
    	for(int i=0; i<=4; i++)
    	{
    		if(i%4 == 0)
    		{
    			colonne1 = new ArrayList<Integer>();
    			colonne1.add(i);
    			colonne1.add(2);
    			this.fieldposition.put(colonne1, colonne1);
    		}
    		else if(i%4 == 2)
    		{
    			for(int j=0; j<=4; j++)
    			{
    				colonne1 = new ArrayList<Integer>();
    				colonne1.add(i);
    				colonne1.add(j);
    				this.fieldposition.put(colonne1, colonne1);
    			}
    		}
    		else
    		{
    			for(int j=1; j<=3; j++)
    			{
    				colonne1 = new ArrayList<Integer>();
    				colonne1.add(i);
    				colonne1.add(j);
    				this.fieldposition.put(colonne1, colonne1);
    			}
    		}
    	}
    }
    /**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visit�
     * @return les positions d'ajout possible
     */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> accept(FieldVisitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visit(this, 0);
	}
	/**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visit�
     * @return les positions occup� par une {@link Card} non null du Terrain
     */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> acceptU(FieldVisitor visitor) {
		// TODO Auto-generated method stub
		return visitor.visitU(this);
	}
	/**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visit�
     */

	
	public void acceptT(FieldVisitor visitor) {
		// TODO Auto-generated method stub
		visitor.visit(this);
	}
	/**
	 * cette methode dont le param�tre est toujours false permet de dire qu'on a
	 * d�j� 2 carte sur le {@link Terrain} et donc qu'on ne peut plus accepter
	 * toutes formes de d�placement
	 * @param directlyFirstCard vaut toujours false
	 */
	
	public void setDirectlyFirstCard(boolean directlyFirstCard) {
		// TODO Auto-generated method stub
		this.directlyFirstCard = directlyFirstCard;
	}
	/**
	 * getters de l'aspect du terrain � un instant donn� :
	 * la disposition du Terrain : {@link CircularField#fieldView}
	 * @return le fieldView {@link CircularField#fieldView}
	 */
	 public ArrayList<ArrayList<Card>> getFieldView()
	   {
		   return this.fieldView;
	   }
	 /**
		 * Methode d'ajout de la carte sur le {@link CircularField}
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
	public boolean addCard(Card card, int posi, int posj, int typeSet) {
		// TODO Auto-generated method stub
		posi--;
        posj--;
        ArrayList<Integer> colonne1 = new ArrayList<Integer>();
        colonne1.clear();
        colonne1.add(posi);
        colonne1.add(posj);
        if(this.CanIAdd(posi, posj, typeSet))
        {
        	this.setFieldposition(card, posi, posj, 1);
            this.updateValuePossible();
            this.setdirectlyFirstCard(typeSet);
            return true;
        }
        else
        {
            return false;
        }
	}
	 
	/**
	    * renvoie une instance de {@link CircularField} si il y en a une ou cr�e et renvoie une nouvelle
	    * instance de {@link CircularField} si aucun {@link Terrain} n'est r�f�renc�
	    * @return une r�f�rence sur un Objet de type {@link CircularField}
	    */
	
	public static CircularField getInstanceint()
	{
		if(CircularField.field  == null)
			field = new CircularField();
		return CircularField.field;
    }
	/**
     * retourne la {@link Card} � une position donn�e sur la vue du {@link CircularField#fieldView}
     * @param i ligne d'ajout
     * @param j colonne d'ajout
     * @return retourne une {@link Card} si il y'a belle et bien une Card et Null si
     * la position est vide
     */

	public Card getFieldposition(int i, int j)
    {
    	return this.fieldView.get(i).get(j);
    }

	/**
     * setters des �l�ments du {@link Terrain}
     * elle modifie l'�l�ment � une position donn�e de la vue du {@link CircularField#fieldView}
     * elle place une {@link Card}
     * @param card card � positionner � l'emplacement du {@link CircularField#fieldView}
     * @param i indice-ligne de la Position
     * @param j indice-colonne de la Position
     * @param type permet de savoir si on a besoin de notifier au vue
     * de ce changement :: elle peut prendre 2 valeur : 1 pour l'approbation de la
     * notification et 0 pour l'opposition � la notification
     */
	public void setFieldposition(Card card, int i, int j, int type)
    {
    	ArrayList<Card> col = this.fieldView.get(i);
    	col.set(j, card);
    	this.fieldView.set(i, col);
    	if(type == 1){
    		this.setChanged();
    		this.notifyObservers(Update.No);
    	}
    }

	/**
     * permet de changer la position d'une {@link Card} sur le {@link CircularField#fieldView}
     * @param card est la {@link Card} � changer de position
     * @param posi est la position-ligne final d'ajout
     * @param posj est la position-colonne finale d'ajout
     * @return {@link Boolean#TRUE} si l'ajout est r�ussi
     * elle ne l'est pas dans le cas o� la Card � changer de place n'est pas sur le {@link CircularField#fieldView} de {@link Card}
     * �galement lorsque la position finale est d�j� occup�e o� si la position finale est n'est pas lier
     * {@link Terrain#AmILink(int, int)}
     */
	public boolean setPositionCard(Card card, int posi, int posj) {
		// TODO Auto-generated method stub
		posi--;
    	posj--;
        ArrayList<Integer> colonne1 = new ArrayList<Integer>();
        boolean getIt = false;
        int posini = -1;
        int posjni = -1;
        if(!this.CanIAdd(posi, posj, 1))
            return false;
        for(int i = 0; i<= this.Lenght; i++)
        {//on recherche la carte
            for(int j = 0; j<=this.large; j++)
            {
            	if(this.getFieldposition(i, j) != null && card != null)
            	{
            		if(this.getFieldposition(i, j).equals(card) && this.canIBeSet(i, j, 1, posi, posj))
                    {
                        posini = i;
                        posjni =j;
                        colonne1.clear();
                        colonne1.add(i);
                        colonne1.add(j);
                        getIt = true;
                    }
            	}
            }
        }
        if(getIt)
        {//on d�place la carte
        	if(posi == posini && posj == posjni)//on le remet � la m�me place
        	{
        		return true;
        	}
            this.addCard(card, posi+1, posj+1, 1);
            this.setFieldposition(null, posini, posjni, 1);
            
            this.updateValuePossible();
            return true;
        }
        //on a pas trouv� la carte
        return false;
	}

	/**
     * mettre � jour les positions libres de {@link Terrain}
     * c'est-�-dire les position o� l'on peut ajouter une {@link Card} et qui est li�e
     * @see Terrain#AmILink(int, int)
     */
	public void updateValuePossible() {
		// TODO Auto-generated method stub
		if(this.isEmpty())
    	{
    		for (int i = 0; i<5; i++)
	        {
	            for(int j = 0; j<5; j++)
	            {
	            	this.setFieldposition(null, i, j, 0);
                    
	                ArrayList<Integer> colonne1 = new ArrayList<Integer>();
	                colonne1.clear();
	                colonne1.add(i);
	                colonne1.add(j);
	                if(this.fieldposition.containsKey(colonne1))
	                	valuepossible.put(colonne1, colonne1);
	            }

	        }
    		
    	}
    	else
    	{
	    	this.valuepossible.clear();
	    	boolean update = false; 
        	for(int i = 0; i<=this.Lenght; i++)
        	{
        		for(int j = 0; j<=this.large; j++)
        		{
        			ArrayList<Integer> colonne1 = new ArrayList<Integer>();
        			colonne1.add(i);
	                colonne1.add(j);
	                update = false;
        			if(this.getFieldposition(i, j) == null)
        			{
        				  	//pour voir si l'�l�ment posj-1 et posi-1 est li�
        					if(i-1>=0 && i-1 <=this.Lenght)
        					{
        						if(this.getFieldposition(i-1, j) != null)
            	                {
            	                	if(!this.valuepossible.containsValue(colonne1) && this.fieldposition.containsKey(colonne1));
            	                	{
            	                		this.valuepossible.put(colonne1, colonne1);
            	                		update = true;
            	                	}
            	                }
        					}
        					
        					//cas 2
        					if(i+1 >=0 && i+1 <=this.Lenght)
        					{
        						if(this.getFieldposition(i+1, j) != null)
            	                {
            	                	if(!this.valuepossible.containsValue(colonne1) && this.fieldposition.containsKey(colonne1));
            	                	{
            	                		this.valuepossible.put(colonne1, colonne1);
            	                		update = true;
            	                	}
            	                }
        					}
        					
        					//cas 3
        	                if(j-1>=0 && j-1<=this.large)
        	                {
        	                	if(this.getFieldposition(i, j-1) != null)
            	                {
            	                	if(!this.valuepossible.containsValue(colonne1) && this.fieldposition.containsKey(colonne1));
            	                	{
            	                		this.valuepossible.put(colonne1, colonne1);
            	                		update = true;
            	                	}
            	                }
        	                }
        	                
        	                //cas 4
        	                if(j+1>=0 && j+1<=this.large)
        	                {
        	                	if(this.getFieldposition(i, j+1) != null)
            	                {
            	                	if(!this.valuepossible.containsValue(colonne1) && this.fieldposition.containsKey(colonne1));
            	                	{
            	                		this.valuepossible.put(colonne1, colonne1);
            	                		update = true;
            	                	}
            	                }
        	                }
        	            }
        		}
        	}
    	}
	}

	/**
     * mettre � jour les positions libres de {@link CircularField#fieldView}
     * c'est-�-dire les position o� une {@link Card} est pr�sente
     * @see Terrain#AmILink(int, int)
     */
	public void updateValueUsed() {
		// TODO Auto-generated method stub
		this.valueused.clear();
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
    	for(int i = 0; i<=this.Lenght; i++)
    	{
    		for(int j = 0; j<=this.large; j++)
    		{
    			colonne1.clear();
    			colonne1.add(i);
                colonne1.add(j);
    			if(this.getFieldposition(i, j) != null)
    			{
    				this.valueused.put(colonne1, colonne1);
        			colonne1 = new ArrayList<Integer>();
    			}
    		}
    	}
	}

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
     */
	public boolean canIBeSet(int posi, int posj, int typeSet, int vali, int valj, Card[][] card) {
		// TODO Auto-generated method stub
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
        int value = 0;
        Card virtualField[][] = new Card[5][5];
        if(this.getdirectlyFirstCard(typeSet))
        	{
        		this.valuepossible.clear();
        		for (int i = 0; i<=this.Lenght; i++)
    	        {
    	            for(int j = 0; j<=this.large; j++)
    	            {
    	                ArrayList<Integer> colonne11 = new ArrayList<Integer>();
    	                colonne11.clear();
    	                colonne11.add(i);
    	                colonne11.add(j);
    	                if(this.fieldposition.containsKey(colonne11))
    	                	valuepossible.put(colonne1, colonne11);
    	            }
    	        }
        		return true;
        	}
        for (int i = 0; i<= this.Lenght; i++)
        {
        	for(int j=0; j<= this.large; j++)
        	{
        		virtualField[i][j] = card[i][j];
        	}
        }

        virtualField[posi][posj] = null;
        virtualField[vali][valj] = card[posi][posj];

        for(int i = 0; i<=this.Lenght; i++)
        {
        	for(int j = 0; j<=this.large; j++)
        	{
        		if(!this.AmILink(i, j, 0, 0, virtualField) && virtualField[i][j] != null)
        		{
        			return false;
        		}
        	}
        }
        return true;
	}

	/**
     * v�rifie si une {@link Card} peut �tre d�plac� d'une position
     * c'est-�-dire qu'elle ne va pas caus� la cr�ation de deux Blocs de {@link Card} sur le {@link RectangularField#fieldView}
     * donn� en param�tre
     * @param posi la position ligne finale d'ajout
     * @param posj la position finale d'ajout
     * @param typeSet le type de d�placement r�sultant {@link Terrain#addCard(Card, int, int, int)}
     * @param vali la position ligne initiale de la {@link Card}
     * @param valj la position colonne initiale de la {@link Card}
     * @return {@link Boolean#TRUE} si c'est bien possible de d�plac� la {@link Card}
     */
	public boolean canIBeSet(int posi, int posj, int typeSet, int vali, int valj) {
		// TODO Auto-generated method stub
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
        int value = 0;
        Card virtualField[][] = new Card[5][5];
        if(this.getdirectlyFirstCard(typeSet))
        {
        		this.valuepossible.clear();
        		for (int i = 0; i<=this.Lenght; i++)
    	        {
    	            for(int j = 0; j<=this.large; j++)
    	            {
    	                ArrayList<Integer> colonne11 = new ArrayList<Integer>();
    	                colonne11.clear();
    	                colonne11.add(i);
    	                colonne11.add(j);
    	                if(this.fieldposition.containsKey(colonne11))
    	                	valuepossible.put(colonne1, colonne11);
    	            }
    	        }
        		return true;
        }
        for (int i = 0; i<= this.Lenght; i++)
        {
        	for(int j=0; j<= this.large; j++)
        	{
        		virtualField[i][j] = this.getFieldposition(i, j);
        	}
        }
        virtualField[posi][posj] = null;
        virtualField[vali][valj] = this.getFieldposition(posi, posj);

        for(int i = 0; i<=this.Lenght; i++)
        {
        	for(int j = 0; j<=this.large; j++)
        	{
        		if(!this.AmILink(i, j, 0, 0, virtualField) && virtualField[i][j] != null)
        		{
        			return false;
        		}
        	}
        }
        return true;
	}

	/**
     * permet de savoir si le {@link RectangularField} est totalement occup�
     * c'est � dire les positions dont la d�finition de la forme admet 
     * ont toutes �t� occup�es
     * ici il s'agit des positions
     * (1,3)
     * (2,2) (2,3) (1,4)
     * (3,1) (3,2) (3,3) (3,4) (3,5)
     * (4,2) (4,3) (4,4)
     * (5,1)
     * @return {@link Boolean#TRUE} si le {@link Terrain} est bien occup�e
     */
	public boolean isFull() {
		// TODO Auto-generated method stub
		boolean toujours = true;
		Iterator<ArrayList<Integer>> it = this.fieldposition.values().iterator();
		while(it.hasNext())
		{
			if(!this.valueused.containsKey(it.next()))
				return false;
		}
    	return true;
	}

	/**
	 * m�thode permettant donner false � la valeur de {@link CircularField#firstAdd} si le {@link CircularField#fieldView} contient d�j� au moins
	 * une {@link Card} et typeSet vaut 1
	 * @param typeSet vaut toujours false
	 */
	public void setdirectlyFirstCard(int typeSet) {
		// TODO Auto-generated method stub
		int value = 0;
    	for(int i = 0; i<=this.Lenght; i++)
    	{
    		for(int j = 0; j<=this.large; j++)
    		{
    			if(this.getFieldposition(i, j) != null)
    			{
    				value++;
    			}
    		}
    	}
    	if(value>=2)
    		this.directlyFirstCard = false;
    	else if(value == 1 && typeSet == 1)
    		this.directlyFirstCard = true;
	}

	/**
	 * m�thode permettant renvoyant false si le {@link Terrain} contient d�j� au moins
	 * une {@link Card} et typeSet vaut 1
	 * @param typeSet vaut toujours false
	 * @return un {@link Boolean}
	 */
	public boolean getdirectlyFirstCard(int typeSet) {
		// TODO Auto-generated method stub
		int value = 0;
    	for(int i = 0; i<=this.Lenght; i++)
    	{
    		for(int j = 0; j<=this.large; j++)
    		{
    			if(this.getFieldposition(i, j) != null)
    			{
    				value++;
    			}
    		}
    	}
    	if(value>=2)
    		return false;
    	else if(value == 1 && typeSet == 1)
    		return true;
    	return false;
	}

	/**
     * m�thode permettant de savoir si l'emplacement sur le {@link CircularField#fieldView} dont
     * les positions sont pass�es en param�tres sont li�es
     * @param posi indice ligne de l'emplacement sur le {@link CircularField#fieldView}
     * @param posj indice colonne de l'emplacement sur le {@link CircularField#fieldView}
     * @param vali valeur permettant d'�liminer un emplacement en ligne parmi ceux en param�tres
     * @param valj valeur permettant d'�liminer un emplacement en colonne parmi ceux en param�tres
     * @return {@link Boolean#TRUE} si l'emplacement pass� en param�tre est li�e � un emplacement occup� par une {@link Card}
     * hormis la {@link Card} � d�placer
     */
	public boolean AmILink(int posi, int posj, int vali, int valj) {
		// TODO Auto-generated method stub
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
        if(this.directlyFirstCard)
        	return true;
        if(posi-1>=0 && posi-1<=this.Lenght && posj>=0 && posj<=this.large && vali != +1)//v�rifie que l'�l�ment n'est pas celui qu'on veut d�placer
        {
        	colonne1.clear();
            colonne1.add(posi-1);
            colonne1.add(posj);
            //pour voir si l'�l�ment posj-1 et posi-1 est li�
            if(this.fieldposition.containsKey(colonne1) && !this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi-1, posj) != null)
                return true;
        }
        if(posi+1>=0 && posi+1<=this.Lenght && posj>=0 && posj<=this.large && vali != -1)
        {
          	colonne1.clear();
            colonne1.add(posi+1);
            colonne1.add(posj);
            if(this.fieldposition.containsKey(colonne1) && !this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi+1, posj) != null)
                return true;
        }
        if(posi>=0 && posi<=this.Lenght && posj+1>=0 && posj+1<=this.large && valj != -1)
        {
           	colonne1.clear();
            colonne1.add(posi);
            colonne1.add(posj+1);
            if(this.fieldposition.containsKey(colonne1) && !this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi, posj+1) != null)
                return true;
        }
        if(posi>=0 && posi<=this.Lenght && posj-1>=0 && posj-1<=this.large && valj != +1)
        {
          	colonne1.clear();
            colonne1.add(posi);
            colonne1.add(posj-1);
            if(this.fieldposition.containsKey(colonne1) && !this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi, posj-1) != null)
                return true;
        }
        return false;
	}

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
	public boolean AmILink(int posi, int posj, int vali, int valj, Card[][] card) {
		// TODO Auto-generated method stub
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		if(posi-1>=0 && posi-1<=this.Lenght && posj>=0 && posj<=this.large)//v�rifie que l'�l�ment n'est pas celui qu'on veut d�placer
        {
            //pour voir si l'�l�ment posj-1 et posi-1 est li�
			colonne1.clear();
            colonne1.add(posi-1);
            colonne1.add(posj);
            if(card[posi-1][posj] != null && this.fieldposition.containsKey(colonne1))
                return true;
        }
        if(posi+1>=0 && posi+1<=this.Lenght && posj>=0 && posj<=this.large && vali != -1)
        {
        	colonne1.clear();
            colonne1.add(posi+1);
            colonne1.add(posj);
            if(card[posi+1][posj] != null && this.fieldposition.containsKey(colonne1))
                return true;
        }
        if(posi>=0 && posi<=this.Lenght && posj+1>=0 && posj+1<=this.large && valj != -1)
        {
        	colonne1.clear();
            colonne1.add(posi);
            colonne1.add(posj+1);
            if(card[posi][posj+1] != null && this.fieldposition.containsKey(colonne1))
                return true;
        }
        if(posi>=0 && posi<=this.Lenght && posj-1>=0 && posj-1<=this.large && valj != +1)
        {
        	colonne1.clear();
            colonne1.add(posi);
            colonne1.add(posj-1);
            if(card[posi][posj-1] != null && this.fieldposition.containsKey(colonne1))
                return true;
        }
        return false;
	}

	/**
     * r�initialise la {@link CircularField} avec ses param�tre par d�faut
     * elle d�truit l'instance pr�sent et recr�e un nouveau
     */
	public void fieldInitialise() {
		// TODO Auto-generated method stub
		this.fieldDestroy();
		field = new CircularField();
		for (int i = 0; i<5; i++)
        {
		   this.fieldView.add(new ArrayList<Card>());
            for(int j = 0; j<5; j++)
            {
            	this.fieldView.get(i).add(null);
                ArrayList<Integer> colonne1 = new ArrayList<Integer>();
                colonne1.clear();
                colonne1.add(i);
                colonne1.add(j);
                valuepossible.put(colonne1, colonne1);
            }

        }
	}

	/**
     * d�r�f�rence l'instance de {@link CircularField} pr�sent
     * elle fait pointer
     */
	public void fieldDestroy() {
		// TODO Auto-generated method stub
		CircularField.field = null;
		this.valuepossible = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
		this.valueused = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
		fieldView = new ArrayList<ArrayList<Card>>();
		this.large = 4;
		this.Lenght = 4;
		this.directlyFirstCard = true;
		this.firstAdd =true;
	}

	/**
     * m�thode permettant de savoir si l'emplacement sur le card dont
     * les positions sont pass�es en param�tres sont li�es
     * @param posi indice ligne de l'emplacement sur le card
     * @param posj indice colonne de l'emplacement sur le card
     * @return {@link Boolean#TRUE} si l'emplacement pass� en param�tre est li�e � un emplacement occup� par une {@link Card}
     */
	public boolean AmILink(int posi, int posj) {
		// TODO Auto-generated method stub
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
        if(this.directlyFirstCard)
        	return true;
        if(posi-1>=0 && posi-1<=4 && posj>=0 && posj<=4)//v�rifie que l'�l�ment n'est pas celui qu'on veut d�placer
        {
        	colonne1.clear();
            colonne1.add(posi-1);
            colonne1.add(posj);
            //pour voir si l'�l�ment posj-1 et posi-1 est li�
            if(this.fieldposition.containsKey(colonne1) && !this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi-1, posj) != null)
                return true;
        }
        if(posi+1>=0 && posi+1<=4 && posj>=0 && posj<=4)
        {
          	colonne1.clear();
            colonne1.add(posi+1);
            colonne1.add(posj);
            if(this.fieldposition.containsKey(colonne1) && !this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi+1, posj) != null)
                return true;
        }
        if(posi>=0 && posi<=4 && posj+1>=0 && posj+1<=4)
        {
           	colonne1.clear();
            colonne1.add(posi);
            colonne1.add(posj+1);
            if(this.fieldposition.containsKey(colonne1) && !this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi, posj+1) != null)
                return true;
        }
        if(posi>=0 && posi<=4 && posj-1>=0 && posj-1<=4)
        {
          	colonne1.clear();
            colonne1.add(posi);
            colonne1.add(posj-1);
            if(this.fieldposition.containsKey(colonne1) && !this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi, posj-1) != null)
                return true;
        }
        return false;
	}

	/**
     * permet de savoir si une {@link Card} peut �tre ajouter � l'emplacement
     * donc les coordonn�es sont pass�es en param�tres
     * @param posi indice ligne de l'emplacement
     * @param posj indice colonne de l'emplacement
     * @param typeSet permet de savoir la raison de la v�rification d'ajout
     * 1 => d�placement
     * 0 => ajout
     * ceci car la possibilit� d'ajout et de d�placement lorsqu'il y'a juste une
     * seule carte sur le {@link CircularField#fieldView} doit �tre possible dans le cas du d�placement
     * � n'importe qu'elle position
     * @return {@link Boolean#TRUE} si il est bel et bien possible d'ajouter une {@link Card}
     * � l'emplacement donn�
     */
	public boolean CanIAdd(int posi, int posj, int typeSet) {
		// TODO Auto-generated method stub
		if(!(posi>=0 && posi<=this.Lenght && posj>=0 && posj<=this.large) && typeSet != 2)//typeSet == 2 dans le cas des joueurs virtuels
            return false;
    	ArrayList<Integer> colonne1 = new ArrayList<Integer>();
    	this.updateValuePossible();
	    colonne1.add(posi);
        colonne1.add(posj);
        if(!this.fieldposition.containsKey(colonne1))
        	return false;
        if(firstAdd)
        {	
        	this.firstAdd = false;
            return true;
        }
        else if(this.getdirectlyFirstCard(typeSet))
    	{
    		return true;
    	}
        else if (this.valuepossible.containsValue(colonne1))
        {
           return true;
        }
        return false;
	}

	/**
     * getter informant du nombre de colonne du {@link CircularField} � un moment donn�
     * @return {@link CircularField#large}
     */
	public int getLarge() {
		// TODO Auto-generated method stub
		return this.large;
	}

	/**
     * getter informant du nombre de ligne du {@link CircularField} � un moment donn�
     * @return {@link CircularField#Lenght}
     */
	public int getLenght() {
		// TODO Auto-generated method stub
		return this.Lenght;
	}

	
	/**
     * permet de savoir si le {@link CircularField#fieldView} est totalement vide
     * c'est � dire les positions dont la d�finition de la forme admet 
     * ne sont occup�es par aucune {@link Card}
     * @see CircularField#fieldView
     * @return {@link Boolean#TRUE} si le {@link CircularField#fieldView} ne contient aucune emplacement occup�e
     */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		Card [][] fieldview = this.toArray();
		int value=0;
    	for(int i = 0; i<=this.Lenght; i++)
    	{
    		for(int j = 0; j<=this.large; j++)
    		{
    			if(fieldview[i][j] != null)
    			{
    				value++;
    			}
    		}
    	}
    	if(value==0)
    		return true;
    	return false;
	}
	
	/**
     * une description de l'�tat du {@link Terrain} en renvoyant
     * l'{@link Card#getIDCARD()} de la {@link Card} occupant de la position
     * @return les infos sur le {@link CircularField#fieldView}
     */
	public String toString()
    {
		Card[][] fieldview = this.toArray();
		ArrayList<Integer> colonne = new ArrayList<Integer>();
    	StringBuffer sd = new StringBuffer();
    	sd.append("je suis le terrain  et les cartes sont comme suis : ");
    	for (int i = 0; i <= this.Lenght; i++)
    	{
    		sd.append("\n");
    		for (int j=0; j<=this.large; j++)
    		{
    			colonne.clear();
    			colonne.add(i);
    			colonne.add(j);
    			if(!this.fieldposition.containsKey(colonne))
    				sd.append("\t   ");
    			else if(this.getFieldposition(i, j) == null)
    				sd.append("\txxx");
    			else
    				sd.append("\t" + this.getFieldposition(i, j).getIDCARD());
    		}
    	}
    	return sd.toString();
    }
	
	
	/**
	 * transforme la vue du {@link CircularField#fieldView} en une Matrice de {@link Card} 5*5
	 * fait correspondre chaque position occup� par une {@link Card} par 
	 * le m�me emplacement dans la matrice de card
	 * @return la matrice de {@link Card}
	 */
	public Card[][] toArray() {
		// TODO Auto-generated method stub
		Card card[][] = new Card[5][5];
    	for(int i=0; i<=this.Lenght; i++)
    	{
    		for(int j = 0; j<=this.large; j++)
    		{
    			card[i][j] = this.getFieldposition(i, j);
    		}
    	}
    	return card;
	}
	
	/**
	 * prend une chaine de trois caract�re et le premier est soit 'f' ou 'h'
	 * le deuxi�me est 'r' 'g' ou 'b' et le dernier 't' 'c' ou 's'
	 * et transforme cela en une {@link Card}
	 * @see Card#assignID(Filled, Color, Shape)
	 * @param str chaine de trois caract�res
	 * @return {@link Card} qui a le m�me {@link Card#getIDCARD()} que le param�tre
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
	 * v�rifie si la {@link Card} donn� en param�tre est pr�sente sur
	 * le {@link CircularField#fieldView}
	 * @param c une {@link Card} donc on veut savoir si il existe une {@link Card} sur le terrain semblable
	 * @return {@link Boolean#TRUE} s'il existe sur le terrain une Card au m�me {@link Card#getIDCARD()}
	 */
	public boolean hasCard(Card c) {
		// TODO Auto-generated method stub
		boolean getIt = false;
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();;
		for(int i = 0; i<= this.Lenght; i++)
        {//on recherche la carte
            for(int j = 0; j<=this.large; j++)
            {
            	if(this.fieldView.get(i).get(j) != null)
            	{
            		if(this.fieldView.get(i).get(j).equals(c))
                    {
                        colonne1.clear();
                        colonne1.add(i);
                        colonne1.add(j);
                        getIt = true;
                    }
            	}
            }
        }
		return getIt;
	}


	/**
	 * v�rifie si l'emplacement pass� en param�tre est vide
	 * elle est vide si {@link Card} vaut null � l'emplacement du {@link CircularField#fieldView}
	 * @param i indice colonne de l'emplacement
	 * @param j indice ligne de l'emplacement
	 * @return {@link Boolean#TRUE} si l'emplacement est bel et bien vide
	 */
	public boolean hasNothing(int i, int j) {
		// TODO Auto-generated method stub
		if(this.fieldView.get(i).get(j) == null)
			return true;
		return false;
	}
	/**
	 * renvoie un {@link ArrayList} des emplacements (i,j) occup�s par une {@link Card} sur le {@link Terrain}
	 * @return un {@link HashMap} de {@link ArrayList} de {@link Integer}
	 */
	

	
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> getValueUsed() {
		// TODO Auto-generated method stub
		return this.valueused;
	}
	/**
	 * renvoie un {@link ArrayList} des emplacements (i,j) libre une {@link Card} sur le {@link CircularField#fieldView}
	 * @return un {@link HashMap} de {@link ArrayList} de {@link Integer}
	 */

	
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> getValuePossible() {
		// TODO Auto-generated method stub
		return this.valuepossible;
	}
	/**
	 * Ajoute un Observeur dans la liste d'{@link Observer} de {@link Terrain} qui n'appartient pas d�j� au {@link Terrain}
	 * @param o un {@link Observer} � ajouter
	 */
	public void addObservers(Observer o) {
		// TODO Auto-generated method stub
		this.addObserver(o);
	}

}
