package eg.edu.guc.yugioh.board.player;

import eg.edu.guc.yugioh.cards.Card;
import eg.edu.guc.yugioh.cards.Location;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.translater;
import eg.edu.guc.yugioh.cards.spells.SpellCard;
import eg.edu.guc.yugioh.cards.spells.EquipmentSpellCard;
import eg.edu.guc.yugioh.exceptions.*;
import eg.edu.guc.yugioh.exceptions.MonsterMultipleAttackException;
import eg.edu.guc.yugioh.exceptions.NoMonsterSpaceException;
import eg.edu.guc.yugioh.exceptions.NoSpellSpaceException;
import eg.edu.guc.yugioh.exceptions.UnexpectedFormatException;
import eg.edu.guc.yugioh.exceptions.WrongPhaseException;
import eg.edu.guc.yugioh.board.player.Player;

import java.io.IOException;
import java.util.ArrayList;

public class Field {

	private Phase phase = Phase.MAIN1;
	//private final Deck deck;
	private Deck deck;
	private ExtraDeck extradeck;
	private MonsterCard monstersArea2[];
	private MonsterCard monstersExtraArea2[];
	private ArrayList<SpellCard> spellArea;
	private ArrayList<Card> hand;
	private ArrayList<Card> graveyard;
	private ArrayList<SpellCard> fieldarea;
	private ArrayList<Card> bannished;
	
	public MonsterCard[] getMonstersExtraArea2() {
		return monstersExtraArea2;
	}

	public void setMonstersExtraArea2(MonsterCard[] monstersExtraArea2) {
		this.monstersExtraArea2 = monstersExtraArea2;
	}

	public MonsterCard[] getMonstersArea2() {
		return monstersArea2;
	}

	public void setMonstersArea2(MonsterCard[] monstersArea2) {
		this.monstersArea2 = monstersArea2;
	}

	public ArrayList<SpellCard> getFieldarea() {
		return fieldarea;
	}

	public void setFieldarea(ArrayList<SpellCard> fieldarea) {
		this.fieldarea = fieldarea;
	}

	public Field(String monsterp,String spellp,String monsterextrap) throws IOException, UnexpectedFormatException, NumberFormatException, InstantiationException, IllegalAccessException {

		//monstersArea = new ArrayList<MonsterCard>();
		monstersArea2 = new MonsterCard[5];
		monstersExtraArea2 = new MonsterCard[1];
		for (int i=0;i<5;i++){
			monstersArea2[i]=null;
		}
		spellArea = new ArrayList<SpellCard>();
		fieldarea = new ArrayList<SpellCard>();
		hand = new ArrayList<Card>();
		graveyard = new ArrayList<Card>();
		deck = new Deck(monsterp, spellp);
		extradeck = new ExtraDeck(monsterextrap);
		bannished = new ArrayList<Card>();

	}

	public int countMonstersonField(){
		int count=0;
		for (int i=0;i<5;i++){
			if (monstersArea2[i]!=null){
				count++;
			}
		}
		if (monstersExtraArea2[0]!=null){
			count++;
		}
		return count;
	}
	
	public int countMonstersonGraveyard(){
		int count=0;
		for (int i=0;i<graveyard.size();i++){
			if (graveyard.get(i) instanceof MonsterCard ){
				count++;
			}
		}
		return count;
	}
	
