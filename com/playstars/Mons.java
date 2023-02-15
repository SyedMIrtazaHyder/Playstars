package com.playstars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import java.io.*;

public class Mons implements Serializable{
	private Types [] montypes = new Types[2];
	private boolean status;
	private int hp,atk,satk,def,sdef,spd,temphp,level;
	private int catchrate;
	private Moves [] movelist = new Moves[5];
	//The index will also decide what image is posted on screen
	String name;
	private int index;
	
	//Decides if a mon can evolve or not
	private boolean canevo = false;
	//Adding an image to the mon;
	//private Texture sprite;
	//Enter the moves attribute here
	
//	public Mons(Mons mon) {
//		this(mon.getName(),
//			new Types[] {mon.getMontypes(0), mon.getMontypes(1)},
//			mon.getHp(), mon.getAtk(), mon.getDef(), mon.getSatk(), mon.getSdef(), mon.getSpd(),
//			mon.getLevel(), mon.getCatchrate(), mon.get);
//	}
	
	public Mons() {
		
	}
	
	public Mons(String name, Types [] montypes, int hp, int atk, int satk, int def, int sdef, int spd, int level, int catchrate, Moves [] moves, int index, boolean evo) {
		this.name = name;
		this.montypes = montypes;
		this.status = true;
		
		this.hp = hp;
		this.atk = atk;
		this.satk = satk;
		this.def = def;
		this.sdef = sdef;
		this.spd = spd;
		this.temphp = hp;
		this.level = level;
		
		this.catchrate = catchrate;
		movelist = moves;
		this.index = index;
		this.canevo = evo;
		
		//this.sprite = new Texture(Gdx.files.internal(index + ".png"));
		
	}
	
	public static void writeMons(Mons [] mons) throws FileNotFoundException, IOException {
		
		//Opens a file to write a new Mons class object to it.
		//Only appends data at the end of the file. Need to delete in order to write all data
		File monfile = new File("assets/data/Mondex.dat");
		FileOutputStream monload = new FileOutputStream(monfile);
		ObjectOutputStream monloader = new ObjectOutputStream(monload);
		
		
		for (int i = 0; i < mons.length; i++)
			monloader.writeObject(mons[i]);
		
		monloader.close();
		monload.close();
		
	}
	
	public static Mons getmons(int index) throws FileNotFoundException, IOException, ClassNotFoundException, ArrayIndexOutOfBoundsException {
		File monfile = new File("assets/data/Mondex.dat");
		FileInputStream monload = new FileInputStream(monfile);
		ObjectInputStream monloader = new ObjectInputStream(monload);
		
		for (int i = 0; i < index+1; i++) {
			Mons tempmon = (Mons)monloader.readObject();
			
			if (tempmon.getIndex() == index)
			{
				monload.close();
				monloader.close();
				return tempmon;
			}
			
		}
		
		monload.close();
		monloader.close();
		//Returns a blank object if nothing is found
		return (Mons)new Object();
		
	}
	
	/*public void setSprite(int index) {
		sprite = new Texture(Gdx.files.internal(index + ".png"));
	}
	
	public Texture getSprite() {
		return sprite;
	}*/

	public Types getMontypes(int index) {
		return montypes[index];
	}


	public void setMontypes(Types tp, int index) {
		montypes[index] = tp;
	}


	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public int getHp() {
		return hp;
	}


	public void setHp(int hp) {
		this.hp = hp;
	}


	public int getAtk() {
		return atk;
	}


	public void setAtk(int atk) {
		this.atk = atk;
	}



	public int getSatk() {
		return satk;
	}


	public void setSatk(int satk) {
		this.satk = satk;
	}


	public int getDef() {
		return def;
	}


	public void setDef(int def) {
		this.def = def;
	}


	public int getSdef() {
		return sdef;
	}


	public void setSdef(int sdef) {
		this.sdef = sdef;
	}


	public int getSpd() {
		return spd;
	}


	public void setSpd(int spd) {
		this.spd = spd;
	}



	public int getTemphp() {
		return temphp;
	}


	public void setTemphp(int temphp) {
		if (temphp < 0)
			this.temphp = 0;
		else if (temphp > this.hp)
			this.temphp = this.hp;
		else
			this.temphp = temphp;
		
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public int getCatchrate() {
		return catchrate;
	}


	public void setCatchrate(int catchrate) {
		this.catchrate = catchrate;
	}


	public Moves getMovelist(int index) {
		return movelist[index];
	}


	public void setMovelist(Moves move, int index) {
		this.movelist[index] = move;
	}
	
	public void setIndex(int i) {
		this.index = i;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public boolean getEvo() {
		return canevo;
	}
	
	public void setEvo(boolean evo) {
		canevo = evo;
	}
	
	public Mons evolveMon() throws ArrayIndexOutOfBoundsException, FileNotFoundException, ClassNotFoundException, IOException {
		if (getmons(index + 1).getLevel() <= level & canevo)
			return getmons(index + 1);
		return this;
	}
}
