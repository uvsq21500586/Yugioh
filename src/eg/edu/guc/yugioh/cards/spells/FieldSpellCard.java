package eg.edu.guc.yugioh.cards.spells;

import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.gui.Gui;

public abstract class FieldSpellCard extends SpellCard {

	public FieldSpellCard(String type2, String name, String desc) {
		super(type2, name, desc);
		// TODO Auto-generated constructor stub
		targetspell = false;
	}
/*
	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		
	}*/
	abstract public void gotograveyard(Gui gui);

	abstract public void setmonster(MonsterCard monster);

	abstract public void action2(Gui gui);
}