	/*
	public boolean addMonsterToField(MonsterCard monster, Mode m,
			boolean isHidden) {

		if (!(hand.contains(monster) && monster.getLocation() == Location.HAND))
			return false;
		
		
/*
		if (monstersArea.size() >= 5)
			throw new NoMonsterSpaceException();

		if (countMonstersonField() >= 5)
			throw new NoMonsterSpaceException();
		
		if (phase == Phase.BATTLE)
			throw new WrongPhaseException();

		hand.remove(monster);
		monster.setHidden(isHidden);
		monster.setMode(m);
		monster.setLocation(Location.FIELD);
		//monstersArea.add(monster);
		return true;

	}*/
	public boolean addMonsterToField(MonsterCard monster,int place, Mode m,
			boolean isHidden) {

		if (!(hand.contains(monster) && monster.getLocation() == Location.HAND))
			return false;
		
		

		if (monstersArea2[place]!=null)
			throw new NoMonsterSpaceException();
		
		if (phase == Phase.BATTLE)
			throw new WrongPhaseException();

		hand.remove(monster);
		monster.setHidden(isHidden);
		monster.setMode(m);
		monster.setSwitchedMode(true);
		monster.setLocation(Location.FIELD);
		monstersArea2[place] = monster;
		return true;

	}
//invocation d'un monstre par saccrifice
	public boolean addMonsterToField(MonsterCard monster, Mode m,
			ArrayList<MonsterCard> sacrifices,int placemonster) {

		if (!(hand.contains(monster) && monster.getLocation() == Location.HAND))
			return false;

		if (monster.getLevel() <= 4) {
			if (sacrifices != null)
				return false;
		} else if (monster.getLevel() <= 6) {
			if (sacrifices.size() != 1)
				return false;
		} else {
			if (sacrifices.size() != 2)
				return false;
		}
		
		if (sacrifices != null) {
			removeMonsterToGraveyard(sacrifices);
		}

		boolean hidden = (m == Mode.DEFENSE);

		boolean monsterAdded = addMonsterToField(monster, placemonster, m, hidden);

		if (!monsterAdded)
			return false;

		
		return true;

	}
/*
	public void removeMonsterToGraveyard(MonsterCard monster) {

		if (monstersArea.contains(monster)) {

			monstersArea.remove(monster);
			graveyard.add(monster);
			monster.setLocation(Location.GRAVEYARD);

		}

	}*/
	
	private void removeMonsterToGraveyard(ArrayList<MonsterCard> sacrifices) {
		for (int i=0;i<sacrifices.size();i++){
			if (sacrifices.get(i).getLocation()==Location.FIELD){
				//les équipements attachés aux monstres à sacrifier vont au cimetière
				for (int j=0;j<sacrifices.get(i).getBoard().getOpponentPlayer().getField().getSpellArea().size();j++){
					SpellCard equipedspell = sacrifices.get(i).getBoard().getOpponentPlayer().getField().getSpellArea().get(j);
					if (equipedspell.getType2().equals("equipment")){
						if (((EquipmentSpellCard)equipedspell).getMonsterlinked().equals(sacrifices.get(i))) {
							removeSpellToGraveyard(equipedspell);
						}
					}
				}
				for (int j=0;j<sacrifices.get(i).getBoard().getActivePlayer().getField().getSpellArea().size();j++){
					SpellCard equipedspell = sacrifices.get(i).getBoard().getActivePlayer().getField().getSpellArea().get(j);
					if (equipedspell.getType2().equals("equipment")){
						if (((EquipmentSpellCard)equipedspell).getMonsterlinked().equals(sacrifices.get(i))) {
							removeSpellToGraveyard(equipedspell);
						}
					}
				}
				
				if (monstersExtraArea2[0]!=null && monstersExtraArea2[0].equals(sacrifices.get(i))) {
					MonsterCard monster = new MonsterCard(monstersExtraArea2[0]);
					monstersExtraArea2[0] = null;
					graveyard.add(monster);
					monster.setLocation(Location.GRAVEYARD);
				} else {
					int j=0;
					for (int k=0;k<5;k++){
						if (monstersArea2[k]!=null && monstersArea2[k].equals(sacrifices.get(i))){
							j=k;
						}
					}
					MonsterCard monster = new MonsterCard(monstersArea2[j]);
					monstersArea2[j] = null;
					graveyard.add(monster);
					monster.setLocation(Location.GRAVEYARD);
				}

				
			}
		}
		// TODO Auto-generated method stub
		
	}

	public void removeMonsterToGraveyard(int place) {

		MonsterCard monster = new MonsterCard(monstersArea2[place]);
		monstersArea2[place] = null;
		graveyard.add(monster);
		monster.setLocation(Location.GRAVEYARD);

	}
	
