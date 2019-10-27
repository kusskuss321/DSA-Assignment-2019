import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

/**
 * This class file contains all the methods for Interactive Mode
 */
public class InteractiveMode
{
    /**Constant for comparing floating points**/
    private static final double TOL = 0.0001;

    /**
     * Takes network object as an input, prompting the user to enter the filename. Each line read, a person object created. These person objects then stored in network
     * object.
     * @param graph The graph network object
     * @return graph
     */
    public Network loadNetwork(Network graph)
    {
        Scanner sc = new Scanner(System.in);

        String filename;
        loadNetwork network = new loadNetwork();

        System.out.println("To load the network, please enter the filename");
        filename = sc.nextLine();

        network.readFile(filename,graph);

        return graph;
    }

    /**
     * Prompting the user to enter the desired probabilities for follow and like. Validate every inputs. The floating point cannot exceed 1 and fall below 0.
     * @return probabilities
     */

    public String setProbabilities()
    {
        double p1,p2;
        String probabilities;
        Scanner sc = new Scanner(System.in);
        System.out.println("This setting set the initial liking probability and follow probability");
        System.out.println("The default initial probability for both event is 0");
        System.out.print("Please enter the probability to like: ");
        p1=sc.nextDouble();

        while(p1 > 1.00 || p1< 0.00)
        {
            if (p1 < 0.00)
            {
                System.out.println("Invalid Probability. Probability cannot fall below 0");
                System.out.print("Please enter the probability to like: ");
                p1=sc.nextDouble();
            }
            else if (p1> 1.00)
            {
                System.out.println("Invalid Probability. Probability cannot exceed 1");
                System.out.print("Please enter the probability to like: ");
                p1=sc.nextDouble();
            }
        }

        System.out.print("Please enter the probability to follow: ");
        p2=sc.nextDouble();
        while(p2 > 1.00 || p2 < 0.00)
        {
            if (p2 < 0.00)
            {
                System.out.println("Invalid Probability. Probability cannot fall below 0");
                System.out.print("Please enter the probability to like: ");
                p2=sc.nextDouble();
            }
            else if (p2 > 1.00)
            {
                System.out.println("Invalid Probability. Probability cannot exceed 1");
                System.out.print("Please enter the probability to like: ");
                p2=sc.nextDouble();
            }
        }

        probabilities = p1 + "," +p2;

        return probabilities;
    }

    /**
     * Prompting the user to choose which node operations they would like to carry.
     * @param graph The graph network object
     * @return graph
     */

