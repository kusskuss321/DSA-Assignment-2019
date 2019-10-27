/**
 * This file is responsible for Simulation Mode.
 */

public class simulationMode
{

    public Network loadNetwork(Network network,String filename)
    {
        loadNetwork loadNetwork = new loadNetwork();

        loadNetwork.readFile(filename,network);

        return network;
    }

    public Network readEvent(Network network, String EventFileName)
    {
        readEvent eventFile = new readEvent();

        eventFile.readEventFile(network,EventFileName);
        return network;
    }

    public void Simulation(Network network, double likeProb, double followProb)
    {
        InteractiveMode interactiveMode = new InteractiveMode();
        interactiveMode.Update(network,followProb,likeProb);
    }

}
