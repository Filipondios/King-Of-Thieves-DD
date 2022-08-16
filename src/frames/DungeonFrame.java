package frames;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

@SuppressWarnings("serial")
public class DungeonFrame extends JFrame{
	
	JPanel contentPane = new JPanel();
	JScrollPane scrollpane = new JScrollPane();
			
	public DungeonFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen  = tk.getScreenSize(); 
		setSize(screen.width/4,screen.height/4);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setTitle("Choose the desired dungeon");
		setIconImage(new ImageIcon("resources/images/basic/icon.png").getImage());
				
		contentPane.setBorder(new EmptyBorder(5,10,5,10));
		setContentPane(contentPane);		
		
		for (int i = 0; i < 125; i++)
			contentPane.add(new JButton("Base "+i));
	}
}
