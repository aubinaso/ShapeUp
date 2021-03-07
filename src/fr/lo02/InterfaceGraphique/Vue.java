package fr.lo02.InterfaceGraphique;

import java.io.BufferedReader;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.channels.Channels;
import java.util.InputMismatchException;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

import fr.lo02.modele.*;
/**
 * classe d�finissant une vue de la {@link Partie}
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seaux et T�l�communication Semestre 01 - Automne 2020 - LO02
 *
 * @see VueGraphique
 * 
 * Non impl�ment�
 */
public class Vue implements Observer{
	/**
	 * Mod�le qu'elle g�re, ici c'est la {@link Partie}
	 */
	protected Partie partie;
	
	/**
	 * Constructeurs de {@link Vue}
	 * @param p la {@link Partie}
	 */
	public Vue(Partie p) {
		this.partie = p;
		partie.addObserver(this);
		partie.getConfiguration().addObserver(this);
	}
	/**
	 * m�thode de mise � jour suite � une modification dans la Partie
	 */
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
}