	public void removeMonsterToGraveyard(MonsterCard m) {

		if (MonsterInField(m)){
			MonsterCard monster;
			if (m.getType2().equals("normal")) {
				monster = new MonsterCard(monstersArea2[getpositioninMonsterField(m)]);
			} else {
				translater trans = new translater();
				monster = trans.translatedmonster( monstersExtraArea2[0].getName(), monstersExtraArea2[0].getDescription(), monstersExtraArea2[0].getAttribut(), monstersExtraArea2[0].getEspece(),
						monstersExtraArea2[0].getLevel(), monstersExtraArea2[0].getBaseattackPoints(), monstersExtraArea2[0].getBasedefensePoints(), monstersExtraArea2[0].getType2());
			}
			monstersArea2[getpositioninMonsterField(m)] = null;
			graveyard.add(monster);
			monster.setLocation(Location.GRAVEYARD);
		} else {
			translater trans = new translater();
			MonsterCard monster = trans.translatedmonster( monstersExtraArea2[0].getName(), monstersExtraArea2[0].getDescription(), monstersExtraArea2[0].getAttribut(), monstersExtraArea2[0].getEspece(),
					monstersExtraArea2[0].getLevel(), monstersExtraArea2[0].getBaseattackPoints(), monstersExtraArea2[0].getBasedefensePoints(), monstersExtraArea2[0].getType2());
					//MonsterCard(monstersExtraArea2[0]);
			monstersExtraArea2[0] = null;
			graveyard.add(monster);
			monster.setLocation(Location.GRAVEYARD);
		}

	}
	
	public void removeMonsterToHand(int place) {

		MonsterCard monster = new MonsterCard(monstersArea2[place]);
		monstersArea2[place] = null;
		hand.add(monster);
		monster.setLocation(Location.HAND);

	}
	
	public int getpositioninMonsterField(MonsterCard monster){
		int pos = 0;
		for (int i=0;i<5;i++) {
			if (monstersArea2[i] != null && monstersArea2[i].equals(monster)){
				pos = i;
			}
		}
		return pos;
	}
	
	public void removeMonsterExtraToGraveyard() {
		//MonsterCard monster0 = 
		translater trans = new translater();
		MonsterCard monster = trans.translatedmonster( monstersExtraArea2[0].getName(), monstersExtraArea2[0].getDescription(), monstersExtraArea2[0].getAttribut(), monstersExtraArea2[0].getEspece(),
				monstersExtraArea2[0].getLevel(), monstersExtraArea2[0].getBaseattackPoints(), monstersExtraArea2[0].getBasedefensePoints(), monstersExtraArea2[0].getType2());
				//MonsterCard(monstersExtraArea2[0]);
		monstersExtraArea2[0] = null;
		graveyard.add(monster);
		monster.setLocation(Location.GRAVEYARD);

	}
	
	public void removeMonsterExtraToExtra() {

		MonsterCard monster = new MonsterCard(monstersExtraArea2[0]);
		monstersArea2[0] = null;
		graveyard.add(monster);
		monster.setLocation(Location.EXTRADECK);

	}
	
	/*
	public void removeMonsterToGraveyard(MonsterCard monster,int place) {
		
		//if (monstersArea.contains(monster)) {

			//monstersArea.remove(monster);
			monstersArea2[place]=null;
			graveyard.add(monster);
			monster.setLocation(Location.GRAVEYARD);

		//}

	}*/
/*
	public void removeMonsterToGraveyard(ArrayList<MonsterCard> monsters) {

		for (int i = 0; i < monsters.size(); i++)
			removeMonsterToGraveyard(monsters.get(i));

	}*/
	public void removeMonsterToGraveyard(MonsterCard[] monsters,int i) {

		removeMonsterToGraveyard(i);

	}

