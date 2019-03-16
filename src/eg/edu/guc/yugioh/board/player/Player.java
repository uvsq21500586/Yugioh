package eg.edu.guc.yugioh.board.player;

import java.io.IOException;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.exceptions.MultipleMonsterAdditionException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;

public class Player implements Duelist {

	private final String name;
	private int lifePoints;
	private Field field;
	private boolean addedMonsterThisTurn;
	private int numeroturn;
	private String evenement;

	public Player(String name,String monsterp,String spellp,String monsterextrap) throws IOException, UnexpectedFormatException, NumberFormatException, InstantiationException, IllegalAccessException {

		this.name = name;
		this.lifePoints = 8000;
		this.field = new Field(monsterp,spellp,monsterextrap);
		addedMonsterThisTurn = false;
		numeroturn = 1;
		evenement = "passe";

	}

	
	public String getEvenement() {
		return evenement;
	}


	public void setEvenement(String evenement) {
		this.evenement = evenement;
	}


	public int getNumeroturn() {
		return numeroturn;
	}

	public void setNumeroturn(int numeroturn) {
		this.numeroturn = numeroturn;
	}
/*
	@Override
	public boolean summonMonster(MonsterCard monster) {

		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		if (addedMonsterThisTurn)
			throw new MultipleMonsterAdditionException();

		boolean monsterAdded = this.field.addMonsterToField(monster,
				Mode.ATTACK, false);

		if (!monsterAdded)
			return false;

		addedMonsterThisTurn = true;

		return true;

	}*/

	@Override
	public boolean summonMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices,int placemonster) {

		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		if (addedMonsterThisTurn)
			throw new MultipleMonsterAdditionException();

		boolean monsterAdded = this.field.addMonsterToField(monster,
				Mode.ATTACK, sacrifices, placemonster);

		if (!monsterAdded)
			return false;

		addedMonsterThisTurn = true;

		return true;

	}

	@Override
	public boolean setMonster(MonsterCard monster,int placemonster) {

		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		if (addedMonsterThisTurn)
			throw new MultipleMonsterAdditionException();

		boolean monsterAdded = this.field.addMonsterToField(monster,
				placemonster, Mode.DEFENSE, true);

		if (!monsterAdded)
			return false;

		addedMonsterThisTurn = true;

		return true;

	}

	@Override
	public boolean setMonster(MonsterCard monster,
			ArrayList<MonsterCard> sacrifices,int placemonster) {

		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		if (addedMonsterThisTurn)
			throw new MultipleMonsterAdditionException();

		boolean monsterAdded = this.field.addMonsterToField(monster,
				Mode.DEFENSE, sacrifices, placemonster);

		if (!monsterAdded)
			return false;

		addedMonsterThisTurn = true;

		return true;

	}

	@Override
	public boolean setSpell(SpellCard spell) {

		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		boolean spellAdded = this.field.addSpellToField(spell, null, true);

		return spellAdded;

	}

	@Override
	public boolean activateSpell(SpellCard spell, MonsterCard monster) {

		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		boolean spellActivated;

		
		if (this.field.getSpellArea().contains(spell))
			spellActivated = this.field.activateSetSpell(spell, monster);
		else
			spellActivated = this.field.addSpellToField(spell, monster, false);
		if (spellActivated) {
			spell.setHidden(false);
		}
		return spellActivated;

	}

	@Override
	public boolean declareAttack(MonsterCard monster) {

		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		boolean monsterAttacked = this.field.declareAttack(monster, null);

		return monsterAttacked;

	}

	@Override
	public boolean declareAttack(MonsterCard activeMonster,
			MonsterCard opponentMonster) {

		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		boolean monsterAttacked = this.field.declareAttack(activeMonster,
				opponentMonster);

		return monsterAttacked;

	}

	@Override
	public void endPhase() {

		if (Card.getBoard().isGameOver())
			return;

		if (this != Card.getBoard().getActivePlayer())
			return;

		this.getField().endPhase();

	}

	@Override
	public boolean endTurn() {

		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		addedMonsterThisTurn = false;
		this.getField().endTurn();

		return true;

	}

	@Override
	public boolean switchMonsterMode(MonsterCard monster) {

		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		boolean monsterSwitched = this.field.switchMonsterMode(monster);

		return monsterSwitched;

	}

	@Override
	public void addCardToHand() {

		this.field.addCardToHand();

	}

	@Override
	public void addNCardsToHand(int n) {

		this.field.addNCardsToHand(n);

	}

	public int getLifePoints() {
		return lifePoints;
	}

	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}

	public String getName() {
		return name;
	}

	public Field getField() {
		return field;
	}

	@Override
	public boolean summonMonster(MonsterCard monster,int placemonster) {
		// TODO Auto-generated method stub
		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		if (addedMonsterThisTurn)
			throw new MultipleMonsterAdditionException();

		boolean monsterAdded = this.field.addMonsterToField(monster,
				placemonster, Mode.ATTACK, false);

		if (!monsterAdded)
			return false;

		addedMonsterThisTurn = true;

		return true;

		
	}

	public boolean activateSpell2(SpellCard spell, SpellCard spell2) {
		// TODO Auto-generated method stub
		if (Card.getBoard().isGameOver())
			return false;

		if (this != Card.getBoard().getActivePlayer())
			return false;

		boolean spellActivated;

		
		if (this.field.getSpellArea().contains(spell))
			spellActivated = this.field.activateSetSpell2(spell, spell2);
		else
			spellActivated = this.field.addSpellToField2(spell, spell2, false);
		if (spellActivated) {
			spell.setHidden(false);
		}
		return spellActivated;
	}

}
