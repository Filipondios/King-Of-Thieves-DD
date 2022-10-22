package app.types;

/**Class that storages the data of a item.
 * @author Filipondios, Haggernaut*/
public class Item {
	/** String that represents the id of a item. For example: Door, Platform... */
	private String id;
	/** Array of integers that will have 2 positions only. In dimension[0] will be storaged
	* the width and in dimension[1] the height of the image that represents the item.*/
	private int dimension[];

	/**Constructor with fields.
	 * @param id String that represents the id of the item
	 * @param dimension Integer array that storages the width and height of the item.*/
	public Item(String id, int[] dimension) {
		this.id = id;
		this.dimension = dimension;
	}
	
	/* Returns the width of the item */
	public int getWidth() {
		return (dimension == null)? -1 : dimension[0];
	}
	
	/* Returns the height of the item */
	public int getHeight() {
		return (dimension == null)? -1 : dimension[1];
	}
	
	@Override
	public String toString() {
		return (dimension==null)? "Item: " + id :
			"Item: " + id + " <" + dimension[0]+","+dimension[1] + ">";
	}
}
