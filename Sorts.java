/** 
** Software Technology 152
** Class to hold various static sort methods.
*/
import java.util.*;
class Sorts
{
    // bubble sort
    public static void bubbleSort(int[] A)
    {
        boolean sorted;
        int end = 1;
        do
        {
            sorted = true;
            for(int i = 0; i < A.length-end; i++)
            {
                if(A[i] > A[i+1])
                {
                    int temp = A[i];
                    A[i] = A[i+1];
                    A[i+1] = temp;
                    sorted = false;//If no swaps are made sorted remains true
                }
            }
            end++;
        }while(!sorted);
    }//bubbleSort()

    // selection sort
    public static void selectionSort(int[] A)
    {
        for(int start = 0; start < A.length; start++)
        {
            int sIndex = start;
            for(int i = 0 + start; i < A.length; i++)
            {
                if(A[i] < A[sIndex])
                {
                    sIndex = i;
                }
            }
            int temp = A[sIndex];
            A[sIndex] = A[start];
            A[start] = temp;
        }
        
    }// selectionSort()

    // insertion sort
    public static void insertionSort(int[] A)
    {
        for(int i = 1; i <A.length; i++)
        {
            int j = i;
            int temp = A[j];
            while(j > 0 && A[j-1] > temp)
            {
                A[j] = A[j-1];//move element j-1 right by one
                j--;
            }
            A[j] = temp;
        }
    }// insertionSort()
    
    //shell sort
    public static void shellSort(int[] A)
    {
        int len = A.length;
        int gap = getGap(len); 
        while(gap > 0)
        {
            for(int z = 0; z < gap; z++)
            {
                for(int i = gap + z; i < len; i += gap)
                {
                    int j = i;
                    int temp = A[j];
                    while(j >= gap && A[j-gap] > temp)
                    {
                        A[j] = A[j-gap];    //move element j-gap right by gap
                        j -= gap;
                    }
                    A[j] = temp;
                }
            }
            gap = gap >> 1;
        }
    }

    //Returns a number in the form (2 power k) - 1
    private static int getGap(int len)
    {
        int n = 30;
        int gap;
        while(len >> n != 1)
        {
            n--;
        }
        gap = len & (1 << n);
        return gap - 1;
    }

    // mergeSort - front-end for kick-starting the recursive algorithm
    public static void mergeSort(int[] A)
    {
        mergeSortRecurse(A, 0, A.length-1);
    }//mergeSort()
    private static void mergeSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
        if(leftIdx < rightIdx)
        {
            int midIdx = (leftIdx + rightIdx) / 2;
            
            mergeSortRecurse(A, leftIdx, midIdx);
            mergeSortRecurse(A, midIdx + 1, rightIdx);

            merge(A, leftIdx, midIdx, rightIdx);
        }
    }//mergeSortRecurse()
    private static void merge(int[] A, int leftIdx, int midIdx, int rightIdx)
    {
        int[] tempArr = new int[rightIdx - leftIdx +1];
        int i = leftIdx; //Index for left sub array
        int j = midIdx + 1; //Index for right sub array
        int tempIdx = 0; //Index for temp array
        do
        {
            if(A[i] <= A[j])
            {
                tempArr[tempIdx] = A[i];
                i++;
            }
            else
            {
                tempArr[tempIdx] = A[j];
                j++;
            }
            tempIdx++;
        }while(i <= midIdx && j <= rightIdx);
        
        
        
        //flush remainder of left sub array
        for(int ii = i; ii <= midIdx; ii++)
        {
            tempArr[tempIdx] = A[ii];
            tempIdx ++;
        }
        
        //flush remainder of right sub array
        for(int jj = j; jj <= rightIdx; jj++)
        {
            tempArr[tempIdx] = A[jj];
            tempIdx++;
        }
        tempIdx = 0;
        //Copy contents of sorted temp array back to main array
        for(int kk = leftIdx; kk <= rightIdx; kk++)
        {
            A[kk] = tempArr[tempIdx];
            tempIdx++;
        }
    }//merge()


    // quickSort - front-end for kick-starting the recursive algorithm
    public static void quickSort(int[] A)
    {
        quickSortRecurse(A, 0, A.length-1);
    }//quickSort()
    private static void quickSortRecurse(int[] A, int leftIdx, int rightIdx)
    {
        if(leftIdx < rightIdx)
        {
            int pivotIdx = (leftIdx + rightIdx) / 2;
            int newPivotIdx = doPartitioning(A, leftIdx, rightIdx, pivotIdx);
            quickSortRecurse(A, leftIdx, newPivotIdx -1);
            quickSortRecurse(A, newPivotIdx + 1, rightIdx);
        }
    }//quickSortRecurse()
    private static int doPartitioning(int[] A, int leftIdx, int rightIdx, int pivotIdx)
    {
	    int pivot = A[pivotIdx];
        A[pivotIdx] = A[rightIdx];
        A[rightIdx] = pivot;
        int currIdx = leftIdx;
        for(int i = leftIdx; i <= rightIdx -1; i++)
        {
            if(A[i] < pivot)
            {
                int temp = A[i];
                A[i] = A[currIdx];
                A[currIdx] = temp;
                currIdx++;
            }
        }	
        
        int newPivotIdx = currIdx;
        A[rightIdx] = A[newPivotIdx];
        A[newPivotIdx] = pivot;
            
        return newPivotIdx;
    }//doPartitioning

    public static void countingSort(int[] A, int digit)
    {
        int[] count = new int[10];
        int[] output = new int[A.length];
        Arrays.fill(count, 0);
        for(int i = 0; i < A.length; i++)
        {
            ++count[getDigit(A[i], digit)];
        }
        for(int k = 1; k < count.length; k++)
        {
            count[k] = count[k] + count[k-1];
        }
        for(int o = 0; o < A.length; o++)
        {
            output[count[getDigit(A[o], digit)] - 1] = A[o];
            count[getDigit(A[o], digit)]--;
        }
        for(int p = 0; p < A.length; p++)
        {
            A[p] = output[p];
        }
    }

    public static void radixLSD10(int[] A)
    {
        int maxDigit = numDigits(getMax(A));
        for(int i = maxDigit; i >= 0 ; i--)
        {
            countingSort(A, i);
        }
    }
    
    private static int getMax(int[] A)
    {
        int max = 0;
        for(Integer e : A)
        {
            max = e > max ? e : max;
        }
        return max;

    
    private static int getDigit(int num, int digit)
    {
        int numDigits = numDigits(num); 
        int retval = 0;
        int div = 1;
        for(int i = 0; i < digit; i++)
        {
            div *= 10;
        }
        retval = num / div;
        retval = retval % 10;
        return retval;
    }

    private static int numDigits(int num)
    {
        return (int)(Math.log(num) / Math.log(10));
    }
}//end Sorts calss
