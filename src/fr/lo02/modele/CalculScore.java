package fr.lo02.modele;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
/**
 * c'est cette classe qui s'occupe du calcul de score
 * 
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01 - Automne 2020 - LO02
 * @see InfoField
 * @see InfoFieldColor
 * @see InfoFieldFilled
 * @see InfoFieldShape
 */
public class CalculScore {
	/**
	 * instance de {@link InfoFieldColor} pour renseigner de la disposition des
	 * différentes couleurs sur le {@link Terrain}
	 */
	protected InfoFieldColor scoreColor;
	/**
	 * instance de {@link InfoFieldShape} pour renseigner de la disposition des
	 * différentes formes sur le {@link Terrain}
	 */
	protected InfoFieldShape scoreShape;
	/**
	 * instance de {@link InfoFieldFilled} pour renseigner de la disposition des
	 * différents remplissages sur le {@link Terrain}
	 */
	protected InfoFieldFilled scoreFilled;
	/**
	 * Constructeur de la classe {@link CalculScore}
	 */
	public CalculScore()
	{
		scoreColor = new InfoFieldColor();
		scoreShape = new InfoFieldShape();
		scoreFilled = new InfoFieldFilled();
	}
	/**
	 * Calcul du score correspondant à une Carte donné sur le {@link Terrain}
	 * les scores c'est selon la disposition ci dessus
	 * <ul>
	 * <li>Score			1	2	3	4	5</li>
	 * <li>{@link Shape}	0	1	2	3	4</li>
	 * <li>{@link Filled}	0	0	3	4	5</li>
	 * <li>{@link Color}	0	0	4	5	6</li>
	 * </ul>
	 * 
	 * @param field {@link Terrain} sur lequel on effectue le calcul
	 * @param card  {@link Card} donc on veut connaître le score correspondant
	 * @return le score
	 */
	public int calculScorePlayer(Terrain field, Card card)
	{
		int score = 0;
		for(int i = 0; i<= field.getLenght(); i++)
		{
			score = score + this.scoreLigne(field, card, i);
		}
		for(int j = 0; j<= field.getLarge(); j++)
		{
			score = score + this.scoreColonne(field, card, j);
		}
		return score;
	}
	/**
	 * Calcul du score correspondant à une Carte donné sur le {@link Terrain}
	 * les scores c'est selon la disposition ci dessus
	 * <ul>
	 * <li>Score			1	2	3	4	5</li>
	 * <li>{@link Shape}	0	1	2	3	4</li>
	 * <li>{@link Filled}	0	0	3	4	5</li>
	 * <li>{@link Color}	0	0	4	5	6</li>
	 * </ul>
	 * 
	 * @param card vue sur lequel on effectue le calcul
	 * @param c  {@link Card} donc on veut connaître le score correspondant
	 * @return le score
	 */
	public int calculScorePlayerVirtual(Card card[][], Card c) {
		int score = 0;
		for(int i = 0; i<= 4; i++)
		{
			score = score + this.scoreLigne(card, c, i);
		}
		for(int j = 0; j<= 4; j++)
		{
			score = score + this.scoreColonne(card, c, j);
		}
		return score;
	}
	/**
	 * Calcul du score sur une ligne correspondant à une Carte donné sur le {@link Terrain}
	 * les scores c'est selon la disposition ci dessus
	 * <ul>
	 * <li>Score			1	2	3	4	5</li>
	 * <li>{@link Shape}	0	1	2	3	4</li>
	 * <li>{@link Filled}	0	0	3	4	5</li>
	 * <li>{@link Color}	0	0	4	5	6</li>
	 * </ul>
	 * 
	 * @param field {@link Terrain} sur lequel on effectue le calcul
	 * @param card  {@link Card} donc on veut connaître le score correspondant
	 * @param i	la ligne sur lequel on veut calculer le score correspondant à la {@link Card}
	 * @return le score de la colonne
	 */
	public int scoreLigne(Terrain field, Card card, int i)
	{
		HashMap<Integer, ArrayList<Integer>> colorS = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> shapeS = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> filledS = new HashMap<Integer, ArrayList<Integer>>();

		this.scoreColor.updateValuePossibleLigne(field, card.getColor(), i);
		this.scoreFilled.updateValuePossibleLigne(field, card.getFilled(), i);
		this.scoreShape.updateValuePossibleLigne(field, card.getShape(), i);
		colorS = scoreColor.getResult();
		shapeS = scoreShape.getResult();
		filledS = scoreFilled.getResult();
		
		int scoreC = this.getScore(colorS, "C");
		int scoreS = this.getScore(shapeS, "S");
		int scoreF = this.getScore(filledS, "F");
		
		int Value[] = new int[5];
		for(int j=0; j<5; j++)
		{
			Value[j] = 0;
		}
		
		if(scoreC >= 5)
		{
			return scoreC;
		}
		else if(scoreC == 4)
		{
			if(scoreF > scoreC)
			{
				return scoreF;
			}
			else if(scoreS >= 1)
			{
				//tableau
				if(colorS.get(1).size() == 3)
				{
					for(int j : colorS.get(1))
					{
						Value[j] = 1;
					}
				}
				else		
				{
					for(int j : colorS.get(2))
					{
						Value[j] = 1;
					}
				}
				for(int j : shapeS.get(1))
				{
					if(Value[j] == 0)
						Value[j] = 3;
				}
				if(shapeS.size() == 2)
				for(int j : shapeS.get(2))
				{
					if(Value[j] == 0)
						Value[j] = 3;
				}
				for(int j=0; j<5; j++)
				{
					//j<5
					if(j+1<5 && Value[j] == 3 && Value[j+1] == 3)
					{
						return scoreC+1;
					}
				}
				return scoreC;
			}
			else
			{
				return scoreC;
			}
		}
		else
		{
			if(scoreF >= 4)
			{
				return scoreF;
			}
			else if(scoreF == 3)
			{
				if(scoreS < 1)
				{
					return scoreF;
				}
				else if(scoreS > scoreF)
				{
					return scoreS;
				}
				else
				{
					//tableau
					//tableau
						if(filledS.get(1).size() == 3)
						{
							for(int j : filledS.get(1))
							{
								Value[j] = 2;
							}
						}
						else		
						{
							for(int j : filledS.get(2))
							{
								Value[j] = 2;
							}
						}
						for(int j : shapeS.get(1))
						{
							if(Value[j] == 0)
								Value[j] = 3;
						}
						if(shapeS.size() == 2)
						for(int j : shapeS.get(2))
						{
							if(Value[j] == 0)
								Value[j] = 3;
						}
						for(int j=0; j<5; j++)
						{
							if(Value[j] == 3 && j+1<5 && Value[j+1] == 3)
							{
								return scoreF+1;
							}
						}
						return scoreF;
			}
				
		}
		else
		{
			return scoreS;
		}
	}
	
}
	
