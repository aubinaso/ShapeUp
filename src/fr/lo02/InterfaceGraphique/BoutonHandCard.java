package fr.lo02.InterfaceGraphique;

import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import fr.lo02.controler.GraphicController;
import fr.lo02.modele.Card;
import fr.lo02.modele.RealPlayer;
import fr.lo02.modele.Terrain;
import fr.lo02.modele.Update;
/**
 * Emplacement d'un {@link RealPlayer} dans la main d'un {@link RealPlayer} ({@link HandCard})
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see GraphicController
 * @see GamePanel
 * @see Image
 * @see File
 * @see Graphics
 * @see GradientPaint
 * @see Bouton
 * @see HandCard
 */
public class BoutonHandCard extends Bouton implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * La {@link HandCard} associée à ce BoutonHandCard
	 * elle représente la main d'un {@link RealPlayer}
	 */
	private HandCard handCard;
	
	/**
	 * Le constructeur du BoutonHandCard
	 * @param img une image
	 */
	public BoutonHandCard(ImageIcon img) {
		super(img);
		this.handCard = new HandCard();
		// TODO Auto-generated constructor stub
	}
	/**
	 * le Constructeur du BoutonHandCard
	 * @param str le nom du BoutonHandCard
	 * @param ligne l'indice ligne de l'emplacement du Bouton
	 * @param colonne l'indice colonne de l'emplacement du Bouton
	 */
	public BoutonHandCard(String str, int ligne, int colonne){
		// TODO Auto-generated constructor stub
	    super(str, ligne, colonne);
	    this.name = str;
	    this.card = "aaa";
	    this.ligne = ligne;
	    this.colonne = colonne;
	    this.handCard = new HandCard();
	  
	    try {
	        img = ImageIO.read(new File("xxx.jpg"));
	      } catch (IOException e) {
	        e.printStackTrace();
	      }
	  }
	/**
	 * Pour mettre à jour la {@link HandCard} du {@link RealPlayer}
	 * elle met à jour
	 * la VictoryCard, la NextCard et la HandCard du Joueur
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		if(o instanceof RealPlayer) {
			if(((Update)arg) == Update.No) {
				int i = 0;
				String str;
				this.handCard.initHandCard();
				
				for(Card c : ((RealPlayer)o).getHandCard()) {
					str = c.getIDCARD();
					if(str == null)
						str = "xxx";
					str = str + ".jpg";
					if(this.handCard.getCard().size()>i)
						this.handCard.getCard().get(i).setImage(str);
					i++;
				}
				if (((RealPlayer)o).getNextCard() == null)
					str = "xxx";
				else
					str = ((RealPlayer)o).getNextCard().getIDCARD();
				str = str + ".jpg";
				this.handCard.getNextCard().setImage(str);
				if (((RealPlayer)o).getVictoryCard() == null)
					str = "aaa";
				else
					str = ((RealPlayer)o).getVictoryCard().getIDCARD();
				str = str + ".jpg";
				this.handCard.getVictoryCard().setImage(str);
				this.handCard.getFrame().repaint();
			}
		}
	}
	/**
	 * getters de la {@link HandCard}
	 * @return {@link BoutonHandCard#handCard}
	 */
	public HandCard getHandCard() {
		return handCard;
	}
}
