package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
/**
 * Cette Classe définie une stratégie "Intermédiaire" pour les Joueurs Virtuels
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see Strategy
 * @see VirtualPlayer
 * @see EasyVirtualPlayer
 * @see DifficultVirtualPlayer
 * @see Player
 */
public class IntermediateVirtualPlayer implements FieldVisitor, Strategy{
	/**
	 * ensemble des positions possible d'ajout de {@link Card} sur le {@link Terrain}
	 */
    protected HashMap<ArrayList<Integer>, ArrayList<Integer>> valuepossible = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
    //les valeurs pouvant être déplacées
    /**
     * ensemble des {@link Card} pouvant être déplacé sur le {@link Terrain}
     */
    protected HashMap<ArrayList<Integer>, ArrayList<Integer>> valuecanBeSet = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
    //Les valeurs initiales de prises et de dépot
    /**
     * ensemble des valeur possible de prise et de dépôt sur le {@link Terrain}
     */
    protected HashMap<ArrayList<Integer>, ArrayList<Integer>> valuePossibleSet = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();
    /**
     * vue d'ensemble du {@link Terrain} et des {@link Card} dans leur emplacement
     */
    protected Card cardField[][] = new Card[5][5];

    /**
     * Joue une {@link Card} sur une position disponible sur le {@link Terrain}
     */
	@Override
	public void jouerCard(Terrain field, Card card) {
		// TODO Auto-generated method stub
		boolean isok = false;
		int i = 0, j = 0;
		field.updateValuePossible();
		this.valuepossible = field.accept(this);
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		ArrayList<Integer> colonne2 = new ArrayList<Integer>();
		for(int k =0; k<6; k++)
		{
			colonne1 = new ArrayList<Integer>();
			colonne2.clear();
			if(k<=2)
			{
				colonne1.add(3);
				colonne1.add(k);
				colonne2.add(2);
				colonne2.add(k);
				
				if(field.getFieldView().get(colonne2.get(0)).get(colonne2.get(1)) != null)
				{
					this.valuepossible.put(colonne1, colonne1);
				}
			}
			else
			{
				colonne1.add(k-3);
				colonne1.add(3);
				colonne2.add(k-3);
				colonne2.add(2);
				if(field.getFieldView().get(colonne2.get(0)).get(colonne2.get(1)) != null)
				{
					this.valuepossible.put(colonne1, colonne1);
				}
			}
			
		}
		while(!isok)
		{
			colonne2.clear();
			colonne2 = this.ValueShuffle(this.valuepossible);
			i = colonne2.get(0)+1;
			j = colonne2.get(1)+1;
			isok = field.addCard(card, i, j, 0);
			this.valuepossible.remove(colonne2);
			if(field.isFull())
				break;
		}
	}

	/**
	 * déplace une {@link Card} aléatoire du {@link Terrain} à une position aléatoire
	 */
	@Override
	public void deplacerCard(Terrain field) {
		// TODO Auto-generated method stub
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		ArrayList<Integer> colonne2 = new ArrayList<Integer>();
		boolean isok = false;
		
		this.valuecanBeSet = field.acceptU(this);
		this.valuepossible = field.accept(this);
		this.valuePossibleSet.clear();
		field.acceptT(this);
		if(!this.valuecanBeSet.isEmpty())
		{
			for (HashMap.Entry<ArrayList<Integer>, ArrayList<Integer>> entry : this.valuecanBeSet.entrySet())
				{
					ArrayList<Integer> key = entry.getKey();
					for (HashMap.Entry<ArrayList<Integer>, ArrayList<Integer>> entry1 : this.valuepossible.entrySet())
						{
							colonne1.clear();
							colonne2.clear();
							ArrayList<Integer> key1 = entry1.getKey();
							colonne1.add(key.get(0));
							colonne1.add(key.get(1));
							colonne2.add(key1.get(0));
							colonne2.add(key1.get(1));
							if(field.canIBeSet(colonne1.get(0), colonne1.get(1), 0, colonne2.get(0), colonne2.get(1), field.toArray()))
							{
								colonne1.add(colonne2.get(0));
								colonne1.add(colonne2.get(1));
								if(!this.valuePossibleSet.containsKey(colonne1))
									this.valuePossibleSet.put(colonne1, colonne1);
								colonne1 = new ArrayList<Integer>();
							}
						}
				}
					if(!this.valuePossibleSet.isEmpty())
					{
						int i = 0;
						while(!isok && i<= this.valuePossibleSet.size())
						{
							i++;
							colonne2.clear();
							colonne2 = this.ValueShuffle(this.valuePossibleSet);
							isok = field.setPositionCard(field.getFieldView().get(colonne2.get(0)).get(colonne2.get(1)), colonne2.get(2), colonne2.get(3));
						}
					}
		}
	}
	/**
	 * décide de jouer avant ou après de manière aléatoire
	 * @return {@link Boolean#TRUE} si il joue avant ou {@link Boolean#FALSE} si il déplace avant
	 */
	public boolean playFirst(Terrain field) {
		// TODO Auto-generated method stub
		Random r = new Random();
		int j = r.nextInt(2);
		if(j==0)
			return true;
		else
			return false;
	}
	
