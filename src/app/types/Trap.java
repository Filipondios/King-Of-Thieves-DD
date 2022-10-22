package app.types;

public class Trap extends Item {

	private boolean can_move;
	private boolean can_rotate;
	
	/*public Trap() {
		super(null,null);
		this.can_move = false;
		this.can_rotate = false;
	}*/
	
	public Trap(boolean can_move, boolean rotable, String id, int[] dimension) {
		super(id,dimension);
		this.can_move = can_move;
		this.can_rotate = rotable;
	}
	
	@Override
	public String toString() {
		return 	"Can move: " + can_move +"\n" + 
				"Is rotable: " + can_rotate + "\n" +
				"Dimension: <" + super.getWidth() +"," + super.getHeight() +">";
	}
}
