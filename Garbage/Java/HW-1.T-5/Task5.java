/**
 * Title: 		Homework 1 Task 5
 * Data:		7.02.2010
 * Description:	Program generate random field (matrix) which elements have
 * 				boolean values. When field generated it found biggest area
 * 				which have only "false" elements.
 */
package task5;

/**
 * @author Anton Ishkov
 *
 */
public class Task5 {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// Define options
		final int  verLeght	= 10,							// Vertical length of the field (oY)
		     	   horLeght	= 10;							// Horizontal length of the field (oX)
	
		// Declare variables 
			  Area oprField = new Area(verLeght, horLeght),	// Investigated field 
		     	   curField	= new Area(verLeght, horLeght), // The field which contains
															// current area 
		 	       bigField	= new Area(verLeght, horLeght); // The field which contains
															// biggest area
		
		// Generate field 
		oprField.generate();

		// Print generated field
		System.out.println("Invisigated field (* - free area):");
		oprField.print();
		
		// Explore field and find biggest
		for(int i = 0; i < verLeght; i++) {
			for(int j = 0; j < verLeght; j++) {
				// If this point is free (has true value) 
				if(oprField.getPoint(i, j)) {
					// Expand to new layer area which have point(i,j) 
					curField = oprField.explore(i, j);

					// If area is bigger save it in new layer
					if(curField.getWeight() > bigField.getWeight()) {
						bigField = curField;
					}
				}
			}
		}

		// Print biggest field
		System.out.println("Biggest field:");
		bigField.print();		
	}
}