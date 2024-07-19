package data_structures;

import counter.MultiCounter;

public class ThreadedBSTwArray {
	private final int info = 0;
	private final int left = 1;
	private final int right = 2;
	private int leftThr = 3;		//1 if left points to thread, 0 if left points to thread 
	private int rightThr = 4;		//1 if right points to thread, 0 if right points to thread 
	private int rootIndex;
	private int avail;
	private int [][] array;
	
	/*
	 * leftmost has leftThr 1 and left -1 , topmost has rightThr 1 and right -1
	 */
	public ThreadedBSTwArray(int size) {
		this.array = new int [size][5];
		this.rootIndex = -1;			//empty tree
		this.avail = 0;					//empty tree
		for(int i = 0; i < size; i++) {
			array[i][info] = -1;
			array[i][left] = -1;
			array[i][leftThr] = -1;
			array[i][rightThr] = -1;
			array[i][right] = i+1;		//initializing array's 3rd column pointing to next avail pos			
		}
		array[size-1][right] = -1;	//-1 ->last row has no available next node
	}
	public void getArray() {
		for(int i = 0; i <array.length; i++) {
		for(int j = 0; j < 5; j++) {
			System.out.print(array[i][j]+" ");
		}
		System.out.println();
		}
	}

	
	public void insert(int val) {
		rootIndex = inserthelp(rootIndex, val);
	}
	
	private int inserthelp(int rootIndex, int val) {	//counter 1 for insertion in tbst
		if(MultiCounter.increaseCounter(2) && avail == -1) { 
			System.out.println("Maximum memory reached. Can't insert more elements");
		}
		else if(MultiCounter.increaseCounter(2) && avail == 0) {	//empty tree
			rootIndex = 0;
			avail = array[rootIndex][right];
			array[rootIndex][info] = val;
			array[rootIndex][right] = -1;			//new node has no children, left is already == -1
			array[rootIndex][leftThr] = 1;
			array[rootIndex][rightThr] = 1;
			return rootIndex;
		}
		else if(MultiCounter.increaseCounter(2) && array[rootIndex][info] > val) {		//value < root
			if((MultiCounter.increaseCounter(2) && array[rootIndex][left] == -1) || ((MultiCounter.increaseCounter(2) && array[rootIndex][left] != -1) && (MultiCounter.increaseCounter(2) && array[rootIndex][leftThr] == 1))) {		//root has no left child
				array[avail][left] = array[rootIndex][left];	//tmp->left = parent->left(child points to where parent pointed)
				array[rootIndex][leftThr] = 0;						
				array[rootIndex][left] = avail;		//parent left points to child
				array[avail][info] = val;			//insert left child of root
				array[avail][leftThr] = 1;			//
				array[avail][rightThr] = 1;			
				avail = array[avail][right];		//avail points to next available position of array
				array[array[rootIndex][left]][right] = rootIndex; //tmp->right = par
														//new node has no children, left is already == -1
			}
			else{
				MultiCounter.increaseCounter(2);
				inserthelp(array[rootIndex][left], val);
			}
		}
		else{										//value >= root
			if((MultiCounter.increaseCounter(2) && array[rootIndex][right] == -1) || ((MultiCounter.increaseCounter(2) && array[rootIndex][right] != -1) && (MultiCounter.increaseCounter(2) && array[rootIndex][rightThr] == 1))) {		//root has no right child
				int nextAvail = array[avail][right];
				array[avail][right] = array[rootIndex][right];	//tmp->right = par->right
				array[rootIndex][rightThr] = 0;		//par->rightTh = 0
				array[rootIndex][right] = avail;	//par->right = tmp
				array[avail][info] = val;			//insert right child of root
				array[avail][leftThr] = 1;			//
				array[avail][rightThr] = 1;
				avail = nextAvail;					//avail points to next available position of array		
				array[array[rootIndex][right]][left] = rootIndex; //tmp->left = par
													//new node has no children, left is already == -1
			}
			else {
				MultiCounter.increaseCounter(2);
				inserthelp(array[rootIndex][right], val);
			}	
		}
		return rootIndex;
	}
	
