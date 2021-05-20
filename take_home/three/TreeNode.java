package take_home.three;


public class TreeNode<T> 
{
	private T data;
	private TreeNode<T> left;
	private TreeNode<T> right;
	private int height;//augmentation from bst to avl
	private int size; //Augmentation for rank method

	
	
	private TreeNode(T data)
	{
		setData(data);
		
		//height and subtree size of node when you insert is 0 mah
		height=0;
		size=0;
		
	}
	
	//Factory methods
	public static <T extends Comparable<T> > TreeNode<T> createTreeNode(T data)
	{
		return new TreeNode<T>(data);
	}
	
	
	
	//Some setters are private because we want them only for the sake of encapsulation to reap benefit of modularizing
	public T getData() 
	{
		return data;
	}
	public void setData(T data) 
	{
		this.data = data;
	}
	
	//Left, right pointer stuff
	public TreeNode<T> getLeft() 
	{
		return left;
	}
	public void setLeft(TreeNode<T> left) 
	{
		this.left = left;
	}
	
	
	public TreeNode<T> getRight() 
	{
		return right;
	}
	public void setRight(TreeNode<T> right) 
	{
		this.right = right;
	}
	
	//Height stuff
	public int getHeight()
	{
		return height;
	}
	
	public void updateHeight()
	{
		int height_LeftTree= (left==null)? -1 : left.getHeight() ;
		int height_RightTree= (right==null)? -1 : right.getHeight() ;
		
		height=Math.max(height_RightTree, height_LeftTree) + 1;
	}
	
	public int getBalance()
	{
		int height_LeftTree= (left==null)? -1 : left.getHeight() ;
		int height_RightTree= (right==null)? -1 : right.getHeight() ;
		
		return height_LeftTree - height_RightTree;
	}
		
	//Subtree stuff
	public int getSize() {
		return size;
	}
	
	public void updateSize() {
		int size_LeftTree= (left==null)? 0 : left.getSize() ;
		int size_RightTree= (right==null)? 0 : right.getSize() ; 
		
		size = size_LeftTree + size_RightTree + 1;
	}
	
}

