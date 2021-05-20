package week_9.bst.ownImplementation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

import fast_input_classes.FastReader;

//Since the tree is not an AVL, the tree becomes unbalanced and complexity degenerates to O(N)
//Hence, I shouldn't actually create a tree and insert. Instead look at Zheng Yong's way
public class BST<T extends Comparable<T> >
{
	private BSTNode<T> root;
	private int depth;
	
	public BST()
	{
		root=null;
	}
	
	
	//Insert algo
	public int insertAnd_ReturnDepthtOfNode(T data)
	{
		depth = 0;
		root = insertRecursive(root, data);
		
		return depth;
	}
	
	//Wrapping up this private method in insert method
	private BSTNode<T> insertRecursive(BSTNode<T> currentNode,T data)
	{
		if(currentNode==null)//empty tree or empty sub-tree
		{
			currentNode=BSTNode.createTreeNode(data);
		}
		else if( data.compareTo(currentNode.getData()) < 0 )//traverse to left sub-tree if lower  value than current node
		{
			BSTNode<T> updatedLeft=insertRecursive(currentNode.getLeft(), data);
			currentNode.setLeft(updatedLeft);
			depth++;
		}
		else//traverse to right sub-tree if greater value than current node
		{
			BSTNode<T> updatedRight=insertRecursive(currentNode.getRight(), data);
			currentNode.setRight(updatedRight);
			depth++;
		}
		
		return currentNode; //We return so that the left,right nodes gets updated on the returning calls
	}
	

	
	
	public static void main(String[] args) throws Exception
	{
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int insertions = scanner.nextInt();
		int insertedVal;
		int C = 0;
		
		BST<Integer> bst = new BST<>();
		
		for(int i = 0; i <= insertions - 1;i++)
		{
			insertedVal = scanner.nextInt();

			C += bst.insertAnd_ReturnDepthtOfNode(insertedVal);
			
			sb.append(C+"\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
}



