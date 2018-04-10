package gene;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

public class Population {
	private Parameters parameter;
	public ArrayList<IndividualImage> imageList;
	private Random random;
	public Population(Parameters parameter) {
		this.parameter = parameter;
		this.imageList = new ArrayList<IndividualImage>();
		for(int i = 0; i < parameter.populationNum; i++) {
			this.imageList.add(new IndividualImage(parameter));
		}
		this.random = new Random();
	}
	
	private PriorityQueue<IndividualImage> getPQ() {
		PriorityQueue<IndividualImage> pq = new PriorityQueue<IndividualImage>(imageList.size(), 
				(img1,img2) -> (int) (img1.fitness() - img2.fitness()));
		pq.addAll(imageList);
		return pq;
	}
	
	public IndividualImage selection() {
		IndividualImage bestImage = getPQ().peek();
		return bestImage;
	}
	
	public IndividualImage crossOver() {
		IndividualImage img = new IndividualImage(parameter);
		img.dna.clear();
		IndividualImage first = getPQ().poll();
		IndividualImage second = getPQ().poll();
		for(int i = 0; i < this.parameter.geneNum; i++) {
			if(this.random.nextDouble() > .5) {
				Polygon polygon = first.dna.get(i);
				polygon.mutatePolygon(this.parameter, this.parameter.getProbability(0.001f, 0.01f));
				img.dna.add(polygon);
			}else {
				Polygon polygon = second.dna.get(i);
				polygon.mutatePolygon(this.parameter, this.parameter.getProbability(0.001f, 0.01f));
				img.dna.add(polygon);
			}
		}
		return img;
	}
}
