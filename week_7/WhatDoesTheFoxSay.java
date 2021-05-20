package week_7;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import fast_input_classes.FastReader;

//I used map at first BUT realised can use set too
public class WhatDoesTheFoxSay {

	public static void main(String[] args) throws IOException {

		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		FastReader scanner = new FastReader();
		
		int cases = scanner.nextInt();
		
		String[] sounds;
		
		
		/* Map variation
		Map<String,String> soundToAnimalMap = new HashMap<>();
		
		//input variables
		String sound;
		String animal;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i <= cases - 1; i++)
		{
			sounds = scanner.nextLine().split(" ");
			animal = scanner.next();
			
			while(!animal.equals("what"))
			{
				//get rid of the word goes
				scanner.next();
				sound = scanner.next();
				
				soundToAnimalMap.put(sound,animal);
				
				animal = scanner.next();
			}
			
			//Get the words that hasn't been recorded in the map yet
			for(String someSound: sounds)
			{
				if(Objects.isNull(soundToAnimalMap.get(someSound)))
				{
					sb.append(someSound+" ");
				}
			}
			
			out.write(sb.toString());
			out.flush();
			
			sb.setLength(0);
			
			//get rid of the line with fox, nextLine doesnt work? fast reader class rabz kebabz
			scanner.next();
			scanner.next();
			scanner.next();
			scanner.next();
			
		}*/
		
		Set<String> soundsRecorded = new HashSet<>();
		
		//input variables
		String sound;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i <= cases - 1; i++)
		{
			sounds = scanner.nextLine().split(" ");
			 
			
			while(!scanner.next().equals("what"))
			{
				//get rid of the word goes
				scanner.next();
				sound = scanner.next();
				
				soundsRecorded.add(sound);

			}
			
			//Get the words that hasn't been recorded in the map yet
			for(String someSound: sounds)
			{
				if(!soundsRecorded.contains(someSound))
				{
					sb.append(someSound+" ");
				}
			}
			
			out.write(sb.toString());
			out.flush();
			
			sb.setLength(0);
			
			//get rid of the line with fox, nextLine doesnt work? fast reader class rabz kebabz
			scanner.next();
			scanner.next();
			scanner.next();
			scanner.next();
			
		}

	}

}
