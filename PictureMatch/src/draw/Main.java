package draw;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.imageio.ImageIO;

import gene.Population;
import gene.IndividualImage;
import gene.Parameters;

public class Main {
	private final static Logger logger = Logger.getLogger("Genetic Algorithm");
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Image img = ImageIO.read(new File("target1.jpg"));
		BufferedImage bi1 = (BufferedImage) img;
		IndividualImage[] rst = new IndividualImage[2];
		Parameters p = new Parameters(bi1.getWidth(),bi1.getHeight(),20,100,3,bi1,2);
		FileHandler fileHandler = new FileHandler("log.txt");
		SimpleFormatter sf = new SimpleFormatter();
		fileHandler.setFormatter(sf);
		logger.addHandler(fileHandler);
		Population gen = new Population(p);
		int count = 1;
		rst[0] = gen.getPQ().peek();
		double iniFitness = rst[0].fitness();
		while(gen.getPQ().peek().fitness() > iniFitness * 0.55) {
			gen.evolution();
			logging(count, gen.getPQ().peek());
			count++;
		}
		rst[1] = gen.getPQ().peek();
		File outputfile = new File("final.png");
		ImageIO.write(gen.getPQ().peek().img, "png", outputfile);

	}
	
	private static void logging(int count, IndividualImage img) throws IOException {
		LogRecord lr = new LogRecord(Level.INFO, "Generation: " + count + ". Best Fitness: " + img.fitness() + ".");
		logger.log(lr);
		if(count % 50 == 0 || count == 1) {
			String fileName = "Generation" + count + ".png";
			File outputfile = new File(fileName);
			ImageIO.write(img.img, "png", outputfile);
		}
	
	}
	

}
