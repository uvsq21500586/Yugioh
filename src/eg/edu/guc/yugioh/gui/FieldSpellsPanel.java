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

public class FieldSpellsPanel extends JPanel {
	private ArrayList<SpellButton> spells;
	private Player p;
	
	public FieldSpellsPanel(Player p) {
		super();
		setPreferredSize(new Dimension(100,100));
		spells = new ArrayList<SpellButton>();
		for(int i = 0;i<1;i++){
			SpellButton sb = new SpellButton();
			spells.add(sb);
			sb.setBackground(Color.GRAY);
			sb.setOpaque(false);
			this.add(sb);
			
		}
	update(p);
	/*
	for(int i = 0;i<1;i++){
		SpellButton sb = new SpellButton();
		spells.add(sb);
		sb.setBackground(Color.GRAY);
		sb.setOpaque(false);
		this.add(sb);
		
	}*/
	}
	
	
	
	public ArrayList<SpellButton> getSpells() {
		return spells;
	}



	public void setSpells(ArrayList<SpellButton> spells) {
		this.spells = spells;
	}



	public Player getP() {
		return p;
	}



	public void setP(Player p) {
		this.p = p;
	}



	public void update(Player p){
		//spells = new ArrayList<SpellButton>();
		this.setLayout(new GridLayout(1,1));
		this.setOpaque(true);
		this.setVisible(true);
		//for(int i = 0;i<p.getField().getSpellArea().size();i++){
		for(int i = 0;i<p.getField().getFieldarea().size();i++){
			if(p.getField().getFieldarea().get(i)!=null){
			SpellButton spellbutton = new SpellButton();
			//spells.get(i).setSpells(p.getField().getMonstersArea().get(i));
			spells.get(i).setSpell(p.getField().getFieldarea().get(i));
			spells.get(i).setVisible(true);
			//ImageIcon img = new ImageIcon("Cards Images Database/Card Back  Set2.png");
			ImageIcon img = new ImageIcon("Cards Images Database/Spells/"+p.getField().getFieldarea().get(p.getField().getFieldarea().size()-1).getName()+".jpg");
			Image img2 = img.getImage();
			Image newimg = img2.getScaledInstance(62, 91,  java.awt.Image.SCALE_SMOOTH);
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
		for (int i = 0; i < 1-p.getField().getFieldarea().size(); i++) {
		
			JButton but = new JButton();
			but.setOpaque(false);
			but.setOpaque(false);
			spells.add((SpellButton) but);
			this.add(but);
		}*/
	}
}
