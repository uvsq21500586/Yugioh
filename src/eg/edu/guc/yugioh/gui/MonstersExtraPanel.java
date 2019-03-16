package eg.edu.guc.yugioh.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import eg.edu.guc.yugioh.board.player.Player;
import eg.edu.guc.yugioh.cards.Mode;

public class MonstersExtraPanel extends JPanel {

	private MonsterButton monsters2[];
	public MonstersExtraPanel(Player p) {
		super();
		setPreferredSize(new Dimension(100,100));
		//monsters = new ArrayList<MonsterButton>();
		monsters2 = new MonsterButton[1];
		this.setLayout(new GridLayout(1,1));
		this.setOpaque(true);
		this.setVisible(true);
		for(int i = 0;i<1;i++){
			//MonsterButton monsterbutton = new MonsterButton();
			monsters2[i]=new MonsterButton();
			monsters2[i].setBackground(Color.GRAY);
			monsters2[i].setOpaque(false);
			this.add(monsters2[i]);
			
		}
		/*
		for (int i = 0; i < p.getField().getMonstersArea().size(); i++) {
			monsters.get(i).setMonster(p.getField().getMonstersArea().get(i));
			monsters.get(i).setVisible(true);
			ImageIcon newIcon;
			if(monsters.get(i).getMonster().getMode()==Mode.ATTACK){
			ImageIcon img = new ImageIcon("Cards Images Database/Monsters/"+p.getField().getMonstersArea().get(i).getName()+".jpg");
			Image img2 = img.getImage();
			Image newimg = img2.getScaledInstance(62, 91,  java.awt.Image.SCALE_SMOOTH);
			newIcon = new ImageIcon(newimg);
			}else if (!monsters.get(i).getMonster().isHidden()){
				ImageIcon img = new ImageIcon("Cards Images Database/Monstersdefense/"+p.getField().getMonstersArea().get(i).getName()+".jpg");
				Image img2 = img.getImage();
				Image newimg = img2.getScaledInstance(91, 62,  java.awt.Image.SCALE_SMOOTH);
				 newIcon = new ImageIcon(newimg);
			}else{
			ImageIcon img = new ImageIcon("Cards Images Database/Card Back  Set2.png");
			Image img2 = img.getImage();
			Image newimg = img2.getScaledInstance(91, 62,  java.awt.Image.SCALE_SMOOTH);
			 newIcon = new ImageIcon(newimg);
			}
			monsters.get(i).setIcon(newIcon);
			monsters.get(i).setPreferredSize(new Dimension(62,91));
			monsters.get(i).revalidate();
			monsters.get(i).setOpaque(false);
			monsters.get(i).repaint();
		}*/
		for (int i = 0; i < 1; i++) {
			if (p.getField().getMonstersExtraArea2()[i]!=null){
			monsters2[i].setMonster(p.getField().getMonstersExtraArea2()[i]);
			monsters2[i].setVisible(true);
			ImageIcon newIcon;
			if(monsters2[i].getMonster().getMode()==Mode.ATTACK){
			ImageIcon img = new ImageIcon("Cards Images Database/Monsters/"+p.getField().getMonstersExtraArea2()[i].getName()+".jpg");
			Image img2 = img.getImage();
			Image newimg = img2.getScaledInstance(62, 91,  java.awt.Image.SCALE_SMOOTH);
			newIcon = new ImageIcon(newimg);
			}else if (!monsters2[i].getMonster().isHidden()){
				ImageIcon img = new ImageIcon("Cards Images Database/Monstersdefense/"+p.getField().getMonstersExtraArea2()[i].getName()+".jpg");
				Image img2 = img.getImage();
				Image newimg = img2.getScaledInstance(91, 62,  java.awt.Image.SCALE_SMOOTH);
				 newIcon = new ImageIcon(newimg);
			}else{
			ImageIcon img = new ImageIcon("Cards Images Database/Card Back  Set2.png");
			Image img2 = img.getImage();
			Image newimg = img2.getScaledInstance(91, 62,  java.awt.Image.SCALE_SMOOTH);
			 newIcon = new ImageIcon(newimg);
			}
			monsters2[i].setIcon(newIcon);
			monsters2[i].setPreferredSize(new Dimension(62,91));
			monsters2[i].revalidate();
			monsters2[i].setOpaque(false);
			monsters2[i].repaint();
			}
		}
	}
	
	public MonsterButton[] getMonsters2() {
		return monsters2;
	}

	public void setMonsters2(MonsterButton[] monsters2) {
		this.monsters2 = monsters2;
	}
	
	public int countMonstersonField(Player p){
		int count=0;
		for (int i=0;i<5;i++){
			if (p.getField().getMonstersArea2()[i]!=null){
				count++;
			}
		}
		return count;
	}
}
