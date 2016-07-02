package aufgabe4;


import java.util.Comparator;

public class IndegreeComparator implements Comparator<Node> {

	@Override
	public int compare(Node o1, Node o2) {
		// TODO implementieren und Rückgabewert anpassen
		int comparison = Integer.compare(o1.getIndegree(), o2.getIndegree());
		
		if (comparison == 0) {
			// if o1 has o2 as successor
			if (o1.getSuccessors().contains(o2)) {
				comparison = -1;
			// if o2 has o1 as succesor
			} else if (o2.getSuccessors().contains(o1)) {
				comparison = 1;
			} else {
				comparison = Integer.compare(o1.getId(), o2.getId());
			}
		}
		
		return comparison;
	}
}