package gene;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;
/*
 * 4. a sort function (priority queue is best)--to order the organisms by their fitness function;
 * 		the getPQ method is the priority queue to get the best image with the lowest fitness value.
 * 
 * 5. n evolution mechanism--this takes care of the seeding of generation 0, and the births and deaths between generation N and N+1;
 * 		the evolution method uses both sexual and asexual reproduction to produce the next generation
 */
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
	
	
	public IndividualImage crossOver(IndividualImage first, IndividualImage second) {
		IndividualImage img = new IndividualImage(parameter,false);
		for(int i = 0; i < this.parameter.geneNum; i++) {
			if(this.random.nextDouble() > .5) {
				Polygon polygon = first.dna.get(i);
				img.dna.add(polygon);
			}else {
				Polygon polygon = second.dna.get(i);
				img.dna.add(polygon);
			}
		}
		img.mutatePolygon();
		return img;
	}
	
	public void evolution() {
		ArrayList<IndividualImage> childrenList = new ArrayList<>();
		PriorityQueue<IndividualImage> pq = getPQ();
		IndividualImage best = pq.peek();
		IndividualImage first = pq.poll();
		IndividualImage second = pq.poll();
		childrenList.add(best);
		while(childrenList.size() < this.imageList.size() / 2) {
			childrenList.add(IndividualImage.mutateAndCreate(best));
		}
		while (childrenList.size() < this.imageList.size()) {
			IndividualImage child = crossOver(first,second);
			childrenList.add(child);
		}
		
		this.imageList = childrenList;
	}
	
	

	
	
}
