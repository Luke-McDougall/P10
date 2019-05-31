import java.io.*;
import java.util.*;
public class HashTest
{
    public static void main(String[] args)
    {
        HashMap<String, Integer> h = new HashMap<>();
        boolean success = true;
        char c = 'a';
        for(int i = 0; i < 26; i++)
        {
            String entry = Character.toString(c + i);
            h.put(entry, i + 1);
            int size = h.size();
            System.out.printf("Size: %d\n", size);
            if(h.get(entry) != i + 1)
            {
                success = false;
            }
        }

        System.out.println("Test 1 was a " + (success ? "success" : "failure"));
        
        success = true;

        for(int k = 1; k <= 11; k++)
        {
            
            try
            {
                int size = h.size(); 
                System.out.printf("Size: %d\n", size);
                h.remove("test");
                System.out.printf("%d removed.\n", k);
            }
            catch(NoSuchElementException e)
            {
                System.out.println("Expecting one exception thrown. " + e.getMessage());
            }
        }

        for(int p = 0; p < 26; p++)
        {
            String entry = Character.toString(c + p);
            int size = h.size();
            System.out.printf("Size: %d\n", size);
            h.remove(entry);
        }
    
        System.out.println("Hash map " + (h.containsKey("test") ? "contains test" : "does not contain test"));

        System.out.printf("Hash table has size: %d\n", h.size());

        System.out.println("Testing randomNames7000.csv");
        HashMap<String, Integer> t = new HashMap<>();
        readCSV(t);
    }

    public static void readCSV(HashMap<String, Integer> t)
    {
        String line = null;
		FileInputStream fileIn = null;
		InputStreamReader rdr = null;
		BufferedReader bufrdr = null;
		try
		{
			fileIn = new FileInputStream("RandomNames7000.csv");
			rdr = new InputStreamReader(fileIn);
			bufrdr = new BufferedReader(rdr);
			line = bufrdr.readLine();
            int i = 0;
			while(line != null)
			{
			    String[] p = line.split(",");
                t.put(p[1], Integer.parseInt(p[0]));	
				line = bufrdr.readLine();
                i++;
			}
            System.out.println(i);
			fileIn.close();
			
		}
		catch(IOException e)
		{
			if(fileIn != null)
			{
				try
				{
					fileIn.close();
				}
				catch(IOException e2) {}
			}
			System.out.println("Error in file processing: " + e.getMessage());
		}
    }
}
