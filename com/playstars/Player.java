package com.playstars;
import java.io.*;
import java.util.*;

public class Player extends Trainer{
	private ArrayList<Items> inventory;
	private int score,money;
	private boolean status;
	private static File savefile = new File("assets/data/Saves.dat");
	
	public Player() {
		try {
		inventory = Items.getAllItems();
		money = 0;
		}
		catch(Exception a) {
			a.printStackTrace();
		}
	}
	
	public Player(String n, Mons [] monlist) throws ClassNotFoundException, IOException {
		super(n,monlist);
		score = 0;
		money = 0;
		status = false;
		inventory = Items.getAllItems();
		
	}
	
	public void setPlayer(Player newplayer) {
		this.setFullInventory(newplayer.getFullInventory());
		this.setScore(newplayer.getScore());
		this.setMoney(newplayer.getMoney());
		this.setStatus(newplayer.getStatus());
		this.setname(newplayer.getname());
		this.setMons(newplayer.getMons());
	}
	
	public void save() throws FileNotFoundException, IOException {
		FileOutputStream saver = new FileOutputStream(savefile);
		ObjectOutputStream objectsave = new ObjectOutputStream(saver);
		
		objectsave.writeObject(this);
		objectsave.close();
		saver.close();
	}
	
	public void delete() {
		savefile.delete();
	}
	
	public static Player load() throws FileNotFoundException, IOException, ClassNotFoundException  {
		FileInputStream loader = new FileInputStream(savefile);
		ObjectInputStream objectload = new ObjectInputStream(loader);
		
		Player loadedsave = new Player();
		loadedsave =  (Player) objectload.readObject();
		objectload.close();
		loader.close();
		
		
		return loadedsave;
		
	}

	public void ViewParty() {
		//Add code into this to view and select mons
	}
	
	public Items getInventory(int index) {
		return inventory.get(index);
	}
	
	public ArrayList<Items> getFullInventory(){
		return inventory;
	}
	
	public void setFullInventory(ArrayList<Items> inventory) {
		this.inventory = inventory;
	}

	public void setInventory(int index, Items a) {
		this.inventory.set(index, a);
	}
	
	public void newItem(Items item, int quantity) {
		//System.out.println(item.getName() + quantity);
		for (Items _item: inventory)
		{
			if (item.getName().equals(_item.getName())){
				//System.out.println(item.getName() + quantity);
				_item.setQuantity( _item.getQuantity() + quantity);
				return;
 			}
		}
		item.setQuantity(quantity);
		inventory.add(item);
	}
	
	public int getScore() {
		return score;
	}
	
	public void evolveMon(Mons pkmn, int index) throws ArrayIndexOutOfBoundsException, FileNotFoundException, ClassNotFoundException, IOException {
		if (pkmn.getEvo() && Mons.getmons(pkmn.getIndex() + 1).getLevel() <= pkmn.getLevel())
			setMons(Mons.getmons(pkmn.getIndex() + 1), index);
		}

	public void setScore(int score) {
		this.score = score;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}
