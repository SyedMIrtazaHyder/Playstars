package com.playstars;

import java.util.Random;
public abstract class Battle {
	protected Mons [] battlemons = new Mons[2];
	private static Random hit = new Random();
	
	
	public Battle(Mons mymon, Mons enemymon) {
		this.battlemons[0] = mymon;
		this.battlemons[1] = enemymon;
	}
	
	
	public void givedamage(Moves attack, int index) { //index is of mon that is being damaged
		//Receiver is a boolean which decides which Mon will take the damage
		float damage = 1;
		
		if (battlemons[index].getTemphp() <= 0 || battlemons[index] == null)
			return;
		
		
		if (hit.nextInt(101) > attack.getAccuracy())
		{
			damage *= 0;
		}
		
		for (int i = 0; i < 2; i++)
			damage *= attack.getTyping().calcMultiplier(this.getMons(index).getMontypes(i).getName());
		
		if (index == 1) {
			if (attack.isPhy() == true)
				damage *= this.getMons(0).getAtk()*attack.getDamage()*(25-this.getMons(1).getDef())/150;
			else
				damage *= this.getMons(0).getSatk()*attack.getDamage()*(25-this.getMons(1).getSdef())/150;
			
			this.getMons(1).setTemphp(this.getMons(1).getTemphp()-(int)damage);
		}
		
		else {
			if (attack.isPhy() == true)
				damage *= this.getMons(1).getAtk()*attack.getDamage()*(25-this.getMons(0).getDef())/150;
			else
				damage *= this.getMons(1).getSatk()*attack.getDamage()*(25-this.getMons(0).getSdef())/150;
				
			this.getMons(0).setTemphp(this.getMons(0).getTemphp()-(int)damage);
		}
		
		//System.out.println("Damage: " + (int)damage + "\nName: " + battlemons[index].getName());
	}
	
	public void setMons(int index, Mons newmon) {
		this.battlemons[index] = newmon;
	}
	
	public Mons getMons(int index) {
		return this.battlemons[index];
	}
	
	public abstract Trainer getEnemy();
	
	public abstract boolean throwBall();
	
	public abstract String getName();
	
	public Mons getEnemyMon() {
		return battlemons[1];
	}
	
	public Mons getMyMon() {
		return battlemons[0];
	}
	
	

}
