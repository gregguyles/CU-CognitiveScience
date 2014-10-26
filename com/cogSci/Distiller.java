//File write
package com.cogSci;

import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.ListIterator;

public class Distiller {
	private FileWriter fw;
	private BufferedWriter bw;
	private FileReader fr;
	private BufferedReader br;
	String line;
	LinkedList<Reaction> dataList = new LinkedList<Reaction>();
	int stringSize = 4;
//	GetInQueue queue = new GetInQueue(stringSize);
	public Distiller()
	{
		try
		{
			fw = new FileWriter("output.txt", false);
			bw = new BufferedWriter(fw);
			
			fr = new FileReader("data.txt");
			br = new BufferedReader(fr);
		
		while ((line = br.readLine()) != null){
				String[] rawEllements = line.split("\\ ");
				int[] ellements = new int[8];
				int ellementsIndex = 0;
				for (int j = 0; j < rawEllements.length; j++){
					if (rawEllements[j].equals(""))
						;
					else{
						ellements[ellementsIndex] = Integer.parseInt(rawEllements[j]);
						ellementsIndex++;
					}
				}
				Reaction r = new Reaction(ellements);
				dataList.add(r);
		}
		
		//Create strings of size n with all times and a sum
		int start = 0, end = stringSize;
		int curDay = dataList.get(start).getDay();
		int count = 1;
		boolean error;
		int sum;
		String values;
		int[] respTimes = new int[stringSize];
		while (end < dataList.size()){
			error = false;
			sum = 0;
			values = "";
			if ( curDay != dataList.get(start).getDay() ){
				count = 1;
				curDay = dataList.get(start).getDay();
			}
			for (int i = start; i < end; i++){
				values += dataList.get(i).getResponse();
				if(dataList.get(i).getResponse()!=(dataList.get(i).getStimulus()))
					error = true;
				if (dataList.get(i).getDay() != curDay)
					error = true;
				if (dataList.get(i).getResponse() <= 89 || dataList.get(i).getResponse() >= 625)
					error = true;
				sum += respTimes[i - start] = dataList.get(i).getResponseTime();
			}
			values += ",";
			for (int i = 0; i < respTimes.length; i++){
				values += respTimes[i] + ",";
			}
			values += sum + ",";
			values += error ? "true," : "false,";
			values += count + ",";
			values += dataList.get(start).getCondititon();
			bw.write(values + "\n");
			start++;
			end++;
			count++;
		}
		
		//Export values
//		ListIterator<Reaction> itr = dataList.listIterator();
//		int curDay = dataList.getFirst().getDay();
//		int count = 1;
//		Reaction react;
//		while (itr.hasNext()){
//			String values = "";
//			react = itr.next();
//			if ( curDay != react.getDay() ){
//				count = 1;
//				curDay = react.getDay();
//			}
//			values += react.getSubjectNumber() + ",";
//			values += react.getDay() + ",";
//			values += count + ",";
//			values += react.getCondititon() + ",";
//			values += react.getCurrentTrial() + ",";
//			values += react.getPreviousTrial() + ",";
//			values += react.getStimulus() + ",";
//			values += react.getResponse() + ",";
//			values += react.getResponseTime();
//			bw.write(values + "\n");
//			count++;
//		}
		
		bw.close();	//closing buffer frees up memory
		}
		catch(FileNotFoundException fnf)
		{
			System.out.print("Error: file not found");
		}
		catch(IOException io)
		{
			System.out.print("Error: IO exception");
		}
	}
	
	
	
	public static void main(String [] args)
	{
		Distiller w1 = new Distiller();
	}

}
