import javax.swing.*;
import java.awt.*;

public class MainMenu {
    protected static void setMainFrame(){
        JFrame jf = new JFrame();
        JPanel jp = new JPanel(new FlowLayout());
        jf.setSize(500,500);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jf.setVisible(true);
        JLabel jl1 = new JLabel("Hello world!");
        JLabel jl2 = new JLabel("Свабоду чєсним варам!");

        jp.add(jl1);
        jp.add(jl2);

        jf.add(jp);
    }
}
