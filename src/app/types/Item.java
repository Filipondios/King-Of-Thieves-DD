package app.types;

public class Item {
	
	private String id;
	private int dimension[];

	public Item(String id, int[] dimension) {
		this.id = id;
		this.dimension = dimension;
	}

	public int getWidth() {
		return (dimension == null)? -1 : dimension[0];
	}
	
	public int getHeight() {
		return (dimension == null)? -1 : dimension[1];
	}
	
	@Override
	public String toString() {
		return (dimension==null)? "Item: " + id :
			"Item: " + id + " <" + dimension[0]+","+dimension[1] + ">";
	}
}