/**
 * Created by Yulia and Efrat.
 */
public class Link {

    private BTreeNode bt;
    private Link next;

    public Link(BTreeNode bt, Link next){
        this.bt = bt;
        this.next = next;
    }
    public Link(BTreeNode bt) {
        this(bt, null);
    }
    public Link getNext() {
        return next;
    }
    public void setNext(Link newNext){
        next = newNext;
    }
    public BTreeNode getBTreeNode () {
        return this.bt;
    }
    public String toString() {
        return ""+bt;
    }
}

