/**
 * Created by Yulia and Efrat.
 */
import java.lang.Iterable;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.management.RuntimeErrorException;

public class Messages implements Iterable<Message> {
    private Message [] m;
    int size;
    HashTable [] table;


    public Messages(){
        this.size=size();
        this.m=new Message[size];
        this.table=new HashTable[size];
        for(int i=0;i<table.length;i++)
        	table[i]=new HashTable(m.length);
        
    }

    public int size(){//we are finding the amount of messages so we could build an array on that size
        File messages = new File(System.getProperty("user.dir")+ "/messages.txt");
        int counter = 1;
        Scanner input=null;
        try {
            input = new Scanner(messages) ;
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() == 0) {continue;}
                if(line.equals("#"))
                    counter++;
            }
            
        } catch(FileNotFoundException ex) {
        }
        return counter;
    }

    public Iterator<Message> iterator(){
        return new MessageIterator(m);
    }

    public String findSpams(String path,BTree friends){//for each message, determine if it a spam message
        String ans="";
        Spams spams=new Spams(path);
        Iterator<Message> mesIter=new MessageIterator(m);
        int i=0;
        while(mesIter.hasNext()) {
        	Message curr=mesIter.next();
        	//check if the sender and the receiver are friends- if a is a friend of b, then b is a friend of a
        	String fromto=curr.getFrom()+" & "+curr.getTo();
        	String tofrom=curr.getTo()+" & "+curr.getFrom();
          if(!friends.search(fromto)&&!friends.search(tofrom)) //if they are not friends, check if it a spam message
        	 ans=ifNotFriends(spams, i, ans, curr);
          i++;
        }
        return ans;
    }
    
    public String ifNotFriends(Spams spams, int i, String ans, Message curr) {//for each message, check if there is a high percentage of spam words
    	boolean found=false;
    	Iterator<Spam> spamIter=new SpamsIterator(spams.getS());
    	while(!found&spamIter.hasNext()){
    		Spam sp=(Spam) spamIter.next();//go threw all the spam words possible
    		int find=table[i].HashFunction(sp.getWord());//if the spam word was in the list, this is the place it would go into
    		HashListElement temp=table[i].getList(find).search(sp.getWord());//find the spam word. if this is null, the word does not exist
    		if(temp!=null&&calculatePercentage(temp.getFrequency(),curr.getNumOfWords())>=sp.getFrequency()){//if this is a spam message
    			if(ans=="")
    				ans=ans+i;
    			else
    				ans+=","+i;
    			found=true;
    		}
    	}
    	found=false;
    	return ans;
    }
    public int calculatePercentage(int freq, int num) {//find the word frequency in the message
    	return (freq*100)/num;
    }

    public void createHashTables(String mes) {//create hash table for each message
        int i=0;
        Iterator<Message> mesIter=new MessageIterator(m);
        while(mesIter.hasNext()) {
        		Message curr=mesIter.next();
        		table[i] = new HashTable(Integer.parseInt(mes));
                curr.setNumOfWords(table[i].setText(curr.getText()));
                i++;
            }
        }
    public void checkExceptions(String excption) {
    	if(excption==null)
    		throw new RuntimeErrorException(null, "you are searching a null path");
    	if(excption=="")
    		 throw new RuntimeErrorException(null, "you are searching an empty path");
    }
    	
    public void generateMessages(String location){//copy the messages from the text file
    	checkExceptions(location);
        File messages = new File(location);
        Message receiver = new Message();
        Scanner input=null;
        int i=0;
        try {
            input = new Scanner(messages);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() == 0) {continue;}
                if (!line.equals("#"))
                    receiver = makeNewMessage(line,receiver);
                else
                {
                    m[i]=receiver;
                    i++;
                    receiver=new Message();
                }
            }
            m[i]=receiver;

        } catch(FileNotFoundException ex) {
        }
    }


    public Message makeNewMessage(String line, Message receiver){
        if (line.contains("From:")){
            receiver.setFrom(line.substring(5));
        }
        else if (line.contains("To:")){
            receiver.setTo(line.substring(3));
        }
        else
            receiver.setText(line);

        return receiver;
    }
}
