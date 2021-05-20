package week_9.bst.zhengYongImplementation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Set;
import java.util.TreeSet;

import fast_input_classes.FastReader;

/* Credits to Zheng Yong sama
 * 
 * Idea is depth of inserted node = depth of node directly on top + 1
 * 
 * Since it's not an AVL, height of node once inserted won't change. We can store the heights in an array for O(1) access.
 * 
 *  One way to determine the height would be to check the position of the predecessor and successor! So like:
	If node does not have a predecessor/successor, set its height = 0 (why?Cos it's the root)
	If node has a predecessor or a successor (but not both), set its height to (pred/succ’s height + 1)
	If node has both a predecessor and a successor, set its height to _____?____
 */
public class BST 
{
	public static void main(String[] args) throws IOException
	{
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int numOperations = scanner.nextInt();
		
		int[] depthOfInsertedVal = new int[numOperations + 1];
		TreeSet<Integer> tree = new TreeSet<>();
		
		int C = 0;
		Integer insertedVal, successor, predeccessor;
		for(int i = 1; i <= numOperations; i++)
		{
			insertedVal = scanner.nextInt();
			
			tree.add(insertedVal);
			successor = tree.higher(insertedVal);
			predeccessor = tree.lower(insertedVal);
			
			//Must be root
			if( successor == null && predeccessor == null)
			{
				depthOfInsertedVal[insertedVal] = 0;
			}
			else if( successor != null && predeccessor != null)
			{
				depthOfInsertedVal[insertedVal] = Math.max(depthOfInsertedVal[predeccessor], depthOfInsertedVal[successor]) + 1;	
			}
			else if(successor != null)
			{
				depthOfInsertedVal[insertedVal] = depthOfInsertedVal[successor] + 1;
			}
			else
			{
				depthOfInsertedVal[insertedVal] = depthOfInsertedVal[predeccessor] + 1;
			}
			
			C += depthOfInsertedVal[insertedVal];
			
			sb.append(C+"\n");
		}
		
		out.write(sb.toString());
		out.flush();
		
	}
}
