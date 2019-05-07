/**
 * 
 * @author Yulia Goldberg 203840863 && Efrat Eitani 312534522
 */
public class Page {
	private String s;
	private int index;
	private Link link;
	
	public Page(String s,int index, Link l)
	{
		this.s=s;
		this.index=index;
		this.link=l;
	}
	public String getS()
	{
		return this.s;
	}
	public int getIndex()
	{
		return this.index;
	}
	public void setIndex(int index)
	{
		this.index=index;
		
	}
	public void setString(String string)
	{
		this.s=string;
		
	}
	public void changeString(char c)
	{
		this.s=s+c;
		
	}
	public Link getLink()
	{
		return this.link;
	}
}
