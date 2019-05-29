public class shelltest
{
    public static void main(String[] args)
    {
        int[] A = new int[30];
        for(int i = 0; i < A.length; i++)
        {
            A[i] = (int)(Math.random() * A.length);
        }

        Sorts.shellSort(A);

        for(Integer e : A)
        {
            System.out.println(e);
        }
    }
}
