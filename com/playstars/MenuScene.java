package com.playstars;



import com.badlogic.gdx.Gdx;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldListener;



import java.util.EventListener;
import java.util.Random;

import java.io.IOException;

public class MenuScene {
	private BitmapFont font;
	private Texture backdrop = new Texture("assets/backdrops/beyj_vo5b_220304-min.jpg");
	private Skin mySkin;
	private Stage Menu;
	ShapeRenderer border;
	ShapeRenderer menuLayout;
	
	private int state = 1;
	
	float totalWidth = Gdx.graphics.getWidth();
	float totalHeight = Gdx.graphics.getHeight();
	
	private BattleScene battleScene;
	private InventoryScene inventory;
	
	private Player player;
	private TextButton savebutton;
	private ImageButton newMonImage;
	private Mons starters[] = new Mons[3];
	private Random randomint = new Random();
	
	public MenuScene(BitmapFont font, Skin newskin, BattleScene battle,InventoryScene inventory, Player player) {
		this.inventory = inventory;
		this.player = player;
		this.battleScene = battle;
		this.mySkin = newskin;
		this.font = font;
		
		savebutton = new TextButton("Exit Game\n(Progress will be saved)",mySkin);
		font.setColor(new Color((float)0.5,0,(float)0.5,0));
		
		Menu = new Stage();
		menuLayout = new ShapeRenderer();
		border = new ShapeRenderer();
	}
	
	
	public void startcomponents() {
		Menu.clear();
		
		Label title = new Label("Welcome to Playstars: The Rogue Begins",mySkin);
		title.setPosition(200, totalHeight-50);
		title.setFontScale(2);
		Menu.addActor(title);
		
		Label stuff = new Label("Made by Taimoor Azam\nBadar Husnain Sheikhi\nIrtaza Hyder\nCE 43 A", mySkin);
		stuff.setPosition(200, totalHeight/2);
		Menu.addActor(stuff);
		
		TextButton newbutton = new TextButton("New Game",mySkin);
		newbutton.moveBy(Gdx.graphics.getWidth()/2, 150);
		Menu.addActor(newbutton);
		
		
		final TextButton loadbutton = new TextButton("Load Game",mySkin);
		loadbutton.moveBy(Gdx.graphics.getWidth()/2, 100);
		Menu.addActor(loadbutton);
		
		TextButton exitbutton = new TextButton("Exit",mySkin);
		exitbutton.moveBy(Gdx.graphics.getWidth()/2, 50);
		Menu.addActor(exitbutton);
		
		newbutton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				Menu.clear();
				namecomponents();
				
			}
		});
		
		loadbutton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				try {
					player.setPlayer(Player.load());
					components();
				}
				catch(Exception a) {
					loadbutton.setColor((float)0.75,0,0,1);
					loadbutton.setText("No Save File Found!");
					
				}
			}
		});
		
		exitbutton.addListener(new ClickListener() {
			//The Override is here to show that it's overwriting the original clicked function
			//Apparently this specific clicked function is called every time, so it has to be overwritten
			@Override
			public void clicked(InputEvent input, float x, float y) {
				Gdx.app.exit();
			}
		});
		
	}
	
		
	public void namecomponents() {
		//player = new Player();
		player.setMons(new Mons[6]);
		TextField namegiven = new TextField("",mySkin);
		namegiven.setPosition(totalWidth/3, 100);
		namegiven.setSize(200, 40);
		
		Label explanation = new Label("Enter your name in the box below",mySkin);
		explanation.setPosition(totalWidth/3,140);
		explanation.setSize(200, 30);
		
		
		
		Menu.addActor(namegiven);
		Menu.addActor(explanation);
		
		namegiven.setTextFieldListener(new TextFieldListener() {
			@Override
			public void keyTyped(TextField textfield, char c) {
				if (c == '\n') {
					player.setname(textfield.getText());
					selectioncomponents();
				}
			}

		});
	}
	
	
	public void selectioncomponents(){
		Menu.clear();
		int index = 0;
		int monindex;
		
		while (true) {
			try {
				if (index == 3)
					break;
				
				monindex = randomint.nextInt(22);
				starters[index] = Mons.getmons(monindex);
			
				//Condition is set here so that no second or third stage Mons show up on starter screen.
				if (monindex > 0 && starters[index].getEvo() && !(Mons.getmons(monindex-1).getEvo()))
					index +=1;
			}
			
			catch(Exception a) {
				a.printStackTrace();
			}
			
		}
		
		Label chooseone = new Label("Choose one of these starting Mons.",mySkin);
		chooseone.setSize(300, 50);
		chooseone.setPosition(100, 50);
		Menu.addActor(chooseone);
		
		ImageButton mon1 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("assets/sprites/" + starters[0].getIndex() + ".png"))));
		mon1.setSize(200, 200);
		mon1.setPosition(100, 100);
		Menu.addActor(mon1);
		
		ImageButton mon2 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("assets/sprites/" + starters[1].getIndex() + ".png"))));
		mon2.setSize(200, 200);
		mon2.setPosition(300, 100);
		Menu.addActor(mon2);
		
		ImageButton mon3 = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("assets/sprites/" + starters[2].getIndex() + ".png"))));
		mon3.setSize(200, 200);
		mon3.setPosition(500, 100);
		Menu.addActor(mon3);
		
		
		
		mon1.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				player.setMons(starters[0],0);
				components();
				
			}
		});
		
		mon2.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				player.setMons(starters[1], 0);
				components();
				
			}
		});
		
		mon3.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				player.setMons(starters[2], 0);
				components();
				
			}
		});
		
		
	}
	
	public void partycomponents(Mons newmon) {
		Menu.clear();
		
		TextButton returnbutton = new TextButton("Back",mySkin);
		returnbutton.setSize(100, 50);
		returnbutton.setPosition(700, totalHeight/2);
		
		final Label choose = new Label("Click on the Mon you would like to be in the first position.",mySkin);
		choose.setSize(300, 75);
		choose.setPosition(30, 50);
		
		if (newMonImage == null)
		{
			newMonImage= new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("assets/sprites/" + newmon.getIndex() + ".png"))));
			newMonImage.setSize(200, 100);
			newMonImage.setPosition(700,totalHeight-100);
			newMonImage.setVisible(false);

		}
		
		if (newMonImage.isVisible())
		{
			choose.setText("Select which Mon you'd like to replace with your new Catch!");
			newMonImage.setBackground(new TextureRegionDrawable(new TextureRegion(new Texture("assets/sprites/" + newmon.getIndex() + ".png"))));
		}
		

		Menu.addActor(choose);
		Menu.addActor(returnbutton);
		Menu.addActor(newMonImage);
		
		returnbutton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				components();
				
			}
		});
		
		for (int i = 0; i < 6; i++) {
			try {
				ImageButton mon = new ImageButton(new TextureRegionDrawable(new Texture("assets/sprites/" + player.getMons(i).getIndex() + ".png")));
				mon.setSize(150, 150);
				
				String monstuff = "Name: " + player.getMons(i).getName() + "  Level: " + player.getMons(i).getLevel() + "\nTypes: " + 
									player.getMons(i).getMontypes(0).getName() + " " + player.getMons(i).getMontypes(1).getName() + "\n" + player.getMons(i).getTemphp() + "/" + 
										player.getMons(i).getHp() + " HP" + "\nAtk: " + player.getMons(i).getAtk() + "  SAtk: " + player.getMons(i).getSatk() + 
											"\nDef: " + player.getMons(i).getDef() + "  SDef: " + player.getMons(i).getSdef() + "\nSpd: " + player.getMons(i).getSpd();
				
				Label mondesc = new Label(monstuff,mySkin);
				mondesc.setColor(Color.ORANGE);
				mondesc.setSize(200, 100);
				
				if (i%2 == 0)
				{
					mon.setPosition(0, (totalHeight-150)-i*75);
					mondesc.setPosition(150, (totalHeight-150)-i*75);
		
				}
				else
				{
					mon.setPosition(350, (totalHeight-150)-(i-1)*75);
					mondesc.setPosition(500, (totalHeight-150)-(i-1)*75);
				}
				
				Menu.addActor(mondesc);
				Menu.addActor(mon);
				
				final int monIndex = i;
				final Mons caught = newmon;
				mon.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent input, float x, float y) {
						if (!newMonImage.isVisible())
						{
						
							Mons tempmon = player.getMons(0);
							player.setMons(player.getMons(monIndex),0 );
							player.setMons(tempmon, monIndex);
						
							try {
								partycomponents(Mons.getmons(0));
							}
							catch(Exception a) {
								a.printStackTrace();
							}
							
							
						}
						
						else
						{
							player.setMons(caught, monIndex);
							newMonImage.setVisible(false);
							partycomponents(caught);
							
							
						}
							
					}
				});
				
			}
			catch(Exception a) {
				continue;
			}
			
		}
	}
	
	public void components() {
		Menu.clear();
		TextButton inventorybutton = new TextButton("Inventory",mySkin);
		TextButton nextbutton = new TextButton("Next Fight",mySkin);
		savebutton= new TextButton("Exit Game\n(Progress will be saved)",mySkin);
		TextButton wildbutton = new TextButton("Enter Wild Battle",mySkin);
		TextButton partybutton = new TextButton("Check Party",mySkin);
		
		Label scoreSheet = new Label("Name: " + player.getname() + "\nScore: " + player.getScore() + "\nMoney: " + player.getMoney() + " $", mySkin);
		
		scoreSheet.setPosition(50, totalHeight - 150);
		scoreSheet.setSize(totalWidth/3 - 30, 65);
		
		inventorybutton.setPosition(totalWidth*2/3 +10, totalHeight-150);
		inventorybutton.setSize(totalWidth/3 -30,65);
		
		nextbutton.setPosition(totalWidth*2/3 +10, totalHeight-215);
		nextbutton.setSize(totalWidth/3 -30,65);
		
		savebutton.setPosition(totalWidth*2/3 +10, totalHeight-280);
		savebutton.setSize(totalWidth/3 -30,65);
		
		wildbutton.setPosition(totalWidth*2/3 +10, totalHeight-345);
		wildbutton.setSize(totalWidth/3 -30,65);
		
		partybutton.setPosition(totalWidth*2/3 +10, totalHeight-410);
		partybutton.setSize(totalWidth/3 -30,65);
		
		Menu.addActor(scoreSheet);
		Menu.addActor(inventorybutton);
		Menu.addActor(savebutton);
		Menu.addActor(nextbutton);
		Menu.addActor(wildbutton);
		Menu.addActor(partybutton);
		
		inventorybutton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				
				try{
					inventory.setMons(player.getMons(0));
					inventory.inventoryComponents();
					inventory.shopComponents();
					inventory.setBackButton(false);
					
				}
				catch(Exception a) {
					a.printStackTrace();
				}
				
				state = 3;
				
			}
		});
		
		savebutton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				try{
					player.save();
					startcomponents();
				}
				catch(Exception a){
					a.printStackTrace();
				}
				
			}
		});
		
		/*shopbutton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				System.out.println("shop clicked");
				
			}
		});*/
	
		nextbutton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y){
				Trainer enemy = new Trainer("Enemy",new Mons[6]);
				int randommon;
				try {
					int i = 0;
					while (i < 6)
					{
						//To make sure the enemy does not have 0 mons
						if (i == 0)
						{
							randommon = randomint.nextInt(22);
							enemy.setMons(Mons.getmons(randommon),i);
							
						}
						//This is for getting the rest of the mons. If they have any that is.
						else 
							randommon = randomint.nextInt(35);
							
						if (randommon < 23)
							enemy.setMons(Mons.getmons(randommon), i);
							
						else
							continue;
						
						if (player.getScore() < 100 && Mons.getmons(randommon).getEvo())
							i++;
						
						else if (player.getScore() > 50 && !Mons.getmons(randommon).getEvo())
							i++;
							
					}
						
						
					
				battleScene.createTrainerBattle(player.getMons(0),enemy.getMons(0),enemy);
				battleScene.components();
				
				state = 2;
				}
				catch(Exception except) {
					System.out.println("Could not load Mons Object from Memory.");
					except.printStackTrace();
				}
			}
		});
		
		wildbutton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y){
				try {
					Mons wildmon;
					while (true) {
						wildmon = Mons.getmons(randomint.nextInt(22));
						if (wildmon.getEvo())
							break;
					}
					
				battleScene.createWildBattle(player.getMons(0),wildmon);
				battleScene.components();
				
				state = 2;
				}
				
				catch(Exception except) {
					System.out.println("Could not load Mons Object from Memory.");
					except.printStackTrace();
				}
			}
		});
		
		partybutton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y){
				try {
					partycomponents(Mons.getmons(0));
					newMonImage.setVisible(false);
				}
				catch(Exception a) {
					a.printStackTrace();
				}
			}
		});
	}
	
	
	public void draw() {
		if (player.getMons(0) == null)
			for (int i = 0; i < 6; i++)
			{
				if (player.getMons(i) == null)
					continue;
				player.setMons(player.getMons(i), 0);
				player.setMons(null, i);
				break;
			}
		
		
		if (Menu.getActors().contains(savebutton,true ))
		{
			
		//generating background rectangle
		menuLayout.begin(ShapeRenderer.ShapeType.Filled);
		menuLayout.rect(totalWidth, 50, -totalWidth/3, totalHeight-100);
		menuLayout.rect(45,totalHeight-157,totalWidth/3 - 20, 85);
		menuLayout.setColor(new Color((float)0.3,0,(float)0.5,1));
		menuLayout.end();
		
		
		border.setColor(new Color((float)0.3,(float)0.2,(float)0.3,1));
		border.begin(ShapeRenderer.ShapeType.Line);
		for(int i = 0; i < 10; i++)
		{	 //generating border
			border.rect(totalWidth-i, 50-i, -totalWidth/3, totalHeight-100);
			border.rect(45-i, totalHeight-157-i, totalWidth/3 - 20, 85);
		}
		border.setColor(Color.CHARTREUSE);
		border.end();
		
		}
		
		
		
		
		Menu.draw();
		Menu.act();
		Gdx.input.setInputProcessor(Menu); // allows the input to be processed during rendering of the buttons
	}
	
	public void loadBatch(SpriteBatch batch) {
		batch.draw(backdrop, 0 , 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//streching our image to fit background
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public void setnewMonImage(boolean var) {
		newMonImage.setVisible(var);
	}
	public int getState() {
		return state;
	}
	
	public void resetState() {
		state = 1;
	}
}
