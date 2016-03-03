package com.datastructure;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class BTNode{
	int data;
	BTNode left;
	BTNode right;
}
@SuppressWarnings("all")
public class BinaryTree {

	/**
	 * @param args
	 */

	private BTNode root;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int a[] = new int[]{2,4,1,5,7,4,9};
		BinaryTree bt = new BinaryTree();
		
		for(int i : a){
			//add(i);
			bt.addRecursive(i);
		}
		//System.out.println(root.data);
//		bt.inOrder(bt.root);
		//bt.inOrderIterative(bt.root);
		/*bt.bfs(bt.root);
		System.out.println();
		bt.postOrder(bt.root);
		System.out.println();
		bt.preOrderIterative(bt.root);
		System.out.println();
		System.out.println(bt.findSum(bt.root));
		System.out.println(bt.countTree(3)); */
//		System.out.println(bt.nodeExist(bt.root, 4));
//		System.out.println(bt.findLCSBST(bt.root, 5, 7).data);
//		System.out.println(bt.findLCABT(bt.root, 2, 7).data);
		
		int [] in_order = new int[]{4,2,5,1,6,3};
		int [] pre_order = new int []{1,2,4,5,3,6};
		BTNode root = bt.buildTree(pre_order, in_order, 0, in_order.length-1);
		System.out.println("printing the tree");
		bt.inOrder(root);
//		System.out.println(bt.searchNode(in_order, 3));
	}
	
	/**
	 * 
	 * @param data
	 * @return
	 */
	
	private BTNode createNode(int data){
		BTNode newNode = new BTNode();
		newNode.data = data;
		newNode.left = newNode.right = null;
		return newNode;
	}
	
	// In-order recursive
	/**
	 * 
	 * @param root
	 */
	private void inOrder(BTNode root){
		if(root==null)
			return;
		inOrder(root.left);
		System.out.print(root.data + " ");
		inOrder(root.right);
	}
	
	// find sum of all nodes of a binary tree
	
	int sum = 0;
	/**
	 * 
	 * @param root
	 * @return
	 */
	private int findSum(BTNode root){
		if(root == null){
			return 0;
		}
		findSum(root.left); 
		sum = sum+root.data;
		findSum(root.right);
		return sum;
	}
	
	// Count number of bs tree created from a given number,
	/**
	 * 
	 * @param num
	 * @return
	 */
	private int countTree(int num){
		if(num==0 || num ==1)
			return 1;
		int sum = 0;
		for(int i = 1;i<=num;i++){
			int leftCount = countTree(i-1);
			int rightCount = countTree(num-i);
			sum = sum + leftCount*rightCount;
		}
		return sum;
	}
	
	// Iterative in order traversal
	/**
	 * 
	 * @param root
	 */
	private void inOrderIterative(BTNode root){
		if(root == null)
			return;
		Stack<BTNode> s = new Stack<>();
		
		while(true){
			while(root!=null){
				s.push(root);
				root = root.left;
			}
			if(s.isEmpty())
				break;
			root = s.pop();
			System.out.print(root.data + " ");
			root = root.right;
		}
	}
	
	// Iterative pre order traversal
	/**
	 * 
	 * @param root
	 */
	private void preOrderIterative(BTNode root){
		if(root == null)
			return;
		Stack<BTNode> s = new Stack<>();
		
		while(true){
			while(root!=null){
				System.out.print(root.data + " ");
				s.push(root);
				root = root.left;
			}
			if(s.isEmpty())
				break;
			root = s.pop();
			root = root.right;
		}
	}
	
	// Iterative post order traversal
	/**
	 * 
	 * @param root
	 */
	private void postOrder(BTNode root){
		if(root == null)
			return;
		Stack<BTNode> s1 = new Stack<>();
		Stack<BTNode> s2 = new Stack<>();
		s1.push(root);
		while(!s1.isEmpty()){
			root = s1.pop();
			s2.push(root);
			if(root.left!=null)
				s1.push(root.left);
			if(root.right!=null)
				s1.push(root.right);
		}
		while(!s2.isEmpty()){
			root = s2.pop();
			System.out.print(root.data + " ");
		}
	}
	
	// Iterative level order traversal
	/**
	 * 
	 * @param root
	 */
	private void bfs(BTNode root){
		if(root == null)
			return;
		Queue<BTNode> q = new LinkedList<BTNode>();
		q.add(root);
		while(!q.isEmpty()){
			BTNode temp = (BTNode) q.poll();
			System.out.print(temp.data + " ");
			if(temp.left!=null)
				q.add(temp.left);
			if(temp.right!=null)
				q.add(temp.right);
		}
		
	}
	
	// Add node to a binary tree iteratively
	/**
	 * 
	 * @param value
	 */
	private void add(int value){
		if(root==null)
			root = createNode(value);
		else{
			BTNode temp = root;
			BTNode parent = root;
			while(temp!=null){
				if(temp.data>=value){
					parent = temp;
					temp =temp.left;
				}
				else{
					parent = temp;
					temp = temp.right; 
				}
			}
			if(value<=parent.data){
				parent.left = createNode(value);
			}
			else{
				parent.right = createNode(value);
			}
		}
		
	}
	
	// Add node to a binary tree recursively
	/**
	 * 
	 * @param value
	 */
	private void addRecursive(int value){
		root = addRecursive(root, value);
	}
	
	private BTNode addRecursive(BTNode root, int data){
		if(root == null){
			root = createNode(data);
			return root;
		}
		if(data<=root.data){
			root.left =  addRecursive(root.left, data);
		}
		else{
			root.right =  addRecursive(root.right, data);
		}
		return root;
	}
	
	// find LCA in a Binary Search Tree

	/**
	 * 
	 * @param root
	 * @param n1
	 * @param n2
	 * @return
	 */
	private BTNode findLCSBST(BTNode root, int n1, int n2){
		if(root == null)
			return root;
		if(root.data>n1 && root.data >n2)
			return findLCSBST(root.left, n1,n2);
		else if(root.data<n1 && root.data<n2)
			return findLCSBST(root.right, n1, n2);
		else
			return root;
	}
	
	private boolean nodeExist(BTNode root, int n){
		if(root==null)
			return false;
		else if(root.data == n)
			return true;
		else if(root.data>n)
			return nodeExist(root.left, n);
		else
			return nodeExist(root.right, n);
	}
	
	// find LCA in a Binary Tree
	/**
	 * 
	 * @param root
	 * @param n1
	 * @param n2
	 * @return
	 */
	private BTNode findLCABT(BTNode root, int n1, int n2){
		if(root == null)
			return root;
		else if(root.data == n1 || root.data == n2)
			return root;
		BTNode leftLCA = findLCABT(root.left, n1, n2);
		BTNode rightLCA = findLCABT(root.right, n1, n2);
		if(leftLCA!=null && rightLCA!=null)
			return root;
		return (leftLCA!=null?leftLCA:rightLCA);
	}
	static int pre_index = 0;
	
	// Build a tree with given In-order and pre order .
	/**
	 * 
	 * @param preOrder
	 * @param inOrder
	 * @param in_start
	 * @param in_end
	 * @return
	 */
	BTNode buildTree(int [] preOrder, int [] inOrder, int in_start, int in_end){
		if(in_start>in_end){
			return null;
		}
		BTNode newNode = new BTNode();
		newNode.data = preOrder[pre_index];
		pre_index ++;
		if(in_start == in_end){
			return newNode;
		}
		int in_index = 0;
		in_index= searchNode(inOrder, newNode.data);
		newNode.left = buildTree(preOrder, inOrder, in_start, in_index-1);
		newNode.right = buildTree(preOrder, inOrder, in_index+1, in_end);
		return newNode;
		
		
	}
	/**
	 * 
	 * @param inOrder
	 * @param data
	 * @return
	 */
	int searchNode(int [] inOrder, int data){
		for (int i = 0; i < inOrder.length; i++) {
			if(inOrder[i]==data)
				return i;
		}
		return -1;
	}

}