	public boolean addSpellToField(SpellCard spell, MonsterCard monster,
			boolean hidden) {

		if (spell.getType2().equals("field")){
			if (!hand.contains(spell))
				return false;
			if (phase == Phase.BATTLE)
				throw new WrongPhaseException();
			if (fieldarea.size()>0)
				removeSpellfromFToGraveyard(fieldarea.get(0));
			
			hand.remove(spell);
			fieldarea.add(spell);
			spell.setLocation(Location.FIELD);
			return true;
		}
		if (!hand.contains(spell))
			return false;

		if (spellArea.size() >= 5)
			throw new NoSpellSpaceException();

		if (phase == Phase.BATTLE)
			throw new WrongPhaseException();
		/*
		if (spell.getType2().equals("equipment")){
			boolean compatiblemonster = ((EquipmentSpellCard) spell).monstercondition(monster);
			if (!compatiblemonster) {
				throw new IncompatibleMonster();
			}
			spell.action(monster);
			hand.remove(spell);
			spellArea.add(spell);
			spell.setLocation(Location.FIELD);
			spell.setHidden(false);
			return true;
		}*/
		if (!hidden) {
			if (spell.getType2().equals("equipment")){
				if (monster.isHidden()) {
					throw new IncompatibleMonster();
				}
				boolean compatiblemonster = ((EquipmentSpellCard) spell).monstercondition(monster);
				if (!compatiblemonster) {
					throw new IncompatibleMonster();
				}
				spell.action(monster);
				hand.remove(spell);
				spellArea.add(spell);
				spell.setLocation(Location.FIELD);
				spell.setHidden(false);
				return true;
			}
		}
		hand.remove(spell);
		spellArea.add(spell);
		spell.setLocation(Location.FIELD);

		if (!hidden)
			return activateSetSpell(spell, monster);

		return true;

	}

	public boolean activateSetSpell(SpellCard spell, MonsterCard monster) {

		if (spell.getType2().equals("field")){
			if (fieldarea.size()>0)		
				removeSpellfromFToGraveyard(fieldarea.get(0));
			spell.setLocation(Location.FIELD);
			return true;
		} else {
		if (!spellArea.contains(spell))
			return false;

		if (phase == Phase.BATTLE)
			throw new WrongPhaseException();
		if (spell.getType2().equals("equipment")){
			if (monster.isHidden()) {
				throw new IncompatibleMonster();
			}
			boolean compatiblemonster = ((EquipmentSpellCard) spell).monstercondition(monster);
			if (!compatiblemonster) {
				throw new IncompatibleMonster();
			}
			spell.action(monster);
			spell.setLocation(Location.FIELD);
			spell.setHidden(false);
			return true;
		}
		
		spell.action(monster);
		if (!spell.getType2().contains("continue") && !spell.isTimespell()){
			removeSpellToGraveyard(spell);
		}

		return true;
		}

	}
	
	public boolean activateSetSpell2(SpellCard spell, SpellCard spell2) {

		if (spell.getType2().equals("field")){
			if (fieldarea.size()>0)		
				removeSpellfromFToGraveyard(fieldarea.get(0));
			spell.setLocation(Location.FIELD);
			return true;
		} else {
		if (!spellArea.contains(spell))
			return false;

		if (phase == Phase.BATTLE)
			throw new WrongPhaseException();

		spell.actionbis(spell2);
		if (!spell.getType2().contains("continue") && !spell.isTimespell()){
			removeSpellToGraveyard(spell);
		}

		return true;
		}

	}

	public void removeSpellToGraveyard(SpellCard spell) {

		if (!spellArea.contains(spell))
			return;

		spell.gotograveyard();
		spellArea.remove(spell);
		graveyard.add(spell);
		spell.setLocation(Location.GRAVEYARD);

	}
	
	public void removeCardfromHandToGraveyard(Card card) {
		hand.remove(card);
		graveyard.add(card);
	}
	
	public void removeSpellfromFToGraveyard(SpellCard spell) {

		if (!fieldarea.contains(spell))
			return;

		fieldarea.remove(spell);
		graveyard.add(spell);
		spell.setLocation(Location.GRAVEYARD);

	}

	public void removeSpellToGraveyard(ArrayList<SpellCard> spells) {

		for (int i = 0; i < spells.size(); i++) {

			SpellCard c = spells.get(i);

			if (!spellArea.contains(c))
				continue;

			spellArea.remove(c);
			graveyard.add(c);
			c.setLocation(Location.GRAVEYARD);

		}

	}

