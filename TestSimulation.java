/**
 * This class file is responsible for testing the functionality of simulationMode.java classfile.
 */

public class TestSimulation
{
    public static void main(String[] args) {

        simulationMode simulationMode = new simulationMode();
        Network network = new Network();

        String networkFile = "network1";
        String eventFile = "events1";
        simulationMode.loadNetwork(network,networkFile);
        network.displayNetwork();
        simulationMode.readEvent(network,eventFile);

        network.displayNetwork();
    }
}
