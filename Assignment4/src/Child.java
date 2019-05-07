/**
 * Created by Yulia and Efrat.
 */
public class Child {//every node has an array made of "child" that contains all the friendships 
    private String friend;//the friendship string
    private BTreeNode child;//the child of a certain string, contains all the string that are larger than the previous string and smaller from the current string


    public Child(){
            friend="";
            child=null;
    }

    public void setFriend(String friend){
        this.friend=friend;
    }
    
    public void setChild(BTreeNode child){
        this.child=child;
    }
    
    public BTreeNode getChild(){

       return child;
    }
    
    public String getFriend(){
       return friend;
        }
}