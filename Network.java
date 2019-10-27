import javax.swing.*;
import java.awt.*;
import java.io.Serializable;
import java.util.Iterator;

/**
 * Graph Abstract Data Type implemented using Linked list
 */
public class Network implements Serializable
{
    /**Linked List Object **/
    LinkedList vertices;
    /**To keep track the amount of edges **/
    int edgeCount;
    /**To keep tract the amount of vertex**/
    int vertexcount;

    /**
     * Default Constructor
     */
    public Network()
    {
        this.vertices = new LinkedList();
        this.edgeCount = 0;
        this.vertexcount = 0;
    }

    /**
     * Creating a new person Object.
     * @param label the Person's name
     */
    public void addVertex(String label)
    {
        Person node = new Person(label);
        if (!vertexExist(node))
        {
            addNewVertex(node);

        }
    }

    /**
     * add the newly created person object to the tail node of the linked list
     * @param vertex person object
     */
    private void addNewVertex(Person vertex)
    {
        vertices.insertLast(vertex.getLabel(),vertex);
        vertexcount++;
    }

    /**
     * Iterating through the linked list to see if the same person is inside the linked lsit
     * @param vertex
     * @return exist
     */
    public boolean vertexExist(Person vertex)
    {
        boolean exist = false;
        Iterator iterator = vertices.iterator();
        while (iterator.hasNext())
        {
            if (iterator.next().equals(vertex))
            {
                exist = true;
            }
        }

        return exist;
    }

    /**
     *  A method to execute unfollow.
     * @param name1 the person deciding to unfollow
     * @param name2 the person to unfollow
     */
    public void unfollow(String name1, String name2)
    {
        Person original = getVertex(name1);
        Person toUnfollow = getVertex(name2);
        if (original!=null)
        {
            original.unFollow(name2);
            toUnfollow.decreaseFollowers();
            toUnfollow.removeFollowers(original);
        }
        original.decreaseFollowers();
    }

    public void removeNode(String name)
    {
        Person target = getVertex(name);
        if (target!=null)
        {
            //make sure everyone follow target unfollow him first
            for (Object o: vertices)
            {
                Person current = (Person)o;
                for (Object followingList: current.getAdjacent())
                {
                    Person isTarget = (Person)followingList;
                    if (isTarget.getLabel().equals(name))
                    {
                        unfollow(current.getLabel(),target.getLabel());
                    }
                }
            }
            vertices.remove(name);
            vertexcount--;
        }
	    else
	    {
		    System.out.println(name + " Does not exist");
	    }
    }
    /**
     * Returning the linked list
     * @return vertices
     */

    public LinkedList getVertices()
    {
        return vertices;
    }

    /**
     * add Edge connection between two people
     * @param inLabel1 the person wants to connect
     * @param inLabel2 the person to connect
     */
    public void addEge(String inLabel1, String inLabel2)
    {
        Person node1 = getVertex(inLabel1);
        Person node2 = getVertex(inLabel2);

        if (node1 == null)
        {
            addVertex(inLabel1);
        }

        if (node2 == null)
        {
            addVertex(inLabel2);
        }

        //now two nodes exist
        Person personwants = getVertex(inLabel1);
        Person personto = getVertex(inLabel2);

        //one direction graph
        personwants.addEdge(personto.getLabel(),personto);
        personto.addFollowers(personwants);
        personto.increaseFollowers();
        edgeCount++;
    }


    /**
     * Accessor
     * @return edgeCount
     */
    public int getEdgeCount() {
        return edgeCount;
    }

    /**
     * Accessor
     * @return vertexcount
     */
    public int getVertexcount()
    {
        return vertexcount;
    }

    /**
     * Searching for the desired person and returning it
     * @param label the person's name
     * @return nodes
     */
    public Person getVertex(String label)
    {
        Iterator graphNodeIterator = vertices.iterator();
        Person nodes = null;
        while (graphNodeIterator.hasNext())
        {
            Person node = (Person)graphNodeIterator.next();
            if (label.equals(node.getLabel()))
            {
                nodes = node;
            }
        }
        return nodes;
    }

