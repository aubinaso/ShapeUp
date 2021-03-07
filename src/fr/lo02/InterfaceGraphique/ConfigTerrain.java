package fr.lo02.InterfaceGraphique;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import fr.lo02.controler.GraphicController;
import fr.lo02.controler.PlaySet;
import fr.lo02.modele.AdvancedGameRule;
import fr.lo02.modele.Card;
import fr.lo02.modele.CircularField;
import fr.lo02.modele.Configuration;
import fr.lo02.modele.NormalGameRule;
import fr.lo02.modele.Partie;
import fr.lo02.modele.RealPlayer;
import fr.lo02.modele.RectangularField;
import fr.lo02.modele.Terrain;
import fr.lo02.modele.TriangularField;
import fr.lo02.modele.Update;
/**
 * Ecran du {@link Terrain}
 * elle présente la vue Graphique du {@link Terrain}
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see GamePanel
 * @see VueGraphique
 * @see Vue
 * @see GraphicController
 */
public class ConfigTerrain extends JPanel implements Observer{

	/**
	 * l'ensemble des emplacements des {@link Card} sur le {@link Terrain}
	 * collections de {@link Bouton}
	 */
	ArrayList<ArrayList<Bouton>> collectCardTotal = new ArrayList<ArrayList<Bouton>>();
	/**
	 * l'ensemble des emplacements des {@link Card} sur le {@link Terrain}
	 * collections de {@link Bouton} sur une ligne
	 */
	ArrayList<Bouton> collectCard1 = new ArrayList<Bouton>();
	/**
	 * l'ensemble des emplacements des {@link Card} sur le {@link Terrain}
	 * collections de {@link Bouton} sur une ligne
	 */
	ArrayList<Bouton> collectCard2 = new ArrayList<Bouton>();
	/**
	 * l'ensemble des emplacements des {@link Card} sur le {@link Terrain}
	 * collections de {@link Bouton} sur une ligne
	 */
	ArrayList<Bouton> collectCard3= new ArrayList<Bouton>();
	/**
	 * l'ensemble des emplacements des {@link Card} sur le {@link Terrain}
	 * collections de {@link Bouton} sur une ligne
	 */
	ArrayList<Bouton> collectCard4 = new ArrayList<Bouton>();
	/**
	 * l'ensemble des emplacements des {@link Card} sur le {@link Terrain}
	 * collections de {@link Bouton} sur une ligne
	 */
	ArrayList<Bouton> collectCard5 = new ArrayList<Bouton>();
	/**
	 * l'ensemble des noms des {@link Bouton} de {@link ConfigTerrain#collectCardTotal} sur une ligne
	 */
	ArrayList<String> nomImage1 = new ArrayList<String>();
	/**
	 * l'ensemble des noms des {@link Bouton} de {@link ConfigTerrain#collectCardTotal} sur une ligne
	 */
	ArrayList<String> nomImage2 = new ArrayList<String>();
	/**
	 * l'ensemble des noms des {@link Bouton} de {@link ConfigTerrain#collectCardTotal} sur une ligne
	 */
	ArrayList<String> nomImage3 = new ArrayList<String>();
	/**
	 * l'ensemble des noms des {@link Bouton} de {@link ConfigTerrain#collectCardTotal} sur une ligne
	 */
	ArrayList<String> nomImage4 = new ArrayList<String>();
	/**
	 * l'ensemble des noms des {@link Bouton} de {@link ConfigTerrain#collectCardTotal} sur une ligne
	 */
	ArrayList<String> nomImage5 = new ArrayList<String>();
	/**
	 * l'ensemble des noms des {@link Bouton} de {@link ConfigTerrain#collectCardTotal}
	 */
	ArrayList<ArrayList<String>> nomImageTotal = new ArrayList<ArrayList<String>>();
	/**
	 * le {@link Terrain} de la {@link Partie}
	 */
	Terrain field;
	
