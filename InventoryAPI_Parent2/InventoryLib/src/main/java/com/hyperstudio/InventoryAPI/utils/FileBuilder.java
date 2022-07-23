package com.hyperstudio.InventoryAPI.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileBuilder {
	
	public static void createFile(File file, String text) {
		if(file.exists()) return;
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(text);
			writer.flush();
			writer.close();
			return;
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void writeFile(File file, String text) {
		if(file.exists()) file.delete();
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(text);
			writer.flush();
			writer.close();
			return;
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String readFile(File file) {
		if(file.exists()) {
			try {
				String result = "";
				BufferedReader reader = new BufferedReader(new FileReader(file));
				String line;
				while((line = reader.readLine()) != null) {
					if(result.equals("")) {
						result = line;
						continue;
					}
					else {
						result = result + "\n" + line;
						continue;
					}
				}
				reader.close();
				return result;
				
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
