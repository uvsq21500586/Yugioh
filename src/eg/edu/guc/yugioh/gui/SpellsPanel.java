package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Mode;
import eg.edu.guc.yugioh.cards.MonsterCard;
import eg.edu.guc.yugioh.cards.spells.SpellCard;

public class SpellsPanel extends JPanel {
	private ArrayList<SpellButton> spells;
	private Player p;
	
	public SpellsPanel(Player p) {
		super();
		setPreferredSize(new Dimension(500,100));
		spells = new ArrayList<SpellButton>();
		for(int i = 0;i<5;i++){
			SpellButton sb = new SpellButton();
			spells.add(sb);
			sb.setBackground(Color.GRAY);
			sb.setOpaque(false);
			this.add(sb);
			
		}
	update(p);
	}

	public ArrayList<SpellButton> getSpells() {
		return spells;
	}

	public void setSpells(ArrayList<SpellButton> spells) {
		this.spells = spells;
	}
	
	public void update(Player p){
		//spells = new ArrayList<SpellButton>();
		this.setLayout(new GridLayout(1,5));
		this.setOpaque(true);
		this.setVisible(true);
		for(int i = 0;i<p.getField().getSpellArea().size();i++){
			spells.get(i).setSpell(p.getField().getSpellArea().get(i));
			if(p.getField().getSpellArea().get(i)!=null){
			SpellButton spellbutton = new SpellButton();
			spells.get(i).setVisible(true);
			ImageIcon img;
			if(!spells.get(i).getSpell().isHidden()){
				img = new ImageIcon("Cards Images Database/Spells/"+p.getField().getSpellArea().get(i).getName()+".jpg");
			}else {
				img = new ImageIcon("Cards Images Database/Card Back2.png");
			}
			
			Image img2 = img.getImage();
			Image newimg = img2.getScaledInstance(62, 91,  java.awt.Image.SCALE_SMOOTH);
			/*ImageIcon newIcon = new ImageIcon(newimg);
			spellbutton.setIcon(newIcon);
			spellbutton.setSpell(p.getField().getSpellArea().get(i));
			spellbutton.setOpaque(false);
			spells.add(spellbutton);
			this.add(spellbutton);*/
			ImageIcon newIcon = new ImageIcon(newimg);
			spellbutton.setIcon(newIcon);
			spells.get(i).setIcon(newIcon);
			//spells.setSpell(p.getField().getFieldarea().get(i));
			spells.get(i).setOpaque(false);
			//spells.add(spellbutton);
			//this.add(spellbutton);
			spells.get(i).revalidate();
			spells.get(i).repaint();
		
			}
			
		}
		/*
		for (int i = 0; i < 5-p.getField().getSpellArea().size(); i++) {
		
			JButton but = new JButton();
			but.setOpaque(false);
			but.setOpaque(false);
			spells.add(but);
			this.add(but);
		}*/
	}

}
