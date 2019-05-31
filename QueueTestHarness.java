import java.util.*;

public class QueueTestHarness
{
    public static void main(String[] args)
    {
        Queue<Integer> q = new LinkedList<Integer>();
        for(int i = 0; i < 10; i++)
        {
            q.offer(i);
        }

        if(q.size() != 10) 
        {
            System.out.printf("Error: Incorrect size. Expected size %d, returned size %d", 10, q.size());
        }

        boolean valid = true;
        for(int i = 0; i < 10; i++)
        {
            int d = q.poll();
            if(d != i)
            {
                valid = false;
            }
        }
        if(q.size() != 0) {
            System.out.println("Error: Incorrect size.");
        }
        System.out.println("The test was a" + (valid ? " success" : " failure"));
    }
}
