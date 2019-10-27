import java.io.Serializable;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Linked List Abstract Data Structure
 */
public class LinkedList implements Iterable, Serializable
{
    /**A head pointer**/
    private LinkedListNode head;
    /**A tail pointer**/
    private LinkedListNode tail;

    /**
     * Creating a Iterator for the LinkedList
     * @return LinkedListIterator
     */
    public Iterator iterator()
    {
        return new LinkedListIterator(this);
    }

    /**
     * Constructor
     */
    public LinkedList()
    {
        this.head = null;
        this.tail = null;
    }

    /**
     * Insert the node from the head of the linked list.
     * @param inkey the node identifier.
     * @param invalue the object that need to be stored.
     */
    public void insertFirst(String inkey, Object invalue)
    {
        LinkedListNode node = new LinkedListNode(inkey, invalue);
        if (isEmpty())
        {
            insertEmpty(node);
        }
        else
        {
            node.setNext(head);
            node.setPrevious(null);
            head.setPrevious(node);
            head = node;
        }

    }

    /**
     * Inserting the node from the tail of the linked list
     * @param inkey the node indentifier
     * @param invalue the object that is needed to be stored.
     */
    public void insertLast(String inkey, Object invalue)
    {
        LinkedListNode node = new LinkedListNode(inkey,invalue);
        if (isEmpty())
        {
            insertEmpty(node);
        }
        else
        {
            tail.setNext(node);
            node.setPrevious(tail);
            tail = node;
            tail.setNext(null);
        }
    }

    /**
     * removes the head element of the linked list
     */
    public void removeFirst()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("The Linked List is Empty");
        }
        else if (head.getNext() == null)
        {
            head = null;
        }
        else
        {
            head=head.getNext();
            head.setPrevious(null);
        }
    }

    /**
     * removes the tail element of the linked list
     */
    public void removeLast()
    {
        if (isEmpty())
        {
            throw new NoSuchElementException("The Linked List is empty");
        }
        else if (tail.getPrevious()==null)
        {
            tail = null;
        }
        else
        {
            tail = tail.getPrevious();
            tail.setNext(null);
        }
    }

    /**
     * Iterate through the linked list to find the matching node. Remove the matching node from the linked list.
     * @param fullName the linked list node identifier
     */
    public void remove(String fullName)
    {
       if (isEmpty())
       {
           System.out.println("Linked List is Empty");
       }
       else
       {
           LinkedListNode cur = head;
           while (cur.hasNext())
           {
               if (cur.getKey().equals(fullName))
               {
                   if (!cur.hasPrev())
                   {
                      removeFirst();
                   }
                   else
                       {
                           LinkedListNode prev,next;
                           prev=cur.getPrevious();
                           next=cur.getNext();

                           prev.setNext(next);
                           next.setPrevious(prev);

                   }
               }
               cur=cur.getNext();
           }
           if (tail.getKey().equals(fullName))
           {
               removeLast();
           }
       }
    }

    /**
     * Returning the head element of linked list.
     * @return head
     */
    public Object getFirst()
    {
        return head;
    }

    /**
     * Peeking the head element of linked list
     * @return value
     */

    public Object peekFirst()
    {
        Object value;
        if (isEmpty())
        {
            throw new NoSuchElementException("The linked list is empty");
        }
        else
        {
            value = head.getValue();
        }

        return value;
    }

    /**
     * peeking the last element of linked list
     * @return value
     */
    public Object peekLast()
    {
        Object value;

        if (isEmpty())
        {
            throw new NoSuchElementException("The linked list is empty");
        }
        else
        {
            value = tail.getValue();
        }

        return value;
    }


    /**
     * checking if linked list is empty
     * @return isEmpty
     */
    public boolean isEmpty()
    {
        boolean isEmpty = false;
        if (head ==null)
        {
            isEmpty = true;
        }

        return isEmpty;
    }

    /**
     * returning the head object
     * @return head
     */
    public LinkedListNode getHead()
    {
        return head;
    }

    /**
     * returning the tail object
     * @return tail
     */
    public LinkedListNode getTail()
    {
        return tail;
    }

    /**
     * This method is executed if isEmpty method returns true.
     * @param node
     */
    private void insertEmpty(LinkedListNode node)
    {
        head = tail = node;
        node.setPrevious(null);
        node.setNext(null);
    }



}
