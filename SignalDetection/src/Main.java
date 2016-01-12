import java.awt.EventQueue;

import org.opencv.core.Core;

import com.soniya.gui.DisplayWindow;
import com.soniya.gui.DisplayWindow2;

/**
 * Project Name:
 * 
 * @version
 * @since
 * @author sony
 * @see
 *
 */
public class Main {

	static{
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DisplayWindow2 frame = new DisplayWindow2("Original Image", 400, 500);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
