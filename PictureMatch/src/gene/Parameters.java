package gene;

import java.awt.image.BufferedImage;


public class Parameters {
	public int width;
	public int height;
	public int populationNum;
	public int geneNum;
	public int polygonMax;
	public BufferedImage targetImage;
	public int cutOff;
	
	public Parameters(int width, int height, int populationNum, int geneNum, int polygonMax, BufferedImage targetImage, int cutOff) {
		this.width = width;
		this.height = height;
		this.populationNum = populationNum;
		this.geneNum = geneNum;
		this.polygonMax = polygonMax;
		this.targetImage = targetImage;
		this.cutOff = cutOff;
	}
	
	
}
