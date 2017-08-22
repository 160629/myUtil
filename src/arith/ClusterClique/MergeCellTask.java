package arith.ClusterClique;

public class MergeCellTask implements Runnable{

	@Override
	public void run() {
		FacetSet fs = null;

		while ((fs = ClusterCoordinator.GetInstance().FacetSetQueue.poll()) != null) {
			Facet rstFacet = fs.GetCommonFacet();
			ClusterCoordinator.GetInstance().FacetQueue.offer(rstFacet);
		}
	}
	
	public static void main(String[] args) {
		Thread t = new Thread(new MergeCellTask());
		t.run();
		
	}
}