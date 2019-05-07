/**
 * Created by Yulia and Efrat.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MessageIterator implements Iterator<Message>{
    private Message [] messages ;
    private int index ;

    public MessageIterator(Message [] messages) {
        this.messages = messages;
        this.index=0;
    }
    public boolean hasNext() {
        return index < messages.length;
    }
    public Message next() {
        if(!hasNext())
            throw new NoSuchElementException() ;
        Message next = this.messages[index] ;
        index ++;
        return next ;
    }

}
