import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
class Bagofwords{
	HashMap<String,Integer> hm=new HashMap<String,Integer>();
	public String Func1(File f1)
	{
		/* 
			in these function,a new object of class string builder is created and also object of buffer reader 
			is created to read the contents in the file
		*/
		String str1=null;
		StringBuilder str=new StringBuilder();
		try{
			BufferedReader buf=new BufferedReader(new FileReader(f1));
			while ((str1= buf.readLine())!= null){  // if line (string ) is not empty
				str.append(str1); //appends content of file into string object
				str.append(" ");//space is added between the strings
			}
		}
		catch (Exception e){ // any exception is handled while appending the contents of file
			e.printStackTrace();
		}
		return str.toString();// typecastes the content of object into completely string
	}
	public String lower(String string1){
		/* String string1 is given as input*/
			
		
				string1=string1.toLowerCase(); //converts the content of sring into lowercase characters 
				string1= string1.replaceAll("[^\\w\\s]","");// replaces all special characters with nothing.
				return string1;
	}
	/*this function returns the dot product of the strings in the files*/
	int dot(HashMap<String,Integer> hm,HashMap<String,Integer> hm2)
	{
				int var=0;
				for(String k:hm.keySet()){
					for (String m:hm2.keySet()) {
				
						if((k).equals(m)==true){   
						  							/* if keys in first hashmap equals 
						  							to key within the second 
													hashmap,the no of keys are returned and
													 dot product is 
													calculated accordingly */
							int p=(Integer)hm.get(k);
							int q=(Integer)hm2.get(m);
							var=var+(p*q);

						}
					}
				}
		return var;
	}
	
	
	/* this function returns euclid formula for each key value */
	double form(HashMap<String,Integer> hm)
	{
			double var1=0.0;
			for(String k:hm.keySet()){
				var1=var1+Math.pow(hm.get(k),2);
			}
			var1=Math.sqrt(var1);
		return var1;
	}
	/*this function returns the word count of each and every word that appeared in the string*/
	public HashMap<String,Integer> map(String lines[])
	{
			int len1=lines.length;
			for (int i=0;i<len1;i++){ //iterating over the string
				if(!hm.containsKey(lines[i])){
					hm.put(lines[i],1); //if the word dosent repeates,assigining it to 1 as value
				}
				else
				{
					hm.put(lines[i],hm.get(lines[i])+1); //if the word repeats no of times,the word count gets updated
				}
			}	
		return hm;
	}
	
	// the below function splits the strings according to spaces
	public String[] frag(String s)
	{
			String a[]=new String[30];
			a=s.split(" ");
			return a;
	}
	public static void main(String[] args) {
		File[] fileList = new File[100];   //creating a filelist array with 100 as length
		Scanner in = new Scanner(System.in);
		String path=args[0];
		int l=0;
		try{
			/* the code with in the try block navigates through the directiry and checks for the 
			required files */
			File Directory = new File(path);
			File[] files = Directory.listFiles();
			for (File f : files){
				if (f.getName().endsWith(".txt")){
					fileList[l] = f; //appending each and every index to a specific file and incrementing.
					l++;
				}
			}
			}
		catch (Exception e) {
			e.printStackTrace();
		}
		/* creation of hashmap objects and class objects */
		HashMap<String,Integer> hm=new HashMap<String,Integer>();
		HashMap<String,Integer> hm2=new HashMap<String,Integer>();//hashmap objects
		String string1;
		String string2;
		Bagofwords f1=new Bagofwords();
		Bagofwords f2=new Bagofwords(); //class objects
		System.out.print("\t\t");
		for (int i=0;i<l;i++) {
			System.out.print(fileList[i].getName() + "\t"); //gets the name of file in the array
		}
		
		for(int i=0;i<l;i++) {
			System.out.println("\n");
			System.out.print(fileList[i].getName()+"\t");
			
			for (int j=0;j<l;j++) {
				string1=f1.Func1(fileList[i]);//files are sent to FileRading method to read the content
				string2=f2.Func1(fileList[j]);
				string1=f1.lower(string1);// the returned string is accessed to these methods by f1 and f2 objects
				string2=f2.lower(string2);				
				String lines[]=f1.frag(string1);//string array is being returned and sent to method that splits it
				String lines2[]=f2.frag(string2);
				hm=f1.map(lines); // counting frequency of words
				hm2=f2.map(lines2);
				int var=f2.dot(hm,hm2);// returning dot product of two files
				double var1=f1.form(hm);
				double var2=f2.form(hm2);
				double var3=var1*var2;
				double result=(var/var3)*100.0; // final value of bag of words.
				System.out.print(String.format("%.2f",result)+("\t\t"));
				hm.clear();
				hm2.clear();
			}
		}		
	}
}