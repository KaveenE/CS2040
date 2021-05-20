package take_home.two;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;

import fast_input_classes.FastReader;

/*
 * Will have 2 maps to simulate 2 "arrays" used in the TequeWithList program
 * These maps map indices to values
 * 
 * Need to have to pointers to the head and tail of these "arrays" which helps obtain indices.
 * Obviously, these indices are required for for get,push.
 * 
 * NOTE: These pointers point to indices that I'll be inserting at.
 * 
 * Things to know before doing:
 * Both arrays should be of same size after adding.
 * If size is odd after adding, frontArray should have the additional element.
 * Rebalancing is achieved simply by sliding the middle elements accordingly
 * 
 * Problem #1: I did not consider the case when the maps are empty and hence did not update both pointers
 * This caused me alot of problems
 * Problem #2: I didn't know how to set up my get function. I had to step thru program + copied from
 * https://github.com/y33-j3T/Coding-Problems-Archive/blob/master/teque.java
 * 
 */
public class TequeWithMap {

	private Map<Integer,Integer> frontArray;
	private int frontHead, frontTail;
	
	private Map<Integer,Integer> backArray;
	private int backHead,backTail;
	
	public TequeWithMap()
	{
		this.frontArray = new HashMap<>();
		this.backArray = new HashMap<>();
	}
	
	public void pushBack(int num)
	{
		//Only time when we change both
		//backHead and backTail
		if(backArray.isEmpty())
		{
			backHead--;
		}
		backArray.put(backTail, num);
		backTail++;
		balanceArrays();

	}
	
	public void pushFront(int num)
	{
		//Only time when we change both
		//frontHead and frontTail
		if(frontArray.isEmpty())
		{
			frontTail++;
		}
		
		frontArray.put(frontHead, num);
		frontHead--;
		balanceArrays();
	}
	
	public void pushMiddle(int num)
	{
		if(frontArray.isEmpty())
		{
			frontHead--;
		}
		
		frontArray.put(frontTail, num);
        frontTail++;
        balanceArrays();
	}
	
	public int get(int idx) 
	{
		if(idx <= frontArray.size() - 1)
		{
			return frontArray.get(idx + frontHead + 1);
		}
		else
		{
			return backArray.get(idx - frontArray.size() + backHead + 1) ;
		}
	}
	private void balanceArrays()
	{
		if(backArray.size() > frontArray.size())
		{
			frontArray.put(frontTail, backArray.get(backHead + 1) );
			backArray.remove(backHead + 1);
			
			if(backArray.isEmpty())
			{
				backTail--;
			}
			backHead++;
			
			//If prior to this insertion, it's empty.
			//I have to update both frontTail and frontHead
			if(frontArray.size() - 1 ==0)
			{
				frontHead--;
			}
			frontTail++;
		}
		else if(frontArray.size() - backArray.size() > 1)
		{
			backArray.put(backHead, frontArray.get(frontTail - 1));
			frontArray.remove(frontTail - 1);
			
			if(frontArray.isEmpty())
			{
				frontHead++;
			}
			frontTail--;
			
			//If prior to this insertion, it's empty.
			//I have to update both backTail and backHead
			if(backArray.size() - 1 ==0)
			{
				backTail++;
			}
			backHead--;
		}
	}
	public static void main(String[] args) throws IOException {
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		TequeWithMap teque = new TequeWithMap();
		Map<String, Integer> commandToNum = new HashMap<>();
		
		commandToNum.put("push_back", 0);
		commandToNum.put("push_front", 1);
		commandToNum.put("push_middle", 2);
		commandToNum.put("get", 3);
		
		int operations = scanner.nextInt();
		String[] instructions;
		String command;
		int num;

		StringBuilder sb = new StringBuilder();
		for(int i = 1;i <= operations; i++)
		{
			instructions = scanner.nextLine().split(" ");
			command = instructions[0];
			num = Integer.parseInt(instructions[1]);
			
			if(commandToNum.get(command) == 0)
			{
				teque.pushBack(num);
			}
			else if(commandToNum.get(command) == 1)
			{
				teque.pushFront(num);
			}
			else if(commandToNum.get(command) == 2)
			{
				teque.pushMiddle(num);
			}
			else
			{
				sb.append(teque.get(num)+"\n");
					
			}
		}

		out.write(sb.toString());
		out.flush();
		
	}
}
