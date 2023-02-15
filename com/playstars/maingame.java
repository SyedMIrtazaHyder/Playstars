package com.playstars;

import com.badlogic.gdx.ApplicationAdapter;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.*;

import java.util.EventListener;

public class maingame extends ApplicationAdapter {
	private SpriteBatch batch;
	private Texture img;
	private BitmapFont font;
	//private Stage gameStage;
	private Player player;
	private Skin mySkin;
	private BattleScene battleScene;
	private MenuScene menu;
	private InventoryScene inventory;
	
	private int state = 1;
	
	@Override
	public void create () {
		player = new Player();
		batch = new SpriteBatch();
		img = new Texture("assets/backdrops/Backpack.png");
		font = new BitmapFont();
		
		Types randomtyping= new Types();
		//gameStage = new Stage();
		//----------------Battle Stage creation----------------------
	
		mySkin = new Skin(Gdx.files.internal("assets/skin/gdx-skins-master/gdx-holo/skin/uiskin.json"));
		
		//battleScene.components();
		inventory = new InventoryScene(mySkin,font,player,new Mons());
		
		battleScene = new BattleScene(mySkin, font,player,inventory);
		menu = new MenuScene(font,mySkin,battleScene,inventory,player);
		
		
		menu.startcomponents();
	
		
		
	}

	@Override
	public void render () {
		ScreenUtils.clear((float)0.5,0,(float)0.5,1);
		
		//System.out.println(state);
		switch(state) {
		case 1:
			batch.begin();
			menu.loadBatch(batch);
			batch.end();
			menu.draw();
			state = menu.getState();
			menu.resetState();
			
			//gameLoop();
			break;
		case 2:
			batch.begin();
			battleScene.loadSprites(batch);
			batch.end();
			battleScene.draw(menu);
			state = battleScene.getState();
			battleScene.resetState();
			break;
		
		case 3:
			batch.begin();
			inventory.loadBatch(batch);
			batch.end();
			inventory.draw();
			state = inventory.getState();
			inventory.resetState();
			break;
			
		case 4:
			menu.partycomponents(battleScene.getBattle().getEnemyMon());
			menu.setnewMonImage(true);
			state = 1;
			break;
		case 5:
			menu.components();
			state = 1;
			break;
		}
		
		
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
		font.dispose();
		//gameStage.dispose();
	}
}

