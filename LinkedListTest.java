import java.util.Iterator;

/**
 * This class file is used to test the functionality of linked list ADT.
 */
public class LinkedListTest
{
    public static void main(String[] args)
    {
        LinkedList newlist = new LinkedList();

        Person n1 = new Person("A");
        Person n2 = new Person("B");
        Person n3 = new Person("C");
        Person n4 = new Person("D");
        Person n5 = new Person("E");


        newlist.insertFirst(n1.getLabel(),n1);
        newlist.insertFirst(n2.getLabel(),n2);
        newlist.insertFirst(n3.getLabel(),n3);
        newlist.insertFirst(n4.getLabel(),n4);
        newlist.insertFirst(n5.getLabel(),n5);

        for (Object o: newlist)
        {
            Person x = (Person)o;
            System.out.println(x.toString());
        }

        newlist.remove("A");
        System.out.println("After");
        for (Object o: newlist)
        {
            Person x = (Person)o;
            System.out.println(x.toString());
        }

    }
}
