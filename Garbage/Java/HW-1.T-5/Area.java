/**
 * Title: 		Boolean area
 * Data:		6.02.2010
 * Description:	The class contains a matrix of boolean values, its size and
 * 		 		number of true values.
 */
package task5;

import java.util.Random;

/**
 * @author Anton Ishkov
 *
 */
public class Area {
	private int		ver;		 	 // Vertical length 
	private int		hor;			 // Horizontal length
	private int		weight = 0;		 // Count of true values
	private boolean field[][];		 // Matrix

	public Area(int ver, int hor) {
		this.field = new boolean[ver][hor];

		this.ver = ver;
		this.hor = hor;
	}
	public void generate() {
		// Declare variables
		Random gen = new Random();
		
		// Generate boolean array
		for(byte i = 0; i < this.ver; i++) {
			for(byte j = 0; j < this.hor; j++) {
				this.field[i][j] = gen.nextBoolean();
			}
		}
			
		// Set weight
		this.setWeight();
	}
	public void print() {
		// Explore array
		for(byte i = 0; i < this.ver; i++) {
			// Print line
			for(byte j = 0; j < this.hor; j++) {
				if(this.field[i][j]) {
					System.out.print("[*]");
				} else {
					System.out.print("[ ]");
				}
			}
			
			// New line
			System.out.print("\n");
		}
	}
	public int getWeight() {
		return this.weight;
	}
	public void setPoint(int posY, int posX, boolean value) {
		this.field[posY][posX] = value;
	}
	public boolean getPoint(int posVer, int posHor) {
		return this.field[posVer][posHor];
	}	
	public Area explore(int hostY, int hostX) {
		// Declare variable
		Area    newField = new Area(this.ver, this.hor);
		boolean model 	 = this.field[hostY][hostX];
		
		// Explore point
		this.pointExplore(newField, hostY, hostX, model);
		
		// Count weight and return
		newField.setWeight();
		return newField;
	}
	public void setWeight() {	
		// Count "True" elements
		for(byte i = 0; i < this.ver; i++) {
			for(byte j = 0; j < this.hor; j++) {
				if(this.field[i][j]) {
					this.weight++;
				}
			}
		}	
	}	

	private void pointExplore(Area resultField, int posY, int posX, boolean model) {
		// If point accepted
		if(posY < this.ver && posX < this.hor && this.field[posY][posX] == model) {
			// Delete point from old field and add to new
			this.field[posY][posX] = !model;
			resultField.setPoint(posY, posX, true);

			// Go up
			if(posY > 0) {
				this.pointExplore(resultField, posY - 1, posX, model);
			}

			// Go left
			if(posX > 0) {
				this.pointExplore(resultField, posY, posX - 1, model);
			}
			
			// Go down
			if(posY < ver - 1) {
				this.pointExplore(resultField, posY + 1, posX, model);
			}
			
			// Go up
			if(posX < hor - 1) {
				this.pointExplore(resultField, posY, posX + 1, model);
			}
		}
	}
}