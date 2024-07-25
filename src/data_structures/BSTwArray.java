package data_structures;

import counter.MultiCounter;

/*
 * Binary search tree implemented with array. Nodes contain only integers
 */
public class BSTwArray {

	private final int info = 0;
	private final int left = 1;
	private final int right = 2;
	private int rootIndex;
	private int avail;
	private final int insertC;
	private final int findC;
	private final int rangeC;
	private final int [][] array;
	
	public BSTwArray(int size, int insertC, int findC, int rangeC) {
		this.array = new int [size][3];
		this.rootIndex = -1;			//empty tree
		this.avail = 0;					//empty tree
		this.insertC = insertC;
		this.findC = findC;
		this.rangeC = rangeC;
		for(int i = 0; i < size; i++) {
			array[i][info] = -1;
			array[i][left] = -1;
			array[i][right] = i+1;		//initializing array's 3rd column pointing to next avail pos
		}
		array[size-1][right] = -1;	//-1 ->last row has no available next node
	}
	
	
	public void getArray() {
        for (int[] rows : array) {
            for (int j = 0; j < 3; j++) {
                System.out.print(rows[j] + " ");
            }
            System.out.println();
        }
	}

	public void insert(int val) {
		rootIndex = inserthelp(rootIndex, val);
	}
	public int getRoot() {
		return this.rootIndex;
	}
	public int inserthelp(int rootIndex, int val) {	//counter 0 for insertion in bst
		if(MultiCounter.increaseCounter(insertC) && avail == -1) {
			System.out.println("Maximum memory reached. Can't insert more elements");
		}
		else if(MultiCounter.increaseCounter(insertC) && avail == 0) {	//empty tree
			rootIndex = 0;
			avail = array[rootIndex][right];
			array[rootIndex][info] = val;
			array[rootIndex][right] = -1;			//new node has no children, left is already == -1
			return rootIndex;
		}
		else if(MultiCounter.increaseCounter(insertC) && array[rootIndex][info] > val) {		//value < root
			if(MultiCounter.increaseCounter(insertC) && array[rootIndex][left] == -1) {		//root has no left child
				array[rootIndex][left] = avail;		
				array[avail][info] = val;			//insert left child of root
				avail = array[avail][right];		//avail points to next available position of array
				array[array[rootIndex][left]][right] = -1;
				//array[avail][right] = -1;			//new node has no children, left is already == -1
				//return rootIndex;
			}
			else {
				MultiCounter.increaseCounter(insertC);
				inserthelp(array[rootIndex][left], val);
			}
		}
		else{										//value >= root
			if(MultiCounter.increaseCounter(insertC) && array[rootIndex][right] == -1) {		//root has no right child
				array[rootIndex][right] = avail;		
				array[avail][info] = val;			//insert right child of root
				avail = array[avail][right];		//avail points to next available position of array
				array[array[rootIndex][right]][right] = -1;
				//array[avail][right] = -1;			//new node has no children, left is already == -1
				//return rootIndex;
			}
			else {
				MultiCounter.increaseCounter(insertC);
				inserthelp(array[rootIndex][right], val);
			}
		}
		return rootIndex;
	}
	
	
	public int find(int key) {
		return findhelp(rootIndex, key);
	}
	
	private int findhelp(int rootIndex, int key) {
		if (MultiCounter.increaseCounter(findC) && rootIndex == -1) {				//Multicounter 3
			//System.out.println("Key: "+key+" not found");	
			MultiCounter.increaseCounter(findC);
			return Integer.MIN_VALUE;
		}
		if (MultiCounter.increaseCounter(findC) && array[rootIndex][info] > key) {
			MultiCounter.increaseCounter(findC);
			return findhelp(array[rootIndex][left], key);}
		else if (MultiCounter.increaseCounter(findC) && array[rootIndex][info] == key) {
			//System.out.println("Key: "+key+" found");
			return array[rootIndex][info];
		}
		else {
			MultiCounter.increaseCounter(findC);
			return findhelp(array[rootIndex][right], key);}
		}
	
	
	
	public void inorder() {
		inorderhelp(rootIndex);
		System.out.println();
	}
	
	private void inorderhelp(int subroot) {
		if (subroot == -1)
			return; // Empty, do nothing
		inorderhelp(array[subroot][left]);
		visit(subroot); // Perform desired action
		inorderhelp(array[subroot][right]);
	}
	
	private void visit(int rt) {
		System.out.print(array[rt][info] + " ");
	}
	
	public void printRange(int low, int high) {	//Multicounter 6
		//System.out.print("\nPrint keys between " + low + " and " + high +": ");
		//increase counter for every method call except for first call from main
		MultiCounter.increaseCounter(rangeC);
		printrangehelp(rootIndex, low, high);
		//System.out.println();
	}
	
	private void printrangehelp(int rootIndex, int low, int high) {
		if (MultiCounter.increaseCounter(rangeC) && rootIndex == -1)
			return;
		if (MultiCounter.increaseCounter(rangeC) && high < array[rootIndex][info]) { // all to left
			MultiCounter.increaseCounter(rangeC);
			printrangehelp(array[rootIndex][left], low, high);}
		else if (MultiCounter.increaseCounter(rangeC) && low > array[rootIndex][info]) { // all to right
			MultiCounter.increaseCounter(rangeC);
			printrangehelp(array[rootIndex][right], low, high);}
		else { // Must process both children
			MultiCounter.increaseCounter(rangeC);
			printrangehelp(array[rootIndex][left], low, high);
		//System.out.print(" " + array[rootIndex][info]);
			MultiCounter.increaseCounter(rangeC);
		printrangehelp(array[rootIndex][right], low, high);
		}
	}
	
	
	
	
}
