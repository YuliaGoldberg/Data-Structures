/**
 * Created by Yulia and Efrat.
 */
import java.lang.Iterable;
import java.util.Iterator;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.RuntimeException;

public class Spams implements Iterable<Spam> {
    private Spam [] s;
    int size;


    public Spams(String path){
    	checkExceptions(path);
        this.size=size(path);
        this.s=new Spam[size];
        SpamsWords(path);
    }
    public int size(String path){//find the amount of possible spam words
        File spams = new File(path);
        int counter = 0;
        Scanner input =null;
        try {
            input = new Scanner(spams);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() == 0) {continue;}
                counter++;
            }
        } catch(FileNotFoundException ex) {
        }
        return counter;
    }
    public Spam [] getS(){
        return this.s;
    }
    public Iterator<Spam> iterator(){
        return new SpamsIterator(this.s);
    }
    public void checkExceptions(String excption) {
    	if(excption==null)
    		throw new RuntimeException("this is a null string");
    	if(excption=="")
    		 throw new RuntimeException("this is an empty string");
    }

    public void SpamsWords(String location){//add to the array spam words from the file
    	checkExceptions(location);
    	Scanner input=null;
        File spams = new File(location);
        Spam receiver = new Spam();
        int i=0;
        try {
            input = new Scanner(spams);
            while (input.hasNextLine()) {
                String line = input.nextLine();
                if (line.length() == 0) {continue;}
                s[i]=createSpamWord(line,receiver);
                i++;
                receiver=new Spam();
            }
        } catch(FileNotFoundException ex) {
        }
    }

    public Spam createSpamWord(String line, Spam receiver) {
        int index=line.indexOf(" ");//trim a single spam word from a string
        receiver.setWord(line.substring(0,index));
        int frequency=Integer.parseInt(line.substring(index+1));//trim a single spam word frequency from a string
        receiver.setFrequency(frequency);
        return receiver;
    }

}
