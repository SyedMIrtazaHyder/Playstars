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
import java.util.*;

public class Spinner { //trying to make a custom widget...
	private Label objName;
	private Label objQuantity;
	private int maxQuantity;
	private Skin skin;
	private TextButton increment;
	private TextButton decrement;
	private int price;
	public static int total; //initally at 0
	public static Label totalPrice;
	//private int maxQuantity = 5;
	
	
	public Spinner(String name, int quantity, Skin skin, int price) {
		totalPrice = new Label("Total Price: 0", skin);
		this.skin = skin;
		objName = new Label(name, skin);
		objQuantity = new Label("0", skin);
		increment = new TextButton("=>", skin);
		decrement = new TextButton("<=", skin);	
		maxQuantity = quantity;
		this.price = price;
	}
	
//	public Spinner(String name, int quantity, Skin skin, int maxQuantity)
//	{
//		this.skin = skin;
//		objName = new Label(name, skin);
//		objQuantity = new Label(Integer.toString(quantity), skin);
//		increment = new TextButton("=>", skin);
//		decrement = new TextButton("<=", skin);	
//		this.maxQuantity = maxQuantity;
//		
//	}

	public Label getObjName() {
		return objName;
	}

	public void setObjName(Label objName) {
		this.objName = objName;
	}

	public Label getObjQuantity() {
		return objQuantity;
	}

	public void setObjQuantity(Label objQuantity) {
		this.objQuantity = objQuantity;
	}
	
	public void getTotal() {
		//System.out.println(total);
		totalPrice.setText("Total Price: " + total);
	}
	
	public void display(Table table, float totalWidth, int btnSize, int valSize) {
		table.add(objName).width(totalWidth/4);
    	table.add(decrement).width(btnSize);
    	table.add(objQuantity).width(valSize); //add a counter here
    	table.add(increment).width(btnSize);
    	table.row();
    	
    	increment.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				//System.out.println("Button 1 clicked");
				int quan = Integer.parseInt(objQuantity.getText().toString());
				if (quan < maxQuantity) {
					objQuantity.setText(quan+1);
					calcTotal(price);
				}
			}
		});
    	
    	decrement.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent input, float x, float y) {
				int quan = Integer.parseInt(objQuantity.getText().toString());
				if (quan > 0) {
					objQuantity.setText(quan-1);
					calcTotal(-price);
				}
			}
		});
    	
	}
	
	public void calcTotal(int price) {
		total += price;
		getTotal();
	}
	
	public void userBuys(Player User) {
		
	}
}
