import java.io.File;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
/**
This class file is responsible for reading the event file supplied through command line parametesrs
 **/
public class readEvent
{
    /**
     * reading the event file for simulation mode.
     * @param network the network graph object
     * @param filename the event file object
     */
    public void readEventFile(Network network, String filename)
    {
        File eventFile;
        Scanner readLine;
        String line;

        try {
            eventFile = new File("C:\\Users\\Dicky Gultom\\Documents\\DSA_ASSIGNMENT\\src\\"+filename);
            readLine = new Scanner(eventFile);

            while (readLine.hasNext())
            {
                String [] split =readLine.nextLine().split(":");
                String [] newSplit = new String[4];
                for (int i = 0; i<split.length;i++)
                {
                    newSplit[i]=split[i];
                }
                //System.out.println(Arrays.toString(newSplit));
                processEvent(network,newSplit);
            }
            readLine.close();
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("Filename: " +filename+ " does not exist! Try again");

        }
    }

    /**
     * process the event line
     * @param network network object
     * @param eventLine event line
     */
    private void processEvent(Network network,String [] eventLine)
    {
        switch (eventLine[0].toUpperCase())
        {
            case "A":
                //THIS WILL CREATE A NEW NODE AKA VERTEX
                String name = eventLine[1];
                network.addVertex(name);
                break;

            case "F":
                //LENGTH OF 3 | 0 - IDENTIFIER | 1 - PERSON TO FOLLOW | 2 - ORIGINAL
                String personToFollow = eventLine[1];
                String original = eventLine[2];
                network.addEge(original,personToFollow);
                System.out.println(original + " follow " + personToFollow);
                break;

            case "P":
                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date date = new Date();
                //LENGTH OF 4 OR 3 DEPENDS | 0 - IDENTIFIER | 1 - PERSON WHO MAKE THE POST | 2 - POST | 3 - CLICKBAIT FACTOR
                String poster = eventLine[1];
                String post = eventLine[2];
                int factor;
                if (eventLine[3]==null)
                {
                    factor = 0;
                }
                else
                {
                    factor = Integer.parseInt(eventLine[3]);
                }
                Person person = network.getVertex(poster);
                person.makePost(formatter.format(date),post,factor);
                System.out.println("Post made by : " +person.getLabel() + " with post : " + post);
                break;

                //incase REMOVE AND UNFOLLOW EXIST;
            case "R":
                String toRemove = eventLine[1];
                System.out.println("Removing : " +toRemove);
                network.removeNode(toRemove);
                break;

            case "U":
                String or = eventLine[2];
                String toUnfollow = eventLine[1];
                network.unfollow(or,toUnfollow);
                break;

            default:
                break;

        }
    }
}
