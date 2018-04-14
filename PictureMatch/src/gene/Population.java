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
			this.imageList.add(new IndividualImage(parameter,true));
		}
		this.random = new Random();
	}
	
	public PriorityQueue<IndividualImage> getPQ() {
		PriorityQueue<IndividualImage> pq = new PriorityQueue<IndividualImage>(imageList.size(), 
				(img1,img2) -> (int) (img1.fitness() - img2.fitness()));
		pq.addAll(imageList);
		return pq;
	}
	
//	public IndividualImage selection() {
//		IndividualImage bestImage = getPQ().peek();
//		return bestImage;
//	}
	
	public IndividualImage crossOver(IndividualImage first, IndividualImage second) {
		IndividualImage img = new IndividualImage(parameter,false);
//		img.dna.clear();
//		PriorityQueue<IndividualImage> pq = getPQ();
//		IndividualImage first = selection();
//		System.out.println("First Parent Image Fitness: " + first.fitness());
//		IndividualImage second = selection();
//		System.out.println("Second Parent Image Fitness: " + second.fitness());
		for(int i = 0; i < this.parameter.geneNum; i++) {
			if(this.random.nextDouble() > .5) {
				Polygon polygon = first.dna.get(i);
//				polygon.mutatePolygon(this.parameter, this.parameter.getProbability());
				img.dna.add(polygon);
			}else {
				Polygon polygon = second.dna.get(i);
//				polygon.mutatePolygon(this.parameter, this.parameter.getProbability());
				img.dna.add(polygon);
			}
		}
		img.mutatePolygon();
//		img.draw();
		return img;
	}
	
	public void evolution() {
//		long a = System.currentTimeMillis();
		ArrayList<IndividualImage> childrenList = new ArrayList<>();
		PriorityQueue<IndividualImage> pq = getPQ();
		IndividualImage best = pq.peek();
		IndividualImage first = pq.poll();
		IndividualImage second = pq.poll();
		childrenList.add(best);
//		while(childrenList.size() < this.imageList.size() / 2) {
//			childrenList.add(IndividualImage.mutateAndCreate(best));
//		}
		while (childrenList.size() < this.imageList.size()) {
			IndividualImage child = crossOver(first,second);
//			while(child.fitness() >= best.fitness()) child = crossOver(first,second);
			childrenList.add(child);
		}
		
		this.imageList = childrenList;
//		System.out.println("evlution: " + (System.currentTimeMillis() - a));
	}
	
	public IndividualImage selection() {
		int size = this.imageList.size();
		for(int i = 1; i < size; i++) {
			if(random.nextDouble() <= ((double)(size - i)) / ((double)(1.6 * size))) {
				return this.imageList.get(i);
			}
		}
		return getPQ().peek();
	}
	
	public IndividualImage copyImage(IndividualImage image) {
		IndividualImage copy = new IndividualImage(parameter,false);
		ArrayList<Polygon> copyDNA = new ArrayList<>();
		for(int i = 0; i < image.dna.size(); i++) {
			copyDNA.add(image.dna.get(i));
		}
		copy.dna = copyDNA;
		copy.draw();
		return copy;
	}
	
	
}
