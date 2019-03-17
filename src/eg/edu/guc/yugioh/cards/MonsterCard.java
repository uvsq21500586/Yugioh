package eg.edu.guc.yugioh.cards;

import java.util.ArrayList;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.spells.*;

public class MonsterCard extends Card {

	private String type2;
	private int level;
	private String attribut;
	private String espece;
	private int defensePoints;
	private int attackPoints;
	private int basedefensePoints;
	private int baseattackPoints;
	private Mode mode = Mode.DEFENSE;
	private boolean switchedMode;
	private boolean attacked;
	private boolean lockswitch;
	private boolean canattack;

	public int getBasedefensePoints() {
		return basedefensePoints;
	}

	public void setBasedefensePoints(int basedefensePoints) {
		this.basedefensePoints = basedefensePoints;
	}

	public int getBaseattackPoints() {
		return baseattackPoints;
	}

	public void setBaseattackPoints(int baseattackPoints) {
		this.baseattackPoints = baseattackPoints;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public MonsterCard(String n, String desc,String attr,String esp, int l, int a, int d,String type2) {

		super(n, desc);
		this.level = l;
		this.attackPoints = a;
		this.defensePoints = d;
		this.baseattackPoints = a;
		this.basedefensePoints = d;
		this.attacked = false;
		this.switchedMode = false;
		this.espece = esp;
		this.attribut = attr;
		this.type2 = type2;
		lockswitch = false;
		canattack = true;

	}

	public MonsterCard(MonsterCard monster){
		super(monster.getName(), monster.getDescription());
		this.level = monster.getLevel();
		this.attackPoints = monster.getAttackPoints();
		this.defensePoints = monster.getDefensePoints();
		this.baseattackPoints = monster.getBaseattackPoints();
		this.basedefensePoints = monster.getDefensePoints();
		this.attacked = false;
		this.switchedMode = false;
		this.espece = monster.getEspece();
		this.attribut = monster.getAttribut();
		this.type2 = monster.getType2();
		lockswitch = false;
	}
	
	public void action() {

		attackLifePoints();
		this.setAttacked(true);

	}

	public void action(int placemonster) {

		if (getBoard().getOpponentPlayer().getField().getMonstersArea2()[placemonster] != null) {
			attackMonster(placemonster);
		}

		this.setAttacked(true);

	}

	public void attackLifePoints() {

		int lp = getBoard().getOpponentPlayer().getLifePoints();
		getBoard().getOpponentPlayer().setLifePoints(
				lp - this.getAttackPoints());

	}

/*
	
	public void attackMonster(MonsterCard target) {

		Player active = getBoard().getActivePlayer();
		Player opponent = getBoard().getOpponentPlayer();

		if (target.getMode() == Mode.ATTACK) {
			boolean remove[] = new boolean[5];
			

			if (this.getAttackPoints() > target.getAttackPoints()) {

				int damage = this.getAttackPoints() - target.getAttackPoints();
				opponent.getField().removeMonsterToGraveyard(target);
				target.setAttackPoints(target.getBaseattackPoints());
				target.setDefensePoints(target.getBasedefensePoints());
				
				//for (int i=0;i<getBoard().getActivePlayer().getField().getSpellArea().size();i++){
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(target)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}

				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(target)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				int lp = opponent.getLifePoints();
				opponent.setLifePoints(lp - damage);

			} else if (this.getAttackPoints() == target.getAttackPoints()) {

				active.getField().removeMonsterToGraveyard(this);
				opponent.getField().removeMonsterToGraveyard(target);
				
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(target)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(target)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				target.setAttackPoints(target.getBaseattackPoints());
				target.setDefensePoints(target.getBasedefensePoints());
				attackPoints = baseattackPoints;
				defensePoints = basedefensePoints;
				this.setAttacked(true);

			} else {

				int damage = target.getAttackPoints() - this.getAttackPoints();
				active.getField().removeMonsterToGraveyard(this);
				
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i++){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				attackPoints = baseattackPoints;
				defensePoints = basedefensePoints;
				int lp = active.getLifePoints();
				active.setLifePoints(lp - damage);

			}

		} else {

			if (this.getAttackPoints() > target.getDefensePoints()) {

				opponent.getField().removeMonsterToGraveyard(target);
				for (int i=0;i<getBoard().getActivePlayer().getField().getSpellArea().size();i++){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(target)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=0;i<getBoard().getOpponentPlayer().getField().getSpellArea().size();i++){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(target)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				target.setAttackPoints(target.getBaseattackPoints());
				target.setDefensePoints(target.getBasedefensePoints());
				this.setAttacked(true);

			} else if (this.getAttackPoints() == target.getAttackPoints()) {

				this.setAttacked(true);
				target.setHidden(false);

			} else {

				int damage = target.getDefensePoints() - this.getAttackPoints();
				int lp = active.getLifePoints();
				active.setLifePoints(lp - damage);
				target.setHidden(false);

			}
		}

	}
	*/

	
	
	public void attackMonster(int target) {

		Player active = getBoard().getActivePlayer();
		boolean innormalfield = false;
		int place = 0;
		for (int i=0;i<5;i++){
			if (getBoard().getActivePlayer().getField().getMonstersArea2()[i] == this){
				place = i;
				innormalfield = true;
			}
		}
		Player opponent = getBoard().getOpponentPlayer();
		MonsterCard opponentmonster = getBoard().getOpponentPlayer().getField().getMonstersArea2()[target];

		if (opponentmonster.getMode() == Mode.ATTACK) {
			boolean remove[] = new boolean[5];
			

			if (this.getAttackPoints() > opponentmonster.getAttackPoints()) {

				int damage = this.getAttackPoints() - opponentmonster.getAttackPoints();
				opponent.getField().removeMonsterToGraveyard(target);
				opponentmonster.setAttackPoints(opponentmonster.getBaseattackPoints());
				opponentmonster.setDefensePoints(opponentmonster.getBasedefensePoints());
				
				//for (int i=0;i<getBoard().getActivePlayer().getField().getSpellArea().size();i++){
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(opponentmonster)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}

				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(opponentmonster)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				int lp = opponent.getLifePoints();
				opponent.setLifePoints(lp - damage);

			} else if (this.getAttackPoints() == opponentmonster.getAttackPoints()) {

				if (innormalfield) {
					active.getField().removeMonsterToGraveyard(place);
				} else {
					active.getField().removeMonsterExtraToGraveyard();
				}
				opponent.getField().removeMonsterToGraveyard(target);
				
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(opponentmonster)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(opponentmonster)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				opponentmonster.setAttackPoints(opponentmonster.getBaseattackPoints());
				opponentmonster.setDefensePoints(opponentmonster.getBasedefensePoints());
				attackPoints = baseattackPoints;
				defensePoints = basedefensePoints;
				this.setAttacked(true);

			} else {

				int damage = opponentmonster.getAttackPoints() - this.getAttackPoints();
				if (innormalfield) {
					active.getField().removeMonsterToGraveyard(place);
				} else {
					active.getField().removeMonsterExtraToGraveyard();
				}
				
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				attackPoints = baseattackPoints;
				defensePoints = basedefensePoints;
				int lp = active.getLifePoints();
				active.setLifePoints(lp - damage);

			}

		} else {
			if (opponentmonster.isHidden() && opponentmonster.getType2().contains("effect") && ((EffectMonsterCard)opponentmonster).getTypeeffect().equals("flip")){
				getBoard().nextPlayer();
				if (((EffectMonsterCard)opponentmonster).conditionactivation()) {
					if (((EffectMonsterCard)opponentmonster).isMultipletargetcard()) {
						((EffectMonsterCard)opponentmonster).getNombretotalcible();
						ArrayList<Card> targets = new ArrayList<Card>();
						for (int i1=0;i1<((EffectMonsterCard)opponentmonster).getNombrecible().size();i1++){
							//sélectionner les cibles
							for (int i2=0;i2<((EffectMonsterCard)opponentmonster).getNombrecible().get(i1);i2++)
								targets.add(targetCard((EffectMonsterCard)opponentmonster,i1));
						
						}
						((EffectMonsterCard)opponentmonster).actionmulti(targets);
					} else {
						((EffectMonsterCard)opponentmonster).action();
					}
				}
				getBoard().nextPlayer();
			}
			

			if (this.getAttackPoints() > opponentmonster.getDefensePoints()) {

				opponent.getField().removeMonsterToGraveyard(target);
				for (int i=0;i<getBoard().getActivePlayer().getField().getSpellArea().size();i++){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(target)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=0;i<getBoard().getOpponentPlayer().getField().getSpellArea().size();i++){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(target)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				opponentmonster.setAttackPoints(opponentmonster.getBaseattackPoints());
				opponentmonster.setDefensePoints(opponentmonster.getBasedefensePoints());
				this.setAttacked(true);

			} else if (this.getAttackPoints() == opponentmonster.getAttackPoints()) {

				this.setAttacked(true);
				opponentmonster.setHidden(false);

			} else {

				int damage = opponentmonster.getDefensePoints() - this.getAttackPoints();
				int lp = active.getLifePoints();
				active.setLifePoints(lp - damage);
				opponentmonster.setHidden(false);

			}
		}

	}

	public void switchMode() {

		if (mode == Mode.ATTACK) {

			mode = Mode.DEFENSE;
			//setHidden(true);

		} else {

			mode = Mode.ATTACK;
			if (isHidden() && type2.contains("effect") && ((EffectMonsterCard)this).getTypeeffect().equals("flip")) {
				if (((EffectMonsterCard)this).conditionactivation()) {
					if (((EffectMonsterCard)this).isMultipletargetcard()) {
						((EffectMonsterCard)this).getNombretotalcible();
						ArrayList<Card> targets = new ArrayList<Card>();
						for (int i1=0;i1<((EffectMonsterCard)this).getNombrecible().size();i1++){
							//sélectionner les cibles
							for (int i2=0;i2<((EffectMonsterCard)this).getNombrecible().get(i1);i2++)
								targets.add(targetCard((EffectMonsterCard)this,i1));
						
						}
						((EffectMonsterCard)this).actionmulti(targets);
					} else {
						((EffectMonsterCard)this).action();
					}
				}
			}
			setHidden(false);

		}
	}
	
	public boolean isSwitchedMode() {
		return switchedMode;
	}

	public void setSwitchedMode(boolean switchedMode) {
		this.switchedMode = switchedMode;
	}

	public boolean isAttacked() {
		return attacked;
	}

	public void setAttacked(boolean attacked) {
		this.attacked = attacked;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getDefensePoints() {
		return defensePoints;
	}

	public void setDefensePoints(int defensePoints) {
		this.defensePoints = defensePoints;
	}

	public int getAttackPoints() {
		return attackPoints;
	}

	public void setAttackPoints(int attackPoints) {
		this.attackPoints = attackPoints;
	}

	public Mode getMode() {
		return mode;
	}

	public void setMode(Mode mode) {
		this.mode = mode;
	}

	public void setHidden(boolean hidden) {
		super.setHidden(hidden);
	}

	public String getAttribut() {
		return attribut;
	}

	public void setAttribut(String attribut) {
		this.attribut = attribut;
	}

	public String getEspece() {
		return espece;
	}

	public void setEspece(String espece) {
		this.espece = espece;
	}

	@Override
	public void action(MonsterCard monster) {
		// TODO Auto-generated method stub
		
	}

	public boolean isLockswitch() {
		return lockswitch;
	}

	public void setLockswitch(boolean lockswitch) {
		this.lockswitch = lockswitch;
	}

	public void actionextra() {
		// TODO Auto-generated method stub
		Player active = getBoard().getActivePlayer();
		boolean innormalfield = false;
		int place = 0;
		for (int i=0;i<5;i++){
			if (getBoard().getActivePlayer().getField().getMonstersArea2()[i] == this){
				place = i;
				innormalfield = true;
			}
		}
		Player opponent = getBoard().getOpponentPlayer();
		MonsterCard opponentmonster = getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0];
		
		if (opponentmonster.getMode() == Mode.ATTACK) {
			boolean remove[] = new boolean[5];
			

			if (this.getAttackPoints() > opponentmonster.getAttackPoints()) {

				int damage = this.getAttackPoints() - opponentmonster.getAttackPoints();
				opponent.getField().removeMonsterExtraToGraveyard();
				opponentmonster.setAttackPoints(opponentmonster.getBaseattackPoints());
				opponentmonster.setDefensePoints(opponentmonster.getBasedefensePoints());
				
				//for (int i=0;i<getBoard().getActivePlayer().getField().getSpellArea().size();i++){
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(opponentmonster)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}

				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(opponentmonster)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				int lp = opponent.getLifePoints();
				opponent.setLifePoints(lp - damage);

			} else if (this.getAttackPoints() == opponentmonster.getAttackPoints()) {

				if (innormalfield) {
					active.getField().removeMonsterToGraveyard(place);
				} else {
					active.getField().removeMonsterExtraToGraveyard();
				}
				opponent.getField().removeMonsterExtraToGraveyard();
				
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(opponentmonster)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(opponentmonster)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				opponentmonster.setAttackPoints(opponentmonster.getBaseattackPoints());
				opponentmonster.setDefensePoints(opponentmonster.getBasedefensePoints());
				attackPoints = baseattackPoints;
				defensePoints = basedefensePoints;
				this.setAttacked(true);

			} else {

				int damage = opponentmonster.getAttackPoints() - this.getAttackPoints();
				if (innormalfield) {
					active.getField().removeMonsterToGraveyard(place);
				} else {
					active.getField().removeMonsterExtraToGraveyard();
				}
				
				for (int i=getBoard().getActivePlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=getBoard().getOpponentPlayer().getField().getSpellArea().size()-1;i>=0;i--){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(this)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				attackPoints = baseattackPoints;
				defensePoints = basedefensePoints;
				int lp = active.getLifePoints();
				active.setLifePoints(lp - damage);

			}

		} else {
			if (opponentmonster.isHidden() && opponentmonster.getType2().contains("effect") && ((EffectMonsterCard)opponentmonster).getTypeeffect().equals("flip")){
				getBoard().nextPlayer();
				if (((EffectMonsterCard)opponentmonster).conditionactivation()) {
					if (((EffectMonsterCard)opponentmonster).isMultipletargetcard()) {
						((EffectMonsterCard)opponentmonster).getNombretotalcible();
						ArrayList<Card> targets = new ArrayList<Card>();
						for (int i1=0;i1<((EffectMonsterCard)opponentmonster).getNombrecible().size();i1++){
							//sélectionner les cibles
							for (int i2=0;i2<((EffectMonsterCard)opponentmonster).getNombrecible().get(i1);i2++)
								targets.add(targetCard((EffectMonsterCard)opponentmonster,i1));
						
						}
						((EffectMonsterCard)opponentmonster).actionmulti(targets);
					} else {
						((EffectMonsterCard)opponentmonster).action();
					}
				}
				getBoard().nextPlayer();
			}
			

			if (this.getAttackPoints() > opponentmonster.getDefensePoints()) {

				opponent.getField().removeMonsterExtraToGraveyard();
				for (int i=0;i<getBoard().getActivePlayer().getField().getSpellArea().size();i++){
					SpellCard spell = getBoard().getActivePlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(opponentmonster)){
						active.getField().removeSpellToGraveyard(spell);
					}
				}
				for (int i=0;i<getBoard().getOpponentPlayer().getField().getSpellArea().size();i++){
					SpellCard spell = getBoard().getOpponentPlayer().getField().getSpellArea().get(i);
					if (spell.getType2().equals("equipment") && ((EquipmentSpellCard) spell).getMonsterlinked().equals(opponentmonster)){
						opponent.getField().removeSpellToGraveyard(spell);
					}
				}
				opponentmonster.setAttackPoints(opponentmonster.getBaseattackPoints());
				opponentmonster.setDefensePoints(opponentmonster.getBasedefensePoints());
				this.setAttacked(true);

			} else if (this.getAttackPoints() == opponentmonster.getAttackPoints()) {

				this.setAttacked(true);
				opponentmonster.setHidden(false);

			} else {

				int damage = opponentmonster.getDefensePoints() - this.getAttackPoints();
				int lp = active.getLifePoints();
				active.setLifePoints(lp - damage);
				opponentmonster.setHidden(false);

			}
		}

	}

	
	public boolean isCanattack() {
		return canattack;
	}

	public void setCanattack(boolean canattack) {
		this.canattack = canattack;
	}

	
}
