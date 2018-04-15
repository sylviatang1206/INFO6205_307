package gene;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

/*
 * 1. a genetic code (or use the four bases of DNA for simplicity) and 
 *    a random generator/mutator of such codes;
 *    
 *		a. Each individual image (DNA) is consists of various of polygons (Gene)
 *    	b. Each polygon is randomly generated with random size, color and position
 * 
 * 2. gene expression: how do individual genes code for particular traits 
 * 	  --a symbol table using a hash function?
 * 
 * 		a. Each polygon(Gene) has a draw function which is a gene expression function
 * 		b. The expression function converts the polygon with its color, size and position 
 * 		   to graphic and then put into buffered image.
 */ 

public class Polygon {
	private Color color;
	private int[] c;
	private int[] x;
	private int[] y;
	private int z;
	private Random random;
	
	
	public Polygon(int[] color, int[] x, int[] y, int z) {
		this.color = new Color(color[0],color[1],color[2],color[3]);
		this.x = x;
		this.y = y;
		this.z = z;
		this.c = color;
		this.random = new Random();
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillPolygon(x, y, x.length);
		
	}
	
	public Polygon copy() {
		return new Polygon(c,x,y,z);
	}
	
	public void mutatePolygon(Parameters parameter, double probability) {
		int maxDelta = ((parameter.width + parameter.height) / 2) / 2;
		int halfDelta = maxDelta / 2 + 2;
		int maxColorDelta = 100;
		int halfMaxColorDelta = maxColorDelta / 2;
		int colorMutation = random.nextInt(4);
		
		if(random.nextDouble() > probability) return;
//		System.out.println("change occours");
		if(random.nextBoolean()) {
			
			// mutate color
			switch(colorMutation) {
				case 0:
					this.c[0] = bond(this.c[0] + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255);
					break;
				case 1:
					this.c[1] = bond(this.c[1] + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255);
					break;
				case 2:
					this.c[2] = bond(this.c[2] + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255);
					break;
				case 3:
					this.c[3] = bond(this.c[3] + random.nextInt(maxColorDelta) - halfMaxColorDelta, 0, 255);
					break;
			}
			this.color = new Color(c[0],c[1],c[2],c[3]);
		}else {
			
			//mutate coordinates of point in polygon 
			if(random.nextBoolean()) {
				int polygonIndex = random.nextInt(this.x.length);
				this.x[polygonIndex] = bond(this.x[polygonIndex] + random.nextInt(maxDelta) - halfDelta, 0, parameter.width);
				this.y[polygonIndex] = bond(this.y[polygonIndex] + random.nextInt(maxDelta) - halfDelta, 0, parameter.height);
			}else {
				this.z = random.nextInt(1000);
			}
		}

	}
	
	private int bond(int val, int min, int max) {
		if(val < min) return min;
		else if(val > max) return max;
		else return val;
	}
	

}
