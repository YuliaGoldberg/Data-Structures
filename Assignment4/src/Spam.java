/**
 * Created by yulia on 26-May-18.
 */
public class Spam {
    private String word;
    private int frequency;

    public Spam(String word, int frequency)
    {
        this.word=word;
        this.frequency=frequency;
    }
    public Spam(){
        this(null,0);
    }
    public  String getWord(){
        return this.word;
    }
    public  int getFrequency(){
        return this.frequency;
    }
    public  void setWord(String word){
       this.word=word;
    }
    public  void setFrequency(int frequency){
        this.frequency=frequency;
    }
}
