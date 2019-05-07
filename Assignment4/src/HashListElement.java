/**
 * Created by Yulia and Efrat.
 */
public class HashListElement {
    private String word;
    private int frequency;
    private HashListElement next;

    public HashListElement(String word, int f,HashListElement next){
        this.word=word;
        this.frequency=f;
        this.next=next;
    }
    public HashListElement getNext(){
        return this.next;
    }
    public void setNext(HashListElement next){
        this.next=next;
    }
    public String getWord(){
        return this.word;
    }
    public void setWord(String word){
       this.word=word;
    }
    public int getFrequency(){
        return this.frequency;
    }
    public void setFrequency(int f){
        this.frequency=f;
    }
    public boolean hasNext()
    {
        return next!=null;
    }
}
