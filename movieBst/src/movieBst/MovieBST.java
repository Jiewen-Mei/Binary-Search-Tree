/**This program can read in CSV files and sort data in a binary tree in alphabetical order
 * and it can print out data in ranges or in whole
 */
package movieBst;

import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;
import java.io.*;
/**
 * @author Jiewen Mei
 *
 */
public class MovieBST {
	private Movie root;
	public MovieBST() {
		root=null;
	}                        //constructor
	
	
	//it simply called addMovie() method and pass the root.
	public void add(Movie movie) {
		root=addMovie(root,movie);
	}
	
//-----------------------------------------------------------------------------------------
/*	addMovie(): take an movie object, which is the root of the tree, and a new movie object
	*Function: if new string is smaller than the root, it keeps calling itself of the left subtree;
	*			otherwise, it keeps calling itself of the right subtree;
	*			all data are sorted in alphabetical order
	* output: return the unchanged movie object for tracking
	*/
	public Movie addMovie(Movie root, Movie movie) {
		if(root==null) {
			root=movie;
			return root;
		}
			if(movie.getTitle().compareTo(root.getTitle())<0) {
				root.left=addMovie(root.left,movie);
			}
			else if(movie.getTitle().compareTo(root.getTitle())>0) {
				root.right=addMovie(root.right,movie);
			}
		return root;
	}//close addMovie method;
	
//-----------------------------------------------------------------------------------------	
/*	readIn(); takes a file address for reading in data
 * 	function: read in data line by line; 
 * 			  has two main fuctions to deal with three formats of Strings as below:
 * 			* 984,"Pompatus of Love, The (1996)",Comedy|Drama
 * 			* 986,Fly Away Home (1996),Adventure|Children
 * 			* 780,Independence Day (a.k.a. ID4) (1996)
 * 			calling add(Movie) by passing the info with right formats
 */
	public void readIn(File onefile)throws Exception {
		Scanner sc = new Scanner(onefile);
		sc.nextLine();
		String[] tmp0=new String[5];      //tmp0, tmp1 are used for split up the csv line into string literals
		String[] tmp1=new String[5];  
		String[] genres=new String[5];  
		int check=0, id=0,year=0;        //check is used for detect the strings which have more than one pair of ();
		String t="";
		while(sc.hasNext()) {
			String s=sc.nextLine();
			check=s.indexOf("(");
			check=s.indexOf("(",check+1);
			if(s.contains("\"")||check>0) {          //this if block is for lines with "" or more than one ()
				int lastcomma=s.lastIndexOf(",");   //basically, strings literals are retrieved by tracking comma and () positions in the String;
				int firstcomma=s.indexOf(",");
				genres=s.substring(lastcomma).split("|");
				id=Integer.parseInt(s.substring(0,firstcomma));
				int lastP=s.lastIndexOf("(");
				year=Integer.parseInt(s.substring(lastP+1,lastcomma-2));
				t=s.substring(firstcomma+1,lastP);
				t=t.replace("\"","");
				t=t.trim();
			}
			else {                           //else block is for standard csv, it simply retrieves data using split();
			tmp0=s.split(",");
			id=Integer.parseInt(tmp0[0]);
			tmp1=tmp0[1].split("\\(");
			t=tmp1[0];
			t=t.trim();
			year=Integer.parseInt(tmp1[1].replace(")",""));
			genres=tmp0[2].split("|");
			}
			add(new Movie(t,year,genres,id));
		}
		sc.close();
	}//close readIn method

//-----------------------------------------------------------------------------------------	
/*subset(): taking in a Set<String> object, movie titles, and the Movie root from the tree
 * function: using recursion, go to the bottom left, then jump up to go to the right, and jump back up to the last one
			in such a process, only titles that are in the range are allowed to add to the set.
*/
	
	public Set<String> subset(Set<String> sample, Movie root, String start, String end) {
		if(root==null) {
			return null;
		}
		subset(sample,root.right,start,end);
            if(root.getTitle().compareTo(start)>=0 &&root.getTitle().compareTo(end)<=0 ) {
            	sample.add(root.getTitle());
            }
            subset(sample,root.left,start,end); 
		return sample;
	}//close subset method
	
//---------------------------------------------------------------------
/* inOrder() and inOrder(Movie node)
 * these two methods are used to display all data in the binary tree in order(the same algorithm as the subset)
 * it was meant to help track
 */
	public void inOrder() {
		inOrder(root);
	  }

	public void inOrder(Movie node) {
	    if (node == null) {
	      return;
	    }

	    inOrder(node.left);
	    System.out.println(node.getTitle());
	    inOrder(node.right);
	  }
//------------------------------------------------------------------------------------
/*main method: can read in multiple files and pass it to the readIn in the BST one by one;
 * 			and simply print out data as required
 */

	public static void main(String[] args)throws Exception {
		MovieBST data=new MovieBST();
		File filesaddress = new File("filesAddress.txt");
		Scanner scanner=new Scanner(filesaddress);
		while(scanner.hasNext()) {
			data.readIn(new File(scanner.next()));
		}
		File p = new File("movie report.txt");
		PrintWriter output=new PrintWriter(p);
		data.inOrder();
		Set<String> sample1=new TreeSet<String>();
		Set<String> sample2=new TreeSet<String>();
		Set<String> sample3=new TreeSet<String>();
		sample1 = data.subset(sample1,data.root, "Toy Story", "When Night Is Falling");
		sample2 = data.subset(sample2,data.root, "Balto", "Cutthroat Island");
		sample3 = data.subset(sample3,data.root, "Lassie", "Old Yeller");
		output.println("Sameple1: from Toy Story to When Night Is Falling");
		output.println();
		for(String title: sample1) {
			output.println(title);
		}
		output.println();
		output.println();
		output.println();
		output.println("Sameple2: from Balto to cutthroat Island");
		for(String title: sample2) {
			output.println(title);
		}
		output.println();
		output.println();
		output.println("Sameple3: from Lassie to Old Yeller");
		output.println();
		for(String title: sample3) {
			output.println(title);
	}
		output.close();
		scanner.close();
	}
}//close MovieBST class.
