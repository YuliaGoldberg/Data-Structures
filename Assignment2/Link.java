/**
 * 
 * @author Yulia Goldberg 203840863 && Efrat Eitani 312534522
 */



public class Link {
	
	
	private Link next;
	private Link prev;
	private int data;
	
	public Link(int data, Link next, Link prev){
	    this.next = next;
	    this.prev=prev;
	    this.data=data;
	}
	
	
	public Link getPrev(){
		return prev;
	}
	
	public void setPrev(Link prev){
		this.prev = prev;
	}
	
	public Link getNext(){
		return next;
	}
	
	public void setNext(Link next){
		this.next = next;
	}
	public int getData(){
		return this.data;

	}
	
	public void setData(int data){
		this.data=data;

	}
	

}