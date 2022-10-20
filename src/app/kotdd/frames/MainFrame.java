package app.kotdd.frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import app.kotdd.main.RunClass;
import app.kotdd.panels.ImagesPanel;
import app.kotdd.panels.LayoutPanel;

/**This will be the main Frame for the application. Called by {@link RunClass}, this window
 * sets an area where the user can open different dungeons and place traps and other stuff into
 * the workspace created in this Frame.
 * @author Filipondios, Hagernaut 
 * @version 20.10.2022**/
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static ArrayList<Integer> BOARD_COMPOSITION = new ArrayList<>(160); // Dungeon tiles
	JLayeredPane layers = new JLayeredPane(); // Application layers: one for the images and other for the tiles
	LayoutPanel board = new LayoutPanel(); // Panel fill up with 160 tiles = dungeon layout
	ImagesPanel imagePanel = new ImagesPanel(); // Panel where all the traps and items images are placed
	
	/**Method that starts all the configurations for the Frame. **/
	public MainFrame() {
		setIconImage(new ImageIcon("resources/images/basic/icon.gif").getImage());
		this.setTitle("King of Thieves Dungeon Dessigner");
		this.setSize(1050,670);
		this.setLayout(new BorderLayout());
		layers.setSize(1050,670);
		board.setBounds(0,0,1050,670);
		
		this.add(layers);
		board.setOpaque(true);		
		imagePanel.setOpaque(false);
						
		layers.add(board,0,0);
		layers.add(imagePanel,1,0);
				
		this.setJMenuBar(createMenubar());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/** Method that creates the menubar for the application */
	private JMenuBar createMenubar() {
		/* * * * * Add the basic options to the menu-bar * * * * */
		JMenu options = new JMenu("Options");
		JMenu items = new JMenu("Add Dungeon Default Stuff");
		JMenu traps = new JMenu("Add Traps");
		
		/* * * * * Add sub-items to the first item of the menu-bar * * * * */
		JMenuItem create = new JMenuItem("Import dungeon (From ID number or name)");
		JMenuItem save = new JMenuItem("Save dungeon as...");
		JMenuItem clear = new JMenuItem("Clear dungeon");
		options.add(create);
		options.add(save);
		options.add(clear);
		
		/* * * * * Make the items sub-menu * * * * */
		ActionListener items_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id  = e.paramString();
				imagePanel.add(id.substring(21,id.indexOf("w")-1),true);
				imagePanel.doLayout();
			}
		};
		
		String[] items_images = {"Totem","Door","Gravity Switch","Platform","Trampoline"};
		
		for (String id : items_images) {
			JMenuItem item = new JMenuItem(id);
			item.addActionListener(items_listener);
			items.add(item);
		}
		
		/* * * * * Make the traps sub-menu * * * * */
		ActionListener traps_listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id  = e.paramString();
				imagePanel.add(id.substring(21,id.indexOf(",w")),false);
				imagePanel.doLayout();
			}
		};
		
		String[] traps_images = {"Saw","Red Guard","Cannon","Seeker Bird","Homing Cannon",
				"Spinner","Ricochet","Lil' Scorcher","Warder","Roaster","Bloodhound","Electro cannon"};
				
		for (String id : traps_images) {
			JMenuItem trap = new JMenuItem(id);
			trap.addActionListener(traps_listener);
			traps.add(trap);
		}

		/* * * * * Add listeners to the options sub-menu * * * * */
		create.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) { 
				String str = JOptionPane.showInputDialog(null, "Type the base number/name to import.\n"
						+ "NOTE: If you want an empty dungeon, type 0.");
				importDungeon("resources/data/binBases/"+str+".bs");
				board.setScheme(BOARD_COMPOSITION);
			}
		});	
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String str = JOptionPane.showInputDialog(null, "Type the base number:");
				if(str!=null) exportDungeon(BOARD_COMPOSITION,str);
			}
		});	
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { imagePanel.removeAll(); }
		});
		
		/* * * * * Adds all the options to the main menu-bar of the application * * * * */
		JMenuBar menu = new JMenuBar();
		menu.add(options);
		menu.add(items);
		menu.add(traps);
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
}
