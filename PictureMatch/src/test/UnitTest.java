package test;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;

import gene.Parameters;
import gene.Population;

class UnitTest {

	@Test
	void test1() throws IOException {
		Image img = ImageIO.read(new File("target.jpg"));
		BufferedImage bi1 = (BufferedImage) img;
		Parameters p = new Parameters(bi1.getWidth(),bi1.getHeight(),20,100,3,bi1,2);
		Population gen = new Population(p);
		double init = gen.getPQ().peek().fitness();
		while(gen.getPQ().peek().fitness() > init * 0.5) {
			gen.evolution();
		}
		double result = gen.getPQ().peek().fitness();
		assert(result < 1200000);
	}
	
	@Test
	void test2() throws IOException {
		Image img = ImageIO.read(new File("target1.jpg"));
		BufferedImage bi1 = (BufferedImage) img;
		Parameters p = new Parameters(bi1.getWidth(),bi1.getHeight(),20,100,3,bi1,2);
		Population gen = new Population(p);
		double init = gen.getPQ().peek().fitness();
		while(gen.getPQ().peek().fitness() > init * 0.5) {
			gen.evolution();
		}
		double result = gen.getPQ().peek().fitness();
		assert(result < 600000);
	}
	
	

}
