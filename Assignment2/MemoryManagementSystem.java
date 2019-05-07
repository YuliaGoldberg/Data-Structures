/**
 * 
 * @author Yulia Goldberg 203840863 && Efrat Eitani 312534522
 */
import java.util.Arrays;


public class MemoryManagementSystem{
	public String[] secondaryMemory;
	private boolean useLRU;
	private int[]indexArray;
	private Page[]pageArray;
	private LinkedList<Integer>queueList;

	public MemoryManagementSystem(int mainMemorySize, int secondaryMemorySize, boolean useLRU) {
		this.secondaryMemory=new String[secondaryMemorySize];
		this.useLRU=useLRU;
		this.indexArray=new int[secondaryMemorySize];//contains the information if the page is in the main memory, and if so it keeps the main memory index
		//if the page dose not exist on the main memory, indexArray[index] shows -1
		this.pageArray=new Page[mainMemorySize]; //main memory pages
		this.queueList=new LinkedList<Integer>();// indicates the page priority
		for(int i=0; i<secondaryMemorySize;i=i+1)//initializing secondaryMemory Array
		{
			secondaryMemory[i]="";
		}
		
		for(int i=0;i<mainMemorySize;i=i+1)//initializing pageArray Array
		{
			queueList.add(i);
			pageArray[i]=new Page(secondaryMemory[i],i,queueList.getLast());
		
		}
		for(int i=0; i<secondaryMemorySize;i=i+1)//initializing indexArray
		{
			if(i<mainMemorySize)
				indexArray[i]=i;
			else
				indexArray[i]=-1;
		}
		
	}

	public String toString() {
		return "secondaryMemory=" + Arrays.toString(secondaryMemory);
	}
	
	
	public String read(int index) {
			if(indexArray[index]!=-1)
			{
				if(!useLRU)//FIFO
					{
						return pageArray[indexArray[index]].getS();
					}
				else//LRU
				{
					queueList.update(pageArray[indexArray[index]].getLink());//change the queue order so the most recent page becomes the last in queue.
					return pageArray[indexArray[index]].getS();
				}
			}
			else
			{
				String read=secondaryMemory[index];//the new string we need to replace
				int deleteIndex=queueList.getFirst().getData();// saves the index of page we need to delete
				Page p=pageArray[deleteIndex];
				secondaryMemory[p.getIndex()]=p.getS();//updates the string of the page we delete in the secondary memory
				indexArray[p.getIndex()]=-1;//Indicates that the page we deleted is no longer in the main memory
				pageArray[deleteIndex].setString(read);//changes the page's string to the one we need to replace
				pageArray[deleteIndex].setIndex(index);
				indexArray[index]=queueList.getFirst().getData();//indicates the index array that the new page is now in the main memory
				queueList.update(queueList.getFirst());//makes the new page last in queue.
				return pageArray[indexArray[index]].getS();
			}
			
	}
	
	public void write(int index, char c) {
		if(indexArray[index]!=-1)
		{
			if(!useLRU)//FIFO
				{
					pageArray[indexArray[index]].changeString(c);
				}
			else//LRU
			{
				queueList.update(pageArray[indexArray[index]].getLink());
				pageArray[indexArray[index]].changeString(c);
			}
		}
		else
		{
			String read=secondaryMemory[index];
			int deleteIndex=queueList.getFirst().getData();
			Page p=pageArray[deleteIndex];
			secondaryMemory[p.getIndex()]=p.getS();
			indexArray[p.getIndex()]=-1;
			pageArray[deleteIndex].setString(read);
			pageArray[deleteIndex].setIndex(index);
			indexArray[index]=queueList.getFirst().getData();
			queueList.update(queueList.getFirst());
			pageArray[deleteIndex].changeString(c);
		}
		
	}
	
}