	/**
	 * Calcul du score sur une colonne correspondant à une Carte donné sur le {@link Terrain}
	 * les scores c'est selon la disposition ci dessus
	 * <ul>
	 * <li>Score			1	2	3	4	5</li>
	 * <li>{@link Shape}	0	1	2	3	4</li>
	 * <li>{@link Filled}	0	0	3	4	5</li>
	 * <li>{@link Color}	0	0	4	5	6</li>
	 * </ul>
	 * 
	 * @param field {@link Terrain} sur lequel on effectue le calcul
	 * @param card  {@link Card} donc on veut connaître le score correspondant
	 * @param i	la colonne sur lequel on veut calculer le score correspondant à la {@link Card}
	 * @return le score de la colonne
	 */
	public int scoreColonne(Terrain field, Card card, int i)
	{
		HashMap<Integer, ArrayList<Integer>> colorS = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> shapeS = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> filledS = new HashMap<Integer, ArrayList<Integer>>();

		this.scoreColor.updateValuePossibleColonne(field, card.getColor(), i);
		this.scoreFilled.updateValuePossibleColonne(field, card.getFilled(), i);
		this.scoreShape.updateValuePossibleColonne(field, card.getShape(), i);
		colorS = scoreColor.getResult();
		shapeS = scoreShape.getResult();
		filledS = scoreFilled.getResult();
		
		int scoreC = this.getScore(colorS, "C");
		int scoreS = this.getScore(shapeS, "S");
		int scoreF = this.getScore(filledS, "F");
		
		int lenght = field.getLenght();
		
		int Value[] = new int[5];
		for(int j=0; j<5; j++)
		{
			Value[j] = 0;
		}
		
		
		
		if(scoreC >= 5)
			return scoreC;
		else if(scoreC == 4)
		{
			if(scoreF > scoreC)
				return scoreF;
			else if(scoreS >= 1)
			{
				//tableau
				if(colorS.get(1).size() == 3)
				{
					for(int j : colorS.get(1))
					{
						Value[j] = 1;
					}
				}
				else		
				{
					for(int j : colorS.get(2))
					{
						Value[j] = 1;
					}
				}
				for(int j : shapeS.get(1))
				{
					if(Value[j] == 0)
						Value[j] = 3;
				}
				if(shapeS.size() == 2)
				for(int j : shapeS.get(2))
				{
					if(Value[j] == 0)
						Value[j] = 3;
				}
				for(int j=0; j<5; j++)
				{
					if(Value[j] == 3 && j+1<5 && Value[j+1] == 3)
						return scoreC+1;
				}
				return scoreC;
			}
			else
				return scoreC;
		}
		else
		{
			if(scoreF >= 4)
				return scoreF;
			else if(scoreF == 3)
			{
				if(scoreS < 1)
					return scoreF;
				else if(scoreS > scoreF)
					return scoreS;
				else
				{
					//tableau
					//tableau
						if(filledS.get(1).size() == 3)
						{
							for(int j : filledS.get(1))
							{
								Value[j] = 2;
							}
						}
						else		
						{
							for(int j : filledS.get(2))
							{
								Value[j] = 2;
							}
						}
						for(int j : shapeS.get(1))
						{
							if(Value[j] == 0)
								Value[j] = 3;
						}
						if(shapeS.size() == 2)
						for(int j : shapeS.get(2))
						{
							if(Value[j] == 0)
								Value[j] = 3;
						}
						for(int j=0; j<5; j++)
						{
							if(Value[j] == 3 && j+1<5 && Value[j+1] == 3)
								return scoreF+1;
						}
						return scoreF;
			}
				
		}
		else
			return scoreS;
	}
	
}
	
