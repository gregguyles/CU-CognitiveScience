package com.cogSci;

public class Reaction {
	
	private int subjectNumber;
	private int day;
	private String condition;
	private String currentTrial;
	private String previousTrial;
	private char stimulus;
	private char response;
	private int responseTime;
	
	public Reaction(int[] results){
		this.subjectNumber = results[0];
		this.day = results[1];
		this.condition = (results[2] == 1) ? "repetitions" : "alterations";
		this.currentTrial = (results[3] == 1) ? "alteration" : "repetition";
		this.previousTrial = (results[4] == 1) ? "alteration" : "repetition";
		this.stimulus = (results[5] == 8) ? 'A' : 'B';
		if (results[6] == 8) 
			this.response = 'A';
		else if (results[6] == 9)
			this.response = 'B';
		else
			this.response = 'O';
		this.responseTime = results[7];
	}
	
	public int getSubjectNumber(){
		return subjectNumber;
	}
	public int getDay(){
		return day;
	}
	public String getCondititon(){
		return condition;
	}
	public String getCurrentTrial(){
		return currentTrial;
	}
	public String getPreviousTrial(){
		return previousTrial;
	}
	public char getStimulus(){
		return stimulus;
	}
	public char getResponse(){
		return response;
	}
	public int getResponseTime(){
		return responseTime;
	}
}
