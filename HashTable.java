package preethi;
import java.util.Scanner;
public class HashTable {
	String values[],newtable[],oldtable[];
	String data=new String();
	int tmp[];
	int maxsize,currentsize=0,oldsize,collisions;
	boolean deleted[];
	public HashTable(int maxsize)
	{
		this.maxsize=maxsize;
		values=new String[maxsize];
		oldtable=new String[maxsize];
		deleted=new boolean[maxsize];
	}
	public void add(String x)
	{
		data=x;
		float loadfactor;
		collisions=0;
		int i=hash(data);
		//System.out.println(i+"hash key");
		while(data!=null)
		{
		 if(values[i]==null||deleted[i]==true)
		{
			values[i]=data;
			currentsize++;
			deleted[i]=false;
			loadfactor=(float)currentsize/(float)maxsize;
			//System.out.println("The load factor is "+loadfactor);
			System.out.println(collisions+" collisions");
			if(loadfactor>=0.5)
			{
				oldsize=maxsize;
				maxsize=2*maxsize;
				maxsize=nextPrime(maxsize);
				System.out.println("The load factor is more than 0.5 so the table size is increased to "+maxsize);
				rehash(maxsize,oldsize);
			}
			break;
		}
		else if(i==maxsize-1) 
		{
			i=0;
			collisions=collisions+1;
			//System.out.println(collisions+" collisions");
		}
		else
		{
			i++;
			collisions=collisions+1;
			//System.out.println(collisions+" collisions");
		}
	  }
		
	}
	public void rehash(int newsize,int old)
	{
		String rp;
		newtable=new String[newsize];
		deleted=new boolean[newsize];
		currentsize=0;collisions=0;
		for(int i=0;i<old;i++)
	    {
		
	       if(values[i]!=null){
	    	   rp=values[i];
	    	 // System.out.println(rp+"rp");
	    	  int j=hash(rp);
	    	  while(rp!=null)
	    	  {
	    	  if(newtable[j]==null)
	    	  {
	    		  newtable[j]=rp;
	    		  currentsize++;
	    		  //System.out.println(collisions+" collisions");
	    		  break;
	    	  }
	    	  else if(j==newsize-1)
	    	  {
	    		  j=0;
	    		  collisions=collisions+1;
	    	  }
	    	  else
	    	  {
	    		  j++;
	    		  collisions=collisions+1;
	    	  }
	      }
	     }
	       }
	    values=newtable;
	}
	
	public int nextPrime(int size)
	{
		boolean isPrime=false;
	  while(isPrime==false)
	  {
		int count=0;
		for(int i=1;i<=size;i++)
		{
			if(size%i==0)
				count++;
		}
		if(count==2)
		{
		   isPrime=true;
		}
		else
		size++;
	  }
	  return size;
	}
	
	public String search(String find)
	{
		int i=hash(find);
		while(values[i]!=null)
		{
			//System.out.println(values[i]);
			//System.out.println(deleted[i]);
			if(values[i].equals(find)&&deleted[i]!=true)
			{
				String y="y";
				System.out.println("The index of "+find+" is "+i);
				return y;
			}
			else if(i==maxsize-1)
				i=0;
			else
				i++;
		}
		return null;
	}
	public void delete(String del)
	{
		int k=hash(del);
		while(values[k]!=null)
		{
			//System.out.println(values[k]);
			//System.out.println(deleted[k]);
		if(values[k].equals(del)&&deleted[k]==false)
		{
			System.out.println("The value is present in the Hash Table");
			deleted[k]=true;
			System.out.println("The value is now deleted !");
			currentsize=currentsize-1;
			return;
			}
		else if(values[k].equals(del)&&deleted[k]==true)
		{
			System.out.println("Error!There is no such value in the Hash Table to be deleted");
			return;
		}
		else if(k==maxsize-1)
		{
			k=0;
		}
		else
			k++;
	}
		System.out.println("Error!No such value exists in the hash table");
	}
	public int hash(String key)
	{
		int strlen=key.length();
		long h=0;
		for(int i=0;i<strlen;i++)
		{
			h=h+(long)key.charAt(i)*(long)Math.pow(37,i);
		  //System.out.println(h);
		}
		h=(h%maxsize);
		//System.out.println(h+" hash key");
		return (int) h;
	}
	public void printhash(){
		 System.out.println("\nHash Table: ");
		 System.out.println("Index : String");
	        for (int i = 0; i < maxsize; i++)
	            if (values[i] != null&&deleted[i]!=true)
	                System.out.println(i+"     :  "+values[i]);
	        System.out.println();
	}
public static void main(String args[])
{
  String d;
  int size;
 Scanner s=new Scanner(System.in);

	System.out.println("Enter the initial size");
	try
	{
	size=s.nextInt();
	HashTable h=new HashTable(size);
	char c;
	do
	{
	System.out.println("Select one of the Hash Table Operations(Type 1 or 2 or 3)");
	System.out.println("1.ADD\n2.SEARCH\n3.DELETE");
	int ch=s.nextInt();
	switch(ch)
	{
	case 1:System.out.println("Enter the string value you want to insert");
	d=s.next();
	h.add(d);
	h.printhash();
	break;
	case 2:System.out.println("Enter the string value you want to search");
    d=s.next();
    String y=h.search(d);
    if(y==null)
    {
    	System.out.println("Error! Value not present in the Hash Table");
    }
    h.printhash();
    break;
	case 3:System.out.println("Enter the string value you want to delete");
	d=s.next();
	h.delete(d);
	h.printhash();
	break;
	default:System.out.println("Wrong Choice");
	break;
	}
	 System.out.println("Do you want to continue (Type y or n)");
   c= s.next().charAt(0);                        
    }while(c== 'Y'|| c== 'y');  
	}
	catch(Exception e)
	{
		System.out.println("Interrupt occured due to an error");
	}
    s.close();
	}
}

