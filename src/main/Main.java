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

	static final int BSTInsertCounter = 1;
	static final int ThrBSTInsertCounter = 2;

	static final int BSTFindCounter = 3;
	static final int ThrBSTFindCounter = 4;
	static final int SortedArrFindCounter = 5;

	static final int BSTRangeCounter = 6;
	static final int ThrBSTRangeCounter = 7;
	static final int SortedArrRangeCounter = 8;


	public static void main(String[] args) {
		
		int [] keys = intArrayGenerator(minKey, maxKey, numKeys);
		int [] searchKeys = intArrayGenerator(minKey, maxKey, numOfSearches);

		//now creating BST, Threaded BST and sortedArray
		BSTwArray bst = new BSTwArray(numKeys, BSTInsertCounter, BSTFindCounter, BSTRangeCounter);
		ThreadedBSTwArray tbst = new ThreadedBSTwArray(numKeys, ThrBSTInsertCounter, ThrBSTFindCounter, ThrBSTRangeCounter);


		System.out.println("Now inserting nodes");
        for (int key : keys) {
            bst.insert(key);            //MultiCounter 1
            tbst.insert(key);            //MultiCounter 2
        }
		SortedArray sortArray = new SortedArray(keys, SortedArrFindCounter, SortedArrRangeCounter);

		System.out.println("Mean number of comparisons in insertion in BST: "+(MultiCounter.getCount(1)/numKeys));
		System.out.println("Mean number of comparisons in insertion in Threaded BST: "+(MultiCounter.getCount(2)/numKeys));
		//searching same random keys in all data structures
		//now making 100 random key searches
		for(int i = 0; i < numOfSearches; i++) {
			bst.find(searchKeys[i]);			//MultiCounter 3
			tbst.find(searchKeys[i]);			//MultiCounter 4
			sortArray.find(searchKeys[i]);		//MultiCounter 5
		}
		System.out.println("Mean number of comparisons in searching in BST: "+(MultiCounter.getCount(3)/numOfSearches));
		System.out.println("Mean number of comparisons in searching in Threaded BST: "+(MultiCounter.getCount(4)/numOfSearches));
		System.out.println("Mean number of comparisons in searching in Sorted Array: "+(MultiCounter.getCount(5)/numOfSearches));
		
		//now making 100 random range searches (range = 100 )
		for(int i = 0; i < numOfSearches; i++) {
			bst.printRange(searchKeys[i], searchKeys[i]+rangeMin);		//MultiCounter 6
			tbst.printRange(searchKeys[i], searchKeys[i]+rangeMin);		//MultiCounter 7
			sortArray.printRange(searchKeys[i], searchKeys[i]+rangeMin);	//MultiCounter 8
		}
		System.out.println("Mean number of comparisons in range searching(100) in BST: "+(MultiCounter.getCount(6)/numOfSearches));
		System.out.println("Mean number of comparisons in range searching(100) in Threaded BST: "+(MultiCounter.getCount(7)/numOfSearches));
		System.out.println("Mean number of comparisons in range searching(100) in Sorted Array: "+(MultiCounter.getCount(8)/numOfSearches));

		
		//now making 100 random range searches (range = 1000 )
		MultiCounter.resetCounter(6);		//reset counters that count printRange
		MultiCounter.resetCounter(7);
		MultiCounter.resetCounter(8);
		for(int i = 0; i < numOfSearches; i++) {
			
			bst.printRange(searchKeys[i], searchKeys[i]+rangeMax);		//MultiCounter 6
			tbst.printRange(searchKeys[i], searchKeys[i]+rangeMax);		//MultiCounter 7
			sortArray.printRange(searchKeys[i], searchKeys[i]+rangeMax);//MultiCounter 8
		}
		
		System.out.println("Mean number of comparisons in range searching(1000) in BST: "+(MultiCounter.getCount(6)/numOfSearches));
		System.out.println("Mean number of comparisons in range searching(1000) in Threaded BST: "+(MultiCounter.getCount(7)/numOfSearches));
		System.out.println("Mean number of comparisons in range searching(1000) in Sorted Array: "+(MultiCounter.getCount(8)/numOfSearches));
		
	}

	/**
	 * Creates an array with distinct numbers between startInt (inclusive)  and endInt (exclusive)
	 * @param startInt the lower limit of the numbers created
	 * @param endInt the upper limit of the numbers created
	 * @param numOfElements the number of numbers created i.e. the size of the array
	 * @return int []
	 */
	public static int[] intArrayGenerator(int startInt, int endInt, int numOfElements) {
		java.util.Random randomGenerator = new java.util.Random();
		return randomGenerator.ints(startInt, endInt).distinct().limit(numOfElements).toArray();
	}
	
}

