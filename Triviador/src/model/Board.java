package model;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Board implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Set<Territory> territories;

	public Board() {
        territories = new HashSet<Territory>();
        addTerritories();
	}
	
	public void addTerritories() {
		Territory americaDelSur = new Territory("America del Sur", 100, 187, 346);
		Territory americaCentral = new Territory("America Central", 100, 138, 256);
		Territory americaDelNorte = new Territory("America del Norte", 100, 88, 159);
		Territory poloNorte = new Territory("Polo Norte", 100, 226, 34);
		Territory europa = new Territory("Europa", 100, 368, 131);
		Territory africa = new Territory("Africa", 100, 368, 294);
		Territory medioOriente = new Territory("MedioOriente", 100, 508, 188);
		Territory asia = new Territory("Asia", 100, 573, 98);
		Territory oceania = new Territory("Oceania", 100, 613, 359);
		
		americaDelSur.addAdjacents(americaCentral);
		americaDelSur.addAdjacents(africa);
		americaDelSur.addAdjacents(oceania);
		
		americaCentral.addAdjacents(americaDelSur);
		americaCentral.addAdjacents(americaDelNorte);
		
		americaDelNorte.addAdjacents(americaCentral);
		americaDelNorte.addAdjacents(poloNorte);
		americaDelNorte.addAdjacents(asia);
		
		poloNorte.addAdjacents(americaDelNorte);
		poloNorte.addAdjacents(europa);
		
		europa.addAdjacents(poloNorte);
		europa.addAdjacents(asia);
		europa.addAdjacents(medioOriente);
		europa.addAdjacents(africa);
		
		africa.addAdjacents(americaDelSur);
		africa.addAdjacents(europa);
		africa.addAdjacents(medioOriente);
		africa.addAdjacents(oceania);
		
		medioOriente.addAdjacents(europa);
		medioOriente.addAdjacents(asia);
		medioOriente.addAdjacents(africa);
		medioOriente.addAdjacents(oceania);
		
		asia.addAdjacents(medioOriente);
		asia.addAdjacents(europa);
		asia.addAdjacents(americaDelNorte);
		
		oceania.addAdjacents(africa);
		oceania.addAdjacents(medioOriente);
		oceania.addAdjacents(americaDelSur);
		
		territories.add(americaDelSur);
		territories.add(americaCentral);
		territories.add(americaDelNorte);
		territories.add(poloNorte);
		territories.add(europa);
		territories.add(africa);
		territories.add(medioOriente);
		territories.add(asia);
		territories.add(oceania);
	}

	public void distributeTerritories(ArrayList<Player> players) {
		int i = 0;  /* Este metodo solo se usa al principio de la partida! */
		for(Territory t: territories) {
			territories.add(t);
            players.get(i%3).addTerritories(t);
            i++;
		}
	}

	public Set<Territory> getTerritories() {
		return territories;
	}
}
