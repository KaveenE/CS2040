package week_8;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

import fast_input_classes.FastReader;

public class GuessTheDataStructure {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		Deque<Integer> queue = new ArrayDeque<>() , stack = new ArrayDeque<>();
		Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
		
		int operations;
		int i;
		
		int command;
		int val;
		
		boolean isStack,isQueue,isPQ;
		
		
		operations = scanner.nextInt();
		
		while(operations != -1)
		{
			isStack = isQueue = isPQ = true;
			
			for(i =0 ; i<=operations - 1;i++)
			{
				command = scanner.nextInt();
				val = scanner.nextInt();
				
				if(command == 1)
				{
					addVal(stack, queue, pq, val);
				}
				else
				{
					if(stack.isEmpty())
						isStack = false;
					else if(val != stack.removeLast())
						isStack = false;
					
					if(queue.isEmpty())
						isQueue = false;
					else if(val != queue.removeFirst())
						isQueue = false;
					
					if(pq.isEmpty())
						isPQ = false;
					else if(val != pq.remove())
						isPQ = false;
				}
			}
			
			if(isStack == false && isQueue == false && isPQ == false)
				out.write("impossible\n");
			else if(isStack == true && isQueue == false && isPQ == false)
				out.write("stack\n");
			else if(isStack == false && isQueue == true && isPQ == false)
				out.write("queue\n");
			else if(isStack == false && isQueue == false && isPQ == true)
				out.write("priority queue\n");
			else
				out.write("not sure\n");
			
			reset(stack, queue, pq);
			
			operations = scanner.nextInt();
		}
		
		out.flush();
	}
	
	public static void addVal(Deque<Integer> stack, Deque<Integer> queue,Queue<Integer> pq, int val)
	{
		stack.addLast(val);
		queue.addLast(val);
		pq.add(val);
	}
	
	public static void reset(Deque<Integer> stack, Deque<Integer> queue,Queue<Integer> pq)
	{
		stack.clear();;
		queue.clear();;
		pq.clear();;
	}

}
