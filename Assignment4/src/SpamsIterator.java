/**
 * Created by yulia on 26-May-18.
 */
import java.util.Iterator;
import java.util.NoSuchElementException;
public class SpamsIterator implements Iterator<Spam>{
    private Spam [] spams ;
    private int index ;

    public SpamsIterator(Spam []s) {
        this.spams = s;
        this.index=0;
    }
    public boolean hasNext() {
        return index < spams.length;
    }
    public Spam next() {
        if(!hasNext())
            throw new NoSuchElementException() ;
        Spam next = this.spams[index] ;
        index ++;
        return next ;
    }

}
