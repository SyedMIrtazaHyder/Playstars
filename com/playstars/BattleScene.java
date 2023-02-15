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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;
import java.util.Random;
import java.io.FileNotFoundException;
import java.io.IOException;


public class BattleScene{
	private Texture backdrop = new Texture("assets/backdrops/mainbackground.jpg"); 
	private Skin mySkin;
	private Stage battleArena;
	ShapeRenderer menuLayout;
	private BitmapFont font;
	private ProgressBar maHealth;
	private ProgressBar enemyHealth;
	float totalWidth = Gdx.graphics.getWidth();
	float totalHeight = Gdx.graphics.getHeight();
	
	private int state = 2;
	private int catchcount = 0;
	private Battle battle;
	private Player player;
	
	private InventoryScene inventory;
	private int deadmon;
	private Random randommove = new Random();
	
	Sprite mon1, mon2;
	
		
	public BattleScene( Skin mySkin, BitmapFont font, Player player, InventoryScene inventory) {
		this.inventory = inventory;
		this.player = player;
		this.mySkin = mySkin;
		battleArena = new Stage();
		this.font = font;
	}
	
	public void createTrainerBattle(Mons mymon, Mons enemymon,Trainer trainer) {
		battle = new TrainerBattle(mymon,enemymon,trainer);
		maHealth = new ProgressBar(0, mymon.getHp(), 1, false, mySkin);
		enemyHealth = new ProgressBar(0, enemymon.getHp(), 1, false, mySkin);
	}
	
	public void createWildBattle(Mons mymon, Mons enemymon) throws IOException, ClassNotFoundException {
		battle = new WildBattle(mymon,enemymon);
		maHealth = new ProgressBar(0, mymon.getHp(), 1, false, mySkin);
		enemyHealth = new ProgressBar(0, enemymon.getHp(), 1, false, mySkin);
	}
	
