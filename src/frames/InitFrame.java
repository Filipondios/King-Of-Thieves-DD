package frames;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class InitFrame extends javax.swing.JFrame {
	
	ImagePanel ip = new ImagePanel();
	private javax.swing.JProgressBar jProgressBar1;
	private javax.swing.Timer timer;
	private ActionListener al;
	private int x = 0;
	
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
				}
			}
		};	
		timer = new Timer(15, al);		
		timer.start();
	}
	
	private void initComponents() {
		setUndecorated(true);
		setVisible(true);
		setSize(533,311);
		
		ImagePanel ip = new ImagePanel();
		add(ip);
		
		jProgressBar1 = new javax.swing.JProgressBar();
		jProgressBar1.setBackground(new Color(255,255,255));
		jProgressBar1.setForeground(new Color(85,255,85));
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 553, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 298, Short.MAX_VALUE)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pack();
	}
}

@SuppressWarnings("serial")
class ImagePanel extends JPanel{
	
	private Image image;
	
	public void paint(Graphics g) {
		super.paintComponent(g);
		
		image = new ImageIcon("resources/images/logo300.jpg").getImage();
		
		g.drawImage(image,0,0,getWidth(),getHeight(),null);
		setOpaque(false);
		super.paint(g);
	}
}
