import java.util.Scanner;
import java.util.List;


public class BootCampProject {
    Scanner scan =  new Scanner(System.in);
	 public static void main(String[] args) {
		int[] lib =  encrptionLibrary();
		 char g = 'A';
		 int t = Character.getNumericValue(g);
		print(t);
	 }
	 public static int[] encrptionLibrary(){
		 
	  int[] library = new int[255];
	  for(int i = 0; i < library.length; i++)
	  {
		 int nextByte = (int)(Math.random() * 256);
		 if(contains(nextByte,library)) {
			 i--;
		 }
		 else {
			 
			 library[i] = nextByte;
		 }
	  }
	  
	  return(library);
	  
	 }
	 public static boolean contains(int value,int[] subject) {
		 for(int t : subject) {
			
			 if(t == value) {
				 
				 return(true);
			 }
			 else {
				 
				 return(false);
			 }
		 }
		 return(false);
	 }
    public static void print(Object message) {
    	System.out.println(message);
    }
}
