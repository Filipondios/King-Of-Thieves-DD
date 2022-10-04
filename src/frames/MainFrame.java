package frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**This will be the main Frame for the application. Called by {@link InitFrame}, this window
 * sets an area where the user can open different dungeons and place traps and other stuff into
 * the workspace created in this Frame.
 * @author Filipondios, Hagernaut 
 * @version 03.10.2022**/
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements KeyListener{

	public static final boolean[] BOARD_COMPOSITION = new boolean[112];

	/**Method that starts all the configurations for the Frame. **/
	public MainFrame() {
		setIconImage(new ImageIcon("resources/images/basic/icon.gif").getImage());
		this.setTitle("King of Thieves Dungeon Dessigner");
		this.setSize(960,540);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setJMenuBar(createMenubar());
		this.add(new BoardPanel(), BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		this.setVisible(true);
	}
		
	private JMenuBar createMenubar() {
		/* Create a menu bar for the main application */
		/* Add items to the menu-bar */
		JMenu index1 = new JMenu(); index1.setText("Options");
		JMenu index2 = new JMenu(); index2.setText("Dungeon Default Stuff");
		JMenu index3 = new JMenu(); index3.setText("Traps");

		/* Add sub-items to the first item of the menu-bar */
		JMenuItem item1s1 = new JMenuItem("Save dungeon as...");
		JMenuItem item1s2 = new JMenuItem("Exit");
		index1.add(item1s1);
		index1.add(item1s2);
		
		/* Adds a listener to the third sub-item of the first item of the menu-bar
		 * (Exit). It finishes the current program. */
		item1s2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				System.exit(0);
			}
		});			
		
		/* Add sub-items to the second item of the menu-bar */
		JMenuItem item2s1 = new JMenuItem("Totem");
		JMenuItem item2s2 = new JMenuItem("Door");
		JMenuItem item2s3 = new JMenuItem("Gravity Switch");
		JMenuItem item2s4 = new JMenuItem("Platform");
		JMenuItem item2s5 = new JMenuItem("Trampoline");
		
		item2s1.setIcon(new ImageIcon("resources/images/items/totem.png"));
		item2s2.setIcon(new ImageIcon("resources/images/items/door.png"));
		item2s3.setIcon(new ImageIcon("resources/images/items/gravity.png"));
		item2s4.setIcon(new ImageIcon("resources/images/items/platform.png"));
		item2s5.setIcon(new ImageIcon("resources/images/items/trampoline.png"));
		
		index2.add(item2s1);
		index2.add(item2s2);
		index2.add(item2s3);
		index2.add(item2s4);
		index2.add(item2s5);

		/* Add sub-items to the second item of the menu-bar */
		JMenuItem item3s1 = new JMenuItem("Saw");
		JMenuItem item3s2 = new JMenuItem("Red Guard");
		JMenuItem item3s3 = new JMenuItem("Cannon");
		JMenuItem item3s4 = new JMenuItem("Seeker Bird");
		JMenuItem item3s5 = new JMenuItem("Homing Cannon");
		JMenuItem item3s6 = new JMenuItem("Spinner");
		JMenuItem item3s7 = new JMenuItem("Ricochet");
		JMenuItem item3s8 = new JMenuItem("Lil' Scorcher");
		JMenuItem item3s9 = new JMenuItem("Warder");
		JMenuItem item3s10 = new JMenuItem("Roaster");
		JMenuItem item3s11 = new JMenuItem("Bloodhound");
		
		
		item3s1.setIcon(new ImageIcon("resources/images/traps/saw.gif"));
		item3s2.setIcon(new ImageIcon("resources/images/traps/redguard.gif"));
		item3s3.setIcon(new ImageIcon("resources/images/traps/cannon.gif"));
		item3s4.setIcon(new ImageIcon("resources/images/traps/fly.gif"));
		item3s5.setIcon(new ImageIcon("resources/images/traps/homingcannon.gif"));
		item3s6.setIcon(new ImageIcon("resources/images/traps/spinner.gif"));
		item3s7.setIcon(new ImageIcon("resources/images/traps/ricochet.gif"));
		item3s8.setIcon(new ImageIcon("resources/images/traps/dragon.gif"));
		item3s9.setIcon(new ImageIcon("resources/images/traps/warder.gif"));
		item3s10.setIcon(new ImageIcon("resources/images/traps/roaster.gif"));
		item3s11.setIcon(new ImageIcon("resources/images/traps/bh.gif"));
		
		index3.add(item3s1);
		index3.add(item3s2);
		index3.add(item3s3);
		index3.add(item3s4);
		index3.add(item3s5);
		index3.add(item3s6);
		index3.add(item3s7);
		index3.add(item3s8);
		index3.add(item3s9);
		index3.add(item3s10);
		index3.add(item3s11);
		
		/* Adds all the menu-bar items and the menu-bar to the frame*/
		JMenuBar menu = new JMenuBar();
		menu.add(index1);
		menu.add(index2);
		menu.add(index3);
		return menu;
	}
	
	/**Method that any number that represents a dungeon represents a real dungeon.
	 * @author Filipondios
	 * @return True if the number represents a real dungeon, false if dont.*/
	/*private boolean isDungeon(int dungeonNumber) {
		int[] notDungeons = {50,51,55,59,60,85,86};
		for (int e : notDungeons)
			if (dungeonNumber==e)
				return false;
		return true;
	}*/
	
	private final class BoardPanel extends JPanel{
		public BoardPanel() {
			super(new GridLayout(8,14));
			for (int i = 0; i < 112; i++)
				add(new TilePanel(this,i));				
		}
	}

	private final class TilePanel extends JPanel implements MouseListener{

		public TilePanel(final BoardPanel boardPanel, final int tileId) {
			this.setLayout(new GridBagLayout());
			assignColor(tileId);
			this.addMouseListener(this);
		}

		private void assignColor(final int tileId) {
			setBackground(Color.white);
			setBorder(new LineBorder(Color.BLACK));
			MainFrame.BOARD_COMPOSITION[tileId]=false;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			setBackground(Color.BLACK);			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	public void keyTyped(KeyEvent e) {/*Nothing TODO*/}
	public void keyReleased(KeyEvent e) {/*Nothing TODO*/}
	public void keyPressed(KeyEvent e) {/*Nothing TODO*/}
}
