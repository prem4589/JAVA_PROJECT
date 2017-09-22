import java.util.*;
import java.io.*;
import java.lang.*;
class Lcs2
{
	/*this function lowers each and every case in the words by using ascii values */
	public static String Func1(String words)
	{
		String temp1="";
		words.toLowerCase();
		for(int i=0;i<words.length();i++)
		{
			if(words.charAt(i)>96 && words.charAt(i)<123 || words.charAt(i)==32)
			{
                temp1+=words.charAt(i);
            } 
		}
		return temp1;

	}
	/* this function returns the length of sub string which matches with the first string */ 
	public static int lcs1(String f1_words,String f2_words)
	{
	     int count1=0;
		String temp2="";
	
   	for(int i=0;i<f1_words.length();i++)
	{
		int x=i;
		int y=x;
		for(int j=0;j<f2_words.length();j++)
		{
    		if(x<f1_words.length())
			{
				if (f1_words.charAt(x)==f2_words.charAt(j))
				{
					x+=1;
					if((x-i)>count1)
					{
		               count1=x-i;
		              
		               temp2="";
		                for(int z=i;z<x;z++)
		                	{

		                		temp2+=f1_words.charAt(z);
		                	
		                	}
		              
					}
				} 
				else
        		{
            		x=y;
        		}   
			}
		}
	}
    return(count1-1);
}
	/* this function checks for the next line and in the file and replaces it
		with empty string or white space */
	public static String access(String words2)
	{
		File fileloc = new File(words2);
		String words="";
		String temp1;
		
		try
		{
			Scanner sc=new Scanner(fileloc);
			while(sc.hasNextLine())
			{
				words+=sc.nextLine();
			}
			words=words.replace("\n"," ");
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		temp1 = Func1(words);
		return temp1;

	}
/* this function calculates the final value of the string match percentage within
	the files */
public static float lcs3(int f1_wordlen,int f2_wordlen,int count2)
{
	float intialval = (2*count2);
	float terminalval = intialval /(f1_wordlen+f2_wordlen);
	return terminalval*100;
}

/* this is the class which contains main method and functions to access the 
	directory of file folder*/
public class lcs1 extends Lcs2
{
	public static void main(String[] args) 
	{
		Lcs2 op = new Lcs2();
		Scanner sc = new Scanner(System.in);
		String route = sc.nextLine();
		File location = new File(route);
		File[] files = location.listFiles();
		ArrayList<String> filelist=new ArrayList<String>();
		String f1_words;
		String f2_words;
		int count2,f1_wordlen,f2_wordlen;
		float content;
		for(File i:files)// navigating through each and every file
		{
			if(i.getName().endsWith("txt"))
			{
				filelist.add(i.getName().toString());
					
			}
		}
		for (String str:filelist) {
			System.out.print("    	 	  "+str+"     ");//printing the file names
			
		}
		
		System.out.println("     ");//leaving spaces to differentiate the values
		System.out.println("	 ");
		for(String str:filelist)
		{
			System.out.print(str);
			for(String j:filelist)
			{
				if(str!=j)
				{
	
					f1_words = access(str);
					f2_words = access(j);
					
			
					f1_wordlen = f1_words.length();
					f2_wordlen = f2_words.length();
					
					count2 = lcs1(f1_words,f2_words);
					content =  lcs3(f1_wordlen,f2_wordlen,count2);
					System.out.printf("		%.2f",content);
					
				}
				else
				{
					System.out.printf("		0.00" );;
				}
			}
			System.out.println("    ");
			System.out.println("	");
		}
	}
}

