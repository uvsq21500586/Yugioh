package eg.edu.guc.yugioh.cards;

import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;;

public class FusionMonsterCard extends MonsterCard {

	protected int nbmaterielsfusions;
	
	public FusionMonsterCard(MonsterCard monster) {
		super(monster);
		// TODO Auto-generated constructor stub
		nbmaterielsfusions = 2;
	}
	
	
	
	public int getNbmaterielsfusions() {
		return nbmaterielsfusions;
	}



	public void setNbmaterielsfusions(int nbmaterielsfusions) {
		this.nbmaterielsfusions = nbmaterielsfusions;
	}



	public boolean compatiblemonster(MonsterCard monster,int i){
		return false;	
	}

	
}
