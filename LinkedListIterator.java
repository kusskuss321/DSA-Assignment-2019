import java.io.Serializable;
import java.util.Iterator;

/**
 * An interator class file for Linked list
 */
public class LinkedListIterator implements Iterator, Serializable
{
    /**A pointer**/
    private LinkedListNode iterNext;

    /**
     * Constructor
     * @param theList Linked List Object
     */
    public LinkedListIterator(LinkedList theList)
    {
        iterNext = theList.getHead();
    }

    /**
     * Checking if the next node exist
     * @return boolean
     */
    @Override
    public boolean hasNext()
    {
        return (iterNext!=null);
    }

    /**
     * Getting the next object
     * @return value
     */
    @Override
    public Object next()
    {
        Object value;
        if (iterNext == null)
        {
            value = null;
        }
        else
        {
            value = iterNext.getValue();
            iterNext = iterNext.getNext();
        }
        return value;
    }

    /**
     * Remove method is not supported
     */
    @Override
    public void remove()
    {
        throw new UnsupportedOperationException("Not Supported");
    }
}
