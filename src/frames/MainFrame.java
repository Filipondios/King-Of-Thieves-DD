package frames;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.*;

import FilipondiosUtils.ResourceLoader;

/**This will be the main Frame for the application. Called by {@link InitFrame}, this window
 * sets an area where the user can open different dungeons and place traps and other stuff into
 * the workspace created in this Frame.
 * @author Filipondios, Hagernaut 
 * @version 24.08.2022**/
public class MainFrame extends JFrame implements KeyListener{

	private final JLayeredPane lpane = new JLayeredPane();
	private int actualDungeon = 4;
	protected static JPanel dungeonPane = new JPanel();
	protected static JPanel gridPane = new JPanel();
	JMenuBar menu = new JMenuBar();

	/**Method that starts all the configurations for the Frame. **/
	public MainFrame() {
		/*Get the user's screen resolution, so the window will have for the first
		 * time the dimensions: screenWidth/2 x screenHeight/2 */
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen  = tk.getScreenSize();
		setSize(screen.width/2,screen.height/2);

		/* Common Frame settings and other stuff */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("King of Thieves Dungeon Designer");
		setVisible(true);
		setIconImage(new ImageIcon("resources/images/basic/icon.gif").getImage());

		/* Frame Layout config. Creates to Panes in the frame: 
		 * - The one with a grid image that is above the other one.
		 * - A pane with the dungeon scheme, wich the user can change. */
		add(lpane, BorderLayout.CENTER);
		lpane.setBounds(0, 0, getWidth(), getHeight());

		dungeonPane.setBounds(0, 0, getWidth(), getHeight());
		dungeonPane.setOpaque(true);

		gridPane.setBounds(0, 0, getWidth(), getHeight());
		gridPane.setOpaque(false);

		/* Sets the grid image */
		ImageIcon grid = new ImageIcon(new ImageIcon("resources/images/bases/grid.gif").getImage()
				.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
		JLabel gridLabel = new JLabel(grid);
		gridPane.add(gridLabel);
		
		/* Sets the dungeon no.4 as the background of the frame.*/
		setDungeon(4);

		/* Sets both panes one above the other */
		lpane.add(dungeonPane, 0, 0);
		lpane.add(gridPane, 1, 0);

		/* Create a menu bar for the main application */
		/* Add items to the menu-bar */
		JMenu index1 = new JMenu(); index1.setText("Options");
		JMenu index2 = new JMenu(); index2.setText("Dungeon Default Stuff");
		JMenu index3 = new JMenu(); index3.setText("Traps");

		/* Add sub-items to the first item of the menu-bar */
		JMenuItem item1s1 = new JMenuItem("Restart Application");
		JMenuItem item1s2 = new JMenuItem("Exit");
		index1.add(item1s1);
		index1.add(item1s2);
		
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
		//JMenuItem item3s12 = new JMenuItem("Electro Cannon");
		
		
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
		//item3s5.setIcon(new ImageIcon(new ImageIcon("resources/images/traps/.gif").getImage()));
		
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
		menu.add(index1);
		menu.add(index2);
		menu.add(index3);
		setJMenuBar(menu);
		
		/* Adds a listener to the second sub-item of the first item of the menu-bar
		 * (Restart). It restarts the current program. To do so, we search the binary file
		 * of Java. In Windows, it should be like C:\Program Files\Java\jre1.8.0_161\bin\java,
		 * and in Linux should be like /usr/bin/java. Then we see the current path of the running
		 * .jar file, and then it runs this file and closes the app. */
		item1s1.addActionListener(new ActionListener() {
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
		item1s2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		/* Sets as keylistener this frame. This means that every key pressed
		 * by the user with the frame active, will be processed if necessary.*/
		addKeyListener(this);
	}

	/**Method that sets to the background pane of the main frame an image that represents 
	 * the scheme of a dungeon. If the pane is already filled by other image, it removes this one 
	 * and adds the desired image.
	 * @author Filipondios*/
	private void setDungeon(int dungeonNumber){		
		ImageIcon dungeon2 = new ImageIcon(new ImageIcon("resources/images/bases/"+dungeonNumber+".gif").getImage().
				getScaledInstance(dungeonPane.getWidth(), dungeonPane.getHeight(), Image.SCALE_SMOOTH));

		if (dungeonPane.getComponents().length!=0)
			dungeonPane.remove(0);

		JLabel dungeonLabel2 = new JLabel(dungeon2);
		dungeonPane.add(dungeonLabel2);
	}
	
	/**Method that any number that represents a dungeon represents a real dungeon.
	 * @author Filipondios
	 * @return True if the number represents a real dungeon, false if dont.*/
	private boolean isDungeon(int dungeonNumber) {
		int[] notDungeons = {50,51,55,59,60,85,86};
		for (int e : notDungeons)
			if (dungeonNumber==e) { return false; }
		return true;
	}

	@Override
	public void keyPressed(KeyEvent e) { 
		/* Pressing the Right Key, the dungeon changes to the next*/
		if(e.getKeyCode() == KeyEvent.VK_RIGHT && actualDungeon<125) {
			if (isDungeon(actualDungeon+1)) {
				actualDungeon++;
			}else {
				while (!isDungeon(actualDungeon+1)||!isDungeon(actualDungeon))
					actualDungeon++;
			}
		}
		
		/* Pressing the Right Key, the dungeon changes to the next*/
		if(e.getKeyCode() == KeyEvent.VK_LEFT && actualDungeon>4) {
			if (isDungeon(actualDungeon-1)) {
				actualDungeon--;
			}else {
				while (!isDungeon(actualDungeon-1)||!isDungeon(actualDungeon))
					actualDungeon--;
			}
		}
		
		this.setTitle("King of Thieves Dungeon Designer (Base "+actualDungeon+")");
		setDungeon(actualDungeon);
	}
	
	@Override
	public void keyTyped(KeyEvent e) { }

	@Override
	public void keyReleased(KeyEvent e) { }
}