	/**
	 * réinitialise l'instance de {@link Card} avec les valeurs par défaut
	 */
	public void reInit()
	{
		try {
			this.collectCard1.clear();
			this.collectCard2.clear();
			this.collectCard3.clear();
			this.collectCard4.clear();
			this.collectCard5.clear();
			this.collectCardTotal.clear();
			this.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * Constructeur de ConfigTerrain
	 * @param field le {@link Terrain} qu'il observe
	 */
	public ConfigTerrain(Terrain field)
	{
		this.field = field;
		this.ajouter();
		this.ini();
		try {
			this.init();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * initialise les nom des Boutons du {@link Terrain}
	 */
	public void ajouter() {
		this.nomImage1.clear();
		this.nomImage2.clear();
		this.nomImage3.clear();
		this.nomImage4.clear();
		this.nomImage5.clear();
		
		this.nomImage1.add("aaa.jpg");
		this.nomImage1.add("aaa.jpg");
		this.nomImage1.add("aaa.jpg");
		this.nomImage1.add("aaa.jpg");
		this.nomImage1.add("aaa.jpg");
		
		this.nomImage2.add("aaa.jpg");
		this.nomImage2.add("aaa.jpg");
		this.nomImage2.add("aaa.jpg");
		this.nomImage2.add("aaa.jpg");
		this.nomImage2.add("aaa.jpg");
		
		this.nomImage3.add("aaa.jpg");
		this.nomImage3.add("aaa.jpg");
		this.nomImage3.add("aaa.jpg");
		this.nomImage3.add("aaa.jpg");
		this.nomImage3.add("aaa.jpg");
		
		this.nomImage4.add("aaa.jpg");
		this.nomImage4.add("aaa.jpg");
		this.nomImage4.add("aaa.jpg");
		this.nomImage4.add("aaa.jpg");
		this.nomImage4.add("aaa.jpg");
		
		this.nomImage5.add("aaa.jpg");
		this.nomImage5.add("aaa.jpg");
		this.nomImage5.add("aaa.jpg");
		this.nomImage5.add("aaa.jpg");
		this.nomImage5.add("aaa.jpg");
		
		
		this.nomImageTotal.add(nomImage1);
		this.nomImageTotal.add(nomImage2);
		this.nomImageTotal.add(nomImage3);
		this.nomImageTotal.add(nomImage4);
		this.nomImageTotal.add(nomImage5);
	}
	/**
	 * Initialize the contents of the frame.
	 * @exception {@link IOException} une exception
	 */
	public void init() throws IOException
	{
		
		this.setBounds(68, 97, 815, 440);
		this.setLayout(new GridLayout(5, 5, 1, 1));             
		
		Bouton carte_1 = new Bouton("1", 1, 1);
		Image img_1 = ImageIO.read(new File(this.nomImage1.get(0)));
		carte_1.setImg(img_1);
		this.add(carte_1);		
		this.collectCard1.add(carte_1);
		
		Bouton carte_2 = new Bouton("2", 1, 2);
		Image img_2 = ImageIO.read(new File(this.nomImage1.get(1)));
		carte_2.setImg(img_2);
		this.add(carte_2);
		this.collectCard1.add(carte_2);
		
		Bouton carte_3 = new Bouton("3", 1, 3);
		Image img_3 = ImageIO.read(new File(this.nomImage1.get(2)));
		carte_3.setImg(img_3);
		this.add(carte_3);
		this.collectCard1.add(carte_3);
		
		Bouton carte_4 = new Bouton("4", 1, 4);
		Image img_4 = ImageIO.read(new File(this.nomImage1.get(3)));
		carte_4.setImg(img_4);
		this.add(carte_4);
		this.collectCard1.add(carte_4);
		
		Bouton carte_5 = new Bouton("5", 1, 5);
		Image img_5 = ImageIO.read(new File(this.nomImage1.get(4)));
		carte_5.setImg(img_5);
		this.add(carte_5);
		this.collectCard1.add(carte_5);
		
		Bouton carte_6 = new Bouton("6", 2, 1);
		Image img_6 = ImageIO.read(new File(this.nomImage2.get(0)));
		carte_6.setImg(img_6);
		this.add(carte_6);
		this.collectCard2.add(carte_6);
		
		Bouton carte_7 = new Bouton("7", 2, 2);
		Image img_7 = ImageIO.read(new File(this.nomImage2.get(1)));
		carte_7.setImg(img_7);
		this.add(carte_7);
		this.collectCard2.add(carte_7);
		
		Bouton carte_8 = new Bouton("8", 2, 3);
		Image img_8 = ImageIO.read(new File(this.nomImage2.get(2)));
		carte_8.setImg(img_8);
		this.add(carte_8);
		this.collectCard2.add(carte_8);
		
		Bouton carte_9 = new Bouton("9", 2, 4);
		Image img_9 = ImageIO.read(new File(this.nomImage2.get(3)));
		carte_9.setImg(img_9);
		this.add(carte_9);
		this.collectCard2.add(carte_9);
		
		Bouton carte_10 = new Bouton("10", 2, 5);
		Image img_10 = ImageIO.read(new File(this.nomImage2.get(4)));
		carte_10.setImg(img_10);
		this.add(carte_10);
		this.collectCard2.add(carte_10);
		
		Bouton carte_11 = new Bouton("11", 3, 1);
		Image img_11 = ImageIO.read(new File(this.nomImage3.get(0)));
		carte_11.setImg(img_11);
		this.add(carte_11);
		this.collectCard3.add(carte_11);
		
		Bouton carte_12 = new Bouton("12", 3, 2);
		Image img_12 = ImageIO.read(new File(this.nomImage3.get(1)));
		carte_12.setImg(img_12);
		this.add(carte_12);
		this.collectCard3.add(carte_12);
		
		Bouton carte_13 = new Bouton("13", 3, 3);
		Image img_13 = ImageIO.read(new File(this.nomImage3.get(2)));
		carte_13.setImg(img_13);
		this.add(carte_13);
		this.collectCard3.add(carte_13);
		
		Bouton carte_14 = new Bouton("14", 3, 4);
		Image img_14 = ImageIO.read(new File(this.nomImage3.get(3)));
		carte_14.setImg(img_14);
		this.add(carte_14);
		this.collectCard3.add(carte_14);
		
		Bouton carte_15 = new Bouton("15", 3, 5);
		Image img_15 = ImageIO.read(new File(this.nomImage3.get(4)));
		carte_15.setImg(img_15);
		this.add(carte_15);
		this.collectCard3.add(carte_15);
		
		Bouton carte_16 = new Bouton("16", 4, 1);
		Image img_16 = ImageIO.read(new File(this.nomImage4.get(0)));
		carte_16.setImg(img_16);
		this.add(carte_16);
		this.collectCard4.add(carte_16);
		
		Bouton carte_17 = new Bouton("17", 4, 2);
		Image img_17 = ImageIO.read(new File(this.nomImage4.get(1)));
		carte_17.setImg(img_17);
		this.add(carte_17);
		this.collectCard4.add(carte_17);
		
		Bouton carte_18 = new Bouton("18", 4, 3);
		Image img_18 = ImageIO.read(new File(this.nomImage4.get(2)));
		carte_18.setImg(img_18);
		this.add(carte_18);
		this.collectCard4.add(carte_18);
		
		Bouton carte_19 = new Bouton("19", 4, 4);
		Image img_19 = ImageIO.read(new File(this.nomImage4.get(3)));
		carte_19.setImg(img_19);
		this.add(carte_19);
		this.collectCard4.add(carte_19);
		
		Bouton carte_20 = new Bouton("20", 4, 5);
		Image img_20 = ImageIO.read(new File(this.nomImage4.get(4)));
		carte_20.setImg(img_20);
		this.add(carte_20);
		this.collectCard4.add(carte_20);
		
		Bouton carte_21 = new Bouton("21", 5, 1);
		Image img_21 = ImageIO.read(new File(this.nomImage5.get(0)));
		carte_21.setImg(img_21);
		this.add(carte_21);
		this.collectCard5.add(carte_21);
		
		Bouton carte_22 = new Bouton("22", 5, 2);
		Image img_22 = ImageIO.read(new File(this.nomImage5.get(1)));
		carte_22.setImg(img_22);
		this.add(carte_22);
		this.collectCard5.add(carte_22);
		
		Bouton carte_23 = new Bouton("23", 5, 3);
		Image img_23 = ImageIO.read(new File(this.nomImage5.get(2)));
		carte_23.setImg(img_23);
		this.add(carte_23);
		this.collectCard5.add(carte_23);
		
		Bouton carte_24 = new Bouton("24", 5, 4);
		Image img_24 = ImageIO.read(new File(this.nomImage5.get(3)));
		carte_24.setImg(img_24);
		this.add(carte_24);
		this.collectCard5.add(carte_24);
		
		Bouton carte_25 = new Bouton("25", 5, 5);
		Image img_25 = ImageIO.read(new File(this.nomImage5.get(4)));
		carte_25.setImg(img_25);
		this.add(carte_25);
		this.collectCard5.add(carte_25);
	}
	/**
	 * Bouton pour ajouter les HandLers aux Bouton du {@link Terrain} de jeu
	 * @param g le {@link GraphicController}
	 */
	public void addHandler(GraphicController g) {
		this.collectCardTotal.get(0).get(0).addActionListener(new PlaySet(this.collectCardTotal.get(0).get(0), g));
		this.collectCardTotal.get(0).get(1).addActionListener(new PlaySet(this.collectCardTotal.get(0).get(1), g));
		this.collectCardTotal.get(0).get(2).addActionListener(new PlaySet(this.collectCardTotal.get(0).get(2), g));
		this.collectCardTotal.get(0).get(3).addActionListener(new PlaySet(this.collectCardTotal.get(0).get(3), g));
		this.collectCardTotal.get(0).get(4).addActionListener(new PlaySet(this.collectCardTotal.get(0).get(4), g));
		this.collectCardTotal.get(1).get(0).addActionListener(new PlaySet(this.collectCardTotal.get(1).get(0), g));
		this.collectCardTotal.get(1).get(1).addActionListener(new PlaySet(this.collectCardTotal.get(1).get(1), g));
		this.collectCardTotal.get(1).get(2).addActionListener(new PlaySet(this.collectCardTotal.get(1).get(2), g));
		this.collectCardTotal.get(1).get(3).addActionListener(new PlaySet(this.collectCardTotal.get(1).get(3), g));
		this.collectCardTotal.get(1).get(4).addActionListener(new PlaySet(this.collectCardTotal.get(1).get(4), g));
		this.collectCardTotal.get(2).get(0).addActionListener(new PlaySet(this.collectCardTotal.get(2).get(0), g));
		this.collectCardTotal.get(2).get(1).addActionListener(new PlaySet(this.collectCardTotal.get(2).get(1), g));
		this.collectCardTotal.get(2).get(2).addActionListener(new PlaySet(this.collectCardTotal.get(2).get(2), g));
		this.collectCardTotal.get(2).get(3).addActionListener(new PlaySet(this.collectCardTotal.get(2).get(3), g));
		this.collectCardTotal.get(2).get(4).addActionListener(new PlaySet(this.collectCardTotal.get(2).get(4), g));
		this.collectCardTotal.get(3).get(0).addActionListener(new PlaySet(this.collectCardTotal.get(3).get(0), g));
		this.collectCardTotal.get(3).get(1).addActionListener(new PlaySet(this.collectCardTotal.get(3).get(1), g));
		this.collectCardTotal.get(3).get(2).addActionListener(new PlaySet(this.collectCardTotal.get(3).get(2), g));
		this.collectCardTotal.get(3).get(3).addActionListener(new PlaySet(this.collectCardTotal.get(3).get(3), g));
		this.collectCardTotal.get(3).get(4).addActionListener(new PlaySet(this.collectCardTotal.get(3).get(4), g));
		this.collectCardTotal.get(4).get(0).addActionListener(new PlaySet(this.collectCardTotal.get(4).get(0), g));
		this.collectCardTotal.get(4).get(1).addActionListener(new PlaySet(this.collectCardTotal.get(4).get(1), g));
		this.collectCardTotal.get(4).get(2).addActionListener(new PlaySet(this.collectCardTotal.get(4).get(2), g));
		this.collectCardTotal.get(4).get(3).addActionListener(new PlaySet(this.collectCardTotal.get(4).get(3), g));
		this.collectCardTotal.get(4).get(4).addActionListener(new PlaySet(this.collectCardTotal.get(4).get(4), g));
	}
	/**
	 * Bouton pour remplir les éléments 
	 */
	public void ini() {
		this.collectCardTotal.add(collectCard1);
		this.collectCardTotal.add(collectCard2);
		this.collectCardTotal.add(collectCard3);
		this.collectCardTotal.add(collectCard4);
		this.collectCardTotal.add(collectCard5);
	}
	/**
	 * Mettre à jour la vue du {@link Terrain}
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
			ArrayList<ArrayList<Card>> fieldView = ((Terrain) o).getFieldView();
				int i = 0;
				for(ArrayList<Card> aCard : fieldView)
				{
					String str = new String();
					int j = 0;
					for(Card c : aCard) {
						if(c == null)
							str = "aaa.jpg";
						else
							str = c.getIDCARD() + ".jpg";
						this.collectCardTotal.get(i).get(j).setImage(str);
						this.collectCardTotal.get(i).get(j).setText("");
						this.nomImageTotal.get(i).set(j, str);
						j++;
					}
					i++;
				}
				this.repaint();
	}
}
