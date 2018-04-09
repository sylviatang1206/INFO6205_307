package gene;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Population {
	private Parameters parameter;
	public ArrayList<IndividualImage> imageList;
	
	public Population(Parameters parameter) {
		this.parameter = parameter;
		this.imageList = new ArrayList<IndividualImage>();
		for(int i = 0; i < parameter.populationNum; i++) {
			this.imageList.add(new IndividualImage(parameter));
		}
	}
	
	public IndividualImage selection() {
		PriorityQueue<IndividualImage> pq = new PriorityQueue<IndividualImage>(imageList.size(), 
				(img1,img2) -> (int) (img1.fitness() - img2.fitness()));
		pq.addAll(imageList);
		IndividualImage bestImage = pq.peek();
		return bestImage;
	}
}
