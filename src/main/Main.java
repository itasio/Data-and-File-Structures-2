package main;

import counter.MultiCounter;
import data_structures.BSTwArray;
import data_structures.SortedArray;
import data_structures.ThreadedBSTwArray;

public class Main {
	static final int numKeys = 100000;
	static final int minKey = 1;
	static final int maxKey = 1000001;
	static final int numOfSearches = 10;
	static final int rangeMin = 100;
	static final int rangeMax = 1000;
	
	public static void main(String[] args) {
		
		int [] keys = intArrayGenerator(minKey, maxKey, numKeys);
		int [] searchKeys = intArrayGenerator(minKey, maxKey, numOfSearches);
		
		
		/*for(int i =0; i<keys.length; i++) {
			System.out.println(keys[i]+" ");
		}*/
		//now creating BST, Threaded BST and sortedArray
		BSTwArray bst = new BSTwArray(numKeys);
		ThreadedBSTwArray tbst = new ThreadedBSTwArray(numKeys);


		System.out.println("Now inserting nodes");
		for(int i = 0; i < keys.length; i++) {
			bst.insert(keys[i]);			//Multicounter 1 
			tbst.insert(keys[i]);			//Multicounter 2
		}
		SortedArray sortArray = new SortedArray(keys);

		System.out.println("Mean number of comparisons in insertion in BST: "+(MultiCounter.getCount(1)/numKeys));
		System.out.println("Mean number of comparisons in insertion in Threaded BST: "+(MultiCounter.getCount(2)/numKeys));
		//searching same random keys in all data structures
		//now making 100 random key searches
		for(int i = 0; i < numOfSearches; i++) {
			bst.find(searchKeys[i]);			//Multicounter 3 
			tbst.find(searchKeys[i]);			//Multicounter 4
			sortArray.find(searchKeys[i]);		//Multicounter 5
		}
		System.out.println("Mean number of comparisons in searching in BST: "+(MultiCounter.getCount(3)/numOfSearches));
		System.out.println("Mean number of comparisons in searching in Threaded BST: "+(MultiCounter.getCount(4)/numOfSearches));
		System.out.println("Mean number of comparisons in searching in Sorted Array: "+(MultiCounter.getCount(5)/numOfSearches));
		
		//now making 100 random range searches (range = 100 )
		for(int i = 0; i < numOfSearches; i++) {
			bst.printRange(searchKeys[i], searchKeys[i]+rangeMin);		//Multicounter 6			
			tbst.printRange(searchKeys[i], searchKeys[i]+rangeMin);		//Multicounter 7
			sortArray.printRange(searchKeys[i], searchKeys[i]+rangeMin);	//Multicounter 8
		}
		System.out.println("Mean number of comparisons in range searching(100) in BST: "+(MultiCounter.getCount(6)/numOfSearches));
		System.out.println("Mean number of comparisons in range searching(100) in Threaded BST: "+(MultiCounter.getCount(7)/numOfSearches));
		System.out.println("Mean number of comparisons in range searching(100) in Sorted Array: "+(MultiCounter.getCount(8)/numOfSearches));

		
		//now making 100 random range searches (range = 1000 )
		MultiCounter.resetCounter(6);		//reset counters that count printRange
		MultiCounter.resetCounter(7);
		MultiCounter.resetCounter(8);
		for(int i = 0; i < numOfSearches; i++) {
			
			bst.printRange(searchKeys[i], searchKeys[i]+rangeMax);		//Multicounter 6
			tbst.printRange(searchKeys[i], searchKeys[i]+rangeMax);		//Multicounter 7
			sortArray.printRange(searchKeys[i], searchKeys[i]+rangeMax);//Multicounter 8
		}
		
		System.out.println("Mean number of comparisons in range searching(1000) in BST: "+(MultiCounter.getCount(6)/numOfSearches));
		System.out.println("Mean number of comparisons in range searching(1000) in Threaded BST: "+(MultiCounter.getCount(7)/numOfSearches));
		System.out.println("Mean number of comparisons in range searching(1000) in Sorted Array: "+(MultiCounter.getCount(8)/numOfSearches));
		
	}
			
	/**
	 * returns int [numOfElemnts] with distinct numbers between startInt(inclusive)  and endInd(exclusive)
	 */
	public static int[] intArrayGenerator(int startInt, int endInt, int numOfElements) {
		java.util.Random randomGenerator = new java.util.Random();
		int[] randomInts = randomGenerator.ints(startInt, endInt).distinct().limit(numOfElements).toArray();
		return randomInts;
	}

	
}

