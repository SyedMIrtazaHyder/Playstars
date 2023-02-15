package com.playstars;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

import java.io.IOException;
import java.util.*;


public class InventoryScene {
	//Also takes in a user
	private int state = 3;
	private Player user;
	private Skin skin;
	private Stage bag;
	private Texture backdrop = new Texture("assets/backdrops/Backpack.png");
	private BitmapFont font;
	float totalWidth = Gdx.graphics.getWidth();
	float totalHeight = Gdx.graphics.getHeight();
	private Mons pkmn;
	ShapeRenderer shape;
	Button buy;
	private TextButton battleBackButton;
	Label Credit;
	Button userPotion1, userPotion2, userPotion3, userCandy, userHpItems, userAtkItems, userDefItems, userSpDefItems, userSpAtkItems, userSpdItems;
	Spinner potion1, potion2, potion3, candy, atk, def, hp, spAtk, spDef, spd; 
	Label labelP1, labelP2, labelP3, labelC, labelHp, labelAtk, labelDef, labelSpAtk, labelSpDef, labelSpd;
	ArrayList<Items> shopItems;
	
	public InventoryScene(Skin skin, BitmapFont font, Player user, Mons pkmn) {
		this.user = user;
		this.skin = skin;
		bag = new Stage();		
		this.font = font;
		this.pkmn = pkmn;
	}
	
