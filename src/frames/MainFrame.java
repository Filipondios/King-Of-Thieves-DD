package frames;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import filipondiosUtils.TreeNode;

@SuppressWarnings("serial")
public class TreeFrame extends JFrame{
	
	JTree tree = new JTree();
	JPanel contentPane = new JPanel();
	
	public TreeFrame() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screen  = tk.getScreenSize(); 
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(screen.width/2,screen.height/2);
		setVisible(true);
		setLocationRelativeTo(null);
		
		contentPane.setBorder(new EmptyBorder(5,5,5,5));
		contentPane.setLayout(new BorderLayout());
		setContentPane(contentPane);
		
		contentPane.add(tree, BorderLayout.WEST);
		initView();
	}
	
	private void initView() {
		tree.expandRow(0);
		
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("MATHALL - MENU");
		DefaultMutableTreeNode matrices = new DefaultMutableTreeNode(new TreeNode("@1", "Operaciones con matrices", "closed.png"));
		DefaultMutableTreeNode ecuations = new DefaultMutableTreeNode(new TreeNode("@2", "Operaciones con matrices", "closed.png"));
		
		root.add(matrices);
		root.add(ecuations);
				
		DefaultMutableTreeNode addSub = new DefaultMutableTreeNode(new TreeNode("@1.1", "Addition or subtraction of two matrix", "module.png"));
		DefaultMutableTreeNode prod = new DefaultMutableTreeNode(new TreeNode("@1.2", "Product of two matrix", "module.png"));
		DefaultMutableTreeNode power = new DefaultMutableTreeNode(new TreeNode("@1.3", "Power of any square matrix", "module.png"));
		DefaultMutableTreeNode det = new DefaultMutableTreeNode(new TreeNode("@1.4", "Determinant of any square matrix", "module.png"));
		DefaultMutableTreeNode rank = new DefaultMutableTreeNode(new TreeNode("@1.5", "Rank of a matrix", "module.png"));
		DefaultMutableTreeNode echelon = new DefaultMutableTreeNode(new TreeNode("@1.6", "Row echelon form of a matrix", "module.png"));
		DefaultMutableTreeNode inverse = new DefaultMutableTreeNode(new TreeNode("@1.7", "Inverse of any square matrix", "module.png"));
		DefaultMutableTreeNode traspose = new DefaultMutableTreeNode(new TreeNode("@1.8", "Transpose of a matrix", "module.png"));
		
		matrices.add(addSub);	matrices.add(rank);	
		matrices.add(prod); 	matrices.add(echelon);
		matrices.add(power);	matrices.add(inverse);
		matrices.add(det);		matrices.add(traspose);
		
		DefaultMutableTreeNode secondG = new DefaultMutableTreeNode(new TreeNode("@2.1", "Resolution of second grade equations", "module.png"));
		DefaultMutableTreeNode linear = new DefaultMutableTreeNode(new TreeNode("@2.2", "Resolution linear equations systems", "module.png"));
		
		ecuations.add(secondG);
		ecuations.add(linear);
		
		DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);
		tree.setModel(defaultTreeModel);
		tree.setCellRenderer(new MyNodeTreeCellRender());
	}
	
	private class MyNodeTreeCellRender extends DefaultTreeCellRenderer{

		@Override
		public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
				boolean leaf, int row, boolean hasFocus) {
			Component component = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
			DefaultMutableTreeNode node  = (DefaultMutableTreeNode) value;
			
			if (node.getUserObject() instanceof TreeNode) {
				TreeNode tn = (TreeNode) node.getUserObject();
				setText(tn.getValue());
				ImageIcon icon = new ImageIcon(new ImageIcon("resources/images/"+tn.getIcon()).getImage().getScaledInstance(20, 20, Image.SCALE_DEFAULT));
				setIcon(icon);
				
			}else {
				setLeafIcon(null);
				setClosedIcon(null);
				setOpenIcon(null);
			}
			return component;
		}
		
	}
}
