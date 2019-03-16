package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.Gui;

import java.util.ArrayList;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.board.player.Player;

public abstract class SpellCard extends Card {

	protected String type2;
	protected boolean fusionspell;
	protected boolean targetspell;
	protected boolean targetmonster;
	protected boolean multipletargetcard;
	//multipletargetmonster -> si la carte a plusieurs cibles
	protected ArrayList<String> locationcible;
	protected ArrayList<Integer> nombrecible;
	protected int nombretotalcible;
	
	
	public SpellCard(String type2,String name, String desc) {

		super(name, desc);
		type = "SpellCard";
		this.type2 = type2;
		targetspell = false;
		targetmonster = false;
		multipletargetcard = false;
		locationcible = new ArrayList<String>();
		nombrecible = new ArrayList<Integer>();
		nombretotalcible = 0;
		fusionspell = false;
	}
	public String getType2() {
		return type2;
	}
	public void setType2(String type2) {
		this.type2 = type2;
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

	public boolean compatible(MonsterCard monster) {
		return true;
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
	
	
	public boolean isFusionspell() {
		return fusionspell;
	}
	public void setFusionspell(boolean fusionspell) {
		this.fusionspell = fusionspell;
	}
	public boolean multicompatible(int element,Card card){
		return true;
		
	}
	
}
