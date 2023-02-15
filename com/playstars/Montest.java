package com.playstars;
import java.io.*;
import java.util.ArrayList;
public class Montest {
	
	public static void main(String [] args) throws IOException, ClassNotFoundException {
		Moves [] movestosave = new Moves[30];
		movestosave[0] = new Moves("Mindbend", 5, 100,false,new Types("Psychic"));
		movestosave[1] = new Moves("Psychosis", 10, 90,false,new Types("Psychic"));
		movestosave[2] = new Moves("Migraine", 12, 80,false,new Types("Psychic"));
		movestosave[3] = new Moves("Whiplash", 7, 500,false,new Types("Psychic"));
		movestosave[4] = new Moves("Brain Split", 14, 75,false,new Types("Psychic"));
		movestosave[5] = new Moves("Inception", 20, 70,false,new Types("Psychic"));
		
		movestosave[6] = new Moves("Right Hook", 7, 100, true, new Types("Fighting"));
		movestosave[7] = new Moves("Counter Punch", 11, 90, true, new Types("Fighting"));
		movestosave[8] = new Moves("Wild Kick", 14, 80, true, new Types("Fighting"));
		movestosave[9] = new Moves("Roundhouse Kick", 16, 75, true, new Types("Fighting"));
		movestosave[10] = new Moves("Jab", 5, 500, true, new Types("Fighting"));
		movestosave[11] = new Moves("Ultimate Blow", 22, 70, true, new Types("Fighting"));
		
		movestosave[12] = new Moves("Shade Whip", 6, 100, false, new Types("Dark"));
		movestosave[13] = new Moves("Traumatic Strike", 9, 90, true, new Types("Dark"));
		movestosave[14] = new Moves("Shadow's Rolling", 8, 500, true, new Types("Dark"));
		movestosave[15] = new Moves("Chaos Blast", 25, 50, false, new Types("Dark"));
		movestosave[16] = new Moves("Wicked Slash", 14, 80, true, new Types("Dark"));
		movestosave[17] = new Moves("Dark World", 20, 75, false, new Types("Dark"));
		
		movestosave[18] = new Moves("Deal Steel", 5, 100, true, new Types("Steel"));
		movestosave[19] = new Moves("Hard Edge", 9, 90, true, new Types("Steel"));
		movestosave[20] = new Moves("Metallic Blast", 13, 80, false, new Types("Steel"));
		movestosave[21] = new Moves("Heavy Metal", 15, 75, true, new Types("Steel"));
		movestosave[22] = new Moves("Catch Wire", 8, 500, false, new Types("Steel"));
		movestosave[23] = new Moves("Steelheart", 18, 70, true, new Types("Steel"));
		
		movestosave[24] =  new Moves("Glittery Scratch", 6, 100, false, new Types("Fairy"));
		movestosave[25] =  new Moves("Rainbow Dash", 11, 90, true, new Types("Fairy"));
		movestosave[26] =  new Moves("Sparkle Burst", 14, 80, false, new Types("Fairy"));
		movestosave[27] =  new Moves("Charming Assault", 9, 500, true, new Types("Fairy"));
		movestosave[28] =  new Moves("Cuddly Death", 17, 75, false, new Types("Fairy"));
		movestosave[29] =  new Moves("Flower Doom", 23, 60, false, new Types("Fairy"));
		
		Moves.saveMoves(movestosave);
		Moves move = Moves.getMoves(15);
		System.out.println("Name: " + move.getName() + "\nAtk: " + move.getDamage());
		
		
		
		Mons [] monlist = new Mons[23];
		//Types [] montypes= {new Types("Psychic"), new Types()};
		monlist[0] = new Mons("Dumby",new Types[]{new Types("Psychic"), new Types()},15,5,7,7,7,2,1,22,new Moves[] {movestosave[0],movestosave[1],new Moves(),new Moves(),new Moves()},0,true);
		monlist[1] = new Mons("Dumberin", new Types[]{new Types("Psychic"), new Types()},30,7,10,12,12,5,3,17,new Moves[] {movestosave[0],movestosave[1],movestosave[2],movestosave[12],new Moves()},1,true);
		monlist[2] = new Mons("Dumbestan", new Types[]{new Types("Psychic"), new Types()},45,9,14,15,15,8,7,14,new Moves[] {movestosave[0],movestosave[1],movestosave[2],movestosave[12],movestosave[15]},2,false);
		

		//montypes[0] = new Types("Steel");
		monlist[3] = new Mons("Steevil",new Types[]{new Types("Steel"), new Types()},14,7,6,9,4,3,1,70,new Moves[] {movestosave[18],movestosave[6],new Moves(),new Moves(),new Moves()},3,true);
		//montypes[1] = new Types("Dark");
		monlist[4] = new Mons("Goldimp",new Types[]{new Types("Steel"), new Types("Dark")},33,9,9,14,10,7,4,60,new Moves[] {movestosave[18],movestosave[19],movestosave[6],movestosave[16],movestosave[14]},4,false);
		
//		montypes[0] = new Types("Fairy");
		//montypes[1] = new Types("Psychic");
		monlist[5] = new Mons("Hatrick", new Types[]{new Types("Fairy"), new Types("Psychic")},27,10,10,10,10,10,1,50,new Moves[] {movestosave[5],movestosave[4],movestosave[13],movestosave[25],new Moves()},5,false);
//		
//		montypes[0] = new Types("Fighting");
//		montypes[1] = new Types();
		monlist[6] = new Mons("Knuckameleon",new Types[]{new Types("Fighting"), new Types()},31,14,6,8,8,11,1,50,new Moves[] {movestosave[7],movestosave[8],movestosave[9],movestosave[17],new Moves()},6,false);
//		
//		montypes[1] = new Types("Steel");
		monlist[7] = new Mons("Devestroid",new Types[]{new Types("Steel"), new Types()},40,14,14,14,14,14,1,10,new Moves[] {movestosave[9],movestosave[10],movestosave[11],movestosave[22],movestosave[23]},7,false);
//		
//		montypes[0] = new Types("Dark");
//		montypes[1] = new Types();
		monlist[8] = new Mons("Vuddle",new Types[]{new Types("Dark"), new Types()},16,7,10,6,10,5,1,50,new Moves[] {movestosave[12],movestosave[13],movestosave[18],new Moves(), new Moves()},8,true);
		monlist[9] = new Mons("Voidrain",new Types[]{new Types("Dark"), new Types()},35,9,16,8,18,7,4,25,new Moves[] {movestosave[12],movestosave[14],movestosave[18],movestosave[16],movestosave[17]},9,false);
//		
//		montypes[0] = new Types("Fairy");
		monlist[10] = new Mons("Scubug",new Types[]{new Types("Fairy"), new Types()},7,6,10,7,9,7,1,80,new Moves[] {movestosave[24],movestosave[25],new Moves(), new Moves(), new Moves()},10,true);
		monlist[11] = new Mons("Dripupa",new Types[]{new Types("Fairy"), new Types()},15,7,12,8,10,8,3,70,new Moves[] {movestosave[24],movestosave[25],movestosave[26],new Moves(),new Moves()},11,true);
		monlist[12] = new Mons("Slimoth",new Types[]{new Types("Fairy"), new Types("Dark")},28,8,14,8,12,11,5,30,new Moves[] {movestosave[10],movestosave[25],movestosave[28],movestosave[26],movestosave[27]},12,false);
//		
		monlist[13] = new Mons("Archerub",new Types[]{new Types("Fairy"), new Types()},16,7,7,5,5,4,1,80,new Moves[] {movestosave[12],movestosave[24],new Moves(), new Moves(), new Moves()},13,true);
		monlist[14] = new Mons("Hayfae",new Types[]{new Types("Fairy"), new Types()},29,10,10,6,7,6,3,30,new Moves[] {movestosave[25],movestosave[12],movestosave[13],movestosave[29],new Moves()},14,true);
		monlist[15] = new Mons("Swardrow",new Types[]{new Types("Fairy"), new Types("Steel")},43,14,15,9,11,8,7,10,new Moves[] {movestosave[26],movestosave[28],movestosave[14],movestosave[15],movestosave[29]},15,false);
//		
//		montypes[0] = new Types("Dark");
		monlist[16] = new Mons("Megadon",new Types[]{new Types("Dark"), new Types("Fighting")},50,14,6,15,7,9,1,20,new Moves[] {movestosave[15],movestosave[21],movestosave[8],movestosave[9],movestosave[17]},16,false);
//		
		//montypes[0] = new Types("Psychic");
		monlist[17] = new Mons("Magicat",new Types[]{new Types("Psychic"), new Types()},12,5,7,4,8,5,1,60,new Moves[] {movestosave[0],movestosave[2],new Moves(), new Moves(), new Moves()},17,true);
		monlist[18] = new Mons("Divineko",new Types[]{new Types("Psychic"), new Types()},27,7,11,6,12,8,4,20,new Moves[] {movestosave[2],movestosave[1],movestosave[25],new Moves(), new Moves()},18,true);
		//montypes[1] = new Types("Fairy");
		monlist[19] = new Mons("Thaumatiger", new Types[]{new Types("Fairy"), new Types("Psychic")},39,7,16,11,15,11,8,5,new Moves[] {movestosave[4],movestosave[3],movestosave[13],movestosave[26],movestosave[28]},19,false);
//		
//		montypes[0] = new Types("Fighting");
//		montypes[1] = new Types();
		monlist[20] = new Mons("Greekid",new Types[]{new Types("Fighting"), new Types()},18,7,2,7,4,3,1,60,new Moves[] {movestosave[6],movestosave[7],movestosave[19],new Moves(), new Moves()},20,true);
		monlist[21] = new Mons("Sparteen",new Types[]{new Types("Fighting"), new Types()},34,12,3,10,6,6,3,20,new Moves[] {movestosave[7],movestosave[7],movestosave[19],movestosave[10],new Moves()},21,true);
		monlist[22] = new Mons("Olympartan",new Types[]{new Types("Fighting"), new Types("Steel")},50,17,4,13,7,9,6,5,new Moves[] {movestosave[8],movestosave[9],movestosave[10],movestosave[11],movestosave[19]},22,false);
//		
		
//		for (int i = 0; i < 23; i++) {
//			System.out.println(monlist[i].getMontypes(0).getName());
//		}
		
		Mons.writeMons(monlist);
		
		Mons mon = Mons.getmons(4);
		System.out.println("Name: " + mon.getName() + "\nAttack: " + mon.getAtk() + "\nIndex: " + mon.getIndex());
		
		ArrayList<Items> itemsForShop = new ArrayList<Items>();
		itemsForShop.add(new Items("Potion", 20, Effect.HEAL, 5, new int[]{0, 0, 0, 0, 0, 0}));
		itemsForShop.add(new Items("Ultra Potion", 50, Effect.HEAL, 20, new int[]{0, 0, 0, 0, 0, 0}));
		itemsForShop.add( new Items("Master Potion", 120, Effect.HEAL, 45, new int[]{0, 0, 0, 0, 0, 0}));
		itemsForShop.add( new Items("Exp Candy", 300, Effect.LEVEL, 0, new int[]{0, 0, 0, 0, 0, 0}));
		itemsForShop.add( new Items("Atk Buff", 150, Effect.BUFF, 0, new int[]{0, 3, 0, 0, 0, 0}));
		itemsForShop.add( new Items("Hp Buff", 150, Effect.BUFF, 0, new int[]{3, 0, 0, 0, 0, 0}));
		itemsForShop.add( new Items("Def Buff", 150, Effect.BUFF, 0, new int[]{0, 0, 3, 0, 0, 0}));
		itemsForShop.add( new Items("SpAtk Buff", 150, Effect.BUFF, 0, new int[]{0, 0, 0, 3, 0, 0}));
		itemsForShop.add( new Items("SpDef Buff", 150, Effect.BUFF, 0, new int[]{0, 0, 0, 0, 3, 0}));
		itemsForShop.add( new Items("Spd Buff", 150, Effect.BUFF, 0, new int[]{0, 0, 0, 0, 0, 3}));
		
		Items.writeFile(itemsForShop);
		
		Player player = new Player("Lil Subatomic Particle", monlist);
		player.save();
		
		
		Player player2 = Player.load();
		System.out.println(player2.getname());

		
		ArrayList<Items> all = Items.getAllItems();
		for (Items stuff: all)
			System.out.printf("\nItem name: %s\nItem Price: %d\nEffect: %s\n",
					stuff.getName(), stuff.getQuantity(),
					stuff.getEffect());
		
		player.setFullInventory(itemsForShop);
		System.out.println(player.getMons(0).getMontypes(0).getName());
		
//		Types cT = new Types("Psychic");
//		double[] psy = cT.getTyping();
//		for (double i: psy)
//			System.out.println("Psychic " + i);
			
		
	}
}
