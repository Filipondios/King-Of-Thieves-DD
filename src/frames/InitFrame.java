package frames;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
/**Class that shows the first, with a image from {@link ImagePanel} and a progress bar.
 * Then it starts the second frame, {@link MainFrame} with the editor panel and workspace.
 * @author Filipondios
 * @version 10.08.2022*/
@SuppressWarnings("serial")
public class InitFrame extends javax.swing.JFrame {
	
	ImagePanel ip = new ImagePanel();
	private javax.swing.JProgressBar jProgressBar1;
	private javax.swing.Timer timer;
	private ActionListener al;
	private int x = 0;
	
	/**Method that sets the basic configurations to the frame and make the instances for 
	 * the {@link JProgressBar} and the image from {@link ImagePanel}.**/
	public InitFrame() {
		this.setContentPane(ip);
		initComponents();
		setLocationRelativeTo(null);
		
		al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				x++;
				jProgressBar1.setValue(x);
				
				if (jProgressBar1.getValue()==100) {
					dispose();
					timer.stop();
					
					//Start the real program					
	                try {
	                	for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
	                        if ("Windows".equals(info.getName())) {
	                            javax.swing.UIManager.setLookAndFeel(info.getClassName());
	                            break;
	                        }
	                    }
					} catch (Exception ex) {
						/*Could do a multi try-catch with ClassNotFoundException, InstantiationException,
						 * IllegalAccessException, UnsupportedLookAndFeelException but its cleaner to
						 * do a single try-catch with the general Exception cause we know that the only thing
						 * we are specting is to get the Windows Theme. Otherwise, the theme will be the default (Metal).*/
						ex.printStackTrace();
					}
					@SuppressWarnings("unused")
					MainFrame tf = new MainFrame();
				}
			}
		};	
		timer = new Timer(20, al);		
		timer.start();
	}
	
	/**Method that sets more basic configurations to the Frame and sets the values for the 
	 * positions of the {@link JProgressBar} and the rest of the pane.**/
	private void initComponents() {
		setUndecorated(true);
		setVisible(true);
		setIconImage(new ImageIcon("resources/images/basic/icon.png").getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		jProgressBar1 = new javax.swing.JProgressBar();

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

/**A simple class that makes visible a image in a {@link JPanel}.
 * @author Filipondios
 * @version 11.08.2022*/
@SuppressWarnings("serial")
class ImagePanel extends JPanel{
	
	private Image image;
	/**Method that overrides the method paint from {@link JPanel} and draws an image.*/
	public void paint(Graphics g) {
		super.paintComponent(g);
		image = new ImageIcon("resources/images/init/logo.png").getImage();
		g.drawImage(image,0,0,getWidth(),getHeight(),null);
	}
}


