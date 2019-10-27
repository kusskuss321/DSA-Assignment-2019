import java.io.Serializable;

/**
 * Linked List's node class file.
 */
public class LinkedListNode implements Serializable
{
    /**Help identifying the Linked List Node **/
    private String key;
    /**Where the Object is stored **/
    private Object value;
    /**A pointer to the next node **/
    private LinkedListNode next;
    /**A pointer to the previous node **/
    private LinkedListNode previous;

    /**
     * Alternate Constructor
     * @param inKey the Linked List Node identifier.
     * @param inValue the Object that is need to be stored.
     */
    public LinkedListNode(String inKey, Object inValue)
    {
        this.key = inKey;
        this.value = inValue;
        this.next = null;
        this.previous = null;
    }

    /**
     * Mutator
     * @param key The Linked List Node identifier
     */
    public void setKey(String key)
    {
        this.key = key;
    }

    /**
     * Mutator
     * @param next The next node pointer.
     */

    public void setNext(LinkedListNode next)
    {
        this.next = next;
    }


    /**
     * Mutator
     * @param previous The previous node pointer
     */
    public void setPrevious(LinkedListNode previous)
    {
        this.previous = previous;
    }

    /**
     * Mutator
     * @param value The object that is need to be stored.
     */
    public void setValue(Object value)
    {
        this.value = value;
    }

    /**
     * Accessor
     * @return nextNode
     */
    public LinkedListNode getNext()
    {
        LinkedListNode nextNode = next;
        return nextNode;
    }

    /**
     *  Accessor
     * @return value
     */

    public Object getValue()
    {
        return value;
    }

    /**
     * Accessor
     * @return key
     */

    public String getKey()
    {
        return key;
    }

    /**
     * Accessor
     * @return previous
     */

    public LinkedListNode getPrevious()
    {
        return previous;
    }

    /**
     * Checking if the next node is not null
     * @return isnext
     */
    public boolean hasNext()
    {
        boolean isnext;
        isnext = false;
        if (next != null)
        {
            isnext=true;
        }
        return isnext;
    }

    /**
     * Checking if the previous node is not null
     * @return isPrev
     */
    public boolean hasPrev()
    {
        boolean isPrev =false;
        if (previous !=null)
        {
            isPrev=true;
        }
        return isPrev;
    }

    /**
     * Import the LinkedListNode's Object Key.
     * @return key
     */
    @Override
    public String toString()
    {
        return this.key;
    }

}
