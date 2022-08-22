package frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.RunClass;

/**This will be the main Frame for the application. Called by {@link InitFrame}, this window
 * sets an area where the user can open different dungeons and place traps and other stuff into
 * the workspace created in this Frame. This frame is also linked to other different frames, like
 * {@link DungeonFrame}, in the case that the user selects any option of the menu bar. 
 * @author Filipondios, Hagernaut 
 * @version 19.08.2022**/
@SuppressWarnings("serial")
public class MainFrame extends JFrame{
	
	JPanel contentPane = new JPanel();
	JMenuBar menu = new JMenuBar();
	DungeonFrame dungeonFrame = new DungeonFrame();
	
	/**Method that starts all the configurations for the Frame. **/
	public MainFrame() {
		/*Get the user's screen resolution, so the window will have for the first
		 * time the dimensions: screenWidth/2xscreenHeight/2 */
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen  = tk.getScreenSize(); 
		setSize(screen.width/2,screen.height/2);
		
		/* Common Frame settings and other stuff */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setTitle("King of Thieves Dungeon Dessigner");
		setIconImage(new ImageIcon("resources/images/basic/icon.png").getImage());
		
		/* Create a menu bar for the main application */
		JMenu index1 = new JMenu(); index1.setText("Options");		
		JMenu index2 = new JMenu(); index2.setText("Dungeon Default Stuff");
		JMenu index3 = new JMenu(); index3.setText("Traps");
		
		JMenuItem item1s1 = new JMenuItem("Create/Open new Dungeon");
		JMenuItem item1s2 = new JMenuItem("Restart");
		JMenuItem item1s3 = new JMenuItem("Exit");
		index1.add(item1s1);
		index1.add(item1s2);
		index1.add(item1s3);
		
		item1s1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dungeonFrame.setVisible(true);
			}
		});
		
		item1s2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				final String javaBin = System.getProperty("java.home") + File.separator + "bin" + File.separator + "java";
				File jarDir = null;
				
				try {
					jarDir = new File(ClassLoader.getSystemClassLoader().getResource(".").toURI());
				} catch (URISyntaxException e2) {
					e2.printStackTrace();
				}
				
				String[] dir = jarDir.list();
				String jarFile = "";
				
				for (String obj : dir) {
					if (obj.contains(".jar")) {
						jarFile = obj; break;
					}
				}
						
				final ArrayList<String> command = new ArrayList<String>();
				command.add(javaBin);
				command.add("-jar");
				command.add(jarDir+ File.separator +jarFile);

				final ProcessBuilder builder = new ProcessBuilder(command);
				try {
					builder.start();
				} catch (IOException e1) {
					System.out.println("Start failed");
					e1.printStackTrace();
				}
				System.exit(0);	
			}
		});
		
		item1s3.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		menu.add(index1);
		menu.add(index2);
		menu.add(index3);
		setJMenuBar(menu);
		
		/* Create a content pane, where is going to be placed the editing space
		 * for the images manipulation / dungeon dessingn */
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
	}
}
