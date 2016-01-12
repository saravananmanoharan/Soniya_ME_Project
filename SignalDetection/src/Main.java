import java.awt.EventQueue;
import org.opencv.core.Core;
import com.soniya.common.utils.PropertyValuesFinder;
import com.soniya.gui.DisplayWindow2;

/**
 * Project Name:
 * 
 * @version
 * @since
 * @author sony
 * @see
 */
public class Main {

    static {
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                try {
                    PropertyValuesFinder propertyValuesFinder = PropertyValuesFinder.getInstance();
                    DisplayWindow2 frame = new DisplayWindow2(propertyValuesFinder.getStringValues("MainFrameName")
                                                              , propertyValuesFinder.getIntValues("MainFrameWidth")
                                                              , propertyValuesFinder.getIntValues("MainFramHeight"));
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
