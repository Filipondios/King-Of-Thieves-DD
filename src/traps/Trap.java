package traps;

public abstract class Trap {
		
	protected static final String commonPath = "resources/images/traps/";
	protected static int trapsNumber = -11;
	
	public abstract boolean canRotate();
	public abstract boolean canMove();
}