	public boolean declareAttack(MonsterCard m1, MonsterCard m2) {

		if (phase != Phase.BATTLE)
			throw new WrongPhaseException();

		if (m1.getMode() != Mode.ATTACK)
			throw new DefenseMonsterAttackException();

		if (m1.isAttacked())
			throw new MonsterMultipleAttackException();

		MonsterCard[] oppMonstersArea = Card.getBoard()
				.getOpponentPlayer().getField().monstersArea2;

		if (m2 == null && Card.getBoard()
				.getOpponentPlayer().getField().countMonstersonField() == 0)
			m1.action();
		else if (m2 != null &&  Card.getBoard().getOpponentPlayer().getField().MonsterInField(m2)){
			int place = 0;
			boolean isinextraarea = true;
			for (int i=0;i<5;i++){
				if (oppMonstersArea[i] == m2){
					place = i;
					isinextraarea = false;
				}
			}
			m1.action(place);
			
		} else if (Card.getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0]!=null && Card.getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0].equals(m2)){
			m1.actionextra();
		}
		else
			return false;

		if (Card.getBoard().getActivePlayer().getLifePoints() <= 0) {
			Card.getBoard().getActivePlayer().setLifePoints(0);
			Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
		}
		if (Card.getBoard().getOpponentPlayer().getLifePoints() <= 0) {
			Card.getBoard().getOpponentPlayer().setLifePoints(0);
			Card.getBoard().setWinner(Card.getBoard().getActivePlayer());
		}

		return true;

	}

	public void endPhase() {

		switch (phase) {

		case MAIN1:
			setPhase(Phase.BATTLE);
			break;

		case BATTLE:
			setPhase(Phase.MAIN2);
			break;

		case MAIN2:
			endTurn();
			break;

		}

	}

	public void endTurn() {

		phase = Phase.MAIN1;

		for (int i=0;i<5;i++){
			if (monstersArea2[i]!=null){
				monstersArea2[i].setAttacked(false);
				monstersArea2[i].setSwitchedMode(false);
			}
		}
		
		if (monstersExtraArea2[0]!=null){
			monstersExtraArea2[0].setAttacked(false);
			monstersExtraArea2[0].setSwitchedMode(false);
		}
		/*
		for (MonsterCard m : monstersArea) {
			m.setAttacked(false);
			m.setSwitchedMode(false);
		}*/

		Card.getBoard().nextPlayer();
		
		Card.getBoard().getActivePlayer().setNumeroturn(Card.getBoard().getActivePlayer().getNumeroturn()+1);

	}

	public boolean MonsterInField(MonsterCard monster){
		for (int i=0;i<5;i++){
			if (monstersArea2[i] == monster){
				return true;
			}
		}
		return false;
	}
	public boolean switchMonsterMode(MonsterCard monster) {

		if (!MonsterInField(monster) && (monstersExtraArea2[0]==null || !monstersExtraArea2[0].equals(monster)))
			return false;

		if (phase == Phase.BATTLE)
			throw new WrongPhaseException();

		if (monster.isSwitchedMode())
			return false;

		monster.switchMode();
		monster.setSwitchedMode(true);

		return true;

	}

	public void addCardToHand() {

		if (deck.getDeck().size() == 0) {

			if (this == Card.getBoard().getActivePlayer().getField())
				Card.getBoard().setWinner(Card.getBoard().getOpponentPlayer());
			else
				Card.getBoard().setWinner(Card.getBoard().getActivePlayer());

			return;
		}

		Card temp = deck.drawOneCard();
		hand.add(temp);
		temp.setLocation(Location.HAND);

	}

	public void addNCardsToHand(int n) {

		for (int j = 0; j < n; j++)
			addCardToHand();

	}

	public Phase getPhase() {
		return phase;
	}

	public void setPhase(Phase phase) {
		this.phase = phase;
	}

	public Deck getDeck() {
		return deck;
	}
