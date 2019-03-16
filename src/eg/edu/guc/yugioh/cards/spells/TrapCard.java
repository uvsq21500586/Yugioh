package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.*;

public class TrapCard extends SpellCard {

	private boolean choisi;
	private boolean joue;
	private boolean ciblejoue;
	protected Card cible;
	//joue permet d'éviter qu'un joueur active la carte durant le même tour où elle est posée
	
	public TrapCard(String type2, String name, String desc) {
		super(type2, name, desc);
		// TODO Auto-generated constructor stub
		joue = false;
		ciblejoue = false;
		cible = null;
	}
	public boolean isChoisi() {
		return choisi;
	}
	public void setChoisi(boolean choisi) {
		this.choisi = choisi;
	}
	public boolean isJoue() {
		return joue;
	}
	public void setJoue(boolean joue) {
		this.joue = joue;
	}
	public boolean isCiblejoue() {
		return ciblejoue;
	}
	public void setCiblejoue(boolean ciblejoue) {
		this.ciblejoue = ciblejoue;
	}
	public Card getCible() {
		return cible;
	}
	public void setCible(Card cible) {
		this.cible = cible;
	}
	public void actionsurcible() {
		
	}
	
	
}
