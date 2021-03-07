 package fr.lo02.modele;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 * Cette Classe définie une stratégie "Facile" pour les Joueurs Virtuels
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see Strategy
 * @see VirtualPlayer
 * @see IntermediateVirtualPlayer
 * @see DifficultVirtualPlayer
 * @see Player
 */
public class EasyVirtualPlayer implements FieldVisitor, Strategy{
	/**
	 * ensemble des valeurs possibles d'ajout de {@link Card}
	 */
    protected HashMap<ArrayList<Integer>, ArrayList<Integer>> valuepossible = new HashMap<ArrayList<Integer>, ArrayList<Integer>>();

	/**
	 * Constructeur par défaut
	 */
	public EasyVirtualPlayer() {
		// TODO Auto-generated constructor stub
	}
	
	/**
     * Joue une {@link Card} sur une position disponible sur le {@link Terrain}
     */

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
			colonne2 = this.ValueShuffle();
			i = colonne2.get(0)+1;
			j = colonne2.get(1)+1;
			isok = field.addCard(card, i, j, 0);
			if(field.isFull())
				break;
		}
	}
	/**
	 * mélange les éléments de {@link IntermediateVirtualPlayer#valuepossible}
	 * @return une liste d'entier
	 */
	public ArrayList<Integer> ValueShuffle()
	{
		
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		int j = 0;
		Random r = new Random();
		int min = 0, max = this.valuepossible.size()-1;
		j = r.nextInt((max - min) + 1) + min;
		int i = 0;
		for (HashMap.Entry<ArrayList<Integer>, ArrayList<Integer>> entry : this.valuepossible.entrySet()) {
			colonne1.clear();
			ArrayList<Integer> key = entry.getKey();
			if(i==j)
			{
				colonne1.add(key.get(0));
				colonne1.add(key.get(1));
				break;
			}
			i++;
		}
		return colonne1;
	}
	/**
	 * ne déplace pas
	 */
	public void deplacerCard(Terrain field) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * cette méthode renvoie les positions disponibles du {@link Terrain}
	 * @param field le {@link Terrain} de la {@link Partie}
	 * @param i un {@link Integer}
	 * @return une liste de position disponible {@link Terrain#getValuePossible()}
	 */
	@Override
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
	 * cette méthode renvoie une certaines dispositions du terrain
	 * @param field {@link Terrain} de la {@link Partie}
	 */
	@Override
	public void visit(Terrain field) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * cette méthode renvoie les positions indisponibles du {@link Terrain}
	 * @param field le {@link Terrain} de la {@link Partie}
	 * @return une liste de position indisponible {@link Terrain#getValueUsed()}
	 */
	@Override
	public HashMap<ArrayList<Integer>, ArrayList<Integer>> visitU(Terrain field) {
		// TODO Auto-generated method stub
		if(field instanceof RectangularField)
			return ((RectangularField) field).getValueUsed();
		else if(field instanceof CircularField)
			return ((CircularField) field).getValueUsed();
		else
			return ((TriangularField) field).getValueUsed();
	}
	
}
