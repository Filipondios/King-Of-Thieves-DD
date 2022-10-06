package frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import filipondiosUtils.Pair;
import traps.*;

/**This will be the main Frame for the application. Called by {@link InitFrame}, this window
 * sets an area where the user can open different dungeons and place traps and other stuff into
 * the workspace created in this Frame.
 * @author Filipondios, Hagernaut 
 * @version 03.10.2022**/
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements KeyListener{

	public static Integer[][] BOARD_COMPOSITION;
	BoardPanel board = new BoardPanel();

	/**Method that starts all the configurations for the Frame. **/
	public MainFrame() {
		setIconImage(new ImageIcon("resources/images/basic/icon.gif").getImage());
		this.setTitle("King of Thieves Dungeon Dessigner");
		this.setSize(1050,670);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setJMenuBar(createMenubar());
		this.add(board, BorderLayout.CENTER);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
		this.setVisible(true);
		importDungeon("resources/data/binBases/4.bs");
	}

	private JMenuBar createMenubar() {
		/* Create a menu bar for the main application */
		/* Add items to the menu-bar */
		JMenu index1 = new JMenu(); index1.setText("Options");
		JMenu index2 = new JMenu(); index2.setText("Dungeon Default Stuff");
		JMenu index3 = new JMenu(); index3.setText("Traps");

		/* Add sub-items to the first item of the menu-bar */
		JMenuItem item1s1 = new JMenuItem("Clear Dungeon and start from scratch");
		JMenuItem item1s2 = new JMenuItem("Save dungeon as...");
		JMenuItem item1s3 = new JMenuItem("Exit");
		index1.add(item1s1);
		index1.add(item1s2);	
		index1.add(item1s3);

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
		JMenuItem item3s12 = new JMenuItem("Electro cannon");

		item3s1.setIcon(new ImageIcon(Saw.icon.getImage().getScaledInstance(37,37,Image.SCALE_SMOOTH)));		
		item3s2.setIcon(new ImageIcon(RedGuard.icon.getImage().getScaledInstance(35,37,Image.SCALE_SMOOTH)));		
		item3s3.setIcon(new ImageIcon(Cannon.icon.getImage().getScaledInstance(40,37,Image.SCALE_SMOOTH)));
		item3s4.setIcon(new ImageIcon(Fly.icon.getImage().getScaledInstance(37,32,Image.SCALE_SMOOTH)));
		item3s5.setIcon(new ImageIcon(HCannon.icon.getImage().getScaledInstance(42,34,Image.SCALE_SMOOTH)));
		item3s6.setIcon(new ImageIcon(Spinner.icon.getImage().getScaledInstance(38,37,Image.SCALE_SMOOTH)));
		item3s7.setIcon(new ImageIcon(Ricochet.icon.getImage().getScaledInstance(36,37,Image.SCALE_SMOOTH)));
		item3s8.setIcon(new ImageIcon(Scorcher.icon.getImage().getScaledInstance(35,37,Image.SCALE_SMOOTH)));
		item3s9.setIcon(new ImageIcon(Warder.icon.getImage().getScaledInstance(36,37,Image.SCALE_SMOOTH)));
		item3s10.setIcon(new ImageIcon(Roaster.icon.getImage().getScaledInstance(46,37,Image.SCALE_SMOOTH)));
		item3s11.setIcon(new ImageIcon(BHound.icon.getImage().getScaledInstance(29,35,Image.SCALE_SMOOTH)));
		item3s12.setIcon(new ImageIcon(ECannon.icon.getImage().getScaledInstance(23,37,Image.SCALE_SMOOTH)));

		index3.add(item3s1);  index3.add(item3s2);
		index3.add(item3s3);  index3.add(item3s4);
		index3.add(item3s5);  index3.add(item3s6);
		index3.add(item3s7);  index3.add(item3s8);
		index3.add(item3s9);  index3.add(item3s10);
		index3.add(item3s11); index3.add(item3s12);

		/* Adds all the menu-bar items and the menu-bar to the frame*/
		JMenuBar menu = new JMenuBar();
		menu.add(index1);
		menu.add(index2);
		menu.add(index3);
	
		item1s1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				importDungeon("resources/data/binBases/blank.bs");
				board.setLayout(BOARD_COMPOSITION);
			}
		});	
		
		item1s2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				exportDungeon(BOARD_COMPOSITION);
			}
		});	
		
		item1s3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				System.exit(0);
			}
		});
		
		return menu;
	}


	public void exportDungeon(Integer[][] dungeon) {
		try {
			FileOutputStream fos = new FileOutputStream("resources/data/binBases/blank.bs");
			ObjectOutputStream ous = new ObjectOutputStream(fos);
			ous.writeObject(dungeon);
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void importDungeon(String file) {
		try {
			FileInputStream fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			BOARD_COMPOSITION = (Integer[][]) ois.readObject();
			
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}









	/**Method that checks if any number that represents a real dungeon.
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
		
		private TilePanel[] tiles = new TilePanel[160];
		
		public BoardPanel() {
			super(new GridLayout(10,16));
			for (int i = 0; i < 160; i++) {
				tiles[i] = new TilePanel(this,i);
				this.add(tiles[i]);
			}
		}
		
		public void setLayout(Integer[][] layout) {
			for (int i = 0; i < layout.length; i++) {
				for (int j = 0; j < layout[0].length; j++) {
					if(layout[i][j]==1) tiles[i*j].setBackground(Color.black);
					else tiles[i*j].setBackground(Color.white);
				}
			}
		}
	}

	private final class TilePanel extends JPanel {
		
		private static Pair<Integer, Integer> cc = new Pair<Integer,Integer>(0,0);
		private Pair<Integer, Integer> coordinate;
 		
		public TilePanel(BoardPanel panel, int tileID) {
			coordinate= new Pair<Integer, Integer>(cc.getKey(), cc.getValue());
			this.setBackground(assignColor());
			checkPositions();
			this.setBorder(new LineBorder(Color.BLACK));
		}
		
		private Color assignColor() {
			return (cc.getKey()==0 || cc.getKey() ==9 
					|| cc.getValue()==0 || cc.getValue()==15)? Color.black : Color.white;
		}
		
		private void checkPositions() {
			if(cc.getValue()==15) {
				cc.setValue(0);
				cc.setKey(cc.getKey()+1);
			} else cc.setValue(cc.getValue()+1);
		}
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void keyTyped(KeyEvent e) {/*Nothing TODO*/}
	public void keyReleased(KeyEvent e) {/*Nothing TODO*/}
	public void keyPressed(KeyEvent e) {/*Nothing TODO*/}
}
