public class shelltest
{
    public static void main(String[] args)
    {
        int[] A = new int[30];
       // int numba = Integer.parseInt(args[0]);
        //int digit = Integer.parseInt(args[1]);
        //int RANGE = 10;
        for(int i = 0; i < A.length; i++)
        {
            A[i] = (int)(Math.random() * A.length);
        }

        int[] B = A.clone();
        Sorts.radixLSD10(A);
        int[] C = B.clone();

        Sorts.bubbleSort(C);

        boolean valid = true;
        for(int yingd = 0; yingd < B.length; yingd++)
        {
            if(A[yingd] != C[yingd])
            {
                valid = false;
            }
        }

        System.out.println("It was a " + (valid ? "success" : "failure"));
        
        for(int flimb = 0; flimb < A.length; flimb++)
        {
            System.out.printf("Olde = %d, newe = %d\n", B[flimb], A[flimb]);
        }

        //System.out.printf("%d %dth digit = %d.\n", numba, digit, getDigit(numba, digit)); 
    }

    private static int getDigit(int num, int digit)
    {
        int numDigits = (int)Math.floor(Math.log(num) / Math.log(10));
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
}
