package gene;

import java.awt.image.BufferedImage;
import java.util.Random;

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
	
	public float getProbability() {
		float min = 0.001f;
		float max = 0.01f;
		Random random = new Random();
		return min + random.nextFloat() * (max - min);
	}
	
	
}
