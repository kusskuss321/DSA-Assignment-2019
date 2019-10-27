import java.util.*;

/**
 * This class file is the main class file in this project.
 */
public class SocialSim
{
    /**
     * Showing the user to how to use the command line
     * @return usage
     */
    private static String DefaultUsage()
    {
        String usage;

        usage = "Command Line argument \n" +
                "-i : Run the program in interactive mode\n" +
                "-s : Run the program in simulation mode\n";

        return usage;
    }

    /**
     * showing the user to how to use the simulation
     * @return usage
     */
    private static String simulationUsage()
    {
        String usage;
        usage = "Need more command arguments\n" +
                "Expected argument : java SocialSim -s [networkFile] [eventFile] [liking probability] [follow probability]";

        return usage;
    }

    /**
     * the Main method
     * @param args command line arguments
     */
    public static void main (String [] args)
    {
        if (args.length < 1)
        {
            System.out.println(DefaultUsage());
        }
        else
        {
            if (args[0] == "-i")
            {
                interactiveMode();
            }
            else if (args[0] == "-s")
            {
                if (args.length<5)
                {
                    System.out.println(simulationUsage());
                }
                else if (args.length > 6)
                {
                    System.out.println(simulationUsage());
                }
                else
                {
                    simulationMode(args[1],args[2],Double.parseDouble(args[3]),Double.parseDouble(args[4]));
                }
            }
        }
    }

    /**
     * The method that implement simulation mode of the program
     * @param networkfile the network filename
     * @param eventfile the event filename
     * @param liking the liking probability
     * @param follow the follow probability
     */
    private static void simulationMode(String networkfile, String eventfile, double liking, double follow)
    {
        Network network = new Network();

        simulationMode simulationMode = new simulationMode();
        //first load the network file
        simulationMode.loadNetwork(network,networkfile);
        simulationMode.readEvent(network,eventfile);
        simulationMode.Simulation(network,liking,follow);
    }

    /**
     * The method that implement the interactive mode of the program
     */
    private static void interactiveMode()
    {
        InteractiveMode interactiveMode = new InteractiveMode();

        Network network = new Network();

        double followProbability = 0.0, likeProbability=0.0;

        int menu;
        Scanner sc = new Scanner(System.in);
        System.out.println("============================================");
        System.out.println("WELCOME TO SOCIAL SIMULATOR INTERACTIVE MODE\n");
        System.out.println("============================================");
        do
            {
            System.out.println("Interactive Menu:\n" +
                    "1: Load Network\n" +
                    "2: Set Probabilities\n" +
                    "3: Node Operations\n" +
                    "4: Edge Operations (Like & Follow )\n" +
                    "5: New Post\n" +
                    "6: Display Network\n" +
                    "7: Display Statistics\n" +
                    "8: Update - Running a timestep\n" +
                    "9: Save Network\n" +
                    "10: Exit\n");
            menu = sc.nextInt();
            switch (menu)
            {
                case 1:
                    interactiveMode.loadNetwork(network);
                    break;
                case 2:
                    String probability = null;
                    probability=interactiveMode.setProbabilities();
                    String [] split = probability.split(",");

                    likeProbability = Double.parseDouble(split[0]);
                    followProbability = Double.parseDouble(split[1]);
                    break;
                case 3:
                    interactiveMode.NodeOperations(network);
                    break;
                case 4:
                    interactiveMode.EdgeOperation(network);
                    break;
                case 5:
                    interactiveMode.NewPost(network);
                    break;
                case 6:
                    interactiveMode.displayNetwork(network);
                    break;
                case 7:
                    interactiveMode.displayStatistics(network);
                    break;
                case 8:
                    interactiveMode.Update(network,followProbability,likeProbability);
                    break;
                case 9:
                    interactiveMode.saveNetwork(network);
                    break;

                case 10:
                    System.out.println("PROGRAM TERMINATED");
                    break;
                default:
                    System.out.println("Invalid Input");
            }
        }
        while (menu > 0 && menu < 10);
    }
}
