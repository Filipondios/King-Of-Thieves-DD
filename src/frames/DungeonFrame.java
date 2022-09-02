package frames;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.ScrollPane;
import java.awt.Toolkit;
import java.util.ArrayList;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**This frame is called {@link MainFrame} in the case that the user selected the menu-bar option
 * to create a new dungeon. In this frame, with the help of a {@link ScrollPane} and a {@link JList}, 
 * the program can representate easily a list with all the available dungeons in the game, so the user
 * can choose between all of them. 
 * @author Filipondios, Hagernaut
 * @version 24.08.2022*/
public class DungeonFrame extends JFrame{
	private JPanel contentPane = new JPanel();
	private JLabel label = new JLabel();
	private JList<String> checkBoxesJList;

	/**Method that starts all the configurations for the Frame. **/
	public DungeonFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen  = tk.getScreenSize();
		setSize(screen.width/8,screen.height/8);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setLayout(new BorderLayout(20,20));
		setTitle("Choose the dungeon");
		setIconImage(new ImageIcon("resources/images/basic/icon.png").getImage());

		/* 125-10 (There are no Bases 1,2,3,50,51,55,59,60,85,86) */
		String listMembers[] = new String[115];
		ArrayList<String> members = new ArrayList<String>();

		for (int i = 4; i < 126; i++)
			members.add("Base["+(i)+"]");

		members.remove(46); //Remove base 50
		members.remove(46); //Remove base 51
		members.remove(49); //Remove base 55
		members.remove(52); //Remove base 59
		members.remove(52); //Remove base 85
		members.remove(76); //Remove base 85
		members.remove(76); //Remove base 86

		for (int i = 0; i < members.size(); i++)
			listMembers[i]=members.get(i);

		checkBoxesJList = new JList<String>(listMembers);
		checkBoxesJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		checkBoxesJList.setFixedCellWidth(getWidth());
		checkBoxesJList.setFixedCellHeight(20);
		checkBoxesJList.setVisibleRowCount(10);

		checkBoxesJList.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				label.setBounds(new Rectangle(0, 0, MainFrame.contentPane.getWidth(),MainFrame.contentPane.getHeight()));

				ImageIcon icon = new ImageIcon(new ImageIcon("resources/images/bases/"+checkBoxesJList.getSelectedValue()+".gif")
						.getImage().getScaledInstance(MainFrame.contentPane.getWidth(), MainFrame.contentPane.getHeight(), Image.SCALE_SMOOTH));

				label.setIcon(icon);
				MainFrame.contentPane.add(label,null);
			}
		});

		DefaultListCellRenderer renderer = (DefaultListCellRenderer) checkBoxesJList.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane scrollPane = new JScrollPane(checkBoxesJList);
		getContentPane().add(scrollPane, BorderLayout.CENTER);
		pack();
	}
}