	/**
	 * calcule le correspondant à un {@link ArrayList} de disposition d'élément {@link Filled} ou {@link Color} ou {@link Shape}
	 * et renvoie le score corrpondant
	 * @param score1 {@link ArrayList} d'élément
	 * @param str détermine si on fait un calcul {@link Shape} ou {@link Color} ou {@link Shape}
	 * @return le score correspondant
	 */
	public int getScore(HashMap<Integer, ArrayList<Integer>> score1, String str)
	{
		int scoreC1 = 0;
		int scoreC2 = 0;
		int scoreS1 = 0;
		int scoreS2 = 0;
		int scoreF1 = 0;
		int scoreF2 = 0;
		if(score1.size() == 3 || score1.isEmpty() || score1 == null)
			return 0;
		if(score1.size() >= 1)
		{
			if(score1.get(1).size() == 1)
			{
				scoreC1=0;
				scoreF1=0;
				scoreS1=0;
			}
			else if(score1.get(1).size() == 2)
			{
				scoreC1=0;
				scoreF1=0;
				scoreS1=1;
			}
			else if(score1.get(1).size() == 3)
			{
				scoreC1=4;
				scoreF1=3;
				scoreS1=2;
			}
			else if(score1.get(1).size() == 4)
			{
				scoreC1=5;
				scoreF1=4;
				scoreS1=3;
			}
			else
			{
				scoreC1=6;
				scoreF1=5;
				scoreS1=4;
			}
			
			
			if(score1.size() == 2)
			{
			
			
				if(score1.get(2).size() == 1)
				{
					scoreC2=0;
					scoreF2=0;
					scoreS2=0;
				}
				else if(score1.get(2).size() == 2)
				{
					scoreC2=0;
					scoreF2=0;
					scoreS2=1;
				}
				else if(score1.get(2).size() == 3)
				{
					scoreC2=4;
					scoreF2=3;
					scoreS2=2;
				}
				else if(score1.get(2).size() == 4)
				{
					scoreC2=5;
					scoreF2=4;
					scoreS2=3;
				}
				else
				{
					scoreC2=6;
					scoreF2=5;
					scoreS2=4;
				}
				
			}
		}
		if(str.equals("F"))
		{
			return scoreF1+scoreF2;
		}
		else if(str.equals("S"))
		{
			return scoreS1+scoreS2;
		}
		else
		{
			return scoreC1+scoreC2;
		}
	}
	/**
	 * Calcul du score sur une ligne correspondant à une Carte donné sur le {@link Terrain}
	 * les scores c'est selon la disposition ci dessus
	 * <ul>
	 * <li>Score			1	2	3	4	5</li>
	 * <li>{@link Shape}	0	1	2	3	4</li>
	 * <li>{@link Filled}	0	0	3	4	5</li>
	 * <li>{@link Color}	0	0	4	5	6</li>
	 * </ul>
	 * 
	 * @param field vue sur lequel on effectue le calcul
	 * @param card  {@link Card} donc on veut connaître le score correspondant
	 * @param i	la ligne sur lequel on veut calculer le score correspondant à la {@link Card}
	 * @return le score de la colonne
	 */
	public int scoreLigne(Card field[][], Card card, int i)
	{
		HashMap<Integer, ArrayList<Integer>> colorS = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> shapeS = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> filledS = new HashMap<Integer, ArrayList<Integer>>();

		this.scoreColor.updateValuePossibleLigneVirtualPlayer(field, card.getColor(), i);
		this.scoreFilled.updateValuePossibleLigneVirtualPlayer(field, card.getFilled(), i);
		this.scoreShape.updateValuePossibleLigneVirtualPlayer(field, card.getShape(), i);
		colorS = scoreColor.getResult();
		shapeS = scoreShape.getResult();
		filledS = scoreFilled.getResult();
		
		int scoreC = this.getScore(colorS, "C");
		int scoreS = this.getScore(shapeS, "S");
		int scoreF = this.getScore(filledS, "F");
		
		int Value[] = new int[5];
		for(int j=0; j<5; j++)
		{
			Value[j] = 0;
		}
		
		if(scoreC >= 5)
		{
			return scoreC;
		}
		else if(scoreC == 4)
		{
			if(scoreF > scoreC)
			{
				return scoreF;
			}
			else if(scoreS >= 1)
			{
				//tableau
				if(colorS.get(1).size() == 3)
				{
					for(int j : colorS.get(1))
					{
						Value[j] = 1;
					}
				}
				else		
				{
					for(int j : colorS.get(2))
					{
						Value[j] = 1;
					}
				}
				for(int j : shapeS.get(1))
				{
					if(Value[j] == 0)
						Value[j] = 3;
				}
				if(shapeS.size() == 2)
				for(int j : shapeS.get(2))
				{
					if(Value[j] == 0)
						Value[j] = 3;
				}
				for(int j=0; j<5; j++)
				{
					//j<5
					if(j+1<5 && Value[j] == 3 && Value[j+1] == 3)
					{
						return scoreC+1;
					}
				}
				return scoreC;
			}
			else
			{
				return scoreC;
			}
		}
		else
		{
			if(scoreF >= 4)
			{
				return scoreF;
			}
			else if(scoreF == 3)
			{
				if(scoreS < 1)
				{
					return scoreF;
				}
				else if(scoreS > scoreF)
				{
					return scoreS;
				}
				else
				{
					//tableau
					//tableau
						if(filledS.get(1).size() == 3)
						{
							for(int j : filledS.get(1))
							{
								Value[j] = 2;
							}
						}
						else		
						{
							for(int j : filledS.get(2))
							{
								Value[j] = 2;
							}
						}
						for(int j : shapeS.get(1))
						{
							if(Value[j] == 0)
								Value[j] = 3;
						}
						if(shapeS.size() == 2)
						for(int j : shapeS.get(2))
						{
							if(Value[j] == 0)
								Value[j] = 3;
						}
						for(int j=0; j<5; j++)
						{
							if(Value[j] == 3 && j+1<5 && Value[j+1] == 3)
							{
								return scoreF+1;
							}
						}
						return scoreF;
			}
				
		}
		else
		{
			return scoreS;
		}
	}
	
}
	/**
	 * Calcul du score sur une colonne correspondant à une Carte donné sur le {@link Terrain}
	 * les scores c'est selon la disposition ci dessus
	 * <ul>
	 * <li>Score			1	2	3	4	5</li>
	 * <li>{@link Shape}	0	1	2	3	4</li>
	 * <li>{@link Filled}	0	0	3	4	5</li>
	 * <li>{@link Color}	0	0	4	5	6</li>
	 * </ul>
	 * 
	 * @param field vue sur lequel on effectue le calcul
	 * @param card  {@link Card} donc on veut connaître le score correspondant
	 * @param i	la colonne sur lequel on veut calculer le score correspondant à la {@link Card}
	 * @return le score de la colonne
	 */
	public int scoreColonne(Card field[][], Card card, int i)
	{
		HashMap<Integer, ArrayList<Integer>> colorS = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> shapeS = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, ArrayList<Integer>> filledS = new HashMap<Integer, ArrayList<Integer>>();

		this.scoreColor.updateValuePossibleVirtualPlayer(field, card.getColor(), i);
		this.scoreFilled.updateValuePossibleVirtualPlayer(field, card.getFilled(), i);
		this.scoreShape.updateValuePossibleVirtualPlayer(field, card.getShape(), i);
		colorS = scoreColor.getResult();
		shapeS = scoreShape.getResult();
		filledS = scoreFilled.getResult();
		
		int scoreC = this.getScore(colorS, "C");
		int scoreS = this.getScore(shapeS, "S");
		int scoreF = this.getScore(filledS, "F");
				
		int Value[] = new int[5];
		for(int j=0; j<5; j++)
		{
			Value[j] = 0;
		}
		
		
		
		if(scoreC >= 5)
			return scoreC;
		else if(scoreC == 4)
		{
			if(scoreF > scoreC)
				return scoreF;
			else if(scoreS >= 1)
			{
				//tableau
				if(colorS.get(1).size() == 3)
				{
					for(int j : colorS.get(1))
					{
						Value[j] = 1;
					}
				}
				else		
				{
					for(int j : colorS.get(2))
					{
						Value[j] = 1;
					}
				}
				for(int j : shapeS.get(1))
				{
					if(Value[j] == 0)
						Value[j] = 3;
				}
				if(shapeS.size() == 2)
				for(int j : shapeS.get(2))
				{
					if(Value[j] == 0)
						Value[j] = 3;
				}
				for(int j=0; j<5; j++)
				{
					if(Value[j] == 3 && j+1<5 && Value[j+1] == 3)
						return scoreC+1;
				}
				return scoreC;
			}
			else
				return scoreC;
		}
		else
		{
			if(scoreF >= 4)
				return scoreF;
			else if(scoreF == 3)
			{
				if(scoreS < 1)
					return scoreF;
				else if(scoreS > scoreF)
					return scoreS;
				else
				{
					//tableau
					//tableau
						if(filledS.get(1).size() == 3)
						{
							for(int j : filledS.get(1))
							{
								Value[j] = 2;
							}
						}
						else		
						{
							for(int j : filledS.get(2))
							{
								Value[j] = 2;
							}
						}
						for(int j : shapeS.get(1))
						{
							if(Value[j] == 0)
								Value[j] = 3;
						}
						if(shapeS.size() == 2)
						for(int j : shapeS.get(2))
						{
							if(Value[j] == 0)
								Value[j] = 3;
						}
						for(int j=0; j<5; j++)
						{
							if(Value[j] == 3 && j+1<5 && Value[j+1] == 3)
								return scoreF+1;
						}
						return scoreF;
			}
				
		}
		else
			return scoreS;
	}
	
}
	
}
