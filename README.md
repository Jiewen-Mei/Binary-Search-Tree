# Binary-Search-Tree
two classes - binaryserachtree and a movie as node calss
This program can read in CSV files and sort data in a binary tree in alphabetical order and you can retrieve movie titles in the given ranges


in Movie Class: -with data fileds: title, release year, movieID and movies genres
                -and one getter methhod to return the movie title


in MovieBST Class: contains methods as :

##/*	addMovie(): take an movie object, which is the root of the tree, and a new movie object
	*Function: if new string is smaller than the root, it keeps calling itself of the left subtree;
	*			otherwise, it keeps calling itself of the right subtree;
	*			all data are sorted in alphabetical order
	* output: return the unchanged movie object for tracking
	*/

##/*	readIn(); takes a file address for reading in data
 * 	function: read in data line by line; 
 * 			  has two main fuctions to deal with three formats of Strings as below:
 * 			* 984,"Pompatus of Love, The (1996)",Comedy|Drama
 * 			* 986,Fly Away Home (1996),Adventure|Children
 * 			* 780,Independence Day (a.k.a. ID4) (1996)
 * 			calling add(Movie) by passing the info with right formats
 */
 
 ##/*subset(): taking in a Set<String> object, movie titles, and the Movie root from the tree
 * function: using recursion, go to the bottom left, then jump up to go to the right, and jump back up to the last one
			in such a process, only titles that are in the range are allowed to add to the set.
*/
  
------------------------ADDITONAL BUT IMPORTANT METHODS------------------------------------
##/* inOrder() and inOrder(Movie node)
 * these two methods are used to display all data in the binary tree in order(the same algorithm as the subset)
 * it was meant to help track
 */
 ------------------------------------------------------------------------------
 /*main method: can read in multiple files and pass it to the readIn in the BST one by one;
 * 			and simply print out data as required
 */
