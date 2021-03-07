package fr.lo02.InterfaceGraphique;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import fr.lo02.controler.GraphicController;
import fr.lo02.modele.Configuration;
import fr.lo02.modele.Partie;
import fr.lo02.modele.Player;
/**
 * Ecran de fin de {@link Partie} affichant les score finales des tours joué
 * c'est dans cet écran également que les {@link Player} joue
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see Partie
 * @see VueGraphique
 * @see Vue
 * @see GraphicController
 * @see ConfigTerrain
 */
public class PanelScore extends JPanel{
	/**
	 * Label contenant le nom des Vainqueurs de tour de {@link Partie}
	 */
	JLabel labelName;
	/**
	 * Label contenant le score des Vainqueurs de tour de {@link Partie}
	 */
	JLabel labelScore;
	/**
	 * Label contenant le numéro de la {@link Partie}
	 */
	JLabel labelPartie;
	
	/**
	 * Constructeur du {@link PanelScore}
	 * @param value pour définir la position de ce Panel dans le {@link ConfigEnd}
	 */
	public PanelScore(int value) {
		this.setBounds(10, value, 901, 94);
		this.setLayout(null);
		
		labelPartie = new JLabel("1");
		labelPartie.setHorizontalAlignment(SwingConstants.CENTER);
		labelPartie.setForeground(Color.BLUE);
		labelPartie.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		labelPartie.setBounds(10, 5, 76, 79);
		this.add(labelPartie);
		
		this.labelName = new JLabel("1");
		this.labelScore = new JLabel("2");
		labelName.setHorizontalAlignment(SwingConstants.CENTER);
		labelName.setForeground(Color.BLUE);
		labelName.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		labelName.setBounds(10, 5, 450, 79);
		this.add(labelName);
		
		labelScore.setHorizontalAlignment(SwingConstants.CENTER);
		labelScore.setForeground(Color.BLUE);
		labelScore.setFont(new Font("Myanmar Text", Font.BOLD, 14));
		labelScore.setBounds(470, 5, 335, 79);
		this.add(labelScore);
	}
	/**
	 * Initialize the contents of the {@link PanelScore#labelPartie}
	 * @param i numero de {@link Partie}
	 */
	public void setLabelPartie(int i) {
		this.labelPartie.setText("" + i + "");
		this.repaint();
	}
	/**
	 * Initialize the contents of the {@link PanelScore#labelName}
	 * @param str le nom du Vainqueurs de la {@link Partie}
	 */
	public void setLabelName(String str) {
		this.labelName.setText(str);
		this.repaint();
	}
	/**
	 * Initialize the contents of the {@link PanelScore#labelScore}
	 * @param str le score du Vainqueurs de la {@link Partie}
	 */
	public void setLabelScore(String str) {
		this.labelScore.setText(str);
		this.repaint();
	}
}
