/**
 * Created by Yulia and Efrat.
 */
import java.util.NoSuchElementException;

public class QueueAsLinkedList  {
    private LinkedList list;
    
    public QueueAsLinkedList() {
        this.list = new LinkedList();
    }
    public boolean isEmpty()
    {
    	return list.getFirst()==null;
    }
    public void enqueue(BTreeNode bt) {
    	list.add(bt);
   	}
    
    public BTreeNode dequeue() {
        if(isEmpty())
            throw new NoSuchElementException();
        BTreeNode output = list.removeFirst();
        return output;
    }
    public BTreeNode peek() {
    	return list.getFirst();
    }
}
