package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * classe renseignant sur les infos de l'aspect de la couleur sur le {@link Terrain}
 * 
 * @author Aubin Patrick TAKAM TAYO- Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01- Automne 2020 - LO02
 *
 *@see InfoField
 *@see InfoFieldFilled
 *@see InfoFieldShape
 *@see CalculScore
 *@see Player
 *@see Partie
 *@see Color
 */
public class InfoFieldColor extends InfoField{
	/**
	 * r�pr�sente une vue du {@link Terrain} � un moment donn�e
	 * @see RectangularField#fieldView
	 * @see CircularField#fieldView
	 */
	protected Card[][] fieldView;
    /**
     * COnstructeur de la classe {@link InfoFieldColor}
     */
	public InfoFieldColor()
    {
		result.clear();
    }
	/**
	 * permet d'informer sur le classement (succession de {@link Color}) d'une forme pr�cise sur le {@link Terrain}
	 * sur une ligne pr�cise
	 * @param field {@link Terrain}
	 * @param color la {@link Color} en question
	 * @param posi la ligne d'�tude
	 */
	public void updateValuePossibleLigne(Terrain field, Color color, Integer posi)
	{
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		ArrayList<Integer> colonne2 = new ArrayList<Integer>();
		ArrayList<Integer> colonne3 = new ArrayList<Integer>();
		this.result.clear();
		this.visit(field);
		int value = 0;
		colonne1.clear();
		colonne2.clear();
		colonne3.clear();
		for(int j=0; j<=field.getLarge(); j++)
		{
			if(this.fieldView[posi][j] != null)
			{
				if(this.fieldView[posi][j].getColor().equals(color))
				{
					value++;
					if(colonne1.isEmpty() || colonne1.contains(j-1))
					{
						colonne1.add(j);
					}
					else if (colonne2.isEmpty() || colonne2.contains(j-1)){
						colonne2.add(j);
					}
					else {
						colonne3.add(j);
					}
				}
			}
		}
		
		if(colonne3.isEmpty())
		{
			if(colonne2.isEmpty())
			{
				if(!colonne1.isEmpty())
					this.result.put(1, colonne1);
			}
			else
			{
				this.result.put(1, colonne1);
				this.result.put(2, colonne2);
			}
		}
		else
		{
			this.result.put(1, colonne1);
			this.result.put(2, colonne2);
			this.result.put(3, colonne3);
		}
}
	/**
	 * permet d'informer sur le classement (succession de {@link Color}) d'une forme pr�cise sur le {@link Terrain}
	 * sur une colonne pr�cise
	 * @param field {@link Terrain}
	 * @param color la {@link Color} en question
	 * @param posj la colonne d'�tude
	 */
	public void updateValuePossibleColonne(Terrain field, Color color, Integer posj)
	{
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		ArrayList<Integer> colonne2 = new ArrayList<Integer>();
		ArrayList<Integer> colonne3 = new ArrayList<Integer>();
		this.result.clear();
		int value = 0;
		colonne1.clear();
		this.visit(field);
		colonne2.clear();
		colonne3.clear();
		//ici
		for(int i=0; i<=field.getLenght(); i++)
		{
			if(this.fieldView[i][posj] != null)
			{
				if(this.fieldView[i][posj].getColor().equals(color))
				{
					value++;
					if(colonne1.isEmpty() || colonne1.contains(i-1))
					{
						colonne1.add(i);
					}
					else if (colonne2.isEmpty() || colonne2.contains(i-1)){
						colonne2.add(i);
					}
					else {
						colonne3.add(i);
					}
				}
			}
		}
		
		if(colonne3.isEmpty())
		{
			if(colonne2.isEmpty())
			{
				if(!colonne1.isEmpty())
					this.result.put(1, colonne1);
			}
			else
			{
				this.result.put(1, colonne1);
				this.result.put(2, colonne2);
			}
		}
		else
		{
			this.result.put(1, colonne1);
			this.result.put(2, colonne2);
			this.result.put(3, colonne3);
		}
	}
	/**
	 * permet d'informer sur le classement (succession de {@link Color}) d'une forme pr�cise sur le {@link Terrain}
	 * sur une ligne pr�cise
	 * @param card vue
	 * @param color la {@link Color} en question
	 * @param posi la ligne d'�tude
	 */
	public void updateValuePossibleLigneVirtualPlayer(Card card[][], Color color, Integer posi)
	{
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		ArrayList<Integer> colonne2 = new ArrayList<Integer>();
		ArrayList<Integer> colonne3 = new ArrayList<Integer>();
		this.result.clear();
		int value = 0;
		colonne1.clear();
		colonne2.clear();
		colonne3.clear();
		//ici
		for(int j=0; j<card[0].length; j++)
		{
			if(card[posi][j] != null)
			{
				if(card[posi][j].getColor().equals(color))
				{
					value++;
					if(colonne1.isEmpty() || colonne1.contains(j-1))
					{
						colonne1.add(j);
					}
					else if (colonne2.isEmpty() || colonne2.contains(j-1)){
						colonne2.add(j);
					}
					else {
						colonne3.add(j);
					}
				}
			}
		}
		
		if(colonne3.isEmpty())
		{
			if(colonne2.isEmpty())
			{
				if(!colonne1.isEmpty())
					this.result.put(1, colonne1);
			}
			else
			{
				this.result.put(1, colonne1);
				this.result.put(2, colonne2);
			}
		}
		else
		{
			this.result.put(1, colonne1);
			this.result.put(2, colonne2);
			this.result.put(3, colonne3);
		}
	}
	/**
	 * permet d'informer sur le classement (succession de {@link Color}) d'une forme pr�cise sur le {@link Terrain}
	 * sur une colonne pr�cise
	 * @param card vue
	 * @param color la {@link Color} en question
	 * @param posj la colonne d'�tude
	 */
	public void updateValuePossibleVirtualPlayer(Card card[][], Color color, Integer posj)
	{
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		ArrayList<Integer> colonne2 = new ArrayList<Integer>();
		ArrayList<Integer> colonne3 = new ArrayList<Integer>();
		this.result.clear();
		int value = 0;
		colonne1.clear();
		colonne2.clear();
		colonne3.clear();
		//ici
		for(int i=0; i<card.length; i++)
		{
			if(card[i][posj] != null)
			{
				if(card[i][posj].getColor().equals(color))
				{
					value++;
					if(colonne1.isEmpty() || colonne1.contains(i-1))
					{
						colonne1.add(i);
					}
					else if (colonne2.isEmpty() || colonne2.contains(i-1)){
						colonne2.add(i);
					}
					else {
						colonne3.add(i);
					}
				}
			}
		}
		
		if(colonne3.isEmpty())
		{
			if(colonne2.isEmpty())
			{
				if(!colonne1.isEmpty())
					this.result.put(1, colonne1);
			}
			else
			{
				this.result.put(1, colonne1);
				this.result.put(2, colonne2);
			}
		}
		else
		{
			this.result.put(1, colonne1);
			this.result.put(2, colonne2);
			this.result.put(3, colonne3);
		}
	}
	/**
	 * cette m�thode renvoie une certaines dispositions du terrain
	 * @param field {@link Terrain} de la {@link Partie}
	 */
	public void visit(Terrain field) {
		// TODO Auto-generated method stub
		this.fieldView = field.toArray();
	}
	/**
	 * cette m�thode renvoie les positions disponibles du {@link Terrain}
	 * @param field le {@link Terrain} de la {@link Partie}
	 * @param i un {@link Integer}
	 * @return une liste de position disponible {@link Terrain#getValuePossible()}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> visit(Terrain field, int i) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * cette m�thode renvoie les positions indisponibles du {@link Terrain}
	 * @param field le {@link Terrain} de la {@link Partie}
	 * @return une liste de position indisponible {@link Terrain#getValueUsed()}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> visitU(Terrain field) {
		// TODO Auto-generated method stub
		return null;
	}
}
