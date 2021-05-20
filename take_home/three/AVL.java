package take_home.three;

import java.io.BufferedWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;

import fast_input_classes.FastReader;

/* Some notes:
 * -My AVL implementation is generic. This class was meant for my own use since I created this couple months ago
 *  so it might not look CS2040ish.
 * -Supports insert, search ,rank as question's prerequisites + rebalancing
 * -Additional operations it supports: search, traversals, findMax, findHeight!
 */
public class AVL<T extends Comparable<T> >
{
	private TreeNode<T> root;
	
	public AVL()
	{
		root=null;
	}
	
	
	//Insert algo
	public void insert(T data)
	{
		root=insertRecursive(root,data);
	}
	
	//Wrapping up this private method in insert method
	private TreeNode<T> insertRecursive(TreeNode<T> currentNode,T data)
	{
		if(currentNode==null)//empty tree or empty sub-tree
		{
			currentNode=TreeNode.createTreeNode(data);
		}
		else if( data.compareTo(currentNode.getData())<=0 )//traverse to left sub-tree if lower or equal value than current node
		{
			TreeNode<T> updatedLeft=insertRecursive(currentNode.getLeft(), data);
			currentNode.setLeft(updatedLeft);
			
		}
		else//traverse to right sub-tree if greater value than current node
		{
			TreeNode<T> updatedRight=insertRecursive(currentNode.getRight(), data);
			currentNode.setRight(updatedRight);
			
		}
		
		//Must update height before rebalancing!
		currentNode.updateHeight();
		currentNode.updateSize();
		
		currentNode = rebalance(currentNode);
		
		return currentNode; //We return so that the left,right nodes gets updated on the returning calls
	}
	
	
	//Search algo
	public boolean search(T data)
	{
		return searchRecursive(root,data);
	}
	
	//Wrapping up this private method in search method
	private boolean searchRecursive(TreeNode<T> currentNode,T data)
	{
		if(currentNode==null)//empty tree or empty sub-tree, we have reached the end, there is no such value
		{
			return false;
		}
		else if(data.compareTo(currentNode.getData())==0)
		{
			return true;
		}
		else if( data.compareTo(currentNode.getData())<0)//traverse to left sub-tree if lower or equal value than current node
		{
			return searchRecursive(currentNode.getLeft(), data);
		}
		else//traverse to right sub-tree if greater value than current node
		{
			return searchRecursive(currentNode.getRight(), data);
		}
		
		
	}
	
	//Delete algo
	public void delete(T data)
	{
		root=deleteHelper(root,data);
	}

	private TreeNode<T> deleteHelper(TreeNode<T> currentNode,T data)
	{
		if(currentNode==null)//empty subtree or tree
		{
			System.out.println("No such data found-->No deletion of value occurs");
			return currentNode;
		}
		else if( data.compareTo(currentNode.getData())<0)//traverse to left sub-tree if lower or equal value than current node
		{
			TreeNode<T> updatedLeft=deleteHelper(currentNode.getLeft(), data);
			currentNode.setLeft(updatedLeft);
		}
		else if(data.compareTo(currentNode.getData())>0)//traverse to right sub-tree if greater value than current node
		{
			TreeNode<T> updatedRight=deleteHelper(currentNode.getRight(), data);
			currentNode.setRight(updatedRight);
		}
		else//We found the data, logic for the different cases is here
		{
			if(currentNode.getLeft()==null && currentNode.getRight()==null)//leaf node
			{
				return null;
			}
			else if(currentNode.getLeft()==null) //only has a right child
			{
				return currentNode.getRight();
			}
			else if(currentNode.getRight()==null)//only has a left child
			{
				return currentNode.getLeft();
			}
			else
			{
				T copiedData=findMax_recurisve( currentNode.getLeft() );//Find max in left sub-tree
				currentNode.setData(copiedData); //copy data into to-be-deleted node
				TreeNode<T> updatedLeft=deleteHelper(currentNode.getLeft(),copiedData);//Delete duplicate value found in left sub-tree
				currentNode.setLeft(updatedLeft);
			}
		}

		//Must update height before rebalancing!
		currentNode.updateHeight();
		currentNode.updateSize();

		currentNode = rebalance(currentNode);

		return currentNode; //We return so that the left,right nodes gets updated on the returning calls

	}

	//Rank algo
	public int rank(T key) {
		return rankRec(root, key);
	}
	
	private int rankRec(TreeNode<T> root,  T key) {
		
		//Key isn't present
		if(root == null)
		{
			return -1;
		}
		//Rank is equal to num of values smaller than key + 1
		else if(root.getData().compareTo(key) == 0) {
			return getSize(root.getLeft()) + 1;
		}
		//No smaller values to add. Key is in left subtree, traverse left.
		else if(root.getData().compareTo(key) > 0)
		{
			return rankRec(root.getLeft(), key);
		}
		else
		{
			return rankRec(root.getRight(), key) + getSize(root.getLeft()) + 1;
		}
	}
	
