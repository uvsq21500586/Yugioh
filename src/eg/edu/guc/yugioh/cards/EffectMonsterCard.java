package eg.edu.guc.yugioh.cards;

import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.gui.Gui;

public class EffectMonsterCard extends MonsterCard {

	protected boolean jokermaterielfusion;
	protected boolean targetspell;
	protected boolean targetmonster;
	protected boolean multipletargetcard;
	//multipletargetmonster -> si la carte a plusieurs cibles
	protected ArrayList<String> locationcible;
	protected ArrayList<Integer> nombrecible;
	protected int nombretotalcible;
	private String typeeffect;
	//normal = se déchenche automatiquement, 
	//flip = se déchenche en passant de verso à recto sur le terrain
	//choix = le joueur peut choisir ou non d'activer l'effet
	public EffectMonsterCard(MonsterCard monster) {
		super(monster);
		// TODO Auto-generated constructor stub
		typeeffect = "normal";
		targetspell = false;
		targetmonster = false;
		multipletargetcard = false;
		locationcible = new ArrayList<String>();
		nombrecible = new ArrayList<Integer>();
		nombretotalcible = 0;
		jokermaterielfusion = false;
	}
	
	public boolean conditionactiveeffect(){
		return true;
	}
	
	public boolean isTargetspell() {
		return targetspell;
	}
	public void setTargetspell(boolean targetspell) {
		this.targetspell = targetspell;
	}
	
	
	public boolean isTargetmonster() {
		return targetmonster;
	}
	public void setTargetmonster(boolean targetmonster) {
		this.targetmonster = targetmonster;
	}
	public void action(MonsterCard monster) {
	}
	
	public void actionbis(SpellCard spell) {
	}
	
	public boolean monstercondition(MonsterCard monster) {
		return false;
	}
	
	public boolean compatible(SpellCard spell) {
		return false;
	}
	public boolean conditionactivation() {
		return true;
	}
	public void actionterrain(Gui gui) {
	}
	public void gotograveyard() {
	}
	public boolean isMultipletargetcard() {
		return multipletargetcard;
	}
	public void setMultipletargetcard(boolean multipletargetcard) {
		this.multipletargetcard = multipletargetcard;
	}
	public ArrayList<String> getLocationcible() {
		return locationcible;
	}
	public void setLocationcible(ArrayList<String> locationcible) {
		this.locationcible = locationcible;
	}
	public ArrayList<Integer> getNombrecible() {
		return nombrecible;
	}
	public void setNombrecible(ArrayList<Integer> nombrecible) {
		this.nombrecible = nombrecible;
	}
	public void actionmulti(ArrayList<Card> card) {
	}
	public int getNombretotalcible() {
		return nombretotalcible;
	}
	public void setNombretotalcible(int nombretotalcible) {
		this.nombretotalcible = nombretotalcible;
	}
	
	public boolean multicompatible(int element,Card card){
		return true;
		
	}

	public String getTypeeffect() {
		return typeeffect;
	}

	public void setTypeeffect(String typeeffect) {
		this.typeeffect = typeeffect;
	}

	public boolean isJokermaterielfusion() {
		return jokermaterielfusion;
	}

	public void setJokermaterielfusion(boolean jokermaterielfusion) {
		this.jokermaterielfusion = jokermaterielfusion;
	}
	
	

}
