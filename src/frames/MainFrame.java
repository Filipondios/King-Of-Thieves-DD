package frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import FilipondiosUtils.WindowUtils;

/**This will be the main Frame for the application. Called by {@link InitFrame}, this window
 * sets an area where the user can open different dungeons and place traps and other stuff into
 * the workspace created in this Frame.
 * @author Filipondios, Hagernaut 
 * @version 17.09.2022**/
@SuppressWarnings("serial")
public class MainFrame extends JFrame implements KeyListener{

	public static final boolean[] board = new boolean[112];

	/**Method that starts all the configurations for the Frame. **/
	public MainFrame() {
		setIconImage(new ImageIcon("resources/images/basic/icon.gif").getImage());
		this.setTitle("King of Thieves Dungeon Dessigner");
		this.setLayout(new BorderLayout());
		this.setSize(resizeWindowToGrid(WindowUtils.screenHeight/2));
		this.setLocationRelativeTo(null);
		this.setJMenuBar(createMenubar());
		this.add(new BoardPanel(), BorderLayout.CENTER);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.addKeyListener(this);
	}
	
	private Dimension resizeWindowToGrid(final int height) {
		return new Dimension(height/8*14,height);
	}
	
	private JMenuBar createMenubar() {
		/* Create a menu bar for the main application */
		/* Add items to the menu-bar */
		JMenu index1 = new JMenu(); index1.setText("Options");
		JMenu index2 = new JMenu(); index2.setText("Dungeon Default Stuff");
		JMenu index3 = new JMenu(); index3.setText("Traps");

		/* Add sub-items to the first item of the menu-bar */
		JMenuItem item1s1 = new JMenuItem("Save dungeon as...");
		JMenuItem item1s2 = new JMenuItem("Restart Application");
		JMenuItem item1s3 = new JMenuItem("Exit");
		index1.add(item1s1);
		index1.add(item1s2);
		index1.add(item1s3);
		
		/* Adds a listener to the second sub-item of the first item of the menu-bar
		 * (Restart). It restarts the current program. To do so, we search the binary file
		 * of Java. In Windows, it should be like C:\Program Files\Java\jre1.8.0_161\bin\java,
		 * and in Linux should be like /usr/bin/java. Then we see the current path of the running
		 * .jar file, and then it runs this file and closes the app. */
		item1s2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
				File jarDir = null;
				String jarFile = "";

				try {
					jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").toURI());
				} catch (URISyntaxException e1) { e1.printStackTrace(); }

				/* Sometimes, depending on the current jre version, doing the last instruction in the
				 * try-catch, the obtained File could be the container directory where the .jar file is,
				 * or the .jar file */
				if (jarDir.isFile()) {
					jarFile = jarDir.toString();
				}else {
					//Lists the directory where the .jar file is.
					String[] dir = jarDir.list();

					//Obtain the name of the file
					for (String obj : dir) {
						if (obj.contains(".jar")) {
							jarFile = obj; break;
						}
					}
				}

				/* Runs the restart command:
				 * - Windows: C:\Program Files\Java\jre1.8.0_161\bin\java -jar C:\path\to\the\current\jar\file
				 * - Linux: /usr/bin/java -jar /path/to/the/current/jar/file
				 * This can be also be solved as the command java -jar /path/to/the/current/jar/file */
				final ArrayList<String> command = new ArrayList<String>();
				command.add(javaBin);
				command.add("-jar");
				command.add(jarDir+ File.separator +jarFile);

				final ProcessBuilder builder = new ProcessBuilder(command);
				try {
					builder.start();
				} catch (IOException e1) { e1.printStackTrace(); }
				System.exit(0);
			}
		});

		/* Adds a listener to the third sub-item of the first item of the menu-bar
		 * (Exit). It finishes the current program. */
		item1s3.addActionListener(new ActionListener() {
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
		
		item2s1.setIcon(new ImageIcon(new ImageIcon("resources/images/items/totem.gif").getImage()));
		item2s2.setIcon(new ImageIcon(new ImageIcon("resources/images/items/door.gif").getImage()));
		item2s3.setIcon(new ImageIcon(new ImageIcon("resources/images/items/gravity.gif").getImage()));
		item2s4.setIcon(new ImageIcon(new ImageIcon("resources/images/items/platform.gif").getImage()));
		item2s5.setIcon(new ImageIcon(new ImageIcon("resources/images/items/trampoline.gif").getImage()));
		
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
		
		
		item3s1.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/saw.gif").getImage()));
		item3s2.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/rg.gif").getImage()));
		item3s3.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/cannon.gif").getImage()));
		item3s4.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/fly.gif").getImage()));
		item3s5.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/hcannon.gif").getImage()));
		item3s6.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/spinner.gif").getImage()));
		item3s7.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/ricochet.gif").getImage()));
		item3s8.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/dragon.gif").getImage()));
		item3s9.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/warder.gif").getImage()));
		item3s10.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/roaster.gif").getImage()));
		item3s11.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/bh.gif").getImage()));
		
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
	private boolean isDungeon(int dungeonNumber) {
		int[] notDungeons = {50,51,55,59,60,85,86};
		for (int e : notDungeons)
			if (dungeonNumber==e)
				return false;
		return true;
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
	
private final class BoardPanel extends JPanel{
		
		public BoardPanel() {
			super(new GridLayout(8,14));
			for (int i = 0; i < 112; i++)
				add(new TilePanel(this,i));				
		}
}

	private final class TilePanel extends JPanel implements MouseListener{
		private final int tileId;

		public TilePanel(final BoardPanel boardPanel, final int tileId) {
			this.setLayout(new GridBagLayout());
			this.tileId = tileId;
			assignColor(tileId);
			addMouseListener(this);
		}

		private void assignColor(final int tileId) {
			setBackground(Color.white);
			setBorder(new LineBorder(Color.BLACK));
			MainFrame.board[tileId]=true;
		}

		public void mouseClicked(MouseEvent e) {
			setBackground(Color.black);
		}

		public void mousePressed(MouseEvent e) {/*Nothing TODO*/}
		public void mouseReleased(MouseEvent e) {/*Nothing TODO*/}
		public void mouseEntered(MouseEvent e) {/*Nothing TODO*/}
		public void mouseExited(MouseEvent e) {/*Nothing TODO*/}
	}
}
