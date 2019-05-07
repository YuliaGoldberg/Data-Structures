import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Yulia Goldberg 203840863 && Efrat Eitani 312534522
 */
public class LinkedList<T>{


	private Link first;
	private Link last;


	public LinkedList(){
		first = null;
		last=first;
	}

		public Link getLast() {
			return this.last;
		}
	
		public Link getFirst() {
			return this.first;
		}
	
	public void add(int data) 
	{	
			Link current=last; 
			Link newLink=new Link(data,current,null);
			if(last!=null)
				this.last.setPrev(newLink);
			else 
				first=newLink;
			last=newLink;
		
	}
	public void update(Link link) {//the function gets a link and update the list so the link is last in queue and all the links are connected
		if(link.equals(first)&&first.getPrev()!=null)
		{
			first=link.getPrev();
			if(link.getPrev()!=null) 
			{
				link.getPrev().setNext(null);
				link.setNext(last);
				link.setPrev(null);
				last.setPrev(link);
				last=link;
			}
		}
		else 
			if(link.getPrev()!=null) {
				link.getPrev().setNext(link.getNext());
				link.getNext().setPrev(link.getPrev());
				link.setNext(last);
				link.setPrev(null);
				last.setPrev(link);
				last=link;
			}
	}
}
