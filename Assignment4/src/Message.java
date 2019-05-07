/**
 * Created by Yulia and Efrat.
 */
public class Message {
    private String from;
    private String to;
    private String text;
    private int numOfWords;

    public Message(String from,String to,String text)
    {
        this.from=from;
        this.to=to;
        this.text=text;
        this.numOfWords=0;
    }
    public Message(){
        this("","","");
    }
    public String getFrom()
    {
        return this.from;
    }
    public String getTo()
    {
        return this.to;
    }
    public String getText()
    {
        return this.text;
    }
    public void setFrom(String from)
    {
        this.from=from;
    }
    public void setTo(String to)
    {
        this.to=to;
    }
    public void setText(String text)
    {
        this.text=this.text+text;
    }
    public int getNumOfWords() {//get number of words in the message
    	return numOfWords;
    }
    public void setNumOfWords(int sum)//set number of words in the message
    {
    	this.numOfWords=sum;
    }
}
