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
		setIconImage(new ImageIcon("resources/images/basic/icon.png").getImage());
		setVisible(true);

		add(lpane, BorderLayout.CENTER);
		lpane.setBounds(0, 0, getWidth(), getHeight());

		dungeonPane.setBounds(0, 0, getWidth(), getHeight());
		dungeonPane.setOpaque(true);

		gridPane.setBounds(0, 0, getWidth(), getHeight());
		gridPane.setOpaque(false);

		ImageIcon grid = new ImageIcon(new ImageIcon("resources/images/bases/grid.gif").getImage().
				getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH));
		JLabel gridLabel = new JLabel(grid);
		gridPane.add(gridLabel);
		
		setDungeon(4);

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

		menu.add(index1);
		menu.add(index2);
		menu.add(index3);
		setJMenuBar(menu);
		
		addKeyListener(this);
	}

	private void setDungeon(int dungeonNumber){		
		ImageIcon dungeon2 = new ImageIcon(new ImageIcon("resources/images/bases/"+dungeonNumber+".gif").getImage().
				getScaledInstance(dungeonPane.getWidth(), dungeonPane.getHeight(), Image.SCALE_SMOOTH));

		if (dungeonPane.getComponents().length!=0)
			dungeonPane.remove(0);

		JLabel dungeonLabel2 = new JLabel(dungeon2);
		dungeonPane.add(dungeonLabel2);
	}
	
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
