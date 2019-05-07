/**
 * Created by Yulia and Efrat.
 */
import java.util.NoSuchElementException;

public class LinkedList {// a regular linked list as seen in introduction to computer science

	// fields
	private Link first;
	private Link last;
	private int size;

	// constructors
	public LinkedList(){
		first = null;
		last = null;
		this.size=0;
	}
	public boolean isEmpty() {
		return first==null;
	}
	public int Size() {
		return size;
	}
	public BTreeNode getFirst() {
		if(first==null)
			return null;
		return this.first.getBTreeNode();
	}
	public void add(BTreeNode element) {
	   if(element == null)
		   throw new NullPointerException();
	   if(isEmpty()){
		   first = new Link(element);
		   last = first;
		   this.size=size+1;
	   }
	   else {
		   BTreeNode newLast = element;
		   Link add =new Link(newLast);
		   last.setNext(add);
		   last =add;
		   this.size=size+1;
	   }
	}

	public BTreeNode removeFirst() {
		if(isEmpty())
			throw new NoSuchElementException("The list is empty!!!");
		BTreeNode temp=this.first.getBTreeNode();
		size--;	
		first=first.getNext();
		if(first==null)
			last=null;
	   return temp;
	   
   }
	
	}
	