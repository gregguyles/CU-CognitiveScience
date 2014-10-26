package com.congSci;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;



public class TopicModelReparse {
	public static void main(String [] args) {
		FileWriter fw;
		BufferedWriter bw;
		FileReader fr;
		FileReader tFr;
		BufferedReader br;
		BufferedReader tBr;
		String line;
		String outLine = "Index,Scene";
		try
		{
			fw = new FileWriter("output.txt", false);
			bw = new BufferedWriter(fw);
			
			fr = new FileReader("data.txt");
			br = new BufferedReader(fr);
			
			tFr = new FileReader("topics.txt");
			tBr = new BufferedReader(tFr);
			
			while ((line = tBr.readLine()) != null){
				String[] topic = line.split("\\,");
				outLine += "," + topic[1];
				System.out.print(topic[1]);
			}
			bw.write(outLine + "\n");
			outLine = "";
			while ((line = br.readLine()) != null){
					String[] rawEllements = line.split("\\,");
					String[] ellements = new String[17];
					for (int j = 0; j < rawEllements.length-1; j = j + 2){
						if (j < 2){
							ellements[j] = rawEllements[j];
							String[] title1 = rawEllements[j+1].split("\\\\");
							String[] title2 = title1[4].split("\\.");
							ellements[j+1] = title2[0];
						}
						else{
							ellements[Integer.parseInt(rawEllements[j])+1] = rawEllements[j+1];
						}
					}
					outLine = "";
					for (int i = 0; i < ellements.length; i++){
						if (ellements[i] == null)
							ellements[i] = "0";
						outLine += ellements[i] + ",";
					}
					outLine += ellements[ellements.length - 1];
					bw.write(outLine + "\n");
			}
		bw.close();
		}catch(FileNotFoundException fnf)
		{
			System.out.print("Error: file not found");
		}
		catch(IOException io)
		{
			System.out.print("Error: IO exception");
		}
	}
			
}


