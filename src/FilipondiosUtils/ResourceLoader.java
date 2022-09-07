package FilipondiosUtils;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/** Class that helps to get the resources faster from the resource folder
 * "resources".
 * @author Filipondios
 * @version 05.09.2022 */
public class ResourceLoader {
	
	/** Method that loads an Image from a specific path. The disired path should start in the
	 * resources package and end in an image. Example: If the route is "/resources/images/basic/icon.png",
	 * then the parameter to this method has to be "/images/basic/icon.png".
	 * @param path String object that represents the path to the desired image.
	 * @return Object of type {@link Image} that represents the desired image.*/
	public Image loadImage(String path) {
		ImageIcon icon = new ImageIcon(this.getClass().getResource(path));
		Image image = icon.getImage();
		if (image == null) { System.out.println("null image");}
		return image;
	}
	
	/** Method that loads an Image from a specific path. The disired path should start in the
	 * resources package and end in an image. Example: If the route is "/resources/images/basic/icon.png",
	 * then the parameter to this method has to be "images/basic/icon.png".
	 * @param path String object that represents the path to the desired image.
	 * @return Object of type {@link Image} that represents the desired image.*/
	public Image loadImage2(String path) {
		InputStream input = ResourceLoader.class.getClassLoader().getResourceAsStream(path);
		java.net.URL imageURL = ResourceLoader.class.getClassLoader().getResource(path);
		
		if (imageURL == null) {
			System.out.println("null URL from filename");
			System.exit(0);
		}
		
		Image image = null;
		try {
			image = ImageIO.read(input);
		} catch (IOException e) { e.printStackTrace(); }
		return image;
	}
}
