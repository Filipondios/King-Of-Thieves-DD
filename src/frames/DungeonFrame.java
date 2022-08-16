package frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DungeonFrame extends JFrame{
	
	private JPanel contentPane = new JPanel();
	private JList<String> checkBoxesJList;
			
	public DungeonFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen  = tk.getScreenSize(); 
		setSize(screen.width/8,screen.height/8);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout(20,20));
		setTitle("Choose the dungeon");
		setIconImage(new ImageIcon("resources/images/basic/icon.png").getImage());
		
		String listMembers[] = new String[125];
		for (int i = 0; i < listMembers.length; i++)
			listMembers[i] = "base"+i;
		
		checkBoxesJList = new JList<String>(listMembers);
		checkBoxesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		checkBoxesJList.setFixedCellWidth(getWidth());
		checkBoxesJList.setFixedCellHeight(20);
		checkBoxesJList.setVisibleRowCount(10);
		 		
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) checkBoxesJList.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);
		
        JScrollPane scrollPane = new JScrollPane(checkBoxesJList);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        pack();
	}
}