	//Helper method just to detect the annoying nulls
	private int getSize(TreeNode<T> node) {
		return node == null ? 0 : node.getSize(); 
	}
	
	
	//Rebalancing related
	private TreeNode<T> rebalance(TreeNode<T> currentNode)
	{
		
		
		//LL case
		if( currentNode.getBalance()==2 && isBetween(0, 1, currentNode.getLeft()) )
 		{
			currentNode=rightRotate(currentNode);
 		}
		
		//LR case
		if( currentNode.getBalance()==2 && isBetween(-1,-1, currentNode.getLeft()) )
		{
			currentNode.setLeft( leftRotate(currentNode.getLeft()) );
			currentNode=rightRotate(currentNode);
		}
		
		//RR
		if( currentNode.getBalance()==-2 && isBetween(-1, 0, currentNode.getRight()) )
		{
			currentNode=leftRotate(currentNode);
		}
		
		//RL
		if( currentNode.getBalance()==-2 && isBetween(1, 1, currentNode.getRight()) )
		{
			currentNode.setRight( rightRotate(currentNode.getRight()) );
			currentNode=leftRotate(currentNode);
		}
		
		return currentNode;
	}
	
	//Helper for rebalancing
	private boolean isBetween(int lower, int higher, TreeNode<T> node) {
		return node.getBalance() <= higher && node.getBalance() >= lower;
	}
	
	
	//Rotations, necessary for balancing
	//Required in methods that modifies tree ie insertion/deletion
	private TreeNode<T> leftRotate(TreeNode<T> current)
	{
		TreeNode<T> toBeParent=current.getRight();

		current.setRight(toBeParent.getLeft());
		toBeParent.setLeft(current);

		//Update height whose subtrees have changed
		//Look up notes, must update in this order
		current.updateHeight();
		toBeParent.updateHeight();
		
		current.updateSize();
		toBeParent.updateSize();

		//Now this is the parent of the node we passed in 
		return toBeParent;
	}

	private TreeNode<T> rightRotate(TreeNode<T> current)
	{
		TreeNode<T> toBeParent=current.getLeft();

		current.setLeft(toBeParent.getRight());
		toBeParent.setRight(current);

		//Update height whose subtrees have changed
		//Look up notes, must update in this order
		current.updateHeight();
		toBeParent.updateHeight();
		
		current.updateSize();
		toBeParent.updateSize();

		//Now this is the parent of the node we passed in
		return toBeParent;
	}
	
	
	//Extra operations: findMax(),findHeight(), preorder and inorder traversals
	
	//Finding max
	public T findMax()
	{
		return findMax_recurisve(root);
	}
	
	private T findMax_recurisve(TreeNode<T> current)
	{
		if(current==null)
		{	
			System.out.println("BST empty");
			return null;
		}
		else
		{
			while(current.getRight()!=null)//while there is still is right subtree
			{
				current=current.getRight();
			}

			return current.getData();
		}
	}
	
	//Finding height
	public int findHeight()
	{
		return findHeightHelper(root);
	}
	
	private int findHeightHelper(TreeNode<T> current)
	{
		if(current==null)
			return -1;
		else
		{
			int leftHeight=findHeightHelper( current.getLeft() );
			int rightHeight=findHeightHelper( current.getRight() );
			
			return Math.max(leftHeight, rightHeight)+1;
		}
	}
	
	
	//Traversals
	public void preorder()
	{
		preorderRecursive(root);
		System.out.println();
	}
	
	private  void preorderRecursive(TreeNode<T> tempRoot)
	{
		if(tempRoot==null)//empty tree or subtree
			return;
		else
		{
			System.out.print(tempRoot.getData());
			preorderRecursive(tempRoot.getLeft());
			preorderRecursive(tempRoot.getRight());
		}
	}
	
	public void inorder()
	{
		preorderRecursive(root);
		System.out.println();
	}
	
	private  void inorderRecursive(TreeNode<T> tempRoot)
	{
		if(tempRoot==null)//empty tree or subtree
			return;
		else
		{	
			preorderRecursive(tempRoot.getLeft());
			System.out.print(tempRoot.getData());
			preorderRecursive(tempRoot.getRight());
		}
	}
	
	
	
	public static void main(String[] a) throws IOException
	{
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		FastReader scanner = new FastReader();
		StringBuilder sb = new StringBuilder();
		
		AVL<Team> tree=new AVL<>();
		
		int numTeams = scanner.nextInt();
		int numEvents = scanner.nextInt();
		
		Team[] teams = new Team[numTeams + 1];
		
		//Initialize teams with index
		for(int i = 1; i <= teams.length - 1; i++) {
			teams[i] = new Team(i);
			tree.insert(teams[i]);
		}
		
		int teamID;
		int penaltyIncurred;
		for(int events = 1; events <= numEvents; events++) {
			teamID = scanner.nextInt();
			penaltyIncurred = scanner.nextInt();
			
			tree.delete(teams[teamID]);
			
			//update team
			teams[teamID].incrementCorrect();
			teams[teamID].updatePenalties(penaltyIncurred);
			
			tree.insert(teams[teamID]);
			
			
			sb.append((numTeams + 1) - tree.rank(teams[1]) + "\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
}
