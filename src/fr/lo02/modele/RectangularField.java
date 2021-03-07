package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
//import java.util.LinkedList;
//import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Cette classe decrit un {@link Terrain} d'ajout de Carte
 * de forme Rectangulaire et de nombre d'emplacement de 15
 * 
 * cette classe implemente le Pattern Singleton , un processus ne peut donc instancier qu'un seul objet de ce type à la fois 
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see Terrain
 * @see TriangularField
 * @see CircularField
 * @see Partie
 */
public class RectangularField extends Observable implements Visitable, Terrain /*Observabe*/{
	 	
		/**
		 * L'instance du {@link RectangularField} qui est entrain de se dérouler
		 * <b>Ce programme n'admet qu'un seul {@link Terrain} à la fois par {@link Partie}
		 * La classe {@link RectangularField} implémente le pattern Singleton</>
		 */
		protected static RectangularField field = null;
		/**
		 * matrice de {@link Card} en 5*5
		 * en mode {@link RectangularField}
		 * les valeurs Possibles de positionnement est soit en 3*5 ou 5*3
		 */
        protected ArrayList<ArrayList<Card>> fieldView = new ArrayList<ArrayList<Card>>();
        /**
         * le nombre de ligne du {@link RectangularField#fieldView} sur lequel on peut jouer
         * elle vérifie {@link RectangularField#Lenght} + {@link RectangularField#large} = 8
         * à la fin de {@link Partie}
         */
        protected int Lenght = 2;
        /**
         * le nombre de colonne du {@link RectangularField#fieldView} sur lequel on peut jouer
         * elle vérifie {@link RectangularField#Lenght} + {@link RectangularField#large} = 8
         * à la fin de {@link Partie}
         */
        protected int large = 2;
        /**
         * liste de toutes les valeurs possible d'ajour de {@link Card} en cours de {@link Partie}
         * qui est une position vide et lié ({@link Terrain#AmILink(int, int, int, int)})
         */
        protected HashMap<ArrayList<Integer>, ArrayList<Integer>> valuepossible = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
        /**
         * permet de Savoir s'il y'a déjà une {@link Card} sur le {@link RectangularField#fieldView} alors elle vaut {@link Boolean#TRUE} s'il n'y a aucune {@link Card}
         * ou s'il y'a une {@link Card} et que typeSet vaut 1 => l'action est le déplacement
         */
        protected boolean firstAdd = true;
        /**
         * permet de savoir s'il y'a déjà une {@link Card} sur le {@link Terrain} et que c'est le Second {@link Player} de la {@link Partie}
         * qui est en train de Jouer alors s'il déplace en premier, il vaut {@link Boolean#TRUE} dans le cas contraire c'est {@link Boolean#FALSE}
         */
        protected boolean directlyFirstCard = true;
       /**
        * ensemble de toutes les valeurs occupées par une {@link Card} non nulles sur le {@link RectangularField#fieldView}
        */
        protected HashMap<ArrayList<Integer>, ArrayList<Integer>> valueused = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();

        
        
