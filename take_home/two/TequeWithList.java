package take_home.two;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fast_input_classes.FastReader;

/*
 * Disclaimer:I only knew that balance factor between the 2 arrays is ATMOST 1
 * by looking at https://github.com/terrytay/Competitive-Programming/blob/master/teque.cpp
 * 
 * When balance factor is broken, we slide the middle elements accordingly
 * Figured out implementation of get myself through getting some random example.
 * 
 * 
 * Implementation of pushMiddle was challenging, the implementation idea was copied from
 * the site mentioned above
 * 
 * 
 * Due to list's O(n) remove/insert at start, this was too slow
 */
public class TequeWithList {

	
	private List<Integer> frontArray;
	private List<Integer> backArray;
	
	public TequeWithList() {
		frontArray = new ArrayList<>(10*10*10);
		backArray = new ArrayList<>(10*10*10);

	}
	
	public void pushBack(int num)
	{
		backArray.add(num);
		balanceArrays();

	}
	
	public void pushFront(int num)
	{
		frontArray.add(0,num);
		balanceArrays();
	}
	
	public void pushMiddle(int num)
	{
		if(backArray.size() > frontArray.size())
		{
			frontArray.add(backArray.remove(0));
		}
		
		backArray.add(0,num);
	}
	
	
	public int get(int idx)
	{
		if(idx <= frontArray.size() - 1)
		{
			return frontArray.get(idx);
		}
		else
		{
			return backArray.get(idx - frontArray.size());
		}
	}
	
	
	private void balanceArrays()
	{
		if(backArray.size() - frontArray.size() > 1)
		{
			frontArray.add(backArray.remove(0));
		}
		else if(frontArray.size() - backArray.size() > 1)
		{
			backArray.add(0,frontArray.remove(frontArray.size()-1));
		}
	}
	public static void main(String[] args) throws IOException {
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		TequeWithList teque = new TequeWithList();
		Map<String, Integer> commandToNum = new HashMap<>();
		
		commandToNum.put("push_back", 0);
		commandToNum.put("push_front", 1);
		commandToNum.put("push_middle", 2);
		commandToNum.put("get", 3);
		
		int operations = scanner.nextInt();
		String[] instructions;
		String command;
		int num;

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
				out.write(teque.get(num)+"\n");
				out.flush();	
			}
		}

	
		
	}

}
