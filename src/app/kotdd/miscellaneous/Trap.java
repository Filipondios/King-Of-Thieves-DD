package app.kotdd.miscellaneous;

/**Class that storages the data of a trap. This class extends from {@link Item} because both classes share the
 * attributes id and dimension.
 * @author Filipondios, Haggernaut
 * @version 03.12.2022*/
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

	/** Specifies if the trap can move (In kot, when a trap has the yellow movement
	 *  line and can move arround the dungeon, like the rg or the spinner). */
	public boolean can_Move(){
		return this.can_move;
	}

	/** Specifies if the trap can rotate */
	public boolean can_Rotate(){
		return this.can_rotate;
	}
}
