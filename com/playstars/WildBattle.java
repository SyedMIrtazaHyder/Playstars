package com.playstars;

import java.io.IOException;
import java.util.*;

public class WildBattle extends Battle{
	private int catchRate;
	private Items drop;
	private static String name = "WildBattle";
	
	public WildBattle(Mons mymon, Mons enemymon) throws ClassNotFoundException, IOException {
		super(mymon, enemymon);
		//Items.getAllItems().get(new Random(10).nextInt());
		if (new Random(10).nextInt() > 9) {
			drop = Items.getAllItems().get(new Random(10).nextInt());
		}
		catchRate = enemymon.getCatchrate();
	}
	
	public boolean throwBall() {
		if (new Random().nextInt(100) > 100 - catchRate)
			return true;
		return false;
	}
	
	public String getName() {
		return name;
	}
	
	public Trainer getEnemy() {
		return null;
	}
}
