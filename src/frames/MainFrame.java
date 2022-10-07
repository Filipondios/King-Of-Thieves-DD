package frames;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import main.RunClass;
import traps.*;

/**This will be the main Frame for the application. Called by {@link RunClass}, this window
 * sets an area where the user can open different dungeons and place traps and other stuff into
 * the workspace created in this Frame.
 * @author Filipondios, Hagernaut 
 * @version 06.10.2022**/
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements KeyListener{

	protected static ArrayList<Integer> BOARD_COMPOSITION = new ArrayList<>(160);
	private BoardPanel board = new BoardPanel();

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
	}

	/** Method that creates a menubar for the application and adds it to the frame.*/
	private JMenuBar createMenubar() {
		/* Add items to the menu-bar */
		JMenu index1 = new JMenu(); index1.setText("Options");
		JMenu index2 = new JMenu(); index2.setText("Dungeon Default Stuff");
		JMenu index3 = new JMenu(); index3.setText("Traps");

		/* Add sub-items to the first item of the menu-bar */
		JMenuItem item1s1 = new JMenuItem("Import dungeon (From ID number or name)");
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
	
		/* Add listeners to the menu-bar items and sub-items */
		item1s1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				String str = JOptionPane.showInputDialog(null, "Type the base number/name to import.\n"
						+ "NOTE: If you want an empty dungeon, type 0.");
				importDungeon("resources/data/binBases/"+str+".bs");
				board.setScheme(BOARD_COMPOSITION);
			}
		});	
		
		item1s2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				String str = JOptionPane.showInputDialog(null, "Type the base number:");
				if(str!=null) exportDungeon(BOARD_COMPOSITION,str);
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

	private void exportDungeon(Object dungeon, String iD) {
		try {
			ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("resources/data/binBases/"+iD+".bs"));
			ous.writeObject(dungeon);
			ous.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void importDungeon(String path) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
			BOARD_COMPOSITION = (ArrayList<Integer>) ois.readObject();
			ois.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public void keyTyped(KeyEvent e) {/*Nothing TODO*/}
	public void keyReleased(KeyEvent e) {/*Nothing TODO*/}
	public void keyPressed(KeyEvent e) {/*Nothing TODO*/}
}
