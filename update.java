import java.util.Iterator;

/**
 * This class file is responsible for timeStep Update
 */
public class update {
    /**
     * The landing page for updateTimestep functionality
     *
     * @param network    the network object
     * @param followProb the follow probability
     * @param likeProb   the liking probability
     */
    public void landing(Network network, double followProb, double likeProb)
    {

        followUpdate(network, followProb);
        unfollow(network, followProb);
        like(network, likeProb);
    }

    /**
     * @param network    network object
     * @param followProb p
     */
    private void unfollow(Network network, double followProb) {
        double unfollow = 1 - followProb;
        //only goes the loop once
        for (Object o : network.getVertices())
        {
            Person original = (Person) o;
            for (Object x : original.getAdjacent())
            {
                Person personToRemove = (Person) x;

                double gen = Math.random();
                if (Double.compare(gen, unfollow) < 0)
                {
                    if (!checkFollower(original,personToRemove))
                    {
                        network.unfollow(original.getLabel(), personToRemove.getLabel());
                    }
                }
                else
                    {

                }
            }
        }
    }

    /**
     * Update follow
     *
     * @param network    network object
     * @param followProb followe probability
     */
    private void followUpdate(Network network, double followProb) {
        //only goes the loop once
        for (Object o : network.getVertices())
        {
            Person original = (Person) o;
            for (Object l : network.getVertices())
            {
                Person toFollow = (Person) l;
                if (original.getLabel().equals(toFollow.getLabel()))
                {
                    //cant follow yourself
                }
                else
                    {
                    //lets play a probability gammeeeeeee
                    //so if the generated random number is less than the set followprob then Original DOES NOT FOLLOW toFollow person
                    //math random always generate number between 0 and 1 which is perfect for probabiltiy
                    //use Double.compare(d1,d2) to compare double without using TOL
                    double genNumber = Math.random();
                    if (Double.compare(genNumber, followProb) < 0)
                    {
                        if (!checkFollower(original,toFollow))
                        {
                            network.addEge(original.getLabel(), toFollow.getLabel());
                        }
                    }
                    else if (Double.compare(genNumber, followProb) > 0)
                    {
                    }
                }
            }
        }
    }

    public boolean checkFollower(Person original, Person follower)
    {
        boolean follow = false;
        int getFollower = follower.getFollowersCount();

        for (int i =0; i<getFollower; i++)
        {
            if (original.getLabel().equals(follower.getListFollow()[i].getLabel()))
            {
                follow=true;
            }
        }
        return follow;
    }

    private void like(Network network, double likeProb)
    {
        for (Object O : network.getVertices())
        {
            Person startingPoint = (Person) O;

        }
    }

    private void likeTimestep(Network network, double likeProb, Person starting)
    {
        postNode latestpost = starting.getPostTimeline().getLatestpost();
        int getBait = latestpost.getBaitCount();
        if (latestpost != null)
        {
            if (starting.getFollowersCount() == 0)
            {

            }
            else
                {
                for (int i = 0; i < starting.getFollowersCount() - 1; i++)
                {
                    Person toLike = starting.getListFollow()[i];
                    double rand = Math.random();
                    if (getBait==0)
                    {
                        if (Double.compare(rand, likeProb) < 0)
                        {
                            latestpost.incrementLikes();
                        }
                    }
                    else
                    {
                        double trueLike = likeProb;
                        for (int x =0; x<getBait; i++)
                        {
                            trueLike += Math.pow(likeProb,x);
                        }
                        if (Double.compare(rand, likeProb) < 0)
                        {
                            latestpost.incrementLikes();
                        }
                    }
                }
            }
        }
    }
}