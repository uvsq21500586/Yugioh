package eg.edu.guc.yugioh.cards;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import eg.edu.guc.yugioh.board.Board;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

public abstract class Card {

	private boolean choisi;
	private final String name;
	protected String type;
	private String description;
	private boolean isHidden;
	private Location location;
	private static Board board;

	public Card(String name, String desc) {

		this.name = name;
		this.description = desc;
		this.isHidden = true;
		choisi = false;

	}

	public Card(String name, String desc, boolean hidden, Location loc) {

		this.name = name;
		this.description = desc;
		this.isHidden = hidden;
		this.location = loc;

	}

	public abstract void action(MonsterCard monster);

	public boolean isHidden() {
		return isHidden;
	}

	public void setHidden(boolean isHidden) {
		this.isHidden = isHidden;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public static Board getBoard() {
		return board;
	}

	public static void setBoard(Board board) {
		Card.board = board;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {

		return description;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isChoisi() {
		return choisi;
	}
	public void setChoisi(boolean choisi) {

		this.choisi = choisi;
	}
	
	public Card targetCard(SpellCard spell,int ensemble){
		String lieu = spell.getLocationcible().get(ensemble);
		ArrayList<String> buttons = new ArrayList<String>();
		ArrayList<Card> listechoix = new ArrayList<Card>();
		if (lieu.contains("active")) {
			//carte du joueur actif
			if (lieu.contains("field")){
				//carte sur le terrain
				if (lieu.contains("monster")){
					//carte monstre
					for (int i=0;i<5;i++){
						if (board.getActivePlayer().getField().getMonstersArea2()[i]!=null){
							if (spell.multicompatible(ensemble, board.getActivePlayer().getField().getMonstersArea2()[i]) && !board.getActivePlayer().getField().getMonstersArea2()[i].isChoisi()){
								//carte valide
								buttons.add(board.getActivePlayer().getField().getMonstersArea2()[i].getName());
								listechoix.add(board.getActivePlayer().getField().getMonstersArea2()[i]);
							}
						}
						
					}
					if (board.getActivePlayer().getField().getMonstersExtraArea2()[0]!=null && spell.multicompatible(ensemble, board.getActivePlayer().getField().getMonstersExtraArea2()[0]) && !board.getActivePlayer().getField().getMonstersExtraArea2()[0].isChoisi()){
						//carte valide
						buttons.add(board.getActivePlayer().getField().getMonstersExtraArea2()[0].getName());
						listechoix.add(board.getActivePlayer().getField().getMonstersExtraArea2()[0]);
					}
				}
				if (lieu.contains("spell")){
					for (int i=0;i<board.getActivePlayer().getField().getSpellArea().size();i++){
						if (spell.multicompatible(ensemble, board.getActivePlayer().getField().getSpellArea().get(i)) && !board.getActivePlayer().getField().getSpellArea().get(i).isChoisi()){
							//carte valide
							buttons.add(board.getActivePlayer().getField().getMonstersArea2()[i].getName());
							listechoix.add(board.getActivePlayer().getField().getMonstersArea2()[i]);
						}
					}
					
					if (board.getActivePlayer().getField().getFieldarea().size() != 0 && spell.multicompatible(ensemble,board.getActivePlayer().getField().getFieldarea().get(0) ) && !board.getActivePlayer().getField().getFieldarea().get(0).isChoisi()){
						buttons.add(board.getActivePlayer().getField().getFieldarea().get(0).getName());
						listechoix.add(board.getActivePlayer().getField().getFieldarea().get(0));
					}
				}
			}
			if (lieu.contains("graveyard")){
				if (lieu.contains("monster")){
					for (int i=0;i<board.getActivePlayer().getField().getGraveyard().size();i++){
						if ((board.getActivePlayer().getField().getGraveyard().get(i) instanceof MonsterCard) && !board.getActivePlayer().getField().getGraveyard().get(i).isChoisi()){
							buttons.add(board.getActivePlayer().getField().getGraveyard().get(i).getName());
							listechoix.add(board.getActivePlayer().getField().getGraveyard().get(i));
						}
					}
				}
			}
		}
		if (lieu.contains("opponent")) {
			//carte du joueur passif
			if (lieu.contains("field")){
				//carte sur le terrain
				if (lieu.contains("monster")){
					//carte monstre
					for (int i=0;i<5;i++){
						if (board.getOpponentPlayer().getField().getMonstersArea2()[i]!=null){
							if (spell.multicompatible(ensemble, board.getOpponentPlayer().getField().getMonstersArea2()[i]) && !board.getOpponentPlayer().getField().getMonstersArea2()[i].isChoisi()){
								//carte valide
								buttons.add(board.getOpponentPlayer().getField().getMonstersArea2()[i].getName());
								listechoix.add(board.getOpponentPlayer().getField().getMonstersArea2()[i]);
							}
						}
						
					}
					if (board.getOpponentPlayer().getField().getMonstersExtraArea2()[0] != null && spell.multicompatible(ensemble, board.getOpponentPlayer().getField().getMonstersExtraArea2()[0]) && !board.getOpponentPlayer().getField().getMonstersExtraArea2()[0].isChoisi()){
						//carte valide
						buttons.add(board.getOpponentPlayer().getField().getMonstersExtraArea2()[0].getName());
						listechoix.add(board.getOpponentPlayer().getField().getMonstersExtraArea2()[0]);
					}
				}
				if (lieu.contains("spell")){
					for (int i=0;i<board.getOpponentPlayer().getField().getSpellArea().size();i++){
						if (spell.multicompatible(ensemble, board.getOpponentPlayer().getField().getSpellArea().get(i)) && !board.getOpponentPlayer().getField().getSpellArea().get(i).isChoisi()){
							//carte valide
							buttons.add(board.getOpponentPlayer().getField().getMonstersArea2()[i].getName());
							listechoix.add(board.getOpponentPlayer().getField().getMonstersArea2()[i]);
						}
					}
					
					if (board.getOpponentPlayer().getField().getFieldarea().size() != 0 && spell.multicompatible(ensemble,board.getOpponentPlayer().getField().getFieldarea().get(0) ) && !board.getOpponentPlayer().getField().getFieldarea().get(0).isChoisi()){
						buttons.add(board.getOpponentPlayer().getField().getFieldarea().get(0).getName());
						listechoix.add(board.getOpponentPlayer().getField().getFieldarea().get(0));
					}
				}
			}
			if (lieu.contains("graveyard")){
				if (lieu.contains("monster")){
					for (int i=0;i<board.getOpponentPlayer().getField().getGraveyard().size();i++){
						if ( (board.getOpponentPlayer().getField().getGraveyard().get(i) instanceof MonsterCard) && !board.getOpponentPlayer().getField().getGraveyard().get(i).isChoisi()){
							buttons.add(board.getOpponentPlayer().getField().getGraveyard().get(i).getName());
							listechoix.add(board.getOpponentPlayer().getField().getGraveyard().get(i));
						}
					}
				}
			}
		}
		String[] buttons2 = new String[buttons.size()];
		for (int i=0;i<buttons.size();i++){
			buttons2[i] = buttons.get(i);
		}
		int x = JOptionPane.showOptionDialog(null, "Choose one of these cards to active effect of" + spell.getName(), spell.getName(),
				JOptionPane.WARNING_MESSAGE, 0, null, buttons2, buttons2[0]);
		
		Card card = listechoix.get(x);
		listechoix.get(x).setChoisi(true);
		return card;
	}
	
	public Card targetCard(EffectMonsterCard spell,int ensemble){
		String lieu = spell.getLocationcible().get(ensemble);
		ArrayList<String> buttons = new ArrayList<String>();
		ArrayList<Card> listechoix = new ArrayList<Card>();
		if (lieu.contains("active")) {
			//carte du joueur actif
			if (lieu.contains("field")){
				//carte sur le terrain
				if (lieu.contains("monster")){
					//carte monstre
					for (int i=0;i<5;i++){
						if (board.getActivePlayer().getField().getMonstersArea2()[i]!=null){
							if (spell.multicompatible(ensemble, board.getActivePlayer().getField().getMonstersArea2()[i]) && !board.getActivePlayer().getField().getMonstersArea2()[i].isChoisi()){
								//carte valide
								buttons.add(board.getActivePlayer().getField().getMonstersArea2()[i].getName());
								listechoix.add(board.getActivePlayer().getField().getMonstersArea2()[i]);
							}
						}
						
					}
					if (board.getActivePlayer().getField().getMonstersExtraArea2()[0]!=null && spell.multicompatible(ensemble, board.getActivePlayer().getField().getMonstersExtraArea2()[0]) && !board.getActivePlayer().getField().getMonstersExtraArea2()[0].isChoisi()){
						//carte valide
						buttons.add(board.getActivePlayer().getField().getMonstersExtraArea2()[0].getName());
						listechoix.add(board.getActivePlayer().getField().getMonstersExtraArea2()[0]);
					}
				}
				if (lieu.contains("spell")){
					for (int i=0;i<board.getActivePlayer().getField().getSpellArea().size();i++){
						if (spell.multicompatible(ensemble, board.getActivePlayer().getField().getSpellArea().get(i)) && !board.getActivePlayer().getField().getSpellArea().get(i).isChoisi()){
							//carte valide
							buttons.add(board.getActivePlayer().getField().getSpellArea().get(i).getName());
							listechoix.add(board.getActivePlayer().getField().getSpellArea().get(i));
						}
					}
					
					if (board.getActivePlayer().getField().getFieldarea().size() != 0 && spell.multicompatible(ensemble,board.getActivePlayer().getField().getFieldarea().get(0) ) && !board.getActivePlayer().getField().getFieldarea().get(0).isChoisi()){
						buttons.add(board.getActivePlayer().getField().getFieldarea().get(0).getName());
						listechoix.add(board.getActivePlayer().getField().getFieldarea().get(0));
					}
				}
			}
			if (lieu.contains("graveyard")){
				if (lieu.contains("monster")){
					for (int i=0;i<board.getActivePlayer().getField().getGraveyard().size();i++){
						if ((board.getActivePlayer().getField().getGraveyard().get(i) instanceof MonsterCard) && !board.getActivePlayer().getField().getGraveyard().get(i).isChoisi()){
							buttons.add(board.getActivePlayer().getField().getGraveyard().get(i).getName());
							listechoix.add(board.getActivePlayer().getField().getGraveyard().get(i));
						}
					}
				}
			}
		}
		if (lieu.contains("opponent")) {
			//carte du joueur passif
			if (lieu.contains("field")){
				//carte sur le terrain
				if (lieu.contains("monster")){
					//carte monstre
					for (int i=0;i<5;i++){
						if (board.getOpponentPlayer().getField().getMonstersArea2()[i]!=null){
							if (spell.multicompatible(ensemble, board.getOpponentPlayer().getField().getMonstersArea2()[i]) && !board.getOpponentPlayer().getField().getMonstersArea2()[i].isChoisi()){
								//carte valide
								buttons.add(board.getOpponentPlayer().getField().getMonstersArea2()[i].getName());
								listechoix.add(board.getOpponentPlayer().getField().getMonstersArea2()[i]);
							}
						}
						
					}
					if (board.getOpponentPlayer().getField().getMonstersExtraArea2()[0] != null && spell.multicompatible(ensemble, board.getOpponentPlayer().getField().getMonstersExtraArea2()[0]) && !board.getOpponentPlayer().getField().getMonstersExtraArea2()[0].isChoisi()){
						//carte valide
						buttons.add(board.getOpponentPlayer().getField().getMonstersExtraArea2()[0].getName());
						listechoix.add(board.getOpponentPlayer().getField().getMonstersExtraArea2()[0]);
					}
				}
				if (lieu.contains("spell")){
					for (int i=0;i<board.getOpponentPlayer().getField().getSpellArea().size();i++){
						if (spell.multicompatible(ensemble, board.getOpponentPlayer().getField().getSpellArea().get(i)) && !board.getOpponentPlayer().getField().getSpellArea().get(i).isChoisi()){
							//carte valide
							buttons.add(board.getOpponentPlayer().getField().getSpellArea().get(i).getName());
							listechoix.add(board.getOpponentPlayer().getField().getSpellArea().get(i));
						}
					}
					
					if (board.getOpponentPlayer().getField().getFieldarea().size() != 0 && spell.multicompatible(ensemble,board.getOpponentPlayer().getField().getFieldarea().get(0) ) && !board.getOpponentPlayer().getField().getFieldarea().get(0).isChoisi()){
						buttons.add(board.getOpponentPlayer().getField().getFieldarea().get(0).getName());
						listechoix.add(board.getOpponentPlayer().getField().getFieldarea().get(0));
					}
				}
			}
			if (lieu.contains("graveyard")){
				if (lieu.contains("monster")){
					for (int i=0;i<board.getOpponentPlayer().getField().getGraveyard().size();i++){
						if ( (board.getOpponentPlayer().getField().getGraveyard().get(i) instanceof MonsterCard) && !board.getOpponentPlayer().getField().getGraveyard().get(i).isChoisi()){
							buttons.add(board.getOpponentPlayer().getField().getGraveyard().get(i).getName());
							listechoix.add(board.getOpponentPlayer().getField().getGraveyard().get(i));
						}
					}
				}
			}
		}
		String[] buttons2 = new String[buttons.size()];
		for (int i=0;i<buttons.size();i++){
			buttons2[i] = buttons.get(i);
		}
		int x = JOptionPane.showOptionDialog(null, "Choose one of these cards to active effect of" + spell.getName(), spell.getName(),
				JOptionPane.WARNING_MESSAGE, 0, null, buttons2, buttons2[0]);
		
		Card card = listechoix.get(x);
		listechoix.get(x).setChoisi(true);
		return card;
	}
}
