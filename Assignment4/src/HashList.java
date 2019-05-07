/**
 * Created by Yulia and Efrat.
 */
public class HashList {
    private HashListElement first;

    public HashList(){
        first=null;
    }
    public Boolean isEmpty(){
        return first==null;
    }

    public void insert(String st){//insert to a hash table
        if(isEmpty()){
        	first=new HashListElement(st,1,null);
        }
        else{
        	HashListElement temp=search(st);//find out if the word already exists in the hash table
            if(temp==null){//if the word doesn't exist
                HashListElement curr=new HashListElement(st,1,first);
                first=curr;
            }
            else//the word exists more than once in the text message
            	temp.setFrequency(temp.getFrequency()+1);            	
        }
    }

    public HashListElement search(String st){//search for a specific word in the hash table
        HashListElement curr=first;
        while(curr.hasNext()){//if it has next
            if(curr.getWord().equals(st)) 
                return curr;
            curr=curr.getNext();
        }
        if(curr.getWord().equals(st)) //the last element in the list
            return curr;
        return null;
    }
}