    /**
     * get the linked list of the people a person following
     * @param label
     * @return
     */
    public LinkedList getAdjacent(String label)
    {
        LinkedList adjacent = null;
        Iterator graphNodeIterator = getVertex(label).getAdjacent().iterator();
        while (graphNodeIterator.hasNext())
        {
            if (graphNodeIterator.next().equals(getVertex(label)))
            {
                adjacent = getVertex(label).getAdjacent();
            }
        }

        return adjacent;
    }

    /**
     * Output list of people inside the linked lsit
     */
    public void output()
    {
        for (Object o: vertices)
        {
            Person newPerson = (Person)o;

            System.out.println(newPerson.toString());
        }
    }

    /**
     * Displaying the network in Adjacency Matrix form
     */
    public void displayNetwork()
    {
        if (vertices==null)
        {
            System.out.println("The network graph is empty" +
            ".");
        }
        else
            {
            System.out.println("Displaying Network");
            int[][] matrix = new int[vertexcount][vertexcount];
            allZero(matrix);
            int i = 0;
            Iterator iterator = vertices.iterator();
            while (i < matrix.length) {
                int j = 0;
                Person vertex = (Person)iterator.next();
                while (j < matrix[i].length) {
                    for (Object comp : vertices) {
                        if (vertex.hasVertex((Person) comp)) {
                            matrix[i][j] = 1;
                        }
                        j++;
                    }
                }
                i++;
            }
            printMatrix(matrix);
        }

    }

    /**
     * A supporting method for displayNetwork method
     * @param matrix a 2d array
     */
    private void printMatrix(int [][] matrix)
    {
        LinkedListNode nodePerson = vertices.getHead();
        System.out.print("\t\t\t[ ");
        for (Object o: vertices)
        {
            Person obj = (Person)o;
            System.out.print(obj.getLabel()+ " ");
        }
        System.out.print("\t]");
        System.out.println();
        for (int arr[]: matrix)
        {

            System.out.print(nodePerson.getKey()+"\t");
            nodePerson = nodePerson.getNext();
            System.out.print("[\t");
            for (int val : arr)
            {
                System.out.print(val + " \t\t");
            }
            System.out.print("\t]");
            System.out.println();
        }
    }

    /**
     * initialised all elements in 2d matrix to be 0
     * @param matrix 2d integer matrix
     * @return matrix
     */
    private int [][] allZero(int [][] matrix)
    {
        for (int arr[] : matrix)
        {
            for (int val : arr)
            {
                val = 0;
            }
        }

        return matrix;
    }

    /**
     * Outputting the Adjacency Matrix into a JFX.
     * @param matrix 2d array
     */
    private void displayJtableNetwork(int [][] matrix)
    {
        Object [][] obj = new Object[matrix.length][matrix.length];
        for (int i=0;i<obj.length;i++)
        {
            for (int k = 0; k<obj[i].length; k++)
            {
                obj[i][k]= matrix[i][k];
            }
        }
        JFrame frame = new JFrame("Network Adjacency Matrix");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = frame.getContentPane();
        Object col [] = new Object[matrix.length];
        int i=0;
        for(Object o: vertices)
        {
            Person person = (Person)o;
            col[i]=person.getLabel();
            i++;
        }
        JTable table = new JTable(obj,col);
        JScrollPane scrollPane = new JScrollPane(table);
        container.add(scrollPane,BorderLayout.CENTER);
        frame.setSize(300,200);
        frame.setVisible(true);
    }

    /**
     * Creating a hash code using the name of the person
     * @param name the person name
     * @return result
     */
    public int hashCode(String name) {
        int result=0;
        for (int i =0; i<name.length(); i++)
        {
            result+=name.charAt(i);
        }
        return result;
    }
}
