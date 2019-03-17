package eg.edu.guc.yugioh.cards;
import java.util.ArrayList;

import eg.edu.guc.yugioh.cards.spells.*;
import eg.edu.guc.yugioh.gui.Gui;
import particularmonsters.*;
public class translater {
	
	
	public translater() {
		super();
	}


	public SpellCard translatedspell(String type2,String name, String desc){
		SpellCard spellcard = null;
		switch (name) {
		case "Arc et Fleches d'Argent":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("elfe")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Armure Canon-Laser":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("insecte")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Attaque en Double Fourche":
			spellcard = new TrapCard(type2,name,desc){
				public void actionmulti(ArrayList<Card> card) {
					for (int i=0;i<2;i++) {
						if (getBoard().getActivePlayer().getField().MonsterInField((MonsterCard) card.get(i))) {
							int pos = getBoard().getActivePlayer().getField().getpositioninMonsterField((MonsterCard) card.get(i));
							getBoard().getActivePlayer().getField().removeMonsterToGraveyard(pos);
						} else {
							getBoard().getActivePlayer().getField().removeMonsterExtraToGraveyard();
						}
					}
					if (getBoard().getOpponentPlayer().getField().MonsterInField((MonsterCard) card.get(2))) {
						int pos = getBoard().getOpponentPlayer().getField().getpositioninMonsterField((MonsterCard) card.get(2));
						getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(pos);
					} else {
						getBoard().getOpponentPlayer().getField().removeMonsterExtraToGraveyard();
					}
				}
				public boolean conditionactivation() {
					if (getBoard().getActivePlayer().getField().countMonstersonField()<2 || getBoard().getOpponentPlayer().getField().countMonstersonField()<1){
						return false;
					}
					return true;
				}
			};
			spellcard.setNombretotalcible(3);
			spellcard.setMultipletargetcard(true);
			spellcard.getNombrecible().add(2);
			spellcard.getNombrecible().add(1);
			spellcard.getLocationcible().add("active monster field");
			spellcard.getLocationcible().add("opponent monster field");
			return spellcard;
		case "Cristal Violet":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("zombie")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Crocs Demoniaques":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("bete")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Electrofouet":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("tonnerre")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Elevateur de Chaleur Corporelle":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("dinosaure")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Energie Sombre":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("demon")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Epees de Revelation de la Lumiere":
			spellcard = new SpellCard(type2,name,desc){
				public void action(MonsterCard monster) {
					if (getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0]!=null){
						getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0].setCanattack(false);
					}
					
					for (int i=0;i<5;i++) {
						if (getBoard().getOpponentPlayer().getField().getMonstersArea2()[i]!=null) {
							getBoard().getOpponentPlayer().getField().getMonstersArea2()[i].setCanattack(false);
						}
					}
				}
				public void actionmonstreadversejoue(MonsterCard monster){
					monster.setCanattack(false);
				}
				public void gotograveyard() {
					if (getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0]!=null){
						getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0].setCanattack(true);
					}
					
