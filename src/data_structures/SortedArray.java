package data_structures;

import java.util.Arrays;

import counter.MultiCounter;

public class SortedArray {
	private int[] data;
	
	public SortedArray(int [] data) {
		Arrays.sort(data);	
		this.data = data ;
		
	}
	
	public void getArray() {
		for(int i = 0; i < data.length; i++) {
			System.out.print(data[i]+" ");			
		}
	}
		

	
	/**
	 * prints elements [low, high] of array 
	 * @param low
	 * @param high
	 */
	public void printRange(int low, int high) {		//Multicounter 8
		//System.out.print("\nPrint keys between " + low + " and " + high +": ");
		MultiCounter.resetCounter(5);		//resets counter that counts find()
		int ind = find(low);
		MultiCounter.increaseCounter(8,MultiCounter.getCount(5));	//increase counter 8 by number 
		//of comparisons of find()
		if(MultiCounter.increaseCounter(8) && ind == -1) 
			return;			//low > max element of array
		while((MultiCounter.increaseCounter(8) && ind < data.length) && (MultiCounter.increaseCounter(8) && data[ind] <= high)) {
			//System.out.print(data[ind]+" ");
			ind++;
		}
		//System.out.println();
	}
	/**
	 * Searches in array to find key
	 * @param key
	 * @return index of key in array if found, index of next bigger key in array if min < key < max , else -1 
	 */
	public int find(int key) {		//Multicounter 5 continues counting in doSearch
		int ind = doSearch(0, data.length-1, key);
		if (MultiCounter.increaseCounter(5) && ind == data.length) {
			//System.out.println("Key: "+key+" not found");					
			return -1;
		}else if(MultiCounter.increaseCounter(5) && data[ind] != key) {
			//System.out.println("Key: "+key+" not found");					
			return ind;
		}
		//System.out.println(data[ind]);		//key found
		return ind;
		
	}
	/**
	 * Searches binary in array to find key
	 * @param leftIndex, rightIndex, key
	 * @return index of key in array if found,or index of next bigger key in array if min < key < max
	 * 			else array.length
	 */
    private int doSearch(int leftIndex, int rightIndex, int key) 
    { 
        if (MultiCounter.increaseCounter(5) && rightIndex >= leftIndex) { 
            int mid = leftIndex + (rightIndex - leftIndex) / 2; 
  
            // If the element is present at the 
            // middle itself 
            if (MultiCounter.increaseCounter(5) && data[mid] == key) 
                return mid; 
  
            // If element is smaller than mid, then 
            // it can only be present in left subarray 
            if (MultiCounter.increaseCounter(5) && data[mid] > key) { 
            	MultiCounter.increaseCounter(5);
                return doSearch(leftIndex, mid - 1, key);
            } 
  
            // Else the element can only be present 
            // in right subarray 
            MultiCounter.increaseCounter(5);
            return doSearch(mid + 1, rightIndex, key); 
        } 
  
        // We reach here when element is not present in array. 
        // We return Integer.MIN_VALUE in this case, so the data array can not contain this value!
        return leftIndex; 
    }
}
