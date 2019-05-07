/**
 * Created by Yulia and Efrat.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.lang.RuntimeException;;

public class BTree {
    public BTreeNode root;
    private int t;

    public BTree(int t) {
    	if(t<=1)
            throw new RuntimeException("illegal tree size");
        this.t = t;
        this.root = new BTreeNode(t);
    }
    public BTree(String s) {
    	this(Integer.parseInt(s));
    }
    
    public String BFS() {
    	String ans="";
    	QueueAsLinkedList q=new QueueAsLinkedList();
    	q.enqueue(root);
    	while(!q.isEmpty()) {
    		BTreeNode node=q.dequeue();
    		ans=handleBrotherFromTheSameFather(ans,q,node);//add comma after every friendship if it is not the end of the array
    		if(!q.isEmpty()) {
    			ans=handleEndOfANode(ans,q,node);//check whats next on the queue and handle by adding a specific sign to the string
    				
    		}
    	}
    	return ans;
    }	
    
    public String handleBrotherFromTheSameFather(String ans,QueueAsLinkedList q, BTreeNode node) {//add comma after every friendship if it is not the end of the array
		for(int i=0;i<=node.getSize();i++) {
			String add=node.getFriendsArray()[i].getFriend();//get a specific friendship from a cell in the array
			if(node.getFriendsArray()[i].getChild()!=null)
				q.enqueue(node.getFriendsArray()[i].getChild());//insert the "son" of this key into the queue
			if(add!="") {//not an empty string
				if(i==node.getSize()-1)//if it is the last "friendship" in the array
					ans=ans+add;
				else//there are more friendships to come
					ans=ans+add+",";
			}
		}
		return ans;
	}
    
    public String handleEndOfANode(String ans,QueueAsLinkedList q, BTreeNode node) {//if we got to the last "friendship" in the array
    BTreeNode temp=q.peek();//check what's next in the queue to determine the sign
	if(node!=null&&temp!=null) {
		if(node.getDepth()!=temp.getDepth())//if its the end of the level
			ans=ans+"#";
		else if(node.getDepth()==temp.getDepth()&&node.getParent()==temp.getParent())//if the next node is another son from the same father
			ans=ans+"|";
		else if(node.getDepth()==temp.getDepth()&&node.getParent()!=temp.getParent())//if the next node is another son from a different father
			ans=ans+"^";
	}
	return ans;
    }
   
    public String toString() { //create a String that represents all of the nodes in the tree by represented by Breadth-first search
    	SendToDepth(root,0);//set the depth of all the nodes and set parent field for each node
    	return BFS();
    }
    public void SendToDepth(BTreeNode node,int depth)//set the depth of all the nodes
    {
    	for(int i=0;i<=node.getSize();i++)//going threw all the array
    	{
    		CreateDepth(node,depth,i);//help function
    		if(node.getDepth()==0)//we haven't set the depth yet
    			node.setDepth(depth);
    	}
    }
    
    
	public void CreateDepth(BTreeNode node,int depth,int i) {//a recursive function that goes from the root until the leaf
		if(node.IsLeaf()) {
			if(node.getDepth()==0)
				node.setDepth(depth);
		}
		else{
			node.getFriendsArray()[i].getChild().setParent(node);//set a parent for each node
			SendToDepth(node.getFriendsArray()[i].getChild(),depth+1);//so we can go threw the entire array
		}
	}
	//this is the only function in our project that is longer than 15 lines. we think it is necessary to keep it this long so it would be easy to read.
    public boolean search(String findFriend) {//search for a requested friendship
    	checkExceptions(findFriend);
        int i = 0;
        BTreeNode node = root;
        while (i < node.getFriendsArray().length - 1 & node.getFriendsArray()[i].getFriend() != null) {
        	if(node.getFriendsArray()[i].getFriend().equals(findFriend))//if we found the requested string
        		return true;
        	if(node.getFriendsArray()[i].getFriend()==""){//if we got to the end of an array
        		if(node.IsLeaf())//if its a leaf, the String does not exist
        			return false;
        		else{//the node is not a leaf
        			node=node.getFriendsArray()[i].getChild();//try the last child of the node (the "largest")
        			i=0;
        		}
        	}
        	else {//there are more friends to search between
        		if (findFriend.compareTo(node.getFriendsArray()[i].getFriend()) > 0)//if the string we search for is "larger" from the string in place i
        			i++;
        		else {	//if the string we search for is "larger" from the previous string and larger from the next one
        			if (node.IsLeaf() == true) // there are no strings to look for between those string
        				return false;
        			else{
        				node = node.getFriendsArray()[i].getChild();//there are other string between
        				i=0;
        			}
        		}
        	}
        }
        return false;
    }
    
    public void checkExceptions(String findFriend) {
    	if(findFriend==null)
    		throw new RuntimeException("this is a null string");
    	if(findFriend=="")
    		 throw new RuntimeException("this is an empty string");
    }
  
    public boolean insert(String insertFriend) {//insert friendship into the tree
    	checkExceptions(insertFriend);
    	return insert(insertFriend,root,0,false);//we expended the input to insert function
    	 
    }
    public boolean insert(String insertFriend,BTreeNode bt,int index,boolean found){
    	if(!found) {//this is a recursive function and this is the stop term	
    	if(!bt.IsLeaf()) {
    		if(bt.getSize()==2*t-1) 
				found=handleFullNode(insertFriend, 0, bt);//if a node is full split it and start the function again
    		found=insertNotLeaf(bt, found, insertFriend);//find the right place for the data if the node is not a leaf
    	}
    	//now bt is a leaf
    	else {
    		if(bt.getSize()==2*t-1) //if a node is full split it and start the function again
    			found=handleFullNode(insertFriend, 0, bt);
    		else 
    			return insertIfLeaf(insertFriend,bt);//find the right place for the data if the node is a leaf
    		}
    	}
    	return true;
    }
  //find the right place for the data if the node is not a leaf
    public boolean insertNotLeaf(BTreeNode bt, boolean found,String insertFriend) {
    	for(int i=0;i<=bt.getSize()&!found;i++) {//go throw the entire array and find a spot to insert
			Child c=bt.getFriendsArray()[i];
			if(c.getFriend()=="") {//we got to the last open space in the node and we need to enter to the child
				if(c.getChild().getSize()==2*t-1) 
		    		found=handleFullNode(insertFriend, i, bt);//handle if full
				else
					found=insert(insertFriend,c.getChild(),i,false);//start searching a spot in this node
			}
			else if (insertFriend.compareTo(c.getFriend())<0){//we are not in the end of the array, check according to BTree logic
				if(c.getChild().getSize()==2*t-1) 
		    		Split(bt,i);
				found=insert(insertFriend,c.getChild(),i,false);	
		}
	}
    return found;	
    }
    
  //find the right place for the data if the node is a leaf
    public boolean insertIfLeaf(String insertFriend, BTreeNode bt){//we are going to place out string into this node
    	for(int i=0;i<=bt.getSize();i++){
			Child c=bt.getFriendsArray()[i];
			if(c.getFriend()==""){//place the string in the end of the array
				c.setFriend(insertFriend);
				bt.setSize(bt.getSize()+1);
				return true;
			}
			if (insertFriend.compareTo(c.getFriend())<0){//we are still looking for a spot for the string
				Switch(i,bt,insertFriend);//the place we found is between other string and we need to make room
				return true;
			}
		}
    	return false;
    }
    
    public boolean handleFullNode(String insertFriend,int i, BTreeNode bt) {//split the full node according to BTree logic
    	Split(bt,i);
		return insert(insertFriend,root,0,false);	
    }

    public void Switch(int i,BTreeNode n,String friend){//find the first cell that is empty, and shift all the nodes one step to the right 
    	  //and transfer the middle key to the new node
          for(int j=n.getSize()+1;j>i;j--) {
        	  Child c1=n.getFriendsArray()[j];
        	  Child c2=n.getFriendsArray()[j-1];
              c1.setFriend(c2.getFriend());
              c1.setChild(c2.getChild());
          }
          n.getFriendsArray()[i].setFriend(friend);//insert the new friend in he proper spot
          n.setSize(n.getSize()+1);//increase size
      }

    public void Split(BTreeNode n,int i){
          //i symbolizes the space it should go into in the father node(BTree logic)
    	//if the node is a root, i equals 0
        if(n==root&root.getSize()==2*t-1)
        	splitIfRoot(i);
        else {
        	splitIfNotRoot(n,i);
        }
    }
     public void splitIfRoot(int i) {//when split the root, and create 2 new children 
    	 BTreeNode x=new BTreeNode(t);//this will be the new root
    	//x left son will be the former root
         x.getFriendsArray()[0].setFriend(root.getFriendsArray()[t-1].getFriend());
         x.getFriendsArray()[0].setChild(root);
         root.getFriendsArray()[t-1].setFriend("");//we moved the middle key into a new node
         x.setSize(1);
         root.setSize(root.getSize()-1);
         BTreeNode y=new BTreeNode(t);
         y=replace(t,root,y);//splitting and moving half of the information in the former node to a new one 
         boolean determineLeaf=root.IsLeaf();//the new node will be a leaf only if the root was a leaf before splitting
         this.root=x;//now x is the new node
         root.setLeaf(false);//after splitting the root, the new root will never be a leaf
         root.getFriendsArray()[1].setChild(y);
         y.setLeaf(determineLeaf);
     }
     
     public void splitIfNotRoot(BTreeNode n,int i) {//split a node that is not the root
    	BTreeNode temp=n.getFriendsArray()[i].getChild();//i symbolizes the space it should go into in the father node(BTree logic)
     	Switch(i,n,temp.getFriendsArray()[t-1].getFriend());//find the first cell that is empty, and shift all the nodes one step to the right  
     	temp.getFriendsArray()[t-1].setFriend("");//clear the middle key from this node, we copied it on "split" function
     	temp.setSize(temp.getSize()-1);
     	BTreeNode x=new BTreeNode(t);
     	x=replace(t,temp,x);//splitting and moving half of the information in the former node to a new one 
     	//setting the node 2 new children
     	n.getFriendsArray()[i].setChild(temp);
     	n.getFriendsArray()[i+1].setChild(x);
     	x.setLeaf(temp.IsLeaf());// the node that we split new brother will be a leaf if the node was a leaf before splitting
     }
     
    public BTreeNode replace(int i, BTreeNode source, BTreeNode node){//moving half of the information in the former node to a new one 
        for(int j=0;j<=2*t-1&&i<=2*t-1;j++){
        	Child c1=node.getFriendsArray()[j];
        	Child c2=source.getFriendsArray()[i];
            c1.setChild(c2.getChild());
            c1.setFriend(c2.getFriend());
            if(c2.getFriend()!="")//that means the were moving a not empty string
            {//change the sizes after moving a not empty string
            	node.setSize(node.getSize()+1);
            	source.setSize(source.getSize()-1);
            }
            //after moving to the new node, erase from the former node
            c2.setChild(null);
            c2.setFriend("");  
            i++;
        }
        return node;
    }

    public void createFullTree(String friends) {//copying friend couples from the text file 
        File friendFile=new File(friends);//insert the path so we can "read" it
        Scanner toScan=null;
        String couples;
        try{
        	toScan = new Scanner(friendFile);//scan the file to find friendships
            while(toScan.hasNextLine()){//as long as there are more friendships
                couples = toScan.nextLine();
                if (couples.length()==0) continue;
                this.insert(couples);//insert every friendships you find in to the tree
            }
        } 
        catch(FileNotFoundException ex) {}//make sure the file actually exists
    }
}