       /**
        * Constructeur : il initialise le {@link RectangularField} avec les paramètres par défaut
        */
	   protected RectangularField()
	   {
		   //this.addObserver(InterfaceGraphiqueMode.getInstance().getPanelTerrain());
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
		   //Observer
		   this.setChanged();
		   this.notifyObservers(Update.No);
		   //Observer
	   }
	   /**
		 * cette methode dont le paramètre est toujours false permet de dire qu'on a
		 * déjà 2 carte sur le {@link Terrain} et donc qu'on ne peut plus accepter
		 * toutes formes de déplacement
		 * @param directlyFirstCard vaut toujours false
		 */
	   public void setDirectlyFirstCard(boolean directlyFirstCard) {
			this.directlyFirstCard = directlyFirstCard;
		}
	   /**
		 * getters de l'aspect du terrain à un instant donné :
		 * la disposition du Terrain : {@link RectangularField#fieldView}
		 * @return le fieldView {@link RectangularField#fieldView}
		 */
	   public ArrayList<ArrayList<Card>> getFieldView()
	   {
		   return this.fieldView;
	   }
	   /**
	    * renvoie une instance de {@link RectangularField} si il y en a une ou crèe et renvoie une nouvelle
	    * instance de {@link RectangularField} si aucun {@link Terrain} n'est référencé
	    * @return une référence sur un Objet de type {@link RectangularField}
	    */
	   	public static RectangularField getInstanceint()
		{
			if(RectangularField.field == null)
				field = new RectangularField();
			return RectangularField.field;
	    }
	   	/**
		 * Methode d'ajout de la carte sur le {@link RectangularField}
		 * @param card : {@link Card} à ajouter
		 * @param posi : indice de la ligne d'ajout [1 : {@link RectangularField#Lenght} + 1]
		 * @param posj : indice de la colonne d'ajout [1 : {@link RectangularField#large}+1]
		 * @param typeSet : type d'ajout : elle vaut 1 => dans le cas d'un déplacement
		 *  et 1 => dans le cas d'un ajout simple {@link RealPlayer#jouerCard(int, int, int, Terrain)}
		 * @return un {@link Boolean} qui dit si l'ajout est réussi
		 * elle n'est pas réussi si la position d'ajout est occupée ou n'est pas liée à une autre
		 * position déjà occupée dans le cas où il y'a déjà au moins une Card sur le terrain 
		 * d'ou l'utilisation de typeSet si on déplace pas besoin de chercher s'il y'a une card présente au
	 	* premier tour
		 */
	    public boolean addCard(Card card, int posi, int posj, int typeSet)
	    {
	        posi--;
	        posj--;
	        ArrayList<Integer> colonne1 = new ArrayList<Integer>();
	        colonne1.clear();
	        colonne1.add(posi);
            colonne1.add(posj);
            if(this.getCanIchangeDimension(typeSet))
            {
            	if(posi >2 && posj <= 2)
            	{
            		this.Lenght = 4;
            		this.large = 2;
            		this.updateValuePossible();
            	}
            	else if(posi <= 2 && posj > 2)
            	{
            		this.large = 4;
            		this.Lenght = 2;
            		this.updateValuePossible();
            	}
            }
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
	     * retourne la {@link Card} à une position donnée sur la vue du {@link RectangularField#fieldView}
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
	     * setters des éléments du {@link Terrain}
	     * elle modifie l'élément à une position donnée de la vue du {@link RectangularField#fieldView}
	     * elle place une {@link Card}
	     * @param card card à positionner à l'emplacement du {@link RectangularField#fieldView}
	     * @param i indice-ligne de la Position
	     * @param j indice-colonne de la Position
	     * @param type permet de savoir si on a besoin de notifier au vue
	     * de ce changement :: elle peut prendre 2 valeur : 1 pour l'approbation de la
	     * notification et 0 pour l'opposition à la notification
	     */
	    public void setFieldposition(Card card, int i, int j, int type)
	    {
	    	ArrayList<Card> col = this.fieldView.get(i);
        	col.set(j, card);
        	this.fieldView.set(i, col);
        	if(type == 1) {
	        	this.setChanged();
	 		   	this.notifyObservers(Update.No);
        	}
	    }
	    
	    /**
	     * permet de savoir si je suis au deuxième tour et que je déplace en premier
	     * @param typeSet défini si c'est un déplacement ou un ajout
	     * @return un {@link Boolean}
	     */
	    public boolean getCanIchangeDimension(int typeSet)
	    {
	    	int value = 0;
	    	for(int i = 0; i<=this.Lenght; i++)
        	{
        		for(int j = 0; j<=this.large; j++)
        		{
        			if(this.fieldView.get(i).get(j) != null && !(i<3 && j<3))
        			{
        				value++;
        			}
        		}
        	}
	    	if(value == 1 && typeSet == 1)
	    		return true;
	    	else if(value == 1 && typeSet == 0 && (this.Lenght >2 || this.large > 2))
	    		return false;
	    	else if(value == 0)
	    		return true;
	    	else
	    	{
	    		boolean toujours = true;
	    		for(int i = 0; i<4; i++)
	    		{
	    			for(int j = 0; j<4; j++)
	    			{
	    				if(this.fieldView.get(i).get(j) != null && (i>2 || j>2))
	    					toujours = false;
	    			}
	    		}
	    		return toujours;
	    	}
	    	//return false;
	    	//if(this.getdirectlyFirstCard(typeSet))
	    	//	this.canIchangeDimension = true;
	    	//else
	    }
	    
	    /**
	     * permet de changer la position d'une {@link Card} sur le {@link RectangularField#fieldView}
	     * @param card est la {@link Card} à changer de position
	     * @param posi est la position-ligne final d'ajout
	     * @param posj est la position-colonne finale d'ajout
	     * @return {@link Boolean#TRUE} si l'ajout est réussi
	     * elle ne l'est pas dans le cas où la Card à changer de place n'est pas sur le {@link RectangularField#fieldView} de {@link Card}
	     * également lorsque la position finale est déjà occupée où si la position finale est n'est pas lier
	     * {@link Terrain#AmILink(int, int)}
	     */
	    public boolean setPositionCard(Card card, int posi, int posj)
        {
        	
        	posi--;
        	posj--;
            ArrayList<Integer> colonne1 = new ArrayList<Integer>();
            boolean getIt = false;
            int posini = -1;
            int posjni = -1;
            if(this.large > this.Lenght)
            {
            	if(posi > 2 && this.large > 2)
            	{
            		Card[][] fieldview = this.toArray();
            		if(this.AmILink(posi, posj, 0, 0, fieldview))
            		{	
            			int value = 0, iniI = 0, iniJ = 0;
            			for(int i=0; i <=4; i++)
            			{
            				for(int j=0; j<=4; j++)
            				{
            					if(this.fieldView.get(i).get(j) != null && (i>2 || j>2))
            					{
            						iniI = i;
            						iniJ = j;
            						value++;
            					}
            				}
            			}
            			if(value == 1 && this.fieldView.get(iniI).get(iniJ).equals(card) && this.fieldView.get(posi).get(posj) == null)
            			{
            				this.setFieldposition(this.getFieldposition(iniI, iniJ), posi, posj, 1);
            				this.setFieldposition(null, iniI, iniJ, 1);
            				this.large = 2;
            				this.Lenght = 4;
            				return true;
            			}
            		}
            	}
            }
            else if(this.Lenght > this.large)
            {
            	if(posj > 2 && this.Lenght > 2)
            	{
            		Card[][] fieldview = this.toArray();
            		if(this.AmILink(posi, posj, 0, 0, fieldview))
            		{
            			int value = 0, iniI = 0, iniJ = 0;
            			for(int i=0; i <=4; i++)
            			{
            				for(int j=0; j<=4; j++)
            				{
            					if(this.fieldView.get(i).get(j) != null && (i>2 || j>2))
            					{
            						iniI = i;
            						iniJ = j;
            						value++;
            					}
            				}
            			}
            			if(value == 1 && this.fieldView.get(iniI).get(iniJ).equals(card) && this.fieldView.get(posi).get(posj) == null)
            			{
            				this.setFieldposition(this.fieldView.get(iniI).get(iniJ), posi, posj, 1);
            				this.setFieldposition(null, iniI, iniJ, 1);
            				this.large = 4;
            				this.Lenght = 2;
            				return true;
            			}
            		}
            	}
            }
            if(!this.CanIAdd(posi, posj, 1) || getIt)
            {
                return false;
            }
            getIt = false;
            for(int i = 0; i<= this.Lenght; i++)
            {//on recherche la carte
                for(int j = 0; j<=this.large; j++)
                {
                	if(this.fieldView.get(i).get(j) != null && card != null)
                	{
                		if(this.fieldView.get(i).get(j).equals(card) && this.canIBeSet(i, j, 1, posi, posj))
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
            {//on déplace la carte
            	if(posi == posini && posj == posjni)//on le remet à la même place
            	{
            		return true;
            	}
                this.addCard(card, posi+1, posj+1, 1);
                this.setFieldposition(null, posini, posjni, 1);
                this.updateValuePossible();
                return true;
            }
            //on a pas trouvé la carte
            return false;
        }
	    /**
	     * mettre à jour les positions libres de {@link Terrain}
	     * c'est-à-dire les position où l'on peut ajouter une {@link Card} et qui est liée
	     * @see Terrain#AmILink(int, int)
	     */       
        public void updateValuePossible()
        {
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
	        				  	//pour voir si l'élément posj-1 et posi-1 est lié
	        					if(i-1>=0 && i-1 <=this.Lenght)
	        					{
	        						if(this.getFieldposition(i-1, j) != null)
	            	                {
	            	                	if(!this.valuepossible.containsValue(colonne1));
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
	            	                	if(!this.valuepossible.containsValue(colonne1));
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
	            	                	if(!this.valuepossible.containsValue(colonne1));
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
	            	                	if(!this.valuepossible.containsValue(colonne1));
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
         * mettre à jour les positions libres de {@link RectangularField#fieldView}
         * c'est-à-dire les position où une {@link Card} est présente
         * @see Terrain#AmILink(int, int)
         */
        public void updateValueUsed()
        {
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
        public boolean canIBeSet(int posi, int posj, int typeSet, int vali, int valj, Card[][] card)
        {
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
         * vérifie si une {@link Card} peut être déplacé d'une position
         * c'est-à-dire qu'elle ne va pas causé la création de deux Blocs de {@link Card} sur le {@link RectangularField#fieldView}
         * donné en paramètre
         * @param posi la position ligne finale d'ajout
         * @param posj la position finale d'ajout
         * @param typeSet le type de déplacement résultant {@link Terrain#addCard(Card, int, int, int)}
         * @param vali la position ligne initiale de la {@link Card}
         * @param valj la position colonne initiale de la {@link Card}
         * @return {@link Boolean#TRUE} si c'est bien possible de déplacé la {@link Card}
         */
        public boolean canIBeSet(int posi, int posj, int typeSet, int vali, int valj)
        {
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
         * permet de savoir si le {@link RectangularField} est totalement occupé
         * c'est à dire les positions dont la définition de la forme admet 
         * ont toutes été occupées
         * ici il s'adit des positions allant de :
         * [1, 3]*[1, 5] ou [1, 5]*[1, 3]
         * @return {@link Boolean#TRUE} si le {@link Terrain} est bien occupée
         */
        public boolean isFull()
        {
        	if(this.valuepossible.isEmpty() && (this.large > 2 || this.Lenght > 2))
        		return true;
        	return false;
        }
        /**
         * permet de savoir si le {@link RectangularField#fieldView} est totalement vide
         * c'est à dire les positions dont la définition de la forme admet 
         * ne sont occupées par aucune {@link Card}
         * @see RectangularField#fieldView
         * @return {@link Boolean#TRUE} si le {@link RectangularField#fieldView} ne contient aucune emplacement occupée
         */
        public boolean isEmpty()
        {
        	int value=0;
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
        	if(value==0)
        		return true;
        	return false;
        }
        /**
    	 * méthode permettant donner false à la valeur de {@link RectangularField#firstAdd} si le {@link RectangularField#fieldView} contient déjà au moins
    	 * une {@link Card} et typeSet vaut 1
    	 * @param typeSet vaut toujours false
    	 */
        public void setdirectlyFirstCard(int typeSet)
        {
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
    	 * méthode permettant renvoyant false si le {@link Terrain} contient déjà au moins
    	 * une {@link Card} et typeSet vaut 1
    	 * @param typeSet vaut toujours false
    	 */
        public boolean getdirectlyFirstCard(int typeSet)
        {
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
         * méthode permettant de savoir si l'emplacement sur le {@link RectangularField#fieldView} dont
         * les positions sont passées en paramètres sont liées
         * @param posi indice ligne de l'emplacement sur le {@link RectangularField#fieldView}
         * @param posj indice colonne de l'emplacement sur le {@link RectangularField#fieldView}
         * @param vali valeur permettant d'éliminer un emplacement en ligne parmi ceux en paramètres
         * @param valj valeur permettant d'éliminer un emplacement en colonne parmi ceux en paramètres
         * @return {@link Boolean#TRUE} si l'emplacement passé en paramètre est liée à un emplacement occupé par une {@link Card}
         * hormis la {@link Card} à déplacer
         */
        public boolean AmILink(int posi, int posj, int vali, int valj)
        {
            ArrayList<Integer> colonne1 = new ArrayList<Integer>();
            if(this.directlyFirstCard)
            	return true;
            if(posi-1>=0 && posi-1<=this.Lenght && posj>=0 && posj<=this.large && vali != +1)//vérifie que l'élément n'est pas celui qu'on veut déplacer
            {
            	colonne1.clear();
                colonne1.add(posi-1);
                colonne1.add(posj);
                //pour voir si l'élément posj-1 et posi-1 est lié
                if(!this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi-1, posj) != null)
                    return true;
            }
            if(posi+1>=0 && posi+1<=this.Lenght && posj>=0 && posj<=this.large && vali != -1)
            {
              	colonne1.clear();
                colonne1.add(posi+1);
                colonne1.add(posj);
                if(!this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi+1, posj) != null)
                    return true;
            }
            if(posi>=0 && posi<=this.Lenght && posj+1>=0 && posj+1<=this.large && valj != -1)
            {
               	colonne1.clear();
                colonne1.add(posi);
                colonne1.add(posj+1);
                if(!this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi, posj+1) != null)
                    return true;
            }
            if(posi>=0 && posi<=this.Lenght && posj-1>=0 && posj-1<=this.large && valj != +1)
            {
              	colonne1.clear();
                colonne1.add(posi);
                colonne1.add(posj-1);
                if(!this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi, posj-1) != null)
                    return true;
            }
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
        public boolean AmILink(int posi, int posj, int vali, int valj, Card card[][])
        {
            if(posi-1>=0 && posi-1<=this.Lenght && posj>=0 && posj<=this.large)//vérifie que l'élément n'est pas celui qu'on veut déplacer
            {
                //pour voir si l'élément posj-1 et posi-1 est lié
                if(card[posi-1][posj] != null)
                    return true;
            }
            if(posi+1>=0 && posi+1<=this.Lenght && posj>=0 && posj<=this.large && vali != -1)
            {
                if(card[posi+1][posj] != null)
                    return true;
            }
            if(posi>=0 && posi<=this.Lenght && posj+1>=0 && posj+1<=this.large && valj != -1)
            {
                if(card[posi][posj+1] != null)
                    return true;
            }
            if(posi>=0 && posi<=this.Lenght && posj-1>=0 && posj-1<=this.large && valj != +1)
            {
                if(card[posi][posj-1] != null)
                    return true;
            }
            return false;
        }
        /**
         * getter informant du nombre de ligne du {@link RectangularField} à un moment donné
         * @return {@link RectangularField#large}
         */
        public int getLenght()
        {
        	return this.Lenght;
        }
        /**
         * getter informant du nombre de colonne du {@link RectangularField} à un moment donné
         * @return {@link RectangularField#large}
         */
        public int getLarge()
        {
        	return this.large;
        }
        /**
         * réinitialise la {@link RectangularField} avec ses paramètre par défaut
         * elle détruit l'instance présent et recrèe un nouveau
         */
        public void  fieldInitialise()
    	{
    		this.fieldDestroy();
    		field = new RectangularField();
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
         * déréférence l'instance de {@link RectangularField} présent
         * elle fait pointer
         */
        public void fieldDestroy()
    	{
    		RectangularField.field = null;
    		fieldView = new ArrayList<ArrayList<Card>>();
    		this.large = 2;
    		this.Lenght = 2;
    		this.directlyFirstCard = true;
    		this.firstAdd =true;
    	}
        /**
         * méthode permettant de savoir si l'emplacement sur le card dont
         * les positions sont passées en paramètres sont liées
         * @param posi indice ligne de l'emplacement sur le card
         * @param posj indice colonne de l'emplacement sur le card
         * @return {@link Boolean#TRUE} si l'emplacement passé en paramètre est liée à un emplacement occupé par une {@link Card}
         */
        public boolean AmILink(int posi, int posj)
        {
            ArrayList<Integer> colonne1 = new ArrayList<Integer>();
            if(this.directlyFirstCard)
            	return true;
            if(posi-1>=0 && posi-1<=4 && posj>=0 && posj<=4)//vérifie que l'élément n'est pas celui qu'on veut déplacer
            {
            	colonne1.clear();
                colonne1.add(posi-1);
                colonne1.add(posj);
                //pour voir si l'élément posj-1 et posi-1 est lié
                if(!this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi-1, posj) != null)
                    return true;
            }
            if(posi+1>=0 && posi+1<=4 && posj>=0 && posj<=4)
            {
              	colonne1.clear();
                colonne1.add(posi+1);
                colonne1.add(posj);
                if(!this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi+1, posj) != null)
                    return true;
            }
            if(posi>=0 && posi<=4 && posj+1>=0 && posj+1<=4)
            {
               	colonne1.clear();
                colonne1.add(posi);
                colonne1.add(posj+1);
                if(!this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi, posj+1) != null)
                    return true;
            }
            if(posi>=0 && posi<=4 && posj-1>=0 && posj-1<=4)
            {
              	colonne1.clear();
                colonne1.add(posi);
                colonne1.add(posj-1);
                if(!this.valuepossible.containsValue(colonne1) && this.getFieldposition(posi, posj-1) != null)
                    return true;
            }
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
         * seule carte sur le {@link RectangularField#fieldView} doit être possible dans le cas du déplacement
         * à n'importe qu'elle position
         * @return {@link Boolean#TRUE} si il est bel et bien possible d'ajouter une {@link Card}
         * à l'emplacement donné
         */
	    public boolean CanIAdd(int posi, int posj, int typeSet)
	    {
            if(!(posi>=0 && posi<=this.Lenght && posj>=0 && posj<=this.large) && typeSet != 2)//typeSet == 2 dans le cas des joueurs virtuels
                return false;
	    	ArrayList<Integer> colonne1 = new ArrayList<Integer>();
	    	this.updateValuePossible();
		    colonne1.add(posi);
            colonne1.add(posj);
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
		 * transforme la vue du {@link RectangularField#fieldView} en une Matrice de {@link Card} 5*5
		 * fait correspondre chaque position occupé par une {@link Card} par 
		 * le même emplacement dans la matrice de card
		 * @return la matrice de {@link Card}
		 */
	    public Card[][] toArray()
	    {
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
	     * une description de l'état du {@link Terrain} en renvoyant
	     * l'{@link Card#getIDCARD()} de la {@link Card} occupant de la position
	     * @return les infos sur le {@link RectangularField#fieldView}
	     */
	    public String toString()
	    {
	    	Card[][] card = this.toArray(); 
	    	StringBuffer sd = new StringBuffer();
	    	sd.append("je suis le terrain  et les cartes sont comme suis : ");
	    	for (int i = 0; i <= this.Lenght; i++)
	    	{
	    		sd.append("\n");
	    		for (int j=0; j<=this.large; j++)
	    		{
	    			if(card[i][j] == null)
	    				sd.append("\txxx");
	    			else
	    				sd.append("\t" + card[i][j].getIDCARD());
	    		}
	    	}
	    	return sd.toString();
        }
	    /**
	     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visité
	     * @return les positions d'ajout possible
	     */
	    @Override
	    public HashMap<ArrayList<Integer>, ArrayList<Integer>> accept(FieldVisitor visitor) {
		// TODO Auto-generated method stub
	    	return visitor.visit(this, 0);
	    }
	    /**
		 * renvoie un {@link ArrayList} des emplacements (i,j) libre une {@link Card} sur le {@link RectangularField#fieldView}
		 * @return un {@link HashMap} de {@link ArrayList} de {@link Integer}
		 */
	    private HashMap<ArrayList<Integer>, ArrayList<Integer>> getValuepossible() {
	    	return valuepossible;
	    }
	    /**
		 * renvoie un {@link ArrayList} des emplacements (i,j) occupés par une {@link Card} sur le {@link RectangularField#fieldView}
		 * @return un {@link HashMap} de {@link ArrayList} de {@link Integer}
		 */
	    public HashMap<ArrayList<Integer>, ArrayList<Integer>> getValueUsed()
	    {
	    	return this.valueused;
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
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visité
     */
    public void acceptT(FieldVisitor visitor) {
		// TODO Auto-generated method stub
    	visitor.visit(this);
    }
    /**
     * @param visitor Le Visiteur qui dont la methode {@link FieldVisitor#visit(Terrain)}  va permettre d'avoir une vue du {@link Terrain} qui est visité
     * @return les positions occupé par une {@link Card} non null du Terrain
     */
		@Override
		public HashMap<ArrayList<Integer>, ArrayList<Integer>> acceptU(FieldVisitor visitor) {
			// TODO Auto-generated method stub
			return visitor.visitU(this);
		}
		/**
		 * renvoie un {@link ArrayList} des emplacements (i,j) libre une {@link Card} sur le {@link RectangularField#fieldView}
		 * @return un {@link HashMap} de {@link ArrayList} de {@link Integer}
		 */
		@Override
		public HashMap<ArrayList<Integer>, ArrayList<Integer>> getValuePossible() {
			// TODO Auto-generated method stub
			return this.valuepossible;
		}
		/**
		 * vérifie si la {@link Card} donné en paramètre est présente sur
		 * le {@link RectangularField#fieldView}
		 * @param c une {@link Card} donc on veut savoir si il existe une {@link Card} sur le terrain semblable
		 * @return {@link Boolean#TRUE} s'il existe sur le terrain une Card au même {@link Card#getIDCARD()}
		 */
		@Override
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
		 * vérifie si l'emplacement passé en paramètre est vide
		 * elle est vide si {@link Card} vaut null à l'emplacement du {@link RectangularField#fieldView}
		 * @param i indice colonne de l'emplacement
		 * @param j indice ligne de l'emplacement
		 * @return {@link Boolean#TRUE} si l'emplacement est bel et bien vide
		 */
		@Override
		public boolean hasNothing(int i, int j) {
			// TODO Auto-generated method stub
			if(this.fieldView.get(i-1).get(j-1) == null)
				return true;
			return false;
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
}
