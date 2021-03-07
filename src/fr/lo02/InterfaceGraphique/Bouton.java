package fr.lo02.InterfaceGraphique;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import fr.lo02.controler.GraphicController;
import fr.lo02.modele.Card;
import fr.lo02.modele.Partie;
import fr.lo02.modele.RealPlayer;
import fr.lo02.modele.Terrain;
import fr.lo02.modele.Update;
/**
 * Emplacement d'une {@link Card} sur le {@link Terrain} ou la main d'un {@link RealPlayer} ({@link HandCard})
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see GraphicController
 * @see GamePanel
 * @see Image
 * @see File
 * @see Graphics
 * @see GradientPaint
 * @see JButton
 * @see HandCard
 */
public class Bouton extends JButton{
	private static final long serialVersionUID = 1L;
	/**
	 * le nom du Bouton
	 */
	protected String name;
	/**
	 * l'image occupant l'emplacement du Bouton
	 */
	protected Image img;
	/**
	 * l'Id de la {@link Card} occupant l'emplacement
	 */
	protected String card;
	/**
	 * l'indice ligne du Bouton sur le {@link Terrain}
	 */
	protected int ligne;
	/**
	 * l'indice colonne du Bouton sur le {@link Terrain}
	 */
	protected int colonne;
	
	/**
	 * Constructeur de Bouton
	 * @param str le nom du Bouton
	 * @param ligne son indice ligne sur le {@link Terrain}
	 * @param colonne son indice colonne sur le {@link Terrain}
	 */
	public Bouton(String str, int ligne, int colonne){
	    super(str);
	    this.name = str;
	    this.card = "aaa";
	    this.ligne = ligne;
	    this.colonne = colonne;
	    try {
	    	img = ImageIO.read(new File("xxx.jpg"));
	    } catch (IOException e) {
	    	e.printStackTrace();
	    }
	}
	/**
	 * Constructeur du Bouton
	 * @param img la nouvelle image
	 */
	public Bouton(ImageIcon img)
	{
		super(img);
	}
	/**
	 * Permet d'actualiser l'aspect de la {@link Card}
	 */
	public void paintComponent(Graphics g){
	  	Graphics2D g2d = (Graphics2D)g;
	    GradientPaint gp = new GradientPaint(0, 0, Color.blue, 0, 20, Color.cyan, true);
	    g2d.setPaint(gp);
	    g2d.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	    g2d.setColor(Color.black);
	    g2d.drawString(this.name, this.getWidth() / 2 - (this.getWidth() / 2 /4), (this.getHeight() / 2) + 5);
    }  
	/**
	 * permet de mettre à jour une image et ainsi de faire une corrspondans et actualiser le nom de l'image
	 * qui est l'Id de la {@link Card}
	 * @param str l'id de la {@link Card} devant occuper l'emplacement
	 */
	public void setImage(String str) {
		this.card = str.substring(0, 3);
		try {
			this.img = ImageIO.read(new File(str));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * permet de mettre à Jour la {@link Card} occupant un emplacement
	 * @param str Id de la {@link Card} qui va occuper l'emplacement du Bouton
	 */
	public void setPseudo(String str) {
		this.setText(str);
		this.name = str;
		this.repaint();
	}
	/**
	 * le getter d'emplacement ligne de ce Bouton	
	 * @return un entier
	 */
	public int getLigne() {
		return ligne;
	}
	/**
	 * le getter d'emplacement colonne de ce Bouton	
	 * @return un entier
	 */
	public int getColonne() {
		return colonne;
	}
	/**
	 * le getter du nom de ce Bouton	
	 * @return le nom de ce Bouton
	 */
	public String getName() {
		return name;
	}
	/**
	 * met à jour le nom de ce Bouton
	 * @param name le nouveau nom de ce Bouton
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * le getter l'image occupant l'emplacement de ce Bouton	
	 * @return une Image
	 */
	public Image getImg() {
		return img;
	}
	/**
	 * met à jour l'image occupant ce Bouton
	 * @param img la nouvelle image
	 */
	public void setImg(Image img) {
		this.img = img;
	}	
	/**
	 * le getter la {@link Card} occupant d'emplacement de ce Bouton	
	 * @return l'id de la {@link Card}
	 */
	public String getCard() {
		return card;
	}
}