	/**
	 * mélange les éléments de {@link IntermediateVirtualPlayer#valuepossible}
	 * @param valuepossible1 qui est {@link IntermediateVirtualPlayer#valuepossible}
	 * @return une liste de bon choix
	 */
	public ArrayList<Integer> ValueShuffle(HashMap<ArrayList<Integer>, ArrayList<Integer>> valuepossible1)
	{
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		int j = 0;
		Random r = new Random();
		int min = 0, max = valuepossible1.size()-1;
		if(max != 0)
			j = r.nextInt((max - min) + 1) + min;
		else
			j=0;
		int i = 0;
		for (HashMap.Entry<ArrayList<Integer>, ArrayList<Integer>> entry : valuepossible1.entrySet()) {
			colonne1.clear();
			ArrayList<Integer> key = entry.getKey();
			if(i==j)
			{
				colonne1.add(key.get(0));
				colonne1.add(key.get(1));
				if(key.size()==4)
				{
					colonne1.add(key.get(2));
					colonne1.add(key.get(3));
					this.valuePossibleSet.remove(colonne1);
				}
				break;
			}
			i++;
		}
		return colonne1;
	}
	
	/**
	 * cette méthode renvoie une certaines dispositions du terrain
	 * @param field {@link Terrain} de la {@link Partie}
	 */
	@Override
	public void visit(Terrain field) {
		// TODO Auto-generated method stub
		for(int i = 0; i<=4; i++)
		{
			for(int j = 0; j<=4; j++)
			{
				this.cardField[i][j] = field.getFieldView().get(i).get(j);
			}
		}
	}
	/**
	 * cette méthode renvoie les positions disponibles du {@link Terrain}
	 * @param field le {@link Terrain} de la {@link Partie}
	 * @param i un {@link Integer}
	 * @return une liste de position disponible {@link Terrain#getValuePossible()}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> visit(Terrain field, int i) {
		// TODO Auto-generated method stub
		if(field instanceof RectangularField)
			return ((RectangularField) field).getValuePossible();
		else if(field instanceof CircularField)
			return ((CircularField) field).getValuePossible();
		else
			return ((TriangularField) field).getValuePossible();
		}
	/**
	 * cette méthode renvoie les positions indisponibles du {@link Terrain}
	 * @param field le {@link Terrain} de la {@link Partie}
	 * @return une liste de position indisponible {@link Terrain#getValueUsed()}
	 */
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> visitU(Terrain field) {
		// TODO Auto-generated method stub
		field.updateValueUsed();
		if(field instanceof RectangularField)
			return ((RectangularField) field).getValueUsed();
		else if(field instanceof CircularField)
			return ((CircularField) field).getValueUsed();
		else
			return ((TriangularField) field).getValueUsed();
	}
}
