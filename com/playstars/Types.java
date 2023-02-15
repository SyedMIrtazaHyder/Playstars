package com.playstars;

import java.util.*;
import java.io.*;

public class Types implements Serializable{
	public static Dictionary<String, double[]> typings = new Hashtable<String, double[]>();
	private String name;
	//format of array: {Psychic,Dark,Fighting,Steel,Fairy}]
	

	public Types(String name) {
		
		this.name = name;
		setDict();//setting the dictionary to be referred to
	}
	
	public Types() {
		this("");
	}
	
	public void setDict() {
		double [] strengths = {0.5, 0.0, 2.0, 0.5, 1.0, 1.0};
		typings.put("Psychic",strengths);
		
		//strengths = null;
		double [] strengths1 = {2.0,0.5,0.5,1.0,0.5, 1.0};
		typings.put("Dark", strengths1);
		
		//strengths1 = null;
		double [] strengths2 = {0.5,2.0,1.0,2.0,0.5, 1.0};
		typings.put("Fighting", strengths2);
		
		//strengths2 = null;
		double [] strengths3 = {1.0,1.0,1.0,0.5,2.0, 1.0};
		typings.put("Steel", strengths3);
		
		//strengths3 = null;
		double [] strengths4 = {1.0,2.0,2.0,0.5,1.0, 1.0};
		typings.put("Fairy", strengths4);
		
		//strengths4 = null;
		double [] strengths5 = {1.0,1.0,1.0,1.0,1.0, 1.0};
		typings.put("", strengths5);
	}
	
	public double calcMultiplier(String type) {
		//get own type
		//get opponent type
		//send multiplier
		double[] mult = (double[]) typings.get(name);
		int index = 0;
	
		ArrayList<String> types = new ArrayList<String>();
		types.add("Psychic");
		types.add("Dark");
		types.add("Fighting");
		types.add("Steel");
		types.add("Fairy");
		types.add("");
		
		
		//System.out.println(name + " => " + type + "  " + mult[types.indexOf(type)]);
		return mult[types.indexOf(type)];
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