	public void inventoryComponents() throws ClassNotFoundException, IOException {
		shape = new ShapeRenderer();
		Table userTable = new Table();
		userTable.setPosition(totalWidth/4, totalHeight/2);
		
		ArrayList<Items> shopItems = Items.getAllItems();
		
		userPotion1 = new TextButton(shopItems.get(0).getName(), skin);
		userPotion2 = new TextButton(shopItems.get(1).getName(), skin);
		userPotion3 = new TextButton(shopItems.get(2).getName(), skin);
		userCandy = new TextButton(shopItems.get(3).getName(), skin);
		userHpItems = new TextButton(shopItems.get(4).getName(), skin);
		userAtkItems = new TextButton(shopItems.get(5).getName(), skin);
		userDefItems = new TextButton(shopItems.get(6).getName(), skin);
		userSpAtkItems = new TextButton(shopItems.get(7).getName(), skin);
		userSpDefItems = new TextButton(shopItems.get(8).getName(), skin);
		userSpdItems = new TextButton(shopItems.get(9).getName(), skin);	
		
		
		battleBackButton = new TextButton("Back",skin);
		battleBackButton.setSize(75, 50);
		battleBackButton.setPosition(totalWidth/2 - 200, 0);
		
		Label currentMon = new Label("These items will currently affect " + pkmn.getName(),skin);
		currentMon.setSize(200, 50);
		currentMon.setPosition(50, totalHeight-50);
		
		
		labelP1 = new Label(Integer.toString(user.getInventory(0).getQuantity()), skin);		
		labelP2 = new Label(Integer.toString(user.getInventory(1).getQuantity()), skin);		
		labelP3 = new Label(Integer.toString(user.getInventory(2).getQuantity()), skin);		
		labelC = new Label(Integer.toString(user.getInventory(3).getQuantity()), skin);		
		labelHp = new Label(Integer.toString(user.getInventory(4).getQuantity()), skin);		
		labelAtk = new Label(Integer.toString(user.getInventory(5).getQuantity()), skin);		
		labelDef = new Label(Integer.toString(user.getInventory(6).getQuantity()), skin);		
		labelSpAtk = new Label(Integer.toString(user.getInventory(7).getQuantity()), skin);		
		labelSpDef = new Label(Integer.toString(user.getInventory(8).getQuantity()), skin);		
		labelSpd = new Label(Integer.toString(user.getInventory(9).getQuantity()), skin);
		
		userTable.add(userPotion1).width(200);
		userTable.add(labelP1);
		userTable.row();
		userTable.add(userPotion2).width(200);
		userTable.add(labelP2);
		userTable.row();
		userTable.add(userPotion3).width(200);
		userTable.add(labelP3);
		userTable.row();
		userTable.add(userCandy).width(200);
		userTable.add(labelC);
		userTable.row();
		userTable.add(userHpItems).width(200);
		userTable.add(labelHp);
		userTable.row();
		userTable.add(userAtkItems).width(200);
		userTable.add(labelAtk);
		userTable.row();
		userTable.add(userDefItems).width(200);
		userTable.add(labelDef);
		userTable.row();
		userTable.add(userSpAtkItems).width(200);
		userTable.add(labelSpAtk);
		userTable.row();
		userTable.add(userSpDefItems).width(200);
		userTable.add(labelSpDef);
		userTable.row();
		userTable.add(userSpdItems).width(200);
		userTable.add(labelSpd);
		userTable.row();
		
		Credit = new Label("Current Money: " + user.getMoney(), skin);
		
		//buy.setPosition(totalWidth/2 - buy.getWidth() - 50, 0);
		Credit.setPosition(50, 10);
		
		bag.addActor(Credit);
		bag.addActor(userTable);
		bag.addActor(battleBackButton);
		bag.addActor(currentMon);
		
		
		battleBackButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				state = 2;
				
			}
		});
		
		userPotion1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) { //for potion
					if(user.getInventory(0).getQuantity() > 0 & pkmn.getTemphp() < pkmn.getHp()) {
						//System.out.printf("Old: %d", pkmn.getTemphp());
						user.getInventory(0).useItems(pkmn);
						//System.out.printf("New: %d", pkmn.getTemphp());
						labelP1.setText(user.getInventory(0).getQuantity());
					}
					
				}
			});
		
		userPotion2.addListener(new ClickListener() { //for ultra potion
			@Override
			public void clicked(InputEvent input, float x, float y) {
				if(user.getInventory(1).getQuantity() > 0 & pkmn.getTemphp() < pkmn.getHp()) {
					//System.out.printf("Old: %d", pkmn.getTemphp());
					user.getInventory(1).useItems(pkmn);
					//System.out.printf("New: %d", pkmn.getTemphp());
					labelP2.setText(user.getInventory(1).getQuantity());
				}
				}
			});
		
		userPotion3.addListener(new ClickListener() { //for master potion
			@Override
			public void clicked(InputEvent input, float x, float y) {
				if(user.getInventory(2).getQuantity() > 0 & pkmn.getTemphp() < pkmn.getHp()) {
					//System.out.printf("Old: %d", pkmn.getTemphp());
					user.getInventory(2).useItems(pkmn);
					//System.out.printf("New: %d", pkmn.getTemphp());
					labelP3.setText(user.getInventory(2).getQuantity());
				}
				}
			});
		
		userCandy.addListener(new ClickListener() { //for Candy
			@Override
			public void clicked(InputEvent input, float x, float y) {
					if(user.getInventory(3).getQuantity() > 0  & pkmn.getLevel() < 10) {
						System.out.printf("Old Level %d\n", pkmn.getLevel()); 
						user.getInventory(3).useItems(pkmn);
						labelC.setText(user.getInventory(3).getQuantity());
						System.out.printf("New Level %d\n", pkmn.getLevel()); 

					}
				}
			});
		
		userHpItems.addListener(new ClickListener() { //for hp buff
			@Override
			public void clicked(InputEvent input, float x, float y) {
					if(user.getInventory(4).getQuantity() > 0 & pkmn.getHp() < 60) {
						user.getInventory(4).useItems(pkmn);
						labelHp.setText(user.getInventory(4).getQuantity());
						System.out.printf("New stats: %d, %d, %d, %d, %d, %d\n", 
						pkmn.getHp(), pkmn.getAtk(), pkmn.getDef(), pkmn.getSatk(), pkmn.getSdef(), pkmn.getSpd());

					}
				}
			});
		
		userAtkItems.addListener(new ClickListener() { //for atk buff
			@Override
			public void clicked(InputEvent input, float x, float y) {
					if(user.getInventory(5).getQuantity() > 0 & pkmn.getAtk() < 30) {
						user.getInventory(5).useItems(pkmn);
						labelAtk.setText(user.getInventory(5).getQuantity());
						System.out.printf("New stats: %d, %d, %d, %d, %d, %d\n", 
						pkmn.getHp(), pkmn.getAtk(), pkmn.getDef(), pkmn.getSatk(), pkmn.getSdef(), pkmn.getSpd());

					}
				}
			});
		
		userDefItems.addListener(new ClickListener() { //for def buff
			@Override
			public void clicked(InputEvent input, float x, float y) {
					if(user.getInventory(6).getQuantity() > 0 & pkmn.getDef() < 30) {
						user.getInventory(6).useItems(pkmn);
						labelDef.setText(user.getInventory(6).getQuantity());
						System.out.printf("New stats: %d, %d, %d, %d, %d, %d\n", 
						pkmn.getHp(), pkmn.getAtk(), pkmn.getDef(), pkmn.getSatk(), pkmn.getSdef(), pkmn.getSpd());

					}
				}
			});
		
		userSpAtkItems.addListener(new ClickListener() { //for spAtk buff
			@Override
			public void clicked(InputEvent input, float x, float y) {
					if(user.getInventory(7).getQuantity() > 0 & pkmn.getSatk() < 30) {
						user.getInventory(7).useItems(pkmn);
						labelSpAtk.setText(user.getInventory(7).getQuantity());
						System.out.printf("New stats: %d, %d, %d, %d, %d, %d\n", 
						pkmn.getHp(), pkmn.getAtk(), pkmn.getDef(), pkmn.getSatk(), pkmn.getSdef(), pkmn.getSpd());

					}
				}
			});
		
		userSpDefItems.addListener(new ClickListener() { //for Spdef buff
			@Override
			public void clicked(InputEvent input, float x, float y) {
					if(user.getInventory(8).getQuantity() > 0 & pkmn.getSdef() < 30) {
						user.getInventory(8).useItems(pkmn);
						labelSpDef.setText(user.getInventory(8).getQuantity());
						System.out.printf("New stats: %d, %d, %d, %d, %d, %d\n", 
						pkmn.getHp(), pkmn.getAtk(), pkmn.getDef(), pkmn.getSatk(), pkmn.getSdef(), pkmn.getSpd());
					}
				}
			});
		
		userSpdItems.addListener(new ClickListener() { //for spd buff
			@Override
			public void clicked(InputEvent input, float x, float y) {
					if(user.getInventory(9).getQuantity() > 0 & pkmn.getSpd() < 30) {
//						System.out.printf("Old stats: %d, %d, %d, %d, %d, %d", 
//								pkmn.getHp(), pkmn.getAtk(), pkmn.getDef(), pkmn.getSatk(), pkmn.getSdef(), pkmn.getSpd());
						user.getInventory(9).useItems(pkmn);
						labelSpd.setText(user.getInventory(9).getQuantity());
//						System.out.printf("New stats: %d, %d, %d, %d, %d, %d", 
//								pkmn.getHp(), pkmn.getAtk(), pkmn.getDef(), pkmn.getSatk(), pkmn.getSdef(), pkmn.getSpd());
						
					}
				}
			});
	}

	public void shopComponents() throws ClassNotFoundException, IOException {
		
		
		bag.clear();
		shape = new ShapeRenderer();
		Table shopTable = new Table();
		//Table userTable = new Table();
		int pPrice, p2Price, p3Price, cPrice, hpPrice, atkPrice,defPrice, spAPrice, spDPrice, spdPrice;
		buy = new TextButton("Buy", skin);
		//Credit = new Label("Current Money: " + user.getMoney(), skin);
		
		buy.setPosition(totalWidth/2 - buy.getWidth() - 50, 0);
		
		TextButton backbutton = new TextButton("Back",skin);
		backbutton.setSize(75, 50);
		backbutton.setPosition(totalWidth/2 - buy.getWidth() - 125, 0);
		
		
		shopItems = Items.getAllItems();
		potion1 = new Spinner(shopItems.get(0).getName(), 5, skin, shopItems.get(0).getPrice());
		potion2 = new Spinner(shopItems.get(1).getName(), 5, skin, shopItems.get(1).getPrice());
		potion3 = new Spinner(shopItems.get(2).getName(), 5, skin, shopItems.get(2).getPrice());
		candy = new Spinner(shopItems.get(3).getName(), 5, skin, shopItems.get(3).getPrice());
		hp = new Spinner(shopItems.get(4).getName(), 5, skin, shopItems.get(4).getPrice());
		atk = new Spinner(shopItems.get(5).getName(), 5, skin, shopItems.get(5).getPrice());
		def = new Spinner(shopItems.get(6).getName(), 5, skin, shopItems.get(6).getPrice());
		spAtk = new Spinner(shopItems.get(7).getName(), 5, skin, shopItems.get(7).getPrice());
		spDef = new Spinner(shopItems.get(8).getName(), 5, skin, shopItems.get(8).getPrice());
		spd = new Spinner(shopItems.get(9).getName(), 5, skin, shopItems.get(9).getPrice());			
		
		
		shopTable.setPosition(3*totalWidth/4, totalHeight/2);
		
		potion1.display(shopTable, totalWidth, 50, 15);
		potion2.display(shopTable, totalWidth, 50, 15);
		potion3.display(shopTable, totalWidth, 50, 15);
		candy.display(shopTable, totalWidth, 50, 15);
		hp.display(shopTable, totalWidth, 50, 15);
		atk.display(shopTable, totalWidth, 50, 15);
		def.display(shopTable, totalWidth, 50, 15);
		spAtk.display(shopTable, totalWidth, 50, 15);
		spDef.display(shopTable, totalWidth, 50, 15);
		spd.display(shopTable, totalWidth, 50, 15);
		
	    
		Spinner.totalPrice.setPosition(totalWidth/2 + 50, 10);
		
	    bag.addActor(shopTable);
	    inventoryComponents();
	    bag.addActor(Spinner.totalPrice);
	    bag.addActor(buy);
		bag.addActor(backbutton);
	    
	    
	    backbutton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				state = 1;
				
			}
		});
	    
	    buy.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				int amt = Spinner.total;
				if (amt <= user.getMoney()) {
					user.setMoney(user.getMoney() - amt);
					Credit.setText("Current Money: " + user.getMoney());
					
					user.newItem(shopItems.get(0), Integer.parseInt(potion1.getObjQuantity().getText().toString()));
					labelP1.setText(user.getInventory(0).getQuantity());
					
					
					user.newItem(shopItems.get(1), Integer.parseInt(potion2.getObjQuantity().getText().toString()));
					labelP2.setText(user.getInventory(1).getQuantity());
					
					
					user.newItem(shopItems.get(2), Integer.parseInt(potion3.getObjQuantity().getText().toString()));
					labelP3.setText(user.getInventory(2).getQuantity());
					
					
					user.newItem(shopItems.get(3), Integer.parseInt(candy.getObjQuantity().getText().toString()));
					labelC.setText(user.getInventory(3).getQuantity());
					
					
					user.newItem(shopItems.get(4), Integer.parseInt(hp.getObjQuantity().getText().toString()));
					labelHp.setText(user.getInventory(4).getQuantity());
					
					
					user.newItem(shopItems.get(5), Integer.parseInt(atk.getObjQuantity().getText().toString()));
					labelAtk.setText(user.getInventory(5).getQuantity());
					
					
					user.newItem(shopItems.get(6), Integer.parseInt(def.getObjQuantity().getText().toString()));
					labelDef.setText(user.getInventory(6).getQuantity());
					
					
					user.newItem(shopItems.get(7), Integer.parseInt(spAtk.getObjQuantity().getText().toString()));
					labelSpAtk.setText(user.getInventory(7).getQuantity());
					
					
					user.newItem(shopItems.get(8), Integer.parseInt(spDef.getObjQuantity().getText().toString()));
					labelSpDef.setText(user.getInventory(8).getQuantity());
					
					
					user.newItem(shopItems.get(9), Integer.parseInt(spd.getObjQuantity().getText().toString()));
					labelSpd.setText(user.getInventory(9).getQuantity());
					
					Spinner.totalPrice.setText("Total Price: 0");
					
					
				}
				else
					System.out.println("Not enough Money");
			}
		});
	}
	
	public void draw() {
		bag.draw();
		Gdx.input.setInputProcessor(bag);
		shape.begin(ShapeRenderer.ShapeType.Filled);
		shape.rectLine(totalWidth/2, 0, totalWidth/2, totalHeight, 8);
		shape.setColor(Color.BLACK);
		shape.end();
	}
	
	public void loadBatch(SpriteBatch batch) {
		batch.draw(backdrop, 0 , 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());//streching our image to fit background
	}

	//This function is here to make sure two back buttons do not show up on the menu
	public void setBackButton(boolean var) {
		battleBackButton.setVisible(var);
	}
	
	public Stage getBag() {
		return bag;
	}
	
	public int getState() {
		return state;
	}
	public void resetState() {
		state = 3;
	}
	
	public void setMons(Mons mon) {
		this.pkmn = mon;
	}
	
}
