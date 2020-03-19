package movieBst;

public class Movie {
	Movie left, right;
	private String title;
	private int releaseYear;
	private String[] genres; 
	private int movieID;
	public Movie(String t, int y, String[] g, int id) {
		 title=t;
		 releaseYear=y;
		 genres=g;
		 movieID=id;
		 left=null;
		 right=null;
	 }
	 public String getTitle() {
		 return title;
	 }
}
