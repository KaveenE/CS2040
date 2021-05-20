package convenienceClass;

public class Vertex {

	private Pair<Integer, Double> vertex;
	
	public Vertex(int index, double weight) {
		
		vertex = new Pair<>(index, weight);
	}
	
	public Vertex(int index, int weight) {
		this(index, weight+0.0);
	}



	public int getIndex() {
		return vertex.getFirst();
	}
	
	public double getWeight() {
		return vertex.getSecond();
	}
	
	@Override
	public String toString() {
		
		return String.format("Index:%d Weight:%.2f", getIndex(), getWeight());
	}
}