package com.playstars;

import java.io.*;
import java.util.*;

enum Effect {HEAL, BUFF, LEVEL, DEATH} // labels
public class Items implements Serializable{
    private static final File itemShop = new File("assets/data/ItemShoppe.dat");
    //public static ArrayList<Items> shopItems = new ArrayList<Items>();
    private String name;
    private int price;
    private Effect effect;
    private int quantity;
    //Scanner in = new Scanner(System.in);
    int healAmt;
    int[] buffAmt = new int[6];
    private final static int totalItems = 10;
    //maybe add quantity in future

    public Items(String name, int price, Effect effect, int healAmt, int[] buffAmt) throws IOException, ClassNotFoundException{ //member functions
        this.name = name;
        this.price = price;
        this.healAmt = healAmt;
        this.buffAmt = buffAmt;
        switch (effect) {
            case HEAL:
                this.effect = Effect.HEAL;
                break;

            case BUFF:
                this.effect = Effect.BUFF;
                break;

            case LEVEL:
                this.effect = Effect.LEVEL;
                break;

            default:
                //hopefully it never comes here
                this.effect = Effect.DEATH;
        }
        quantity = 0;
    }

    //helper functions
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Effect getEffect() {
        return effect;
    }

    public void setEffect(Effect effect) {
        this.effect = effect;
    }

    public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static ArrayList<Items> getAllItems() throws IOException, ClassNotFoundException{
    	ArrayList<Items> shopItems = new ArrayList<Items>();
    	
    	FileInputStream FIS = new FileInputStream(itemShop);
        ObjectInputStream OIS = new ObjectInputStream(FIS);
        
        for (int i = 0; i < totalItems; i++) {
        	shopItems.add((Items) OIS.readObject() );
        	}
        
        OIS.close();
        return shopItems;
    }
    
    public static Items getItem(String name) throws IOException, ClassNotFoundException{
        //ArrayList<Items> shopItems = new ArrayList<Items>(1);
        FileInputStream FIS = new FileInputStream(itemShop);
        ObjectInputStream OIS = new ObjectInputStream(FIS);
        
        for (int i = 0; i < totalItems; i++) {
        	Items currentItem = (Items) OIS.readObject();
        	
        	if (name.equals(currentItem.getName())) //found the desired item so we return it and exit
        		{
        			OIS.close();
        			return currentItem;
        		}
        }
        //if item name not found return the specified item below
        OIS.close();
        return new Items("Glitched Item", 0, Effect.DEATH, 0, new int[]{0, 0, 0, 0, 0, 0}) ;
    }

    public static void writeFile(ArrayList<Items> bunchOfItems) throws IOException, ClassNotFoundException{
        FileOutputStream FOS = new FileOutputStream(itemShop);
        ObjectOutputStream OOS = new ObjectOutputStream(FOS);
        
        for (Items _item: bunchOfItems) {
        	OOS.writeObject(_item);
        	//System.out.println(_item.getName());
        	}
        
        OOS.close();
    }
    
    public void useItems(Mons mon) {
    	if (getEffect().equals(Effect.BUFF))
    		buffItem(mon);
    	else if (getEffect().equals(Effect.HEAL))
    		healItem(mon);
    	else if (getEffect().equals(Effect.LEVEL))
    		levelUp(mon);
    	else
    		mon.setStatus(false);
    	quantity--;
    }

    public int sellItems(Items userItem){ //alter the user money value
        return userItem.price;
    }

    public void healItem(Mons pkmn){
        pkmn.setTemphp(pkmn.getTemphp() + healAmt);
    }

    public void buffItem(Mons pkmn){
    	pkmn.setAtk(pkmn.getAtk() + buffAmt[1]);
    	pkmn.setHp(pkmn.getHp() + buffAmt[0]);
    	pkmn.setDef(pkmn.getDef() + buffAmt[2]);
    	pkmn.setSatk(pkmn.getSatk() + buffAmt[3]);
    	pkmn.setSdef(pkmn.getSdef() + buffAmt[4]);
    	pkmn.setSpd(pkmn.getSpd() + buffAmt[5]);
    	
    }
    
    public void levelUp(Mons pkmn) {
    	pkmn.setLevel(pkmn.getLevel() + 1);
    }
}