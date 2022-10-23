package app.types;

/**Class that storages the data of a trap. This class extends from {@link Item} because both classes share the
 * attributes id and dimension.
 * @author Filipondios, Haggernaut*/
public class Trap extends Item {
	/** Boolean value that tells if the trap can move ( In the original King of Thieves game, 
	* when the trap has a yellow bar with the dot in the editor mode). */
	private final boolean can_move;
	/** Boolean value that tells if the trap can rotate ( In the original King of Thieves game, 
	* when you click the trap and rotates in the editor mode). */
	private final boolean can_rotate;
	
	/**Constructor using fields
	 * @param can_move Boolean value that tells if the trap can move
	 * @param can_rotate Boolean value that tells if the trap can rotate
	 * @param id String that represents the id of a trap
	 * @param dimension Integer array that contains the dimension of the trap*/
	public Trap(boolean can_move, boolean can_rotate, String id, int[] dimension) {
		super(id, dimension);
		this.can_move = can_move;
		this.can_rotate = can_rotate;
	}

	public boolean can_Move(){
		return this.can_move;
	}

	public boolean can_Rotate(){
		return this.can_rotate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Trap trap = (Trap) o;
		return super.getId().equals(trap.getId());
	}
	@Override
	public String toString() {
		return 	"Can move: " + can_move +"\n" + 
				"Can rotate: " + can_rotate + "\n" +
				"Dimension: <" + super.getWidth() +"," + super.getHeight() +">";
	}
}
