import java.io.Serializable;
import java.util.Arrays;

/**
 * Post class holds all the posts for a person. It is implemented in Stack ADT.
 */
public class Post implements Serializable
{
    /**The upper bound of the array content load factor**/
    private static final double UPPER_BOUND = 0.70;
    /** the postNode array**/
    private postNode [] arrayPost;
    /**to keep track of post **/
    private int count;

    /**
     * default constructor
     * @param size the initial size of the array
     */
    public Post(int size)
    {
        this.arrayPost = new postNode[findNextPrime(size)];
        this.count =0;
    }

    /**
     * getter for count
     * @return count
     */

    public int getCount() {
        return count;
    }

    /**
     * getter for the arraypost array
     * @return arrayPost
     */

    public postNode[] getArrayPost()
    {
        return arrayPost;
    }

    /**
     * get the last post the person made
     * @return postNode
     */

    public postNode getLatestpost()
    {
        return arrayPost[count];
    }

    /**
     * add a new post to the array
     * @param newPost the postNode contains all about the post information
     */
    public void addPost(postNode newPost)
    {
        if (getLoadFactor()<UPPER_BOUND)
        {
            this.arrayPost[count]=newPost;
            count++;
        }
        else
        {
            //resize it
            resizeArray(2*arrayPost.length);
            this.arrayPost[count]=newPost;
            count++;
        }
    }

    /**
     * resizing the post array
     * @param resize the size of the array
     * @return arrayPost
     */
    private postNode[] resizeArray(int resize)
    {
        int findnewPrime = findNextPrime(resize);

        arrayPost = Arrays.copyOf(arrayPost,findnewPrime);

        return arrayPost;
    }

    /**
     * get the load factor of the array
     * @return count/arrayPost.length;
     */
    private double getLoadFactor()
    {
        return count/arrayPost.length;
    }

    /**
     * finding the next prime number
     * @param startVal starting value
     * @return prime
     */
    private int findNextPrime(int startVal)
    {
        int prime;
        int ii;
        if(startVal % 2 == 0)
        {
            prime = startVal - 1;
        }
        else
        {
            prime = startVal;
        }

        boolean isPrime = false;
        do
        {
            prime = prime + 2;
            ii = 3;
            isPrime = true;
            double rootVal = Math.sqrt(prime);
            do
            {
                if(prime % ii == 0)
                {
                    isPrime = false;
                }
                else
                {
                    ii += 2;
                }
            }
            while(ii <= rootVal && isPrime);
        }
        while(!isPrime);
        return prime;
    }
}
