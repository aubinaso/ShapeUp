package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * classe renseignant sur les infos de l'aspect du remplissage sur le {@link Terrain}
 * 
 * @author Aubin Patrick TAKAM TAYO- Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02
 *
 *@see InfoField
 *@see InfoFieldColor
 *@see InfoFieldShape
 *@see CalculScore
 *@see Player
 *@see Partie
 *@see Filled
 */
public class InfoFieldFilled extends InfoField {
	/**
	 * réprésente une vue du {@link Terrain} à un moment donnée
	 * @see RectangularField#fieldView
	 * @see CircularField#fieldView
	 */
	protected Card[][] fieldview;
	/**
     * COnstructeur de la classe {@link InfoFieldFilled}
     */
	public InfoFieldFilled()
    {
		result.clear();
    }
	/**
	 * permet d'informer sur le classement (succession de {@link Filled}) d'une forme précise sur le {@link Terrain}
	 * sur une ligne précise
	 * @param field {@link Terrain}
	 * @param filled la {@link Filled} en question
	 * @param posi la ligne d'étude
	 */
	public void updateValuePossibleLigne(Terrain field, Filled filled, Integer posi)
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
			if(this.fieldview[posi][j] != null)
			{
				if(this.fieldview[posi][j].getFilled().equals(filled))
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
	 * permet d'informer sur le classement (succession de {@link Filled}) d'une forme précise sur le {@link Terrain}
	 * sur une colonne précise
	 * @param field {@link Terrain}
	 * @param filled la {@link Filled} en question
	 * @param posj la colonne d'étude
	 */
	public void updateValuePossibleColonne(Terrain field, Filled filled, Integer posj)
	{
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		ArrayList<Integer> colonne2 = new ArrayList<Integer>();
		ArrayList<Integer> colonne3 = new ArrayList<Integer>();
		this.result.clear();
		
		int value = 0;
		this.visit(field);
		colonne1.clear();
		colonne2.clear();
		colonne3.clear();
		for(int i=0; i<=field.getLenght(); i++)
		{
			if(this.fieldview[i][posj] != null)
			{
				if(this.fieldview[i][posj].getFilled().equals(filled))
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
	 * cette méthode renvoie une certaines dispositions du terrain
	 * @param field {@link Terrain} de la {@link Partie}
	 */
	public void visit(Terrain field) {
		// TODO Auto-generated method stub
		this.fieldview = field.toArray();
	}
	/**
	 * cette méthode renvoie les positions disponibles du {@link Terrain}
	 * @param field le {@link Terrain} de la {@link Partie}
	 * @param i un {@link Integer}
	 * @return une liste de position disponible {@link Terrain#getValuePossible()}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> visit(Terrain field, int i) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * cette méthode renvoie les positions indisponibles du {@link Terrain}
	 * @param field le {@link Terrain} de la {@link Partie}
	 * @return une liste de position indisponible {@link Terrain#getValueUsed()}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> visitU(Terrain field) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * permet d'informer sur le classement (succession de {@link Color}) d'une forme précise sur le {@link Terrain}
	 * sur une ligne précise
	 * @param card vue
	 * @param filled la {@link Filled} en question
	 * @param posi la ligne d'étude
	 */
	public void updateValuePossibleLigneVirtualPlayer(Card card[][], Filled filled, Integer posi)
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
				if(card[posi][j].getFilled().equals(filled))
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
	 * permet d'informer sur le classement (succession de {@link Color}) d'une forme précise sur le {@link Terrain}
	 * sur une colonne précise
	 * @param card vue
	 * @param filled la {@link Filled} en question
	 * @param posj la colonne d'étude
	 */
	public void updateValuePossibleVirtualPlayer(Card card[][], Filled filled, Integer posj)
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
				if(card[i][posj].getFilled().equals(filled))
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
}
