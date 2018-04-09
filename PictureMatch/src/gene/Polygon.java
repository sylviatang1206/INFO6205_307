package gene;

import java.awt.Color;
import java.awt.Graphics;

public class Polygon {
	private Color color;
	private int[] c;
	private int[] x;
	private int[] y;
	private int z;
	
	public Polygon(int[] color, int[] x, int[] y, int z) {
		this.color = new Color(color[0],color[1],color[2],color[3]);
		this.x = x;
		this.y = y;
		this.z = z;
		this.c = color;
	}
	
	public void draw(Graphics g) {
		g.setColor(color);
		g.fillPolygon(x, y, x.length);
	}
	
	public Polygon copy() {
		return new Polygon(c,x,y,z);
	}
	
}
