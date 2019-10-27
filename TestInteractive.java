import jdk.jfr.MemoryAddress;

/**
 * This file tests the functionality of InteractiveMode.java class file
 */
public class TestInteractive
{

    public static void main (String [] args)
    {
        int count = 0;
        int passed = 0;
        InteractiveMode interactiveMode = new InteractiveMode();
        Network network = new Network();

        //test load network
        System.out.println("TESTING LOADING NETWORK: ");
        System.out.println();
        interactiveMode.loadNetwork(network);
        if (network == null)
        {
            System.out.println("FAILED");
        }
        else
        {
            System.out.println("PASSED");
            passed ++;
        }

        interactiveMode.displayStatistics(network);
        network.displayNetwork();
        count++;

        //test settingProbability
        System.out.println();
        System.out.println("TESTING SETTING PROBABILITY");
        System.out.println();
        String split[]= interactiveMode.setProbabilities().split(",");
        if (split[0] == null ||split[1] ==null)
        {
            System.out.println("FAILED");
        }
        else
        {
            System.out.println("PASSED");
            passed++;
        }
        count++;

        //TEST NODE OPERATIOONS
        System.out.println();
        System.out.println("TESTING NODE PROBABILITY");
        System.out.println();
        network = interactiveMode.NodeOperations(network);
        count++;
        passed++;

        //TEST NEW POST
        System.out.println();
        System.out.println("TESTING Posting PROBABILITY");
        System.out.println();
        interactiveMode.NewPost(network);

        //TEST EDGE OPERATIONS
        System.out.println();
        System.out.println("TESTING EDGE PROBABILITY");
        System.out.println();
        interactiveMode.EdgeOperation(network);


        //Test Update
	    System.out.println();
	    System.out.println("TESTING UPDATE OPERATIONS");
	    System.out.println();
	    interactiveMode.Update(network,Double.parseDouble(split[1]),Double.parseDouble(split[0]));
        network.displayNetwork();
        interactiveMode.displayStatistics(network);

        //Test saving network
        System.out.println();
        System.out.println("TESTING SAVING OPERATIONS");
        System.out.println();
        interactiveMode.saveNetwork(network);

    }
}
