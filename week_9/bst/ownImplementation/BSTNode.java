package week_9.bst.ownImplementation;


public class BSTNode<T> 
{
	private T data;
	private BSTNode<T> left;
	private BSTNode<T> right;
	
	private BSTNode(T data)
	{
		setData(data);	
	}
	
	//Factory methods
	public static <T extends Comparable<T> > BSTNode<T> createTreeNode(T data)
	{
		return new BSTNode<T>(data);
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
	
	
	public BSTNode<T> getLeft() 
	{
		return left;
	}
	public void setLeft(BSTNode<T> left) 
	{
		this.left = left;
	}
	
	
	public BSTNode<T> getRight() 
	{
		return right;
	}
	public void setRight(BSTNode<T> right) 
	{
		this.right = right;
	}
	
}
