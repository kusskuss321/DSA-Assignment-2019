import java.io.Serializable;
import java.util.Arrays;

/**
 * This class holds all the list of followers for a person.
 */
public class followersList implements Serializable
{
    /**Upper Bound Array Content Factor**/
    private static final double UPPER_BOUND = 0.70;
    /** Array of followList **/
    private Person [] followList;
    /** counting purposes **/
    private int count;

    /**
     * Constructor
     * @param size the initial size of the array
     */
    public followersList(int size)
    {
        this.followList = new Person[findNextPrime(size)];
        this.count = 0;
    }

    /**
     * Adding the person to the list of followers
     * @param obj the person to be added into the array
     */
    public void addFollowers(Person obj)
    {
        if (loadFactor()<UPPER_BOUND)
        {
            followList[count]=obj;
            count++;
        }
        else
        {
            followList=resize();
            followList[count]=obj;
            count++;
        }
    }

    public void removeFollowers(Person obj)
    {
        int index=0;
        for (int i=0; i<count; i++)
        {
            if (obj.getLabel().equals(followList[i].getLabel()))
            {
                followList[i]=null;
                index = i;
            }
        }
        for (int x = index+1; x<count; x++)
        {
            followList[x-1]=followList[x];
        }

    }

    /**
     * Resizing the array
     * @return followList
     */

    private Person [] resize()
    {
        return Arrays.copyOf(followList,findNextPrime(2*followList.length));
    }

    public int getCount() {
        return count;
    }

    /**
     * Calculate the Load factor of the array
     * @return The load factor of the array
     */
    private double loadFactor()
    {
        return count/followList.length;
    }

    /**
     * accessor
     * @return the array of list
     */
    public Person[] getFollowList() {
        return followList;
    }

    /**
     * Calculate the next prime number
     * @param startVal the starting point
     * @return Prime Number
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
