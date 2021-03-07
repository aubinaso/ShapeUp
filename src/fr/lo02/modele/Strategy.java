package fr.lo02.modele;
/**
 * Cette interface definie les m�thodes � impl�menter dans la Stategie des {@link VirtualPlayer}
 * @author Aubin Patrick TAKAM TAYO - Universit� de Technologie de Troyes - R�seau et T�l�communication Semestre 01- Automne 2020 - LO02 
 * @see VirtualPlayer
 * @see Player
 * @see EasyVirtualPlayer
 * @see IntermediateVirtualPlayer
 * @see DifficultVirtualPlayer
 */
public interface Strategy {
	/**
     * cette methode doit d�finir la mani�re dont le {@link VirtualPlayer} va ajouter une {@link Card}
     * sur le {@link Terrain}
     * @param field {@link Terrain} qui est le terrain sur lequel il va ajouter la {@link Card}
     * @param card {@link Card} � ajouter sur le {@link Terrain}
     */
	public void jouerCard(Terrain field, Card card);
	/**
	 * cette m�thode d�finie la mani�re avec laquelle le {@link VirtualPlayer} va d�placer une {@link Card}
	 * sur le {@link Terrain}
	 * @param field {@link Terrain} de la {@link Partie}
	 */
	public void deplacerCard(Terrain field);
	/**
	 * cette m�thode d�finie le choix du {@link VirtualPlayer}
	 * de {@link Strategy#jouerCard(Terrain, Card)} ou de 
	 * {@link Strategy#deplacerCard(Terrain)} avant ou apr�s
	 * @param field {@link Terrain} de la {@link Partie}
	 * @return {@link Boolean#TRUE} si il joue avant ou {@link Boolean#FALSE} si il d�place avant
	 */
	public boolean playFirst(Terrain field);
}
