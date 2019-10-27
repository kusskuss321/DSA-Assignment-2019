import java.io.Serializable;

/**
The node of Post class.
 **/
public class postNode implements Serializable
{

        /**The Post timestamp**/
        private String timeStamp;
        /** The post status**/
        private String status;
        /**The post likes count**/
        private int likesCount;
        /**The post bait condition**/
        private boolean bait;
        /**The post bait count multiplier**/
        private int baitCount;


    /**
     * Default Constructor
     */
    public postNode() {
            timeStamp = null;
            status = null;
            this.likesCount=0;
            this.bait=false;
            this.baitCount = 0;
        }


    /**
     * Alternate Constructor
     * @param intimeStamp timestamp of the post
     * @param status the status of the post
     */
    public postNode(String intimeStamp, String status) {
            this.timeStamp = intimeStamp;
            this.status = status;
            this.likesCount=0;
        }

    /**
     * setter for bait count
     * @param baitCount
     */
    public void setBaitCount(int baitCount) {
        this.baitCount = baitCount;
    }

    /**
     * getter for bait count
     * @return baitCount
     */
    public int getBaitCount() {
        return baitCount;
    }

    /**
     * if post is bait, set bait to true
     */
    public void setBait() {
        this.bait = true;
    }


    /**
     * getter for bait
     * @return bait
     */
    public boolean getBait()
    {
        return bait;
    }

    /**
     * getter for status
     * @return status
     */
    public String getStatus() {
            return status;
        }

    /**
     * getter for timestamp
     * @return timeStamp
     */
    public String getTimeStamp() {
            return this.timeStamp;
        }

    /**
     * increment likes
     */
    public void incrementLikes()
        {
            likesCount++;
        }

    /**
     * getter for likesCount
     * @return likesCount
     */
    public int getLikesCount()
        {
            return likesCount;
        }


    /**
     * setter for status
     * @param status the status of the post
     */
    public void setStatus(String status) {
            this.status = status;
        }

    /**
     * setter for the timeStamp
     * @param timeStamp the timestamp of the post
     */
    public void setTimeStamp(String timeStamp) {
            this.timeStamp = timeStamp;
        }

    public String toString()
    {
        String st;
        if(this.timeStamp == null && this.status==null)
        {
            st="";
        }
        else
        {
            st = timeStamp+": " +status;
        }
        return st;
    }

    /**
     * check if two post is equal
     * @param obj
     * @return eq
     */
    @Override
        public boolean equals(Object obj) {
            boolean eq=false;
            if (obj instanceof postNode)
            {
                if (this.equals(((postNode) obj).getStatus()))
                {
                    eq=true;
                }
            }
            return eq;
        }
}
