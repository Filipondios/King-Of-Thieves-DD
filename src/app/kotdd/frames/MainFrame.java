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

/**This will be the main Frame for the application. This window sets an area where the user can open different dungeons
 * and place traps images and other stuff into it. The frame its made by two "layers": The first one, at the background
 * is made up by 160 square panels, that makes the dungeon grid and blocks. The second one, at the top, is a panel where
 * the user can place different images, each one made with the class {@link DragableLabel}. This frame also have a menu-bar
 * where the user can add Items and Traps images, import a dungeon and clear up the dungeon.
 * @author Filipondios, Hagernaut 
 * @version 22.10.2022**/
@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	public static ArrayList<Integer> BOARD_COMPOSITION = new ArrayList<>(160); // Dungeon "tiles"
	JLayeredPane frame_layers = new JLayeredPane(); // Application frame layers: one for the images and other for the "tiles"
	LayoutPanel blocks_panel = new LayoutPanel(); // Panel filled up with 160 tiles = dungeon layout
	ImagesPanel images_panel = new ImagesPanel(); // Panel where all the traps and items images are placed
	
	/**Method that starts all the configurations for the Frame. **/
	public MainFrame() {
		setIconImage(new ImageIcon("resources/images/basic/icon.gif").getImage());
		this.setTitle("King of Thieves Dungeon Dessigner");
		this.setSize(1050,670);
		this.setLayout(new BorderLayout());
		frame_layers.setSize(1050,670);
		blocks_panel.setBounds(0,0,1050,670);
		
		this.add(frame_layers);
		blocks_panel.setOpaque(true);		
		images_panel.setOpaque(false);
						
		frame_layers.add(blocks_panel,0,0);
		frame_layers.add(images_panel,1,0);
				
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
				images_panel.add(id.substring(21,id.indexOf("w")-1),true);
				images_panel.doLayout();
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
				images_panel.add(id.substring(21,id.indexOf(",w")),false);
				images_panel.doLayout();
			}
		};
		
		String[] traps_images = {"Saw","Red Guard","Cannon","Seeker Bird","Homing Cannon",
				"Spinner","Ricochet","Lil Scorcher","Warder","Roaster","BloodHound","Electro Cannon"};
				
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
				blocks_panel.setScheme(BOARD_COMPOSITION);
			}
		});	
		
		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				String str = JOptionPane.showInputDialog(null, "Type the base number:");
				if(str!=null) exportDungeon(BOARD_COMPOSITION,str);
			}
		});	
		
		clear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				images_panel.removeAll(); 
				images_panel.repaint();
			}
		});
		
		/* * * * * Adds all the options to the main menu-bar of the application * * * * */
		JMenuBar menu = new JMenuBar();
		menu.add(options);
		menu.add(items);
		menu.add(traps);
		return menu;
	}

	/** Method that saves into a file located in "resources/data/binBases" the object 
	 * BOARD_COMPOSITION that contains the "blocks" that makes the dungeon. This method
	 * wont be available for the users at the first versions, because in future versions,
	 * this mode will be added as a new "edit mode". 
	 * @param dungeon BOARD_COMPOSITION {@link ArrayList} that contains the dungeon blocks.
	 * @param iD String that represents the name of the file where the data is going to
	 * be saved.*/
	private void exportDungeon(Object dungeon, String iD) {
		try {
			ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream("resources/data/binBases/"+iD+".bs"));
			ous.writeObject(dungeon);
			ous.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/** Method that imports (overrides) with a {@link ArrayList} that contains the "blocks"
	 * of a dungeon and was storaged in a file in "resources/data/binBases" the
	 * current BOARD_COMPOSITION {@link ArrayList}. 
	 * @param path String that represents the name of the file where a dungeon is "saved".*/
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
