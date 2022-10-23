package app.kotdd.types;

/**Class that storages the data of an item.
 * @author Filipondios, Haggernaut
 * @version 23.10.2022*/
public class Item {
	/** String that represents the id of an item. For example: Door, Platform... */
	private final String id;
	/** Array of integers that will have 2 positions only. In dimension[0] will be stored
	* the width and in dimension[1] the height of the image that represents the item.*/
	private final int[] dimension;

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

	public String getId(){
		return this.id;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Item item = (Item) o;
		return this.id.equals(item.id);
	}

	@Override
	public String toString() {
		return (dimension==null)? "Item: " + id :
			"Item: " + id + " <" + dimension[0]+","+dimension[1] + ">";
	}
}
