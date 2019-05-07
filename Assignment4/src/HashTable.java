/**
 * Created by Yulia and Efrat.
 */
public class HashTable {
    private int size;
    private HashList[] h;
    private String text;
    

    public HashTable(int m) {
    	if(m<1)//illegal input
            throw new RuntimeException("illegal input-the hash table is too small");
        this.h = new HashList[m];
        for(int i=0;i<h.length;i++)//initialize the hash table
        	h[i]=new HashList();
        this.text="";
        size=m;
    }

    public HashTable(String string) {
		this(Integer.parseInt(string));
	}
    public void insert(String string) {
    	checkExceptions(string);
    	int index=HashFunction(string);//search for a spot in the hash table 
    	h[index].insert(string);
    }

	public int handleText(String st) {//go threw the message and split it into separate words as preparation for a insertion 
        int i = 0;
        int count=0;
        while (!this.text.equals("")) {//if the text message is not empty
        	while (!st.equals(text)&&text.charAt(i)!=' ') {
        		
        		//
                st = st + text.charAt(i);
                i++;
            }
        	if(st.equals(text)) //this is the last word in the text message
        		this.text=text.substring(i);
        	else//delete the word from text
        		this.text = text.substring(i + 1);
            int index=HashFunction(st);//find a space for "st" in the hash table
            count++;
            h[index].insert(st);//insert the word in a specific space
            i=0;
            st="";
        }
        return count;
    }

    public int HashFunction(String st){//find according to hash function, an index in the hash table
    	int num=0;
    	while(!st.equals(""))
    	{
    		num=num+(int)(st.charAt(0));//sums the integer value for every word
    		st=st.substring(1);
    	}
        return num%size;
    }
    
    public int setText(String text){//sets the field text and insert all the words to hash table
        this.text=text;
       return handleText("");
    }
    public HashList getList(int i){
        return this.h[i];
    }
    
    public void checkExceptions(String findFriend) {
    	if(findFriend==null)
    		throw new RuntimeException("this is a null string");
    	if(findFriend=="")
    		 throw new RuntimeException("this is an empty string");
    }
}