	public void printRange(int low, int high) {		//Multicounter 7
		//System.out.print("\nPrint keys between " + low + " and " + high +": ");
		//increase counter for every method call except for first call from main
		MultiCounter.increaseCounter(7);
		printrangehelp(rootIndex, low, high);
		//System.out.println();
	}
	private void printrangehelp(int rootIndex, int low, int high) {
		if (MultiCounter.increaseCounter(7) && rootIndex == -1)		//empty tree
			return;
		MultiCounter.resetCounter(4);//resets counter that counts findhelp()
		int num = find(low);
		MultiCounter.increaseCounter(7,MultiCounter.getCount(4));	//increase counter 7 by number 
		//of comparisons of find()
		if((MultiCounter.increaseCounter(7) && num == -1) || (MultiCounter.increaseCounter(7) && array[num][info] > high)) 
			return; 					//no keys in the given range
		while((MultiCounter.increaseCounter(7) && num != -1) && (MultiCounter.increaseCounter(7) && array[num][info] <= high)) {
			//System.out.print(array[num][info]+" ");	
			num = inorderSuccessor(num);							//MultiCounter 7 continues in inorderSuccessor
		}
	}
	public int find(int key) {
		return findhelp(rootIndex, key);
	}

	/**
	 * epistrefei to amesws megalytero pos toy node apo ayton poy psaxnw an den ton brw h to pos toy node an yparxei
	 *
	 */
	private int findhelp(int rootIndex, int key) {	//Multicounter 4 
		if (MultiCounter.increaseCounter(4) && rootIndex == -1) {
			//System.out.println("Key: "+key+" not found");	
			return Integer.MIN_VALUE;
		}
		if (MultiCounter.increaseCounter(4) && array[rootIndex][info] > key) {	//if key < node 
			if(MultiCounter.increaseCounter(4) && array[rootIndex][leftThr] == 0) {	//and if node has left child
				MultiCounter.increaseCounter(4);
				return findhelp(array[rootIndex][left], key);}
			//System.out.println("Key: "+key+" not found");	//array[rootIndex][left] points to predessesor-
			return rootIndex;						//-(smaller value would have already found)so key does not exist
		}else if (MultiCounter.increaseCounter(4) && array[rootIndex][info] == key) {
			//System.out.println("Key: "+key+" found");
			return rootIndex;		//returns pos of next node with node>key
		}
		else {												//if key > node 
			if(MultiCounter.increaseCounter(4) && array[rootIndex][rightThr] == 0) {	//and if node has right child
				MultiCounter.increaseCounter(4);
				return findhelp(array[rootIndex][right], key);}
			//System.out.println("Key: "+key+" not found");	//array[rootIndex][right] points to successor-
			return array[rootIndex][right];					//-(greater value would have already found)so key does not exist			
			//returns pos of next node with node>key
		}
	}
	
	public void inorder() {
		inorder(rootIndex);
		System.out.println();
	}
	private int inorderSuccessor( int ptr)	//MultiCounter 7 continues from printrangehelp
	{
	    // If rthread is set, we can quickly find
	    if (MultiCounter.increaseCounter(7) && array[ptr][rightThr] == 1)
	        return array[ptr][right];
	   
	    // Else return leftmost child of right subtree
	    ptr = array[ptr][right];
	    while (MultiCounter.increaseCounter(7) && array[ptr][leftThr] == 0)
	        ptr = array[ptr][left];
	    return ptr;
	}
	   
	// Printing the threaded tree
	private void inorder(int rootIndex)
	{
	    if (rootIndex == -1)
	        System.out.printf("Tree is empty");
	   
	    // Reach leftmost node
	    int ptr = rootIndex;
	    while (array[ptr][leftThr] == 0)
	    	ptr = array[ptr][left];
	   
	    // One by one print successors
	    while (ptr != -1)
	    {
	        System.out.printf("%d ",array[ptr][info]);
	        ptr = inorderSuccessor(ptr);
	    }
	}
	
}
