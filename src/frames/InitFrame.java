package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;
import com.formdev.flatlaf.FlatDarkLaf;

/**Class that shows the first, and it's called by {@link MainFrame}, with a image from {@link ImagePanel} and
 * a progress bar from {@link JProgressBar}. Then it starts the second frame, from the class {@link MainFrame}
 * with the editor panel and workspace.
 * <br><br>
 * Note that the progressbar it's only a decorative element for the frame at this version, and it will be like
 * that till all the init processes have much more weight over the application (hevier processes = the aplication
 * starts slower).
 * @author Filipondios, Hagernaut
 * @see MainFrame
 * @version 19.08.2022*/
public class InitFrame extends javax.swing.JFrame {
	
	ImagePanel ip = new ImagePanel();
	private javax.swing.JProgressBar jProgressBar1;
	private final javax.swing.Timer timer;
	private int x = 0;
	
	/**Method that sets the basic configurations to the frame and make the instances for 
	 * the {@link JProgressBar} and the image from {@link ImagePanel}.**/
	public InitFrame() {
		this.setContentPane(ip);
		initComponents();
		setLocationRelativeTo(null);

		/* Try to set the frame Theme to FlatDarkLaf */
		/*Could do a multi try-catch with ClassNotFoundException, InstantiationException,
		 * IllegalAccessException, UnsupportedLookAndFeelException but its cleaner to
		 * do a single try-catch with the general Exception cause we know that the only thing
		 * we are specting is to get the Windows Theme. Otherwise, the theme will be the default (Metal).*/
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				x++;
				jProgressBar1.setValue(x);

				if (jProgressBar1.getValue() == 100) {
					dispose();
					timer.stop();

					/* Try to set the frame Theme to FlatDarkLaf */
					try {
						javax.swing.UIManager.setLookAndFeel(new FlatDarkLaf());
					} catch (Exception ex) {
						/*Could do a multi try-catch with ClassNotFoundException, InstantiationException,
						 * IllegalAccessException, UnsupportedLookAndFeelException but its cleaner to
						 * do a single try-catch with the general Exception cause we know that the only thing
						 * we are specting is to get the Windows Theme. Otherwise, the theme will be the default (Metal).*/
						ex.printStackTrace();
					}

					new MainFrame();
				}
			}
		};	
		/* Changing the first value (x) of Timer(x,y) you set the duration
		 * of the progress bar till is complete.*/
		timer = new Timer(1, al);		
		timer.start();
	}
	
	/**Method that sets more basic configurations to the Frame and sets the values for the 
	 * positions of the {@link JProgressBar} and the rest of the pane.**/
	private void initComponents() {
		/* Basic Frame config */
		setUndecorated(true);
		setVisible(true);
		setIconImage(new ImageIcon("resources/images/basic/icon.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		/* General settings for the bar */
		jProgressBar1 = new javax.swing.JProgressBar();
		jProgressBar1.setBackground(new Color(132, 132, 132));
		jProgressBar1.setForeground(new Color(25, 25, 25));
		jProgressBar1.setBorder(new EmptyBorder(2,5,2,5));
		    	
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 270, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pack();
	}
}

/**A simple class that makes visible a custom image in a {@link JPanel}.
 * @author Filipondios
 * @version 15.08.2022*/
class ImagePanel extends JPanel{

	/**Method that overrides the method paint from {@link JPanel} and draws an image.*/
	public void paint(Graphics g) {
		super.paintComponent(g);
		Image image = new ImageIcon("resources/images/init/logo.png").getImage();
		g.drawImage(image,0,0,getWidth(),getHeight(),null);
	}
}


