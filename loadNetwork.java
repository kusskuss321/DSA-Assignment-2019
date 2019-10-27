import java.io.*;

/**
 * Load the network trough the inputted file
 */
public class loadNetwork
{
    /**
     * reading file,create person object then store it inside network object
     * @param filename the filename
     * @param network the graph network object
     * @return network
     */
    public Network readFile(String filename,Network network)
    {
        FileInputStream fileInputStream = null;
        InputStreamReader inputStreamReader;
        BufferedReader bufferedReader;
        String line;
        int count;


        try
        {
            fileInputStream = new FileInputStream("C:\\Users\\Dicky Gultom\\Documents\\DSA_ASSIGNMENT\\src\\"+filename);
            inputStreamReader = new InputStreamReader(fileInputStream);
            bufferedReader = new BufferedReader(inputStreamReader);

            count = 0;
            line = bufferedReader.readLine();
            count+=1;
            if (line.contains(":"))
            {
                String [] split = line.split(":");
                network.addEge(split[0],split[1]);
            }
            else
            {
                network.addVertex(line);
            }
            while (line != null)
            {
                line = bufferedReader.readLine();
                count += 1;
                if (line != null)
                {
                    if (line.contains(":"))
                    {
                        String[] split = line.split(":");
                        network.addEge(split[1], split[0]);
                    }
                    else
                        {
                        network.addVertex(line);
                    }
                }
                else
                    {

                }
            }
            fileInputStream.close();
        }
        catch (IOException ex)
        {
            if (fileInputStream!=null)
            {
                try
                {
                    fileInputStream.close();
                }
                catch (IOException ex2)
                {

                }
            }
            else
                {
                System.out.println("There is no such file: " +filename);
            }
        }

        return network;

    }

    /**
     * Deserializing a network object file.
     * @param filename
     * @return linkedList
     * @throws IllegalArgumentException
     */

    public LinkedList readSerialisedNetwork(String filename) throws IllegalArgumentException
    {
        FileInputStream fileInputStream = null;
        ObjectInputStream objectInputStream;
        LinkedList linkedList=null;

        try
        {
            fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
            linkedList = (LinkedList)objectInputStream.readObject();

            objectInputStream.close();

        }
        catch (IOException ex)
        {
            if (fileInputStream != null)
            {
                try
                {
                    fileInputStream.close();
                }
                catch (IOException none)
                {

                }

            }
        }
        catch (ClassNotFoundException ex1)
        {
            System.out.println("Class Container not found " +ex1.getMessage());
        }
        catch (Exception ex2)
        {
            throw new IllegalArgumentException("Unable to load object from the file");
        }

        return linkedList;

    }
}