	public void components() {
		battleArena.clear();
		mon1 = new Sprite(new Texture("assets/sprites/"+ battle.getMons(0).getIndex() +".png"), 300, 0, -300, 400);
		mon2 = new Sprite(new Texture("assets/sprites/"+ battle.getMons(1).getIndex() +".png"), 0, 0, 300, 400);
		

		maHealth.setRange(0, battle.getMons(0).getHp());
		enemyHealth.setRange(0, battle.getMons(1).getHp());
		
		
		menuLayout = new ShapeRenderer();
//		maHealth = new ProgressBar(0, 100, 1, false, mySkin);
//		enemyHealth = new ProgressBar(0, 100, 1, false, mySkin);
		font.setColor(Color.WHITE);
		
		
		Button inventory = new TextButton("Open Bag", mySkin); //move name
		Button forfeit = new TextButton("Forfeit Battle (Score will decrease)" ,mySkin); //move name
		Button switchMon = new TextButton("Switch" ,mySkin); //move name
		Button catchMon = new TextButton("Catch Mon",mySkin);
		
		
		
		maHealth.setBounds(0, totalHeight - 20, totalWidth/2 - 50, 10);
		maHealth.setValue(battle.getMons(0).getTemphp());
		maHealth.setColor(Color.GREEN);
		
		enemyHealth.setBounds(totalWidth/2 + 50, totalHeight - 20, totalWidth/2 - 50, 10);	
		enemyHealth.setValue(battle.getMons(1).getTemphp());
		enemyHealth.setColor(Color.RED);
		
		mon1.setPosition(50, totalHeight-410);
		mon2.setPosition(totalWidth - 350, totalHeight-410);
		
		for (int i = 0; i < 5; i++) {
			try {
				Button attack = new TextButton(battle.getMons(0).getMovelist(i).getName(),mySkin);
				if (battle.getMons(0).getMovelist(i).getName().equals(""))
					continue;
				
				
				attack.setSize(120, 50);
				
				if (i == 4)
					attack.setPosition(150, 85);
				
				else if (i%2 == 0)
					attack.setPosition(50+i*100, 150);
				
				else
					attack.setPosition(50+(i-1)*100, 20);
				
				battleArena.addActor(attack);
				
				final int movedex = i;
				attack.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent input, float x, float y) {
						Moves randMove;
						
						while (true)
						{
							try {
								randMove = battle.getMons(1).getMovelist(randommove.nextInt(5));
								System.out.println(randMove.getTyping().getName());
								break;
							}
							
							catch(Exception a) {
								//a.printStackTrace();
							}
						}
						
						if (battle.getMons(0).getSpd() >= battle.getMons(1).getSpd())
						{

							battle.givedamage(battle.getMons(0).getMovelist(movedex),1);
							enemyHealth.setValue(battle.getMons(1).getTemphp());//allows the health bar to go down

							
							if (battle.getMons(1).getTemphp() > 0)
							{
								battle.givedamage(randMove,0);
								maHealth.setValue(battle.getMons(0).getTemphp());//allows the health bar to go down
							}
							
						}
						
						else {

							battle.givedamage(randMove,0);
							maHealth.setValue(battle.getMons(0).getTemphp());//allows the health bar to go down

							if (battle.getMons(0).getTemphp() > 0)
							{
								battle.givedamage(battle.getMons(0).getMovelist(movedex),1);
								enemyHealth.setValue(battle.getMons(1).getTemphp());//allows the health bar to go down
							}
						}
						
						if (battle.getMons(0).getTemphp() == 0)
							switchcomponents();
	
						if (battle.getMons(1).getTemphp() == 0 && battle.getName().equals("TrainerBattle"))
							enemySwitch();
						
						if (battle.getMons(1).getTemphp() == 0 && battle.getName().equals("WildBattle"))
							state = 5;
							
						
					}
				});
					
			}
			catch(Exception a) {
				//a.printStackTrace();
				continue;
			}
		}
		
		//---------------------------Battle Menu Options--------------------------------
		float menuButtonWidth = totalWidth/3;
		float menuButtonHeight = 50;
		
		
		inventory.setSize(menuButtonWidth, menuButtonHeight);
		inventory.setPosition(totalWidth - menuButtonWidth - 50, 50);
		inventory.setColor(Color.BROWN);
		
		forfeit.setSize(menuButtonWidth, menuButtonHeight);
		forfeit.setPosition(totalWidth - menuButtonWidth - 50, 0);
		forfeit.setColor(Color.RED);
		
		switchMon.setSize(menuButtonWidth, menuButtonHeight);
		switchMon.setPosition(totalWidth - menuButtonWidth - 50, 100);
		switchMon.setColor(Color.YELLOW);
		
		catchMon.setSize(menuButtonWidth, menuButtonHeight);
		catchMon.setPosition(totalWidth - menuButtonWidth - 50, 150);
		catchMon.setColor(Color.GREEN);
	    
		battleArena.addActor(switchMon);
		battleArena.addActor(forfeit);
		battleArena.addActor(inventory);
		battleArena.addActor(maHealth);
		battleArena.addActor(enemyHealth);
		
		if (battle.getName().equals("WildBattle"))
			battleArena.addActor(catchMon);
		
		catchMon.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				try {
				if (catchcount > 2)
				{
					System.out.println("The Mon ran away");
					state = 1;
					catchcount = 0;
				}
				
				else if (battle.throwBall())
				{
					for (int i = 0; i < 6; i ++)
					{
						if (player.getMons(i) == null)
						{
							player.setMons(Mons.getmons(battle.getMons(1).getIndex()),i);
							state = 1;
							catchcount = 0;
							break;
						}
						
						else if (i == 5)
						{
							state = 4;
							catchcount = 0;
						}
					}
					
				}
				else
				{
					//System.out.println("Are you high");
					catchcount++;
				}
				}
				catch (Exception a) {
					a.printStackTrace();
				}
				
			}
		});
		
		switchMon.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				switchcomponents();
				
			}
		});
		
		inventory.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				try {
					getInventory().getBag().clear();
					getInventory().setMons(battle.getMons(0));
					getInventory().inventoryComponents();
					getInventory().setBackButton(true);
				}
				catch (Exception a) {
					a.printStackTrace();
				}
				
				state = 3;
			}
		});
		
		forfeit.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				player.setScore(player.getScore()-10);
				state = 5;
				
			}
		});
		//add events fo r the progress bar
		
	}
	
	public void enemySwitch() {
		battleArena.clear();
		//This variable keeps track of how many dead mons or null mons the enemy has.
		//If it reaches 6, it means their entire team is empty and you've won the battle.
		int deadenemy = 0;
		int money = 0;
		for (int i = 0; i < 6; i++)
		{
			try {
				if (battle.getEnemy().getMons(i).getTemphp() > 0)
				{
					battle.setMons(1, battle.getEnemy().getMons(i));
					components();
					break;
				}
				else
				{
					money += 50;
					deadenemy++;
				}
			}
		
			catch(Exception a) {
				//a.printStackTrace();
				deadenemy++;
			}
		
		}
		
		System.out.println(deadenemy);
		if (deadenemy == 6)
		{
			player.setMoney(player.getMoney()+money);
			Win();
		}
	}

	
	public void Loss(final MenuScene menu) {
		battleArena.clear();
		
		Label loss = new Label("You've lost.\nPlayer Name: " + player.getname() + "\nYour score was " + player.getScore(),mySkin);
		loss.setSize(200, 100);
		loss.setPosition(100, 100);
		
		TextButton menuReturn = new TextButton("Return to Main Menu", mySkin);
		menuReturn.setSize(200, 40);
		menuReturn.setPosition(90, 50);
		
		battleArena.addActor(menuReturn);
		battleArena.addActor(loss);
		
		menuReturn.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				player.delete();
				menu.startcomponents();
				state = 1;
				
			}
		});
		
	}
	
	public void Win(){
		try {
		
			int index = 0;
			for (Mons pkmn: player.getMons()) {
				if (pkmn == null || pkmn.getLevel() >= 10)
					continue;
				pkmn.setLevel(pkmn.getLevel() + 1);
				player.evolveMon(pkmn, index);
				index++;
			}
			}
		catch (Exception a) {
		
		}
		
		player.setScore(player.getScore() + 20);
		
		battleArena.clear();
		Label winMessage = new Label("You have won 50 $ for every Mon defeated.",mySkin);
		winMessage.setSize(100, 100);
		winMessage.setPosition(10,130);
		
		TextButton next = new TextButton("Leave Battle", mySkin);
		next.setSize(150, 50);
		next.setPosition(10, 100);
		
		battleArena.addActor(winMessage);
		battleArena.addActor(next);
		
		next.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				state = 5;
				
			}
		});
	}
	
	public void switchcomponents() {
		battleArena.clear();
		deadmon = 0;
		
		for (int i = 0; i < 6; i++) {
			try {
				
				if (player.getMons(i).getTemphp() == 0)
				{
					player.setMons(null, i);
					deadmon++;
					continue;
				}
				
				ImageButton Newmon = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture("assets/sprites/" + player.getMons(i).getIndex() + ".png"))));
				Newmon.setSize(200, 100);
				if (i%2 == 0)
					Newmon.setPosition(400+ i*50, 100);
				else
					Newmon.setPosition(400+ i*50, 10);
				
				battleArena.addActor(Newmon);
				final int monindex = i;
				
				Newmon.addListener(new ClickListener() {
					@Override
					public void clicked(InputEvent input, float x, float y) {
						battle.setMons(0, player.getMons(monindex));
						
						Moves newmove;
						while (true) {
							try {
								newmove = battle.getMons(1).getMovelist(randommove.nextInt(5));
								System.out.println(newmove.getTyping().getName());
								//System.out.println("What what what");
								if (newmove != null)
									break;
							}
							catch (Exception a) {
								//System.out.println("tell me why");
								
							}
						}
						
						
						
						battle.givedamage(newmove, 0);
						//You take damage when switching in because the enemy gets a turn
						
						
						
						components();
						
					}
				});
				
			}
			catch(Exception a) {
				//System.out.println("No mon there");
				deadmon++;
				continue;
			}
			
			
			
		}
		
		
		if (deadmon < 6)
		{	
			
		TextButton back = new TextButton("Back",mySkin);
		back.setPosition(750, 100);
		back.setSize(75, 75);
		battleArena.addActor(back);
		
		back.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				components();
				
			}
		});
		}
		
	}
	
	public void draw(MenuScene menu) {
		menuLayout.begin(ShapeRenderer.ShapeType.Filled); //generating background rectangle
		menuLayout.rect(0, 0, totalWidth, totalHeight/2 - 100);
		menuLayout.setColor(Color.BLACK);
		menuLayout.end();
		
		menuLayout.begin(ShapeRenderer.ShapeType.Line); //generating border
		menuLayout.rect(0, 0, totalWidth, totalHeight/2 - 100);//test rectLine l8r
		menuLayout.setColor(Color.BLACK);
		menuLayout.end();
		
		menuLayout.begin(ShapeRenderer.ShapeType.Line);
		menuLayout.line(totalWidth/2, totalHeight/2 - 100, totalWidth/2, 0);
		menuLayout.setColor(Color.WHITE);
		menuLayout.end();
		
		if (deadmon == 6) {
			Loss(menu);
			deadmon = 0;
		}
		
		
		//mon1.draw(null, 0, (int) totalHeight/2);
		//mon2.draw(null, (int) totalWidth/2, (int) totalHeight/2);
		
		battleArena.draw();
		Gdx.input.setInputProcessor(battleArena); // allows the input to be processed during rendering of the buttons
	}
	
	
	public void loadSprites(SpriteBatch batch) {
		batch.draw(backdrop, 0 , 0, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		mon1.draw(batch);
		mon2.draw(batch);
		//streching our image to fit background
	}
	
	public Battle getBattle() {
		return battle;
	}
	
	public InventoryScene getInventory() {
		return inventory;
	}
	
	public void setPlayer(Player player) {
		this.player = player;
	}
	
	public int getState() {
		return state;
	}
	
	public void resetState() {
		state = 2;
	}
	
}


//public void battleScene() { //takes 2 Mons object as input
//
//for (int i = 0; i < 4; i++) { //adding 4 buttons
//	Button button2 = new TextButton("Atk" ,mySkin); //move name
//	int col_width = 200;
//	int row_height = 50;
//	button2.setSize(col_width, row_height);
//	if (i%2 == 0)
//		button2.setPosition(totalWidth/2 + 50, 50*(i + 1));
//	else
//		button2.setPosition(totalWidth/2 - col_width - 50, 50*i);
//		
//	button2.setColor(255, 0, 0, 1); //decided by move type
//	gameStage.addActor(button2);
//}
//button2.addListener(new InputListener(){ will come in use later hopefully
//    @Override
//    public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
//        outputLabel.setText("Press a Button");
//    }
//    @Override
//    public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
//        outputLabel.setText("Pressed Text Button");
//        return true;
//    }
//});
//gameStage.addActor(button2);
//}

