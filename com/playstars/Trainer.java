package com.playstars;
import java.io.*;

public class Trainer implements Serializable{
	 protected String name;
	 protected Mons[] party = new Mons[6];
	
	
	public Trainer() {
		this.name = "null";
	}
	
	public Trainer(String name, Mons [] monlist) {
		this.name = name;
		this.party = monlist;
	}
	
	public Mons [] getMons() {
		return party;
	}
	
	public Mons getMons(int index) {
		return party[index];
	}
	
	public void setMons(Mons [] monlist) {
		party = monlist;
	}
	
	public void setMons(Mons onemon,int index) {
		party[index] = onemon;
	}
	
	public void setname(String name) {
		this.name = name;
	}
	
	public String getname() {
		return name;
	}
}
