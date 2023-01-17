import java.util.List;
import java.util.ArrayList;
/**
 *	SortMethods - Sorts an array of Integers in ascending order.
 *
 *	@author Aryan Singhal
 *	@since	December 5, 2022
 */
public class SortMethods
{	
	/**
	 *	Swaps two Integer objects in array arr
	 *	@param arr		array of Integer objects
	 *	@param x		index of first object to swap
	 *	@param y		index of second object to swap
	 */
	private void swap(List<City> arr, int x, int y)
	{
		City temp = arr.get(x);
		arr.set(x, arr.get(y));
		arr.set(y, temp);
	}
	
	/**
	 *	Selection Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void selectionSortAscPop(List<City> arr)
	{
		int n = arr.size();
		for(int i = 0; i < n - 1; i++)
		{
			int minI = i;
			for(int j = i + 1; j < n; j++)
				if(arr.get(j).compareTo(arr.get(minI)) < 0)
					minI = j;
					
			swap(arr, minI, i);
		}
	}
	
	/**
	 *	Insertion Sort algorithm - in ascending order (you implement)
	 *	@param arr		array of Integer objects to sort
	 */
	public void insertionSortAscName(List<City> arr)
	{
		for(int n = 1; n < arr.size(); n++)
		{
			City aTemp = arr.get(n);
			
			int i = n;
			while(i > 0 && aTemp.getCityName().compareTo(arr.get(i - 1).getCityName()) < 0)
			{
				arr.set(i, arr.get(i - 1));
				swap(arr, i, i - 1);
				i--;
			}
			arr.set(i, aTemp);
		}
	}
	
	/**
	 * Merge sort algorithim for populations in descending order
	 * @param arr		array of Integer objects to sort
	 * @param l			first index in current array
	 * @param r			last index in current array
	 */
	public void mergeSortDescPop(List<City> arr, int l, int r)
	{
		if (l < r)
		{
			// Find the middle point
			int m = (l+r)/2;

			// Sort first and second halves
			mergeSortDescPop(arr, l, m);
			mergeSortDescPop(arr, m+1, r);

			// Merge the sorted halves
			mergePop(arr, l, m, r);
		}
	}
	
	/**
	 * Merge sort algorithim for populations
	 * @param arr		array of Integer objects to sort
	 * @param l			first index in current array
	 * @param m			middle index of current array to be sorted
	 * @param r			last index in current array
	 */
	public void mergePop(List<City> arr, int l, int m, int r)
	{
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		List<City> L = new ArrayList<City>();
		List<City> R = new ArrayList<City>();

		/*Copy data to temp arrays*/
		for (int i=0; i<n1; i++)
			L.add(arr.get(l + i));
		for (int j=0; j<n2; j++)
			R.add(arr.get(m + 1 + j));

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarray array
		int k = l;
		while (i < n1 && j < n2)
		{
			if (L.get(i).compareTo(R.get(j)) >= 0)
			{
				arr.set(k, L.get(i));
				i++;
			}
			else
			{
				arr.set(k, R.get(j));
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1)
		{
			arr.set(k, L.get(i));
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2)
		{
			arr.set(k, R.get(j));
			j++;
			k++;
		}
	}
	
	/**
	 * Merge sort algorithim for city names in descending order
	 * @param arr		array of String objects to sort
	 * @param l			first index in current array
	 * @param r			last index in current array
	 */
	public void mergeSortDescName(List<City> arr, int l, int r)
	{
		if (l < r)
		{
			// Find the middle point
			int m = (l+r)/2;

			// Sort first and second halves
			mergeSortDescName(arr, l, m);
			mergeSortDescName(arr, m+1, r);

			// Merge the sorted halves
			mergeName(arr, l, m, r);
		}
	}
	
	/**
	 * Merge sort algorithim for city names
	 * @param arr		array of String objects to sort
	 * @param l			first index in current array
	 * @param m			middle index of current array to be sorted
	 * @param r			last index in current array
	 */
	public void mergeName(List<City> arr, int l, int m, int r)
	{
		// Find sizes of two subarrays to be merged
		int n1 = m - l + 1;
		int n2 = r - m;

		/* Create temp arrays */
		List<City> L = new ArrayList<City>();
		List<City> R = new ArrayList<City>();

		/*Copy data to temp arrays*/
		for (int i=0; i<n1; i++)
			L.add(arr.get(l + i));
		for (int j=0; j<n2; j++)
			R.add(arr.get(m + 1 + j));

		/* Merge the temp arrays */

		// Initial indexes of first and second subarrays
		int i = 0, j = 0;

		// Initial index of merged subarray array
		int k = l;
		while (i < n1 && j < n2)
		{
			if (L.get(i).getCityName().compareTo(R.get(j).getCityName()) >= 0)
			{
				arr.set(k, L.get(i));
				i++;
			}
			else
			{
				arr.set(k, R.get(j));
				j++;
			}
			k++;
		}

		/* Copy remaining elements of L[] if any */
		while (i < n1)
		{
			arr.set(k, L.get(i));
			i++;
			k++;
		}

		/* Copy remaining elements of R[] if any */
		while (j < n2)
		{
			arr.set(k, R.get(j));
			j++;
			k++;
		}
	}
}
