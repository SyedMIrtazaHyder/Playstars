package com.playstars;
import java.util.*;
import java.io.*;

public class Moves implements Serializable{
    private static final File moveFile = new File("assets/data/AllMoves.dat");
    private String name;
    private int damage;
    private int accuracy;
    private boolean isPhy;
    private Types typing;

    //constructor
    
    public Moves() {
    	this.name = "";
    }
    
    public Moves(String name, int damage, int accuracy, boolean isPhy, Types typing) {
        this.name = name;
    	this.damage = damage;
        this.accuracy = accuracy;
        this.isPhy = isPhy;
        this.typing = typing;
    }
    
    public static void saveMoves(Moves [] movelist) throws FileNotFoundException, IOException, ClassNotFoundException {
    	FileOutputStream saver = new FileOutputStream(moveFile);
    	ObjectOutputStream movesave = new ObjectOutputStream(saver);
    	
    	for (int i = 0; i < movelist.length; i++) {
    		movesave.writeObject(movelist[i]);
    	}
    	
    	saver.close();
    	movesave.close();
    	
    }
    
    public static Moves getMoves(int index)throws FileNotFoundException, IOException, ClassNotFoundException, ArrayIndexOutOfBoundsException  {
    	FileInputStream loader = new FileInputStream(moveFile);
		ObjectInputStream moveload = new ObjectInputStream(loader);
		
		Moves loadedmove = new Moves("", 0,0,false, new Types());
		for (int i = 0; i < index+1; i++)
			loadedmove = (Moves) moveload.readObject();
		
		
		moveload.close();
		loader.close();
		
		return loadedmove;
    }
    

    //helper functions
    
    public String getName() {
    	return name;
    }
    
    public void setName(String name) {
    	this.name = name;
    }
    
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public boolean isPhy() {
        return isPhy;
    }

    public void setPhy(boolean phy) {
        isPhy = phy;
    }

    public Types getTyping() {
        return typing;
    }

    public void setTyping(Types typing) {
        this.typing = typing;
    }
}