/*
	public ArrayList<MonsterCard> getMonstersArea() {
		return monstersArea;
	}*/

	public ArrayList<SpellCard> getSpellArea() {
		return spellArea;
	}

	public ArrayList<Card> getHand() {
		return hand;
	}

	public ArrayList<Card> getGraveyard() {
		return graveyard;
	}

	public int discardHand() {

		int discardedCards = hand.size();
		for (int i = 0; i < hand.size();)
			graveyard.add(hand.remove(i));
		return (discardedCards);

	}
/*
	public MonsterCard strongestMonsterInGraveyard() {

		MonsterCard strongest = new MonsterCard("", "", 0, 0, 0);
		int strongestValue = 0;
		for (int i = 0; i < graveyard.size(); i++) {

			if (graveyard.get(i) instanceof MonsterCard) {

				if (((MonsterCard) graveyard.get(i)).getAttackPoints() > strongestValue) {

					strongest = (MonsterCard) graveyard.get(i);
					strongestValue = ((MonsterCard) graveyard.get(i))
							.getAttackPoints();

				}

			}

		}

		return (strongest);

	}*/

	public boolean addSpellToField2(SpellCard spell, SpellCard spell2, boolean hidden) {
		// TODO Auto-generated method stub
		
		if (spell.getType2().equals("field")){
			if (!hand.contains(spell))
				return false;
			if (phase == Phase.BATTLE)
				throw new WrongPhaseException();
			if (fieldarea.size()>0)
				removeSpellfromFToGraveyard(fieldarea.get(0));
			
			hand.remove(spell);
			fieldarea.add(spell);
			spell.setLocation(Location.FIELD);
			return true;
		}
		if (!hand.contains(spell))
			return false;

		if (spellArea.size() >= 5)
			throw new NoSpellSpaceException();

		if (phase == Phase.BATTLE && !spell.getType2().contains("trap"))
			throw new WrongPhaseException();
		/*
		if (spell.getType2().equals("equipment")){
			boolean compatiblemonster = ((EquipmentSpellCard) spell).monstercondition(monster);
			if (!compatiblemonster) {
				throw new IncompatibleMonster();
			}
			spell.action(monster);
			hand.remove(spell);
			spellArea.add(spell);
			spell.setLocation(Location.FIELD);
			spell.setHidden(false);
			return true;
		}
		
		if (!hidden) {
			if (spell.getType2().equals("equipment")){
				boolean compatiblespell = ((EquipmentSpellCard) spell).compatible(spell2);
				if (!compatiblespell) {
					throw new IncompatibleMonster();
				}
				spell.actionbis(spell2);
				hand.remove(spell);
				spellArea.add(spell);
				spell.setLocation(Location.FIELD);
				spell.setHidden(false);
				return true;
			}
		}*/
		hand.remove(spell);
		spellArea.add(spell);
		spell.setLocation(Location.FIELD);

		if (!hidden)
			return activateSetSpell2(spell, spell2);

		return true;
	}

	public int countTraporSpeedCards() {
	//compte les cartes pièges et jeu rapide posées sur le terrain
		int count = 0;
		for (int i=0;i<spellArea.size();i++){
			if (spellArea.get(i).isHidden() && (spellArea.get(i).getType2().equals("speed") || spellArea.get(i).getType2().contains("trap"))){
				count++;
			}
		}
		return count;
	}

	public ArrayList<Card> getBannished() {
		return bannished;
	}

	public void setBannished(ArrayList<Card> bannished) {
		this.bannished = bannished;
	}

	public void setDeck(Deck deck) {
		this.deck = deck;
	}

	
	
	public ExtraDeck getExtradeck() {
		return extradeck;
	}

	public void setExtradeck(ExtraDeck extradeck) {
		this.extradeck = extradeck;
	}

	public void setSpellArea(ArrayList<SpellCard> spellArea) {
		this.spellArea = spellArea;
	}

	public void setHand(ArrayList<Card> hand) {
		this.hand = hand;
	}

	public void setGraveyard(ArrayList<Card> graveyard) {
		this.graveyard = graveyard;
	}
	
	
}