					for (int i=0;i<5;i++) {
						if (getBoard().getOpponentPlayer().getField().getMonstersArea2()[i]!=null) {
							getBoard().getOpponentPlayer().getField().getMonstersArea2()[i].setCanattack(true);
						}
					}
				}
				
			};
			
			spellcard.setTimespell(true);
			spellcard.setTimeturn(6);
			return spellcard;
		case "Etincelles":
			spellcard = new SpellCard(type2,name,desc){
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					getBoard().getOpponentPlayer().setLifePoints(getBoard().getOpponentPlayer().getLifePoints()-200);
				}
				
			};
			return spellcard;
		case "Fissure":
			spellcard = new SpellCard(type2,name,desc){
				public boolean conditionactivation() {
					if (getBoard().getOpponentPlayer().getField().countMonstersonField() == 0) {
						return false;
					}
					if (getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0] == null) {
						for (int i = 0; i<5;i++){
							if (getBoard().getOpponentPlayer().getField().getMonstersArea2()[i]!=null && !getBoard().getOpponentPlayer().getField().getMonstersArea2()[i].isHidden()){
								return true;
							}
						}
						return false;
					}
					return true;
				}
				
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					int attaquemin = 100000;
					String lieu = "mainmonsterfield";
					int place = 0;
					for (int i = 0; i<5;i++){
						if (getBoard().getOpponentPlayer().getField().getMonstersArea2()[i]!=null  && !getBoard().getOpponentPlayer().getField().getMonstersArea2()[i].isHidden() && getBoard().getOpponentPlayer().getField().getMonstersArea2()[i].getAttackPoints()<attaquemin ){
							attaquemin = getBoard().getOpponentPlayer().getField().getMonstersArea2()[i].getAttackPoints();
							place = i;
						}
					}
					if (getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0]!=null && getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0].getAttackPoints()<attaquemin){
						attaquemin = getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0].getAttackPoints();
						place = 0;
						lieu = "extramonsterfield";
					}
					if (lieu.equals("mainmonsterfield")) {
						getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(place);
					} else {
						getBoard().getOpponentPlayer().getField().removeMonsterExtraToGraveyard();
					}
					
				}
				
			};
			return spellcard;
		case "Flamme Ultime":
			spellcard = new SpellCard(type2,name,desc){
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					getBoard().getOpponentPlayer().setLifePoints(getBoard().getOpponentPlayer().getLifePoints()-600);
				}
				
			};
			return spellcard;
		case "Goule Fossoyeur":
			spellcard = new SpellCard(type2,name,desc){
				public void actionmulti(ArrayList<Card> card) {
					for (int i=0;i<2;i++) {
						getBoard().getOpponentPlayer().getField().getGraveyard().remove(card.get(i));
						getBoard().getOpponentPlayer().getField().getBannished().add(card.get(i));
					}
				}
				public boolean conditionactivation() {
					if (getBoard().getOpponentPlayer().getField().countMonstersonGraveyard() < 2 ){
						return false;
					}
					
					return true;
				}
			};
			spellcard.setNombretotalcible(2);
			spellcard.setMultipletargetcard(true);
			spellcard.getNombrecible().add(2);
			spellcard.getLocationcible().add("opponent monster graveyard");
			return spellcard;
		case "Foret":
			spellcard = new FieldSpellCard(type2,name,desc){

				@Override
				public void gotograveyard(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("insecte") || M.getEspece().equals("plante") || M.getEspece().equals("bete") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					
					}
					
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("insecte") || M.getEspece().equals("plante") || M.getEspece().equals("bete") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						
						if (M !=null){
							if (M.getEspece().equals("insecte") || M.getEspece().equals("plante") || M.getEspece().equals("bete") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("insecte") || M.getEspece().equals("plante") || M.getEspece().equals("bete") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					
				}

				@Override
				public void setmonster(MonsterCard monster) {
					// TODO Auto-generated method stub
					if (monster.getEspece().equals("insecte")  || monster.getEspece().equals("plante") || monster.getEspece().equals("bete") || monster.getEspece().equals("bete-guerrier")) {
						monster.setAttackPoints(monster.getAttackPoints()+200);
						monster.setDefensePoints(monster.getDefensePoints()+200);
					}
				}

				@Override
				public void action2(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("insecte") || M.getEspece().equals("plante") || M.getEspece().equals("bete") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("insecte") || M.getEspece().equals("plante") || M.getEspece().equals("bete") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("insecte") || M.getEspece().equals("plante") || M.getEspece().equals("bete") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("insecte") || M.getEspece().equals("plante") || M.getEspece().equals("bete") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
				}
				
			};
			return spellcard;
		case "Germes Ignobles":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("plante")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Hinotama":
			spellcard = new SpellCard(type2,name,desc){
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					getBoard().getOpponentPlayer().setLifePoints(getBoard().getOpponentPlayer().getLifePoints()-500);
				}
				
			};
			return spellcard;
		case "Jarre de Capture du Dragon":
			spellcard = new ContinueTrapCard(type2,name,desc){
				public void actionterrain(Gui gui) {
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("dragon")) {
								if (M.getMode()==Mode.ATTACK){
									M.setMode(Mode.DEFENSE);
								}
								M.setLockswitch(true);
							}
						}			
					}
					
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dragon")) {
								if (M.getMode()==Mode.ATTACK){
									M.setMode(Mode.DEFENSE);
								}
								M.setLockswitch(true);
							}
						}
					}
					
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						
						if (M !=null){
							if (M.getEspece().equals("dragon")) {
								if (M.getMode()==Mode.ATTACK){
									M.setMode(Mode.DEFENSE);
								}
								M.setLockswitch(true);
							}
						}
					}
					
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dragon")) {
								if (M.getMode()==Mode.ATTACK){
									M.setMode(Mode.DEFENSE);
								}
								M.setLockswitch(true);
							}
						}
					}
				}
				
				public void gotograveyard() {
					for (int i=0;i<5;i++) {
						MonsterCard M = getBoard().getActivePlayer().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("dragon")) {
								M.setLockswitch(false);
							}
						}			
					}
					
					for (int i=0;i<1;i++) {
						MonsterCard M = getBoard().getActivePlayer().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dragon")) {
								M.setLockswitch(false);
							}
						}
					}
					
					for (int i=0;i<5;i++) {
						MonsterCard M = getBoard().getOpponentPlayer().getField().getMonstersArea2()[i];
						
						if (M !=null){
							if (M.getEspece().equals("dragon")) {
								M.setLockswitch(false);
							}
						}
					}
					
					for (int i=0;i<1;i++) {
						MonsterCard M = getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dragon")) {
								M.setLockswitch(false);
							}
						}
					}
				}
				
				public void action(MonsterCard M) {
					if (M.getEspece().equals("dragon")) {
						if (M.getMode()==Mode.ATTACK){
							M.setMode(Mode.DEFENSE);
						}
						M.setLockswitch(false); 
					}
				}
				
			};
			return spellcard;
		case "Livre d'Arts Secrets":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("magicien")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Lune Mystique":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("bete-guerrier")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Medicament Rouge":
			spellcard = new SpellCard(type2,name,desc){
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					getBoard().getActivePlayer().setLifePoints(getBoard().getActivePlayer().getLifePoints()+500);
				}
				
			};
			return spellcard;
		case "Montagne":
			spellcard = new FieldSpellCard(type2,name,desc){

				@Override
				public void gotograveyard(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M!=null){
							if (M.getEspece().equals("dragon") || M.getEspece().equals("bete ailee") || M.getEspece().equals("tonerre")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dragon") || M.getEspece().equals("bete ailee") || M.getEspece().equals("tonerre")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						if (M!=null){
							if (M.getEspece().equals("dragon") || M.getEspece().equals("bete ailee") || M.getEspece().equals("tonerre")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dragon") || M.getEspece().equals("bete ailee") || M.getEspece().equals("tonerre")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					
				}

				@Override
				public void setmonster(MonsterCard monster) {
					// TODO Auto-generated method stub
					if (monster.getEspece().equals("dragon")  || monster.getEspece().equals("bete ailee") || monster.getEspece().equals("tonerre")) {
						monster.setAttackPoints(monster.getAttackPoints()+200);
						monster.setDefensePoints(monster.getDefensePoints()+200);
					}
				}

				@Override
				public void action2(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M!=null){
							if (M.getEspece().equals("dragon") || M.getEspece().equals("bete ailee") || M.getEspece().equals("tonerre")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dragon") || M.getEspece().equals("bete ailee") || M.getEspece().equals("tonerre")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						if (M!=null){
							if (M.getEspece().equals("dragon") || M.getEspece().equals("bete ailee") || M.getEspece().equals("tonerre")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dragon") || M.getEspece().equals("bete ailee") || M.getEspece().equals("tonerre")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
				}
				
			};
			return spellcard;
		case "Piege Supprime":
			spellcard = new SpellCard(type2,name,desc){
				//targetmonster = false;
				
				@Override
				public void actionbis(SpellCard spell) {
					// TODO Auto-generated method stub
					//suppression de piege
					if (spell.getType2().contains("trap")) {
						getBoard().getOpponentPlayer().getField().removeSpellToGraveyard(spell);
						spell.gotograveyard();
						
					}
					
				}
				public boolean compatible(SpellCard spell) {
					return true;
				}
				
				
			};
			spellcard.setTargetspell(true);
			return spellcard;
		case "Pot de Cupidite":
			spellcard = new SpellCard(type2,name,desc){
				public boolean conditionactivation() {
					if (getBoard().getActivePlayer().getField().getDeck().getDeck().size()>1){
						return true;
					}
					return false;
					
				}
				public void action(MonsterCard monster) {
					getBoard().getActivePlayer().addCardToHand();
					getBoard().getActivePlayer().addCardToHand();
				}
			};
			return spellcard;
		case "Puissance de Kaishin":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("aqua")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Polymerisation":
			spellcard = new SpellCard(type2,name,desc){
				public boolean conditionactivation() {
					if (getBoard().getActivePlayer().getField().getExtradeck().getMonsters().size()>0) {
						return true;
					}
					for (int i=0;i<getBoard().getActivePlayer().getField().getExtradeck().getMonsters().size();i++) {
						if (getBoard().getActivePlayer().getField().getExtradeck().getMonsters().get(i).getType2().contains("fusion")) {
							return true;
						}
					}
					
					return false;
				}
			};
			spellcard.setFusionspell(true);
			return spellcard;
		case "Raigeki":
			spellcard = new SpellCard(type2,name,desc){
				//targetmonster = false;
				
				public void action(MonsterCard monster) {
					if (getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0]!=null){
						getBoard().getOpponentPlayer().getField().removeMonsterExtraToGraveyard();
					}
					
					for (int i=0;i<5;i++) {
						if (getBoard().getOpponentPlayer().getField().getMonstersArea2()[i]!=null) {
							getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(i);
						}
					}
				}
			};
			return spellcard;
		case "Remede Secret du Gobelin":
			spellcard = new SpellCard(type2,name,desc){
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					getBoard().getActivePlayer().setLifePoints(getBoard().getActivePlayer().getLifePoints()+600);
				}
				
			};
			return spellcard;
		case "Sogen":
			spellcard = new FieldSpellCard(type2,name,desc){

				@Override
				public void gotograveyard(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("guerrier") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("guerrier") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("guerrier") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("guerrier") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					
				}

				@Override
				public void setmonster(MonsterCard monster) {
					// TODO Auto-generated method stub
					if (monster.getEspece().equals("guerrier") || monster.getEspece().equals("bete-guerrier")) {
						monster.setAttackPoints(monster.getAttackPoints()+200);
						monster.setDefensePoints(monster.getDefensePoints()+200);
					}
				}

				@Override
				public void action2(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("guerrier") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("guerrier") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("guerrier") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("guerrier") || M.getEspece().equals("bete-guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
				}
				
			};
			return spellcard;
		case "Stop Defense":
			spellcard = new SpellCard(type2,name,desc){
				//targetmonster = false;
				
				public boolean conditionactivation() {
					if (getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0]!=null && getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0].getMode() == Mode.ATTACK
							&& !getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0].isLockswitch()){
						return true;
					} else {
						for (int i=0;i<5;i++) {
							if (getBoard().getOpponentPlayer().getField().getMonstersArea2()[i]!=null && getBoard().getOpponentPlayer().getField().getMonstersArea2()[i].getMode() == Mode.ATTACK
									&& !getBoard().getOpponentPlayer().getField().getMonstersArea2()[i].isLockswitch()) {
								return true;
							}
						}
					}
					return false;
				}
				
				public boolean compatible(MonsterCard monster) {
					if ( !getBoard().getOpponentPlayer().getField().MonsterInField(monster) || monster.isLockswitch() || monster.getMode() == Mode.DEFENSE ) {
						return false;
					}
					return true;
				}
				
				public void action(MonsterCard monster) {
					monster.setMode(Mode.ATTACK);
				}
				
				
			};
			spellcard.setTargetmonster(true);
			return spellcard;
		case "Suit le Vent":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("bete ailee")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Terre Devastee":
			spellcard = new FieldSpellCard(type2,name,desc){

				@Override
				public void gotograveyard(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("dinosaure") || M.getEspece().equals("zombie") || M.getEspece().equals("guerrier")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dinosaure") || M.getEspece().equals("zombie") || M.getEspece().equals("guerrier")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("dinosaure") || M.getEspece().equals("zombie") || M.getEspece().equals("guerrier")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dinosaure") || M.getEspece().equals("zombie") || M.getEspece().equals("guerrier")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					
				}

				@Override
				public void setmonster(MonsterCard monster) {
					// TODO Auto-generated method stub
					if (monster.getEspece().equals("dinosaure")  || monster.getEspece().equals("zombie") || monster.getEspece().equals("guerrier")) {
						monster.setAttackPoints(monster.getAttackPoints()+200);
						monster.setDefensePoints(monster.getDefensePoints()+200);
					}
				}

				@Override
				public void action2(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("dinosaure") || M.getEspece().equals("zombie") || M.getEspece().equals("guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dinosaure") || M.getEspece().equals("zombie") || M.getEspece().equals("guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("dinosaure") || M.getEspece().equals("zombie") || M.getEspece().equals("guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("dinosaure") || M.getEspece().equals("zombie") || M.getEspece().equals("guerrier")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
				}
				
			};
			return spellcard;
		case "Trappe":
			spellcard = new TrapCard(type2,name,desc){
				public boolean conditionactivation(){
					if (getBoard().getOpponentPlayer().getEvenement().equals("invocation normale") || getBoard().getOpponentPlayer().getEvenement().equals("invocation flip")){
						return true;
					}
					
					return false;
					
				}
				
				public void actionsurcible() {
					if (((MonsterCard)cible).getAttackPoints() >=1000) {
						getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard((MonsterCard)cible);
					}
					
				}
			};
			((TrapCard)spellcard).setCiblejoue(true);
			return spellcard;
		case "Tresor de Dragon":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("dragon")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Trou Noir":
			spellcard = new SpellCard(type2,name,desc){
				//targetmonster = false;
				
				public void action(MonsterCard monster) {
					if (getBoard().getActivePlayer().getField().getMonstersExtraArea2()[0]!=null){
						getBoard().getActivePlayer().getField().removeMonsterExtraToGraveyard();
					}
					if (getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0]!=null){
						getBoard().getOpponentPlayer().getField().removeMonsterExtraToGraveyard();
					}
					
					for (int i=0;i<5;i++) {
						if (getBoard().getActivePlayer().getField().getMonstersArea2()[i]!=null) {
							getBoard().getActivePlayer().getField().removeMonsterToGraveyard(i);
						}
						if (getBoard().getOpponentPlayer().getField().getMonstersArea2()[i]!=null) {
							getBoard().getOpponentPlayer().getField().removeMonsterToGraveyard(i);
						}
					}
				}
			};
			return spellcard;
		case "Umi":
			spellcard = new FieldSpellCard(type2,name,desc){

				@Override
				public void gotograveyard(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("poisson") || M.getEspece().equals("serpent de mer") || M.getEspece().equals("tonnerre") || M.getEspece().equals("aqua")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
							if (M.getEspece().equals("machine") || M.getEspece().equals("pyro")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					
					}
					
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("poisson") || M.getEspece().equals("serpent de mer") || M.getEspece().equals("tonnerre") || M.getEspece().equals("aqua")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
							if (M.getEspece().equals("machine") || M.getEspece().equals("pyro")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						
						if (M !=null){
							if (M.getEspece().equals("poisson") || M.getEspece().equals("serpent de mer") || M.getEspece().equals("tonnerre") || M.getEspece().equals("aqua")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
							if (M.getEspece().equals("machine") || M.getEspece().equals("pyro")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("poisson") || M.getEspece().equals("serpent de mer") || M.getEspece().equals("tonnerre") || M.getEspece().equals("aqua")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
							if (M.getEspece().equals("machine") || M.getEspece().equals("pyro")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					
				}

				@Override
				public void setmonster(MonsterCard monster) {
					// TODO Auto-generated method stub
					if (monster.getEspece().equals("poisson") || monster.getEspece().equals("serpent de mer") || monster.getEspece().equals("tonnerre") || monster.getEspece().equals("aqua")) {
						monster.setAttackPoints(monster.getAttackPoints()+200);
						monster.setDefensePoints(monster.getDefensePoints()+200);
					}
					if (monster.getEspece().equals("machine") || monster.getEspece().equals("pyro")) {
						monster.setAttackPoints(monster.getAttackPoints()-200);
						monster.setDefensePoints(monster.getDefensePoints()-200);
					}
				}

				@Override
				public void action2(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("poisson") || M.getEspece().equals("serpent de mer") || M.getEspece().equals("tonnerre") || M.getEspece().equals("aqua")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
							if (M.getEspece().equals("machine") || M.getEspece().equals("pyro")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("poisson") || M.getEspece().equals("serpent de mer") || M.getEspece().equals("tonnerre") || M.getEspece().equals("aqua")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
							if (M.getEspece().equals("machine") || M.getEspece().equals("pyro")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("poisson") || M.getEspece().equals("serpent de mer") || M.getEspece().equals("tonnerre") || M.getEspece().equals("aqua")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
							if (M.getEspece().equals("machine") || M.getEspece().equals("pyro")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("poisson") || M.getEspece().equals("serpent de mer") || M.getEspece().equals("tonnerre") || M.getEspece().equals("aqua")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
							if (M.getEspece().equals("machine") || M.getEspece().equals("pyro")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
				}
				
			};
			return spellcard;
		case "Usine de Convertisseurs":
			spellcard = new EquipmentSpellCard(type2,name,desc){
				
				public boolean monstercondition (MonsterCard monster) {
					if (monster.getEspece().equals("machine")){
						return true;
					}
					return false;
				}
				@Override
				public void action(MonsterCard monster) {
					// TODO Auto-generated method stub
					monsterlinked = monster;
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()+300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()+300);
				}
				public void gotograveyard() {
					// TODO Auto-generated method stub
					monsterlinked.setAttackPoints(monsterlinked.getAttackPoints()-300);
					monsterlinked.setDefensePoints(monsterlinked.getDefensePoints()-300);
				}
				
			};
			return spellcard;
		case "Yami":
			spellcard = new FieldSpellCard(type2,name,desc){

				@Override
				public void gotograveyard(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("magicien") || M.getEspece().equals("demon")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
							if (M.getEspece().equals("elfe")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					
					}
					
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("magicien") || M.getEspece().equals("demon")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
							if (M.getEspece().equals("elfe")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						
						if (M !=null){
							if (M.getEspece().equals("magicien") || M.getEspece().equals("demon")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
							if (M.getEspece().equals("elfe")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("magicien") || M.getEspece().equals("demon")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
							if (M.getEspece().equals("elfe")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
						}
					}
					
				}

				@Override
				public void setmonster(MonsterCard M) {
					// TODO Auto-generated method stub
					if (M.getEspece().equals("magicien") || M.getEspece().equals("demon")) {
						M.setAttackPoints(M.getAttackPoints()+200);
						M.setDefensePoints(M.getDefensePoints()+200);
					}
					if (M.getEspece().equals("elfe")) {
						M.setAttackPoints(M.getAttackPoints()-200);
						M.setDefensePoints(M.getDefensePoints()-200);
					}
				}

				@Override
				public void action2(Gui gui) {
					// TODO Auto-generated method stub
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("magicien") || M.getEspece().equals("demon")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
							if (M.getEspece().equals("elfe")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP1().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("magicien") || M.getEspece().equals("demon")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
							if (M.getEspece().equals("elfe")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					
					for (int i=0;i<5;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersArea2()[i];
						if (M !=null){
							if (M.getEspece().equals("magicien") || M.getEspece().equals("demon")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
							if (M.getEspece().equals("elfe")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
					for (int i=0;i<1;i++) {
						MonsterCard M = gui.getP2().getField().getMonstersExtraArea2()[0];
						if (M !=null){
							if (M.getEspece().equals("magicien") || M.getEspece().equals("demon")) {
								M.setAttackPoints(M.getAttackPoints()+200);
								M.setDefensePoints(M.getDefensePoints()+200);
							}
							if (M.getEspece().equals("elfe")) {
								M.setAttackPoints(M.getAttackPoints()-200);
								M.setDefensePoints(M.getDefensePoints()-200);
							}
						}
					}
				}
				
			};
			return spellcard;

		
		
		
		
		}
		
		return spellcard;
		
	}

	public MonsterCard translatedmonster(String n, String desc,String attr,String esp, int l, int a, int d,String type2){
		MonsterCard monstercard = null;
		MonsterCard monstercard2;
		switch (n) {
			case "Charubin le Chevalier de Feu":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new FusionMonsterCard(monstercard2){
					public boolean compatiblemonster(MonsterCard monster,int i){
						if (i == 0 && monster.getName().equals("Oeuf de Monstre")) {
							return true;
						} else if (i == 1 && monster.getName().equals("Ame Hinotama")) {
							return true;
						} else if (monster.getType2().contains("effect") && ((EffectMonsterCard)monster).isJokermaterielfusion()){
							return true;
						}
						return false;	
					}
				};
				((FusionMonsterCard)monstercard).setNbmaterielsfusions(2);
				return monstercard;
			case "Dechireur de Cartes":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new EffectMonsterCard(monstercard2){
					public boolean conditionactivation() {
						if (getBoard().getActivePlayer().getField().getSpellArea().size()>0 || getBoard().getOpponentPlayer().getField().getSpellArea().size()>0 ){
							return true;
						}
						return false;
					}
					
					public void actionmulti(ArrayList<Card> card) {
						if (((SpellCard)card.get(0)).getType2().contains("trap")) {
							if (getBoard().getActivePlayer().getField().getSpellArea().contains((SpellCard)card.get(0))){
								card.get(0).setLocation(Location.GRAVEYARD);
								getBoard().getActivePlayer().getField().removeSpellToGraveyard((SpellCard)card.get(0));
							} else {
								card.get(0).setLocation(Location.GRAVEYARD);
								getBoard().getOpponentPlayer().getField().removeSpellToGraveyard((SpellCard)card.get(0));
							}
						}
					}
				};
				((EffectMonsterCard)monstercard).setMultipletargetcard(true);
				((EffectMonsterCard)monstercard).setNombretotalcible(1);
				((EffectMonsterCard)monstercard).getNombrecible().add(1);
				((EffectMonsterCard)monstercard).getLocationcible().add("active opponent spell field");
				((EffectMonsterCard)monstercard).setTypeeffect("flip");
				return monstercard;
			case "Dragon de Feu Noir":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new FusionMonsterCard(monstercard2){
					public boolean compatiblemonster(MonsterCard monster,int i){
						if (i == 0 && monster.getName().equals("Plante de Feu")) {
							return true;
						} else if (i == 1 && monster.getName().equals("Petit Dragon")) {
							return true;
						} else if (monster.getType2().contains("effect") && ((EffectMonsterCard)monster).isJokermaterielfusion()){
							return true;
						}
						return false;	
					}
				};
				((FusionMonsterCard)monstercard).setNbmaterielsfusions(2);
				return monstercard;
			case "Dragon Metallique":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new FusionMonsterCard(monstercard2){
					public boolean compatiblemonster(MonsterCard monster,int i){
						if (i == 0 && monster.getName().equals("Ogre d'Acier N°1")) {
							return true;
						} else if (i == 1 && monster.getName().equals("Dragon Inferieur")) {
							return true;
						} else if (monster.getType2().contains("effect") && ((EffectMonsterCard)monster).isJokermaterielfusion()){
							return true;
						}
						return false;	
					}
				};
				((FusionMonsterCard)monstercard).setNbmaterielsfusions(2);
				return monstercard;
			case "Dragonness le Chevalier Malfaisant":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new FusionMonsterCard(monstercard2){
					public boolean compatiblemonster(MonsterCard monster,int i){
						if (i == 0 && monster.getName().equals("Armaill")) {
							return true;
						} else if (i == 1 && monster.getName().equals("Dragon Bouclier a un Oeil")) {
							return true;
						} else if (monster.getType2().contains("effect") && ((EffectMonsterCard)monster).isJokermaterielfusion()){
							return true;
						}
						return false;	
					}
				};
				((FusionMonsterCard)monstercard).setNbmaterielsfusions(2);
				return monstercard;
			case "Fantome de Flamme":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new FusionMonsterCard(monstercard2){
					public boolean compatiblemonster(MonsterCard monster,int i){
						if (i == 0 && monster.getName().equals("Crane Serviteur")) {
							return true;
						} else if (i == 1 && monster.getName().equals("Dissolveroc")) {
							return true;
						} else if (monster.getType2().contains("effect") && ((EffectMonsterCard)monster).isJokermaterielfusion()){
							return true;
						}
						return false;	
					}
				};
				((FusionMonsterCard)monstercard).setNbmaterielsfusions(2);
				return monstercard;
			case "Fusionniste":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new FusionMonsterCard(monstercard2){
					public boolean compatiblemonster(MonsterCard monster,int i){
						if (i == 0 && monster.getName().equals("Petit Ange")) {
							return true;
						} else if (i == 1 && monster.getName().equals("Mouton Mystique N°2")) {
							return true;
						} else if (monster.getType2().contains("effect") && ((EffectMonsterCard)monster).isJokermaterielfusion()){
							return true;
						}
						return false;	
					}
				};
				((FusionMonsterCard)monstercard).setNbmaterielsfusions(2);
				return monstercard;
			case "Guerrier Karbonala":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new FusionMonsterCard(monstercard2){
					public boolean compatiblemonster(MonsterCard monster,int i){
						if (i == 0 && monster.getName().equals("M-Guerrier N°1")) {
							return true;
						} else if (i == 1 && monster.getName().equals("M-Guerrier N°2")) {
							return true;
						} else if (monster.getType2().contains("effect") && ((EffectMonsterCard)monster).isJokermaterielfusion()){
							return true;
						}
						return false;	
					}
				};
				((FusionMonsterCard)monstercard).setNbmaterielsfusions(2);
				return monstercard;
			case "Hane-Hane":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new EffectMonsterCard(monstercard2){
					public boolean conditionactivation() {
						if (getBoard().getOpponentPlayer().getField().countMonstersonField()>0 ){
							return true;
						}
						return false;
					}
					
					public void actionmulti(ArrayList<Card> card) {
						if (getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0]!=null && getBoard().getOpponentPlayer().getField().getMonstersExtraArea2()[0].equals(card.get(0)) ) {
								card.get(0).setLocation(Location.EXTRADECK);
								getBoard().getOpponentPlayer().getField().removeMonsterExtraToExtra();
								
						} else {
							getBoard().getOpponentPlayer().getField().removeMonsterToHand(getBoard().getOpponentPlayer().getField().getpositioninMonsterField( (MonsterCard)card.get(0)));
						}
					}
				};
				((EffectMonsterCard)monstercard).setMultipletargetcard(true);
				((EffectMonsterCard)monstercard).setNombretotalcible(1);
				((EffectMonsterCard)monstercard).getNombrecible().add(1);
				((EffectMonsterCard)monstercard).getLocationcible().add("opponent monster field");
				((EffectMonsterCard)monstercard).setTypeeffect("flip");
				return monstercard;
			case "Loup Fleuri":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new FusionMonsterCard(monstercard2){
					public boolean compatiblemonster(MonsterCard monster,int i){
						if (i == 0 && monster.getName().equals("Croc Argente")) {
							return true;
						} else if (i == 1 && monster.getName().equals("Epines des Tenebres")) {
							return true;
						} else if (monster.getType2().contains("effect") && ((EffectMonsterCard)monster).isJokermaterielfusion()){
							return true;
						}
						return false;	
					}
				};
				((FusionMonsterCard)monstercard).setNbmaterielsfusions(2);
				return monstercard;
			case "Ninja Arme":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new EffectMonsterCard(monstercard2){
					public boolean conditionactivation() {
						if (getBoard().getActivePlayer().getField().getSpellArea().size()>0 || getBoard().getOpponentPlayer().getField().getSpellArea().size()>0 
								|| getBoard().getActivePlayer().getField().getFieldarea().size()>0 
								|| getBoard().getOpponentPlayer().getField().getFieldarea().size()>0 ){
							return true;
						}
						return false;
					}
					
					public void actionmulti(ArrayList<Card> card) {
						if (!((SpellCard)card.get(0)).getType2().contains("trap")) {
							if (getBoard().getActivePlayer().getField().getFieldarea().size()>0 && getBoard().getActivePlayer().getField().getFieldarea().get(0).equals((SpellCard)card.get(0))){
								card.get(0).setLocation(Location.GRAVEYARD);
								getBoard().getActivePlayer().getField().removeSpellfromFToGraveyard((SpellCard)card.get(0));
							}
							if (getBoard().getOpponentPlayer().getField().getFieldarea().size()>0 && getBoard().getOpponentPlayer().getField().getFieldarea().get(0).equals((SpellCard)card.get(0))){
								card.get(0).setLocation(Location.GRAVEYARD);
								getBoard().getOpponentPlayer().getField().removeSpellfromFToGraveyard((SpellCard)card.get(0));
							}
							if (getBoard().getActivePlayer().getField().getSpellArea().contains((SpellCard)card.get(0))){
								card.get(0).setLocation(Location.GRAVEYARD);
								getBoard().getActivePlayer().getField().removeSpellToGraveyard((SpellCard)card.get(0));
							} else {
								card.get(0).setLocation(Location.GRAVEYARD);
								getBoard().getOpponentPlayer().getField().removeSpellToGraveyard((SpellCard)card.get(0));
							}
						}
					}
				};
				((EffectMonsterCard)monstercard).setMultipletargetcard(true);
				((EffectMonsterCard)monstercard).setNombretotalcible(1);
				((EffectMonsterCard)monstercard).getNombrecible().add(1);
				((EffectMonsterCard)monstercard).getLocationcible().add("active opponent spell field");
				((EffectMonsterCard)monstercard).setTypeeffect("flip");
				return monstercard;
			case "Spadassin des Flammes":
				monstercard2 = new MonsterCard(n,desc,attr,esp,l,a,d,type2);
				monstercard = new FusionMonsterCard(monstercard2){
					public boolean compatiblemonster(MonsterCard monster,int i){
						if (i == 0 && monster.getName().equals("Manipulateur des Flammes")) {
							return true;
						} else if (i == 1 && monster.getName().equals("Masaki le Spadassin Legendaire")) {
							return true;
						} else if (monster.getType2().contains("effect") && ((EffectMonsterCard)monster).isJokermaterielfusion()){
							return true;
						}
						return false;	
					}
				};
				((FusionMonsterCard)monstercard).setNbmaterielsfusions(2);
				return monstercard;
			
		}
		
		return monstercard;
	}

}
