package data_structures;

import java.util.Arrays;

import counter.MultiCounter;

public class SortedArray {
	private final int[] data;
	private final int findC;
	private final int rangeC;
	public SortedArray(int [] data,int findC, int rangeC) {
		Arrays.sort(data);	
		this.data = data ;
		this.findC = findC ;
		this.rangeC = rangeC ;
		
	}
	
	@SuppressWarnings("unused")
    public void getArray() {
        for (int datum : data) {
            System.out.print(datum + " ");
        }
	}
		

	
	/**
	 * prints elements [low, high] of array 
	 * @param low the lower bound to print inclusive
	 * @param high the upper bound to print inclusive
	 */
	public void printRange(int low, int high) {		//Multicounter 8
		//System.out.print("\nPrint keys between " + low + " and " + high +": ");
		MultiCounter.resetCounter(findC);		//resets counter that counts find()
		int ind = find(low);
		MultiCounter.increaseCounter(rangeC,MultiCounter.getCount(findC));	//increase counter 8 by number
		//of comparisons of find()
		if(MultiCounter.increaseCounter(rangeC) && ind == -1) 
			return;			//low > max element of array
		while((MultiCounter.increaseCounter(rangeC) && ind < data.length) && (MultiCounter.increaseCounter(rangeC) && data[ind] <= high)) {
			//System.out.print(data[ind]+" ");
			ind++;
		}
		//System.out.println();
	}
	/**
	 * Searches in array to find key
	 * @param key the key to find
	 * @return index of key in array if found, index of next bigger key in array if min < key < max , -1 if key is out of bounds
	 */
	public int find(int key) {		//Multicounter 5 continues counting in doSearch
		if( (MultiCounter.increaseCounter(findC) && key < data[0]) ||
				(MultiCounter.increaseCounter(findC) && key > data[data.length - 1]) ) {
			return -1;
		}
		return doSearch(0, data.length-1, key);
	}
	/**
	 * Searches binary in array to find key
	 * @param leftIndex, rightIndex, key
	 * @return index of key in array if found,or index of next bigger key in array if min < key < max
	 *
	 */
    private int doSearch(int leftIndex, int rightIndex, int key) 
    { 
        if (MultiCounter.increaseCounter(findC) && rightIndex >= leftIndex) { 
            int mid = leftIndex + (rightIndex - leftIndex) / 2; 
  
            // If the element is present at the 
            // middle itself 
            if (MultiCounter.increaseCounter(findC) && data[mid] == key) 
                return mid; 
  
            // If element is smaller than mid, then 
            // it can only be present in left subarray 
            if (MultiCounter.increaseCounter(findC) && data[mid] > key) { 
            	MultiCounter.increaseCounter(findC);
                return doSearch(leftIndex, mid - 1, key);
            } 
  
            // Else the element can only be present 
            // in right subarray 
            MultiCounter.increaseCounter(findC);
            return doSearch(mid + 1, rightIndex, key); 
        } 
  
        // We reach here when element is not present in array.
        return leftIndex;
    }

	public static void main(String[] args) {
		int [] keys = new int[4];
		keys[0] = 10;
		keys[1] = 20;
		keys[2] = 30;
		keys[3] = 40;
		SortedArray s = new SortedArray(keys, 5, 8);

		for (int i = 10; i <= 40; i = i + 5) {
//			System.out.println("Key: "+ i + " Result: "+ s.find(i));
			System.out.println("Key: "+ i + " Result: "+ s.doSearch(0, keys.length-1, i));

		}

	}
}
