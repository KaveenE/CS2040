package week_8;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fast_input_classes.FastReader;

public class ZipfSong {

	public static void main(String[] args) throws IOException 
	{
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int albumLength = scanner.nextInt();
		int songsToPrint = scanner.nextInt();
		
		//Can use list,sort and obtain best x songs
		//Or use pq and extract x times.
		//Former should be faster
		
		List<Song> album = new ArrayList<>();
		
		for(int i = 1; i <= albumLength; i++)
		{
			//Idg why quality = frequency * postion of song lol. Saw in the internet
			album.add(new Song(scanner.nextLong()*i, scanner.next()));
			
		}
		
		Collections.sort(album, Collections.reverseOrder());
		
		for(int i = 0; i <= songsToPrint - 1; i++)
		{
			out.write(album.get(i).title+"\n");
		}

		out.flush();
	}
	
	public static class Song implements Comparable<Song>{
		
		int idx;
		long quality;
		String title;
		
		public Song(long quality, String title)
		{
			this.quality = quality;
			this.title = title;
		}
		
		@Override
		public int compareTo(Song o) {
			
			if(quality != o.quality)
				return Long.compare(quality, o.quality);
			else
				return idx -  o.idx;
		}
	}

}
