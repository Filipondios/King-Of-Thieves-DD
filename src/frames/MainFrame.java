package frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		JMenu index1 = new JMenu(); index1.setText("Dungeon");		
		JMenu index2 = new JMenu(); index2.setText("Dungeon Default Stuff");
		JMenu index3 = new JMenu(); index3.setText("Traps");
		
		JMenuItem item = new JMenuItem("Create/Open new Dungeon");
		index1.add(item);
		
		item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dungeonFrame.setVisible(true);
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
