package fr.lo02.modele;


/**<p>
 * Cartes Jouable caracterisee par : <br>
 *  </p>
 * <ul>
 * <li>Un remplissage : {@link Filled}<br></li>
 * <li>Une Couleur : {@link Color} <br></li>
 * <li>une forme : {@link Shape}<br></li>
 *
 *</ul>
 * 
 * <p>
 * L' attribut IDCARD permet de charger les bonnes images depuis le bon repertoire en mode de jeu Interface Graphique
 * 
 * </p>
 * 
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see Filled
 * @see Shape
 * @see Color
 *
 */
public class Card {
	/**
	 * L'image caractérisant chaque carte est spécifiée par son nom
	 * sous la forme '...' où chaque point est un caractère
	 * le premier caractère pour {@link Filled} peut prendre comme valeur 'f' => {@link Filled#FILLED} ou 'h' => {@link Filled#HOLLOW}
	 * le deuxième caractère pour {@link Color} peut prendre comme valeur 'b' => {@link Color#BLUE} ou 'g' => {@link Color#GREEN} ou 'r' => {@link Color#RED}
	 * le troisième caractère pour {@link Shape} peut prendre comme valeur 'c' => {@link Shape#CIRCLE} ou 's' => {@link Shape#SQUARE} ou 't' => {@link Shape#TRIANGLE}
	 */
	private String IDCARD = new String();
	/**
	 * La forme de la carte
	 */
	private Shape shape;
	/**
	 * La couleur de la carte
	 */
	private Color color;
	/**
	 * Le remplissage de la carte
	 */
	private Filled filled;
	
	
	/**
     * Constructeur d'une carte , 
     * il permet de definir les attributs de la nouvelle carte ainsi que son nom
     * @param filled le remplissage de la carte {@link Filled}
     * @param color la couleur de la carte {@link Color}
     * @param shape la forme de la carte {@link Shape}
     */
	public Card(Filled filled, Color color, Shape shape)
	{
		this.IDCARD = this.assignID(filled, color, shape);
		this.color = color;
		this.shape = shape;
		this.filled = filled;
	}
	/**
	 * compare l'{@link Card#IDCARD} de deux cartes
	 * @param card qui est la {@link Card} avec laquelle on veut comparée celle ci
	 * @return {@link Boolean#TRUE} si les cartes ont les même id et {@link Boolean#FALSE}
	 */
	public boolean equals(Card card)
	{
		String idcard2 = card.getIDCARD();
		if(this.IDCARD.equals(idcard2))
			return true;
		return false;
	}
	/**
	 * carte qui transforme une association de {@link Filled}, {@link Color}, {@link Shape} en in {@link Card#IDCARD}
	 * @param filled le remplissage de la carte {@link Filled}
	 * @param color la couleur de la carte {@link Color}
	 * @param shape la forme de la carte {@link Shape}
	 * @return un string de même genre que {@link Card#IDCARD}
	 */
	public String assignID(Filled filled, Color color, Shape shape)
	{
		StringBuffer sd = new StringBuffer();
		if(filled.equals(Filled.FILLED))
			sd.append("f");
		else
			sd.append("h");
		if(color.equals(Color.BLUE))
			sd.append("b");
		else if(color.equals(Color.GREEN))
			sd.append("g");
		else
			sd.append("r");
		if (shape.equals(Shape.CIRCLE))
			sd.append("c");
		else if(shape.equals(Shape.SQUARE))
			sd.append("s");
		else
			sd.append("t");
		return sd.toString();
	}
	
	/**
	 * @return getters de {@link Card#filled}
	 */
	public Filled getFilled()
	{
		return this.filled;
	}
	/**
	 * @return getters de {@link Card#color}
	 */
	public Color getColor()
	{
		return this.color;
	}
	/**
	 * @return getters de {@link Card#shape}
	 */
	public Shape getShape()
	{
		return this.shape;
	}
	/**
	 * @return getters de {@link Card#IDCARD}
	 */
	public String getIDCARD()
	{
		return this.IDCARD;
	}
	/**
	 * elle renvoie les infos sur la carte<br>
	 * description de l'objet courant de classe <br>{@link Card} sous forme "{@link Card#IDCARD} {@link Card#shape} {@link Card#color} {@link Card#filled}"
	 */
	public String toString()
	{
		StringBuffer sd = new StringBuffer();
		if(this != null) {
			sd.append(this.getIDCARD() + " " + this.getShape());
			sd.append(" " + this.getColor());
			sd.append(" " + this.getFilled());
			return sd.toString();
		}
		sd.append("rien");
		return sd.toString();
	}

}
