package fr.lo02.modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
/**
 * Cette Classe définie une stratégie "Difficile" pour les Joueurs Virtuels
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see Strategy
 * @see VirtualPlayer
 * @see EasyVirtualPlayer
 * @see IntermediateVirtualPlayer
 * @see Player
 */
public class DifficultVirtualPlayer implements FieldVisitor, Strategy{
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
     * le calcul score pour évaluer la meilleure stratégie
     */
    protected CalculScore calculScore = new CalculScore();
    /**
     * variable d'aide générale pour jouer une {@link Card}
     */
    private Card carte;
	
	/**
	 * Non implémenté
	 * teste toute les possibilités possibles et à chaque fois conserve
	 * sa différence de score avec les {@link Player} de la {@link Partie}
	 * et conclut en prenant le cas le plus favorable qui est 
	 * minimun si il n'a pas le meilleur score
	 * maximum s'il a le meilleur score
	 * utilise {@link CalculScore} temporairement
	 */
	public void deplacerCard(Terrain field) {
		// TODO Auto-generated method stub
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
									if(Partie.startPartie().getConfiguration().getGamerule() instanceof NormalGameRule) {
										colonne2 = this.chooseBestOptionSetPosition(field, Partie.startPartie().getPlay().getVictoryCard());
									}else {
										colonne2 = this.chooseBestOptionSetPosition(field, Partie.startPartie().getPlay().getHandCard().get(0));
									}
									isok = field.setPositionCard(field.getFieldView().get(colonne2.get(0)).get(colonne2.get(1)), colonne2.get(2), colonne2.get(3));
								}
							}
				}
	}
	/**
	 * Déplace toujours en premier
	 * @return {@link Boolean#TRUE} si il joue avant ou {@link Boolean#FALSE} si il déplace avant
	 */
	@Override
	public boolean playFirst(Terrain field) {
		// TODO Auto-generated method stub
		return false;
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
	 * Non implémenté
	 * teste toute les possibilités possibles et à chaque fois conserve
	 * sa différence de score avec les {@link Player} de la {@link Partie}
	 * et conclut en prenant le cas le plus favorable qui est 
	 * minimun si il n'a pas le meilleur score
	 * maximum s'il a le meilleur score
	 * utilise {@link CalculScore} temporairement
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
		do{
			this.carte = card;
			colonne1.clear();
			if(Partie.startPartie().getConfiguration().getGamerule() instanceof NormalGameRule) {
				colonne1 = this.chooseBestOption(this.valuepossible, field, Partie.startPartie().getPlay().getVictoryCard());
			}else {
				colonne1 = this.chooseBestOption(this.valuepossible, field, Partie.startPartie().getPlay().getHandCard().get(0));
			}
			i = colonne1.get(0);
			j = colonne1.get(1);
			isok = field.addCard(card, i+1, j+1, 0);
			this.valuepossible.remove(colonne2);
			if(field.isFull())
				break;
		}while(isok == false);
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
	@Override
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
	/**
	 * choisir la meilleure position d'ajout de la {@link Card}
	 * @param valuepossible1 liste des positions possinbles
	 * @param field {@link Terrain}
	 * @param car la meilleure option de jeu
	 * @return une liste d'entier qui est le meilleur choix de jeu
	 */
	public ArrayList<Integer> chooseBestOption(HashMap<ArrayList<Integer>, ArrayList<Integer>> valuepossible1, Terrain field, Card car) {
		this.visit(field);
		Card[][] c = new Card[5][5];
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		ArrayList<Integer> colonne2 = new ArrayList<Integer>();
		int[] score = {0, 0, 0};
		int sc = 0;
		int cas = 0;
		int diff=0;
		int diffmin = 0;
		int diffmax = 0;
		int max = 0;
		int min = 0;
		for(HashMap.Entry<ArrayList<Integer>, ArrayList<Integer>> entry : this.valuepossible.entrySet()) {
			ArrayList<Integer> key = entry.getKey();
			this.copy(c);
			colonne1.clear();
			if(key.isEmpty())
				break;
			colonne1.add(key.get(0));
			colonne1.add(key.get(1));
			c[colonne1.get(0)][colonne1.get(1)] = this.carte;
			int i = 0;
			if(Partie.startPartie().getConfiguration().getGamerule() instanceof NormalGameRule) {
				for(Player p : Partie.startPartie().getPlayers()) {
					if(!p.getVictoryCard().equals(car))
					{
						score[i] = calculScore.calculScorePlayerVirtual(c, p.getVictoryCard());
						i++;
					}
				}
				sc = calculScore.calculScorePlayerVirtual(c, car);
				
			}else {
				for(Player p : Partie.startPartie().getPlayers()) {
					if(!p.getHandCard().get(0).equals(car))
					{
						score[i] = calculScore.calculScorePlayerVirtual(c, p.getHandCard().get(0));
						i++;
					}
				}
				sc = calculScore.calculScorePlayerVirtual(c, car);
			}
			if(i == 1) {
				if(score[0]> score[1]) {
					max = score[0];
					min = score[1];
				}else if(score[0] == score[1]) {
					max = score[1];
					min = score[0];
				}else {
					min = score[0];
					max = score[1];
				}
				
				if(cas == 1) {
					if(sc > max) {
						if(diffmax < sc -max) {
							diffmax = sc - max;
							diffmin = sc - min;
							colonne2.clear();
							colonne2.add(colonne1.get(0));
							colonne2.add(colonne1.get(1));
						}
					}
				}else if(cas == 2) {
					if(sc > max) {
						diffmax = sc - max;
						diffmin = sc - min;
						cas = 1;
						colonne2.clear();
						colonne2.add(colonne1.get(0));
						colonne2.add(colonne1.get(1));
					}
					else if (sc > min) {
						if(diffmax > sc -max) {
							diffmax = sc - max;
							diffmin = sc - min;
							colonne2.clear();
							colonne2.add(colonne1.get(0));
							colonne2.add(colonne1.get(1));
						}
					}
				}else {
					if(sc < min) {
						if(diffmin > sc - min) {
							diffmin = sc - min;
							diffmax = sc -max;
							colonne2.clear();
							colonne2.add(colonne1.get(0));
							colonne2.add(colonne1.get(1));
						}
					}else {
						diffmin = sc -min;
						diffmax = sc-max;
						colonne2.clear();
						colonne2.add(colonne1.get(0));
						colonne2.add(colonne1.get(1));
						if(sc < max)
							cas = 2;
						else
							cas = 1;
					}
				}
				
				
			}else {
				if(sc > score[0]) {
					if(cas != 1) {
						diff = sc - score[i];
						cas = 1;
						colonne2.clear();
						colonne2.add(colonne1.get(0));
						colonne2.add(colonne1.get(1));
					}
					else {
						if(diff < sc-score[i]) {
							diff = sc-score[i];
							colonne2.clear();
							colonne2.add(colonne1.get(0));
							colonne2.add(colonne1.get(1));
						}
					}
				}else
				{
					if(cas != 1) {
						if(diff > sc -score[i]) {
							diff = sc - score[i];
							cas = 2;
							colonne2.clear();
							colonne2.add(colonne1.get(0));
							colonne2.add(colonne1.get(1));
						}
					}
				}
			}
			System.out.println(colonne2 + " colonne2");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(colonne2 + " colonne2 envoyé");
		if(colonne2.isEmpty())
			return this.ValueShuffle(valuepossible);
		return colonne2;
	}
	
	/**
	 * choisir la meilleure position de déplacement de la {@link Card}
	 * @param field liste des positions possinbles
	 * @param car {@link Terrain}
	 * @return une liste d'entier qui est la meilleure option de jeu
	 */
	public ArrayList<Integer> chooseBestOptionSetPosition(Terrain field, Card car) {
		this.visit(field);
		Card[][] c = new Card[5][5];
		ArrayList<Integer> colonne1 = new ArrayList<Integer>();
		ArrayList<Integer> colonne2 = new ArrayList<Integer>();
		int[] score = {0, 0, 0};
		int sc = 0;
		int cas = 0;
		int diff=0;
		int diffmin = 0;
		int diffmax = 0;
		int max = 0;
		int min = 0;
		for(HashMap.Entry<ArrayList<Integer>, ArrayList<Integer>> entry : this.valuePossibleSet.entrySet()) {
			ArrayList<Integer> key = entry.getKey();
			this.copy(c);
			colonne1.clear();
			colonne1.add(key.get(0));
			colonne1.add(key.get(1));
			colonne1.add(key.get(2));
			colonne1.add(key.get(3));
			c[colonne1.get(2)][colonne1.get(3)] = c[colonne1.get(0)][colonne1.get(1)];
			c[colonne1.get(0)][colonne1.get(1)] = null;
			int i = 0;
			for(Player p : Partie.startPartie().getPlayers()) {
					if(p != Partie.startPartie().getPlay())
					{
						if(Partie.startPartie().getConfiguration().getGamerule() instanceof NormalGameRule)
							score[i] = calculScore.calculScorePlayerVirtual(c, p.getVictoryCard());
						else
							score[i] = calculScore.calculScorePlayerVirtual(c, p.getHandCard().get(0));
						i++;
					}
				}
			if(Partie.startPartie().getConfiguration().getGamerule() instanceof NormalGameRule)
				sc = calculScore.calculScorePlayerVirtual(c,car);
			else
				sc= calculScore.calculScorePlayerVirtual(c, Partie.startPartie().getPlay().getHandCard().get(0));
			if(i == 1) {
				if(score[0]> score[1]) {
					max = score[0];
					min = score[1];
				}else if(score[0] == score[1]) {
					max = score[1];
					min = score[0];
				}else {
					min = score[0];
					max = score[1];
				}
				
				if(cas == 1) {
					if(sc > max) {
						if(diffmax < sc -max) {
							diffmax = sc - max;
							diffmin = sc - min;
							colonne2.clear();
							colonne2.add(colonne1.get(0));
							colonne2.add(colonne1.get(1));
							colonne2.add(colonne1.get(2));
							colonne2.add(colonne1.get(3));
						}
					}
				}else if(cas == 2) {
					if(sc > max) {
						diffmax = sc - max;
						diffmin = sc - min;
						cas = 1;
						colonne2.clear();
						colonne2.add(colonne1.get(0));
						colonne2.add(colonne1.get(1));
						colonne2.add(colonne1.get(2));
						colonne2.add(colonne1.get(3));
					}
					else if (sc > min) {
						if(diffmax > sc -max) {
							diffmax = sc - max;
							diffmin = sc - min;
							colonne2.clear();
							colonne2.add(colonne1.get(0));
							colonne2.add(colonne1.get(1));
							colonne2.add(colonne1.get(2));
							colonne2.add(colonne1.get(3));
						}
					}
				}else {
					if(sc < min) {
						if(diffmin > sc - min) {
							diffmin = sc - min;
							diffmax = sc -max;
							colonne2.clear();
							colonne2.add(colonne1.get(0));
							colonne2.add(colonne1.get(1));
							colonne2.add(colonne1.get(2));
							colonne2.add(colonne1.get(3));
						}
					}else {
						diffmin = sc -min;
						diffmax = sc-max;
						colonne2.clear();
						colonne2.add(colonne1.get(0));
						colonne2.add(colonne1.get(1));
						colonne2.add(colonne1.get(2));
						colonne2.add(colonne1.get(3));
						if(sc < max)
							cas = 2;
						else
							cas = 1;
					}
				}
				
				
			}else {
				if(sc > score[0]) {
					if(cas != 1) {
						diff = sc - score[i];
						cas = 1;
						colonne2.clear();
						colonne2.add(colonne1.get(0));
						colonne2.add(colonne1.get(1));
						colonne2.add(colonne1.get(2));
						colonne2.add(colonne1.get(3));
					}
					else {
						if(diff < sc-score[i]) {
							diff = sc-score[i];
							colonne2.clear();
							colonne2.add(colonne1.get(0));
							colonne2.add(colonne1.get(1));
							colonne2.add(colonne1.get(2));
							colonne2.add(colonne1.get(3));
						}
					}
				}else
				{
					if(cas != 1) {
						if(diff > sc -score[i]) {
							diff = sc - score[i];
							cas = 2;
							colonne2.clear();
							colonne2.add(colonne1.get(0));
							colonne2.add(colonne1.get(1));
							colonne2.add(colonne1.get(2));
							colonne2.add(colonne1.get(3));
						}
					}
				}
			}
		}
		return colonne2;
	}
	/**
	 * permet de copier une matrice dans une autre
	 * @param c matrice où on va copier la matrice {@link DifficultVirtualPlayer#cardField}
	 */
	public void copy(Card c[][]) {
		for(int i = 0; i<=4; i++)
		{
			for(int j = 0; j<=4; j++)
			{
				c[i][j] = this.cardField[i][j];
			}
		}
	}
	/**
	 * mélange les éléments de {@link IntermediateVirtualPlayer#valuepossible}
	 * @param valuepossible1 qui est {@link IntermediateVirtualPlayer#valuepossible}
	 * @return une liste d'entier
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
	
}
