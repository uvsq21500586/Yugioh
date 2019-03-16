package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;

public abstract class EquipmentSpellCard extends SpellCard {

	protected MonsterCard monsterlinked;
	public EquipmentSpellCard(String type2, String name, String desc) {
		super(type2, name, desc);
		monsterlinked = null;
		// TODO Auto-generated constructor stub
		targetspell = false;
	}

	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public MonsterCard getMonsterlinked() {
		return monsterlinked;
	}

	public void setMonsterlinked(MonsterCard monsterlinked) {
		this.monsterlinked = monsterlinked;
	}

	public abstract boolean monstercondition (MonsterCard monster);
	
	public void gotograveyard() {
		// TODO Auto-generated method stub
	}
}
