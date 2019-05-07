/**
 * Created by Yulia and Efrat.
 */
public  class BTreeNode {
    private Child[] friendsArray;
    private int size;
    private boolean isLeaf;
    private BTreeNode parent;
    private int depth;

    public BTreeNode(int t) {
        friendsArray = new Child[2 * t];
        for(int i=0; i<2*t;i++)
        	friendsArray[i]=new Child();
        size=0;
        isLeaf=true;
        parent=null;
        depth=0;
    }
    public Boolean IsLeaf(){
        return isLeaf;
    }

    public void setLeaf(boolean leaf){
        isLeaf=leaf;
    }

    public Child [] getFriendsArray(){
        return this.friendsArray;
    }
    public int getSize()
    {
        return this.size;
    }
    public void setSize(int size)
    {
        this.size=size;
    }
    public void setParent(BTreeNode b) {
    	this.parent=b;
    }
    public void setDepth(int d) {
    	this.depth=d;
    }
    public BTreeNode getParent() {
    	return parent;
    }
    public int getDepth() {
    	return depth;
    }
	
	



}