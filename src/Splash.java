/**
 *
/**
 * @author Rohit Ahuja
 * rohitahuja@email.com
 * 9893075987
 */
package ChatClient;

import javax.swing.*;
import java.awt.*;

public class Splash {

    public static void main(String[] args) {
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screen.width - 480) / 2;
        int y = (screen.height - 500) / 2;
        JWindow win = new JWindow();
        SplashPanel s = new SplashPanel();
        win.setContentPane(s);
        win.setBounds(x, y, 480, 500);
        win.setVisible(true);
        try {
            for (int i = 1; i <= 100; i++) {
                Thread.sleep(50);
                s.abc(i);

            }
        } catch (Exception e) {
        }
        win.setVisible(false);
        JFrame f = new JFrame("Client Login");
        f.setContentPane(new ChatJPanel1());
        f.setBounds(10, 10, 450, 325);
        f.setVisible(true);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
/**
* Vedisoft : Java - Module 1 "Desktop Technologies"
*
*   Vedisoft Software & Education Services Pvt. Ltd.
*   Plot No. 275, Zone II, M.P. Nagar,
*   Bhopal.
*   Phone : 0755 - 4076207, 4076208
*   Email : contact@vedisoft.com
*   Web : www.vedisoft.com
*/