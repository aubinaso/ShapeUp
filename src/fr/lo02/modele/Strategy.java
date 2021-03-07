package fr.lo02.modele;
/**
 * Cette interface definie les méthodes à implémenter dans la Stategie des {@link VirtualPlayer}
 * @author Aubin Patrick TAKAM TAYO - Université de Technologie de Troyes - Réseau et Télécommunication Semestre 01- Automne 2020 - LO02 
 * @see VirtualPlayer
 * @see Player
 * @see EasyVirtualPlayer
 * @see IntermediateVirtualPlayer
 * @see DifficultVirtualPlayer
 */
public interface Strategy {
	/**
     * cette methode doit définir la manière dont le {@link VirtualPlayer} va ajouter une {@link Card}
     * sur le {@link Terrain}
     * @param field {@link Terrain} qui est le terrain sur lequel il va ajouter la {@link Card}
     * @param card {@link Card} à ajouter sur le {@link Terrain}
     */
	public void jouerCard(Terrain field, Card card);
	/**
	 * cette méthode définie la manière avec laquelle le {@link VirtualPlayer} va déplacer une {@link Card}
	 * sur le {@link Terrain}
	 * @param field {@link Terrain} de la {@link Partie}
	 */
	public void deplacerCard(Terrain field);
	/**
	 * cette méthode définie le choix du {@link VirtualPlayer}
	 * de {@link Strategy#jouerCard(Terrain, Card)} ou de 
	 * {@link Strategy#deplacerCard(Terrain)} avant ou après
	 * @param field {@link Terrain} de la {@link Partie}
	 * @return {@link Boolean#TRUE} si il joue avant ou {@link Boolean#FALSE} si il déplace avant
	 */
	public boolean playFirst(Terrain field);
}
