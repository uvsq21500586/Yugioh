package eg.edu.guc.yugioh.board.player;

import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

public interface Duelist {

	//public boolean summonMonster(MonsterCard monster);
	
	public boolean summonMonster(MonsterCard monster,int placemonster);
	 
	public boolean summonMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices,int placemonster);
	 
	public boolean setMonster(MonsterCard monster,int placemonster);

	public boolean setMonster(MonsterCard monster, ArrayList<MonsterCard> sacrifices,int placemonster);
	 
	public boolean setSpell(SpellCard spell);
	 
	public boolean activateSpell(SpellCard spell, MonsterCard monster);

	public boolean declareAttack(MonsterCard monster);
	 
	public boolean declareAttack(MonsterCard activeMonster, MonsterCard opponentMonster);
	 
	public void addCardToHand();
	 
	public void addNCardsToHand(int n);
	 
	public void endPhase();
	 
	public boolean endTurn();
	 
	public boolean switchMonsterMode(MonsterCard monster);
}