    public Network NodeOperations(Network graph)
    {
        Scanner sc = new Scanner(System.in);
        Scanner input = new Scanner(System.in);
        int menu;
        String name;
        do
            {

            System.out.println("Please choose which operation you want to carry:\n" +
                    "1. Find User\n" +
                    "2. Insert New Person\n" +
                    "3. Remove a Person\n" +
                    "4. Exit");
            menu = sc.nextInt();
            switch (menu)
            {
                case 1:
                    System.out.println("Please enter the name of the user to find: ");
                    Person person;
                    name = input.nextLine();
                    person = graph.getVertex(name);
                    if (person == null)
                    {
                        System.out.println(name + " Does Not Exist");
                    }
                    else
                    {
                        System.out.println(name + " Exist");
                    }
                    break;
                case 2:
                    System.out.println("To create a new user, please enter the name of the user");
                    name = input.nextLine();
                    graph.addVertex(name);
                    break;
                case 3:
                    System.out.println("Please enter the name of the person to remove");
                    name = input.nextLine();
                    graph.removeNode(name);


                    break;
                case 4:
			System.out.println("Exiting Node Operations");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        } while (menu > 0 && menu < 4);

        return graph;

    }

    /**
     * Prompting the user to choose which edge operations they want to carry.
     * @param graph The graph network object
     */

    public void EdgeOperation(Network graph)
    {
        Scanner sc = new Scanner(System.in);
        int menu;
        String name1,name2;

        do {
            System.out.println("Please choose which operation you want to carry:\n" +
                    "1. Follow\n" +
                    "2. Unfollow \n" +
                    "3. Exit");
            menu = sc.nextInt();
            switch (menu)
            {
                case 1:
                    System.out.println("Enter the first person name");
                    name1 = sc.next();
                    System.out.println("Enter the name of the person that " +name1+ " wants to follow");
                    name2 = sc.next();
                    graph.addEge(name1,name2);
                    break;
                case 2:
                    System.out.println("Enter the name of the person:");
                    name1 = sc.nextLine();
                    System.out.println("Enter the name of the person that " +name1+ " trying to unfollow:");
                    name2 = sc.nextLine();
                    graph.unfollow(name1,name2);

                    break;
                case 3:
                    break;
                default:
                    System.out.println("Invalid Input");

            }
        }while (menu>0 && menu < 3);
    }

    /**
     * Creating a post for a person in the network based on user choice.
     * @param graph The graph network object
     */

    public void NewPost(Network graph)
    {
        Scanner sc = new Scanner(System.in);
	Scanner input = new Scanner(System.in);
        String post,name;
        int baitfactor;
        System.out.println("Please enter person full name\n");
        name = sc.nextLine();

        Person person = graph.getVertex(name);
        name = person.getLabel();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
        Date date = new Date();
        if (name != null)
        {
            System.out.println("Please enter the post the person " + name + " going to make:");
            post = sc.nextLine();
            System.out.println("Please enter the bait factor");
            baitfactor = input.nextInt();
            person.makePost(formatter.format(date),post,baitfactor);
        }
    }

    /**
     * Displaying the network in adjacency matrix.
     * @param network The graph network object
     */

    public void displayNetwork(Network network)
    {
        System.out.println("Displaying Social Network as Adjacency Matrix");
        network.displayNetwork();
    }

    /**
     * Will display the statistics of every person inside the network.
     * @param network The graph network object
     */

    public void displayStatistics(Network network)
    {
        for (Object o: network.getVertices())
        {
            Person person = (Person)o;
            System.out.println("Person : "+ person.getLabel());
            System.out.println("Total Followers: " +person.getFollowersCount());
            System.out.println("Total Post: " +postCount(person));
            System.out.println("The most popular post: ");
            postNode post = getPopularPost(person);
            if(post!=null)
            {
                System.out.println(getPopularPost(person).toString());
            }
            System.out.println("Followers List: ");
            System.out.println(Arrays.toString(person.getListFollow()));
            System.out.println();
        }

    }

    /**
     * Will count how many post a person have.
     * @param person Person Object
     * @return
     */
    private int postCount(Person person)
    {
        return person.postCount();
    }

    /**
     * Get the most popular post a person has.
     * @param node person Object
     * @return
     */

    private postNode getPopularPost(Person node)
    {
        postNode popularpost = null, temp=null;
        int mostLikes;
        mostLikes = 0;
        for (int i=0; i<node.postCount(); i++)
        {
            temp = node.getpostNodes()[i];
            if(temp.getLikesCount()>=mostLikes)
            {
                popularpost = temp;
            }
        }
        return popularpost;
    }

    /**
     *  Will run a timestep. Each time step, there is a chance that other person in the network to follow and unfollow other person and liking their post.
     * @param network The network object
     * @param follow The follow probability
     * @param like The liking probability
     */

    public void Update(Network network, double follow, double like)
    {
        long now, prev,diff;
        now = 0;
        prev =0;
        diff = 0;
        char type;
        Scanner input = new Scanner(System.in);
        update update = new update();

        do
            {
                System.out.println("Timestep: "+diff);
                /** do random things**/
                update.landing(network,follow,like);
                prev = System.currentTimeMillis()/1000L;
                System.out.println("Enter C to continue. Any character to exit");
                type =input.next().toUpperCase().charAt(0);
                now = System.currentTimeMillis()/1000L;
                diff = now - prev;
        }while (type == 'C');

    }

    /**
     * Saving the network object through serialization method.
     * @param network The network Object
     */
    public void saveNetwork(Network network)
    {
        try
        {
            FileOutputStream file = new FileOutputStream("output.txt");
            ObjectOutputStream outputStream = new ObjectOutputStream(file);

            outputStream.writeObject(network);

            outputStream.close();
            file.close();

            System.out.println("Network has been serialized. The output file is output.txt");
        }
        catch (IOException ree)
        {
            System.out.println("Something bad happen :(");
        }

    }

}
