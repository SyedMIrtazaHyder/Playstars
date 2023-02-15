package com.playstars;

public class TrainerBattle extends Battle{
	private Trainer trainer;
	private static String  name = "TrainerBattle";
	
	public TrainerBattle(Mons mon1, Mons mon2, Trainer trainer) {
		super(mon1,mon2);
		this.trainer = trainer;
	}
	
	public Trainer getEnemy() {
		return trainer;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean throwBall() {
		return false;
	}
}
