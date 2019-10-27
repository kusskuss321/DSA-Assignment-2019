import java.io.Serializable;
import java.util.Iterator;

/**
 * The nodes in the graph adt
 */
public class Person implements Serializable
{
    /**The list of person a person follow**/
    private LinkedList links;
    /** The name of the person**/
    private String label;
    /** Used for Traversing the graph**/
    private boolean visited;
    /**Creating a postTimeline Object**/
    private Post postTimeline;
    /**To keep track the number of followers**/
    int followersCount;
    /** List of Followers**/
    followersList listFollow;

    /**
     * Default constructor
     * @param inLabel the person name
     */
    public Person(String inLabel)
    {
        this.links =  new LinkedList();
        this.label = inLabel;
        this.visited= false;
        this.postTimeline = new Post(3);
        this.followersCount = 0;
        this.listFollow = new followersList(3);
    }

    /**
     * The person will make a new post
     * @param timeStamp the time the post was created
     * @param post the content of the post
     */
    public void makePost(String timeStamp, String post, int postfactor)
    {
        postNode newPost = new postNode(timeStamp,post);
        if (postfactor != 0)
        {
            newPost.setBait();
            newPost.setBaitCount(postfactor);
        }
        postTimeline.addPost(newPost);
    }

    /**
     * Getting the latest post the person post
     * @return postTimeline.getLatestpost()
     */
    public postNode latestPost()
    {
        return postTimeline.getLatestpost();
    }

    /**
     * increase the number of follower by increment of 1
     */
    public void increaseFollowers()
    {
        followersCount++;
    }

    /**
     * decrease the number of follower by increment of 1
     */
    public void decreaseFollowers()
    {
        followersCount--;
    }

    /**
     * Getting the number of post the person make
     * @return postTimeline.getCount();
     */
    public int postCount()
    {
        return postTimeline.getCount();
    }

    /**
     * Getting the whole post array
     * @return postTimeline.getArrayPost();
     */

    public postNode[] getpostNodes ()
    {
        return postTimeline.getArrayPost();
    }

    /**
     * Getting the followers count
     * @return followersCount
     */
    public int getFollowersCount()
    {
        return listFollow.getCount();
    }

    /**
     * Getting the Post object
     * @return postTimeline
     */
    public Post getPostTimeline()
    {
        return postTimeline;
    }

    /**
     * set visited to true
     */
    public void setVisited()
    {
        this.visited = true;
    }

    /**
     * get the boolean expression of visited
     * @return visited
     */
    public boolean getVisited()
    {
        return this.visited;
    }

    /**
     * get the name of the person
     * @return label
     */

    public String getLabel()
    {
        return this.label;
    }
    /**
     * get the list of following people
     * @return this.links
     */

    public LinkedList getAdjacent()
    {
        return this.links;
    }

    /**
     * clearing visited boolean. Setting it to false
     */

    public void clearVisited()
    {
        this.visited = false;
    }

    /**
     * Add connection between a person and other peson
     * @param label the name of the person to follow
     * @param vertex the person object
     */

    public void addEdge(String label, Person vertex)
    {
        if (!linkExist(vertex))
        {
            links.insertLast(label,vertex);
        }
    }


    public void addFollowers(Person person)
    {
        this.listFollow.addFollowers(person);
    }

    public void removeFollowers(Person person)
    {
        this.listFollow.removeFollowers(person);
    }

    public Person[] getListFollow()
    {
        return this.listFollow.getFollowList();
    }

    /**
     * Remove connection between a person and another person
     * @param name the name of the person
     */

    public void unFollow(String name)
    {
        links.remove(name);
    }

    /**
     * checking if the connection between two people exist previously
     * @param vertex the person object
     * @return exist
     */

    public boolean linkExist(Person vertex)
    {
        boolean exist = false;
        Iterator iterator = links.iterator();
        while (iterator.hasNext())
        {
            Person node =(Person)iterator.next();
            if (node.getLabel() == vertex.getLabel())
            {
                exist = true;
            }
        }

        return exist;
    }

    /**
     * checking if the list of following has the same person
     * @param comparator the person to compare
     * @return exist
     */

    public boolean hasVertex(Person comparator)
    {
        boolean exist = false;
        Iterator graphNodeIterator = links.iterator();
        while (graphNodeIterator.hasNext())
        {
            Person node = (Person)graphNodeIterator.next();
            if (node.getLabel() == comparator.getLabel())
            {
                exist = true;
            }
        }

        return exist;
    }

    /**
     * just to string method
     * @return label
     */
    public String toString()
    {
        return label;
    }

}
